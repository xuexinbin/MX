package com.mx.generator.pojo;

import com.mx.common.pojo.BaseBean;
import java.math.BigDecimal;
import java.util.Date;

public class CashVipRecharge extends BaseBean {
    private Integer id;

    /**
     * 会员id
     */
    private Integer vipId;

    /**
     * 充值方式：0现金 1微信 2支付宝 3网银 4刷卡 5其他
     */
    private Byte rechargeType;

    /**
     * 充值金额/退款金额
     */
    private BigDecimal money;

    /**
     * 赠送充值金额
     */
    private BigDecimal giveMoney;

    /**
     * 赠送积分
     */
    private BigDecimal givePoint;

    /**
     * 0:充值 1:退款
     */
    private Byte type;

    /**
     * 充值日期
     */
    private Date rechargeTime;

    /**
     * 经办人
     */
    private String operator;

    /**
     * 备注
     */
    private String memo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获得会员id
     * @return vip_id 会员id
     */
    public Integer getVipId() {
        return vipId;
    }

    /**
     * 设置会员id
     * @param vipId 会员id
     */
    public void setVipId(Integer vipId) {
        this.vipId = vipId;
    }

    /**
     * 获得充值方式：0现金 1微信 2支付宝 3网银 4刷卡 5其他
     * @return recharge_type 充值方式：0现金 1微信 2支付宝 3网银 4刷卡 5其他
     */
    public Byte getRechargeType() {
        return rechargeType;
    }

    /**
     * 设置充值方式：0现金 1微信 2支付宝 3网银 4刷卡 5其他
     * @param rechargeType 充值方式：0现金 1微信 2支付宝 3网银 4刷卡 5其他
     */
    public void setRechargeType(Byte rechargeType) {
        this.rechargeType = rechargeType;
    }

    /**
     * 获得充值金额/退款金额
     * @return money 充值金额/退款金额
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * 设置充值金额/退款金额
     * @param money 充值金额/退款金额
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * 获得赠送充值金额
     * @return give_money 赠送充值金额
     */
    public BigDecimal getGiveMoney() {
        return giveMoney;
    }

    /**
     * 设置赠送充值金额
     * @param giveMoney 赠送充值金额
     */
    public void setGiveMoney(BigDecimal giveMoney) {
        this.giveMoney = giveMoney;
    }

    /**
     * 获得赠送积分
     * @return give_point 赠送积分
     */
    public BigDecimal getGivePoint() {
        return givePoint;
    }

    /**
     * 设置赠送积分
     * @param givePoint 赠送积分
     */
    public void setGivePoint(BigDecimal givePoint) {
        this.givePoint = givePoint;
    }

    /**
     * 获得0:充值 1:退款
     * @return type 0:充值 1:退款
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置0:充值 1:退款
     * @param type 0:充值 1:退款
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 获得充值日期
     * @return recharge_time 充值日期
     */
    public Date getRechargeTime() {
        return rechargeTime;
    }

    /**
     * 设置充值日期
     * @param rechargeTime 充值日期
     */
    public void setRechargeTime(Date rechargeTime) {
        this.rechargeTime = rechargeTime;
    }

    /**
     * 获得经办人
     * @return operator 经办人
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 设置经办人
     * @param operator 经办人
     */
    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    /**
     * 获得备注
     * @return memo 备注
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 设置备注
     * @param memo 备注
     */
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}