<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/connect/jstl-connect.jsp" %>
<html>
<head>
    <title>Страница администратора</title>
    <%@ include file="/WEB-INF/connect/css-connect.jsp" %>
</head>
<body>
<header>
    <%@ include file="/WEB-INF/jsp/static/navbar.jsp" %>
    <h3 align="center">Привет админ!</h3>
</header>
<div class="container">
    <ul>
        <li>
            <div class="users-list">
                <p class="admin_settings_list">
                    <a class="nav-link" data-bs-toggle="collapse" href="#users-list" aria-expanded="false"
                       aria-controls="users-list">
                        Список пользователей
                    </a>
                </p>
                <div class="collapse" id="users-list">
                    <div class="card card-body">
                        <table>
                            <tr>
                                <th>Номер</th>
                                <th>Имя пользователя</th>
                                <th>Имя</th>
                                <th>Фамилия</th>
                                <th>Электронная почта</th>
                                <th>Пол</th>
                                <th>Номер телефона</th>
                            </tr>
                            <c:forEach var="user" items="${requestScope.usersList}" varStatus="counter">
                                <tr>
                                    <td><c:out value="${counter.count}"/></td>
                                    <td><c:out value="${user.username}"/></td>
                                    <td><c:out value="${user.firstName}"/></td>
                                    <td><c:out value="${user.lastName}"/></td>
                                    <td><c:out value="${user.email}"/></td>
                                    <td><c:out value="${user.gender}"/></td>
                                    <td><c:out value="${user.phoneNumber}"/></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </li>
        <li>
            <div class="users-list">
                <p class="admin_settings_list">
                    <a class="nav-link" data-bs-toggle="collapse" href="#change-assortment" aria-expanded="false"
                       aria-controls="users-list">
                        Изменить ассортимент снаряжения
                    </a>
                </p>
                <div class="collapse" id="change-assortment">
                    <div class="card card-body">
                        <ul>
                            <li>
                                <a class="nav-link" data-bs-toggle="collapse" href="#change-snowboard"
                                   aria-expanded="false">
                                    <p class="admin_settings_list">
                                        Сноуборд
                                    </p>
                                </a>
                                <%@ include file="/WEB-INF/jsp/administration/change/change-snowboard-items.jsp" %>
                            </li>
                            <li>
                                <a class="nav-link" data-bs-toggle="collapse" href="#change-ski"
                                   aria-expanded="false">
                                    Лыжи
                                </a>
                                <%@ include file="/WEB-INF/jsp/administration/change/change-ski-items.jsp" %>
                            </li>
                            <li>
                            <li>
                                <a class="nav-link" data-bs-toggle="collapse" href="#change-equipment"
                                   aria-expanded="false">
                                    <p class="admin_settings_list">
                                        Экипировка
                                    </p>
                                </a>
                                <%@ include file="/WEB-INF/jsp/administration/change/change-equipment-items.jsp" %>
                            </li>
                            <li>
                        </ul>
                    </div>
                </div>
            </div>
        </li>
    </ul>
</div>
</body>
</html>
