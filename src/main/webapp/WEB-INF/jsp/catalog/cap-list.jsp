<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/connect/jstl-connect.jsp" %>
<html>
<head>
    <title>Каталог шапок</title>
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
                <c:forEach var="cap" items="${requestScope.list}">
                    <li>
                        <div class="card mb-3 d-flex align-items-stretch" style="max-width: 35rem;">
                            <div class="row">
                                <div class="col-md-6">
                                    <img src="${cap.image.imageName}"
                                         class="img-fluid rounded-start" alt="img">
                                </div>
                                <div class="col-md-6" style="padding-left: 30px">
                                    <div class="card-body">
                                        <h5 class="card-title">${cap.maker}</h5>
                                        <p class="card-info">Размер: ${cap.equipmentSizeId.equipmentSizeId}</p>
                                        <p class="card-info">Пол: ${cap.gender.name}</p>
                                        <p class="card-info">Цена проката: ${cap.cost} руб./сутки</p>
                                        <p class="card-info">Доступен к прокату:
                                            <c:choose>
                                                <c:when test="${cap.availableToRental eq 'true'}">
                                                    <c:out value="${'Да'}"/>
                                                </c:when>
                                                <c:when test="${cap.availableToRental eq 'false'}">
                                                    <c:out value="${'Нет'}"/>
                                                </c:when>
                                            </c:choose></p>
                                        <c:if test="${sessionScope.ROLE eq 'ADMIN'}">
                                            <form action="<c:url value="/admin/clothes/cap/catalog/item?id=${cap.id}"/>"
                                                  method="post">
                                                <div class="form-input">
                                                    <label for="label-update-cost"
                                                           class="form-label"> </label>
                                                    <input id="label-update-cost" type="text" class="form-control"
                                                           placeholder="Новая цена"
                                                           name="cost">
                                                </div>
                                                <fieldset class="form-group"> Доступность
                                                    <div class="form-check form-check-inline">
                                                        <label class="form-check-label">
                                                            <input type="radio" class="form-check-input"
                                                                   name="availability" id="availability1"
                                                                   value="true" checked> Да
                                                        </label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <label class="form-check-label">
                                                            <input type="radio" class="form-check-input"
                                                                   name="availability" id="availability2"
                                                                   value="false"> Нет
                                                        </label>
                                                    </div>
                                                </fieldset>
                                                <button class="btn btn btn-warning btn-sm">Изменить</button>
                                            </form>
                                        </c:if>
                                        <c:choose>
                                        <c:when test="${sessionScope.ROLE ne 'ADMIN'}">
                                        <div style="padding-top: 3rem">
                                            </c:when>
                                            <c:when test="${sessionScope.ROLE eq 'ADMIN'}">
                                            <div style="padding-top: 0">
                                                </c:when>
                                                </c:choose>
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
                                     href="${pageContext.request.contextPath}/clothes/cap/catalog/${i}">${i}</a></li>
        </c:forEach>
    </ul>
</nav>
</body>
</html>
