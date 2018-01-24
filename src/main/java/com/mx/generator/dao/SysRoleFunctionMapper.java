package com.mx.generator.dao;

import com.mx.generator.pojo.SysRoleFunction;

public interface SysRoleFunctionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRoleFunction record);

    int insertSelective(SysRoleFunction record);

    SysRoleFunction selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRoleFunction record);

    int updateByPrimaryKey(SysRoleFunction record);
}