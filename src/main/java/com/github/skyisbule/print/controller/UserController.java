package com.github.skyisbule.print.controller;

import com.github.skyisbule.print.common.BaseHttpResponse;
import com.github.skyisbule.print.domain.User;
import com.github.skyisbule.print.exception.GlobalException;
import com.github.skyisbule.print.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/api/user",method = {RequestMethod.GET,RequestMethod.POST})
@Api(description = "用户相关的接口")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation("用户注册接口,会返回uid，且若是用户名密码注册，则passwd字段会返回加密后的value，请前端直接将其写入cookie，uid同理，和登录接口一样。")
    @RequestMapping("/register")
    public BaseHttpResponse<User> doRegister(User user,HttpServletResponse response) throws GlobalException {
        try{
            return new BaseHttpResponse<>(userService.doRegister(user,response));
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @ApiOperation("登录接口,若成功则会返回密匙，请前端自行把它写入cookie,key为session,另外还要再将用户id写入cookie，key为uid。或者也可以用请求头鉴权，key为：accessToken，value为：uid-token，以-分割。")
    @RequestMapping("/login")
    public BaseHttpResponse<User> checkNickName(String account,String passwd,HttpServletResponse response) throws GlobalException {
        try{
            return new BaseHttpResponse<>(userService.doLogin(account,passwd,response));
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @ApiOperation("根据openid获取用户除密码以外的信息")
    @RequestMapping("/get-user-by-openid")
    public BaseHttpResponse<User> getUserByOpenid(String openid) throws GlobalException {
        try{
            return new BaseHttpResponse<>(userService.getUserByOpenId(openid));
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @ApiOperation("验证当前用户的登陆状态")
    @RequestMapping("/check-self")
    public BaseHttpResponse<User> getStatus(HttpServletRequest request) throws GlobalException {
        try {
            return new BaseHttpResponse<>(userService.getUser(request));
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @ApiOperation("根据uid获取用户信息")
    @RequestMapping("/get-by-uid")
    public BaseHttpResponse<User> getByUid(Integer uid) throws GlobalException {
        try {
            return new BaseHttpResponse<>(userService.getByUid(uid));
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @ApiOperation("更新用户信息，管理员用户可以更新所有人的信息，而其他用户只能更新自己的。")
    @RequestMapping("/update-by-uid")
    public BaseHttpResponse<String> doUpdate(User user) throws GlobalException {
        try {
            return new BaseHttpResponse<>(userService.doUpdate(user));
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

}
