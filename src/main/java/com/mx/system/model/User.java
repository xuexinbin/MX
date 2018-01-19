package com.mx.system.model;

import com.mx.generator.pojo.SysUser;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class User extends SysUser {

    /**
     * 出生日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    /**
     * 入职时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date joinTime;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色  ,拼接
     */
    private String roles;

    /**
     * 模糊查询：工号／姓名／ 登录名／手机号
     */
    private String likeStr;

    @Override
    public Date getBirthday() {
        return birthday;
    }

    @Override
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public Date getJoinTime() {
        return joinTime;
    }

    @Override
    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
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