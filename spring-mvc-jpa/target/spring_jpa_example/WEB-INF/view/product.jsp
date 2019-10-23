<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New product</title>
</head>
<body>

<c:url value="/products/create" var="createUrl"/>
<form action="${createUrl}" method="post">
    <%-- Очень часто применяемый способ передачи id через форму --%>
    <input type="hidden" name="id" id="id" value="${product.id}">
    <input type="hidden" name="categoryId" id="categoryId" value="${product.category.id}">
    <p>
        <label for="name">Name</label>
        <input type="text" id="name" name="name" value="${product.name}" />
    </p>
    <p>
        <label for="category">Category</label>
        <input type="text" id="category" name="category" value="${product.category.name}" disabled />
    </p>
    <p>
        <label for="price">Price</label>
        <input type="number" id="price" name="price" value="${product.price}" />
    </p>
    <p>
        <label for="description">Description</label>
        <input type="text" id="description" name="description" value="${product.description}" />
    </p>
    <input type="submit" />
</form>

</body>
</html>
