package com.github.skyisbule.print.dao;

import com.github.skyisbule.print.domain.Order;
import com.github.skyisbule.print.domain.OrderExample;
import org.springframework.stereotype.Repository;

/**
 * OrderDao继承基类
 */
@Repository
public interface OrderDao extends MyBatisBaseDao<Order, Integer, OrderExample> {
}