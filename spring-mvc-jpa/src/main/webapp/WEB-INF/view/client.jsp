<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Category</title>
</head>
<body>

<form action="${action}" method="post">
    <%-- Очень часто применяемый способ передачи id через форму --%>
    <input type="hidden" name="id" id="id" value="${client.id}">
    <p>
        <label for="name">Name</label>
        <input type="text" id="name" name="name" value="${client.name}"/>
    </p>
    <input type="submit"/>
</form>

<form action="editProduct" method="post">
    <input type="hidden" name="clientid" id="clientid" value="${client.id}">
    Select a Product:&nbsp;
    <select name="product">
        <c:forEach items="${allProducts}" var="prod">
            <option value="${prod.id}">${prod.name} + ${prod.price} </option>
        </c:forEach>
    </select>
    <br/><br/>
    <input type="submit" value="Submit"/>
</form>

<c:url value="/clients/goods" var="url">
    <c:param name="id" value="${client.id}"/>
</c:url>

<p>Перейти к списку продуктов данного клиента</p><a href="${url}">К списку продуктов</a>


</body>
</html>

