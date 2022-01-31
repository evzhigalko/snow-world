<%@ page contentType="text/html;charset=UTF-8" %>
<nav class="navbar navbar-expand-md navbar-light">
    <div class="container">
        <a class="navbar-brand" href="<c:url value="/index.jsp"/>"><img
                src="${pageContext.request.contextPath}/assets/static/logo.png" alt="logo"
                height="70" width="70"/>Snow-World.by</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav" style="padding-left: 4rem">
            <ul class="nav">
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
                    <a class="nav-link" aria-current="page" href="#">Подбор снаряжения</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="#">Контакты</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
