<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">
    <fieldset>
        <legend style="font-weight:normal;">搜索</legend>
        <div style="margin:0; padding:1px 5px 5px;">
            <span>&nbsp;日期：</span>
            <input id="operateLog_date" name="operateLog_date" type="text" value=""
                   data-toggle="datepicker" data-pattern="yyyy-MM-dd" placeholder="请选择日志日期">

            <div class="btn-group" style="float: right">
                <button id="operateLog_searchBtn" type="button" class="btn-green" data-icon="search">查询</button>
            </div>
        </div>
    </fieldset>
</div>
<div class="bjui-pageContent">
    <div class="highlight">
        <pre id="operateLog_log" class="prettyprint"></pre>
    </div>
</div>
<script src="js/system/operateLog.js"></script>