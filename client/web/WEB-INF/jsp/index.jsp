<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<elem:head/>
<mod:menu/>

<body>
<div class="center">

    <c:if test="${not empty error_message}">
        <div class="alert alert-dismissible alert-danger">
            <button type="button" class="close" data-dismiss="alert">X</button>
            ${error_message}
        </div>
    </c:if>

    <h2>Movies</h2>
    <hr/>
    <table class="table table-striped table-hover ">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Year</th>
            <c:if test="${not empty logged_user}">
                <th>Add</th>
            </c:if>
        </tr>
        <c:forEach items="${movies.movieList}" var="m">
            <tr>
                <td>${m.id}</td>
                <td>${m.name}</td>
                <td>${m.description}</td>
                <td>${m.year}</td>
                <c:if test="${not empty logged_user}">
                    <td>
                        <a href="/addMovie?id_movie=${m.id}">
                            <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
                        </a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
<c:remove var="error_message" scope="session"/>
