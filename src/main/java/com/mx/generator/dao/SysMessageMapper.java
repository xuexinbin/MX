package com.mx.generator.dao;

import com.mx.generator.pojo.SysMessage;

public interface SysMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysMessage record);

    int insertSelective(SysMessage record);

    SysMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysMessage record);

    int updateByPrimaryKeyWithBLOBs(SysMessage record);

    int updateByPrimaryKey(SysMessage record);
}