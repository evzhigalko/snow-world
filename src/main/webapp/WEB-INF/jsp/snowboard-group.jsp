<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/connect/jstl-connect.jsp"%>
<html>
<head>
    <title>Снаряжение для сноуборда</title>
    <%@ include file="/WEB-INF/connect/css-connect.jsp" %>
</head>
<body>
<header>
    <%@ include file="/WEB-INF/static/navbar.jsp"%>
</header>
<div class="container" style="padding: 4rem 5rem 4rem 5rem">
    <div class="row">
        <div class="col-md-4 d-flex align-items-stretch">
            <div class="shadow-sm">
                <div class="text-center" style="padding: 1rem; width: 18rem; height: 15rem;">
                    <img class="rounded" src="${pageContext.request.contextPath}/assets/static/img/snowboard.png"
                         alt="snowboard" style="height: 200px; width: 200px;"/>
                </div>
                <div class="card-body">
                    <div class="d-flex justify-content-center">
                        <a href="#" class="btn btn-primary">Выбрать сноуборд</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4 d-flex align-items-stretch">
            <div class="shadow-sm">
                <div class="text-center" style="padding: 1rem; width: 18rem; height: 15rem;">
                    <img class="rounded" src="${pageContext.request.contextPath}/assets/static/img/snowboard-boot.png"
                         alt="boots" style="height: 200px; width: 190px;"/>
                </div>
                <div class="card-body">
                    <div class="d-flex justify-content-center">
                        <a href="#" class="btn btn-primary">Выбрать ботинки</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4 d-flex align-items-stretch">
            <div class="shadow-sm">
                <div class="text-center" style="padding: 1rem; width: 18rem; height: 15rem;">
                        <img class="rounded" src="${pageContext.request.contextPath}/assets/static/img/snowboard-helmet.png"
                             alt="helmet" style="height: 200px; width: 200px;"/>
                </div>
                    <div class="card-body">
                    <div class="d-flex justify-content-center">
                        <a href="#" class="btn btn-primary">Выбрать шлем</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
