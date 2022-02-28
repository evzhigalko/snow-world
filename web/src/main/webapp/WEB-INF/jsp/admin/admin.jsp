<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/connect/jstl-connect.jsp" %>
<html>
<head>
    <title>Страница администратора</title>
    <%@ include file="/WEB-INF/connect/css-connect.jsp" %>
</head>
<body>
<header>
    <%@ include file="/WEB-INF/static/navbar.jsp" %>
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
                                <div class="collapse" id="change-snowboard">
                                    <div class="card card-body">
                                        <ul>
                                            <li class="nav-item">
                                                    <a class="nav-link" aria-current="page" data-bs-toggle="collapse"
                                                       href="#add-new-snowboard"
                                                       aria-expanded="false" >
                                                        Сноуборд
                                                    </a>
                                                <div class="collapse" id="add-new-snowboard">
                                                    <div class="card card-body">
                                                        <ul>
                                                            <li>
                                                                <p class="admin_settings_list">
                                                                    <a class="nav-link"
                                                                       href="<c:url value="/admin/create/new/snowboard"/>" aria-expanded="false">
                                                                        Добавить новый
                                                                    </a>
                                                                </p>
                                                            </li>
                                                            <li>
                                                                <p class="admin_settings_list">
                                                                    <a class="nav-link"
                                                                       href="#" aria-expanded="false">
                                                                        Изменить
                                                                    </a>
                                                                </p>
                                                            </li>
                                                            <li>
                                                                <p class="admin_settings_list">
                                                                    <a class="nav-link"
                                                                       href="#" aria-expanded="false">
                                                                        Удалить
                                                                    </a>
                                                                </p>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </li>
                                            <li>
                                                <a class="nav-link" data-bs-toggle="collapse"
                                                   href="#add-new-snowboard-boot"
                                                   aria-expanded="false">
                                                    Ботинки сноубордические
                                                </a>
                                                <div class="collapse" id="add-new-snowboard-boot">
                                                    <div class="card card-body">
                                                        <ul>
                                                            <li>
                                                                <p class="admin_settings_list">
                                                                    <a class="nav-link"
                                                                       href="<c:url value="/admin/create/new/snowboard_boot"/>" aria-expanded="false">
                                                                        Добавить новый
                                                                    </a>
                                                                </p>
                                                            </li>
                                                            <li>
                                                                <p class="admin_settings_list">
                                                                    <a class="nav-link"
                                                                       href="#" aria-expanded="false">
                                                                        Изменить
                                                                    </a>
                                                                </p>
                                                            </li>
                                                            <li>
                                                                <p class="admin_settings_list">
                                                                    <a class="nav-link"
                                                                       href="#" aria-expanded="false">
                                                                        Удалить
                                                                    </a>
                                                                </p>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </li>
                                            <li>
                                                <a class="nav-link" data-bs-toggle="collapse"
                                                   href="#add-new-snowboard-helmet"
                                                   aria-expanded="false">
                                                    Шлемы сноубордические
                                                </a>
                                                <div class="collapse" id="add-new-snowboard-helmet">
                                                    <div class="card card-body">
                                                        <ul>
                                                            <li>
                                                                <p class="admin_settings_list">
                                                                    <a class="nav-link"
                                                                       href="<c:url value="/admin/create/new/snowboard_helmet"/>" aria-expanded="false">
                                                                        Добавить новый
                                                                    </a>
                                                                </p>
                                                            </li>
                                                            <li>
                                                                <p class="admin_settings_list">
                                                                    <a class="nav-link"
                                                                       href="#" aria-expanded="false">
                                                                        Изменить
                                                                    </a>
                                                                </p>
                                                            </li>
                                                            <li>
                                                                <p class="admin_settings_list">
                                                                    <a class="nav-link"
                                                                       href="#" aria-expanded="false">
                                                                        Удалить
                                                                    </a>
                                                                </p>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <a class="nav-link" data-bs-toggle="collapse" href="#change-ski"
                                   aria-expanded="false">
                                    <p class="admin_settings_list">
                                        Лыжи
                                    </p>
                                </a>
                                <div class="collapse" id="change-ski">
                                    <div class="card card-body">
                                        <ul>
                                            <li>
                                                <a class="nav-link" data-bs-toggle="collapse" href="#change-ski-menu"
                                                   aria-expanded="false">
                                                    Лыжи
                                                </a>
                                            </li>
                                            <li>
                                                <a class="nav-link" data-bs-toggle="collapse"
                                                   href="#change-ski-pole-menu"
                                                   aria-expanded="false">
                                                    Палки лыжные
                                                </a>

                                            </li>
                                            <li>
                                                <a class="nav-link" data-bs-toggle="collapse"
                                                   href="#change-ski-boot-menu"
                                                   aria-expanded="false">
                                                    Ботинки лыжные
                                                </a>
                                            </li>
                                            <li>
                                                <a class="nav-link" data-bs-toggle="collapse"
                                                   href="#change-ski-helmet-menu"
                                                   aria-expanded="false">
                                                    Шлемы лыжные
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </li>
                            <li>
                            <li>
                                <a class="nav-link" data-bs-toggle="collapse" href="#change-equipment"
                                   aria-expanded="false">
                                    <p class="admin_settings_list">
                                        Экипировка
                                    </p>
                                </a>
                                <div class="collapse" id="change-equipment">
                                    <div class="card card-body">
                                        <ul>
                                            <li>
                                                <a class="nav-link" data-bs-toggle="collapse" href="#change-jacket-menu"
                                                   aria-expanded="false">
                                                    Куртки
                                                </a>
                                            </li>
                                            <li>
                                                <a class="nav-link" data-bs-toggle="collapse" href="#change-pants-menu"
                                                   aria-expanded="false">
                                                    Штаны
                                                </a>
                                            </li>
                                            <li>
                                                <a class="nav-link" data-bs-toggle="collapse" href="#change-cap-menu"
                                                   aria-expanded="false">
                                                    Шапки
                                                </a>
                                            </li>
                                            <li>
                                                <a class="nav-link" data-bs-toggle="collapse" href="#change-mask-menu"
                                                   aria-expanded="false">
                                                    Маски
                                                </a>
                                            </li>
                                            <li>
                                                <a class="nav-link" data-bs-toggle="collapse"
                                                   href="#change-mittens-menu"
                                                   aria-expanded="false">
                                                    Варежки
                                                </a>
                                            </li>
                                            <li>
                                                <a class="nav-link" data-bs-toggle="collapse" href="#change-gloves-menu"
                                                   aria-expanded="false">
                                                    Перчатки
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
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
