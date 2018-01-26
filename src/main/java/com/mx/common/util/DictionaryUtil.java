package com.mx.common.util;

import com.mx.common.dao.ICommonDao;
import com.mx.generator.pojo.SysDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DictionaryUtil {
    private volatile static DictionaryUtil dictionaryUtil;

    private static List<SysDictionary> dictionaryList;

    @PostConstruct
    public void init() {
        dictionaryUtil = this;
    }

    @Autowired
    ICommonDao commonDao;

    /**
     * 获取字典列表
     *
     * @param enablef 0有效 1失效 2全部
     * @param type    字典分类编码
     * @return 字典列表
     */
    public static List<SysDictionary> getDictionaryListByCode(Integer enablef, String type) {
        if (dictionaryList == null) {
            // 获得字典列表
            dictionaryList = dictionaryUtil.commonDao.getDictionaryListByType();
        }

        ArrayList<SysDictionary> resList = new ArrayList<>();
        // 遍历过滤
        for (SysDictionary item : dictionaryList) {
            if ((int) item.getEnablef() == enablef && type.equals(item.getType())) {
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
        dictionaryList = dictionaryUtil.commonDao.getDictionaryListByType();
    }
}
