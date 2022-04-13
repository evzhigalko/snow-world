<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/connect/jstl-connect.jsp" %>
<html>
<head>
    <title>Платеж успешно проведен</title>
    <%@ include file="/WEB-INF/connect/css-connect.jsp" %>
</head>
<body>
<header>
    <%@ include file="/WEB-INF/jsp/static/navbar.jsp" %>
</header>
<div class="container">
    <h4 style="padding-top: 1rem; color: green">Платеж успешно проведен</h4>
    <div style="padding-top: 2rem"></div>
    <form action="<c:url value="/order/send"/>" method="post">
        <button class="btn btn-primary">Завершить оформление</button>
    </form>
</div>
</body>
</html>
