package com.mx.system.dao;


import com.mx.system.model.Dictionary;
import com.mx.system.model.MasterDictionaryEntity;

import java.util.HashMap;
import java.util.List;

/**
 * 字典Dao
 *
 * @author mx
 */
public interface DictionaryMapper {

    /**
     * 获得字典类列表
     *
     * @return list
     */
    List<MasterDictionaryEntity> getDictionaryType();

    /**
     * 获得字典值列表
     *
     * @param map map
     * @return list
     */
    List<MasterDictionaryEntity> getDictionarysByCode(HashMap<String, Object> map);

    /**
     * 删除字典值
     *
     * @param map map
     */
    void deleteDictionaryById(HashMap<String, Object> map);

    /**
     * 编辑字典值
     *
     * @param dictionary 字典信息
     */
    void editDictionary(Dictionary dictionary);

    /**
     * 添加字典值
     *
     * @param list 字典list
     */
    void addDictionary(List<Dictionary> list);
}