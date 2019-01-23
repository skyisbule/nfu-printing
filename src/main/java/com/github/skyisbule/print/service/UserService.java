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
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Transactional(rollbackFor = Exception.class)
    public User doRegister(User user) throws GlobalException {
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
            user.setPasswd(Security.encode(user.getPasswd()));
        return user;
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

    public User doLogin(String account, String passwd) throws GlobalException {
        UserExample e = new UserExample();
        e.createCriteria()
                .andNickNameEqualTo(account)
                .andPasswdEqualTo(passwd);
        List<User> users = userDao.selectByExample(e);
        if (users == null || users.size() == 0)
            throw new GlobalException(ErrorConstant.ACCOUNT_OR_PASSWD_ERROR);
        User result = users.get(0);
        result.setPasswd(Security.encode(passwd));
        result.setOpenId("此接口不返回openid");
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
        return user;
    }

}
