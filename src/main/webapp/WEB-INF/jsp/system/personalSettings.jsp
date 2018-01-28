<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageContent">
    <ul class="nav nav-tabs" role="tablist" style="margin-top: 5px;height: 30px;">
        <li role="presentation" class="active"><a href="#personalSettings_basicInfo" data-toggle="tab">基础信息</a></li>
        <li role="presentation"><a href="#personalSettings_passwordReset" data-toggle="tab">密码重置</a></li>
    </ul>
    <button id="personalSettings_saveBtn" type="button"
            class="btn-default" data-icon="save" style="position: absolute;right: 50px;top:5px;">保存
    </button>
    <div class="tab-content" style="position: absolute;top: 30px;bottom: 10px;">
        <div id="personalSettings_basicInfo" class="tab-pane active">
            <form id="personalSettings_basicInfoForm" class="bjui-row col-1" style="width: 500px;">

                <label class="row-label">头像照片：</label>
                <div class="row-input">
                    <div id="personalSettings_avatar" class="file-item thumbnail"
                         style="margin-bottom: 0px;width: 80px;height: 80px;">
                        <c:if test='${user.avatar == null}'>
                            <i class="fa fa-picture-o"
                               style="padding: 5px 3px;font-size: 60px;color: rgba(217, 231, 242, 0.58);"></i>
                        </c:if>
                        <c:if test='${user.avatar != null}'>
                            <img src="${user.avatar}"/>
                        </c:if>
                    </div>
                    <div id="personalSettings_avatarPicker"><i class="fa fa-file-image-o"></i>&nbsp;选择图片</div>
                </div>
                <br>

                <label for="personalSettings_trueName" class="row-label">真实姓名：</label>
                <div class="row-input required">
                    <input id="personalSettings_trueName" name="trueName" value="${user.trueName}"
                           type="text" data-rule="required;length(2~6)" placeholder="请输入真实姓名">
                </div>

                <label for="personalSettings_sex" class="row-label">性别：</label>
                <div class="row-input">
                    <select id="personalSettings_sex" name="sex" data-toggle="selectpicker"
                            data-width="100">
                        <option value="1" <c:if test='${user.sex == 1}'>selected=""</c:if>>男</option>
                        <option value="0" <c:if test='${user.sex == 0}'>selected=""</c:if>>女</option>
                    </select>
                </div>

                <label for="personalSettings_birthday" class="row-label">出生日期：</label>
                <div class="row-input">
                    <input id="personalSettings_birthday" name="birthday" type="text" value='<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/>'
                           data-toggle="datepicker" data-pattern="yyyy-MM-dd" placeholder="请输入出生日期">
                </div>

                <label for="personalSettings_mail" class="row-label">邮箱地址：</label>
                <div class="row-input">
                    <input id="personalSettings_mail" name="mail" value="${user.mail}" type="text"
                           data-rule="email" placeholder="请输入邮箱地址">
                </div>

                <label for="personalSettings_phone" class="row-label">手机号码：</label>
                <div class="row-input required">
                    <input id="personalSettings_phone" name="phone" value="${user.phone}" type="text"
                           data-rule="required;mobile" placeholder="请输入手机号码">
                </div>
            </form>
        </div>

        <div id="personalSettings_passwordReset" class="tab-pane">
            <form id="personalSettings_passwordResetForm" class="bjui-row col-1" style="width: 400px;">
                <label for="personalSettings_oldPassword" class="row-label">原密码：</label>
                <div class="row-input required">
                    <input id="personalSettings_oldPassword" name="personalSettings_oldPassword" type="password"
                           data-rule="required;" placeholder="请输入原密码">
                </div>
                <label for="personalSettings_newPassword" class="row-label">新密码：</label>
                <div class="row-input required">
                    <input id="personalSettings_newPassword" name="personalSettings_newPassword" type="password"
                           data-rule="新密码: required;" placeholder="请输入新密码">
                </div>
                <label for="personalSettings_newPassword2" class="row-label">新密码确认：</label>
                <div class="row-input required">
                    <input id="personalSettings_newPassword2" name="personalSettings_newPassword2" type="password"
                           data-rule="required;match(personalSettings_newPassword);" placeholder="请再次输入新密码">
                </div>
            </form>

        </div>
    </div>
</div>
<script type="text/javascript">
    //@ sourceURL=personalSettings.js
    $(function () {
        void function () {
            $("#personalSettings_basicInfoForm").formChangedCheck();
            // 图片上传插件
            var uploader = $("#personalSettings_avatarPicker").initUpload({
                server: 'system/personalSettings/editUserInfo',
                thumbPick: "#personalSettings_avatar",
                uploadBeforeSend: function (block, data) {
                    data.id = 0;
                    data.sex = $("#personalSettings_sex").val();
                    data.birthday = $("#personalSettings_birthday").val();
                    data.phone = $("#personalSettings_phone").val();
                    data.mail = $("#personalSettings_mail").val();
                    data.trueName = $("#personalSettings_trueName").val();
                },
                uploadSuccess: function (file, response) {
                    BJUI.alertmsg('ok', MX.msg.saveSuccess, {});
                    $("#personalSettings_basicInfoForm").setChanged(false);
                }
            })

            // 密码md5加密
            $("#personalSettings_passwordResetForm input[type=password]").on("change", function () {
                this.value = hex_md5(this.value);
            });

            var saveBaseInfo = function () {
                // 存在需要上传的文件：使用webupload上传 否则ajaxform
                if (uploader.getFiles("inited").length > 0) {
                    uploader.upload();
                } else {
                    BJUI.ajax('ajaxform', {
                        url: 'system/personalSettings/editUserInfo',
                        form: $("#personalSettings_basicInfoForm"),
                        validate: true,
                        loadingmask: true,
                        okCallback: function (json, options) {
                            BJUI.alertmsg('ok', '保存成功！', {});
                        }
                    })
                }
            }
            var savePassword = function () {
                // 密码重置
                BJUI.ajax('doajax', {
                    url: 'system/personalSettings/resetPassword',
                    data: {
                        newPassword: $("#personalSettings_newPassword").val(),
                        oldPassword: $("#personalSettings_oldPassword").val()
                    },
                    loadingmask: true,
                    okCallback: function (res, options) {
                        if (!res.success) {
                            // 错误
                            BJUI.alertmsg('error', res.msg, {});
                            return;
                        }
                        // 提示成功
                        BJUI.alertmsg('ok', MX.msg.saveSuccess, {});
                        $("#personalSettings_basicInfoForm").setChanged(false);
                    }
                });
            }
            // 保存事件
            $("#personalSettings_saveBtn").click(function () {
                // 判断激活的tab
                if ($('#personalSettings_basicInfo').hasClass("active")) {
                    // 校验失败 直接return
                    if (!$('#personalSettings_basicInfoForm').isValid()) {
                        return;
                    }
                    // 数据未修改 return
                    if (!$("#personalSettings_basicInfoForm").formHasChanged()) {
                        BJUI.alertmsg('info', MX.msg.dataNoChange, {});
                        return;
                    }
                    saveBaseInfo();
                } else {
                    // 校验失败 直接return
                    if (!$('#personalSettings_passwordResetForm').isValid()) {
                        return;
                    }
                    savePassword();
                }
            });
        }();
    });
</script>