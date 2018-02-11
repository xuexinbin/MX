//@ sourceURL=recharge.js
$(function () {
    void function () {
        var columns = [
            {
                name: 'vipName',
                label: '会员名',
                width: 80,
                align: 'center'
            }, {
                name: 'vipNumber',
                label: '会员号',
                width: 100
            }, {
                name: 'vipPhone',
                label: '会员手机号',
                width: 120
            }, {
                name: 'money',
                label: '充值金额',
                width: 80
            }, {
                name: 'giveMoney',
                label: '赠送金额',
                width: 80
            }, {
                name: 'givePoint',
                label: '赠送积分',
                width: 80
            }, {
                name: 'type',
                label: '类别',
                width: 70,
                align: 'center',
                type: "select",
                items: [{0: '充值'}, {1: '退款'}]
            }, {
                name: 'rechargeType',
                label: '付款方式',
                width: 100,
                align: 'center',
                type: "select",
                items: [{0: '现金'}, {1: '微信'}, {2: '支付宝'}, {3: '网银'}, {4: '刷卡'}, {5: '其他'}]
            }, {
                name: 'rechargeTime',
                label: '充值时间',
                width: 180,
                align: 'center',
                render: function (value, data) {
                    var str = new Date(value).formatDate("yyyy-MM-dd HH:mm:ss");
                    return str;
                }
            }, {
                label: '操作',
                align: 'center',
                width: 80,
                render: function (value, data) {
                    var html = '<i class="fa fa-info-circle grid-icon grid-blue recharge-showVipRechargeDialog" val="' + data.id + '" title="查看详情"></i>';
                    return html;
                }
            },
        ];

        var operateRechargeFn = function (selectedId, type) {
            var postData = {};
            var title = "";
            if (type == "add") {
                title = '新增充值';
            } else {
                postData = {id: selectedId}
                title = '充值信息';
            }

            BJUI.dialog({
                id: 'recharge_addRechargeDialogId',
                title: title,
                postData: postData,
                mask: true, // 模态
                maxable: true, // 显示最大化按钮
                width: 700,
                height: 380,
                url: 'cash/vip/addRechargeDialog',
                onLoad: function ($dialog) {
                    if (type == "add") {
                        $("#addRechargeDialog_rechargeTime").val(new Date().formatDate('yyyy-MM-dd HH:mm:ss'));
                        // 保存充值事件
                        $("#addRechargeDialog_saveBtn").click(function () {
                            // 校验失败 return
                            if (!$('#addRechargeDialog_form').isValid()) {
                                return;
                            }
                            BJUI.ajax('ajaxform', {
                                url: 'cash/vip/addRecharge',
                                form: $.CurrentDialog.find('form'),
                                validate: true,
                                loadingmask: true,
                                okCallback: function (json, options) {
                                    BJUI.alertmsg('ok', MX.msg.saveSuccess, {});
                                    BJUI.dialog('close', 'recharge_addRechargeDialogId')
                                    $("#recharge_grid").datagrid('refresh', true);
                                }
                            });

                        });
                    } else {
                        // 表单只读
                        $("#addRechargeDialog_form").setFormReadOnly();
                        // 删除保存按钮
                        $("#addRechargeDialog_saveBtn").hide();

                        // 获得充值信息
                        BJUI.ajax('doajax', {
                            url: 'cash/vip/getVipRechargeInfoById',
                            data: {
                                id: selectedId
                            },
                            loadingmask: true,
                            okCallback: function (res, options) {
                                if (res == null && res.vipRechargeInfo == null) {
                                    return;
                                }
                                var vipRechargeInfo = res.vipRechargeInfo;
                                // 会编号
                                $("#addRechargeDialog_vipNumber").val(vipRechargeInfo.vipNumber);
                                // 会员名
                                $("#addRechargeDialog_vipName").val(vipRechargeInfo.vipName);
                                // 余额
                                $("#addRechargeDialog_vipRemain").val(vipRechargeInfo.vipRemain);
                                // 赠送余额
                                $("#addRechargeDialog_money").val(vipRechargeInfo.money);
                                // 赠送余额
                                $("#addRechargeDialog_giveMoney").val(vipRechargeInfo.giveMoney);
                                // 赠送积分
                                $("#addRechargeDialog_givePoint").val(vipRechargeInfo.givePoint);
                                // 类型
                                vipRechargeInfo.type == 0 ? $("#addRechargeDialog_type0").iCheck('check')
                                    : $("#addRechargeDialog_type1").iCheck('check');

                                // 类别
                                $("#addRechargeDialog_rechargeType").selectpicker("val", vipRechargeInfo.rechargeType);
                                // 充值日期
                                if (vipRechargeInfo.rechargeTime != null) {
                                    $("#addRechargeDialog_rechargeTime").val(new Date(vipRechargeInfo.rechargeTime).formatDate('yyyy-MM-dd HH:mm:ss'));
                                }
                                // 经办人
                                $("#addRechargeDialog_operator").val(vipRechargeInfo.operator);
                                // 备注
                                $("#addRechargeDialog_memo").val(vipRechargeInfo.memo);
                            }
                        });
                    }
                }
            });

        };
        var addFunction = function () {
            operateRechargeFn(null, "add");
        };
        var showFunction = function () {
            operateRechargeFn($(this).attr("val"), "show");
        };

        $('#recharge_grid').datagrid({
            fullGrid: true,
            height: '100%',
            gridTitle: '充值单列表',
            showToolbar: true,
            selectMult: true,
            filterThead: true,
            columnMenu: true,
            toolbarItem: "add",
            addName: "充值",
            addFunction: addFunction,
            delUrl: 'cash/vip/deleteVipByIds',
            delType: 'POST',
            delPK: 'id',
            paging: {pageSize: 30},
            showCheckboxcol: true,
            linenumberAll: true,
            local: 'local',
            dataUrl: 'cash/vip/getVipRechargeGridData',
            columns: columns,
            // tableWidth: '100%'
        });

        // 查看详情
        $.CurrentNavtab.on("click", ".recharge-showVipRechargeDialog", showFunction);
    }();
});