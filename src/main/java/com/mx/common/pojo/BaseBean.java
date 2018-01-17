package com.mx.common.pojo;

import com.mx.common.util.SessionManager;

/**
 * 基础bean
 *
 * @author mx
 */
public class BaseBean {

    /**
     * 更新user
     * get方法：为空自动获取session中userId
     */
    private Integer updateUserId;

    /**
     * 添加user
     * get方法：为空自动获取session中userId
     */
    private Integer addUserId;

    /**
     * 是否为新增数据：grid
     */
    private boolean addFlag;

    public Integer getUpdateUserId() {
        return updateUserId != null ? updateUserId : SessionManager.getLoginUserId();
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Integer getAddUserId() {
        return addUserId != null ? addUserId : SessionManager.getLoginUserId();
    }

    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }

    public boolean isAddFlag() {
        return addFlag;
    }

    public void setAddFlag(boolean addFlag) {
        this.addFlag = addFlag;
    }
}
