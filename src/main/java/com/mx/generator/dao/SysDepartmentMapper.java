package com.mx.generator.dao;

import com.mx.generator.pojo.SysDepartment;

public interface SysDepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysDepartment record);

    int insertSelective(SysDepartment record);

    SysDepartment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysDepartment record);

    int updateByPrimaryKey(SysDepartment record);
}