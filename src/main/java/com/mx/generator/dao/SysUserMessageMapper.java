package com.mx.generator.dao;

import com.mx.generator.pojo.SysUserMessage;

public interface SysUserMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUserMessage record);

    int insertSelective(SysUserMessage record);

    SysUserMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUserMessage record);

    int updateByPrimaryKey(SysUserMessage record);
}