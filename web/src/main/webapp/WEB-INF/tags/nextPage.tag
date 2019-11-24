<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="activePageNumber" required="true" type="java.lang.Integer" %>

<c:url value="/productList">
    <c:if test="${param.order}">
        <c:param name="order" value="${param.order}"/>
    </c:if>

    <c:if test="${param.sort}">
        <c:param name="sort" value="${param.sort}"/>
    </c:if>

    <c:if test="${param.query}">
        <c:param name="query" value="${param.query}"/>
    </c:if>

    <c:param name="page" value="${activePageNumber eq 9 ? activePageNumber : activePageNumber + 1}"/>
</c:url>