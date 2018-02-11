<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="bjui-pageContent">
    <form id="addRechargeDialog_form" class="bjui-row col-2" enctype="multipart/form-data">
        <input id="addRechargeDialog_vipId" name="vipId" type="hidden">

        <label for="addRechargeDialog_vipNumber" class="row-label">会员号：</label>
        <div class="row-input required">
            <input id="addRechargeDialog_vipNumber" name="number" type="text" data-rule="required" readonly="readonly"
                   placeholder="请选择充值会员" data-toggle="findgrid"
                   data-options="{
                include: 'number',
                dialogOptions: {title:'充值会员'},
                empty: false,
                afterSelect: function(data){
                    $('#addRechargeDialog_vipId').val(data.id);
                    $('#addRechargeDialog_vipName').val(data.name);
                    $('#addRechargeDialog_vipRemain').val(data.remain);
                },
                gridOptions: {
                    local: 'local',
                    dataUrl: 'cash/vip/getVipFindGrid',
                    columns: [
                        {name:'number', label:'会员号'},
                        {name:'name', label:'会员名'},
                        {name:'type', label:'会员类别'},
                        {name:'remain', label:'余额'},
                        {name:'phone', label:'手机号'}
                    ]
                }
            }">
        </div>
        <br>
        <label for="addRechargeDialog_vipName" class="row-label">会员名：</label>
        <div class="row-input">
            <input id="addRechargeDialog_vipName" type="text" readonly="readonly">
        </div>
        <label for="addRechargeDialog_vipRemain" class="row-label">余额：</label>
        <div class="row-input">
            <input id="addRechargeDialog_vipRemain" type="text" readonly="readonly">
        </div>
        <br>
        <label for="addRechargeDialog_money" class="row-label">金额：</label>
        <div class="row-input required">
            <input id="addRechargeDialog_money" name="money" type="text" data-rule="required;range(0~)" placeholder="请输入充值或退款金额">
        </div>
        <label for="addRechargeDialog_rechargeType" class="row-label">付款方式：</label>
        <div class="row-input">
            <select id="addRechargeDialog_rechargeType" name="rechargeType" data-toggle="selectpicker"
                    data-width="80">
                <option value="0" selected="">现金</option>
                <option value="1">微信</option>
                <option value="2">支付宝</option>
                <option value="3">网银</option>
                <option value="4">刷卡</option>
                <option value="5">其他</option>
            </select>
        </div>
        <label for="addRechargeDialog_giveMoney" class="row-label">赠送金额：</label>
        <div class="row-input">
            <input id="addRechargeDialog_giveMoney" name="giveMoney" type="text" data-rule="range(0~)" value="0" placeholder="请输入赠送金额">
        </div>
        <label for="addRechargeDialog_givePoint" class="row-label">赠送积分：</label>
        <div class="row-input">
            <input id="addRechargeDialog_givePoint" name="givePoint" type="text" data-rule="range(0~)" value="0" placeholder="请输入赠送积分">
        </div>

        <label class="row-label">类型：</label>
        <div class="row-input">
            <input id="addRechargeDialog_type0" name="type" type="radio" checked=""
                   data-toggle="icheck" data-label="充值" value="0">
            <input id="addRechargeDialog_type1" name="type" type="radio" data-toggle="icheck"
                   data-label="退款" value="1">
        </div>
        <label for="addRechargeDialog_rechargeTime" class="row-label">充值日期：</label>
        <div class="row-input">
            <input id="addRechargeDialog_rechargeTime" name="rechargeTime" type="text" value=""
                   data-toggle="datepicker" data-pattern="yyyy-MM-dd HH:mm:ss" placeholder="请选择充值日期">
        </div>
        <label for="addRechargeDialog_operator" class="row-label">经办人：</label>
        <div class="row-input">
            <input id="addRechargeDialog_operator" name="operator" type="text" data-rule="length(~8)"
                   placeholder="请输入经办人">
        </div>
        <br>
        <label for="addRechargeDialog_memo" class="row-label">备注：</label>
        <div class="row-input">
            <textarea id="addRechargeDialog_memo" data-rule="length(~50)" name="memo" cols="50" rows="2"
                      data-toggle="autoheight"
                      placeholder="请输入备注"></textarea>
        </div>
        <br>
    </form>

</div>
<div class="bjui-pageFooter">
    <ul>
        <li>
            <button type="button" class="btn-close" data-icon="close">取消</button>&nbsp;&nbsp;&nbsp;
        </li>
        <li>
            <button id="addRechargeDialog_saveBtn" type="button" class="btn-default" data-icon="save">保存</button>&nbsp;&nbsp;&nbsp;
        </li>
    </ul>
</div>