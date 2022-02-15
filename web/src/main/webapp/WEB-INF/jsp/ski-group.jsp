<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/connect/jstl-connect.jsp"%>
<html>
<head>
    <title>Снаряжение для лыж</title>
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
                    <img class="rounded" src="http://ec2-54-165-213-86.compute-1.amazonaws.com:9000/img/nav-ski.png"
                         alt="ski" style="height: 200px; width: 200px;"/>
                </div>
                <div class="card-body">
                    <div class="d-flex justify-content-center">
                        <a href="<c:url value="/ski/catalog/1"/>" class="btn btn-primary">Выбрать лыжи</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4 d-flex align-items-stretch">
            <div class="shadow-sm">
                <div class="text-center" style="padding: 1rem; width: 18rem; height: 15rem;">
                    <img class="rounded" src="http://ec2-54-165-213-86.compute-1.amazonaws.com:9000/img/nav-ski-boot.png"
                         alt="boots" style="height: 200px; width: 220px;"/>
                </div>
                <div class="card-body">
                    <div class="d-flex justify-content-center">
                        <a href="<c:url value="/ski/boot/catalog/1"/>" class="btn btn-primary">Выбрать ботинки</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4 d-flex align-items-stretch">
            <div class="shadow-sm">
                <div class="text-center" style="padding: 1rem; width: 18rem; height: 15rem;">
                    <img class="rounded" src="http://ec2-54-165-213-86.compute-1.amazonaws.com:9000/img/nav-ski-helmet.png"
                         alt="helmet" style="height: 200px; width: 200px;"/>
                </div>
                <div class="card-body">
                    <div class="d-flex justify-content-center">
                        <a href="<c:url value="/ski/helmet/catalog/1"/>" class="btn btn-primary">Выбрать шлем</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
