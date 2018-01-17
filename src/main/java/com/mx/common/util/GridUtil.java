package com.mx.common.util;

import com.mx.common.pojo.BaseBean;
import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * 表格相关工具类
 *
 * @author mx
 */
public class GridUtil {

    /**
     * 获得表格新增list
     *
     * @param list list
     * @return 新增list
     */
    public static List getGridInsertList(List<? extends BaseBean> list) {
        List addList = new ArrayList<>();
        Integer userId = SessionManager.getLoginUserId();
        //判断是否为新增数据
        list.forEach(bean -> {
            //bean.setUpdateUserId(userId);
            if (bean.isAddFlag()) {
              //  bean.setAddUserId(userId);
                addList.add(bean);
            }
        });
        return addList;
    }

    /**
     * 获得表格新增list
     *
     * @param json 表格json数据
     * @param obj  list对应实体class
     * @return 新增list
     */
    public static List getGridInsertList(String json, Class obj) {
        JSONArray ja = JSONArray.fromObject(json);
        List list = (ArrayList) JSONArray.toCollection(ja, obj);
        return getGridInsertList(list);
    }

    /**
     * 获得表格修改list
     *
     * @param list list
     * @return 修改数据list
     */
    public static List getGridUpdateList(List<? extends BaseBean> list) {
        List updateList = new ArrayList<>();
        Integer userId = SessionManager.getLoginUserId();
        //判断是否为编辑数据
        list.forEach(bean -> {
//            bean.setUpdateUserId(userId);
            if (!bean.isAddFlag()) {
//                bean.setAddUserId(userId);
                updateList.add(bean);
            }
        });
        return updateList;
    }

    /**
     * 获得表格修改list
     *
     * @param json 表格json数据
     * @param obj  list对应实体class
     * @return 修改数据list
     */
    public static List getGridUpdateList(String json, Class obj) {
        JSONArray ja = JSONArray.fromObject(json);
        List list = (ArrayList) JSONArray.toCollection(ja, obj);
        return getGridUpdateList(list);
    }


}
