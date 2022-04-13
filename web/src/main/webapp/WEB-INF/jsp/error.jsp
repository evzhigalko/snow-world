<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/connect/jstl-connect.jsp" %>
<html>
<head>
    <title>Ошибка</title>
    <%@ include file="/WEB-INF/connect/css-connect.jsp" %>
</head>
<body>
<header>
    <%@ include file="/WEB-INF/jsp/static/navbar.jsp" %>
</header>
<div class="container">
    <h4>
        <p>Что-то пошло не так...</p>
        <p>Пожалуйста, повторите попытку позже...</p>
    </h4>
    <div>
        <a class="btn btn-primary" href="<c:url value="/index.jsp"/>">Вернуться на главную страницу</a>
    </div>
</div>
</body>
</html>
