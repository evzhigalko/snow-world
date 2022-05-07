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
        <c:when test="${item eq 'snowboards'}">
            <%@ include file="/WEB-INF/jsp/new-snowboard.jsp" %>
        </c:when>
        <c:when test="${item eq 'snowboard-boots'}">
            <%@ include file="/WEB-INF/jsp/new-snowboard-boot.jsp" %>
        </c:when>
        <c:when test="${item eq 'snowboard-helmets'}">
            <%@ include file="/WEB-INF/jsp/new-snowboard-helmet.jsp" %>
        </c:when>
        <c:when test="${item eq 'ski'}">
            <%@ include file="/WEB-INF/jsp/new-ski.jsp" %>
        </c:when>
        <c:when test="${item eq 'ski-boots'}">
            <%@ include file="/WEB-INF/jsp/new-ski-boot.jsp" %>
        </c:when>
        <c:when test="${item eq 'ski-helmets'}">
            <%@ include file="/WEB-INF/jsp/new-ski-helmet.jsp" %>
        </c:when>
        <c:when test="${item eq 'ski-poles'}">
            <%@ include file="/WEB-INF/jsp/new-ski-pole.jsp" %>
        </c:when>
        <c:otherwise>
            <%@ include file="/WEB-INF/jsp/new-clothes-item.jsp" %>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
