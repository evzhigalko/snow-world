<%@ page contentType="text/html;charset=UTF-8" %>
<nav class="navbar navbar-expand-md navbar-light">
    <div class="container">
        <a class="navbar-brand" href="<c:url value="/index.jsp"/>">
            <img src="http://ec2-54-165-213-86.compute-1.amazonaws.com:9000/img/logo.png" alt="logo"
                height="70" width="70"/>
            Snow-World.by
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav" style="padding-left: 4rem">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" aria-current="page" href="<c:url value="/snowboard"/>">Прокат сноубордов</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="<c:url value="/ski"/>">Прокат лыж</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="<c:url value="/clothes"/>">Прокат экипировки</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="<c:url value="/contacts"/>">Контакты</a>
                </li>
            </ul>
            <c:choose>
                <c:when test="${sessionScope.user == null}">
                    <a class="sign_in_link" href="<c:url value="/form/login"/>">
                        <div class="sign_in_block">
                            Войти
                        </div>
                    </a>
                </c:when>
                <c:when test="${sessionScope.user != null}">
                    <ul class="nav nav-pills">
                        <li class="nav-item first-last-name">
                            <a class="nav-link disabled" tabindex="-1" aria-disabled="true"><c:out
                                    value="${sessionScope.user.firstName} ${sessionScope.user.lastName}"/></a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/logout"/>"
                               class="sign_out_block">Выйти</a>
                        </li>
                    </ul>
                </c:when>
            </c:choose>
        </div>
    </div>
</nav>
