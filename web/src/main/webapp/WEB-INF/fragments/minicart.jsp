<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<a href="${pageContext.request.contextPath}/cart" class="btn btn-light">
    <label>
        My cart: ${requestScope['totalQuantity']}
        items <fmt:formatNumber type="currency" currencySymbol="$" value="${requestScope['totalPrice']}"/>
    </label>
</a>
