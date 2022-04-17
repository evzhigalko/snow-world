<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <form:form action="payment" modelAttribute="orderDetailsDto">
        <h3 style="padding-top: 1rem">Информация по заказу:</h3>
        <h4>Заказ № ${order.orderId}</h4>
        <input type="hidden" name="orderId" value="${order.orderId}">
        <div class="reg-form-input">
            <form:errors path="lastname" cssClass="error"/>
            <form:input path="lastname" id="label-last-name" type="text" class="form-control order-details"
                        placeholder="Фамилия"
                        name="lastname"/>
        </div>
        <div class="reg-form-input">
            <form:errors path="firstname" cssClass="error"/>
            <form:input path="firstname" id="label-first-name" type="text" class="form-control order-details"
                        placeholder="Имя"
                        name="firstname"/>
        </div>
        <div class="reg-form-input">
            <form:errors path="email" cssClass="error"/>
            <form:input path="email" id="label-email" type="text" class="form-control order-details"
                        placeholder="Электронная почта"
                        name="email"/>
        </div>
        <div class="reg-form-input">
            <form:errors path="phoneNumber" cssClass="error"/>
            <form:input path="phoneNumber" id="label-phone_number" type="text" class="form-control order-details"
                        placeholder="Телефонный номер в международном формате"
                        name="phoneNumber"/>
        </div>
        <div style="padding-top: 1rem"><h4>Общая сумма заказа: ${order.totalSum} бел. рублей</h4></div>
        <div class="send-order-button">
            <button class="btn btn-primary" id="send-order">Перейти к оплате</button>
        </div>
    </form:form>
</div>
</body>
</html>
