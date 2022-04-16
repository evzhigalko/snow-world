<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/connect/jstl-connect.jsp" %>
<html>
<head>
    <title>Registration Page</title>
    <%@ include file="/WEB-INF/connect/css-connect.jsp" %>
</head>
<body>
<header>
    <%@ include file="/WEB-INF/jsp/static/navbar.jsp" %>
</header>
<div class="container mt-5 mb-5">
    <div class="row d-flex align-items-center justify-content-center">
        <div class="col-md-5">
            <div class="card px-5 py-5"><span class="circle"><i class="fa fa-check"></i></span>
                <form:form action="registration" modelAttribute="userRequest">
                    <div class="reg-form-input">
                        <form:errors path="username" cssClass="error"/>
                        <form:input path="username" id="label-username" type="text" class="form-control"
                                    placeholder="Имя пользователя"
                                    name="username"/>
                    </div>
                    <div class="reg-form-input">
                        <form:errors path="password" cssClass="error"/>
                        <form:input path="password" id="label-username" type="password" class="form-control"
                                    placeholder="Пароль"
                                    name="password"/>
                    </div>
                    <div class="reg-form-input">
                        <form:errors path="email" cssClass="error"/>
                        <form:input path="email" id="label-email" type="text" class="form-control"
                                    placeholder="Электронная почта"
                                    name="email"/>
                    </div>
                    <div class="reg-form-input">
                        <form:errors path="firstName" cssClass="error"/>
                        <form:input path="firstName" id="label-first-name" type="text" class="form-control"
                                    placeholder="Имя"
                                    name="firstName"/>
                    </div>
                    <div class="reg-form-input">
                        <form:errors path="lastName" cssClass="error"/>
                        <form:input path="lastName" id="label-last-name" type="text" class="form-control"
                                    placeholder="Фамилия"
                                    name="lastName"/>
                    </div>
                    <div class="reg-form-input">
                        <form:errors path="phoneNumber" cssClass="error"/>
                        <form:input path="phoneNumber" id="label-phone_number" type="text" class="form-control"
                                    placeholder="Телефонный номер в международном формате"
                                    name="phoneNumber"/>
                    </div>
                    <fieldset class="form-group"> Пол:
                        <div class="form-check form-check-inline">
                            <label class="form-check-label">
                                <input type="radio" class="form-check-input" name="gender" id="gender1" value="MALE"
                                       checked> Мужской
                            </label>
                        </div>
                        <div class="form-check form-check-inline">
                            <label class="form-check-label">
                                <input type="radio" class="form-check-input" name="gender" id="gender2" value="FEMALE">
                                Женский
                            </label>
                        </div>
                    </fieldset>
                    <div class="form-check">
                        <label class="form-check-label" for="flexCheckChecked"> Я согласен со всеми
                            требованиями </label>
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"
                               checked required oninvalid="this.setCustomValidity('Согласитесь с требованиями')"
                               oninput="setCustomValidity('')">
                    </div>
                    <div class="text-center mt-4" style="color: red">
                        <c:if test="${requestScope.error != null}">
                            <c:out value="${requestScope.error}"/>
                        </c:if>
                    </div>
                    <div class="d-grid gap-2">
                        <button class="btn btn-primary mt-4 signup">Зарегистрироваться</button>
                    </div>
                </form:form>
                <div class="text-center mt-4"><span>Уже зарегистрированы?</span> <a href="<c:url value="/login"/>"
                                                                                    class="text-decoration-none">Войти</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
