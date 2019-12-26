<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${not empty pageContext.request.userPrincipal.name}">
        <div class="float-right" style="display: flex">
            <label style="margin-right: 10px">${pageContext.request.userPrincipal.name}</label>
            <form id="logoutForm" method="POST" action="${pageContext.request.contextPath}/logout">
                <button style="margin-right: 10px" class="btn btn-primary" type="submit">Logout</button>
            </form>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/orders">Admin orders</a>
        </div>
    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/login">Login</a>
    </c:otherwise>
</c:choose>