package com.github.skyisbule.print.controller;

import com.github.skyisbule.print.common.BaseHttpResponse;
import com.github.skyisbule.print.domain.Shop;
import com.github.skyisbule.print.exception.GlobalException;
import com.github.skyisbule.print.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/shop",method = {RequestMethod.GET,RequestMethod.POST})
@Api(description = "店铺相关的接口")
public class ShopController {

    @Autowired
    ShopService shopService;

    @ApiOperation("获取所有的店铺，因为店铺数量注定不多所以后台不做分页")
    @RequestMapping("/index")
    public BaseHttpResponse<List<Shop>> index() throws GlobalException {
        try{
            return new BaseHttpResponse<>(shopService.index());
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @ApiOperation("创建自己的店铺")
    @RequestMapping("/create")
    public BaseHttpResponse<String> doCreate(@ApiParam("不用传sid和openUp") Shop shop) throws GlobalException {
        try{
            return new BaseHttpResponse<>(shopService.doCreate(shop));
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @ApiOperation("将自己的店铺状态改为开店")
    @RequestMapping("/open")
    public BaseHttpResponse<String> open() throws GlobalException {
        try{
            return new BaseHttpResponse<>(shopService.open(1));
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @ApiOperation("将自己的店铺状态改为关店")
    @RequestMapping("/close")
    public BaseHttpResponse<String> close() throws GlobalException {
        try{
            return new BaseHttpResponse<>(shopService.open(0));
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

}
