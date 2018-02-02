<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="bjui-pageContent">

    <%--${message.content}--%>
    <div class="title" style="text-align:center;padding: 5px;border-bottom: 1px solid #ececec;">
        <div style="font: bold 24px '宋体';">
            ${message.title}
        </div>
        <div style="color: #999;">
            <span>
                <fmt:formatDate value="${message.addTime}" pattern="yyyy年MM月dd日 HH:mm:ss"/>
                ${message.author}
            </span>
        </div>
    </div>
    <div style="padding: 10px 15px;">
        ${message.content}
    </div>
</div>