package com.mx.system.model;

/**
 * 部门基础信息bean
 * @author mx
 *
 */
public class Department {
    private Integer id;

    private Integer addUserId;

    private Integer updateUserId;

    /** 部门名称 */
    private String departmentName;

    /** 上级部门id，默认0为根部门  */
    private Integer parentId;

    /** 负责人id，关联user表 */
    private Integer leaderUserId;
    
    /** 负责人真实姓名 */
    private String leaderUserTrueName;
    
    /** 部门人数  */
    private Integer userCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLeaderUserId() {
        return leaderUserId;
    }

    public void setLeaderUserId(Integer leaderUserId) {
        this.leaderUserId = leaderUserId;
    }

	public String getLeaderUserTrueName() {
		return leaderUserTrueName;
	}

	public void setLeaderUserTrueName(String leaderUserTrueName) {
		this.leaderUserTrueName = leaderUserTrueName;
	}

	public Integer getUserCount() {
		return userCount;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}
    
}