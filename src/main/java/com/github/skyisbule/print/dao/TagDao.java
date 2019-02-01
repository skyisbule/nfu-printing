package com.github.skyisbule.print.dao;

import com.github.skyisbule.print.domain.Tag;
import com.github.skyisbule.print.domain.TagExample;
import org.springframework.stereotype.Repository;

/**
 * TagDao继承基类
 */
@Repository
public interface TagDao extends MyBatisBaseDao<Tag, Integer, TagExample> {
}