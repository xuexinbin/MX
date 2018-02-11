package com.mx.cash.vip.service;

import com.mx.cash.vip.model.Vip;
import com.mx.cash.vip.model.VipRecharge;
import com.mx.generator.pojo.CashVip;
import com.mx.generator.pojo.CashVipRecharge;

import java.util.List;
import java.util.Map;

/**
 * 会员管理service
 *
 * @author mx
 */
public interface IVipService {

    /**
     * 获得会员管理grid数据
     *
     * @param map 查询信息
     * @return list
     */
    List<Vip> getVipInfoGridData(Map<String, String> map);

    /**
     * 获得会员详细信息
     *
     * @param id 会员id
     * @return vip
     */
    Vip getVipInfoById(Integer id);

    /**
     * 更新会员
     *
     * @param vip 会员信息
     */
    void editVip(Vip vip);

    /**
     * 获得充值会员findGrid
     * @return list
     */
    List<Vip> getVipFindGrid();

    /**
     * 添加充值信息
     * @param vipRecharge 充值信息
     */
    void addRecharge(VipRecharge vipRecharge);

    /**
     * 获得充值grid数据
     * @param map 查询条件
     * @return list
     */
    List<VipRecharge> getVipRechargeGridData(Map<String, String> map);

    /**
     * 获得充值详细信息
     * @param id id
     * @return 充值信息
     */
    VipRecharge getVipRechargeInfoById(Integer id);
}
