package com.github.skyisbule.print.service;

import com.github.skyisbule.print.common.ErrorConstant;
import com.github.skyisbule.print.dao.ShopDao;
import com.github.skyisbule.print.dao.UserDao;
import com.github.skyisbule.print.domain.Shop;
import com.github.skyisbule.print.domain.ShopExample;
import com.github.skyisbule.print.domain.User;
import com.github.skyisbule.print.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Service
public class ShopService {

    @Autowired
    ShopDao shopDao;
    @Autowired
    UserService userService;
    @Autowired
    UserDao userDao;
    @Autowired
    HttpServletRequest request;

    public String doCreate(Shop shop) throws GlobalException {
        User user = null;
        try {
            user = userService.getUser(request);
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
        shop.setSid(user.getUid());
        shop.setOpenUp(1);
        try {
            user = userDao.selectByPrimaryKey(user.getUid());
            shopDao.doInsert(shop);
            user.setOpenShop(1);
            userDao.updateByPrimaryKey(user);
            return "店铺创建成功";
        }catch (Exception e){
            return "您已经创建过店铺了";
        }

    }

    public List<Shop> index(){
        ShopExample e = new ShopExample();
        e.setOrderByClause("open_up desc");
        return shopDao.selectByExample(e);
    }

    public Shop getById(Integer id){
        if (id == null) id = 1;
        return shopDao.selectByPrimaryKey(id);
    }

    public String open(int status) throws GlobalException {
        User user = null;
        try {
            user = userService.getUser(request);
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
        shopDao.updateOpenStatus(status,user.getUid());
        return "店铺状态修改成功";
    }

    public String open(int status,int shopId){
        shopDao.updateOpenStatus(status,shopId);
        return "店铺状态修改成功";
    }

    public String doUpdate(Shop shop) throws GlobalException {
        User user;
        try {
            user = userService.getUser(request);
        }catch (Exception e){
            throw new GlobalException(e.getMessage());
        }
        if (shop.getSid() == null)
            throw new GlobalException(ErrorConstant.SHOP_ID_IS_NULL);
        if (!Objects.equals(user.getUid(), shop.getSid())){
            if (!user.isAdmin())
                throw new GlobalException(ErrorConstant.NO_PERMISSION);
        }
        if (shop.getOpenUp() != null ){
            if (shop.getOpenUp() != 0 && shop.getOpenUp() !=1)
                shop.setOpenUp(null);
        }
        shopDao.updateByPrimaryKeySelective(shop);
        return "修改店铺信息成功";
    }

}
