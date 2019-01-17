package com.github.skyisbule.print.dao;

import com.github.skyisbule.print.domain.User;
import com.github.skyisbule.print.domain.UserExample;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * UserDao继承基类
 */
@Repository
public interface UserDao extends MyBatisBaseDao<User, Integer, UserExample> {

    @Select("select count(*) from db_user")
    public int getMaxForUser();

}