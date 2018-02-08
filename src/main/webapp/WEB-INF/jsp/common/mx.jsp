<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="zh">
<link href="css/plugins/loading/timer.css" rel="stylesheet">
<div id="mx_loadMask" class="loadingmsg" style="z-index:999;height:200px;width: 180px; position: absolute;margin: auto;top: 0;left: 0;right: 0;bottom: 0;">
    <div class="la-timer la-2x" style="color:#f1f1f1;">
        <div></div>
    </div>
</div>
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
    <link rel="apple-touch-icon-precomposed" href="img/logo.ico">
    <link rel="shortcut icon" href="img/logo.ico">
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

    <!-- highlight && ZeroClipboard -->
    <link href="assets/prettify.css" rel="stylesheet">
    <script src="assets/prettify.js"></script>
    <link href="assets/ZeroClipboard.css" rel="stylesheet">
    <script src="assets/ZeroClipboard.js"></script>
    <!-- init -->
    <script src="js/mx.js"></script>
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
                                    data-options="{id:'function_sys-userSeting',url:'system/personalSettings',title:'个人设置'}">设置
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
                <div class="tabsBar" style="width:80px;">
                    <i class="fa fa-home"></i>&nbsp;
                    <i class="fa fa-refresh"></i>&nbsp;
                    <i class="fa fa-times"></i>&nbsp;
                </div>
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