<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="order" class="com.es.core.model.order.Order" scope="request"/>
<tags:template>
    <div class="container">
        <header class="clearfix">
            <h1 class="float-left">Phonify</h1>
        </header>
        <hr>
        <h3>Order</h3>
        <a href="${pageContext.request.contextPath}/cart" class="btn btn-primary"
           style="margin-bottom: 10px">
            Back to cart
        </a>

        <c:choose>
            <c:when test="${not empty order.orderItems}">
                <tags:orderDataTable order="${order}"/>
            </c:when>
            <c:otherwise>
                <h3>
                    Your cart is empty. Can't place order.
                </h3>
            </c:otherwise>
        </c:choose>

    </div>
</tags:template>
