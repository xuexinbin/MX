package com.mx.system.dao;


import com.mx.generator.pojo.SysDictionary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 字典Dao
 *
 * @author mx
 */
@Repository
public interface DictionaryMapper {

    /**
     * 获得字典类列表
     *
     * @return list
     */
    List<SysDictionary> getDictionaryType();

    /**
     * 编辑字典值
     *
     * @param dictionary 字典信息
     */
    void editDictionary(SysDictionary dictionary);

    /**
     * 添加字典值
     *
     * @param list 字典list
     */
    void addDictionary(List<SysDictionary> list);

    /**
     * 获得字典值列表
     *
     * @param map name
     * @return list
     */
    List<SysDictionary> getDictionaryGridData(Map<String, String> map);
}