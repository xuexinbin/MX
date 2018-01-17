package com.mx.system.service.impl;

import com.mx.common.constant.CommonConstant;
import com.mx.common.util.SessionManager;
import com.mx.system.dao.RoleFunctionMapper;
import com.mx.system.dao.RoleMapper;
import com.mx.system.model.Function;
import com.mx.system.model.Role;
import com.mx.system.model.RoleFunction;
import com.mx.system.service.IRoleFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色信息service
 *
 * @author mx
 */
@Service("roleFunctionService")
// TODO 事务 @Transactional
public class RoleFunctionServiceImpl implements IRoleFunctionService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RoleFunctionMapper roleFunctionMapper;

    @Override
    public List<Role> getRoleList() {
        return roleMapper.getRoleList();
    }

    @Override
    public void editRole(Role role) {
        // session获得userId
        Integer userId=(int) SessionManager.getInstance().getValue(CommonConstant.SESSION_USER_ID);
        role.setAddUserId(userId);
        role.setUpdateUserId(userId);
        // 自主添加
        role.setRoleType((byte) 1);
        // id为空添加，否则编辑
        if (role.getId() == null) {
            roleMapper.addRole(role);
        } else {
            roleMapper.editRole(role);
        }
    }

    @Override
    public void delRoleById(int id) {
        roleMapper.delRoleById(id);
    }

    @Override
    public List<Function> getFunctionList(Integer functionType) {
        return roleFunctionMapper.getFunctionList(functionType);
    }

    @Override
    public String getRoleFunctions(Integer roleId) {
        return roleFunctionMapper.getRoleFunctions(roleId);
    }

    @Override
    public void editRoleFunctions(Integer roleId, String functionIds) {
        // session获得userId
        Integer userId=(int) SessionManager.getInstance().getValue(CommonConstant.SESSION_USER_ID);
        // 删除已有权限
        roleFunctionMapper.deleteRoleFunctionsByRoleId(roleId);

        if (functionIds == null || functionIds.length() == 0) {
            return;
        }
        // 批量添加
        String[] ids = functionIds.split(",");
        List<RoleFunction> list = new ArrayList<>();
        for (String id : ids) {
            RoleFunction masterFunctionEntity = new RoleFunction();
            masterFunctionEntity.setAddUserId(userId);
            masterFunctionEntity.setUpdateUserId(userId);
            masterFunctionEntity.setFunctionId(Integer.parseInt(id));
            masterFunctionEntity.setRoleId(roleId);
            list.add(masterFunctionEntity);
        }
        roleFunctionMapper.addRoleFunctions(list);
    }


}
