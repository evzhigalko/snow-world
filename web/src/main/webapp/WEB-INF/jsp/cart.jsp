<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/connect/jstl-connect.jsp"%>
<html>
<head>
    <title>Корзина</title>
  <%@ include file="/WEB-INF/connect/css-connect.jsp" %>
</head>
<body>
<header>
    <%@ include file="/WEB-INF/jsp/static/navbar.jsp"%>
</header>
<c:choose>
    <c:when test="${cartItems!=null and !cartItems.isEmpty()}">
        <c:forEach items="${cartItems}" var="cartItem">

        </c:forEach>
    </c:when>
    <c:otherwise>
        <h3 align="center">Корзина пуста</h3>
    </c:otherwise>
</c:choose>
</body>
</html>
