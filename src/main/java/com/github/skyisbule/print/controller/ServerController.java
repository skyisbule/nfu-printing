package com.github.skyisbule.print.controller;

import com.github.skyisbule.print.exception.ExceptionMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping(value = "/api/server",method = RequestMethod.GET)
@RestController
@Api(description = "服务端信息同步接口")
public class ServerController {

    @RequestMapping("/get-code-info")
    @ApiOperation("获取所有的状态码及其信息")
    public Map<String,String> getAllCodeInfo(){
        return ExceptionMessage.message;
    }

}
