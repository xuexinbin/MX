//@ sourceURL=function.js

$(function () {
    void function () {
        // 默认菜单
        $("#function_typeId").val(0);
        var gridId = "#function_grid";
        var menuColumns = [
            {
                name: 'name',
                label: '菜单名称',
                width: 150,
                align: 'center',
                rule: 'required;length(~15)'
            },
            {
                name: 'parentId',
                label: '父菜单',
                width: 120,
                type: 'select',
                items: [],
                itemsMapper: "parentList"
            }, {
                name: 'code',
                label: '编码',
                width: 120,
                rule: 'required;length(~15)'
            }, {
                name: 'sort',
                label: '排序',
                width: 60,
                align: 'center',
                rule: 'length(~2)'
            }, {
                name: 'url',
                label: '跳转路径',
                width: 200,
                rule: 'length(~40)'
            }, {
                name: 'icon',
                label: '图标',
                width: 50,
                align: 'center',
                rule: 'length(~20)',
                render: function (value, data) {
                    return '<i class="fa fa-' + value + '"></i>';
                }
            },
            {
                label: '操作',
                align: 'center',
                width: 100,
                render: function (value, data) {
                    var html = '<i class="fa fa-edit grid-icon grid-edit" data-toggle="edit.datagrid.tr" title="编辑"></i>&nbsp;&nbsp;&nbsp;'
                        + '<i class="fa fa-times grid-icon grid-del" data-toggle="del.datagrid.tr" title="删除"></i>';
                    return html;
                }
            },
        ];

        var functionColumns = [
            {
                name: 'name',
                label: '功能名称',
                width: 180,
                rule: 'required;length(1~10)'
            }, {
                name: 'parentId',
                label: '所属菜单',
                width: 120,
                type: 'select',
                items: [],
                itemsMapper: "parentList"
            }, {
                name: 'code',
                label: '编码',
                width: 120,
                rule: 'required;length(~15)'
            }, {
                name: 'sort',
                label: '排序',
                width: 80,
                align: 'center',
                rule: 'length(~2)'
            },
            {
                label: '操作',
                align: 'center',
                width: 100,
                render: function (value, data) {
                    var html = '<i class="fa fa-edit grid-icon grid-edit" data-toggle="edit.datagrid.tr" title="编辑"></i>&nbsp;&nbsp;&nbsp;'
                        + '<i class="fa fa-times grid-icon grid-del" data-toggle="del.datagrid.tr" title="删除"></i>';
                    return html;
                }
            },
        ];
        var reloadGrid = function (type) {
            var columns = [];
            var title = "";
            var isTree = '';
            if (type == 0) {
                columns = menuColumns;
                title = "菜单列表";
                isTree = 'name';
            } else {
                columns = functionColumns;
                title = "功能列表";
                isTree = false;
            }
            $(gridId).datagrid('reload', {
                columns: columns,
                gridTitle: title,
                isTree: isTree,
                postData: {
                    type: type
                }
            });
        };

        // 权限类型点击事件
        $("#function_type li").click(function (e) {
            $("#function_type li").removeClass("active");
            $(this).addClass("active");
            reloadGrid(e.currentTarget.getAttribute("data-type"));
            $("#function_typeId").val(e.currentTarget.getAttribute("data-type"));
        });

        $('#function_grid').datagrid({
            fullGrid: true,
            height: '100%',
            gridTitle: '菜单列表',
            editMode: 'inline',
            showToolbar: true,
            selectMult: true,
            filterThead: false,
            columnMenu: false,
            toolbarItem: "add, cancel, save",
            delUrl: 'system/function/deleteFunctionByIds',
            delType: 'POST',
            delPK: 'id',
            editUrl: 'system/function/editFunction',
            paging: {pageSize: 30},
            showCheckboxcol: false,
            linenumberAll: true,
            local: 'local',
            dataUrl: 'system/function/getFunctionGridData',
            resultType: 'many',
            columns: menuColumns,
            postData: {
                type: 0
            },
            afterSave: function ($trs, datas) {
                // 提示成功，关闭dialog
                BJUI.alertmsg('ok', '保存成功！', {});
                $(gridId).datagrid('refresh', true);
            },
            isTree: 'name',
            treeOptions: {
                expandAll: true,
                add: false,
                keys: {
                    key: 'id',
                    parentKey: 'parentId'
                }
            },
            afterLoad: function () {
                // 点击添加：type自动赋值
                $("#function_gridDiv button[data-icon=plus]").click(function () {
                    $("#function_grid").data('allData')[0].type = $("#function_typeId").val();
                    $("#function_grid").data('allData')[0].parentId = 0;
                });
            }

        });
    }();
});