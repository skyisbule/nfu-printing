package com.github.skyisbule.print.dao;

import com.github.skyisbule.print.domain.Tag;
import com.github.skyisbule.print.domain.TagExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * TagDao继承基类
 */
@Repository
public interface TagDao extends MyBatisBaseDao<Tag, Integer, TagExample> {

    @Update("update db_tag set used_count = used_count + 1 where tid = ${tid}")
    void countIns1(@Param("tid")int tid);

}