package com.mx.common.service.impl;

import com.mx.common.constant.ErrorConstant;
import com.mx.common.dao.ICommonDao;
import com.mx.common.exception.WebBusinessException;
import com.mx.common.pojo.*;
import com.mx.common.service.ICommonService;
import com.mx.common.util.ArrayUtil;
import com.mx.common.util.SessionManager;
import com.mx.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class CommonServiceImpl implements ICommonService {

    @Autowired
    private ICommonDao commonDao;

    @Override
    public int updateRows(String tableId, String conditionCol, String updateData) {
        if (StringUtil.isEmpty(tableId) || StringUtil.isEmpty(updateData) || StringUtil.isEmpty(updateData)) {
            throw new WebBusinessException(ErrorConstant.ACE0001, null);
        }
        UpdateDataBean updateDataBean = new UpdateDataBean(tableId, conditionCol, updateData);
        return commonDao.operateBySql(updateDataBean.getSql());
    }

    @Override
    public void saveTableData(TableParam tableParam) {
        // 更新数据
        if (StringUtil.isNotEmpty(tableParam.getUpdateStr())) {
            UpdateDataBean updateDataBean = new UpdateDataBean(tableParam.getTableName(), tableParam.getUniqueId(), tableParam.getUpdateStr());
            commonDao.operateBySql(updateDataBean.getSql());
        }
        // 插入数据
        if (StringUtil.isNotEmpty(tableParam.getInsertStr())) {
            InsertDataBean insertDataBean = new InsertDataBean(tableParam.getTableName(), tableParam.getInsertStr());
            commonDao.operateBySql(insertDataBean.getSql());
        }
        // 删除数据
        if (StringUtil.isNotEmpty(tableParam.getDelStr())) {
            DelDataBean delDataBean = new DelDataBean(tableParam.getTableName(), tableParam.getUniqueId(), tableParam.getDelStr());
            commonDao.operateBySql(delDataBean.getSql());
        }

    }

    @Override
    public int insertRows(String tableId, String insertData) {
        InsertDataBean updateDataBean = new InsertDataBean(tableId, insertData);
        return commonDao.operateBySql(updateDataBean.getSql());
    }

    @Override
    public int deleteRows(String tableName, String column, String key) {
        HashMap<String, String> map = new HashMap<>();
        map.put("tableName", tableName);
        String conditions = column + " IN (" + key + ")";
        map.put("conditions", conditions);
        return commonDao.deleteRows(map);
    }

    @Override
    public int deleteRows(String tableName, String column, Integer key) {
        return deleteRows(tableName, column, key.toString());
    }

    @Override
    public int updateDeletef(String tableName, String ids) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("tableName", tableName);
        map.put("ids", ids);
        map.put("updateUserId", SessionManager.getLoginUserId());
        return commonDao.updateDeletef(map);
    }

    @Override
    public int updateColumn(String tableName, String column, String columnValue, String conditionColumn, String ids) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("tableName", tableName);
        map.put("column", column);
        map.put("columnValue", columnValue);
        map.put("updateUserId", SessionManager.getLoginUserId());
        map.put("ids", ids);
        map.put("conditionColumn", conditionColumn);
        return commonDao.updateColumn(map);
    }

    @Override
    public int deleteRows(String tableName, String conditions) {
        HashMap<String, String> map = new HashMap<>();
        map.put("tableName", tableName);
        map.put("conditions", conditions);
        return commonDao.deleteRows(map);
    }

    @Override
    public List<SelectBean> getSelectList(String tableName, String text, String value, String conditions) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("tableName", tableName);
        map.put("text", text);
        map.put("value", value);
        map.put("conditions", conditions);
        return commonDao.getSelectList(map);
    }

    @Override
    public String getSelectJson(String tableName, String text, String value, String conditions) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("tableName", tableName);
        map.put("text", text);
        map.put("value", value);
        map.put("conditions", conditions);
        List<SelectBean> list = commonDao.getSelectList(map);
        if (list.size() == 0) {
            return "[]";
        }
        StringBuilder json = new StringBuilder();
        json.append("[");
        for (SelectBean bean : list) {
            json.append("{").append(bean.getValue()).append(":").append(bean.getText()).append("},");
        }
        json.append("]");
        json.substring(0, json.length() - 1);
        return json.toString();
    }

}
