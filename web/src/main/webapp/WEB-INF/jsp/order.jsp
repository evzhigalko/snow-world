<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/connect/jstl-connect.jsp" %>
<html>
<head>
    <title>Оформление заказа</title>
    <%@ include file="/WEB-INF/connect/css-connect.jsp" %>
</head>
<body>
<header>
    <%@ include file="/WEB-INF/jsp/static/navbar.jsp" %>
</header>
<div class="container" style="padding-left: 6rem; padding-top: 2rem;}">
    <form action="<c:url value="/order/send"/>" method="post">
        <h3 style="padding-top: 1rem">Информация по заказу:</h3>
        <h4>Заказ № ${order.orderId}</h4>
        <input type="hidden" name="orderId" value="${order.orderId}">
        <label for="fname"><i class="fa fa-user"></i>Фамилия Имя</label>
        <p class="fname"><input type="text" id="fname" name="fullName" placeholder="Фамилия Имя"></p>
        <label for="email"><i class="fa fa-envelope"></i>Электронный адрес</label>
        <p><input type="text" id="email" name="email" placeholder="email@email.com"></p>
        <label for="phoneNumber">Номер телефона: </label>
        <p><input type="text" id="phoneNumber" name="phoneNumber" placeholder="+375291234567">
        <div><h4>Общая сумма заказа: ${order.totalSum} бел. рублей</h4></div>
        <div class="send-order-button"><button class="btn btn-primary">Отправить</button></div>
    </form>
</div>
</body>
</html>
