<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/connect/jstl-connect.jsp"%>
<html>
<head>
    <title>Каталог курток</title>
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
                <c:forEach var="jacket" items="${requestScope.list}">
                    <li>
                        <div class="card mb-3 d-flex align-items-stretch" style="max-width: 35rem;">
                            <div class="row">
                                <div class="col-md-6">
                                    <img src="${pageContext.request.contextPath}/assets/static/jacket.jpg"
                                         class="img-fluid rounded-start" alt="img" height="400" width="260">
                                </div>
                                <div class="col-md-6" style="padding-left: 30px">
                                    <div class="card-body">
                                        <h5 class="card-title">${jacket.maker}</h5>
                                        <p class="card-info">Размер:  ${jacket.equipmentSizeId.equipmentSizeId}</p>
                                        <p class="card-info">Пол: ${jacket.gender.name}</p>
                                        <p class="card-info">Мембрана: ${jacket.membrane}</p>
                                        <p class="card-info">Цена проката: ${jacket.cost}</p>
                                        <div style="padding-top: 5rem">
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
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/clothes/jacket/catalog/${i}">${i}</a></li>
        </c:forEach>
    </ul>
</nav>
</body>
</html>
