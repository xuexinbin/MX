<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row height100">
    <div class="col-md-2 height100" style="width: 20%;">
        <div class="panel-block">
            <div class="title">
                <span>消息类别</span>
            </div>
            <ul id="systemMessage_type">
                <li class="active"><a data-type="">全部</a></li>
                <li><a data-type="0"><i class="fa fa-bell-o"></i>系统消息</a></li>
                <li><a data-type="1"><i class="fa fa-envelope"></i>我的私信</a></li>
                <li><a data-type="2"><i class="fa fa-paper-plane"></i>已发私信</a></li>
            </ul>
        </div>
    </div>
    <div id="function_gridDiv" class="col-md-10 height100 flex-column" style="width: 80%; height: 100%;">
        <input id="systemMessage_typeId" type="hidden">
        <table id="systemMessage_grid" class="table table-bordered"></table>
    </div>
</div>

<script src="js/system/systemMessage.js"></script>