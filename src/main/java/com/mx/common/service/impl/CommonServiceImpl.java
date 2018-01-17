package com.mx.common.service.impl;

import com.mx.common.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.common.constant.ErrorConstant;
import com.mx.common.dao.ICommonDao;
import com.mx.common.exception.WebBusinessException;
import com.mx.common.service.ICommonService;
import com.mx.common.util.CommonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class CommonServiceImpl implements ICommonService {

    @Autowired
    private ICommonDao commonDao;

    @Override
    public int updateRows(String tableId, String conditionCol, String updateData) {
        if (CommonUtil.isEmpty(tableId) || CommonUtil.isEmpty(updateData) || CommonUtil.isEmpty(updateData)) {
            throw new WebBusinessException(ErrorConstant.ACE0001, null);
        }
        UpdateDataBean updateDataBean = new UpdateDataBean(tableId, conditionCol, updateData);
        return commonDao.operateBySql(updateDataBean.getSql());
    }

    @Override
    public void saveTableData(TableParam tableParam) {
        // 更新数据
        if (CommonUtil.isNotEmpty(tableParam.getUpdateStr())) {
            UpdateDataBean updateDataBean = new UpdateDataBean(tableParam.getTableName(), tableParam.getUniqueId(), tableParam.getUpdateStr());
            commonDao.operateBySql(updateDataBean.getSql());
        }
        // 插入数据
        if (CommonUtil.isNotEmpty(tableParam.getInsertStr())) {
            InsertDataBean insertDataBean = new InsertDataBean(tableParam.getTableName(), tableParam.getInsertStr());
            commonDao.operateBySql(insertDataBean.getSql());
        }
        // 删除数据
        if (CommonUtil.isNotEmpty(tableParam.getDelStr())) {
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
    public int deleteRows(String tableName, String conditions) {
        HashMap<String, String> map = new HashMap<>();
        map.put("tableName", tableName);
        map.put("conditions", conditions);
        return commonDao.deleteRows(map);
    }

    @Override
    public List<SelectBean> getSelectList(String tableName, String key, String value, String conditions) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("tableName", tableName);
        map.put("key", key);
        map.put("value", value);
        map.put("conditions", conditions);
        return commonDao.getSelectList(map);
    }

}
