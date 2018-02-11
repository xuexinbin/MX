package com.mx.generator.dao;

import com.mx.generator.pojo.CashVip;

public interface CashVipMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CashVip record);

    int insertSelective(CashVip record);

    CashVip selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CashVip record);

    int updateByPrimaryKey(CashVip record);
}