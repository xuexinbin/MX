package com.mx.system.dao;


import com.mx.generator.pojo.SysDepartment;
import com.mx.system.model.Department;
import com.mx.system.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户管理 Dao
 *
 * @author mx
 */
@Repository
public interface UserMapper {

    /**
     * 获得部门列表grid数据
     *
     * @param department 查询信息
     * @return list
     */
    List<Department> getDepartmentGridData(Department department);

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

    /**
     * 获得用户管理grid数据
     * @param user 查询信息
     * @return list
     */
    List<User> getUserGridData(User user);

    /**
     * 添加用户
     * @param user 用户信息
     */
    void addUser(User user);

    /**
     * 编辑用户
     * @param user 用户信息
     */
    void editUser(User user);

    /**
     * 获得用户详细信息
     * @param id 用户id
     * @return user
     */
    User getUserInfoById(Integer id);
}