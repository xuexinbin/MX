<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">
    <form data-toggle="ajaxsearch" data-options="{searchDatagrid:$.CurrentNavtab.find('#user_grid')}">
        <fieldset>
            <legend style="font-weight:normal;">搜索</legend>
            <div style="margin:0; padding:1px 5px 5px;">
                <span>模糊匹配：</span>
                <input type="text" name="likeStr" class="form-control" size="25" placeholder="工号／姓名／登录名／手机号">

                <span>&nbsp;所属部门：</span>
                <select id="user_departmentId" name="departmentId" data-toggle="selectpicker"
                        data-width="160">
                </select>

                <span>&nbsp;状态：</span>
                <select name="enablef" data-toggle="selectpicker" data-width="80">
                    <option value="" selected="">全部</option>
                    <option value="0">启用</option>
                    <option value="1">禁用</option>
                </select>
                <div class="btn-group" style="float: right">
                    <button type="submit" class="btn-green" data-icon="search">查询</button>
                    <button type="button" class="btn-orange" data-toggle="reset" data-icon="undo">重置</button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
<div class="bjui-pageContent">
    <table id="user_grid" class="table table-bordered"></table>
</div>
<script src="js/system/user.js"></script>