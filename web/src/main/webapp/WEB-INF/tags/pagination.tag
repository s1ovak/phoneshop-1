<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="activePage" required="true" type="java.lang.Integer" %>

<ul class="pagination float-right">
    <li class="page-item">
        <a class="page-link" href="<tags:previousPage activePageNumber="${activePage}"/>">
            Previous
        </a>
    </li>

    <c:forEach var="i" begin="1" end="9">
        <li class="page-item ${i eq activePage ? "active" : ""}">
            <a class="page-link" href="<tags:thisPage activePageNumber="${i}"/>">
                    ${i}
            </a>
        </li>
    </c:forEach>

    <li class="page-item">
        <a class="page-link" href="<tags:nextPage activePageNumber="${activePage}"/>">
            Next
        </a>
    </li>
</ul>