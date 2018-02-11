package com.mx.generator.pojo;

import com.mx.common.pojo.BaseBean;
import java.math.BigDecimal;
import java.util.Date;

public class CashVip extends BaseBean {
    private Integer id;

    /**
     * 会员名
     */
    private String name;

    /**
     * 会员编号
     */
    private String number;

    /**
     * 会员类别
     */
    private Byte type;

    /**
     * 余额
     */
    private BigDecimal remain;

    /**
     * 积分
     */
    private Integer point;

    /**
     * 开户日期
     */
    private Date openTime;

    /**
     * 性别，0女1男
     */
    private Byte sex;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 微信号码
     */
    private String weixin;

    /**
     * QQ号码
     */
    private String qq;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 备注
     */
    private String memo;

    /**
     * 是否禁用0正常，1禁用
     */
    private Byte enablef;

    /**
     * 是否删除，0正常，1删除
     */
    private Byte deletef;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获得会员名
     * @return name 会员名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置会员名
     * @param name 会员名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获得会员编号
     * @return number 会员编号
     */
    public String getNumber() {
        return number;
    }

    /**
     * 设置会员编号
     * @param number 会员编号
     */
    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    /**
     * 获得会员类别
     * @return type 会员类别
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置会员类别
     * @param type 会员类别
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 获得余额
     * @return remain 余额
     */
    public BigDecimal getRemain() {
        return remain;
    }

    /**
     * 设置余额
     * @param remain 余额
     */
    public void setRemain(BigDecimal remain) {
        this.remain = remain;
    }

    /**
     * 获得积分
     * @return point 积分
     */
    public Integer getPoint() {
        return point;
    }

    /**
     * 设置积分
     * @param point 积分
     */
    public void setPoint(Integer point) {
        this.point = point;
    }

    /**
     * 获得开户日期
     * @return open_time 开户日期
     */
    public Date getOpenTime() {
        return openTime;
    }

    /**
     * 设置开户日期
     * @param openTime 开户日期
     */
    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    /**
     * 获得性别，0女1男
     * @return sex 性别，0女1男
     */
    public Byte getSex() {
        return sex;
    }

    /**
     * 设置性别，0女1男
     * @param sex 性别，0女1男
     */
    public void setSex(Byte sex) {
        this.sex = sex;
    }

    /**
     * 获得邮箱
     * @return mail 邮箱
     */
    public String getMail() {
        return mail;
    }

    /**
     * 设置邮箱
     * @param mail 邮箱
     */
    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    /**
     * 获得手机号码
     * @return phone 手机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号码
     * @param phone 手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获得微信号码
     * @return weixin 微信号码
     */
    public String getWeixin() {
        return weixin;
    }

    /**
     * 设置微信号码
     * @param weixin 微信号码
     */
    public void setWeixin(String weixin) {
        this.weixin = weixin == null ? null : weixin.trim();
    }

    /**
     * 获得QQ号码
     * @return qq QQ号码
     */
    public String getQq() {
        return qq;
    }

    /**
     * 设置QQ号码
     * @param qq QQ号码
     */
    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    /**
     * 获得出生日期
     * @return birthday 出生日期
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置出生日期
     * @param birthday 出生日期
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    /**
     * 获得是否禁用0正常，1禁用
     * @return enablef 是否禁用0正常，1禁用
     */
    public Byte getEnablef() {
        return enablef;
    }

    /**
     * 设置是否禁用0正常，1禁用
     * @param enablef 是否禁用0正常，1禁用
     */
    public void setEnablef(Byte enablef) {
        this.enablef = enablef;
    }

    /**
     * 获得是否删除，0正常，1删除
     * @return deletef 是否删除，0正常，1删除
     */
    public Byte getDeletef() {
        return deletef;
    }

    /**
     * 设置是否删除，0正常，1删除
     * @param deletef 是否删除，0正常，1删除
     */
    public void setDeletef(Byte deletef) {
        this.deletef = deletef;
    }
}