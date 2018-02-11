package com.mx.cash.vip.service.impl;

import com.mx.cash.vip.dao.VipMapper;
import com.mx.cash.vip.model.Vip;
import com.mx.cash.vip.model.VipRecharge;
import com.mx.cash.vip.service.IVipService;
import com.mx.generator.pojo.CashVip;
import com.mx.generator.pojo.CashVipRecharge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 会员管理service
 *
 * @author mx
 */
@Service
@Transactional
public class VipServiceImpl implements IVipService {

    @Autowired
    VipMapper vipMapper;

    @Override
    public List<Vip> getVipInfoGridData(Map<String, String> map) {
        return vipMapper.getVipInfoGridData(map);
    }


    @Override
    public Vip getVipInfoById(Integer id) {
        return vipMapper.getVipInfoById(id);
    }

    @Override
    public void editVip(Vip vip) {
        if (vip.getId() == null) {
            vipMapper.addVip(vip);
        } else {
            vipMapper.editVip(vip);
        }
    }

    @Override
    public List<Vip> getVipFindGrid() {
        return vipMapper.getVipFindGrid();
    }

    @Override
    public void addRecharge(VipRecharge vipRecharge) {
        vipMapper.addRecharge(vipRecharge);
    }

    @Override
    public List<VipRecharge> getVipRechargeGridData(Map<String, String> map) {
        return vipMapper.getVipRechargeGridData(map);
    }

    @Override
    public VipRecharge getVipRechargeInfoById(Integer id) {
        return vipMapper.getVipRechargeInfoById(id);
    }

}
