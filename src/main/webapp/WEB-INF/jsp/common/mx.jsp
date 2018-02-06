<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>MX管理系统</title>
    <meta name="Keywords" content="MX"/>
    <meta name="Description" content="专业管理系统"/>
    <!-- 共通变量定义 -->
    <script src="js/common/constant.js"></script>
    <!-- bootstrap - css -->
    <link href="B-JUI/themes/css/bootstrap.css" rel="stylesheet">
    <!-- core - css -->
    <link href="B-JUI/themes/css/style.css" rel="stylesheet">
    <link href="B-JUI/themes/blue/core.css" id="bjui-link-theme" rel="stylesheet">
    <link href="B-JUI/themes/css/fontsize.css" id="bjui-link-theme" rel="stylesheet">
    <!-- plug - css -->
    <link href="B-JUI/plugins/kindeditor_4.1.11/themes/default/default.css" rel="stylesheet">
    <link href="B-JUI/plugins/colorpicker/css/bootstrap-colorpicker.min.css" rel="stylesheet">
    <link href="B-JUI/plugins/nice-validator-1.0.7/jquery.validator.css" rel="stylesheet">
    <link href="B-JUI/plugins/bootstrapSelect/bootstrap-select.css" rel="stylesheet">
    <link href="B-JUI/plugins/webuploader/webuploader.css" rel="stylesheet">
    <link href="B-JUI/themes/css/FA/css/font-awesome.min.css" rel="stylesheet">
    <!-- Favicons -->
    <link rel="apple-touch-icon-precomposed" href="img/logo.png">
    <link rel="shortcut icon" href="img/logo.png">
    <!--[if lte IE 7]>
    <link href="B-JUI/themes/css/ie7.css" rel="stylesheet">
    <![endif]-->
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lte IE 9]>
    <script src="B-JUI/other/html5shiv.min.js"></script>
    <script src="B-JUI/other/respond.min.js"></script>
    <![endif]-->
    <!-- jquery -->
    <script src="B-JUI/js/jquery-1.11.3.min.js"></script>
    <script src="B-JUI/js/jquery.cookie.js"></script>
    <!--[if lte IE 9]>
    <script src="B-JUI/other/jquery.iframe-transport.js"></script>
    <![endif]-->
    <!-- B-JUI -->
    <script src="B-JUI/js/bjui-all.js"></script>
    <!-- plugins -->
    <!-- swfupload for kindeditor -->
    <script src="B-JUI/plugins/swfupload/swfupload.js"></script>
    <!-- Webuploader -->
    <script src="B-JUI/plugins/webuploader/webuploader.js"></script>
    <!-- kindeditor -->
    <script src="B-JUI/plugins/kindeditor_4.1.11/kindeditor-all-min.js"></script>
    <script src="B-JUI/plugins/kindeditor_4.1.11/lang/zh-CN.js"></script>
    <!-- colorpicker -->
    <script src="B-JUI/plugins/colorpicker/js/bootstrap-colorpicker.min.js"></script>
    <!-- ztree -->
    <script src="B-JUI/plugins/ztree/jquery.ztree.all-3.5.js"></script>
    <!-- nice validate -->
    <script src="B-JUI/plugins/nice-validator-1.0.7/jquery.validator.js"></script>
    <script src="B-JUI/plugins/nice-validator-1.0.7/jquery.validator.themes.js"></script>
    <!-- bootstrap plugins -->
    <script src="B-JUI/plugins/bootstrap.min.js"></script>
    <script src="B-JUI/plugins/bootstrapSelect/bootstrap-select.min.js"></script>
    <script src="B-JUI/plugins/bootstrapSelect/defaults-zh_CN.min.js"></script>
    <!-- icheck -->
    <script src="B-JUI/plugins/icheck/icheck.min.js"></script>
    <!-- HighCharts
    <script src="B-JUI/plugins/highcharts/highcharts.js"></script>
    <script src="B-JUI/plugins/highcharts/highcharts-3d.js"></script>
    <script src="B-JUI/plugins/highcharts/themes/gray.js"></script>
    -->
    <!-- other plugins -->
    <script src="B-JUI/plugins/other/jquery.autosize.js"></script>
    <link href="B-JUI/plugins/uploadify/css/uploadify.css" rel="stylesheet">
    <script src="B-JUI/plugins/uploadify/scripts/jquery.uploadify.min.js"></script>
    <script src="B-JUI/plugins/download/jquery.fileDownload.js"></script>
    <script src="js/common/plugins/sockjs.min.js"></script>

    <script src='js/common/plugins/echart/echarts.js'></script>
    <!--<script src='js/common/plugins/echart/echarts.min.js'></script>-->
    <script src='js/common/plugins/md5.js'></script>
    <script src="js/mx.js"></script>
    <script src="js/common/extend.js"></script>
    <script src="js/common/event.js"></script>
    <link href="css/common.css" rel="stylesheet">
    <script src="js/common/common.js"></script>

    <link href="css/plugins/loading/ball-grid-beat.css" rel="stylesheet">
    <!-- init -->
    <script type="text/javascript">
        //@ sourceURL=mxpage.js
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
        })

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
                websocket = new WebSocket("ws://localhost:8080/websocket");
            } else if ('MozWebSocket' in window) {
                websocket = new MozWebSocket("ws://localhost:8080/websocket");
            } else {
                websocket = new SockJS("http://localhost:8080/sockjs/websocket");
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
    </script>
    <!-- highlight && ZeroClipboard -->
    <link href="assets/prettify.css" rel="stylesheet">
    <script src="assets/prettify.js"></script>
    <link href="assets/ZeroClipboard.css" rel="stylesheet">
    <script src="assets/ZeroClipboard.js"></script>
    <style>
        #bjui-navbar .navbar-header a {
            color: white;
        }
    </style>
</head>
<body>
<!--[if lte IE 7]>
<div id="errorie">
    <div>您还在使用老掉牙的IE，正常使用系统前请升级您的浏览器到 IE8以上版本 <a target="_blank"
                                                 href="http://windows.microsoft.com/zh-cn/internet-explorer/ie-8-worldwide-languages">点击升级</a>&nbsp;&nbsp;强烈建议您更改换浏览器：<a
            href="http://down.tech.sina.com.cn/content/40975.html" target="_blank">谷歌 Chrome</a></div>
</div>
<![endif]-->
<header class="navbar bjui-header" id="bjui-navbar">
    <div class="container_fluid">
        <div class="navbar-header" style="width: 222px;">
            <a class="navbar-brand" href="/" style="color: white; width: 100%; text-align: center;">
                <span style="display: block;padding: 3px;">
                    <img src="img/logo.png" height="38" style="margin-top: -8px;">
                    &nbsp;MX管理系统</span>
            </a>
        </div>
        <nav class="collapse navbar-collapse" id="bjui-navbar-collapse">
            <ul class="nav navbar-nav navbar-left" style="margin-left: -20px;">
                <li class="header-icon">
                    <a id="bjui-sidenav-arrow" data-placement="left" title="隐藏左侧菜单">
                        <i class="fa fa-chevron-circle-left size16"></i>
                    </a>
                </li>
                <li>
                    <a id="bjui-sidenav-btn" data-placement="right" title="显示左侧菜单">
                        <i class="fa fa-bars size16"></i>
                    </a>
                </li>

            </ul>
            <ul class="nav navbar-nav navbar-right" id="bjui-hnav-navbar">
                <li class="active">
                    <a href="frame/getMenu" data-toggle="sidenav" data-id-key="targetid"
                       style="display: none;"></a>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li class="header-list">
                    <a href="#" id="mx_showMessage" class="dropdown-toggle" data-toggle="dropdown" title="查看消息">
                        <i class="fa fa-bell-o size16"></i>
                        <span id="mx_messageCount" class="header-badge"><c:if
                                test="${messageCount != 0}">${messageCount}</c:if></span>
                    </a>
                    <ul class="dropdown-menu" role="menu">
                        <li id="mx_messageList" class="header-list-a">

                        </li>
                        <li class="header-list-bottom">
                            <a id="mx_showMessageTab" href="#">查看全部消息<i class="fa fa-arrow-circle-right"></i></a>
                        </li>
                    </ul>
                </li>
                <li class="header-user">
                    <a href="#" style="height: 40px;padding: 5px 10px;" class="dropdown-toggle" data-toggle="dropdown"
                       title="用户信息">
                        <c:if test='${user.avatar == null}'>
                            <i class="fa fa-user-circle"></i>&nbsp;&nbsp;${user.userName}&nbsp;
                        </c:if>
                        <c:if test='${user.avatar != null}'>
                            <img height="30" weight="30" style="border-radius: 15px;"
                                 src="${user.avatar}">&nbsp;&nbsp;${user.userName}&nbsp;
                        </c:if>
                    </a>
                    <ul class="dropdown-menu" role="menu">
                        <li class="header-user-msg">
                            <c:if test='${user.avatar == null}'>
                                <i class="fa fa-user-circle"></i>
                            </c:if>
                            <c:if test='${user.avatar != null}'>
                                <img height="60" weight="60"
                                     style="border: 3px solid rgba(255,255,255,0.2);border-radius: 30px;"
                                     src="${user.avatar}">
                                <br>
                                <span> ${user.userName} - ${roleName}</span>
                            </c:if>
                        </li>
                        <li style="padding: 10px;">
                            <button type="button" class="btn-default" data-icon="cog" data-toggle="navtab"
                                    data-options="{id:'system-5',url:'system/personalSettings'}">设置
                            </button>
                            <button type="button" onclick="mx_loginOut()" class="btn-default" data-icon="sign-out"
                                    style="float: right;">注销
                            </button>
                        </li>
                    </ul>
                </li>
                <li class="header-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-cogs size16"></i>
                    </a>
                    <ul class="dropdown-menu" role="menu">
                        <li id="bjui-themes" style="width: 100%;">
                            <div style="padding: 23px 4px;">主题</div>
                            <a href="javascript:;" class="theme_purple" data-toggle="theme" data-theme="purple"
                               style="color: #614789;">
                                <i class="fa fa-leaf"></i> 紫罗兰</a>
                            <a href="javascript:;" class="theme_blue" data-toggle="theme" data-theme="blue"
                               style="color: #1f73b6;">
                                <i class="fa fa-cloud"></i> 天空蓝</a>
                            <a href="javascript:;" class="theme_green" data-toggle="theme" data-theme="green"
                               style="color: #429342;">
                                <i class="fa fa-sun-o"></i> 绿草如茵</a>
                        </li>
                        <li>
                            <div style="padding: 8px 4px;">显示</div>
                            <a href="javascript:;" onclick="bjui_index_exchange('container_fluid')" title="全屏">
                                <i class="fa fa-arrows-alt"></i> 全屏
                            </a>
                            <a href="javascript:;" onclick="bjui_index_exchange('container')" title="收缩">
                                <i class="fa fa-compress"></i> 收缩
                            </a>
                        </li>
                        <li id="bjui-fonts">
                            <div style="padding: 8px 4px;">字体</div>
                            <a href="javascript:;" class="bjui-font-c" data-toggle="fonts">
                                <i class="fa fa-font"></i>&nbsp;大</a>
                            <a href="javascript:;" class="bjui-font-d" data-toggle="fonts">
                                <i class="fa fa-font"></i>&nbsp;&nbsp;小</a>
                        </li>
                    </ul>

            </ul>
        </nav>
    </div>
</header>
<div id="bjui-body-box">
    <div class="container_fluid" id="bjui-body">
        <div id="bjui-sidenav-col">
            <div id="bjui-sidenav">
                <%--<div id="bjui-sidenav-arrow" data-toggle="tooltip" data-placement="left" data-title="隐藏左侧菜单">--%>
                <%--<i class="fa fa-long-arrow-left"></i>--%>
                <%--</div>--%>
                <div id="bjui-sidenav-box">

                </div>
            </div>
        </div>
        <div id="bjui-navtab" class="tabsPage">
            <%--<div id="bjui-sidenav-btn" data-toggle="tooltip" data-title="显示左侧菜单" data-placement="right">--%>
            <%--<i class="fa fa-bars"></i>--%>
            <%--</div>--%>
            <div class="tabsPageHeader">
                <div class="tabsPageHeaderContent">
                    <ul class="navtab-tab nav nav-tabs">
                        <li><a href="javascript:;"><span><i class="fa fa-home"></i> 首页</span></a></li>
                    </ul>
                </div>
                <div class="tabsLeft"><i class="fa fa-angle-double-left"></i></div>
                <div class="tabsRight"><i class="fa fa-angle-double-right"></i></div>
                <div class="tabsMore"><i class="fa fa-angle-double-down"></i></div>
            </div>
            <ul class="tabsMoreList">
                <li><a href="javascript:;">首页</a></li>
            </ul>
            <div class="navtab-panel tabsPageContent">
                <div class="navtabPage unitBox">
                    <div class="bjui-pageContent" style="padding: 10px;">
                        <div style="width:22%;float:left;">
                            <div class="small-box bg-aqua">
                                <div class="inner">
                                    <h3>150</h3>

                                    <p>新增用户</p>
                                </div>
                                <div class="icon">
                                    <i class="fa fa-user-plus"></i>
                                </div>
                                <a href="#" class="small-box-footer">查看全部 <i class="fa fa-arrow-circle-right"></i></a>
                            </div>
                        </div>
                        <div style="width:22%;float:left;margin-left: 3%;">
                            <div class="small-box bg-green">
                                <div class="inner">
                                    <h3>50%</h3>

                                    <p>比率</p>
                                </div>
                                <div class="icon">
                                    <i class="fa fa-line-chart"></i>
                                </div>
                                <a href="#" class="small-box-footer">查看全部 <i class="fa fa-arrow-circle-right"></i></a>
                            </div>
                        </div>
                        <div style="width:22%;float:left;margin-left: 3%;">
                            <div class="small-box bg-yellow">
                                <div class="inner">
                                    <h3>50%</h3>
                                    <p>比率</p>
                                </div>
                                <div class="icon">
                                    <i class="fa fa-pie-chart"></i>
                                </div>
                                <a href="#" class="small-box-footer">查看全部 <i class="fa fa-arrow-circle-right"></i></a>
                            </div>
                        </div>
                        <div style="width:22%;float:left;margin-left: 3%;">
                            <div class="small-box bg-red">
                                <div class="inner">
                                    <h3>30</h3>
                                    <p>新增订单</p>
                                </div>
                                <div class="icon">
                                    <i class="fa fa-cart-plus"></i>
                                </div>
                                <a href="#" class="small-box-footer">查看全部 <i class="fa fa-arrow-circle-right"></i></a>
                            </div>
                        </div>

                        <div class="box">
                            <div class="box-header">
                                <i class="fa fa-clipboard"></i>&nbsp;To Do List
                            </div>
                            <div>
                                <ul>
                                    <li>Design a nice theme</li>
                                    <li>Make the theme responsive</li>
                                    <li>Let theme shine like a star</li>
                                    <li>Let theme shine like a star</li>
                                </ul>
                            </div>
                        </div>
                        <div class="box" style="margin-left: 5%">
                            <div class="box-header">
                                <i class="fa fa-clipboard"></i>&nbsp;To Do List
                            </div>
                            <div>
                                <ul>
                                    <li>Design a nice theme</li>
                                    <li>Make the theme responsive</li>
                                    <li>Let theme shine like a star</li>
                                    <li>Let theme shine like a star</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="B-JUI/other/ie10-viewport-bug-workaround.js"></script>
</body>
</html>