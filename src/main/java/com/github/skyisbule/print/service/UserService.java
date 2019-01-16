package com.github.skyisbule.print.service;

import com.github.skyisbule.print.common.ErrorConstant;
import com.github.skyisbule.print.common.Security;
import com.github.skyisbule.print.dao.UserDao;
import com.github.skyisbule.print.domain.User;
import com.github.skyisbule.print.domain.UserExample;
import com.github.skyisbule.print.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    HttpServletRequest request;

    public String doRegister(User user) throws GlobalException {
        UserExample e = new UserExample();
        e.createCriteria()
                .andNickNameEqualTo(user.getNickName());
        int userCount = (int)userDao.countByExample(e);
        if (userCount>0){
            throw new GlobalException(ErrorConstant.ACCOUNT_ALREADY_EXISTS);
        }
        userDao.insert(user);
        return "注册成功";
    }

    public User getUserByOpenId(String openId) throws GlobalException {
        UserExample e = new UserExample();
        e.createCriteria()
                .andOpenIdEqualTo(openId);
        List<User> users = userDao.selectByExample(e);
        if (users == null || users.size()==0){
            throw new GlobalException(ErrorConstant.USER_NOT_EXISTS);
        }
        User user = users.get(0);
        user.setPasswd(null);
        return user;
    }

    public String doLogin(String account,String passwd) throws GlobalException {
        UserExample e = new UserExample();
        e.createCriteria()
                .andNickNameEqualTo(account)
                .andPasswdEqualTo(passwd);
        List<User> users = userDao.selectByExample(e);
        if (users == null || users.size() == 0)
            throw new GlobalException(ErrorConstant.ACCOUNT_OR_PASSWD_ERROR);
        return Security.encode(users.get(0).getPasswd());
    }

    public User getUser() throws GlobalException {
        int uid = 1;
        String passwd = "";
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("session")){
                passwd = Security.decode(cookie.getValue());
            }
            if (cookie.getName().equals("uid")){
                uid = Integer.parseInt(cookie.getValue());
            }
        }
        UserExample e = new UserExample();
        e.createCriteria()
                .andUidEqualTo(uid)
                .andPasswdEqualTo(passwd);
        List<User> users = userDao.selectByExample(e);
        if (users == null || users.size()==0)
            throw new GlobalException(ErrorConstant.FAILURE_OF_LOGIN_STATUS);
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
