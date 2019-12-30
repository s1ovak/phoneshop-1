<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<tags:template>
    <div class="container">
        <header class="clearfix">
            <h1 class="float-left">Phonify</h1>
        </header>
        <hr>

        <form method="post" action="${pageContext.request.contextPath}/login" class="form-signin">
            <h2>Login</h2>
            <div class="form-group">
                <span style="color: red">${error}</span>
                <input name="username" type="text" class="form-control" style="width: 25%; margin-bottom: 10px" placeholder="Username"/>
                <input name="password" type="password" class="form-control" style="width: 25%;  margin-bottom: 10px" placeholder="Password"/>
                <button class="btn btn-primary" type="submit">Log In</button>
            </div>
        </form>
    </div>
</tags:template>