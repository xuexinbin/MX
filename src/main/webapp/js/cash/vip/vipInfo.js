//@ sourceURL=vipInfo.js
$(function () {
    void function () {
        var columns = [
            {
                name: 'name',
                label: '姓名',
                width: 120,
                align: 'center'
            }, {
                name: 'number',
                label: '会员编号',
                width: 120
            }, {
                name: 'sex',
                label: '性别',
                width: 70,
                align: 'center',
                type: "select",
                items: [{0: '女'}, {1: '男'}]
            }, {
                name: 'type',
                label: '会员类型',
                width: 100,
                align: 'center',
                type: "select",
                items: [{0: '普通会员'}, {1: '白银会员'}, {2: '黄金会员'}, {3: '白金会员'}, {4: '钻石会员'}]
            }, {
                name: 'remain',
                label: '余额',
                width: 150,
                align: 'center'
            }, {
                name: 'point',
                label: '积分',
                width: 150,
                align: 'center'
            }, {
                name: 'phone',
                label: '手机号',
                width: 150,
                align: 'center'
            }, {
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
                width: 150,
                render: function (value, data) {
                    var html = '<i class="fa fa-info-circle grid-icon grid-blue vip-showVipDialog" val="' + data.id + '" title="查看详情"></i>&nbsp;&nbsp;&nbsp;'
                        + '<i class="fa fa-edit grid-icon grid-edit vip-editVipDialog" val="' + data.id + '" title="编辑"></i>&nbsp;&nbsp;&nbsp;'
                        + '<i class="fa fa-times grid-icon grid-del" data-toggle="del.datagrid.tr" title="删除"></i>';
                    return html;
                }
            },
        ];

        var operateVipFn = function (selectedId, type) {
            var postData = {};
            var title = "";
            if (type == "edit") {
                postData = {id: selectedId}
                title = '编辑会员信息';
            } else if (type == "add"){
                title = '新增会员信息';
            } else {
                postData = {id: selectedId}
                title = '会员信息';
            }

            BJUI.dialog({
                id: 'vipInfo_editVipInfoDialogId',
                title: title,
                postData: postData,
                mask: true, // 模态
                maxable: true, // 显示最大化按钮
                width: 700,
                height: 500,
                url: 'cash/vip/editVipDialog',
                onLoad: function ($dialog) {
                    if (type == "show") {
                        // 表单只读
                        $("#editVipInfoDialog_form").setFormReadOnly();
                        // 删除保存按钮
                        $("#editVipInfoDialog_saveBtn").hide();
                    } else {
                        // 保存会员事件
                        $("#editVipInfoDialog_saveBtn").click(function () {
                            // 校验失败 return
                            if (!$('#editVipInfoDialog_form').isValid()) {
                                return;
                            }
                            // 数据未修改 return
                            if (!$("#editVipInfoDialog_form").formHasChanged()) {
                                BJUI.alertmsg('info', MX.msg.dataNoChange, {});
                                return;
                            }
                            BJUI.ajax('ajaxform', {
                                url: 'cash/vip/editVip',
                                form: $.CurrentDialog.find('form'),
                                validate: true,
                                loadingmask: true,
                                okCallback: function (json, options) {
                                    BJUI.alertmsg('ok', MX.msg.saveSuccess, {});
                                    $("#editVipInfoDialog_form").setChanged(false);
                                    $("#vipInfo_grid").datagrid('refresh', true);
                                    BJUI.dialog('close', 'vipInfo_editVipInfoDialogId')
                                }
                            });

                        });
                    }

                    if (type != "add") {
                        // 获得会员信息
                        BJUI.ajax('doajax', {
                            url: 'cash/vip/getVipInfoById',
                            data: {
                                id: selectedId
                            },
                            loadingmask: true,
                            okCallback: function (res, options) {
                                if (res == null && res.vipInfo == null) {
                                    return;
                                }
                                var vipInfo = res.vipInfo;

                                // 会员信息赋值
                                $("#editVipInfoDialog_id").val(vipInfo.id);
                                // 会员名
                                $("#editVipInfoDialog_name").val(vipInfo.name);
                                // 会员编号
                                $("#editVipInfoDialog_number").val(vipInfo.number);
                                // 会员类型
                                $("#editVipInfoDialog_type").selectpicker("val", vipInfo.type);
                                // 会员余额
                                $("#editVipInfoDialog_remain").val(vipInfo.remain);
                                // 会员积分
                                $("#editVipInfoDialog_point").val(vipInfo.point);
                                // 性别
                                $("#editVipInfoDialog_sex").selectpicker('val', vipInfo.sex);
                                // 开户日期
                                if (vipInfo.openTime != null) {
                                    $("#editVipInfoDialog_openTime").val(new Date(vipInfo.openTime).formatDate('yyyy-MM-dd'));
                                }
                                // 启用
                                $("#editVipInfoDialog_enablef").val(vipInfo.enablef);
                                vipInfo.enablef == 0 ? $("#editVipInfoDialog_enablef0").iCheck('check')
                                    : $("#editVipInfoDialog_enablef1").iCheck('check');

                                // 性别
                                $("#editVipInfoDialog_sex").selectpicker("val", vipInfo.sex);
                                // 出生日期
                                if (vipInfo.birthday != null) {
                                    $("#editVipInfoDialog_birthday").val(new Date(vipInfo.birthday).formatDate('yyyy-MM-dd'));
                                }
                                // 手机
                                $("#editVipInfoDialog_phone").val(vipInfo.phone);
                                // 邮箱
                                $("#editVipInfoDialog_mail").val(vipInfo.mail);
                                // 微信
                                $("#editVipInfoDialog_weixin").val(vipInfo.weixin);
                                // qq
                                $("#editVipInfoDialog_qq").val(vipInfo.qq);
                                // 备注
                                $("#editVipInfoDialog_memo").val(vipInfo.memo);

                                if (type == "edit") {
                                    // 是否修改校验
                                    $("#editVipInfoDialog_form").formChangedCheck();
                                }
                            }
                        });
                    } else {
                        $("#editVipInfoDialog_openTime").val(new Date().formatDate('yyyy-MM-dd'));
                        $("#editVipInfoDialog_form").formChangedCheck();
                    }

                }
            });

        };
        var addFunction = function() {
            operateVipFn(null, "add");
        };
        var editFunction = function () {
            operateVipFn($(this).attr("val"), "edit");
        };
        var showFunction = function () {
            operateVipFn($(this).attr("val"), "show");
        };

        $('#vipInfo_grid').datagrid({
            fullGrid: true,
            height: '100%',
            gridTitle: '会员列表',
            showToolbar: true,
            selectMult: true,
            filterThead: false,
            columnMenu: false,
            toolbarItem: "add, del",
            addName: "新增会员",
            delName: "删除会员",
            addFunction: addFunction,
            delUrl: 'cash/vip/deleteVipByIds',
            delType: 'POST',
            delPK: 'id',
            paging: {pageSize: 30},
            showCheckboxcol: true,
            linenumberAll: true,
            local: 'local',
            dataUrl: 'cash/vip/getVipInfoGridData',
            columns: columns,
            // tableWidth: '100%',
            afterSave: function ($trs, datas) {
                // 提示成功，关闭dialog
                BJUI.alertmsg('ok', '保存成功！', {});
            },
            afterLoad: function () {
            }
        });

        // 编辑信息
        $.CurrentNavtab.on("click", ".vip-editVipDialog", editFunction);
        // 查看详情
        $.CurrentNavtab.on("click", ".vip-showVipDialog", showFunction);
    }();
});