<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="order" required="true" type="java.lang.String" %>
<%@ attribute name="sort" required="true" type="java.lang.String" %>

<a href="
    <c:url value="/productList">
        <c:param name="sort" value="${sort}"/>
        <c:param name="order" value="${order}"/>
        <c:if test="${not empty param.query}">
            <c:param name="query" value="${param.query}"/>
        </c:if>
    </c:url>
">
    ${order eq "asc" ? "↓" : "↑"}
</a>