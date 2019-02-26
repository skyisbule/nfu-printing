package com.github.skyisbule.print.controller;

import com.github.skyisbule.print.common.BaseHttpResponse;
import com.github.skyisbule.print.domain.Order;
import com.github.skyisbule.print.exception.GlobalException;
import com.github.skyisbule.print.service.OrderService;
import com.github.skyisbule.print.vo.OrderWithShopVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/api/order",method = {RequestMethod.GET,RequestMethod.POST})
@Api(description = "订单相关的接口")
public class OrderController {

    @Autowired
    OrderService orderService;

    @ApiOperation("用户创建一条订单,time和oid不用传")
    @RequestMapping("/create")
    public BaseHttpResponse<String> create(Order order) throws GlobalException {
        try{
            return new BaseHttpResponse<>(orderService.doCreate(order));
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

    @ApiOperation("查看自己提交的订单，分页，")
    @RequestMapping("/get-self")
    public BaseHttpResponse<ArrayList<OrderWithShopVO>> getSelf(@ApiParam("从0开始") Integer page,
                                                                @ApiParam("每页的数据量") Integer pageSize,
                                                                @ApiParam("自己提交的传0，收到的传1")Integer type) throws GlobalException {
        try{
            return new BaseHttpResponse<>(orderService.get(page,pageSize,type));
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
    }

}
