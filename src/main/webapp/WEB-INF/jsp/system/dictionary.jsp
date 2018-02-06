<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row height100">
    <div class="col-md-2 height100" style="width: 20%;">
        <div class="panel-block">
            <div class="title">
                <span>字典类别</span>
            </div>
            <ul id="dictionary_type">
            </ul>
        </div>
    </div>
    <div id="function_gridDiv" class="col-md-10 height100 flex-column" style="width: 80%; height: 100%;">
        <input id="dictionary_typeId" type="hidden">
        <table id="dictionary_grid" class="table table-bordered"></table>
    </div>
</div>

<script type="text/javascript">
    //@ sourceURL=dictionary.js

    $(function () {
        void function () {
            var gridId = "#dictionary_grid";

            // 获得左侧列表
            var getDictionaryTypeListFn = function () {
                BJUI.ajax('doajax', {
                    url: 'system/dictionary/getDictionaryType',
                    data: {},
                    loadingmask: false,
                    okCallback: function (res, options) {
                        dictionaryList = res.dictionaryList;
                        // 拼接左侧字典类列表html
                        var htmlStr = '<li class="active"><a data-type="">全部</a></li>';
                        for (var i = 0; i < dictionaryList.length; i++) {
                            htmlStr += '<li><a data-type="' + dictionaryList[i].type + '">&nbsp;&nbsp;' + dictionaryList[i].type + '</a></li>';
                        }
                        $("#dictionary_type").html(htmlStr);

                        // 字典类点击事件
                        $("#dictionary_type li a").click(function (param) {
                            $("#dictionary_type li").removeClass("active");
                            $(this).parent().addClass("active");
                            $("#dictionary_typeId").val(param.currentTarget.getAttribute("data-type"));
                            $(gridId).datagrid("refresh",{type:$("#dictionary_typeId").val()});
                        });
                    }
                });
            };

            var columns = [
                {
                    name: 'type',
                    label: '字典类别',
                    width: 150,
                    align: 'center',
                    rule: 'required;length(1~10)'
                }, {
                    name: 'name',
                    label: '字典项名称',
                    width: 150,
                    align: 'center',
                    rule: 'required;length(1~10)'
                }, {
                    name: 'value',
                    label: '字典项值',
                    width: 150,
                    align: 'center',
                    rule: 'required;length(1~10)'
                }, {
                    name: 'sort',
                    label: '排序',
                    width: 150,
                    align: 'center',
                    rule: 'required;length(~2)'
                }, {
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
                }, {
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

            $(gridId).datagrid({
                fullGrid: true,
                height: '100%',
                gridTitle: '字典列表',
                editMode: 'inline',
                showToolbar: true,
                selectMult: true,
                filterThead: false,
                columnMenu: false,
                toolbarItem: "add, del, cancel, save",
                delUrl: 'system/dictionary/deleteDictionaryByIds',
                delType: 'POST',
                delPK: 'id',
                editUrl: 'system/dictionary/editDictionary',
                paging: {pageSize: 30},
                showCheckboxcol: true,
                linenumberAll: true,
                local: 'local',
                dataUrl: 'system/dictionary/getDictionaryGridData',
                columns: columns,
                postData: {
                    type: $("#dictionary_typeId").val()
                },
                afterSave: function ($trs, datas) {
                    // 提示成功，关闭dialog
                    BJUI.alertmsg('ok', '保存成功！', {});
                    $(gridId).datagrid('refresh', true);
                }
            });

            // ----- 页码初始化 start ------
            // 加载字典类列表
            getDictionaryTypeListFn();
            // ----- 页码初始化 end ------
        }();
    });
</script>