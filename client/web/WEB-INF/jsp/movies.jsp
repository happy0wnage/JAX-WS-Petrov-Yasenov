<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<elem:head/>
<mod:menu/>

<body>
<div class="center">
    <h2>Movies</h2>
    <hr/>
    <table class="table table-striped table-hover ">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Year</th>
            <th>Option</th>
        </tr>
        <c:forEach items="${movies.movieList}" var="m">
            <tr>
                <td>${m.id}</td>
                <td>${m.name}</td>
                <td>${m.description}</td>
                <td>${m.year}</td>
                <td>
                    <a href="/deleteMovie?id_movie=${m.id}">
                        <span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
