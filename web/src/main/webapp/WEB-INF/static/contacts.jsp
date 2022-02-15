<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/connect/jstl-connect.jsp" %>
<html>
<head>
    <title>Контакты</title>
    <%@ include file="/WEB-INF/connect/css-connect.jsp" %>
</head>
<body>
<header>
    <%@ include file="/WEB-INF/static/navbar.jsp" %>
</header>
<div class="container">
    <div class="contacts">
        <h3 style="text-align:center;">Наши Контакты</h3>
        <ul>
            <li>
                <h4 class="description-title">Адрес:</h4>
                <div class="description">
                    Республика Беларусь, г. Минск
                </div>
            </li>
            <li>
                <h4 class="description-title">График работы:</h4>
                <div class="description">
                    пн.-вс. с 10:00 до 21:00
                    <br>Без выходных
                </div>
            </li>
            <li>
                <h4 class="description-title">Электронная почта:</h4>
                <div class="description">
                    <a href="email:evgeniy.zhigalko@gmail.com"> evgeniy.zhigalko@gmail.com</a>
                </div>
            </li>
            <li>
                <h4 class="description-title">Связь по телефону:</h4>
                <div class="description">
                    <a href="tel:375291271633"> +375291271633</a>
                </div>
            </li>
        </ul>
    </div>
</div>
</body>
</html>
