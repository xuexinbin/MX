<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">
    <form data-toggle="ajaxsearch" data-options="{searchDatagrid:$.CurrentNavtab.find('#user_grid')}">
        <fieldset>
            <legend style="font-weight:normal;">高级搜索：</legend>
            <div style="margin:0; padding:1px 5px 5px;">
                <span>用户名：</span>
                <input type="text" name="name" class="form-control" size="15">

                <span>所属部门：</span>
                <input type="text" name="obj.name" class="form-control" size="15">

                <div class="btn-group">
                    <button type="submit" class="btn-green" data-icon="search">开始搜索！</button>
                    <button type="reset" class="btn-orange" data-icon="times">重置</button>
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
                    name: 'departmentId',
                    label: '所属部门',
                    width: 150,
                    align: 'center',
                    render: function (value, data) {
                        return data.departmentName;
                    }
                },
                {
                    name: 'phone',
                    label: '手机',
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
                    name: 'joinTime',
                    label: '入职时间',
                    width: 150,
                    align: 'center'
                },
                {
                    name: 'memo',
                    label: '备注',
                    width: 150,
                    align: 'center'
                },
                {
                    name: 'enable',
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
                        var html = '<button type="button" class="user-editUserDialog btn-green"  val="' + data.id + '">编辑</button>&nbsp;&nbsp;'
                            + '<button type="button" class="btn-red" data-toggle="del.datagrid.tr">删除</button>'
                        return html;
                    }
                },
            ];

            var addFunction = function (selectedId) {
                var postData = {};
                var title = "";
                if (typeof selectedId === "string" && selectedId != null) {
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
                        // 头像文件选中
                        var uploader = WebUploader.create({
                            auto: false,
                            server: 'system/user/editUser',
                            pick: '#user_avatarPicker',
                            swf: BJUI.PLUGINPATH + 'webuploader/Uploader.swf',
                            duplicate: false,
                            mxUpload: true
                        });
                        // 当有文件添加进来的时候
                        uploader.on('fileQueued', function (file) {
                            if (file.size > 1024*1024) {
                                BJUI.alertmsg('error', '图片不能超过1M，请重新选择！')
                                return;
                            }
                            // 判断文件格式：使用accept影响打开选中框的速度
                            var imgReg = /^(jpg|jpeg|png|bmp)$/;
                            if (!imgReg.test(file.ext)) {
                                BJUI.alertmsg('error', '请选择图片文件！')
                                return;
                            }
                            // 创建缩略图
                            uploader.makeThumb(file, function (error, src) {
                                if (error) {
                                    $("#user_avatar").html('<span>预览失败</span>');
                                    return;
                                }
                                $("#user_avatar").html('<img>');
                                $("#user_avatar img").attr("src", src);
                            }, 100, 100);
                        });

                        uploader.on("uploadComplete", function () {
                            uploader.reset()
                        });

                        uploader.on( 'uploadBeforeSend', function( block, data ) {
                            // block为分块数据。

                            // file为分块对应的file对象。
                            var file = block.file;


                            // 修改data可以控制发送哪些携带数据。
                            data.uid = 123;
                            data.a = 11;
                            data.b = 22;
                            data.c = 33;
                        });

                        uploader.on( 'startUpload', function( block, data ) {
                            // block为分块数据。

                            // file为分块对应的file对象。
                            var file = block.file;


                            // 修改data可以控制发送哪些携带数据。
                            data.uid = 123;
                        });
                        // 保存用户
                        $("#editUserDialog_saveBtn").click(function () {
                            uploader.upload();
                            return;
                            $("#editUserDialog_password").val(hex_md5($("#editUserDialog_password").val()));
                            BJUI.ajax('ajaxform', {
                                url: 'system/user/editUser',
                                form: $.CurrentDialog.find('form'),
                                validate: true,
                                loadingmask: true,
                                okCallback: function (json, options) {
                                    // 提示成功，关闭dialog
                                    BJUI.alertmsg('ok', '保存成功！', {});
                                    $("#user_grid").datagrid('refresh', true);
                                }
                            })

                        });

                        if (selectedId != null) {
                            // 获得员工信息
                            BJUI.ajax('doajax', {
                                url: 'system/user/getUserInfoById',
                                data: {
                                    id: selectedId
                                },
                                loadingmask: true,
                                okCallback: function (res, options) {
                                    var userInfo = res.userInfo;
                                    // 头像
                                    if (userInfo.avatar != null && userInfo.avatar != "") {
                                        // r=Math.random() 去缓存
                                        $("#editUserDialog_avatar")[0].setAttribute("src", "/hr/getImg?url=" + userInfo.avatar + "&r=" + Math.random())
                                    }
                                    // 员工信息赋值
                                    $("#editUserDialog_id").val(userInfo.id);
                                    // 真实姓名
                                    $("#editUserDialog_trueName").val(userInfo.trueName);
                                    // 邮箱
                                    $("#editUserDialog_mail").val(userInfo.mail);
                                    // 性别
                                    $("#editUserDialog_sex").selectpicker('val', userInfo.sex);
                                    // 手机
                                    $("#editUserDialog_phone").val(userInfo.phone);
                                    // 工号
                                    $("#editUserDialog_number").val(userInfo.number);
                                    // 入职时间
                                    if (userInfo.joinTime != null) {
                                        $("#editUserDialog_joinTime").val(new Date(userInfo.joinTime).formatDate('yyyy-MM-dd'));
                                    }
                                    // 登录用户名
                                    $("#editUserDialog_userName").val(userInfo.userName);
                                    // 登录密码
                                    $("#editUserDialog_password").val(userInfo.password);
                                    // 部门
                                    $("#editUserDialog_departmentId").val(userInfo.departmentId);
                                    // 启用
                                    $("#editUserDialog_enablef").val(userInfo.enablef);
                                    userInfo.enablef == 0 ? $("#editUserDialog_enablef0").iCheck('check')
                                        : $("#editUserDialog_enablef1").iCheck('check');
                                    // 备注
                                    $("#editUserDialog_memo").val(userInfo.memo);
                                }
                            });
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
                delUrl: 'system/department/deleteDepartmentByIds',
                delType: 'POST',
                delPK: 'id',
                editUrl: 'system/department/editDepartment',
                paging: {pageSize: 30},
                showCheckboxcol: true,
                linenumberAll: true,
                local: 'local',
                dataUrl: 'system/user/getUserGridData',
                columns: columns,
                //tableWidth: '100%',
                //toolbarCustom: toolBarHtml,
                afterSave: function ($trs, datas) {
                    // 提示成功，关闭dialog
                    BJUI.alertmsg('ok', '保存成功！', {});
                },
                afterLoad: function () {
                    //console.log(99999);
                }
            });

            //点击打开编辑画面
            $.CurrentNavtab.on("click", ".user-editUserDialog", editFunction);
        }();
    });
</script>