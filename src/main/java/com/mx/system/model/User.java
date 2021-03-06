package com.mx.system.model;

import com.mx.generator.pojo.SysUser;
import org.springframework.format.annotation.DateTimeFormat;

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

}