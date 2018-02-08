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