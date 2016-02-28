<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>
<body>
<h2>Movies</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Year</th>
        <th>Viewed</th>
    </tr>
    <%--<c:forEach items="${movies}" var="m">--%>
        <tr>
            <td>${movie.id}</td>
            <td>${movie.name}</td>
            <td>${movie.description}</td>
            <td>${movie.year}</td>
            <td>${movie.viewed}</td>
        </tr>
    <%--</c:forEach>--%>
</table>
</body>
</html>
