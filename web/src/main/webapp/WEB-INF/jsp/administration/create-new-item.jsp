<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/connect/jstl-connect.jsp" %>
<html>
<head>
    <title>Добавить новый продукт</title>
    <%@ include file="/WEB-INF/connect/css-connect.jsp" %>
</head>
<body>
<header>
    <%@ include file="/WEB-INF/jsp/static/navbar.jsp"%>
    <h3 align="center">Привет админ!</h3>
</header>
<div class="container">
    <c:choose>
        <c:when test="${item eq 'snowboard'}">
            <%@ include file="/WEB-INF/jsp/administration/new-snowboard.jsp" %>
        </c:when>
        <c:when test="${item eq 'snowboard_boot'}">
            <%@ include file="/WEB-INF/jsp/administration/new-snowboard-boot.jsp" %>
        </c:when>
        <c:when test="${item eq 'snowboard_helmet'}">
            <%@ include file="/WEB-INF/jsp/administration/new-snowboard-helmet.jsp" %>
        </c:when>
    </c:choose>
</div>
</body>
</html>
