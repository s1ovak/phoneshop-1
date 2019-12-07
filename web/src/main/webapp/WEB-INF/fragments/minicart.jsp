<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a href="${pageContext.request.contextPath}/cart" class="btn btn-light">
    <label>
        My cart: ${requestScope['totalQuantity']} items ${requestScope['totalPrice']}$
    </label>
</a>
