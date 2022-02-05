<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/connect/jstl-connect.jsp"%>
<html>
<head>
    <title>Каталог сноубордов</title>
    <%@ include file="/WEB-INF/connect/css-connect.jsp" %>
</head>
<body>
<header>
    <%@ include file="/WEB-INF/static/navbar.jsp"%>
</header>
<ul class="list-group" style="padding-top: 7rem">
    <div class="container">
        <div class="container-fluid">
            <div class="card-group justify-content-start">
                <c:forEach var="snowboard" items="${requestScope.list}">
                    <li>
                        <div class="card mb-3 d-flex align-items-stretch" style="max-width: 35rem;">
                            <div class="row">
                                <div class="col-md-6">
                                    <img src="${snowboard.image.imageName}"
                                         class="img-fluid rounded-start" alt="img">
                                </div>
                                <div class="col-md-6" style="padding-left: 30px">
                                    <div class="card-body">
                                        <h5 class="card-title">${snowboard.maker}</h5>
                                        <p class="card-info">Рост: ${snowboard.equipmentSizeId.userMinHeight}
                                            - ${snowboard.equipmentSizeId.userMaxHeight}</p>
                                        <p class="card-info">Вес: ${snowboard.equipmentSizeId.userMinWeight}
                                            - ${snowboard.equipmentSizeId.userMaxWeight}</p>
                                        <p class="card-info">Уровень катания: ${snowboard.ridingLevel.name}</p>
                                        <p class="card-info">Уровень жесткости: ${snowboard.hardnessLevel.name}</p>
                                        <p class="card-info">Цена проката: ${snowboard.cost}</p>
                                        <a href="#" class="btn btn-primary">Добавить в корзину</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </div>
        </div>
    </div>
</ul>
<nav>
    <ul class="pagination justify-content-center pagination-lg">
        <c:forEach begin="1" end="${requestScope.pagesNumber}" var="i">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/snowboard/catalog/${i}">${i}</a></li>
        </c:forEach>
    </ul>
</nav>
</body>
</html>
