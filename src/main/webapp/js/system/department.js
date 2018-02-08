//@ sourceURL=department.js

$(function () {
    void function () {
        var columns = [
            {
                name: 'name',
                label: '部门名称',
                width: 150,
                align: 'center',
                rule: 'required;length(1~10)'
            },
            {
                name: 'leaderUserId',
                label: '负责人',
                width: 120,
                align: 'center',
                type: 'select',
                items: function () {
                    return $.getJSON('system/department/getUserList');
                },
                itemattr: {value: 'value', label: 'text'},
                render: function (value, data) {
                    return data.leaderUserTrueName;
                }
            },
            {
                name: 'parentId',
                label: '所属部门',
                width: 120,
                align: 'center',
                type: 'select',
                items: function () {
                    return $.getJSON('system/department/getDepartmentList');
                },
                itemattr: {value: 'value', label: 'text'},
                render: function (value, data) {
                    if (value == 0) {
                        return "无";
                    }
                    return data.parentName;
                }
            },
            {
                name: 'memo',
                label: '备注',
                width: 150,
                align: 'center',
                rule: 'length(0~30)'
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
                width: 100,
                render: function (value, data) {
                    var html = '<i class="fa fa-edit grid-icon grid-edit" data-toggle="edit.datagrid.tr" title="编辑"></i>&nbsp;&nbsp;&nbsp;'
                        + '<i class="fa fa-times grid-icon grid-del" data-toggle="del.datagrid.tr" title="删除"></i>';
                    return html;
                }
            },
        ];

        $('#department_grid').datagrid({
            fullGrid: true,
            height: '100%',
            gridTitle: '部门列表',
            editMode: 'inline',
            showToolbar: true,
            selectMult: true,
            filterThead: false,
            columnMenu: false,
            toolbarItem: "add, cancel, save",
            delUrl: 'system/department/deleteDepartmentByIds',
            delType: 'POST',
            delPK: 'id',
            editUrl: 'system/department/editDepartment',
            paging: {pageSize: 30},
            showCheckboxcol: false,
            linenumberAll: true,
            local: 'local',
            dataUrl: 'system/department/getDepartmentGridData',
            columns: columns,
            afterSave: function ($trs, datas) {
                // 提示成功，关闭dialog
                BJUI.alertmsg('ok', '保存成功！', {});
                $("#department_grid").datagrid('refresh', true);
            },
            isTree: 'name',
            treeOptions: {
                expandAll: true,
                add: false,
                keys: {
                    key: 'id',
                    parentKey: 'parentId'
                }
            }
        });
    }();
});