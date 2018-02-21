<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .context-menu-item label {
        font-weight: normal;
    }

    .context-menu-item label:hover {
        color: #fff;
    }

    .index-left button {
        margin: 0 20px 7px 10px;
        width: 100px;
        text-align: left;
    }

    .drop-div {
        height: 30px;
        float: left;
        border: 1px solid #d9e7f2;
        cursor: pointer;
    }

    .left-bar {
        width: 50px;
        word-wrap: break-word;
        float: left;
    }

    .left-bar li {
        border-bottom: 0px solid #eee !important;
    }

    .left-bar li a {
        border-radius: 10px 0px 0px 10px;
        border-bottom: 0px #fff solid !important;
    }

    .w50 {
        padding-left: 10px;
        width: 50% !important;
    }
</style>
<!-- 代码高亮、复制黏贴 -->
<link href="assets/prettify.css" rel="stylesheet">
<script src="assets/prettify.js"></script>
<link href="assets/ZeroClipboard.css" rel="stylesheet">
<script src="js/common/plugins/clipboard.min.js"></script>

<div class="row height100">
    <div class="col-md-2 height100" style="width: 25%;">
        <ul class="nav nav-tabs" role="tablist" style="position: absolute;height: 30px;width: 100%;">
            <li role="presentation" class="active"><a href="#code_form" data-toggle="tab">表单</a></li>
            <li role="presentation"><a href="#code_grid" data-toggle="tab">表格列</a></li>
            <li role="presentation"><a id="code_codeListTab" href="#code_codeListDiv" data-toggle="tab">已存</a></li>
        </ul>
        <div class="tab-content" style="position: absolute;top: 30px;bottom: 0px;padding:0px;">
            <div id="code_form" class="tab-pane active bjui-row col-2">
                <label class="row-label">行：</label>
                <div class="row-input">
                    <input id="code_rowCount" type="text" value="1" data-toggle="spinner" data-min="1"
                           data-max="20" size="5">
                </div>
                <label class="row-label">列：</label>
                <div class="row-input">
                    <input id="code_colCount" type="text" value="2" data-toggle="spinner"
                           data-min="1" data-max="10" size="5">
                </div>
                <div class="row-input w50">
                    <button id="code_text" class="btn-default code_text" type="button" draggable="true"
                            ondragstart="code_drag(event)" data-icon="pencil">文本框
                    </button>
                </div>
                <div class="row-input w50">
                    <button id="code_hideText" class="btn-default code_hideText" type="button" draggable="true"
                            ondragstart="code_drag(event)" data-icon="eye-slash">隐藏文本框
                    </button>
                </div>
                <div class="row-input w50">
                    <button id="code_textarea" class="btn-default code_textarea" type="button" draggable="true"
                            ondragstart="code_drag(event)" data-icon="pencil">textarea
                    </button>
                </div>
                <div class="row-input w50">
                    <button id="code_select" class="btn-default code_select" type="button" draggable="true"
                            ondragstart="code_drag(event)" data-icon="list">下拉列表
                    </button>
                </div>
                <div class="row-input w50">
                    <button id="code_radio" class="btn-default code_radio" type="button" draggable="true"
                            ondragstart="code_drag(event)" data-icon="dot-circle-o">单选框
                    </button>
                </div>
                <div class="row-input w50">
                    <button id="code_checkbox" class="btn-default code_checkbox" type="button" draggable="true"
                            ondragstart="code_drag(event)" data-icon="check-square">复选框
                    </button>
                </div>
                <div class="row-input w50">
                    <button id="code_datepicker" class="btn-default code_datepicker" type="button" draggable="true"
                            ondragstart="code_drag(event)" data-icon="calendar">日期
                    </button>
                </div>
                <div class="row-input w50">
                    <button id="code_findGrid" class="btn-default code_findGrid" type="button" draggable="true"
                            ondragstart="code_drag(event)" data-icon="table">FindGrid
                    </button>
                </div>
                <div class="row-input w50">
                    <button id="code_spinner" class="btn-default code_spinner" type="button" draggable="true"
                            ondragstart="code_drag(event)" data-icon="sort">Spinner
                    </button>
                </div>
            </div>

            <div id="code_grid" class="tab-pane">
                456
            </div>
            <div id="code_codeListDiv" class="tab-pane panel-block" style="border:0px; margin-right:0px;">
                <ul id="code_codeList">
                </ul>
            </div>
        </div>

    </div>
    <div class="col-md-10 height100 flex-column"
         style="width: 73%;height: 100%;position: absolute;right: 15px;">
        <div style="height: 30px;">
            &nbsp;标题：<input id="code_title" type="text" style="width: 180px;" placeholder="标题">
            <div class="btn-group" role="group" style="float: right;right: 5px;">
                <button id="code_previewBtn" type="button" class="btn-blue" data-icon="eye">预览</button>
                <button id="code_codeBtn" type="button" class="btn-blue" data-icon="align-justify">代码</button>
                <button id="code_refreshBtn" type="button" class="btn-blue" data-icon="refresh">刷新</button>
                <button id="code_saveBtn" type="button" class="btn-default" data-icon="save">保存</button>
                <button id="code_deleteBtn" type="button" class="btn-red" data-icon="times">删除</button>
                <button id="code_clearBtn" type="button" class="btn-red" data-icon="times">清空</button>
            </div>
        </div>
        <div style="border-radius: 6px;border: 1px solid #ddd;position:  absolute;width:  100%;position: absolute;top: 30px;bottom: 0px;padding: 0px;">

            <div style="width: 100%; overflow: auto; max-height: 60%;min-height: 30%;">
                <button id="code_copyBtn" data-clipboard-target="#code_codeArea" type="button"
                        style="position: absolute;right: 30px;" class="btn-default"
                        data-icon="copy" title="复制代码">复制
                </button>
                <pre id="code_codeArea" class="prettyprint lang-html linenums">
                </pre>
            </div>
            <input id="code_codeId" type="hidden">
            <input id="code_currentBtnId" type="hidden">
            <div id="code_dropDivId" style="padding: 30px; overflow: auto;max-height: 40%;">

            </div>
        </div>
    </div>
</div>

<script src="js/system/code.js"></script>