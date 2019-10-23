<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <ul>
        <c:url value="/categories/" var="categoriesUrl" />
        <li><a href="${categoriesUrl}">Categories and Products</a></li>
        <%-- Сделайте, чтобы за этим пунктом списка был сисок клиентов --%>
        <%-- с возможностью их добавления и редактирования --%>
        <li>Customers</li>
    </ul>
</body>
</html>
