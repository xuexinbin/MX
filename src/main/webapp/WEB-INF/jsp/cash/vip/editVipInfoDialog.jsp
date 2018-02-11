<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="bjui-pageContent">
    <form id="editVipInfoDialog_form" class="bjui-row col-2" enctype="multipart/form-data">
        <input id="editVipInfoDialog_id" name="id" type="hidden">

        <fieldset>
            <legend>账号信息</legend>
            <label for="editVipInfoDialog_name" class="row-label">会员名：</label>
            <div class="row-input required">
                <input id="editVipInfoDialog_name" name="name" type="text" data-rule="required;length(~8)"
                       placeholder="请输入会员名">
            </div>
            <br>
            <label for="editVipInfoDialog_number" class="row-label">会员编号：</label>
            <div class="row-input">
                <input id="editVipInfoDialog_number" name="number" type="text" placeholder="请会员编号">
            </div>
            <label for="editVipInfoDialog_type" class="row-label">会员类别：</label>
            <div class="row-input">
                <select id="editVipInfoDialog_type" name="type" data-toggle="selectpicker"
                        data-width="160">
                    <option value="0" selected="">普通会员</option>
                    <option value="1">白银会员</option>
                    <option value="2">黄金会员</option>
                    <option value="3">白金会员</option>
                    <option value="4">钻石会员</option>
                </select>
            </div>
            <label for="editVipInfoDialog_remain" class="row-label">会员余额：</label>
            <div class="row-input">
                <input id="editVipInfoDialog_remain" name="remain" type="text" value="0" placeholder="请输入会员余额">
            </div>
            <label for="editVipInfoDialog_point" class="row-label">会员积分：</label>
            <div class="row-input">
                <input id="editVipInfoDialog_point" name="point" type="text" value="0" placeholder="请输入会员积分">
            </div>
            <label for="editVipInfoDialog_openTime" class="row-label">开户日期：</label>
            <div class="row-input">
                <input id="editVipInfoDialog_openTime" name="openTime" type="text" value=""
                       data-toggle="datepicker" data-pattern="yyyy-MM-dd" placeholder="请选择会员开户日期">
            </div>
            <label class="row-label">状态：</label>
            <div class="row-input">
                <input id="editVipInfoDialog_enablef0" name="enablef" type="radio" checked=""
                       data-toggle="icheck" data-label="启用" value="0">
                <input id="editVipInfoDialog_enablef1" name="enablef" type="radio" data-toggle="icheck"
                       data-label="禁用" value="1">
            </div>
        </fieldset>

        <fieldset>
            <legend>会员基础信息</legend>
            <label for="editVipInfoDialog_sex" class="row-label">性别：</label>
            <div class="row-input">
                <select id="editVipInfoDialog_sex" name="sex" data-toggle="selectpicker" data-width="50">
                    <option value="1" selected="">男</option>
                    <option value="0">女</option>
                </select>
            </div>
            <label for="editVipInfoDialog_birthday" class="row-label">生日：</label>
            <div class="row-input">
                <input id="editVipInfoDialog_birthday" name="birthday" type="text" value=""
                       data-toggle="datepicker" data-pattern="yyyy-MM-dd" placeholder="请选择出生日期">
            </div>

            <label for="editVipInfoDialog_phone" class="row-label">手机号码：</label>
            <div class="row-input">
                <input id="editVipInfoDialog_phone" name="phone" type="text" data-rule="mobile"
                       placeholder="请输入手机号码">
            </div>
            <label for="editVipInfoDialog_mail" class="row-label">邮箱：</label>
            <div class="row-input">
                <input id="editVipInfoDialog_mail" name="mail" type="text" data-rule="email"
                       placeholder="请输入邮箱地址">
            </div>

            <label for="editVipInfoDialog_weixin" class="row-label">微信号码：</label>
            <div class="row-input">
                <input id="editVipInfoDialog_weixin" name="weixin" type="text"
                       placeholder="请输入微信号码">
            </div>
            <label for="editVipInfoDialog_qq" class="row-label">QQ号码：</label>
            <div class="row-input">
                <input id="editVipInfoDialog_qq" name="qq" type="text"
                       placeholder="请输入QQ号码">
            </div>

            <label for="editVipInfoDialog_memo" class="row-label">备注：</label>
            <div class="row-input">
            <textarea id="editVipInfoDialog_memo" data-rule="length(~50)" name="memo" cols="50" rows="2"
                      data-toggle="autoheight"
                      placeholder="请输入备注"></textarea>
            </div>
            <br>
        </fieldset>
    </form>

</div>
<div class="bjui-pageFooter">
    <ul>
        <li>
            <button type="button" class="btn-close" data-icon="close">取消</button>&nbsp;&nbsp;&nbsp;
        </li>
        <li>
            <button id="editVipInfoDialog_saveBtn" type="button" class="btn-default" data-icon="save">保存</button>&nbsp;&nbsp;&nbsp;
        </li>
    </ul>
</div>