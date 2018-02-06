<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">
    <form data-toggle="ajaxsearch" data-options="{searchDatagrid:$.CurrentNavtab.find('#user_grid')}">
        <fieldset>
            <legend style="font-weight:normal;">搜索</legend>
            <div style="margin:0; padding:1px 5px 5px;">
                <span>模糊匹配：</span>
                <input type="text" name="likeStr" class="form-control" size="25" placeholder="工号／姓名／登录名／手机号">

                <span>&nbsp;所属部门：</span>
                <select id="user_departmentId" name="departmentId" data-toggle="selectpicker"
                        data-width="160">
                </select>

                <span>&nbsp;状态：</span>
                <select name="enablef" data-toggle="selectpicker" data-width="80">
                    <option value="" selected="">全部</option>
                    <option value="0">启用</option>
                    <option value="1">禁用</option>
                </select>
                <div class="btn-group" style="float: right">
                    <button type="submit" class="btn-green" data-icon="search">查询</button>
                    <button type="button" class="btn-orange" data-toggle="reset" data-icon="undo">重置</button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
<div class="bjui-pageContent">
    <table id="user_grid" class="table table-bordered"></table>
</div>

<script type="text/javascript">
    //@ sourceURL=user.js
    $(function () {
        void function () {
            var columns = [
                {
                    name: 'avatar',
                    label: '头像',
                    align: 'center',
                    width: 80,
                    quicksort: false,
                    render: function (value, data) {
                        if (value == null || value == "") {
                            return '<i class="fa fa-picture-o" style="font-size: 37px;color: rgba(217, 231, 242, 0.58);"></i>';
                        }
                        return '<img src="' + value + '" height="40" width="40" style="border-radius:3px;border: 1px solid #ddd;"/>';
                    }
                },
                {
                    name: 'number',
                    label: '编号',
                    width: 150,
                    align: 'center'
                },
                {
                    name: 'trueName',
                    label: '姓名',
                    width: 150,
                    align: 'center'
                },
                {
                    name: 'userName',
                    label: '登录名',
                    width: 150,
                    align: 'center'
                },
                {
                    name: 'sex',
                    label: '性别',
                    width: 70,
                    align: 'center',
                    type: "select",
                    items: [{0: '女'}, {1: '男'}]
                },
                {
                    name: 'departmentId',
                    label: '所属部门',
                    width: 150,
                    align: 'center',
                    render: function (value, data) {
                        return data.departmentName == null ? "无" : data.departmentName;
                    }
                },
                {
                    name: 'phone',
                    label: '手机',
                    width: 150,
                    align: 'center'
                },
                {
                    name: 'joinTime',
                    label: '入职时间',
                    width: 150,
                    align: 'center',
                    render: function (value, data) {
                        if (value == null || value == "") {
                            return;
                        }
                        return new Date(value).formatDate('yyyy-MM-dd');
                    }
                },
                {
                    name: 'memo',
                    label: '备注',
                    width: 150,
                    align: 'center'
                },
                {
                    name: 'enablef',
                    label: '状态',
                    width: 70,
                    align: 'center',
                    type: "select",
                    items: [{0: '启用'}, {1: '禁用'}],
                    render: function (value, data) {
                        return value == 0 ? '<span class="tabletag bggreen">启用</span>'
                            : '<span class="tabletag bgred">禁用</span>';
                    }
                },
                {
                    label: '操作',
                    align: 'center',
                    width: 180,
                    render: function (value, data) {
                        var html = '<i class="fa fa-edit grid-icon grid-edit user-editUserDialog" val="' + data.id + '" title="编辑"></i>&nbsp;&nbsp;&nbsp;'
                            + '<i class="fa fa-times grid-icon grid-del" data-toggle="del.datagrid.tr" title="删除"></i>';
                        return html;
                    }
                },
            ];

            var addFunction = function (selectedId) {
                var isEdit = typeof selectedId === "string" && selectedId != null ? true : false;
                var postData = {};
                var title = "";
                if (isEdit) {
                    postData = {id: selectedId}
                    title = '编辑用户信息';
                } else {
                    title = '新增用户信息';
                }

                BJUI.dialog({
                    id: 'user_editUserDialogId',
                    title: title,
                    postData: postData,
                    mask: true, // 模态
                    maxable: true, // 显示最大化按钮
                    width: 700,
                    height: 500,
                    url: 'system/user/editUserDialog',
                    onLoad: function ($dialog) {
                        // 图片上传插件
                        var uploader = $("#editUserDialog_avatarPicker").initUpload({
                            server: 'system/user/editUser',
                            thumbPick: "#editUserDialog_avatar",
                            uploadBeforeSend: function (block, data) {
                                data.id = $("#editUserDialog_id").val();
                                data.trueName = $("#editUserDialog_trueName").val();
                                data.number = $("#editUserDialog_number").val();
                                data.sex = $("#editUserDialog_sex").val();
                                data.birthday = $("#editUserDialog_birthday").val();
                                data.phone = $("#editUserDialog_phone").val();
                                data.mail = $("#editUserDialog_mail").val();
                                data.joinTime = $("#editUserDialog_joinTime").val();
                                data.departmentId = $("#editUserDialog_departmentId").val();
                                data.roleIds = $("#editUserDialog_roleIds").val();
                                data.userName = $("#editUserDialog_userName").val();
                                data.password = $("#editUserDialog_password").val();
                                data.enablef = $("#editUserDialog_enablef0")[0].checked ? 0 : 1;
                                data.memo = $("#editUserDialog_memo").val();
                            },
                            uploadSuccess: function (file, response) {
                                BJUI.alertmsg('ok', MX.msg.saveSuccess, {});
                                $("#editUserDialog_form").setChanged(false);
                                $("#user_grid").datagrid('refresh', true);
                            }
                        })

                        // 密码md5加密
                        $("#editUserDialog_password").on("change", function () {
                            this.value = hex_md5(this.value);
                        });

                        // 保存用户
                        $("#editUserDialog_saveBtn").click(function () {
                            // 校验失败 return
                            if (!$('#editUserDialog_form').isValid()) {
                                return;
                            }
                            // 数据未修改 return
                            if (!$("#editUserDialog_form").formHasChanged()) {
                                BJUI.alertmsg('info', MX.msg.dataNoChange, {});
                                return;
                            }
                            // 存在需要上传的文件：使用webupload上传 否则ajaxform
                            if (uploader.getFiles("inited").length > 0) {
                                uploader.upload();
                            } else {
                                BJUI.ajax('ajaxform', {
                                    url: 'system/user/editUser',
                                    form: $.CurrentDialog.find('form'),
                                    validate: true,
                                    loadingmask: true,
                                    okCallback: function (json, options) {
                                        BJUI.alertmsg('ok', MX.msg.saveSuccess, {});
                                        $("#editUserDialog_form").setChanged(false);
                                        $("#user_grid").datagrid('refresh', true);
                                    }
                                })
                            }

                        });

                        if (isEdit) {
                            // 获得员工信息
                            BJUI.ajax('doajax', {
                                url: 'system/user/getUserInfoById',
                                data: {
                                    id: selectedId
                                },
                                loadingmask: true,
                                okCallback: function (res, options) {
                                    if (res == null && res.userInfo == null) {
                                        return;
                                    }
                                    var userInfo = res.userInfo;
                                    // 初始化下拉框
                                    $('#editUserDialog_departmentId').initSelect(res.deptList);
                                    $('#editUserDialog_roleIds').initSelect(res.roleList);

                                    // 头像
                                    if (userInfo.avatar != null && userInfo.avatar != "") {
                                        $("#editUserDialog_avatar").html("<img src='" + userInfo.avatar + "' height='100' width='100'/>")
                                    }
                                    // 员工信息赋值
                                    $("#editUserDialog_id").val(userInfo.id);
                                    // 真实姓名
                                    $("#editUserDialog_trueName").val(userInfo.trueName);
                                    // 工号
                                    $("#editUserDialog_number").val(userInfo.number);
                                    // 性别
                                    $("#editUserDialog_sex").selectpicker('val', userInfo.sex);
                                    // 出生日期
                                    if (userInfo.birthday != null) {
                                        $("#editUserDialog_birthday").val(new Date(userInfo.birthday).formatDate('yyyy-MM-dd'));
                                    }
                                    // 手机
                                    $("#editUserDialog_phone").val(userInfo.phone);
                                    // 邮箱
                                    $("#editUserDialog_mail").val(userInfo.mail);
                                    // 入职时间
                                    if (userInfo.joinTime != null) {
                                        $("#editUserDialog_joinTime").val(new Date(userInfo.joinTime).formatDate('yyyy-MM-dd'));
                                    }
                                    // 部门
                                    $("#editUserDialog_departmentId").selectpicker("val", userInfo.departmentId);
                                    // 所属角色
                                    if (userInfo.roleIds != null) {
                                        $("#editUserDialog_roleIds").selectpicker('val', userInfo.roleIds.split(","));
                                    }
                                    // 登录用户名
                                    $("#editUserDialog_userName").val(userInfo.userName);
                                    // 登录密码
                                    $("#editUserDialog_password").val(userInfo.password);
                                    // 启用
                                    $("#editUserDialog_enablef").val(userInfo.enablef);
                                    userInfo.enablef == 0 ? $("#editUserDialog_enablef0").iCheck('check')
                                        : $("#editUserDialog_enablef1").iCheck('check');
                                    // 备注
                                    $("#editUserDialog_memo").val(userInfo.memo);

                                    // 是否修改校验
                                    $("#editUserDialog_form").formChangedCheck();
                                }
                            });
                        } else {
                            $('#editUserDialog_departmentId').loadSelect("system/department/getDepartmentList");
                            $('#editUserDialog_roleIds').loadSelect("system/user/getRoleList");
                            $("#editUserDialog_form").formChangedCheck();
                        }

                    }
                });

            };

            var editFunction = function () {
                addFunction($(this).attr("val"));
            };

            $('#user_grid').datagrid({
                fullGrid: true,
                height: '100%',
                gridTitle: '用户列表',
                showToolbar: true,
                selectMult: true,
                filterThead: false,
                columnMenu: false,
                toolbarItem: "add, del",
                addName: "新增用户",
                delName: "删除用户",
                addFunction: addFunction,
                delUrl: 'system/user/deleteUserByIds',
                delType: 'POST',
                delPK: 'id',
                paging: {pageSize: 30},
                showCheckboxcol: true,
                linenumberAll: true,
                local: 'local',
                dataUrl: 'system/user/getUserGridData',
                columns: columns,
                //tableWidth: '100%',
                afterSave: function ($trs, datas) {
                    // 提示成功，关闭dialog
                    BJUI.alertmsg('ok', '保存成功！', {});
                },
                afterLoad: function () {
                }
            });

            // 初始化下拉框
            $('#user_departmentId').loadSelect("system/department/getSearhDepartmentList");

            // 点击打开编辑画面事件
            $.CurrentNavtab.on("click", ".user-editUserDialog", editFunction);
        }();
    });
</script>