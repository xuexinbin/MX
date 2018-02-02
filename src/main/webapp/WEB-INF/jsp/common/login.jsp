<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN" style="height: 100%;">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>MX管理系统</title>
    <link href="B-JUI/themes/css/bootstrap.css" rel="stylesheet">
    <link href="css/plugins/loading/line-scale.css" rel="stylesheet">
    <script src="B-JUI/js/jquery-1.11.3.min.js"></script>
    <script src="B-JUI/js/bjui-all.js"></script>
    <script type='text/javascript' src='js/common/plugins/md5.js' charset='utf-8'></script>
    <!-- title图片 -->
    <link rel="shortcut icon" href="img/logo.png">
    <style>
        .header {
            height: 60px;
            width: 100%;
            border-bottom: 1px solid #ddd;
            background-color: #f5f5f5;
        }

        .header-logo {
            margin: 5px 15px;
            float: left;
        }

        .header-logo a {
            display: block;
            height: 50px;
            width: 50px;
            background-size: 50px 50px;
            background-image: url(../../img/logo.png);
            background-repeat: no-repeat;
        }

        .header-welcome {
            height: 100%;
            border-left: 1px solid #d5d4d4;
            float: left;
        }

        .header-welcome span {
            font-size: 30px;
            color: #606060;
            margin-top: 10px;
            margin-left: 20px;
            display: block;
        }

        .content {
            height: calc(100% - 100px);
            z-index: 5;
        }

        .content-panel {
            margin-top: 100px;
            width: 300px;
            float: right;
            margin-right: 10%;
        }

        .content-panel-h {
            text-align: left;
        }

        .content-msg span {
            color: red;
        }

        .content-form-submit {
            width: 100%;
        }

        .content-form-bottom {
            margin-top: 40px;
            text-align: right;
        }

        .content-form-bottom a {
            color: #225591;
        }

        .content-form-bottom span {
            color: #bfbfbf;
            margin: 0 10px;
        }

        .bottom-content {
            width: 100%;
            height: 40px;
            text-align: center;
            background-color: #f5f5f5;
            border-top: 2px solid #ddd;
            padding: 8px;
        }

        .bottom-content a {
            color: #225591;
        }

        .bottom-content span {
            color: #bfbfbf;
            margin: 0 10px;
        }

        #login_mask {
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background-color: rgba(0, 0, 0, 0.2);
            display: none;
            z-index: 100;
        }

        .loadingmsg {
            border-radius: 5px;
            width: 240px;
            height: 50px;
            background-color: rgba(238, 235, 235, 0.8);
            text-align: center;
            margin: auto;
            color: rgb(58, 137, 205);;
            padding: 10px;
        }

        .show {
            display: flex !important;
        }

    </style>
</head>
<body style="height: 100%;">

<div class="header">
    <div class="header-logo">
        <a href="<%=path%>"></a>
    </div>
    <div class="header-welcome">
        <span>欢迎登录·MX管理系统</span>
    </div>
</div>

<div id="login_loginbg" class="content">
    <div class="panel panel-default content-panel">
        <div class="panel-heading content-panel-h">
            <h3 class="panel-title">账号登录</h3>
        </div>
        <div class="panel-body">
            <div id="login_msg" style="height: 20px;width: 100%;color: #a94442;font-weight: bold;">

            </div>
            <div class="input-group" style="margin-top: 10px;">
                <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                <input id="login_userName" name="login_userName" type="text" class="form-control"
                       value="admin" placeholder="请输入用户名">
            </div>
            <div class="input-group" style="margin-top: 18px;">
                <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                <input id="login_password" name="login_password" type="password" class="form-control"
                       value=1 placeholder="请输入密码">
            </div>
            <div style="margin-top: 25px;">
                <button id="login_login" class="btn btn-primary content-form-submit ">登录</button>
            </div>
            <div class="content-form-bottom">
                <a href="" target="_blank">忘记密码？</a>
                <span>|</span>
                <a href="" target="_blank">使用说明</a>
            </div>
        </div>
    </div>
</div>
<div class="bottom-content">
    <a href="" target="_blank">关于MX管理系统</a>
    <span>|</span>
    <a href="" target="_blank">联系我们 </a>
    <span>|&nbsp;&nbsp;&nbsp;Copyright © 2016 All Rights Reserved by MX </span>
</div>
<div id="login_mask">
    <div class="loadingmsg">
        <div id="login_loginMsg" style="float: left;font-size: 18px;margin-top: 3px;">正在登录，请稍后</div>&nbsp;&nbsp;
        <div class="la-line-scale" style="float: left">
            <div></div>
            <div></div>
            <div></div>
            <div></div>
            <div></div>
        </div>
    </div>
</div>
</div>
</body>
</html>
<script>
    $(function () {
        // 15张背景图随机取
        var bg = Math.floor(Math.random() * 15 + 1);
        $('#login_loginbg').css('background', 'url(img/loginbg/loginbg_' + bg + '.jpg)');
        $('#login_loginbg').css('background-size', '100% 100%');
        $('#login_loginbg').css('background-color', 'black');

        $("#login_login").click(function () {
            var mask = $("#login_mask");
            mask.addClass("show");

            var username = $("#login_userName").val();
            var pwd = $("#login_password").val();
            if (username == "" || pwd == "") {
                mask.removeClass("show");
                $("#login_msg").html("账号或密码不能为空！");
                return;
            }
            $.ajax({
                url: "frame/logining",
                type: "POST",
                data: {
                    userName: username,
                    password: hex_md5(pwd)
                },
                success: function (result) {
                    if (result.success) {
                        $("#login_loginMsg").html("登录成功，正在跳转");
                        localStorage.setItem("user", result.data == null ? "" : JSON.stringify(result.data.user));
                        localStorage.setItem("functions", result.data == null ? "" : JSON.stringify(result.data.functions));
                        window.location.href = "/admin";
                    } else {
                        mask.removeClass("show");
                        $("#login_msg").html(result.msg);
                    }
                },
                error: function (req, msg, obj) {
                    mask.removeClass("show");
                    $("#login_msg").html("系统出现未知异常！");
                }
            });
        });
    })
</script>