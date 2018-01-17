package com.mx.system.service;

import com.alibaba.fastjson.JSONObject;
import com.mx.generator.pojo.SysDepartment;
import com.mx.system.model.Department;
import com.mx.system.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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
     * @param sysDepartment 查询信息
     * @return list
     */
    List<SysDepartment> getDepartmentGridData(SysDepartment sysDepartment);

    /**
     * 删除部门
     * @param ids 部门ids
     */
    void deleteDepartmentByIds(String ids);
}
