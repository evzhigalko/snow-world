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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</header>
<div class="container" style="padding-left: 6rem; padding-top: 2rem;}">
    <form action="<c:url value="/order/new/payment"/>" method="post">
        <h3 style="padding-top: 1rem">Информация по заказу:</h3>
        <h4>Заказ № ${order.orderId}</h4>
        <input type="hidden" name="orderId" value="${order.orderId}">
        <label for="lname">Фамилия</label>
        <p class="lname"><input type="text" id="lname" name="lastname" placeholder="Фамилия"
                                required oninvalid="this.setCustomValidity('Введите фамилию')"
                                oninput="setCustomValidity('')"></p>
        <label for="fname">Имя</label>
        <p class="fname"><input type="text" id="fname" name="firstname" placeholder="Имя"
                                required oninvalid="this.setCustomValidity('Введите имя')"
                                oninput="setCustomValidity('')"></p>
        <label for="email">Электронный адрес</label>
        <p><input type="text" id="email" name="email" placeholder="email@email.com"
                  required oninvalid="this.setCustomValidity('Введите электронный адрес')"
                  oninput="setCustomValidity('')"></p>
        <label for="phoneNumber">Номер телефона: </label>
        <p><input type="text" id="phoneNumber" name="phoneNumber" placeholder="+375291234567"
                  required oninvalid="this.setCustomValidity('Введите телефонный номер')"
                  oninput="setCustomValidity('')">
        <div><h4>Общая сумма заказа: ${order.totalSum} бел. рублей</h4></div>
        <div class="send-order-button">
            <button class="btn btn-primary" id="send-order">Перейти к оплате</button>
        </div>
    </form>
</div>
</body>
</html>
