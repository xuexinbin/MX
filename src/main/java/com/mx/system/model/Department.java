package com.mx.system.model;

import com.mx.generator.pojo.SysDepartment;

/**
 * 部门基础信息bean
 *
 * @author mx
 */
public class Department extends SysDepartment {

    /**
     * 父部门名称
     */
    private String parentName;

    /**
     * 负责人真实姓名
     */
    private String leaderUserTrueName;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getLeaderUserTrueName() {
        return leaderUserTrueName;
    }

    public void setLeaderUserTrueName(String leaderUserTrueName) {
        this.leaderUserTrueName = leaderUserTrueName;
    }
}