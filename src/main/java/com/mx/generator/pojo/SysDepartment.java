package com.mx.generator.pojo;

import com.mx.common.pojo.BaseBean;

public class SysDepartment extends BaseBean {
    private Integer id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 上级部门id，默认0为根部门
     */
    private Integer parentId;

    /**
     * 负责人id，关联user表
     */
    private Integer leaderUserId;

    private Byte enable;

    private String memo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获得部门名称
     * @return name 部门名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置部门名称
     * @param name 部门名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获得上级部门id，默认0为根部门
     * @return parent_id 上级部门id，默认0为根部门
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置上级部门id，默认0为根部门
     * @param parentId 上级部门id，默认0为根部门
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获得负责人id，关联user表
     * @return leader_user_id 负责人id，关联user表
     */
    public Integer getLeaderUserId() {
        return leaderUserId;
    }

    /**
     * 设置负责人id，关联user表
     * @param leaderUserId 负责人id，关联user表
     */
    public void setLeaderUserId(Integer leaderUserId) {
        this.leaderUserId = leaderUserId;
    }

    public Byte getEnable() {
        return enable;
    }

    public void setEnable(Byte enable) {
        this.enable = enable;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}