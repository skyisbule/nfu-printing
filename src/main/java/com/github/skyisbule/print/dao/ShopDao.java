package com.github.skyisbule.print.dao;

import com.github.skyisbule.print.domain.Shop;
import com.github.skyisbule.print.domain.ShopExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * ShopDao继承基类
 */
@Repository
public interface ShopDao extends MyBatisBaseDao<Shop, Integer, ShopExample> {

    @Update("update db_shop set open_up = #{status} where sid = #{shopId}")
    public void updateOpenStatus(@Param("status")int status,
                                 @Param("shopId")int shopId);

}