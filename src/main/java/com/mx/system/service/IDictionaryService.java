package com.mx.system.service;


import com.mx.generator.pojo.SysDictionary;
import com.mx.system.model.Dictionary;
import com.mx.system.model.MasterDictionaryEntity;

import java.util.List;
import java.util.Map;

public interface IDictionaryService {

    /**
     * 获得字典类列表
     *
     * @return list
     */
    List<SysDictionary> getDictionaryType();

    /**
     * 编辑字典值list
     *
     * @param json 表格json
     */
    void editDictionary(String json);

    /**
     * 获得字典值列表
     * @param map type
     * @return list
     */
    List<SysDictionary> getDictionaryGridData(Map<String, String> map);
}
