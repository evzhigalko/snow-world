<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/connect/jstl-connect.jsp" %>
<html>
<head>
    <title>Каталог сноубордов</title>
    <%@ include file="/WEB-INF/connect/css-connect.jsp" %>
</head>
<body>
<header>
    <%@ include file="/WEB-INF/jsp/static/navbar.jsp"%>
</header>
<div class="container" style="height: 7rem">
    <c:if test="${sessionScope.ROLE eq 'ADMIN'}">
        <a class="btn btn btn-success btn-sm add-new-item" href="<c:url value="/admin/create/new/snowboard"/>" role="button">Добавить сноуборд</a>
    </c:if>
</div>
<ul class="list-group">
    <div class="container">
        <div class="container-fluid">
            <div class="card-group justify-content-start">
                <c:forEach var="snowboard" items="${requestScope.list}">
                    <li>
                        <div class="card mb-3 d-flex align-items-stretch" style="max-width: 38rem;">
                            <div class="row">
                                <div class="col-md-6">
                                    <img src="${snowboard.image.imageName}"
                                         class="img-fluid rounded-start" alt="img">
                                </div>
                                <div class="col-md-6" style="padding-left: 30px">
                                    <div class="card-body">
                                        <h5 class="card-title">${snowboard.maker}</h5>
                                        <p class="card-info">Рост: ${snowboard.equipmentSizeId.userMinHeight}
                                            - ${snowboard.equipmentSizeId.userMaxHeight} см </p>
                                        <p class="card-info">Вес: ${snowboard.equipmentSizeId.userMinWeight}
                                            - ${snowboard.equipmentSizeId.userMaxWeight} кг</p>
                                        <p class="card-info">Уровень катания: ${snowboard.ridingLevel.name}</p>
                                        <p class="card-info">Уровень жесткости: ${snowboard.hardnessLevel.name}</p>
                                        <p class="card-info">Цена проката: ${snowboard.cost} руб./сутки</p>
                                        <p class="card-info">Доступен к прокату:
                                            <c:choose>
                                                <c:when test="${snowboard.availableToRental eq 'true'}">
                                                    <c:out value="${'Да'}"/>
                                                </c:when>
                                                <c:when test="${snowboard.availableToRental eq 'false'}">
                                                    <c:out value="${'Нет'}"/>
                                                </c:when>
                                            </c:choose></p>
                                        <c:if test="${sessionScope.ROLE eq 'ADMIN'}">
                                            <form action="<c:url value="/admin/snowboard/catalog/${snowboard.id}"/>"
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
                                            <form action="<c:url value="/admin/delete/snowboard/${snowboard.id}"/>"
                                                  method="post">
                                                <button class="btn btn btn-warning btn-sm">Удалить</button>
                                            </form>
                                        </c:if>
                                        <div style="padding-top: 7px">
                                            <a href="<c:url value="/cart/add/snowboard/${snowboard.id}"/>" class="btn btn-primary">Добавить в корзину</a>
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
                                     href="${pageContext.request.contextPath}/snowboard/catalog/${i}">${i}</a></li>
            <input type="hidden" name="page_number" value="${i}"/>
        </c:forEach>
    </ul>
</nav>
</body>
</html>
