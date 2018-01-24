package com.mx.system.service;

import com.mx.system.model.Department;

import java.util.List;

/**
 * 部门管理service
 *
 * @author mx
 */
public interface IDepartmentService {

    /**
     * 编辑部门grid
     *
     * @param json 部门信息
     */
    void editDepartment(String json);

    /**
     * 获得部门列表grid数据
     *
     * @param department 查询信息
     * @return list
     */
    List<Department> getDepartmentGridData(Department department);

    /**
     * 删除部门
     *
     * @param ids 部门ids
     */
    void deleteDepartmentByIds(String ids);

}
