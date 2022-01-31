<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/connect/jstl-connect.jsp" %>
<html>
<head>
    <title>Каталог лыж</title>
    <%@ include file="/WEB-INF/connect/css-connect.jsp" %>
</head>
<body>
<header>
    <%@ include file="/WEB-INF/static/navbar.jsp" %>
</header>
<ul class="list-group" style="padding-top: 7rem">
    <div class="container">
        <div class="container-fluid">
            <div class="card-group justify-content-start">
                <c:forEach var="ski" items="${requestScope.list}">
                    <li>
                        <div class="card mb-3 d-flex align-items-stretch" style="max-width: 35rem;">
                            <div class="row">
                                <div class="col-md-6">
                                    <img src="${pageContext.request.contextPath}/assets/static/ski.jpg"
                                         class="img-fluid rounded-start" alt="img">
                                </div>
                                <div class="col-md-6" style="padding-left: 30px">
                                    <div class="card-body">
                                        <h5 class="card-title">${ski.maker}</h5>
                                        <p class="card-info">Рост: ${ski.equipmentSizeId.userMinHeight}
                                            - ${ski.equipmentSizeId.userMaxHeight}</p>
                                        <p class="card-info">Уровень катания: ${ski.ridingLevel.name}</p>
                                        <p class="card-info">Цена проката: ${ski.cost}</p>
                                        <select class="form-select" aria-label="Пример выбора по умолчанию">
                                            <option selected>Лыжные палки</option>
                                            <c:forEach var="ski_pole" items="${requestScope.poleList}">
                                                <option> ${ski_pole.equipmentSizeId.userMinHeight}
                                                    - ${ski_pole.equipmentSizeId.userMaxHeight}</option>
                                            </c:forEach>
                                        </select>
                                        <div style="padding-top: 2.5rem">
                                            <a href="#" class="btn btn-primary">Добавить в корзину</a>
                                        </div>
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
            <li class="page-item"><a class="page-link"
                                     href="${pageContext.request.contextPath}/ski/catalog/${i}">${i}</a></li>
        </c:forEach>
    </ul>
</nav>
</body>
</html>
