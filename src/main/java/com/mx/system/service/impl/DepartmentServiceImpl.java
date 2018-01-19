package com.mx.system.service.impl;

import com.mx.common.util.GridUtil;
import com.mx.generator.pojo.SysDepartment;
import com.mx.system.dao.DepartmentMapper;
import com.mx.system.model.Department;
import com.mx.system.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门管理service
 *
 * @author mx
 */
@Service
@Transactional
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public void editDepartment(String json) {
        List<SysDepartment> insertList = GridUtil.getGridInsertList(json, SysDepartment.class);
        List<SysDepartment> updataList = GridUtil.getGridUpdateList(json, SysDepartment.class);
        // 逐条update
        for (SysDepartment sd : updataList) {
            departmentMapper.editDepartment(sd);
        }
        // 批量添加新增数据
        if (insertList.size() > 0) {
            departmentMapper.addDepartment(insertList);
        }
    }

    @Override
    public List<Department> getDepartmentGridData(Department department) {
        List<Department> list = departmentMapper.getDepartmentGridData(department);
        // findGrid:不存在父节点，parentId设为0
        ArrayList<Object> idList = new ArrayList<>();
        for (Department item : list) {
            idList.add(item.getId());
        }

        for (Department item : list) {
            if (!idList.contains(item.getParentId())) {
                item.setParentId(0);
            }
        }

        return list;
    }

    @Override
    public void deleteDepartmentByIds(String ids) {
        departmentMapper.deleteDepartmentByIds(ids);
    }

}
