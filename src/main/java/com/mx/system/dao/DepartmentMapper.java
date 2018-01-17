package com.mx.system.dao;


import com.mx.generator.pojo.SysDepartment;
import com.mx.system.model.Department;
import com.mx.system.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 部门管理 Dao
 *
 * @author mx
 */
@Repository
public interface DepartmentMapper {

    /**
     * 获得部门列表grid数据
     *
     * @param sysDepartment 查询信息
     * @return list
     */
    List<SysDepartment> getDepartmentGridData(SysDepartment sysDepartment);

    /**
     * 新增部门
     *
     * @param list 部门信息list
     */
    void addDepartment(List<SysDepartment> list);

    /**
     * 编辑部门
     *
     * @param dept 部门信息
     */
    void editDepartment(SysDepartment dept);

    /**
     * 删除部门
     *
     * @param id 部门id
     * @return id
     */
    int delDepartmentById(int id);


    /**
     * 删除部门
     * @param ids
     */
    void deleteDepartmentByIds(String ids);
}