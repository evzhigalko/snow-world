<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/connect/jstl-connect.jsp"%>
<html>
<head>
    <title>Снаряжение для сноуборда</title>
    <%@ include file="/WEB-INF/connect/css-connect.jsp" %>
</head>
<body>
<header>
    <%@ include file="/WEB-INF/jsp/static/navbar.jsp"%>
</header>
<div class="container" style="padding: 4rem 5rem 4rem 5rem">
    <div class="row">
        <div class="col-md-4 d-flex align-items-stretch">
            <div class="shadow-sm">
                <div class="text-center" style="padding: 1rem; width: 18rem; height: 15rem;">
                    <img class="rounded" src="http://ec2-54-165-213-86.compute-1.amazonaws.com:9000/img/nav-jacket.png"
                         alt="ski" style="height: 200px; width: 180px;"/>
                </div>
                <div class="card-body">
                    <div class="d-flex justify-content-center">
                        <a href="<c:url value="/catalog/clothes/jacket/1"/>" class="btn btn-primary">Выбрать куртку</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4 d-flex align-items-stretch">
            <div class="shadow-sm">
                <div class="text-center" style="padding: 1rem; width: 18rem; height: 15rem;">
                    <img class="rounded" src="http://ec2-54-165-213-86.compute-1.amazonaws.com:9000/img/nav-pants.png"
                         alt="pants" style="height: 200px; width: 100px;"/>
                </div>
                <div class="card-body">
                    <div class="d-flex justify-content-center">
                        <a href="<c:url value="/catalog/clothes/pants/1"/>" class="btn btn-primary">Выбрать штаны</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4 d-flex align-items-stretch">
            <div class="shadow-sm">
                <div class="text-center" style="padding: 1rem; width: 18rem; height: 15rem;">
                    <img class="rounded" src="http://ec2-54-165-213-86.compute-1.amazonaws.com:9000/img/nav-cap.png"
                         alt="cap" style="height: 200px; width: 180px;"/>
                </div>
                <div class="card-body">
                    <div class="d-flex justify-content-center">
                        <a href="<c:url value="/catalog/clothes/cap/1"/>" class="btn btn-primary">Выбрать шапку</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container" style="padding: 0 5rem 4rem 5rem">
    <div class="row">
        <div class="col-md-4 d-flex align-items-stretch">
            <div class="shadow-sm">
                <div class="text-center" style="padding-top: 4rem; width: 18rem; height: 15rem;">
                    <img class="rounded" src="http://ec2-54-165-213-86.compute-1.amazonaws.com:9000/img/nav-mask.png"
                         alt="mask" style="height: 90px; width: 200px;"/>
                </div>
                <div class="card-body">
                    <div class="d-flex justify-content-center">
                        <a href="<c:url value="/catalog/clothes/mask/1"/>" class="btn btn-primary">Выбрать маску</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4 d-flex align-items-stretch">
            <div class="shadow-sm">
                <div class="text-center" style="padding: 1rem; width: 18rem; height: 15rem;">
                    <img class="rounded" src="http://ec2-54-165-213-86.compute-1.amazonaws.com:9000/img/nav-mittens.png"
                         alt="mittens" style="height: 190px; width: 200px;"/>
                </div>
                <div class="card-body">
                    <div class="d-flex justify-content-center">
                        <a href="<c:url value="/catalog/clothes/mittens/1"/>" class="btn btn-primary">Выбрать варежки</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4 d-flex align-items-stretch">
            <div class="shadow-sm">
                <div class="text-center" style="padding: 1rem; width: 18rem; height: 15rem;">
                    <img class="rounded" src="http://ec2-54-165-213-86.compute-1.amazonaws.com:9000/img/nav-gloves.png"
                         alt="gloves" style="height: 190px; width: 200px;"/>
                </div>
                <div class="card-body">
                    <div class="d-flex justify-content-center">
                        <a href="<c:url value="/catalog/clothes/gloves/1"/>" class="btn btn-primary">Выбрать перчатки</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
