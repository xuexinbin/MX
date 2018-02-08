//@ sourceURL=mx.js
$(function () {
    BJUI.init({
        JSPATH: 'B-JUI/',         //[可选]框架路径
        PLUGINPATH: 'B-JUI/plugins/', //[可选]插件路径
        loginInfo: {url: 'login_timeout.html', title: '登录', width: 440, height: 240}, // 会话超时后弹出登录对话框
        statusCode: {ok: 200, error: 300, timeout: 301}, //[可选]
        ajaxTimeout: 300000, //[可选]全局Ajax请求超时时间(毫秒)
        alertTimeout: 3000,  //[可选]信息提示[info/correct]自动关闭延时(毫秒)
        pageInfo: {
            total: 'totalRow',
            pageCurrent: 'pageCurrent',
            pageSize: 'pageSize',
            orderField: 'orderField',
            orderDirection: 'orderDirection'
        }, //[可选]分页参数
        keys: {statusCode: 'statusCode', message: 'message'}, //[可选]
        ui: {
            sidenavWidth: 220,
            showSlidebar: true, //[可选]左侧导航栏锁定/隐藏
            overwriteHomeTab: false //[可选]当打开一个未定义id的navtab时，是否可以覆盖主navtab(我的主页)
        },
        debug: true,    // [可选]调试模式 [true|false，默认false]
        theme: 'blue' // 若有Cookie['bjui_theme'],优先选择Cookie['bjui_theme']。皮肤[五种皮肤:default, orange, purple, blue, red, green]
    });

    $("#mx_showMessageTab").click(function (e) {
        BJUI.navtab({
            id: 'function_sys-message',
            url: '/system/systemMessage',
            title: '系统消息'
        })
    });
    $("#mx_showMessage").click(function (e) {
        $("#mx_messageList").insertLoading("ball-grid-beat");
        BJUI.ajax('doajax', {
            url: 'frame/getUnreadMessage',
            loadingmask: false,
            okCallback: function (res, options) {
                var messageList = res.messageList;
                var str = "";
                for (var i = 0; i < messageList.length; i++) {
                    str += '<a href="#" class="header-message" data-id="' + messageList[i].id + '"  data-userMessageId="' + messageList[i].userMessageId + '" data-title="' + messageList[i].title + '">';
                    str += messageList[i].type == 0 ? '<i class="fa fa-bell-o color-blue"></i>&nbsp;' : '<i class="fa fa-envelope color-darkgreen"></i>&nbsp;';
                    str += messageList[i].top == 1 ? '<span class="tabletag-prefix bgblue">置顶</span>&nbsp;' : '';
                    str += messageList[i].level == 1 ? '<span class="tabletag-prefix bgred">紧急</span>&nbsp;' : '';
                    str += messageList[i].important == 1 ? '<span class="tabletag-prefix bgred">重要</span>&nbsp;' : '';
                    str += messageList[i].title;
                    str += '</a>';
                }
                $("#mx_messageList").html(str);

                // 消息点击事件
                $(".header-message").click(function (e) {
                    var messageElm = $(this);
                    var id = messageElm.attr("data-id");
                    var userMessageId = messageElm.attr("data-userMessageId");
                    // 打开消息页
                    BJUI.navtab({
                        id: 'message_' + id,
                        url: 'system/systemMessage/showMessage',
                        title: messageElm.attr("data-title"),
                        icon: 'newspaper-o',
                        data: {
                            messageId: id
                        }
                    });
                    // 已读消息
                    BJUI.ajax('doajax', {
                        url: 'system/systemMessage/readMessage',
                        data: {
                            id: userMessageId
                        },
                        loadingmask: false,
                        okCallback: function (res, options) {
                            // 未读数 -1
                            var countElm = $("#mx_messageCount");
                            var newCount = parseInt(countElm.text()) - 1;
                            countElm.text(newCount == 0 ? "" : newCount);
                        }
                    });

                });
            }
        });
    });

    $("#mx_loadMask").fadeOut();
})


var homeRefreshFN = function () {
    console.log("首页刷新");
}

/*window.onbeforeunload = function(){
    return "确定要关闭本系统 ?";
}*/

//菜单-事件-zTree
function MainMenuClick(event, treeId, treeNode) {
    if (treeNode.target && treeNode.target == 'dialog' || treeNode.target == 'navtab')
        event.preventDefault()
    if (treeNode.isParent) {
        var zTree = $.fn.zTree.getZTreeObj(treeId)
        zTree.expandNode(treeNode)
        return
    }

    if (treeNode.target && treeNode.target == 'dialog')
        $(event.target).dialog({id: treeNode.targetid, url: treeNode.url, title: treeNode.name})
    else if (treeNode.target && treeNode.target == 'navtab')
        $(event.target).navtab({
            id: treeNode.targetid,
            url: treeNode.url,
            title: treeNode.name,
            fresh: treeNode.fresh,
            external: treeNode.external
        })
}

// 满屏开关
function bjui_index_exchange(bjui_index_container) {
    $('#bjui-top').find('> div').attr('class', bjui_index_container)
    $('#bjui-navbar').find('> div').attr('class', bjui_index_container)
    $('#bjui-body-box').find('> div').attr('class', bjui_index_container)
}

// 注销登录
function mx_loginOut() {
    window.location.href = "/login";
    BJUI.ajax('doajax', {
        url: 'frame/loginOut',
        loadingmask: false,
        okCallback: function (res, options) {
        }
    });
}

// ---- 全局websocket start -----
// 防止建立多个连接
var websocket = this.websocket;
if (websocket == null) {
    // 判断是否支持 WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://"+window.location.host+"/websocket");
    } else if ('MozWebSocket' in window) {
        websocket = new MozWebSocket("ws://"+window.location.host+"/websocket");
    } else {
        websocket = new SockJS("http://"+window.location.host+"/sockjs/websocket");
    }
}
// 打开时
websocket.onopen = function (event) {
    console.log("  websocket.onopen  ");
};
// 处理消息时
websocket.onmessage = function (event) {
    if (event.data == null || event.data == "") {
        return;
    }
    // 未读消息+1
    var countElm = $("#mx_messageCount");
    var newCount = parseInt(countElm.text() == "" ? "0" : countElm.text()) + 1;
    countElm.text(newCount);
};

websocket.onerror = function (event) {
    console.log("  websocket.onerror  ");
};

websocket.onclose = function (event) {
    console.log("  websocket.onclose  ");
};
// ---- 全局websocket end -----