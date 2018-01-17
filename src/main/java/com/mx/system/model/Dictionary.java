package com.mx.system.model;

/**
 * 字典bean
 * @author mx
 *
 */
public class Dictionary {
    private Integer id;

    private Integer addUserId;

    private Integer updateUserId;

    private String dictionaryCode;

    private String dictionaryName;

    private String key;

    private String value;

    private Integer sort;

    private Byte enablef;

    private Byte deletef;
    
    /** 是否为新增数据：grid */
    private boolean addFlag;

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

    public String getDictionaryCode() {
        return dictionaryCode;
    }

    public void setDictionaryCode(String dictionaryCode) {
        this.dictionaryCode = dictionaryCode == null ? null : dictionaryCode.trim();
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName = dictionaryName == null ? null : dictionaryName.trim();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

	public boolean isAddFlag() {
		return addFlag;
	}

	public void setAddFlag(boolean addFlag) {
		this.addFlag = addFlag;
	}
    
}