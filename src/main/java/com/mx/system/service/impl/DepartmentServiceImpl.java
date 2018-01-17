package com.mx.system.service.impl;

import com.mx.common.util.GridUtil;
import com.mx.generator.pojo.SysDepartment;
import com.mx.system.dao.DepartmentMapper;
import com.mx.system.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<SysDepartment> getDepartmentGridData(SysDepartment sysDepartment) {
        return departmentMapper.getDepartmentGridData(sysDepartment);
    }

    @Override
    public void deleteDepartmentByIds(String ids) {
        departmentMapper.deleteDepartmentByIds(ids);
    }

}
