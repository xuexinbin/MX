//@ sourceURL=systemMessage.js
$(function () {
    void function () {
        var gridId = "#systemMessage_grid";

        // 自定义工具栏
        var toolBarHtml = '<div class="btn-group" role="group">'
            + '<button type="button" class="btn-blue systemMessage-readMessage" data-icon="star-o">标记为已读</button>'
            + '</div>';

        var columns = [
            {
                name: 'title',
                label: '消息内容',
                width: 250,
                render: function (value, data) {
                    var str = data.read == 0 ? '&nbsp;' : '&nbsp<span class="message-read">';
                    str += data.read == 0 ? '<i class="fa fa-envelope color-blue"></i>&nbsp;' : '<i class="fa fa-envelope-open"></i>&nbsp;';
                    str += data.top == 0 ? '' : '<span class="tabletag-prefix bgblue">置顶</span>&nbsp;';
                    str += data.level == 0 ? '' : '<span class="tabletag-prefix bgred">紧急</span>&nbsp;';
                    str += data.important == 0 ? '' : '<span class="tabletag-prefix bgred">重要</span>&nbsp;';
                    str += value;
                    str += data.read == 0 ? '' : '</span>';
                    return str;
                }
            }, {
                name: 'type',
                label: '消息类别',
                width: 80,
                align: 'center',
                render: function (value, data) {
                    var str = "";
                    switch (value) {
                        case 0:
                            str = "系统消息";
                            break;
                        case 1:
                            str = "私信";
                            break;
                    }
                    return data.read == 0 ? str : '<span class="message-read">' + str + '</span>';
                }
            }, {
                name: 'addTime',
                label: '发布时间',
                width: 120,
                align: 'center',
                render: function (value, data) {
                    var str = new Date(value).formatDate("yyyy-MM-dd HH:mm:ss");
                    return data.read == 0 ? str : '<span class="message-read">' + str + '</span>';
                }
            }, {
                label: '操作',
                align: 'center',
                width: 120,
                render: function (value, data) {
                    var html = '<button type="button" class="btn-blue systemMessage-showMessage" data-id="' + data.id + '" data-userMessageId="' +data.userMessageId+ '" data-title="' + data.title + '">查看</button>&nbsp;&nbsp;'
                        + '<button type="button" class="btn-red" data-toggle="del.datagrid.tr">删除</button>'
                    return html;
                }
            },
        ];
        // 我的私信列
        var readOnlyColumns = [
            {
                name: 'title',
                label: '消息内容',
                width: 250,
                render: function (value, data) {
                    var str = '&nbsp;';
                    str += data.top == 0 ? '' : '<span class="tabletag-prefix bgblue">置顶</span>&nbsp;';
                    str += data.level == 0 ? '' : '<span class="tabletag-prefix bgred">紧急</span>&nbsp;';
                    str += data.important == 0 ? '' : '<span class="tabletag-prefix bgred">重要</span>&nbsp;';
                    str += value;
                    return str;
                }
            }, {
                name: 'type',
                label: '消息类别',
                width: 80,
                align: 'center',
                render: function (value, data) {
                    var str = "";
                    switch (value) {
                        case 0:
                            str = "系统消息";
                            break;
                        case 1:
                            str = "私信";
                            break;
                    }
                    return str;
                }
            }, {
                name: 'addTime',
                label: '发布时间',
                width: 120,
                align: 'center',
                render: function (value, data) {
                    var str = new Date(value).formatDate("yyyy-MM-dd HH:mm:ss");
                    return str;
                }
            }, {
                label: '操作',
                align: 'center',
                width: 90,
                render: function (value, data) {
                    var html = '<button type="button" class="btn-blue systemMessage-showMessage" data-id="' + data.id + '" data-title="' + data.title + '">查看</button>'
                    return html;
                }
            },
        ];

        // 消息类别点击事件
        $("#systemMessage_type li a").click(function (e) {
            $("#systemMessage_type li").removeClass("active");
            $(this).parent().addClass("active");
            var type= e.currentTarget.getAttribute("data-type");
            var oldType = $("#systemMessage_typeId").val();

            if ((oldType == 0 || oldType ==1) && type == 2){
                $("#systemMessage_typeId").val(type);
                $(gridId).datagrid('reload', {
                    columns: readOnlyColumns,
                    toolbarCustom: '',
                    toolbarItem: 'add',
                    showCheckboxcol: false,
                    postData: {
                        type: $("#systemMessage_typeId").val()
                    }
                });
            } else if ( oldType == 2 && (type ==0 || type==1)) {
                $("#systemMessage_typeId").val(type);
                $(gridId).datagrid('reload', {
                    columns: columns,
                    toolbarCustom: toolBarHtml,
                    toolbarItem: 'del',
                    showCheckboxcol: true,
                    postData: {
                        type: $("#systemMessage_typeId").val()
                    }
                });
            } else {
                $("#systemMessage_typeId").val(type);
                $(gridId).datagrid("refresh", {type: $("#systemMessage_typeId").val()});
            }
        });

        // 添加消息
        var addMessageDialog = function () {
            BJUI.dialog({
                id: 'systemMessage_addMessageDialog',
                title: "新消息",
                postData: {},
                mask: true, // 模态
                maxable: true, // 显示最大化按钮
                width: 900,
                height: 680,
                url: 'system/systemMessage/addMessageDialog',
                onLoad: function ($dialog) {
                    // 创建富文本编辑框
                    var options = {
                        minHeight: 300,
                        uploadJson: '/system/systemMessage/uploadImg',
                        afterBlur: function () {
                            this.sync();
                        },
                        //allowImageRemote: false, //显示网络图片标签
                        items: ['undo', 'redo',
                            '|', 'preview', 'cut', 'copy', 'paste', 'plainpaste', 'wordpaste',
                            '|', 'justifyleft', 'justifycenter', 'justifyright', 'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'clearhtml', 'quickformat', 'selectall',
                            '|', 'fullscreen', '/',
                            'formatblock', 'fontname', 'fontsize',
                            '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat',
                            '|', 'image', 'table', 'hr', 'emoticons', 'pagebreak', 'link', 'unlink'],

                    };
                    KindEditor.create('#addMessageDialog_content', options);

                    // 保存消息
                    $("#addMessageDialog_saveBtn").click(function () {
                        // 校验失败 return
                        if (!$('#addMessageDialog_form').isValid()) {
                            return;
                        }
                        BJUI.ajax('doajax', {
                            url: 'system/systemMessage/addMessage',
                            data: {
                                title: $("#addMessageDialog_title").val(),
                                content: $("#addMessageDialog_content").val(),
                                userIds: $("#addMessageDialog_userIds").val(),
                                type: $("#addMessageDialog_type0")[0].checked ? 1 : 0,
                                top: $("#addMessageDialog_top")[0].checked ? 1 : 0,
                                important: $("#addMessageDialog_important0")[0].checked ? 0 : 1,
                                level: $("#addMessageDialog_level1")[0].checked ? 0 : 1
                            },
                            loadingmask: true,
                            okCallback: function (res, options) {
                                BJUI.alertmsg('ok', MX.msg.saveSuccess, {});
                                BJUI.dialog('close', 'systemMessage_addMessageDialog')
                            }
                        });
                    });

                }
            });
        }

        $(gridId).datagrid({
            fullGrid: true,
            height: '100%',
            gridTitle: '消息列表',
            editMode: 'inline',
            showToolbar: true,
            selectMult: true,
            filterThead: false,
            columnMenu: false,
            toolbarCustom: toolBarHtml,
            toolbarItem: 'del',
            addName: '新消息',
            addFunction : addMessageDialog,
            delUrl: 'system/systemMessage/deleteMessageByIds',
            delType: 'POST',
            delPK: 'userMessageId',
            paging: {pageSize: 30},
            showCheckboxcol: true,
            linenumberAll: true,
            local: 'local',
            dataUrl: 'system/systemMessage/getSystemMessageGridData',
            columns: columns,
            postData: {
                type: $("#systemMessage_typeId").val()
            },
            afterSave: function ($trs, datas) {
                // 提示成功，关闭dialog
                BJUI.alertmsg('ok', '保存成功！', {});
                $(gridId).datagrid('refresh', true);
            }
        });

        // 将消息标记为已读
        var readMessage = function (ids) {
            BJUI.ajax('doajax', {
                url: 'system/systemMessage/readMessage',
                data: {
                    id: ids
                },
                loadingmask: true,
                okCallback: function (res, options) {
                    $(gridId).datagrid('refresh', true);
                }
            });
        };

        // 标记为已读
        $.CurrentNavtab.on("click", ".systemMessage-readMessage", function () {
            var datas = $(gridId).data('selectedDatas');
            if (datas == null || datas.length == 0) {
                BJUI.alertmsg('info', MX.msg.noSelected,{});
                return;
            }
            var userMessageIds=[];
            for (var i=0; i<datas.length; i++) {
                userMessageIds.push(datas[i].userMessageId);
            }
            readMessage(userMessageIds.join(','));
        });

        // 打开消息页
        var showMessage = function () {
            var id = $(this).attr("data-id")
            var title = $(this).attr("data-title")
            BJUI.navtab({
                id: 'message_' + id,
                url: 'system/systemMessage/showMessage',
                title: title,
                icon: 'newspaper-o',
                data: {
                    messageId: id
                }
            });
            // 已发私信：不标记已读
            var type = $("#systemMessage_typeId").val();
            if (type == 0 || type ==1) {
                readMessage($(this).attr("data-userMessageId"));
            }
        }

        // 查看消息
        $.CurrentNavtab.on("click", ".systemMessage-showMessage", showMessage);

    }();
});