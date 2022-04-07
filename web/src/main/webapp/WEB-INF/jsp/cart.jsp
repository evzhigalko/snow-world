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
<div class="container" style="width: 60%">
    <c:choose>
        <c:when test="${cartItems!=null and !cartItems.isEmpty()}">
            <h3 align="center">Корзина</h3>
            <table id="cart-table" class="cart-table">
                <tr>
                    <th>Товар</th>
                    <th>Фото</th>
                    <th>Производитель</th>
                    <th>Стоимость в сутки</th>
                    <th></th>
                </tr>
                <c:forEach items="${cartItems}" var="cartItem">
                    <tr class="cart-table-body">
                        <td><c:out value="${cartItem.productName.name}"/></td>
                        <td><img src="${cartItem.image.imageName}" width="150" height="150" alt="image"></td>
                        <td><c:out value="${cartItem.maker}"/></td>
                        <td>
                            <div id="price" class="price">${cartItem.cost}</div>
                        </td>
                        <td>
                            <div>
                                <a href="<c:url value="/cart/delete/item/${cartItem.id}"/>"
                                   class="btn btn btn-danger btn-sm">Удалить</a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <form action="<c:url value="/order/create/new"/>" method="post">
                <input type="hidden" value="${cartItems}" name="items">
                <div class="start-reservation">
                    <label for="startReservationDate">Дата начала бронирования: <input type="date"
                                                                                       id="startReservationDate"
                                                                                       name="startReservationDate"
                                                                                       value="${cart.startReservationDate}"
                                                                                       min="${cart.startReservationDate}"
                                                                                       max="2099-12-31"></label>
                </div>
                <div class="days-number">
                    Количество дней для бронирования: <input id="qty" type="number" class="qty" min="1" max="100"
                                                                                      name="reservationDayNumber" value="1" style="width: 5%"/>
                    <br>
                    <h4 class="header-cart-total-sum">Сумма итого:
                        <span class="cart-total-sum" id="total">${cart.totalSum}</span>
                        бел. рублей.
                    </h4>
                    <label><input type="hidden" value="${cart.totalSum}"></label>
                </div>
                <label><input type="hidden" class="totalSum" name="totalSum" id="totalSum" value=""></label>
                <label><input type="hidden" value="${cart.id}" name="cartId"></label>
                <script src="<c:out value="${pageContext.request.contextPath}/assets/cart.js"/>"></script>
                <div class="create-order">
                    <button class="btn btn-primary">Оформить заказ</button>
                </div>
            </form>
        </c:when>
        <c:otherwise>
            <h3 align="center">Корзина пуста</h3>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
