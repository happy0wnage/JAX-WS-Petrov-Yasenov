<%@ tag trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-2">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/index.html">Movies</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
            <c:choose>
                <c:when test="${not empty logged_user}">
                    <ul class="nav navbar-nav">
                        <li><a href="/mymovies">My List</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-expanded="false">Welcome back, <strong>${logged_user.email}</strong>
                                <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="/logout">Logout</a></li>
                            </ul>
                        </li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <form class="navbar-form navbar-left" action="/login" method="post">
                        <fieldset>
                            <div class="form-group">
                                <input type="email" name="email" class="form-control"
                                       placeholder="email...">
                            </div>
                            <div class="form-group">
                                <input type="password" name="password" class="form-control"
                                       placeholder="password...">
                            </div>
                            <button type="submit" class="btn btn-primary">Sign in</button>
                        </fieldset>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>