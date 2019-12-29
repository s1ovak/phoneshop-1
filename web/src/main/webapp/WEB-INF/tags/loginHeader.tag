<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAuthenticated()">
    <div class="float-right" style="display: flex">
        <label style="margin-right: 10px"><sec:authentication property="principal.username"/></label>
        <form id="logoutForm" method="POST" action="${pageContext.request.contextPath}/logout">
            <button style="margin-right: 10px" class="btn btn-primary" type="submit">Logout</button>
        </form>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/orders">Admin orders</a>
        </sec:authorize>
    </div>
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
    <a href="${pageContext.request.contextPath}/login">Login</a>
</sec:authorize>
