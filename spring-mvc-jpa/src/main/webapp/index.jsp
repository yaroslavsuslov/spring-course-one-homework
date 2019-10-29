<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main page</title>
</head>
<body>
    <ul>
        <c:url value="/categories/" var="categoriesUrl" />
        <li><a href="${categoriesUrl}">Categories and Products</a></li>
        <c:url value="/clients/" var="clientsUrl" />
        <li><a href="${clientsUrl}">Clients</a></li>
        <c:url value="/products/" var="productsUrl" />
        <li><a href="${productsUrl}">Products</a></li>
    </ul>
</body>
</html>
