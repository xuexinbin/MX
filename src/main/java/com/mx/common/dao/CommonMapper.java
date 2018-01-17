package com.mx.common.dao;


import com.mx.system.model.MasterDictionaryEntity;

import java.util.List;

/**
 * 共通方法Dao
 * @author mx
 *
 */
public interface CommonMapper {

	/**
	 * 获得字典值列表
	 * @return
	 */
	List<MasterDictionaryEntity> getDictionaryListByCode();
}