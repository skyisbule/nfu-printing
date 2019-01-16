package com.github.skyisbule.print.controller;

import com.github.skyisbule.print.common.BaseHttpResponse;
import com.github.skyisbule.print.exception.GlobalException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user",method = {RequestMethod.GET,RequestMethod.POST})
@Api(description = "用户相关的接口")
public class UserController {

    @ApiOperation("用户注册接口")
    @RequestMapping("/register")
    public BaseHttpResponse<String> doRegister() throws GlobalException {
        try{
            //注册
            return new BaseHttpResponse<>("注册成功");
        }catch (Exception e){
            throw new GlobalException("100");
        }
    }

}
