<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="element" required="true" type="java.lang.Object" %>
<%@ attribute name="text" required="false" type="java.lang.String" %>
<%@ attribute name="isPrice" required="false" type="java.lang.Boolean" %>

<c:choose>
    <c:when test="${not empty element}">
        <c:choose>
            <c:when test="${isPrice}">
                <fmt:formatNumber type="currency" currencySymbol="$" value="${element}"/>
            </c:when>
            <c:otherwise>
                <c:out value="${element}${text}"/>
            </c:otherwise>
        </c:choose>
    </c:when>
    <c:otherwise>
        Unknown
    </c:otherwise>
</c:choose>
