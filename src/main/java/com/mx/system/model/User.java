package com.mx.system.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class User {
    private Integer id;

    private Date addTime;

    private Date updateTime;

    private Integer addUserId;

    private Integer updateUserId;

    private Byte userType;

    private Integer orderCustomerId;

    private String userName;

    private String password;

    private String number;

    private String trueName;

    private Byte sex;

    private String mail;

    private String phone;

    private String avatar;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date joinTime;

    private Date deleteTime;

    private Integer departmentId;

    private Byte enablef;

    private Byte deletef;

    private String memo;

    private String skill;

    private BigDecimal hourWage;

    private Date lastLoginTime;

    private Integer loginTimes;
    
    /** 部门名称 */
    private String departmentName;
    
    /** 角色名称 */
    private String roleName;

    /** 角色  ,拼接 */
    private String roles;
    
    /** 模糊查询：工号／姓名／ 登录名／手机号 */
    private String likeStr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getAddUserId() {
        return addUserId;
    }

    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    public Integer getOrderCustomerId() {
        return orderCustomerId;
    }

    public void setOrderCustomerId(Integer orderCustomerId) {
        this.orderCustomerId = orderCustomerId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName == null ? null : trueName.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Byte getEnablef() {
        return enablef;
    }

    public void setEnablef(Byte enablef) {
        this.enablef = enablef;
    }

    public Byte getDeletef() {
        return deletef;
    }

    public void setDeletef(Byte deletef) {
        this.deletef = deletef;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill == null ? null : skill.trim();
    }

    public BigDecimal getHourWage() {
        return hourWage;
    }

    public void setHourWage(BigDecimal hourWage) {
        this.hourWage = hourWage;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes(Integer loginTimes) {
        this.loginTimes = loginTimes;
    }

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getLikeStr() {
		return likeStr;
	}

	public void setLikeStr(String likeStr) {
		this.likeStr = likeStr;
	}
    
}