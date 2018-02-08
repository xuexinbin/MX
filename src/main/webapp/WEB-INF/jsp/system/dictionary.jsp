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

<script src="js/system/dictionary.js"></script>