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
<script src="js/system/function.js"></script>