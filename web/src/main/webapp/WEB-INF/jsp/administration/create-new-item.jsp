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
        <c:when test="${item eq 'ski'}">
            <%@ include file="/WEB-INF/jsp/administration/new-ski.jsp" %>
        </c:when>
        <c:when test="${item eq 'ski_boot'}">
            <%@ include file="/WEB-INF/jsp/administration/new-ski-boot.jsp" %>
        </c:when>
        <c:when test="${item eq 'ski_helmet'}">
            <%@ include file="/WEB-INF/jsp/administration/new-ski-helmet.jsp" %>
        </c:when>
        <c:when test="${item eq 'ski_pole'}">
            <%@ include file="/WEB-INF/jsp/administration/new-ski-pole.jsp" %>
        </c:when>
        <c:otherwise>
            <%@ include file="/WEB-INF/jsp/administration/new-clothes-item.jsp" %>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
