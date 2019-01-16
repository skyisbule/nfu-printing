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

@RestController
@RequestMapping(value = "/api/user",method = {RequestMethod.GET,RequestMethod.POST})
@Api(description = "用户相关的接口")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation("用户注册接口")
    @RequestMapping("/register")
    public BaseHttpResponse<String> doRegister(User user) throws GlobalException {
        try{
            return new BaseHttpResponse<>(userService.doRegister(user));
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @ApiOperation("登录接口,若成功则会返回密匙，请前端自行把它写入cookie,key为session,另外还要再将用户id写入cookie，key为uid")
    @RequestMapping("/login")
    public BaseHttpResponse<String> checkNickName(String account,String passwd) throws GlobalException {
        try{
            return new BaseHttpResponse<>(userService.doLogin(account,passwd));
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
    public BaseHttpResponse<User> getStatus() throws GlobalException {
        try {
            return new BaseHttpResponse<>(userService.getUser());
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

}
