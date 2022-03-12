<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/connect/jstl-connect.jsp"%>
<html>
<head>
    <title>Login Page</title>
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
                <form action="<c:url value="/login"/>" method="post">
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
                    <div class="text-center mt-4" style="color: red">
                        <c:if test="${requestScope.error != null}">
                            <c:out value="${requestScope.error}"/>
                        </c:if>
                    </div>
                    <div class="d-grid gap-2">
                        <button class="btn btn-primary mt-4 signup">Войти</button>
                    </div>
                </form>
                <div class="text-center mt-4"><span>Ещё не зарегистрированы?</span> <a
                        href="<c:url value="/form/registration"/>"
                        class="text-decoration-none">Зарегистрироваться</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
