package com.github.skyisbule.print.service;

import com.github.skyisbule.print.common.ErrorConstant;
import com.github.skyisbule.print.dao.OrderDao;
import com.github.skyisbule.print.domain.Order;
import com.github.skyisbule.print.domain.OrderExample;
import com.github.skyisbule.print.domain.User;
import com.github.skyisbule.print.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderDao orderDao;
    @Autowired
    UserService userService;
    @Autowired
    HttpServletRequest request;

    public String doCreate(Order order) throws GlobalException {
        order.setUploadTime(new Date());
        User user;
        try {
            user = userService.getUser(request);
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
        order.setUid(user.getUid());
        order.setOid(null);
        orderDao.insert(order);
        return "创建订单成功";
    }

    public List<Order> get(Integer page,Integer pageSize,Integer type) throws GlobalException {
        User user;
        try {
            user = userService.getUser(request);
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
        final int SELECT_UID = 0;
        final int SELECT_SID = 1;
        if (page == null) page=0;
        if (pageSize == null) pageSize=10;
        if (type == null) type = 0;
        OrderExample e = new OrderExample();
        e.setOffset((long)page*pageSize);
        e.setLimit(pageSize);
        if (type == SELECT_UID){
            e.createCriteria()
                    .andUidEqualTo(user.getUid());
        }else if (type == SELECT_SID){
            e.createCriteria()
                    .andSidEqualTo(user.getUid());
        }else if (user.isAdmin()){
            e.createCriteria();
        }else{
            throw new GlobalException(ErrorConstant.NO_PERMISSION);
        }
        return orderDao.selectByExample(e);
    }

}
