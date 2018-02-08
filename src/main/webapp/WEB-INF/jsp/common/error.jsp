<%@ page language="java" import="java.util.*" isErrorPage="true" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    int statusCode = response.getStatus();
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="<%=basePath%>">
    <title>HTTP - <%=statusCode%>
    </title>
</head>
<body>
<div style="text-align:center;height:350px;width: 300px; position: absolute;margin: auto;top: 0;left: 0;right: 0;bottom: 0;">
    <!-- 根据网页状态码，自动匹配错误页面 -->
    <div>
        <img src="img/common/<%=statusCode%>.png"/>
    </div>
    <div>
        <span style="font-size: 90px;color: #505050;text-shadow: 5px 5px 5px #C8C8C8;"><%=statusCode%></span>
    </div>
    <div>
        <span style="font-size: 15px;color: #303030;">
        <c:choose>
            <c:when test="${statusCode == 403}">
                非常抱歉，您的访问被拒绝！
            </c:when>
            <c:when test="${statusCode == 404}">
                非常抱歉，您访问的页面不存在！
            </c:when>
            <c:when test="${statusCode == 405}">
                非常抱歉，您的请求失败！
            </c:when>
            <c:when test="${statusCode == 500}">
                非常抱歉，您访问的页面不存在！
            </c:when>
            <c:otherwise>
                非常抱歉，系统出现未知异常！
            </c:otherwise>
        </c:choose>
        </span>
    </div>
    <div style="margin-top: 30px;font-size: 15px;color: red;font-weight: bold">
        <a href="javascript:history.go(-1)">返回上一级页面</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
        <a href="<%=path%>">返回首页</a>
    </div>
</div>

</body>
</html>