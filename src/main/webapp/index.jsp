<%@ page language="java" import="java.util.*" isErrorPage="true" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="Keywords" content="云度,新能源,云度新能源">
    <meta name="description" content="福建省汽车工业集团云度新能源汽车有限公司，由福建省汽车工业集团有限公司、莆田市国有资产投资有限公司、自然人以及福建海源自动化机械股份有限公司，四方共同出资的新能源汽车产业发展平台，
主要从事新能源汽车及汽车零部件的研发、生产、加工、销售和咨询服务。">
    <title>云度</title>
    <base href="<%=basePath%>">
    <%@include file="/WEB-INF/jsp/common/includeLibrary.jsp"%>
    <%@include file="/WEB-INF/jsp/common/includeCommon.jsp"%>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/library/fullSlide.css"/>
    <script type="text/javascript" src="<%=basePath%>js/common/fullSlide.js"></script>
</head>
<body>
    <%@include file="/WEB-INF/jsp/common/header.jsp"%>
    
    <%@include file="/WEB-INF/jsp/common/fullSlide.jsp"%>
    
    <div style="width: 100%;height:850px; border: 1px solid red;">
        <div style="width: 1080px; border: 1px solid red;margin: 0 auto;">
            <%@include file="/WEB-INF/jsp/common/leftBar.jsp"%>
        </div>
    </div>
    <%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>