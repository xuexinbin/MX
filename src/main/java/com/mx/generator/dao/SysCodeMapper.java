package com.mx.generator.dao;

import com.mx.generator.pojo.SysCode;

public interface SysCodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysCode record);

    int insertSelective(SysCode record);

    SysCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysCode record);

    int updateByPrimaryKeyWithBLOBs(SysCode record);

    int updateByPrimaryKey(SysCode record);
}