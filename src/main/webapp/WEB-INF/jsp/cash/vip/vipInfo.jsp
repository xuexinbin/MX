<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">
    <form data-toggle="ajaxsearch" data-options="{searchDatagrid:$.CurrentNavtab.find('#vipInfo_grid')}">
        <fieldset>
            <legend style="font-weight:normal;">搜索</legend>
            <div style="margin:0; padding:1px 5px 5px;">
                <span>模糊匹配：</span>
                <input type="text" name="likeStr" class="form-control" size="25" placeholder="会员名／会员编号／手机号">
                <span>&nbsp;会员类型：</span>
                <select name="type" data-toggle="selectpicker" data-width="120">
                    <option value="" selected="">全部</option>
                    <option value="0">普通会员</option>
                    <option value="1">白银会员</option>
                    <option value="2">黄金会员</option>
                    <option value="3">白金会员</option>
                    <option value="4">钻石会员</option>
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
    <table id="vipInfo_grid" class="table table-bordered"></table>
</div>
<script src="js/cash/vip/vipInfo.js"></script>