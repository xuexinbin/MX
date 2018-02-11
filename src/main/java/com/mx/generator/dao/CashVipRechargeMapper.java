package com.mx.generator.dao;

import com.mx.generator.pojo.CashVipRecharge;

public interface CashVipRechargeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CashVipRecharge record);

    int insertSelective(CashVipRecharge record);

    CashVipRecharge selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CashVipRecharge record);

    int updateByPrimaryKey(CashVipRecharge record);
}