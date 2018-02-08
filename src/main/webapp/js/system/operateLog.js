//@ sourceURL=operateLog.js
$(function () {
    void function () {
        // 刚进入画面pointer为0
        var pointer = 0;
        var str = ""
        var loadLog = function () {
            BJUI.ajax('doajax', {
                url: 'system/monitor/getOperateLog',
                data: {
                    date: $("#operateLog_date").val(),
                    pointerTemp: pointer
                },
                loadingmask: true,
                okCallback: function (res, options) {
                    str += res.log
                    $("#operateLog_log").html(str);
                    // 重新渲染代码区
                    $('#operateLog_log').removeClass('prettyprinted');
                    prettyPrint();
                    pointer = 1;
                }
            });
        }

        $("#operateLog_searchBtn").click(function () {
            loadLog();
        });
    }();
});