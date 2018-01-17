package com.mx.system.service;


import com.mx.system.model.Dictionary;
import com.mx.system.model.MasterDictionaryEntity;

import java.util.List;

public interface IDictionaryService {

    /**
     * 获得字典类列表
     *
     * @return list
     */
    List<MasterDictionaryEntity> getDictionaryType();

    /**
     * 获得字典值列表
     *
     * @param enablef        是否有效  null全部
     * @param dictionaryCode 字典code
     * @return list
     */
    List<MasterDictionaryEntity> getDictionarysByCode(Integer enablef, String dictionaryCode);

    /**
     * 删除字典值
     *
     * @param ids ids
     */
    void deleteDictionaryById(String ids);

    /**
     * 编辑字典值list
     *
     * @param list list
     */
    void editDictionary(List<Dictionary> list);

}
