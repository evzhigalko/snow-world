<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/connect/jstl-connect.jsp" %>
<html>
<head>
    <title>Заказ успешно отправлен</title>
    <%@ include file="/WEB-INF/connect/css-connect.jsp" %>
</head>
<body>
<header>
    <%@ include file="/WEB-INF/jsp/static/navbar.jsp" %>
</header>
<div class="container">
    <h4 style="padding-top: 1rem">Ваш заказ успешно принят!</h4>
    <div style="padding-top: 2rem"></div>
    <a class="btn btn-primary" href="<c:url value="/index.jsp"/>">Вернуться на главную страницу</a>
</div>
</body>
</html>
