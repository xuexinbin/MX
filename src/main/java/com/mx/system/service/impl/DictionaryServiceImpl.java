package com.mx.system.service.impl;

import com.mx.common.constant.CommonConstant;
import com.mx.common.util.DictionaryUtil;
import com.mx.common.util.SessionManager;
import com.mx.system.dao.DictionaryMapper;
import com.mx.system.model.Dictionary;
import com.mx.system.model.MasterDictionaryEntity;
import com.mx.system.service.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 字典service
 *
 * @author mx
 */
@Service("dictionaryService")
@Transactional
public class DictionaryServiceImpl implements IDictionaryService {

    @Autowired
    DictionaryMapper dictionaryMapper;

    @Override
    public List<MasterDictionaryEntity> getDictionaryType() {
        return dictionaryMapper.getDictionaryType();
    }

    @Override
    public List<MasterDictionaryEntity> getDictionarysByCode(Integer enablef, String dictionaryCode) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("enablef", enablef);
        map.put("dictionaryCode", dictionaryCode);
        return dictionaryMapper.getDictionarysByCode(map);
    }

    @Override
    public void deleteDictionaryById(String ids) {
        // session获得userId
        Integer userId=(int) SessionManager.getInstance().getValue(CommonConstant.SESSION_USER_ID);
        HashMap<String, Object> map = new HashMap<>();
        map.put("updateUserId", userId);
        map.put("id", ids);
        dictionaryMapper.deleteDictionaryById(map);
        // 刷新内存中字典列表
        DictionaryUtil.refreshDictionaryList();
    }

    @Override
    public void editDictionary(List<Dictionary> list) {
        // session获得userId
        Integer userId=(int) SessionManager.getInstance().getValue(CommonConstant.SESSION_USER_ID);
        if (list == null || list.size() == 0) {
            return;
        }
        // 逐条update
        List<Dictionary> addList = new ArrayList<>();
        for (Dictionary dic : list) {
            dic.setUpdateUserId(userId);
            //判断是否为新增数据
            if (dic.isAddFlag()) {
                dic.setAddUserId(userId);
                addList.add(dic);
            } else {
                dictionaryMapper.editDictionary(dic);

            }
        }
        // 批量添加新增数据
        if (addList.size() > 0) {
            dictionaryMapper.addDictionary(addList);
        }
        // 刷新内存中字典列表
        DictionaryUtil.refreshDictionaryList();
    }

}
