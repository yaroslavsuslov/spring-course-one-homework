<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Products</title>
</head>

<body>

<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Description</th>
        <th>Category</th>
        <th>Price</th>
    </tr>

    <form action="" method="get">
        <label for="categoryFilter">Category filter</label>
        <select id="categoryFilter" name="categoryId">
            <option value="${-1}" ${param['categoryId'] == null || param['categoryId'] == -1 ? 'selected' : ''}></option>
            <c:forEach items="${categories}" var="category">
                <option value="${category.id}" ${param['categoryId'] == category.id ? 'selected' : ''} >${category.name}</option>
            </c:forEach>
        </select>
        <label for="priceFilter">Price filter</label>
        <select id="priceFilter" name="priceFilter">
            <option value="${"empty"}" ${param['priceFilter'] == null || param['priceFilter'] == "empty" ? 'selected' : ''}></option>
            <c:forEach items="${priceFilterValues}" var="price">
                <option value="${price}" ${param['priceFilter'] == price ? 'selected' : ''} >${price}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Apply"/>
    </form>

    <c:forEach items="${products}" var="prod">
        <tr>
            <td>${prod.id}</td>

            <td>${prod.name}</td>

            <td>${prod.description}</td>

            <td>${prod.category.name}</td>

            <td>${prod.price}</td>
        </tr>
    </c:forEach>

</table>

</body>

</html>
