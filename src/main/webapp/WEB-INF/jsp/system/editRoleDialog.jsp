<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="bjui-pageContent">
    <form class="bjui-row col-1">
	    <label class="row-label">角色名称：</label>
	    <div class="row-input required">
	        <input id="editRoleDialog_name" type="text" class="input-sm" data-rule="required;length(1~8)" placeholder="请输入角色名称">
	    </div>
	    <br>
    </form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">取消</button>&nbsp;&nbsp;&nbsp;</li>
        <li><button id="editRoleDialog_saveBtn" type="button" class="btn-default" data-icon="save">保存</button>&nbsp;&nbsp;&nbsp;</li>
    </ul>
</div>