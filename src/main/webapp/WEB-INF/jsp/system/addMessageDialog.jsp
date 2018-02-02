<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
    #addMessageDialog_form .width15 {
        width: 15%;
    }
    #addMessageDialog_form .width85 {
        width: 80%;
    }
</style>

<div class="bjui-pageContent">
    <form id="addMessageDialog_form" class="bjui-row col-1">
        <label for="addMessageDialog_title" class="row-label width15">标题：</label>
        <div class="row-input required width85">
            <input id="addMessageDialog_title" name="title" type="text" data-rule="required;length(~30)" placeholder="请输入标题">
        </div>

        <label class="row-label width15">内容：</label>
        <div class="row-input required width85">
            <textarea id="addMessageDialog_content" name="content" data-rule="required;"></textarea>
        </div>

        <label class="row-label width15">接收人：</label>
        <div class="row-input required width85">
            <input id="addMessageDialog_userIds"type="text" name="userIds" style="display: none">
            <input name="userNames" data-rule="required;" type="text" placeholder="选择消息接收人" data-toggle="findgrid" readonly ="" data-options="{
                include: 'userNames:trueName,userIds:id',
                pk: 'id',
                dialogOptions: {title:'查询接收人'},
                multiple: true,
                append: true,
                gridOptions: {
                    local: 'local',
                    dataUrl: '/system/systemMessage/getMessageUser',
                    columns: [
                        {name:'trueName', label:'姓名'}
                    ]
                }
            }">
        </div>

        <label class="row-label width15">类型：</label>
        <div class="row-input width85">
            <input id="addMessageDialog_type0" name="type" type="radio" checked=""
                   data-toggle="icheck" data-label="私信" value="1">
            <input id="addMessageDialog_type1" name="type" type="radio" data-toggle="icheck"
                   data-label="系统消息" value="0">
        </div>
        <label for="addMessageDialog_top" class="row-label width15">置顶：</label>
        <div class="row-input width85">
            <input id="addMessageDialog_top" name="top" type="checkbox" data-toggle="icheck" data-label="是" value="1">
        </div>
        <label class="row-label width15">重要程度：</label>
        <div class="row-input width85">
            <input id="addMessageDialog_important0" name="important" type="radio" checked=""
                   data-toggle="icheck" data-label="普通" value="0">
            <input id="addMessageDialog_important1" name="important" type="radio"
                   data-toggle="icheck" data-label="重要" value="1">
        </div>
        <label class="row-label width15">紧急程度：</label>
        <div class="row-input width85">
            <input id="addMessageDialog_level1" name="level" type="radio" checked=""
                   data-toggle="icheck" data-label="普通" value="0">
            <input id="addMessageDialog_level0" name="level" type="radio"
                   data-toggle="icheck" data-label="紧急" value="1">
        </div>
    </form>

</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">取消</button>&nbsp;&nbsp;&nbsp;</li>
        <li><button id="addMessageDialog_saveBtn" type="button" class="btn-default" data-icon="save">保存</button>&nbsp;&nbsp;&nbsp;</li>
    </ul>
</div>