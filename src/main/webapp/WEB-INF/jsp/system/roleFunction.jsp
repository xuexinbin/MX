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
<script type="text/javascript">
    //@ sourceURL=roleFunction.js
    $(function () {
        void function () {

            // ---------- 左侧角色列表 start---------
            // 编辑角色dialog Id
            var editRoleDialogId = 'roleFunction_editRoleialog';

            var openRoleDialog = function (param) {
                var title = "";
                var roleId = null;
                if (param != null) {
                    roleId = param.currentTarget.id;
                    title = '编辑角色';
                } else {
                    title = '新增角色';
                }

                BJUI.dialog({
                    id: editRoleDialogId,
                    title: title,
                    mask: true, // 模态
                    maxable: false,// 显示最大化按钮
                    width: 350,
                    height: 150,
                    url: 'system/roleFunction/editRoleDialog',
                    onLoad: function ($dialog) {
                        if (param != null) {
                            $("#editRoleDialog_name").val(param.currentTarget.getAttribute("data-name"));
                        }

                        // 保存角色
                        $("#editRoleDialog_saveBtn").click(function () {

                            // 校验失败 直接return
                            if (!$('#editRoleDialog_name').isValid()) {
                                return;
                            }

                            BJUI.ajax('doajax', {
                                url: 'system/roleFunction/editRole',
                                data: {
                                    id: roleId,
                                    name: $("#editRoleDialog_name").val()
                                },
                                loadingmask: true,
                                okCallback: function (json, options) {
                                    // 提示成功，关闭dialog
                                    BJUI.alertmsg('ok', '保存成功！', {});
                                    BJUI.dialog('close', editRoleDialogId);

                                    // 刷新角色列表
                                    getRoleListFn();
                                }
                            });
                        });
                    },
                });
            };

            // 获得左侧列表
            var getRoleListFn = function () {
                BJUI.ajax('doajax', {
                    url: 'system/roleFunction/getRoleList',
                    data: {},
                    loadingmask: false,
                    okCallback: function (res, options) {
                        roleList = res.roleList;
                        // 拼接左侧角色列表html
                        var htmlStr = "";

                        for (var i = 0; i < roleList.length; i++) {
                            var tempStr = '<li data-id="' + roleList[i].id + '" data-name="' + roleList[i].name + '">'
                                + '<a>'
                                + roleList[i].name;
                            if (roleList[i].type == 0) {
                                tempStr += "（系统默认）"
                            }
                            tempStr += '</a>';
                            if (roleList[i].type != 0) {
                                tempStr += '<i id="' + roleList[i].id + '" data-name="' + roleList[i].name + '"'
                                    + ' class="fa fa-pencil-square-o"></i>&nbsp;&nbsp;'
                                    + '<i id="' + roleList[i].id + '" class="fa fa-minus-circle"></i>&nbsp;&nbsp;';
                            }
                            tempStr += '</li>';

                            htmlStr += tempStr;
                        }
                        $("#roleFunction_roleList").html(htmlStr);

                        // 角色点击事件
                        $("#roleFunction_roleList li").click(function (e) {
                            $("#roleFunction_roleList li").removeClass("active");
                            $(this).addClass("active");
                            // 右侧显示名称
                            $("#roleFunction_roleId").val(e.currentTarget.getAttribute("data-id"));
                            $("#roleFunction_treeRoleName").val(e.currentTarget.getAttribute("data-name"));
                            loadTreeData();
                        });

                        // 角色编辑事件
                        $("#roleFunction_roleList li .fa-pencil-square-o").click(function (e) {
                            e.stopPropagation();
                            openRoleDialog(e);
                        });

                        // 角色删除事件
                        $("#roleFunction_roleList li .fa-minus-circle").click(function (e) {
                            e.stopPropagation();
                            // 提示框：【确定】删除；【取消】不删除
                            BJUI.alertmsg('confirm', '确定要删除该角色？', {
                                okCall: function () {
                                    BJUI.ajax('doajax', {
                                        url: 'system/roleFunction/delRoleById',
                                        data: {
                                            id: e.currentTarget.id
                                        },
                                        loadingmask: true,
                                        okCallback: function (json, options) {
                                            // 提示成功，关闭dialog
                                            BJUI.alertmsg('ok', '删除成功！', {});
                                            // 刷新角色列表
                                            getRoleListFn();
                                            $("#roleFunction_treeRoleName").val("");
                                            $("#roleFunction_roleId").val("");
                                            // 菜单tree
                                            var functionTree = $.fn.zTree.getZTreeObj("roleFunction_functionTree");
                                            // 权限tree
                                            var authorityTree = $.fn.zTree.getZTreeObj("roleFunction_authorityTree");
                                            // 全部取消勾选
                                            functionTree.checkAllNodes(false);
                                            functionTree.expandAll(true);
                                            authorityTree.checkAllNodes(false);
                                        }
                                    });
                                }
                            });
                        });
                    }
                });
            };

            // 新增角色 click事件
            $("#roleFunction_addRoleBtn").click(function () {
                openRoleDialog(null);
            });

            // 加载树数据
            function loadTreeData() {
                var id = $("#roleFunction_roleId").val();
                // 菜单tree
                var functionTree = $.fn.zTree.getZTreeObj("roleFunction_functionTree");
                // 权限tree
                var authorityTree = $.fn.zTree.getZTreeObj("roleFunction_authorityTree");
                // 全部取消勾选
                functionTree.checkAllNodes(false);
                functionTree.expandAll(true);
                // 全部取消勾选
                authorityTree.checkAllNodes(false);

                BJUI.ajax('doajax', {
                    url: 'system/roleFunction/getRoleFunctions',
                    data: {
                        roleId: id
                    },
                    loadingmask: true,
                    okCallback: function (res, options) {
                        if (res.roleFunctions == null || res.roleFunctions.length == 0) {
                            return;
                        }
                        var functions = res.roleFunctions.split(",");
                        for (var i = 0; i < functions.length; i++) {
                            // 菜单节点
                            var node = functionTree.getNodeByParam("id", functions[i], null);
                            if (node != null) {
                                functionTree.checkNode(node, true, true);
                                continue;
                            }
                            // 权限节点
                            var node2 = authorityTree.getNodeByParam("id", functions[i], null);
                            if (node2 != null) {
                                authorityTree.checkNode(node2, true, true);
                            }
                        }
                    }
                });
            };
            // ---------- 左侧角色列表 end---------

            // ----- 角色权限页面 start ------
            var functionSetting = {
                check: {
                    enable: true,
                    chkboxType: { "Y": "p", "N": "s" }
                },
                data: {
                    simpleData: {
                        enable: true,
                        idKey: "id",
                        pIdKey: "parentId",
                    },
                    key: {
                        checked: "checked", // 保存 check 状态的属性名称
                        name: "name"

                    }
                },
                async: {
                    enable: true, // async配置生效
                    otherParam: ["type", "3",],
                    url: "system/roleFunction/getFunctionList"
                }
            };
            var authoritySetting = {
                check: {
                    enable: true
                },
                data: {
                    simpleData: {
                        enable: true,
                        idKey: "id",
                        pIdKey: "parentId",
                    },
                    key: {
                        checked: "checked", // 保存 check 状态的属性名称
                        name: "name"

                    }
                },
                async: {
                    enable: true, // async配置生效
                    otherParam: ["type", "4",],
                    url: "system/roleFunction/getFunctionList"
                }
            };

            $.fn.zTree.init($("#roleFunction_functionTree"), functionSetting);
            $.fn.zTree.init($("#roleFunction_authorityTree"), authoritySetting);

            // 菜单树/权限树 保存
            $("#roleFunction_saveBtn").click(function () {
                var roleId = $("#roleFunction_roleId").val();
                if (roleId == null || roleId.length == 0) {
                    BJUI.alertmsg('info', '请先选择需要配置的角色！', {});
                    return;
                }

                // 菜单tree
                var functionTree = $.fn.zTree.getZTreeObj("roleFunction_functionTree");
                var functionNodes = functionTree.getCheckedNodes(true);
                // 权限tree
                var authorityTree = $.fn.zTree.getZTreeObj("roleFunction_authorityTree");
                var authorityNodes = authorityTree.getCheckedNodes(true);

                var nodes = $.merge(functionNodes, authorityNodes);
                var functionIds = [];
                for (var i = 0; i < nodes.length; i++) {
                    functionIds.push(nodes[i].id);
                }

                BJUI.ajax('doajax', {
                    url: 'system/roleFunction/editRoleFunctions',
                    data: {
                        roleId: roleId,
                        functionIds: functionIds.join(",")
                    },
                    loadingmask: true,
                    okCallback: function (res, options) {
                        // 提示成功，关闭dialog
                        BJUI.alertmsg('ok', '保存成功！', {});
                    }
                });

            });

            // 菜单树/权限树 刷新
            $("#roleFunction_refreshBtn").click(function () {
                loadTreeData();
            });
            // ----- 角色权限页面 end ------

            // ----- 页码初始化 start ------
            // 加载角色列表
            getRoleListFn();
            // ----- 页码初始化 end ------
        }();
    });
</script>
