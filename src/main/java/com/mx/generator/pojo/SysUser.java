package com.mx.generator.pojo;

import com.mx.common.pojo.BaseBean;
import java.util.Date;

public class SysUser extends BaseBean {
    private Integer id;

    /**
     * 真实姓名
     */
    private String trueName;

    /**
     * 工号
     */
    private String number;

    /**
     * 所属部门id
     */
    private Integer departmentId;

    /**
     * 角色Id ','拼接
     */
    private String roleIds;

    /**
     * 用户登录名
     */
    private String userName;

    /**
     * 登录密码
     */
    private String password;

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
     * 头像地址
     */
    private String avatar;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 入职时间
     */
    private Date joinTime;

    /**
     * 删除／离职时间
     */
    private Date deleteTime;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    /**
     * 登录次数
     */
    private Integer loginTimes;

    /**
     * 备注
     */
    private String memo;

    /**
     * 是否禁用0正常，1禁用
     */
    private Byte enablef;

    /**
     * 是否删除／离职，0正常，1删除离职
     */
    private Byte deletef;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获得真实姓名
     * @return true_name 真实姓名
     */
    public String getTrueName() {
        return trueName;
    }

    /**
     * 设置真实姓名
     * @param trueName 真实姓名
     */
    public void setTrueName(String trueName) {
        this.trueName = trueName == null ? null : trueName.trim();
    }

    /**
     * 获得工号
     * @return number 工号
     */
    public String getNumber() {
        return number;
    }

    /**
     * 设置工号
     * @param number 工号
     */
    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    /**
     * 获得所属部门id
     * @return department_id 所属部门id
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * 设置所属部门id
     * @param departmentId 所属部门id
     */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * 获得角色Id ','拼接
     * @return role_ids 角色Id ','拼接
     */
    public String getRoleIds() {
        return roleIds;
    }

    /**
     * 设置角色Id ','拼接
     * @param roleIds 角色Id ','拼接
     */
    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds == null ? null : roleIds.trim();
    }

    /**
     * 获得用户登录名
     * @return user_name 用户登录名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户登录名
     * @param userName 用户登录名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获得登录密码
     * @return password 登录密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置登录密码
     * @param password 登录密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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
     * 获得头像地址
     * @return avatar 头像地址
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置头像地址
     * @param avatar 头像地址
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
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
     * 获得入职时间
     * @return join_time 入职时间
     */
    public Date getJoinTime() {
        return joinTime;
    }

    /**
     * 设置入职时间
     * @param joinTime 入职时间
     */
    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    /**
     * 获得删除／离职时间
     * @return delete_time 删除／离职时间
     */
    public Date getDeleteTime() {
        return deleteTime;
    }

    /**
     * 设置删除／离职时间
     * @param deleteTime 删除／离职时间
     */
    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    /**
     * 获得最后登录时间
     * @return last_login_time 最后登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置最后登录时间
     * @param lastLoginTime 最后登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获得登录次数
     * @return login_times 登录次数
     */
    public Integer getLoginTimes() {
        return loginTimes;
    }

    /**
     * 设置登录次数
     * @param loginTimes 登录次数
     */
    public void setLoginTimes(Integer loginTimes) {
        this.loginTimes = loginTimes;
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
     * 获得是否删除／离职，0正常，1删除离职
     * @return deletef 是否删除／离职，0正常，1删除离职
     */
    public Byte getDeletef() {
        return deletef;
    }

    /**
     * 设置是否删除／离职，0正常，1删除离职
     * @param deletef 是否删除／离职，0正常，1删除离职
     */
    public void setDeletef(Byte deletef) {
        this.deletef = deletef;
    }
}