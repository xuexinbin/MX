package com.mx.common.util;

import com.mx.common.dao.CommonMapper;
import com.mx.system.model.MasterDictionaryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DictionaryUtil {
    private volatile static DictionaryUtil dictionaryUtil;

    private static List<MasterDictionaryEntity> dictionaryList;

    @PostConstruct
    public void init() {
        dictionaryUtil = this;
    }

    @Autowired
    CommonMapper commonMapper;

    /**
     * 获取字典列表
     * @param enablef 0有效 1失效 2全部
     * @param dictionaryCode 字典分类编码
     * @return 字典列表
     */
    public static List<MasterDictionaryEntity> getDictionaryListByCode(Integer enablef, String dictionaryCode) {
        if (dictionaryList == null) {
            // 获得字典列表
            dictionaryList = dictionaryUtil.commonMapper.getDictionaryListByCode();
        }

        ArrayList<MasterDictionaryEntity> resList = new ArrayList<>();
        // 遍历过滤
        for (MasterDictionaryEntity item : dictionaryList) {
            if ((int)item.getEnablef() == enablef && dictionaryCode.equals(item.getDictionaryCode())) {
                resList.add(item);
            }
        }
        return resList;
    }

    /**
     * 刷新字典列表
     */
    public static void refreshDictionaryList() {
        // 获得字典列表
        dictionaryList = dictionaryUtil.commonMapper.getDictionaryListByCode();
    }
}
