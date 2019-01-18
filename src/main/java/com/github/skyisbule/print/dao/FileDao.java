package com.github.skyisbule.print.dao;

import com.github.skyisbule.print.domain.DbFile;
import com.github.skyisbule.print.domain.DbFileExample;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * FileDao继承基类
 */
@Repository
public interface FileDao extends MyBatisBaseDao<DbFile, Integer, DbFileExample> {

    @Select("select max(fid) from db_file")
    public Integer getMaxFid();

}