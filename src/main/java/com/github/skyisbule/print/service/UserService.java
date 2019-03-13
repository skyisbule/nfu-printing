package com.github.skyisbule.print.service;

import com.github.skyisbule.print.common.ErrorConstant;
import com.github.skyisbule.print.common.Security;
import com.github.skyisbule.print.dao.UserDao;
import com.github.skyisbule.print.domain.User;
import com.github.skyisbule.print.domain.UserExample;
import com.github.skyisbule.print.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    HttpServletRequest request;

    @Transactional(rollbackFor = Exception.class)
    public synchronized User doRegister(User user,HttpServletResponse response) throws GlobalException {
        if (user.getNickName() == null || user.getPasswd() ==null){
            throw new GlobalException("用户名密码不能为空");
        }
        if (user.getHeadPic() == null)
            user.setHeadPic("https://pic.warehouse.saiwoyun.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20181204140127.jpg");
        user.setUid(null);
        user.setOpenShop(0);
        UserExample e = new UserExample();
        e.createCriteria()
                .andNickNameEqualTo(user.getNickName());
        int userCount = (int) userDao.countByExample(e);
        if (userCount > 0) {
            throw new GlobalException(ErrorConstant.ACCOUNT_ALREADY_EXISTS);
        }
        userDao.insert(user);
        int uid = userDao.getMaxForUser();
        user.setUid(uid);
        if (user.getPasswd() != null)
            user.setOpenId(Security.encode(user.getPasswd()));
        writeLoginCookie(uid,user.getPasswd(),response);
        return user;
    }

    private void writeLoginCookie(Integer uid,String passwd,HttpServletResponse response){
        Cookie idCookie = new Cookie("uid",uid.toString());
        idCookie.setPath("/");
        Cookie session  = new Cookie("session",passwd);
        session.setPath("/");
        response.addCookie(idCookie);
        response.addCookie(session);
    }

    public User getUserByOpenId(String openId) throws GlobalException {
        UserExample e = new UserExample();
        e.createCriteria()
                .andOpenIdEqualTo(openId);
        List<User> users = userDao.selectByExample(e);
        if (users == null || users.size() == 0) {
            throw new GlobalException(ErrorConstant.USER_NOT_EXISTS);
        }
        User user = users.get(0);
        user.setPasswd(null);
        return user;
    }

    public User doLogin(String account, String passwd,HttpServletResponse response) throws GlobalException {
        UserExample e = new UserExample();
        e.createCriteria()
                .andNickNameEqualTo(account)
                .andPasswdEqualTo(passwd);
        List<User> users = userDao.selectByExample(e);
        if (users == null || users.size() == 0)
            throw new GlobalException(ErrorConstant.ACCOUNT_OR_PASSWD_ERROR);
        User result = users.get(0);
        //result.setPasswd(Security.encode(passwd));
        result.setOpenId(Security.encode(passwd));
        writeLoginCookie(result.getUid(),result.getOpenId(),response);
        return result;
    }

    public User getUser(HttpServletRequest request) throws GlobalException {
        int uid = 1;
        String passwd = "";
        //这里增加一下尝试用请求头获取用户凭证
        String accessToken = request.getHeader("accessToken");
        //使用请求头鉴权，否则使用cookie鉴权
        if (accessToken != null){
            String[] strs = accessToken.split("-");
            uid = Integer.parseInt(strs[0]);
            passwd = Security.decode(strs[1]);
        }else {
            if (request.getCookies() == null)
                throw new GlobalException(ErrorConstant.FAILURE_OF_LOGIN_STATUS);
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("session")) {
                    passwd = Security.decode(cookie.getValue());
                }
                if (cookie.getName().equals("uid")) {
                    uid = Integer.parseInt(cookie.getValue());
                }
            }
        }
        if (uid == 1 && passwd.equals(""))
            throw new GlobalException(ErrorConstant.FAILURE_OF_LOGIN_STATUS);
        UserExample e = new UserExample();
        e.createCriteria()
                .andUidEqualTo(uid)
                .andPasswdEqualTo(passwd);
        List<User> users = userDao.selectByExample(e);
        if (users == null || users.size() == 0)
            throw new GlobalException(ErrorConstant.FAILURE_OF_LOGIN_STATUS);
        users.get(0).setPasswd("此接口不返回密码");
        users.get(0).setOpenId("此接口不返回openId");
        return users.get(0);
    }

    public User getByUid(Integer uid) throws GlobalException {
        User user = userDao.selectByPrimaryKey(uid);
        if (user == null)
            throw new GlobalException(ErrorConstant.UID_NOT_EXISTS);
        user.setPasswd(null);
        user.setOpenId(null);
        return user;
    }

    public String doUpdate(User updateUser) throws GlobalException {
        User requestUser;
        try {
            requestUser = this.getUser(request);
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
        if (updateUser.getUid() == null){
            updateUser.setUid(requestUser.getUid());
        }
        if (requestUser.isAdmin()){
            userDao.updateByPrimaryKeySelective(updateUser);
            return "更新成功";
        }
        if (!Objects.equals(requestUser.getUid(), updateUser.getUid())){
            throw new GlobalException(ErrorConstant.NO_PERMISSION);
        }
        updateUser.setNickName(requestUser.getNickName());
        updateUser.setOpenShop(requestUser.getOpenShop());
        userDao.updateByPrimaryKeySelective(updateUser);
        return "更新成功";
    }

}
