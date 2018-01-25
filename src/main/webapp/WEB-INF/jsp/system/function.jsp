<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row height100">
    <div class="col-md-2 height100" style="width: 20%;">
        <div class="panel-block">
            <div class="title">
                <span>权限类别</span>
            </div>
            <ul id="function_type">
                <li class="active" data-type="0"><a>菜单权限</a></li>
                <li data-type="1"><a>功能权限</a></li>
            </ul>
        </div>
    </div>
    <div id="function_gridDiv" class="col-md-10 height100 flex-column" style="width: 80%; height: 100%;">
        <input id="function_typeId" type="hidden">
        <table id="function_grid" class="table table-bordered"></table>
    </div>
</div>

<script type="text/javascript">
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
                    rule: 'required;length(1~10)'
                },
                {
                    name: 'parentId',
                    label: '父菜单',
                    width: 150,
                    align: 'center',
                    type: 'select',
                    items: [],
                    itemsMapper: "parentList"
                }, {
                    name: 'sort',
                    label: '排序',
                    width: 150,
                    align: 'center',
                    rule: 'length(~2)'
                }, {
                    name: 'url',
                    label: 'url',
                    width: 150,
                    align: 'center',
                    rule: 'length(~20)'
                }, {
                    name: 'icon',
                    label: '图标',
                    width: 150,
                    align: 'center',
                    rule: 'length(~20)'
                },
                {
                    label: '操作',
                    align: 'center',
                    width: 180,
                    render: function (value, data) {
                        var html = '<button type="button" class="btn-green" data-toggle="edit.datagrid.tr">编辑</button>&nbsp;&nbsp;'
                            + '<button type="button" class="btn-red" data-toggle="del.datagrid.tr">删除</button>'
                        return html;
                    }
                },
            ];

            var functionColumns = [
                {
                    name: 'name',
                    label: '功能名称',
                    width: 150,
                    align: 'center',
                    rule: 'required;length(1~10)'
                }, {
                    name: 'sort',
                    label: '排序',
                    width: 150,
                    align: 'center',
                    rule: 'length(~2)'
                },
                {
                    label: '操作',
                    align: 'center',
                    width: 180,
                    render: function (value, data) {
                        var html = '<button type="button" class="btn-green" data-toggle="edit.datagrid.tr">编辑</button>&nbsp;&nbsp;'
                            + '<button type="button" class="btn-red" data-toggle="del.datagrid.tr">删除</button>'
                        return html;
                    }
                },
            ];
            var reloadGrid = function (type) {
                var columns = [];
                var title = "";
                if (type == 0) {
                    columns = menuColumns;
                    title = "菜单列表";
                } else {
                    columns = functionColumns;
                    title = "功能列表";
                }
                $(gridId).datagrid('reload', {
                    columns: columns,
                    gridTitle: title,
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
                afterLoad: function (){
                    // 点击添加：type自动赋值
                    $("#function_gridDiv button[data-icon=plus]").click(function () {
                        $("#function_grid").data('allData')[0].type = $("#function_typeId").val();
                        $("#function_grid").data('allData')[0].parentId = 0;
                    });
                }

            });
        }();
    });
</script>