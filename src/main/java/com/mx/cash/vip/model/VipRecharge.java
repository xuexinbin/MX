package com.mx.cash.vip.model;

import com.mx.generator.pojo.CashVipRecharge;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class VipRecharge extends CashVipRecharge {

    /**
     * 充值日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date rechargeTime;

    /**
     * 会员名
     */
    private String vipName;

    /**
     * 会员号
     */
    private String vipNumber;

    /**
     * 会员手机
     */
    private String vipPhone;

    private BigDecimal vipRemain;

    @Override
    public Date getRechargeTime() {
        return rechargeTime;
    }

    @Override
    public void setRechargeTime(Date rechargeTime) {
        this.rechargeTime = rechargeTime;
    }

    public String getVipName() {
        return vipName;
    }

    public void setVipName(String vipName) {
        this.vipName = vipName;
    }

    public String getVipNumber() {
        return vipNumber;
    }

    public void setVipNumber(String vipNumber) {
        this.vipNumber = vipNumber;
    }

    public String getVipPhone() {
        return vipPhone;
    }

    public void setVipPhone(String vipPhone) {
        this.vipPhone = vipPhone;
    }

    public BigDecimal getVipRemain() {
        return vipRemain;
    }

    public void setVipRemain(BigDecimal vipRemain) {
        this.vipRemain = vipRemain;
    }
}