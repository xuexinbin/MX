package com.mx.system.service.impl;

import com.mx.common.util.GridUtil;
import com.mx.generator.pojo.SysFunction;
import com.mx.system.dao.FunctionMapper;
import com.mx.system.service.IFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 菜单或功能管理service
 *
 * @author mx
 */
@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService {

    @Autowired
    FunctionMapper functionMapper;

    @Override
    public void editFunction(String json) {
        List<SysFunction> insertList = GridUtil.getGridInsertList(json, SysFunction.class);
        List<SysFunction> updataList = GridUtil.getGridUpdateList(json, SysFunction.class);
        // 逐条update
        for (SysFunction sd : updataList) {
            functionMapper.editFunction(sd);
        }
        // 批量添加新增数据
        if (insertList.size() > 0) {
            functionMapper.addFunction(insertList);
        }
    }

    @Override
    public List<SysFunction> getFunctionGridData(Map<String, String> map) {
        List<SysFunction> list = functionMapper.getFunctionGridData(map);
        // findGrid:不存在父节点，parentId设为0
        ArrayList<Object> idList = new ArrayList<>();
        for (SysFunction item : list) {
            idList.add(item.getId());
        }

        for (SysFunction item : list) {
            if (!idList.contains(item.getParentId())) {
                item.setParentId(0);
            }
        }

        return list;
    }

}
