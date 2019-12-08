<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="element" required="true" type="java.lang.Object" %>
<%@ attribute name="text" required="false" type="java.lang.String" %>

<c:choose>
    <c:when test="${not empty element}">
        ${element}${text}
    </c:when>
    <c:otherwise>
        Unknown
    </c:otherwise>
</c:choose>
