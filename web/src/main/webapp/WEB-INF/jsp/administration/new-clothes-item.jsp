<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="padding-top: 3rem">
    <c:choose>
        <c:when test="${item eq 'cap'}">
        <p>Шапка</p>
        <form action="<c:url value="/admin/create/new/clothes/cap"/>" method="post" enctype="multipart/form-data" style="width: 60%">
        <input type="hidden" name="productName" value="CAP">
        </c:when>
        <c:when test="${item eq 'glove'}">
        <p>Перчатки</p>
        <form action="<c:url value="/admin/create/new/clothes/glove"/>" method="post" enctype="multipart/form-data" style="width: 60%">
        <input type="hidden" name="productName" value="GLOVE">
            <div class="form-input">
                <label for="label-membrane" class="form-label"> </label>
                <input id="label-membrane" style="width: 15rem" type="text" class="form-control"
                       placeholder="Мембрана"
                       name="membrane" required oninvalid="this.setCustomValidity('Введите количество мм')"
                       oninput="setCustomValidity('')">
            </div>
        </c:when>
        <c:when test="${item eq 'jacket'}">
        <p>Куртка</p>
        <form action="<c:url value="/admin/create/new/clothes/jacket"/>" method="post" enctype="multipart/form-data" style="width: 60%">
        <input type="hidden" name="productName" value="JACKET">
            <div class="form-input">
                <label for="label-membrane" class="form-label"> </label>
                <input id="label-membrane" style="width: 15rem" type="text" class="form-control"
                       placeholder="Мембрана"
                       name="membrane" required oninvalid="this.setCustomValidity('Введите количество мм')"
                       oninput="setCustomValidity('')">
            </div>
        </c:when>
        <c:when test="${item eq 'mask'}">
        <p>Маска</p>
        <form action="<c:url value="/admin/create/new/clothes/mask"/>" method="post" enctype="multipart/form-data" style="width: 60%">
        <input type="hidden" name="productName" value="MASK">
        </c:when>
        <c:when test="${item eq 'mitten'}">
        <p>Варежки</p>
        <form action="<c:url value="/admin/create/new/clothes/mitten"/>" method="post" enctype="multipart/form-data" style="width: 60%">
        <input type="hidden" name="product_group" value="MITTEN">
            <div class="form-input">
                <label for="label-membrane" class="form-label"> </label>
                <input id="label-membrane" style="width: 15rem" type="text" class="form-control"
                       placeholder="Мембрана"
                       name="membrane" required oninvalid="this.setCustomValidity('Введите количество мм')"
                       oninput="setCustomValidity('')">
            </div>
        </c:when>
        <c:when test="${item eq 'pants'}">
        <p>Штаны</p>
        <form action="<c:url value="/admin/create/new/clothes/pants"/>" method="post" enctype="multipart/form-data" style="width: 60%">
        <input type="hidden" name="productName" value="PANTS">
            <div class="form-input">
                <label for="label-membrane" class="form-label"> </label>
                <input id="label-membrane" style="width: 15rem" type="text" class="form-control"
                       placeholder="Мембрана"
                       name="membrane" required oninvalid="this.setCustomValidity('Введите количество мм')"
                       oninput="setCustomValidity('')">
            </div>
        </c:when>
            </c:choose>
        <div class="form-input">
            <label for="label-maker" class="form-label"> </label>
            <input id="label-maker" style="width: 15rem" type="text" class="form-control" placeholder="Производитель"
                   name="maker" required oninvalid="this.setCustomValidity('Введите производителя')"
                   oninput="setCustomValidity('')">
        </div>
        <div class="mb-3 add-image">
            <p style="padding-top: 1rem">
                <label for="file" class="form-label">Добавить фото квадратного размера, например: 250х250 400х400:</label>
                <input class="form-control" type="file" id="file" name="file" accept="image/png, image/jpeg, image/jpg">
            </p>
        </div>
        <div class="form-input">
            <label for="label-cost" class="form-label"> </label>
            <input id="label-cost" style="width: 15rem" type="text" class="form-control" placeholder="Стоимость проката"
                   name="cost" required oninvalid="this.setCustomValidity('Введите стоимость проката')"
                   oninput="setCustomValidity('')">
        </div>
        <div class="form-input">
            Доступность к прокату:
            <label>
                <select class="form-select" name="availableToRental">
                    <option value="true">Да</option>
                    <option value="false">Нет</option>
                </select>
            </label>
        </div>
        <div class="form-input">
            Размер:
            <label>
                <select class="form-select" name="equipmentSize">
                    <c:forEach items="${requestScope.allSizes}" var="size">
                        <option value="${size.equipmentSizeId}">${size.equipmentSizeId}</option>
                    </c:forEach>
                </select>
            </label>
        </div>
        <div class="form-input">
            Пол:
            <label>
                <select class="form-select" name="gender">
                    <option value="MALE">Мужской</option>
                    <option value="FEMALE">Женский</option>
                    <option value="UNISEX">Унисекс</option>
                </select>
            </label>
        </div>
        <div class="d-grid gap-2" style="width: 15rem; padding-top: 1rem">
            <button class="btn btn-primary">Сохранить</button>
        </div>
    </form>
</div>
