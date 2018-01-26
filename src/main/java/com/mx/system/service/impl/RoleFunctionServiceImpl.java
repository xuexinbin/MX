package com.mx.system.service.impl;

import com.mx.common.service.ICommonService;
import com.mx.generator.pojo.SysFunction;
import com.mx.generator.pojo.SysRole;
import com.mx.generator.pojo.SysRoleFunction;
import com.mx.system.dao.RoleFunctionMapper;
import com.mx.system.service.IRoleFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色信息service
 *
 * @author mx
 */
@Service
@Transactional
public class RoleFunctionServiceImpl implements IRoleFunctionService {

    @Autowired
    RoleFunctionMapper roleFunctionMapper;

    @Autowired
    ICommonService commonService;

    @Override
    public List<SysRole> getRoleList() {
        return roleFunctionMapper.getRoleList();
    }

    @Override
    public void editRole(SysRole role) {
        // 自主添加
        role.setType((byte) 1);
        // id为空添加，否则编辑
        if (role.getId() == null) {
            roleFunctionMapper.addRole(role);
        } else {
            roleFunctionMapper.editRole(role);
        }
    }

    @Override
    public List<SysFunction> getFunctionList(Integer type) {
        return roleFunctionMapper.getFunctionList(type);
    }

    @Override
    public String getRoleFunctions(Integer roleId) {
        return roleFunctionMapper.getRoleFunctions(roleId);
    }

    @Override
    public void editRoleFunctions(Integer roleId, String functionIds) {
        // 删除已有权限
        //roleFunctionMapper.deleteRoleFunctionsByRoleId(roleId);
        commonService.deleteRows("sys_role_function", "role_id", roleId);

        if (functionIds == null || functionIds.length() == 0) {
            return;
        }
        // 批量添加
        String[] ids = functionIds.split(",");
        List<SysRoleFunction> list = new ArrayList<>();
        for (String id : ids) {
            SysRoleFunction roleFunction = new SysRoleFunction();
            roleFunction.setFunctionId(Integer.parseInt(id));
            roleFunction.setRoleId(roleId);
            list.add(roleFunction);
        }
        roleFunctionMapper.addRoleFunctions(list);
    }


}
