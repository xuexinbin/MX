jQuery.fn.extend({
    // ----- 表单相关 start -----
    // form添加change校验
    formChangedCheck: formChangedCheck,
    // form是否修改
    formHasChanged: formHasChanged,
    // form设为只读
    setFormReadOnly: setFormReadOnly,
    // 设定form changed状态
    setChanged: setChanged,
    // 获得select下拉列表
    loadSelect: loadSelect,
    // 初始化select下拉列表
    initSelect: initSelect,
    // 初始化图片上传控件
    initUpload: initUpload,
    // 插入loading动画
    insertLoading: insertLoading,
    // ----- 表单相关 end -----

});

/**
 * form添加change校验
 */
function formChangedCheck() {
    var formId = this.selector;
    $(formId).on("change", function () {
        $(formId).data("changed", true);
    });
    $(formId + ' input[data-toggle=icheck]').on('ifChecked', function (event) {
        $(formId).data("changed", true);
    });
}

/**
 * form是否修改
 * @returns {boolean}
 */
function formHasChanged() {
    return $(this.selector).data("changed") ? true : false;
}

/**
 * form changed状态
 * @param bool 修改状态
 */
function setChanged(bool) {
    $(this.selector).data("changed", bool)
}
/**
 * form设为只读
 */
function setFormReadOnly() {
    var formId = this.selector;
    $(formId + ' input').attr("disabled", "");
    $(formId + ' textarea').attr("readonly", "");
    $(formId + ' select').attr("disabled", "");
    // 隐藏日期按钮
    $(formId + ' a[data-toggle="datepickerbtn"]').hide();
    // 隐藏findGrid按钮
    $(formId + ' a[data-toggle="findgridbtn"]').hide();
}
/**
 * 获得select下拉列表
 * @param url 请求参数
 */
function loadSelect(url) {
    var id = this.selector;
    BJUI.ajax('doajax', {
        url: url,
        type: "GET",
        loadingmask: false,
        okCallback: function (res, options) {
            if (res == null || res.length == 0) {
                return;
            }
            var htmlStr = "";
            for (var i = 0; i < res.length; i++) {
                htmlStr += '<option value="' + res[i].value + '">' + res[i].text + '</option>';
            }
            $(id).html(htmlStr);
            $(id).selectpicker('refresh');
        }
    });
}

/**
 * 初始化select下拉列表
 * @param list list数据
 */
function initSelect(list) {
    if (list == null || list.length == 0) {
        return;
    }
    var id = this.selector;
    var htmlStr = "";
    for (var i = 0; i < list.length; i++) {
        htmlStr += '<option value="' + list[i].value + '">' + list[i].text + '</option>';
    }
    $(id).html(htmlStr);
    $(id).selectpicker('refresh');
}

/**
 * 初始化图片上传控件
 *
 * @param option 对象参数 必须：server thumbPick
 */
function initUpload(option) {
    var defaultOptions = {
        server: null, // 上传地址
        pick: this.selector, // 选择图片id
        maxSize: 1024 * 1024, // 默认最大1M
        loadingmask: true, // 加载遮罩
        thumbPick: null, // 缩略图id:带#
        thumbWidth: 100, // 缩略图宽
        thumbHeight: 100, // 缩略图高
        uploadBeforeSend: null, // form表单数据修改
        uploadError: null, // 上传失败事件
        uploadSuccess: null, // 上传成功事件
    };
    var options = $.extend({}, defaultOptions, typeof option === 'object' && option)

    var $target, $ajaxMask

    // 头像文件选中
    var uploader = WebUploader.create({
        auto: false,
        server: options.server,
        pick: options.pick,
        swf: BJUI.PLUGINPATH + 'webuploader/Uploader.swf',
        mxUpload: true,
        thumb: {
            // 是否允许裁剪。
            crop: false,
            allowMagnify: true,
        }
    });
    // 当有文件添加进来的时候
    uploader.on('fileQueued', function (file) {
        if (file.size > options.maxSize) {
            BJUI.alertmsg('error', '图片不能超过' + WebUploader.formatSize(options.maxSize) + '，请重新选择！')
            return;
        }
        // 判断文件格式：使用accept影响打开选中框的速度
        var imgReg = /^(jpg|jpeg|png|bmp)$/;
        if (!imgReg.test(file.ext)) {
            BJUI.alertmsg('error', '请选择图片文件！')
            return;
        }
        // 创建缩略图
        uploader.makeThumb(file, function (error, src) {
            if (error) {
                $(options.thumbPick).html('<span>预览失败</span>');
                return;
            }
            // 缩略图填满父元素
            $(options.thumbPick).html('<img style="width: 100%;height: 100%;">');
            $(options.thumbPick + " img").attr("src", src);
        });
    });
    // 开始上传事件
    uploader.on('startUpload', function () {
        if (options.loadingmask) {
            $target = $.CurrentDialog != null ? $.CurrentDialog : $.CurrentNavtab;
            $target.trigger(BJUI.eventType.ajaxStatus)
            $ajaxMask = $target.find('> .bjui-ajax-mask')
        }
    });
    // 开始发送数据事件
    uploader.on('uploadBeforeSend', function (block, data) {
        if (typeof options.uploadBeforeSend === 'function') {
            options.uploadBeforeSend(block, data);
        }
    });
    // 上传完成事件:不管成功失败
    uploader.on('uploadComplete', function (file) {
        if ($ajaxMask && options.loadingmask) {
            $target.trigger('bjui.ajaxStop')
        }
    });
    // 上传失败事件
    uploader.on('uploadError', function (file, reason) {
        if (typeof options.uploadError === 'function') {
            options.uploadError(file, reason);
        } else {
            BJUI.alertmsg('error', reason)
        }
    });
    // 上传成功事件
    uploader.on('uploadSuccess', function (file, response) {
        if (typeof options.uploadSuccess === 'function') {
            options.uploadSuccess(file, response);
        } else {
            BJUI.alertmsg('ok', MX.msg.saveSuccess, {});
        }
    });
    return uploader;
}

/**
 * 插入loading动画
 * @param type 类别
 */
function insertLoading(type) {
    var id = this.selector;
    var str = ""
    switch (type) {
        case "ball-grid-beat":
            str += '<div class="div-mask">'
                + '<div class="la-ball-grid-beat la-2x div-center">'
                + '<div></div> <div></div> <div></div> <div></div> <div></div> <div></div> <div></div> <div></div> <div></div>'
                + '</div></div>';
            break;
        case "ball-square-clockwise-spin":
            str += '<div class="div-mask">'
                + '<div class="la-ball-square-clockwise-spin la-2x div-center">'
                + '<div></div> <div></div> <div></div> <div></div> <div></div> <div></div> <div></div> <div></div>'
                + '</div></div>';
            break;
    }
    $(id).html(str);
}


