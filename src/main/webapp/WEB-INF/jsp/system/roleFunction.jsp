<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row height100">
    <div class="col-md-2 height100" style="width: 25%;">
        <div class="panel-block">
            <div class="title">
                <span>角色列表</span>
                <div>
                    <button id="roleFunction_addRoleBtn" type="button" class="btn-blue" data-icon="plus">新建角色</button>
                </div>
            </div>
            <ul id="roleFunction_roleList">
            </ul>
        </div>
    </div>
    <div class="col-md-10 height100 flex-column" style="width: 75%;">
        <div style="border: 1px solid #ddd;border-radius: 6px; height: 100%;">
            <div style="height:45px;padding: 10px 15px;border-bottom: 1px solid #ddd;">
                <span style="color: #222;font-weight: bolder;">角色名称：</span>
                <input id="roleFunction_treeRoleName" type="text" readonly="" style="width:120px;">

                <div class="btn-group" role="group" style="float: right;">
                    <button id="roleFunction_refreshBtn" type="button" class="btn-blue" data-icon="refresh">刷新</button>
                    <button id="roleFunction_saveBtn" type="button" class="btn-default" data-icon="save">保存</button>
                </div>
            </div>
            <div style="width: 100%;position: absolute;top: 50px;bottom: 0px;">
                <div style="width: 45%; height: 100%; padding: 10px 20px; float: left;">
                    <fieldset style="height: 100%;">
                        <legend>菜单权限设定</legend>
                        <input id="roleFunction_roleId" type="hidden">
                        <div id="roleFunction_functionTree" class="ztree" style="height: 95%;overflow-y:auto;"></div>
                    </fieldset>
                </div>
                <div style="width: 45%; height: 100%; padding: 10px 20px;float: left;">
                    <fieldset style="height: 100%;">
                        <legend>功能权限设定</legend>
                        <div id="roleFunction_authorityTree" class="ztree" style="height: 95%;overflow-y:auto;"></div>
                    </fieldset>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="js/system/roleFunction.js"></script>
