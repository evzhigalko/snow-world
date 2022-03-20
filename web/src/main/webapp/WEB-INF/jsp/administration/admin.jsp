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
    </ul>
</div>
</body>
</html>
