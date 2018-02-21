//@ sourceURL=code.js
// 已存代码
var codeList = {};
// html模版赋值替换
var initTemplate = function (btn, template) {
    // 遍历属性赋值
    var obj = JSON.parse(btn.getAttribute("data-code"));
    // 未定义替换为空
    if (obj == null || obj == "") {
        return template.replaceAll(/#[^#]*Text#/, "")
    }
    for (var index in obj) {
        template = template.replaceAll("#" + index + "Text#", obj[index]);
    }
    // 未定义替换为空
    template = template.replaceAll(/#[^#]*Text#/, "")
    return template;
}

// code模版赋值替换
var initCodeTemplate = function (btn, template) {
    // 遍历属性赋值
    var obj = JSON.parse(btn.getAttribute("data-code"));
    // 未定义替换为空
    if (obj == null || obj == "") {
        return template.replaceAll(/#[^#]*Text#/, "")
    }
    for (var index in obj) {
        var value = obj[index];
        // 去除引号
        value = value.replaceAll("'", "");
        value = value.replaceAll("\"", "");
        if (value.indexOf("=") > 0) {
            value = value.split("=")[1];
        }
        // name为空取id“_”后面值
        if (index == "name" && (value == null || value == "")) {
            var tempId = obj["id"].replaceAll("'", "").replaceAll("\"", "");
            if (tempId.indexOf("=") > 0) {
                tempId = tempId.split("=")[1];
            }
            if (tempId != null) {
                // id: xxDialog_name
                var idSplit = tempId.split("_");
                if (idSplit.length > 1) {
                    value = idSplit[idSplit.length - 1];
                } else {
                    value = idSplit[0];
                }
            }
        }
        template = template.replaceAll("#" + index + "Text#", value);
    }

    // 未定义替换为空
    template = template.replaceAll(/#[^#]*Text#/, "")
    return template;
}

// 获得代码
var getCode = function (type) {
    // 文本框
    var textTemplate = '<label #idForText# class="row-label">#labelText#</label>\n'
        + '<div class="row-input #requiredText#">\n'
        + '    <input type="text" #idText# #nameText# #placeholderText# #readonlyText# #disabledText# #classText# #sizeText# #ruleText# #valueText#>\n'
        + '</div>\n';
    var textSet = '// #labelText#\n$("##idText#").val(obj.#nameText#);\n';
    var textGet = '// #labelText#\n$("##idText#").val();\n';
    var textObjGet = '#nameText#: $("##idText#").val(),\n';
    // 隐藏文本框
    var hideTextTemplate = '<input type="hidden" #idText# #nameText# #valueText#>\n';
    // 多行文本框
    var textareaTemplate = '<label #idForText# class="row-label">#labelText#</label>\n'
        + '<div class="row-input #requiredText#">\n'
        + '    <textarea #idText# #nameText# #placeholderText# #readonlyText# #disabledText# #colsText# #rowsText#  #autoheightText# #ruleText# #valueText#></textarea>\n'
        + '</div>\n';
    // select
    var selectTemplate = '<label #idForText# class="row-label">#labelText#</label>\n'
        + '<div class="row-input #requiredText#">\n'
        + '    <select data-toggle="selectpicker" #idText# #nameText# #disabledText# #multipleText# #widthText# #ruleText#>\n'
        + '      #optionText#\n'
        + '    </select>\n'
        + '</div>\n';
    var selectSet = '// #labelText#\n$("##idText#").selectpicker("val",obj.#nameText#);\n';
    var selectGet = '// #labelText#\n$("##idText#").val();\n';
    var selectObjGet = '#nameText#: $("##idText#").val(),\n';
    // 单选框
    var radioTemplate = '<label #idForText# class="row-label">#labelText#</label>\n'
        + '<div class="row-input">\n'
        + '    <input type="radio" data-toggle="icheck"  #idText# #nameText# #disabledText# #valueText# #checkedText# #dataLabelText#>\n'
        + '</div>\n';
    // 复选框
    var checkboxTemplate = '<label #idForText# class="row-label">#labelText#</label>\n'
        + '<div class="row-input">\n'
        + '    <input type="checkbox" data-toggle="icheck"  #idText# #nameText# #disabledText# #valueText# #checkedText# #dataLabelText#>\n'
        + '</div>\n';
    var checkboxSet = '// #labelText#\n"是否选中" ? $("##idText#").iCheck("check"): $("##idText#").iCheck("uncheck");\n';
    var checkboxGet = '// #labelText#\n$("##idText#")[0].checked ? "选中的值" : "未选中的值";\n';
    var checkboxObjGet = '#nameText#: $("##idText#")[0].checked ? "选中的值" : "未选中的值",\n';

    // 日期
    var datepickerTemplate = '<label #idForText# class="row-label">#labelText#</label>\n'
        + '<div class="row-input #requiredText#">\n'
        + '    <input type="text" data-toggle="datepicker" #idText# #nameText# #placeholderText# #readonlyText# #disabledText# #patternText# #ruleText# #valueText#></input>\n'
        + '</div>\n';
    var datepickerSet = '// #labelText#\n$("##idText#").val(new Date(obj.#nameText#).formatDate("#patternText#"));\n';

    // 文本框按钮前缀
    var textIdPrefix = "code_text_";
    // 隐藏文本框按钮前缀
    var hideTextIdPrefix = "code_hideText_";
    // 隐藏文本框按钮前缀
    var textareaIdPrefix = "code_textarea_";
    // select按钮前缀
    var selectIdPrefix = "code_select_";
    // 单选框按钮前缀
    var radioIdPrefix = "code_radio_";
    // 复选框按钮前缀
    var checkboxIdPrefix = "code_checkbox_";
    // 日期按钮前缀
    var datepickerIdPrefix = "code_datepicker_";

    var btns = $("#code_dropDivId div button");
    var code = "";
    var setCode = "";
    var getCode = "";
    var getObjCode = "";
    for (var i = 0; i < btns.length; i++) {
        // 文本框
        if (btns[i].id.startsWith(textIdPrefix)) {
            code += initTemplate(btns[i], textTemplate);
            setCode += initCodeTemplate(btns[i], textSet);
            getCode += initCodeTemplate(btns[i], textGet);
            getObjCode += initCodeTemplate(btns[i], textObjGet);
        } else if ((btns[i].id.startsWith(hideTextIdPrefix))) {
            code += initTemplate(btns[i], hideTextTemplate);
            setCode += initCodeTemplate(btns[i], textSet);
            getCode += initCodeTemplate(btns[i], textGet);
            getObjCode += initCodeTemplate(btns[i], textObjGet);
        } else if ((btns[i].id.startsWith(textareaIdPrefix))) {
            code += initTemplate(btns[i], textareaTemplate);
            setCode += initCodeTemplate(btns[i], textSet);
            getCode += initCodeTemplate(btns[i], textGet);
            getObjCode += initCodeTemplate(btns[i], textObjGet);
        } else if ((btns[i].id.startsWith(selectIdPrefix))) {
            code += initTemplate(btns[i], selectTemplate);
            setCode += initCodeTemplate(btns[i], selectSet);
            getCode += initCodeTemplate(btns[i], selectGet);
            getObjCode += initCodeTemplate(btns[i], selectObjGet);
        } else if ((btns[i].id.startsWith(radioIdPrefix))) {
            code += initTemplate(btns[i], radioTemplate);
            setCode += initCodeTemplate(btns[i], checkboxSet);
            getCode += initCodeTemplate(btns[i], checkboxGet);
            getObjCode += initCodeTemplate(btns[i], checkboxObjGet);
        } else if ((btns[i].id.startsWith(checkboxIdPrefix))) {
            code += initTemplate(btns[i], checkboxTemplate);
            setCode += initCodeTemplate(btns[i], checkboxSet);
            getCode += initCodeTemplate(btns[i], checkboxGet);
            getObjCode += initCodeTemplate(btns[i], checkboxObjGet);
        } else if ((btns[i].id.startsWith(datepickerIdPrefix))) {
            code += initTemplate(btns[i], datepickerTemplate);
            setCode += initCodeTemplate(btns[i], datepickerSet);
            getCode += initCodeTemplate(btns[i], textGet);
            getObjCode += initCodeTemplate(btns[i], textObjGet);
        }
    }

    var res;
    if (type == 1) {
        res = setCode;
    } else if (type == 2) {
        res = getCode;
    } else if (type == 3) {
        // 拼接头尾；截取最后,和换行符
        getObjCode = 'var obj= {\n' + getObjCode.substring(0, getObjCode.length - 2) + '\n};\n';
        res = getObjCode;
    } else {
        //type == 0
        var formTemplateStart = '<form id="demoDialog_form" class="bjui-row #colCountText#">\n';
        var formTemplateEnd = '</form>\n';
        // 几列
        formTemplateStart = formTemplateStart.replace("#colCountText#", "col-" + $("#code_colCount").val());
        code = formTemplateStart + code + formTemplateEnd;
        res = code;
    }
    // 多个空格改为一个
    res = res.replaceAll(/ {2,}/, " ");
    return res;
}

// 渲染代码区
var initCodeArea = function () {
    var code = getCode(0);
    // 替换特殊字符 < > &
    code = code != "" ? code.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;') : "";
    $("#code_codeArea").html(code);
    // 重新渲染代码区
    $('#code_codeArea').removeClass('prettyprinted');
    prettyPrint();
}

// 允许放入div
function code_allowDrop(ev) {
    ev.preventDefault();
}

// 开始拖动按钮
function code_drag(ev) {
    ev.dataTransfer.setData("id", ev.target.id);
    ev.dataTransfer.setData("textContent", ev.target.textContent);
    ev.dataTransfer.setData("icon", ev.target.getAttribute("data-icon"));
    ev.dataTransfer.setData("code", ev.target.getAttribute("data-code"));
}

// 放下按钮
function code_drop(ev) {
    ev.preventDefault();
    var fromBtnId = ev.dataTransfer.getData("id");
    var fromDiv = $("#" + fromBtnId).parent();
    // 右侧区域内拖动
    if (fromDiv.hasClass("drop-div")) {
        // 右侧框：是否已有元素
        if (ev.currentTarget.children.length > 0) {
            // 交换按钮
            var targetDiv = $(ev.currentTarget);
            var fromHtml = fromDiv.html();
            var targetHtml = targetDiv.html();
            targetDiv.html(fromHtml);
            fromDiv.html(targetHtml);
        } else {
            // 移动按钮
            var fromHtml = fromDiv.html();
            $(ev.currentTarget).html(fromHtml);
            fromDiv.html("");
        }

    } else {
        // 左侧拖动到右侧
        var fromBtnTextContent = ev.dataTransfer.getData("textContent");
        var fromBtnIcon = ev.dataTransfer.getData("icon");
        var code = ev.dataTransfer.getData("code");
        var id = fromBtnId + "_" + new Date().getTime();
        var newBtn = '&nbsp;<input name="dropDivCheckbox" type="checkbox" data-toggle="icheck">&nbsp;&nbsp;'
            + '<button id="' + id + '" data-code=\'' + code + '\' class="btn-default ' + fromBtnId + ' btn" type="button" draggable="true" ondragstart="code_drag(event)" data-icon="' + fromBtnIcon + '"><i class="fa fa-' + fromBtnIcon + '"></i> ' + fromBtnTextContent + '</button>';
        $(ev.currentTarget).html(newBtn);
    }
    initCodeArea();
}

$(function () {
    void function () {
        // 行修改
        $("#code_rowCount").change(function () {
            initDropFn(1);
            initCodeArea();
        });
        // 列修改
        $("#code_colCount").change(function () {
            initDropFn(2);
            initCodeArea();
        });
        // 预览
        $("#code_previewBtn").click(function () {
            BJUI.dialog({
                id: 'code_previewDialogId',
                title: '预览',
                width: 650,
                height: 320,
                mask: true,
                html: getCode(0),
            });
        });
        // 代码
        $("#code_codeBtn").click(function () {
            BJUI.dialog({
                id: 'code_codeDialogId',
                title: '代码',
                width: 650,
                height: 320,
                mask: true,
                url: 'html/system/codeDialog.html',
                onLoad: function ($dialog) {
                    new Clipboard('#codeDialog_copySetBtn');
                    new Clipboard('#codeDialog_copyGetBtn');
                    new Clipboard('#codeDialog_copyGetObjBtn');
                    $("#codeDialog_set").html(getCode(2));
                    $('#codeDialog_set').removeClass('prettyprinted');
                    $("#codeDialog_get").html(getCode(1));
                    $('#codeDialog_get').removeClass('prettyprinted');
                    $("#codeDialog_getObj").html(getCode(3));
                    $('#codeDialog_getObj').removeClass('prettyprinted');
                    prettyPrint();
                }
            });
        });
        // 刷新
        $("#code_refreshBtn").click(function () {
            initCodeArea();
        });
        var getCodeListFn = function () {
            BJUI.ajax('doajax', {
                url: 'system/code/getCode',
                data: {
                    type: 0
                },
                loadingmask: true,
                okCallback: function (res, options) {
                    var list = res.codeList;

                    var htmlStr = "";
                    for (var i = 0; i < list.length; i++) {
                        codeList[list[i].id] = list[i].code;
                        var tempStr = '<li data-id="' + list[i].id + '" data-title="' + list[i].title + '">'
                            + '<a>' + list[i].title + '</a>'
                            + '<i id="' + list[i].id + '" class="fa fa-minus-circle"></i>&nbsp;&nbsp;</li>';
                        htmlStr += tempStr;
                    }
                    $("#code_codeList").html(htmlStr);

                    // 代码点击事件
                    $("#code_codeList li").click(function (e) {
                        $("#code_codeList li").removeClass("active");
                        $(this).addClass("active");
                        var id = e.currentTarget.getAttribute("data-id");
                        $("#code_dropDivId").html(codeList[id]);
                        $("#code_codeId").val(id);
                        $("#code_title").val(e.currentTarget.getAttribute("data-title"));
                        initCodeArea();
                    });

                    // 代码删除事件
                    $("#code_codeList li .fa-minus-circle").click(function (e) {
                        e.stopPropagation();
                        BJUI.alertmsg('confirm', '确定要删除该代码？', {
                            okCall: function () {
                                BJUI.ajax('doajax', {
                                    url: 'system/code/deleteCode',
                                    data: {
                                        id: e.currentTarget.id
                                    },
                                    loadingmask: true,
                                    okCallback: function (json, options) {
                                        // 提示成功，关闭dialog
                                        BJUI.alertmsg('ok', '删除成功！', {});
                                        getCodeListFn();
                                    }
                                });
                            }
                        });
                    });
                }
            });
        };
        // 保存
        $("#code_saveBtn").click(function () {
            var title = $("#code_title").val();
            if (title == null || title == "") {
                title = "form_" + new Date().getTime();
            }
            BJUI.ajax('doajax', {
                url: 'system/code/addCode',
                data: {
                    id: $("#code_codeId").val(),
                    title: title,
                    type: 0,
                    code: $("#code_dropDivId").html()
                },
                loadingmask: true,
                okCallback: function (res, options) {
                    $("#code_codeId").val(res.id);
                    getCodeListFn();
                }
            });
        });
        // 删除选中组件
        $("#code_deleteBtn").click(function () {
            var checkbox = $(".drop-div input[name=dropDivCheckbox]");
            for (var i = 0; i < checkbox.length; i++) {
                var cb = $(checkbox[i]);
                if (cb.is(':checked')) {
                    cb.parent().html("")
                }
            }
            initCodeArea();
        });
        // 清空全部组件
        $("#code_clearBtn").click(function () {
            var dropDiv = $(".drop-div");
            for (var i = 0; i < dropDiv.length; i++) {
                $(dropDiv[i]).html("")
            }
            initCodeArea();
        });
        // 选择已存代码
        $("#code_codeListTab").click(function () {
            getCodeListFn();
        });

        // 获得表单通用属性
        var getCommonCodeData = function (dialogId) {
            var codeData = {};
            // 标签名
            var label = $("#" + dialogId + "_label").val();
            codeData.label = label == null || label == "" ? "" : label;
            // id
            var id = $("#" + dialogId + "_id").val();
            codeData.id = id == null || id == "" ? "" : "id='" + id + "'";
            codeData.idFor = id == null || id == "" ? "" : "for='" + id + "'";
            // name
            var name = $("#" + dialogId + "_name").val();
            codeData.name = name == null || name == "" ? "" : "name='" + name + "'";
            // value
            var value = $("#" + dialogId + "_value").val();
            codeData.value = value == null || value == "" ? "" : "value='" + value + "'";
            // 占位符
            var placeholder = $("#" + dialogId + "_placeholder").val();
            codeData.placeholder = placeholder == null || placeholder == "" ? "" : "placeholder='" + placeholder + "'";
            // 只读
            var readonly = $("#" + dialogId + "_readonly");
            codeData.readonly = readonly != null && readonly.length > 0 && readonly[0].checked
                ? "readonly=''" : "";
            // 禁用
            var disabled = $("#" + dialogId + "_disabled");
            codeData.disabled = disabled != null && disabled.length > 0 && disabled[0].checked
                ? "disabled=''" : "";
            return codeData;
        };
        // 设置表单通用属性
        var setCommonCodeData = function (dialogId, obj) {
            $("#" + dialogId + "_label").val(obj.label);
            $("#" + dialogId + "_id").val(obj.id.substring("id='".length, obj.id.length - 1));
            $("#" + dialogId + "_name").val(obj.name.substring("name='".length, obj.name.length - 1));
            $("#" + dialogId + "_value").val(obj.value.substring("value='".length, obj.value.length - 1));
            $("#" + dialogId + "_placeholder").val(obj.placeholder.substring("placeholder='".length, obj.placeholder.length - 1));
            if (obj.readonly != null && obj.readonly != "") {
                $("#" + dialogId + "_readonly").iCheck('check')
            }
            if (obj.disabled != null && obj.disabled != "") {
                $("#" + dialogId + "_disabled").iCheck('check')
            }
        }
        // 右侧可拖动表格
        var dropDiv = function (count) {
            var dropHtml = "";
            for (var i = 0; i < count; i++) {
                dropHtml += '<div class="drop-div" ondrop="code_drop(event)" ondragover="code_allowDrop(event)"> </div>';
            }
            return dropHtml;
        };
        // 初始化右侧框 type:0初始化 1行修改 2列修改
        var initDropFn = function (type) {
            var row = $("#code_rowCount").val();
            var column = $("#code_colCount").val();
            var dropHtml = "";
            var dropDivs = $("#code_dropDivId div");
            var dropDivCount = dropDivs.length;
            if (type == 1) {
                var rowT = row - dropDivCount / column;
                if (rowT > 0) {
                    // 添加行
                    dropHtml = $("#code_dropDivId").append(dropDiv(rowT * column));
                } else if (rowT < 0) {
                    // 删除行，更新code
                    for (var i = 0; i < Math.abs(rowT * column); i++) {
                        dropDivs[--dropDivCount].remove();
                    }
                    initCodeArea();
                }
            } else if (type == 2) {
                var oldColumn = dropDivCount / row;
                var colT = column - oldColumn;
                if (colT > 0) {
                    for (var i = 1; i <= row; i++) {
                        $(dropDivs[i * oldColumn - 1]).after(dropDiv(1));
                    }
                } else if (colT < 0) {
                    for (var i = 1; i <= row; i++) {
                        $(dropDivs[i * oldColumn - 1]).remove();
                    }
                }
            } else {
                dropHtml = dropDiv(row * column);
                $("#code_dropDivId").html(dropHtml);
            }

            var width = Math.floor(1 / column * 100) + "%";
            $(".drop-div").css("width", width);
        };

        // 文本框:长度隐藏
        var hideTextDataStartEnd = function () {
            $("#textDialog_dataStart").val("");
            $("#textDialog_dataEnd").val("");
            $("#textDialog_dataStart").hide();
            $("#textDialog_dataEnd").hide();
        }
        // 文本框
        var textClick = function (e) {
            $("#code_currentBtnId").val(e.currentTarget.id);
            BJUI.dialog({
                id: 'code_textDialogId',
                title: '文本框属性',
                width: 650,
                height: 320,
                mask: true,
                url: 'html/system/textDialog.html',
                onLoad: function ($dialog) {
                    // 尺寸大小
                    $("#textDialog_size").change(function (e) {
                        if (e.currentTarget.value == 1) {
                            $("#textDialog_sizeNum").show();
                        } else {
                            $("#textDialog_sizeNum").hide();
                        }
                    });

                    $("#textDialog_length").selectpicker('hide');
                    $("#textDialog_integer").selectpicker('hide');
                    $("#textDialog_range").selectpicker('hide');
                    hideTextDataStartEnd();

                    // 数据类型 1字符 2整数 3数值
                    $("#textDialog_dataType").change(function (e) {
                        $("#textDialog_length").selectpicker('hide');
                        $("#textDialog_integer").selectpicker('hide');
                        $("#textDialog_range").selectpicker('hide');
                        hideTextDataStartEnd();
                        switch (e.currentTarget.value) {
                            case "1":
                                $("#textDialog_length").selectpicker('show');
                                break;
                            case "2":
                                $("#textDialog_integer").selectpicker('show');
                                break;
                            case "3":
                                $("#textDialog_range").selectpicker('show');
                                break;
                        }
                    });

                    // 字符长度
                    $("#textDialog_length").change(function (e) {
                        hideTextDataStartEnd();
                        if (e.currentTarget.value == 4) {
                            $("#textDialog_dataStart").show();
                            $("#textDialog_dataEnd").show();
                        } else if (e.currentTarget.value != 0) {
                            $("#textDialog_dataStart").show();
                        }
                    });
                    // 数值长度
                    $("#textDialog_range").change(function (e) {
                        hideTextDataStartEnd();
                        if (e.currentTarget.value == 3) {
                            $("#textDialog_dataStart").show();
                            $("#textDialog_dataEnd").show();
                        } else {
                            $("#textDialog_dataStart").show();
                        }
                    });
                    // 整数
                    $("#textDialog_integer").change(function (e) {
                        hideTextDataStartEnd();
                        var value = e.currentTarget.value;
                        if (value == "4") {
                            $("#textDialog_dataStart").show();
                            $("#textDialog_dataEnd").show();
                        } else if (value == "2" || value == "3") {
                            $("#textDialog_dataStart").show();
                        }
                    });

                    // 保存
                    $("#textDialog_saveBtn").click(function () {
                        var codeData = {};
                        // // 标签名
                        // var label = $("#textDialog_label").val();
                        // codeData.label = label == null || label == "" ? "" : label;
                        // // id
                        // var id = $("#textDialog_id").val();
                        // codeData.id = id == null || id == "" ? "" : "id='" + id + "'";
                        // codeData.idFor = id == null || id == "" ? "" : "for='" + id + "'";
                        // // name
                        // var name = $("#textDialog_name").val();
                        // codeData.name = name == null || name == "" ? "" : "name='" + name + "'";
                        // // value
                        // var value = $("#textDialog_value").val();
                        // codeData.value = value == null || value == "" ? "" : "value='" + value + "'";
                        // // 占位符
                        // var placeholder = $("#textDialog_placeholder").val();
                        // codeData.placeholder = placeholder == null || placeholder == "" ? "" : "placeholder='" + placeholder + "'";
                        // // 只读
                        // codeData.readonly = $("#textDialog_readonly")[0].checked ? "readonly=''" : "";
                        // // 禁用
                        // codeData.disabled = $("#textDialog_disabled")[0].checked ? "disabled=''" : "";
                        codeData = getCommonCodeData("textDialog");
                        // 大小
                        codeData.size = "";
                        codeData.class = "";
                        var size = $("#textDialog_size").val();
                        if (size == 0) {
                            codeData.class = "";
                        } else if (size == 1) {
                            codeData.size = "size='" + $("#textDialog_sizeNum").val() + "'";
                        } else {
                            codeData.class = "class='" + size + "'";
                        }

                        // 验证规则
                        var ruleArr = [];
                        // 必填
                        codeData.required = "";
                        if ($("#textDialog_required")[0].checked) {
                            ruleArr.push("required");
                            codeData.required = "required";
                        }
                        // 常用规则
                        var other = $("#textDialog_other").val();
                        if (other != "0") {
                            ruleArr.push(other)
                        }
                        var dataType = $("#textDialog_dataType").val();
                        if (dataType == "1") {
                            // 字符
                            switch ($("#textDialog_length").val()) {
                                case "1":
                                    // 最多n个
                                    ruleArr.push("length(~" + $("#textDialog_dataStart").val() + ")");
                                    break;
                                case "2":
                                    // 至少n个
                                    ruleArr.push("length(" + $("#textDialog_dataStart").val() + "~)");
                                    break;
                                case "3":
                                    // n个
                                    ruleArr.push("length(" + $("#textDialog_dataStart").val() + ")");
                                    break;
                                case "4":
                                    // n1~n2个
                                    ruleArr.push("length(" + $("#textDialog_dataStart").val() + "~" + $("#textDialog_dataEnd").val() + ")");
                                    break;
                            }

                        } else if (dataType == "2") {
                            // 整数
                            switch ($("#textDialog_integer").val()) {
                                case "1":
                                    // 整数
                                    ruleArr.push("integer");
                                    break;
                                case "2":
                                    // 不小于n
                                    ruleArr.push("integer(" + $("#textDialog_dataStart").val() + "~)");
                                    break;
                                case "3":
                                    // 不大于n
                                    ruleArr.push("integer(~" + $("#textDialog_dataStart").val() + ")");
                                    break;
                                case "4":
                                    // n1~n2个
                                    ruleArr.push("integer(" + $("#textDialog_dataStart").val() + "~" + $("#textDialog_dataEnd").val() + ")");
                                    break;
                            }
                        } else if (dataType == "3") {
                            // 整数
                            switch ($("#textDialog_range").val()) {
                                case "1":
                                    // 不小于n
                                    ruleArr.push("range(" + $("#textDialog_dataStart").val() + "~)");
                                    break;
                                case "2":
                                    // 不大于n
                                    ruleArr.push("range(~" + $("#textDialog_dataStart").val() + ")");
                                    break;
                                case "3":
                                    // n1~n2个
                                    ruleArr.push("range(" + $("#textDialog_dataStart").val() + "~" + $("#textDialog_dataEnd").val() + ")");
                                    break;
                            }

                        }
                        codeData.rule = ruleArr.length == 0 ? "" : "data-rule='" + ruleArr.join(";") + "'";

                        $("#" + $("#code_currentBtnId").val()).attr("data-code", JSON.stringify(codeData))
                        initCodeArea();
                        BJUI.dialog('close', 'code_textDialogId')
                    });

                    // 赋值
                    var codeObj = JSON.parse($("#" + $("#code_currentBtnId").val())[0].getAttribute("data-code"));
                    if (codeObj != null) {
                        setCommonCodeData("textDialog", codeObj);
                        var tempClass = codeObj.class;
                        if (tempClass != null && tempClass != "") {
                            $("#textDialog_size").selectpicker("val", tempClass.substring("class='".length, tempClass.length - 1))
                        }
                        var tempSize = codeObj.size;
                        if (tempSize != null && tempSize != "") {
                            $("#textDialog_size").selectpicker("val", "1");
                            $("#textDialog_sizeNum").show();
                            $("#textDialog_sizeNum").val(tempSize.substring("size='".length, tempSize.length - 1));
                        }
                        if (codeObj.required != null && codeObj.required != "") {
                            $("#textDialog_required").iCheck('check')
                        }
                        var tempRule = codeObj.rule;
                        tempRule = tempRule == null ? "" : tempRule;
                        if (tempRule.indexOf("email") >= 0) {
                            $("#textDialog_other").selectpicker("val", "email");
                        } else if (tempRule.indexOf("mobile") >= 0) {
                            $("#textDialog_other").selectpicker("val", "mobile");
                        } else if (tempRule.indexOf("length") >= 0) {
                            $("#textDialog_dataType").selectpicker("val", "1");
                            $("#textDialog_length").selectpicker('show');
                            $("#textDialog_dataStart").show();
                            var maxMatch = tempRule.match(/length\(~\d+\)/g);
                            var leastMatch = tempRule.match(/length\(\d+~\)/g);
                            var nMatch = tempRule.match(/length\(\d+\)/g);
                            var nnMatch = tempRule.match(/length\(\d+~\d+\)/g);
                            if (nnMatch != null && nnMatch.length > 0) {
                                var nnMatchArr = nnMatch[0].match(/\d+/g);
                                $("#textDialog_length").selectpicker("val", "4");
                                $("#textDialog_dataStart").val(nnMatchArr[0]);
                                $("#textDialog_dataEnd").val(nnMatchArr[1]);
                                $("#textDialog_dataEnd").show();
                            } else if (maxMatch != null && maxMatch.length > 0) {
                                $("#textDialog_length").selectpicker("val", "1");
                                $("#textDialog_dataStart").val(maxMatch[0].match(/\d+/g)[0]);
                            } else if (leastMatch != null && leastMatch.length > 0) {
                                $("#textDialog_length").selectpicker("val", "2");
                                $("#textDialog_dataStart").val(leastMatch[0].match(/\d+/g)[0]);
                            } else if (nMatch != null && nMatch.length > 0) {
                                $("#textDialog_length").selectpicker("val", "3");
                                $("#textDialog_dataStart").val(nMatch[0].match(/\d+/g)[0]);
                            }
                        } else if (tempRule.indexOf("integer") >= 0) {
                            $("#textDialog_dataType").selectpicker("val", "2");
                            $("#textDialog_integer").selectpicker('show');
                            var maxMatch = tempRule.match(/integer\(~\d+\)/g);
                            var leastMatch = tempRule.match(/integer\(\d+~\)/g);
                            var nMatch = tempRule.match(/integer/g);
                            var nnMatch = tempRule.match(/integer\(\d+~\d+\)/g);
                            if (nnMatch != null && nnMatch.length > 0) {
                                var nnMatchArr = nnMatch[0].match(/\d+/g);
                                $("#textDialog_integer").selectpicker("val", "4");
                                $("#textDialog_dataStart").val(nnMatchArr[0]);
                                $("#textDialog_dataStart").show();
                                $("#textDialog_dataEnd").val(nnMatchArr[1]);
                                $("#textDialog_dataEnd").show();
                            } else if (maxMatch != null && maxMatch.length > 0) {
                                $("#textDialog_integer").selectpicker("val", "3");
                                $("#textDialog_dataStart").val(maxMatch[0].match(/\d+/g)[0]);
                                $("#textDialog_dataStart").show();
                            } else if (leastMatch != null && leastMatch.length > 0) {
                                $("#textDialog_integer").selectpicker("val", "2");
                                $("#textDialog_dataStart").val(leastMatch[0].match(/\d+/g)[0]);
                                $("#textDialog_dataStart").show();
                            } else if (nMatch != null && nMatch.length > 0) {
                                $("#textDialog_integer").selectpicker("val", "1");
                            }
                        } else if (tempRule.indexOf("range") >= 0) {
                            $("#textDialog_dataType").selectpicker("val", "3");
                            $("#textDialog_range").selectpicker('show');
                            var maxMatch = tempRule.match(/range\(~\d+\)/g);
                            var leastMatch = tempRule.match(/range\(\d+~\)/g);
                            var nnMatch = tempRule.match(/range\(\d+~\d+\)/g);
                            if (nnMatch != null && nnMatch.length > 0) {
                                var nnMatchArr = nnMatch[0].match(/\d+/g);
                                $("#textDialog_range").selectpicker("val", "3");
                                $("#textDialog_dataStart").val(nnMatchArr[0]);
                                $("#textDialog_dataStart").show();
                                $("#textDialog_dataEnd").val(nnMatchArr[1]);
                                $("#textDialog_dataEnd").show();
                            } else if (maxMatch != null && maxMatch.length > 0) {
                                $("#textDialog_range").selectpicker("val", "2");
                                $("#textDialog_dataStart").val(maxMatch[0].match(/\d+/g)[0]);
                                $("#textDialog_dataStart").show();
                            } else if (leastMatch != null && leastMatch.length > 0) {
                                $("#textDialog_range").selectpicker("val", "1");
                                $("#textDialog_dataStart").val(leastMatch[0].match(/\d+/g)[0]);
                                $("#textDialog_dataStart").show();
                            }
                        }
                    }

                }
            })
        }

        // 隐藏文本框
        var hideTextClick = function (e) {
            $("#code_currentBtnId").val(e.currentTarget.id);
            BJUI.dialog({
                id: 'code_hideTextDialogId',
                title: '隐藏文本框属性',
                width: 600,
                height: 200,
                mask: true,
                url: 'html/system/hideTextDialog.html',
                onLoad: function ($dialog) {
                    // 保存
                    $("#hideTextDialog_saveBtn").click(function () {
                        var codeData = {};
                        // id
                        var id = $("#hideTextDialog_id").val();
                        codeData.id = id == null || id == "" ? "" : "id='" + id + "'";
                        // name
                        var name = $("#hideTextDialog_name").val();
                        codeData.name = name == null || name == "" ? "" : "name='" + name + "'";
                        // value
                        var value = $("#hideTextDialog_value").val();
                        codeData.value = value == null || value == "" ? "" : "value='" + value + "'";

                        $("#" + $("#code_currentBtnId").val()).attr("data-code", JSON.stringify(codeData))
                        initCodeArea();
                        BJUI.dialog('close', 'code_hideTextDialogId')
                    });

                    // 赋值
                    var codeObj = JSON.parse($("#" + $("#code_currentBtnId").val())[0].getAttribute("data-code"));
                    if (codeObj != null) {
                        $("#hideTextDialog_id").val(codeObj.id.substring("id='".length, codeObj.id.length - 1));
                        $("#hideTextDialog_name").val(codeObj.name.substring("name='".length, codeObj.name.length - 1));
                        $("#hideTextDialog_value").val(codeObj.value.substring("value='".length, codeObj.value.length - 1));
                    }

                }
            })
        };

        // textarea
        var textareaClick = function (e) {
            $("#code_currentBtnId").val(e.currentTarget.id);
            BJUI.dialog({
                id: 'code_textareaDialogId',
                title: 'textarea',
                width: 650,
                height: 320,
                mask: true,
                url: 'html/system/textareaDialog.html',
                onLoad: function ($dialog) {
                    // 保存
                    $("#textareaDialog_saveBtn").click(function () {
                        var codeData = {};
                        codeData = getCommonCodeData("textareaDialog");
                        // 自动行高
                        codeData.autoheight = $("#textareaDialog_autoheight")[0].checked ? "data-toggle='autoheight'" : "";
                        // 列尺寸
                        var cols = $("#textareaDialog_cols").val();
                        codeData.cols = cols == null || cols == "" ? "" : "cols='" + cols + "'";
                        // 行尺寸
                        var rows = $("#textareaDialog_rows").val();
                        codeData.rows = rows == null || rows == "" ? "" : "rows='" + rows + "'";
                        // 验证规则
                        var ruleArr = [];
                        // 必填
                        codeData.required = "";
                        if ($("#textareaDialog_required")[0].checked) {
                            ruleArr.push("required");
                            codeData.required = "required";
                        }
                        var length = $("#textareaDialog_length").val();
                        if (length != null && length != "") {
                            ruleArr.push("length(~" + length + ")");
                        }
                        codeData.rule = ruleArr.length == 0 ? "" : "data-rule='" + ruleArr.join(";") + "'";

                        $("#" + $("#code_currentBtnId").val()).attr("data-code", JSON.stringify(codeData))
                        initCodeArea();
                        BJUI.dialog('close', 'code_textareaDialogId');
                    });

                    // 赋值
                    var codeObj = JSON.parse($("#" + $("#code_currentBtnId").val())[0].getAttribute("data-code"));
                    if (codeObj != null) {
                        setCommonCodeData("textareaDialog", codeObj);
                        if (codeObj.required != null && codeObj.required != "") {
                            $("#textareaDialog_required").iCheck('check')
                        }
                        if (codeObj.autoheight != null && codeObj.autoheight != "") {
                            $("#textareaDialog_autoheight").iCheck('check')
                        }
                        $("#textareaDialog_cols").val(codeObj.cols.substring("cols='".length, codeObj.cols.length - 1));
                        $("#textareaDialog_rows").val(codeObj.rows.substring("rows='".length, codeObj.rows.length - 1));
                        var tempRule = codeObj.rule;
                        tempRule = tempRule == null ? "" : tempRule;
                        if (tempRule.indexOf("length") >= 0) {
                            var maxMatch = tempRule.match(/length\(~\d+\)/g);
                            if (maxMatch != null && maxMatch.length > 0) {
                                $("#textareaDialog_length").val(maxMatch[0].match(/\d+/g)[0]);
                            }
                        }

                    }

                }
            })
        };

        // select
        var selectClick = function (e) {
            $("#code_currentBtnId").val(e.currentTarget.id);
            BJUI.dialog({
                id: 'code_selectDialogId',
                title: '下拉选择',
                width: 650,
                height: 320,
                mask: true,
                url: 'html/system/selectDialog.html',
                onLoad: function ($dialog) {
                    // 保存
                    $("#selectDialog_saveBtn").click(function () {
                        var codeData = {};
                        codeData = getCommonCodeData("selectDialog");
                        // 是否多选
                        codeData.multiple = $("#selectDialog_multiple")[0].checked ? " multiple=''" : "";
                        // 固定宽度
                        var width = $("#selectDialog_width").val();
                        codeData.width = width == null || width == "" ? "" : "data-width='" + width + "'";
                        // option
                        var option = $("#selectDialog_option").val();
                        codeData.option = option == null || option == "" ? "" : option;
                        // 验证规则
                        var ruleArr = [];
                        // 必填
                        codeData.required = "";
                        if ($("#selectDialog_required")[0].checked) {
                            ruleArr.push("required");
                            codeData.required = "required";
                        }
                        codeData.rule = ruleArr.length == 0 ? "" : "data-rule='" + ruleArr.join(";") + "'";

                        $("#" + $("#code_currentBtnId").val()).attr("data-code", JSON.stringify(codeData))
                        initCodeArea();
                        BJUI.dialog('close', 'code_selectDialogId');
                    });

                    // 赋值
                    var codeObj = JSON.parse($("#" + $("#code_currentBtnId").val())[0].getAttribute("data-code"));
                    if (codeObj != null) {
                        setCommonCodeData("selectDialog", codeObj);
                        if (codeObj.required != null && codeObj.required != "") {
                            $("#selectDialog_required").iCheck('check')
                        }
                        if (codeObj.multiple != null && codeObj.multiple != "") {
                            $("#selectDialog_multiple").iCheck('check')
                        }
                        $("#selectDialog_width").val(codeObj.width.substring("data-width='".length, codeObj.width.length - 1));
                        $("#selectDialog_option").val(codeObj.option);

                    }

                }
            })
        };

        // 单选框
        var radioClick = function (e) {
            $("#code_currentBtnId").val(e.currentTarget.id);
            BJUI.dialog({
                id: 'code_radioDialogId',
                title: '单选框',
                width: 650,
                height: 250,
                mask: true,
                url: 'html/system/radioDialog.html',
                onLoad: function ($dialog) {
                    // 保存
                    $("#radioDialog_saveBtn").click(function () {
                        var codeData = {};
                        codeData = getCommonCodeData("radioDialog");
                        // 默认选中
                        codeData.checked = $("#radioDialog_checked")[0].checked ? " checked=''" : "";
                        // 单选框对应label
                        var dataLabel = $("#radioDialog_dataLabel").val();
                        codeData.dataLabel = dataLabel == null || dataLabel == "" ? "" : "data-label='" + dataLabel + "'";

                        $("#" + $("#code_currentBtnId").val()).attr("data-code", JSON.stringify(codeData))
                        initCodeArea();
                        BJUI.dialog('close', 'code_radioDialogId');
                    });

                    // 赋值
                    var codeObj = JSON.parse($("#" + $("#code_currentBtnId").val())[0].getAttribute("data-code"));
                    if (codeObj != null) {
                        setCommonCodeData("radioDialog", codeObj);
                        if (codeObj.checked != null && codeObj.checked != "") {
                            $("#radioDialog_checked").iCheck('check')
                        }
                        $("#radioDialog_dataLabel").val(codeObj.dataLabel.substring("data-label='".length, codeObj.dataLabel.length - 1));
                    }
                }
            })
        };

        // 复选框
        var checkboxClick = function (e) {
            $("#code_currentBtnId").val(e.currentTarget.id);
            BJUI.dialog({
                id: 'code_checkboxDialogId',
                title: '复选框',
                width: 650,
                height: 250,
                mask: true,
                url: 'html/system/checkboxDialog.html',
                onLoad: function ($dialog) {
                    // 保存
                    $("#checkboxDialog_saveBtn").click(function () {
                        var codeData = {};
                        codeData = getCommonCodeData("checkboxDialog");
                        // 默认选中
                        codeData.checked = $("#checkboxDialog_checked")[0].checked ? " checked=''" : "";
                        // 单选框对应label
                        var dataLabel = $("#checkboxDialog_dataLabel").val();
                        codeData.dataLabel = dataLabel == null || dataLabel == "" ? "" : "data-label='" + dataLabel + "'";

                        $("#" + $("#code_currentBtnId").val()).attr("data-code", JSON.stringify(codeData))
                        initCodeArea();
                        BJUI.dialog('close', 'code_checkboxDialogId');
                    });

                    // 赋值
                    var codeObj = JSON.parse($("#" + $("#code_currentBtnId").val())[0].getAttribute("data-code"));
                    if (codeObj != null) {
                        setCommonCodeData("checkboxDialog", codeObj);
                        if (codeObj.checked != null && codeObj.checked != "") {
                            $("#checkboxDialog_checked").iCheck('check')
                        }
                        $("#checkboxDialog_dataLabel").val(codeObj.dataLabel.substring("data-label='".length, codeObj.dataLabel.length - 1));
                    }
                }
            })
        };

        // 日期
        var datepickerClick = function (e) {
            $("#code_currentBtnId").val(e.currentTarget.id);
            BJUI.dialog({
                id: 'code_datepickerDialogId',
                title: '日期选择器',
                width: 650,
                height: 300,
                mask: true,
                url: 'html/system/datepickerDialog.html',
                onLoad: function ($dialog) {
                    // 保存
                    $("#datepickerDialog_saveBtn").click(function () {
                        var codeData = {};
                        codeData = getCommonCodeData("datepickerDialog");
                        // 日期格式
                        var pattern = $("#datepickerDialog_pattern").val();
                        codeData.pattern = pattern == null || pattern == "" ? "" : "data-pattern='" + pattern + "'";
                        // 验证规则
                        var ruleArr = [];
                        // 必填
                        codeData.required = "";
                        if ($("#datepickerDialog_required")[0].checked) {
                            ruleArr.push("required");
                            codeData.required = "required";
                        }
                        codeData.rule = ruleArr.length == 0 ? "" : "data-rule='" + ruleArr.join(";") + "'";

                        $("#" + $("#code_currentBtnId").val()).attr("data-code", JSON.stringify(codeData))
                        initCodeArea();
                        BJUI.dialog('close', 'code_datepickerDialogId');
                    });

                    // 赋值
                    var codeObj = JSON.parse($("#" + $("#code_currentBtnId").val())[0].getAttribute("data-code"));
                    if (codeObj != null) {
                        setCommonCodeData("datepickerDialog", codeObj);
                        if (codeObj.required != null && codeObj.required != "") {
                            $("#datepickerDialog_required").iCheck('check')
                        }
                        $("#datepickerDialog_pattern").val(codeObj.pattern.substring("data-pattern='".length, codeObj.pattern.length - 1));
                    }
                }
            })
        };

        // 选中按钮前checkbox
        var dropDivClick = function (e) {
            var cd = e.currentTarget.children;
            for (var i = 0; i < cd.length; i++) {
                if (cd[i].name == "dropDivCheckbox") {
                    var cb = $(cd[i]);
                    if (cb.is(':checked')) {
                        cb.iCheck('uncheck');
                    } else {
                        cb.iCheck('check');
                    }
                }
            }
        };

        // 点击文本框
        $.CurrentNavtab.on("click", ".code_text", textClick);
        // 点击隐藏文本框
        $.CurrentNavtab.on("click", ".code_hideText", hideTextClick);
        // 点击textarea
        $.CurrentNavtab.on("click", ".code_textarea", textareaClick);
        // 点击select
        $.CurrentNavtab.on("click", ".code_select", selectClick);
        // 点击复选框
        $.CurrentNavtab.on("click", ".code_checkbox", checkboxClick);
        // 点击单选框
        $.CurrentNavtab.on("click", ".code_radio", radioClick);
        // 点击时间
        $.CurrentNavtab.on("click", ".code_datepicker", datepickerClick);
        $.CurrentNavtab.on("click", ".drop-div", dropDivClick);
        $.CurrentNavtab.on("click", ".drop-div button", function (e) {
            // 阻止冒泡
            e.stopPropagation();
        });
        $.CurrentNavtab.on("click", ".drop-div input", function (e) {
            // 阻止冒泡
            e.stopPropagation();
        });
        // 复制jsp代码
        new Clipboard('#code_copyBtn');
        // 初始化方法
        initDropFn(0);
        // code插件
        prettyPrint();
    }();
});