<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/connect/jstl-connect.jsp" %>
<html>
<head>
    <title>Оплата</title>
    <%@ include file="/WEB-INF/connect/css-connect.jsp" %>
</head>
<body>
<header>
    <%@ include file="/WEB-INF/jsp/static/navbar.jsp" %>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</header>
<div class="container" style="padding-top: 16px">
    <div><h4>Общая сумма заказа: ${order.totalSum} бел. рублей</h4></div>
    <div style="padding-top: 1rem"><h4>Оплата картой</h4></div>
    <form action="<c:url value="/order/payment/success"/>" method="post">
        <label for="cname">Имя владельца:<br/><input type="text" id="cname" name="cardname"
                                                     placeholder="IVANOV IVAN" required
                                                     oninvalid="this.setCustomValidity('Введите имя владельца')"
                                                     oninput="setCustomValidity('')" minlength="6"
                                                     maxlength="64"><br/></label><br/>
        <label for="ccnum">Номер карты:<br/><input type="tel" id="ccnum" name="cardnumber" minlength="16"
                                                   maxlength="16"
                                                   placeholder="XXXX-XXXX-XXXX-XXXX" pattern="\d*" required
                                                   oninvalid="this.setCustomValidity('В веденном номере карты не хватает цифр')"
                                                   oninput="setCustomValidity('')"><br/></label><br/>
        <label>Срок действия:<br/>
            <input maxlength='5' placeholder="MM/YY" type="text" onkeyup="formatString(event);">
        </label><br/>
        CVV:<br/>
        <input type="password" id="cvv" name="cvv" style="width: 10%"
               placeholder="352"
               minlength="3" maxlength="3"
               required oninvalid="this.setCustomValidity('Введите код CVV')"
               oninput="setCustomValidity('')">
        <div style="padding-top: 1rem">
            <input type="submit" id="register" value="Оплатить" disabled="disabled"
                   class="btn btn-primary"/>
        </div>
    </form>
    <script src="<c:out value="${pageContext.request.contextPath}/assets/order.js"/>"></script>
</div>
</body>
</html>
