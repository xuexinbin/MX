<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="bjui-pageContent">
    <form id="editUserDialog_form" class="bjui-row col-2" enctype="multipart/form-data">
        <input id="editUserDialog_id" name="id" type="hidden">
        <div id="editUserDialog_pic">
            <label class="row-label">头像图片：</label>
            <div class="row-input" style="width: 100px;">
                <div id="editUserDialog_avatar" class="file-item thumbnail" style="margin-bottom: 0px;width: 80px;height: 80px;">

                    <i class="fa fa-picture-o"
                       style="padding: 5px 3px;font-size: 60px;color: rgba(217, 231, 242, 0.58);"></i>
                    <img>
                </div>
            </div>
            <div class="row-input">
                <div id="editUserDialog_avatarPicker"><i class="fa fa-file-image-o"></i>&nbsp;选择图片</div>
            </div>
            <br>
        </div>

        <label for="editUserDialog_trueName" class="row-label">真实姓名：</label>
        <div class="row-input required">
            <input id="editUserDialog_trueName" name="trueName" type="text" data-rule="required;length(2~6)"
                   placeholder="请输入真实姓名">
        </div>

        <label for="editUserDialog_number" class="row-label">工号：</label>
        <div class="row-input">
            <input id="editUserDialog_number" name="number" type="text" placeholder="请输入工号">
        </div>

        <label for="editUserDialog_sex" class="row-label">性别：</label>
        <div class="row-input">
            <select id="editUserDialog_sex" name="sex" data-toggle="selectpicker" data-width="100">
                <option value="1" selected="">男</option>
                <option value="0">女</option>
            </select>
        </div>

        <label for="editUserDialog_birthday" class="row-label">出生日期：</label>
        <div class="row-input">
            <input id="editUserDialog_birthday" name="birthday" type="text" value=""
                   data-toggle="datepicker" data-pattern="yyyy-MM-dd" placeholder="请输入出生日期">
        </div>

        <label for="editUserDialog_phone" class="row-label">手机号码：</label>
        <div class="row-input">
            <input id="editUserDialog_phone" name="phone" type="text" data-rule="mobile"
                   placeholder="请输入手机号码">
        </div>
        <label for="editUserDialog_mail" class="row-label">邮箱地址：</label>
        <div class="row-input">
            <input id="editUserDialog_mail" name="mail" type="text" data-rule="email"
                   placeholder="请输入邮箱地址">
        </div>

        <label for="editUserDialog_joinTime" class="row-label">入职时间：</label>
        <div class="row-input">
            <input id="editUserDialog_joinTime" name="joinTime" type="text" value=""
                   data-toggle="datepicker" data-pattern="yyyy-MM-dd" placeholder="请输入入职日期">
        </div>
        <br>
        <label for="editUserDialog_departmentId" class="row-label">所属部门：</label>
        <div class="row-input">
            <select id="editUserDialog_departmentId" name="departmentId" data-toggle="selectpicker"
                    data-width="160">
            </select>
        </div>

        <label class="row-label">所属角色：</label>
        <div class="row-input">
            <select id="editUserDialog_roleIds" name="roleIds" data-toggle="selectpicker" multiple
                    data-width="160">
            </select>
        </div>
        <label for="editUserDialog_userName" class="row-label">登录用户名：</label>
        <div class="row-input required">
            <input id="editUserDialog_userName" name="userName" type="text"
                   data-rule="required;length(1~10)" placeholder="请输入登录用户名">
        </div>

        <label for="editUserDialog_password" class="row-label">登录密码：</label>
        <div class="row-input required">
            <input id="editUserDialog_password" name="password" type="password" data-rule="required"
                   placeholder="请输入登录密码">
        </div>
        <br>

        <label class="row-label">是否启用：</label>
        <div class="row-input">
            <input id="editUserDialog_enablef0" name="enablef" type="radio" checked=""
                   data-toggle="icheck" data-label="启用" value="0">
            <input id="editUserDialog_enablef1" name="enablef" type="radio" data-toggle="icheck"
                   data-label="禁用" value="1">
        </div>
        <br>

        <label for="editUserDialog_memo" class="row-label">备注：</label>
        <div class="row-input">
            <textarea id="editUserDialog_memo" data-rule="length(~50)" name="memo" cols="50" rows="2" class="input-lg"
                      style="height: 50px;"
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
            <button id="editUserDialog_saveBtn" type="button" class="btn-default" data-icon="save">保存</button>&nbsp;&nbsp;&nbsp;
        </li>
    </ul>
</div>