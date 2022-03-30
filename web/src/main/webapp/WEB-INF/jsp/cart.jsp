<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/connect/jstl-connect.jsp" %>
<html>
<head>
    <title>Корзина</title>
    <%@ include file="/WEB-INF/connect/css-connect.jsp" %>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<header>
    <%@ include file="/WEB-INF/jsp/static/navbar.jsp" %>
</header>
<div class="container">
    <c:choose>
        <c:when test="${cartItems!=null and !cartItems.isEmpty()}">
            <h3 align="center">Корзина</h3>
            <table id="cart-table" class="cart-table">
                <tr>
                    <th>Товар</th>
                    <th>Фото</th>
                    <th>Производитель</th>
                    <th>Дата начала бронирования</th>
                    <th>Дней бронирования</th>
                    <th>Стоимость в сутки</th>
                    <th>Полная стоимость</th>
                    <th></th>
                </tr>
                <c:forEach items="${cartItems}" var="cartItem">
                    <tr class="cart-table-body">
                        <td><c:out value="${cartItem.productName.name}"/></td>
                        <td><img src="${cartItem.image.imageName}" width="150" height="150" alt="image"></td>
                        <td><c:out value="${cartItem.maker}"/></td>
                        <td>
                            <label for="start"><input type="date" id="start" name="rent-start"
                                                      value="${cart.startReservationDate}"
                                                      min="${cart.startReservationDate}" max="2099-12-31"></label></td>
                        <td><label>
                            <input id="qty" type="number" class="qty" min="1" max="100" value="1" style="width: 30%"/>
                        </label></td>
                        <td>
                            <div id="price" class="price"><c:out value="${cartItem.cost}"/></div>
                        </td>
                        <td class="text-center">
                            <span id="subtotal" class="subtotal"><c:out value="${cartItem.cost}"/></span>
                        </td>
                        <td><div>
                            <a href="<c:url value="/cart/delete/item/${cartItem.id}"/>" class="btn btn btn-danger btn-sm">Удалить</a>
                        </div></td>
                    </tr>
                    <script src="<c:out value="${pageContext.request.contextPath}/assets/cart.js"/>"></script>
                </c:forEach>
            </table>
            <br>
            <h3 class="cart-total-sum" id="total">Сумма итого: ${cart.totalSum} бел. рублей</h3>
        </c:when>
        <c:otherwise>
            <h3 align="center">Корзина пуста</h3>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
