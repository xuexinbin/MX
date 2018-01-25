<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script src="js/common/common.js"></script>
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
    <!-- HighCharts -->
    <script src="B-JUI/plugins/highcharts/highcharts.js"></script>
    <script src="B-JUI/plugins/highcharts/highcharts-3d.js"></script>
    <script src="B-JUI/plugins/highcharts/themes/gray.js"></script>
    <!-- other plugins -->
    <script src="B-JUI/plugins/other/jquery.autosize.js"></script>
    <link href="B-JUI/plugins/uploadify/css/uploadify.css" rel="stylesheet">
    <script src="B-JUI/plugins/uploadify/scripts/jquery.uploadify.min.js"></script>
    <script src="B-JUI/plugins/download/jquery.fileDownload.js"></script>
    <script src='js/common/plugins/md5.js'></script>

    <script src="js/mx.js"></script>
    <script src="js/common/extend.js"></script>
    <script src="js/common/event.js"></script>
    <link href="css/common.css" rel="stylesheet">
    <!-- init -->
    <script type="text/javascript">
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
        var bjui_index_container = 'container_fluid';
        function bjui_index_exchange() {
            bjui_index_container = bjui_index_container == 'container_fluid' ? 'container' : 'container_fluid'

            $('#bjui-top').find('> div').attr('class', bjui_index_container)
            $('#bjui-navbar').find('> div').attr('class', bjui_index_container)
            $('#bjui-body-box').find('> div').attr('class', bjui_index_container)
        }

        function mx_loginOut() {
            window.location.href = "/login";
            BJUI.ajax('doajax', {
                url: 'frame/loginOut',
                loadingmask: false,
                okCallback: function (res, options) {}
            });
        }

    </script>
    <!-- highlight && ZeroClipboard -->
    <link href="assets/prettify.css" rel="stylesheet">
    <script src="assets/prettify.js"></script>
    <link href="assets/ZeroClipboard.css" rel="stylesheet">
    <script src="assets/ZeroClipboard.js"></script>
    <style>
        #bjui-navbar .navbar-header a{color: white;}
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
                <span style="display: block;padding: 3px;"><img src="img/logo.png" height="38"
                                                                style="margin-top: -8px;">&nbsp;MX管理系统</span>
            </a>
        </div>
        <nav class="collapse navbar-collapse" id="bjui-navbar-collapse" style="overflow: visible;">
            <ul class="nav navbar-nav navbar-right" id="bjui-hnav-navbar">
                <li class="active">
                    <a href="json/menu.json" data-toggle="sidenav" data-id-key="targetid"
                       style="display: none;"></a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">

                <li style="padding: 10px 2px;color: white;">&nbsp;<i class="fa fa-user-circle"></i>&nbsp;&nbsp;MX&nbsp;&nbsp;
                </li>
                <li style="padding: 10px 0px; color: white;">|</li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle theme" data-toggle="dropdown" title="切换皮肤">
                        <i class="fa fa-tree"></i>&nbsp;主题&nbsp;<i class="fa fa-caret-down" style="color: black;"></i>
                    </a>
                    <ul class="dropdown-menu" role="menu" id="bjui-themes">
                        <li>
                            <a href="javascript:;" class="theme_purple" data-toggle="theme" data-theme="purple">&nbsp;<i
                                    class="fa fa-leaf"></i> 紫罗兰</a></li>
                        <li>
                            <a href="javascript:;" class="theme_blue" data-toggle="theme"
                               data-theme="blue">&nbsp;<i class="fa fa-cloud"></i> 天空蓝</a></li>
                        <li><a href="javascript:;" class="theme_green" data-toggle="theme" data-theme="green">&nbsp;<i
                                class="fa fa-sun-o"></i> 绿草如茵</a></li>
                    </ul>
                </li>
                <li style="padding: 10px 0px; color: white;">|</li>
                <li><a>&nbsp;<i class="fa fa-cog"></i>&nbsp;设置&nbsp;<i
                        class="fa fa-caret-down" style="color: black;"></i></a></li>
                <li style="padding: 10px 0px; color: white;">|</li>
                <li><a href="javascript:;" onclick="mx_loginOut()" title="退出登录">&nbsp;<i class="fa fa-sign-out"></i>注销</a></li>
                <li style="padding: 10px 0px; color: white;">|</li>
                <li><a href="javascript:;" onclick="bjui_index_exchange()" title="横向收缩/充满屏幕"><i
                        class="fa fa-exchange"></i></a></li>
            </ul>
        </nav>
    </div>
</header>
<div id="bjui-body-box">
    <div class="container_fluid" id="bjui-body">
        <div id="bjui-sidenav-col">
            <div id="bjui-sidenav">
                <div id="bjui-sidenav-arrow" data-toggle="tooltip" data-placement="left" data-title="隐藏左侧菜单">
                    <i class="fa fa-long-arrow-left"></i>
                </div>
                <div id="bjui-sidenav-box">

                </div>
            </div>
        </div>
        <div id="bjui-navtab" class="tabsPage">
            <div id="bjui-sidenav-btn" data-toggle="tooltip" data-title="显示左侧菜单" data-placement="right">
                <i class="fa fa-bars"></i>
            </div>
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
                    <div class="bjui-pageContent">
                        <div class="highlight">
                                <pre class="prettyprint">
------------------------
BJUI 更新至 V1.31
------------------------
[修复] datagrid参数templateWidth、dialogFilterW为0时默认为启用；修复排序bug；新增字段参数itemattr，为items参数指定key/value；新增方法filter，用于数据筛选。
[修复] 分页组件。
[更新] ajaxform、ajaxsearch新增参数validate，是否验证标记。
[更新] 验证插件nice validate更新至1.0.7。
[更新] 图标字体Font Awesome更新至4.7.0。
[调整] CSS细微调整。
------------------------

　　　　　　2016-11-01 by.萧克南
                                </pre>
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