package com.mx.system.service.impl;

import com.mx.common.util.DictionaryUtil;
import com.mx.common.util.GridUtil;
import com.mx.generator.pojo.SysDictionary;
import com.mx.system.dao.DictionaryMapper;
import com.mx.system.service.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 字典service
 *
 * @author mx
 */
@Service
@Transactional
public class DictionaryServiceImpl implements IDictionaryService {

    @Autowired
    DictionaryMapper dictionaryMapper;

    @Override
    public List<SysDictionary> getDictionaryType() {
        return dictionaryMapper.getDictionaryType();
    }

    @Override
    public void editDictionary(String json) {
        List<SysDictionary> insertList = GridUtil.getGridInsertList(json, SysDictionary.class);
        List<SysDictionary> updataList = GridUtil.getGridUpdateList(json, SysDictionary.class);
        // 逐条update
        for (SysDictionary sd : updataList) {
            dictionaryMapper.editDictionary(sd);
        }
        // 批量添加新增数据
        if (insertList.size() > 0) {
            dictionaryMapper.addDictionary(insertList);
        }

        // 刷新内存中字典列表
        DictionaryUtil.refreshDictionaryList();
    }

    @Override
    public List<SysDictionary> getDictionaryGridData(Map<String, String> map) {
        return dictionaryMapper.getDictionaryGridData(map);
    }

}
