<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Clients</title>
</head>
<body>

<a href="create">Create new Client</a>

<table border="1">
    <tr>
        <th>Id</th>
        <th>name</th>
    </tr>

    <c:forEach items="${clientsList}" var="client">
        <tr>
            <c:url value="/clients/edit" var="editUrl">
                <c:param name="id" value="${client.id}"/>
            </c:url>
            <td>${client.id}</td>

            <td>
                <a href="${editUrl}">${client.name}</a>
            </td>
        </tr>
    </c:forEach>

</table>
</body>

</html>
