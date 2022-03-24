<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/connect/jstl-connect.jsp"%>
<html>
<head>
    <title>Registration Page</title>
    <%@ include file="/WEB-INF/connect/css-connect.jsp" %>
</head>
<body>
<header>
    <%@ include file="/WEB-INF/jsp/static/navbar.jsp"%>
</header>
<div class="container mt-5 mb-5">
    <div class="row d-flex align-items-center justify-content-center">
        <div class="col-md-5">
            <div class="card px-5 py-5"><span class="circle"><i class="fa fa-check"></i></span>
                <form action="<c:url value="/registration"/>" method="post">
                    <div class="form-input">
                        <label for="label-username" class="form-label"> </label>
                        <input id="label-username" type="text" class="form-control" placeholder="Имя пользователя"
                               name="username" required oninvalid="this.setCustomValidity('Введите имя пользователя')" oninput="setCustomValidity('')">
                    </div>
                    <div class="form-input">
                        <label for="label-password" class="form-label"> </label>
                        <input id="label-password" type="password" class="form-control" placeholder="Пароль"
                               name="password" required oninvalid="this.setCustomValidity('Введите пароль')" oninput="setCustomValidity('')">
                    </div>
                    <div class="form-input">
                        <label for="label-email" class="form-label"> </label>
                        <input id="label-email" type="text" class="form-control" placeholder="Электронная почта"
                               name="email" required oninvalid="this.setCustomValidity('Введите электронную почту')" oninput="setCustomValidity('')">
                    </div>
                    <div class="form-input">
                        <label for="label-first-name" class="form-label"> </label>
                        <input id="label-first-name" type="text" class="form-control" placeholder="Имя"
                               name="firstName" required oninvalid="this.setCustomValidity('Введите имя')" oninput="setCustomValidity('')">
                    </div>
                    <div class="form-input">
                        <label for="label-last-name" class="form-label"> </label>
                        <input id="label-last-name" type="text" class="form-control" placeholder="Фамилия"
                               name="lastName" required oninvalid="this.setCustomValidity('Введите фамилию')" oninput="setCustomValidity('')">
                    </div>
                    <div class="form-input">
                        <label for="label-phone_number" class="form-label"> </label>
                        <input id="label-phone_number" type="text" class="form-control" placeholder="Телефонный номер в международном формате"
                               name="phoneNumber" required oninvalid="this.setCustomValidity('Введите телефонный номер')" oninput="setCustomValidity('')">
                    </div>
                    <fieldset class="form-group"> Пол:
                        <div class="form-check form-check-inline">
                            <label class="form-check-label">
                                <input type="radio" class="form-check-input" name="gender" id="gender1" value="MALE" checked> Мужской
                            </label>
                        </div>
                        <div class="form-check form-check-inline">
                            <label class="form-check-label">
                                <input type="radio" class="form-check-input" name="gender" id="gender2" value="FEMALE"> Женский
                            </label>
                        </div>
                    </fieldset>
                    <div class="form-check">
                        <label class="form-check-label" for="flexCheckChecked"> Я согласен со всеми требованиями </label>
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"
                               checked required oninvalid="this.setCustomValidity('Согласитесь с требованиями')" oninput="setCustomValidity('')">
                    </div>
                    <div class="text-center mt-4" style="color: red">
                        <c:if test="${requestScope.error != null}">
                            <c:out value="${requestScope.error}"/>
                        </c:if>
                    </div>
                    <div class="d-grid gap-2">
                        <button class="btn btn-primary mt-4 signup">Зарегистрироваться</button>
                    </div>
                </form>
                <div class="text-center mt-4"><span>Уже зарегистрированы?</span> <a href="<c:url value="/login"/>"
                                                                                class="text-decoration-none">Войти</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
