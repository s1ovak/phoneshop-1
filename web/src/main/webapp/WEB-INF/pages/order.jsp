<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
                <c:choose>
                    <c:when test="${containQuantitiesErrors}">
                        <span style="color: red">Error with stocks of products.</span>
                    </c:when>
                </c:choose>
                <tags:orderDataTable order="${order}"/>

                <form:form action="${pageContext.request.contextPath}/order" method="post"
                           modelAttribute="placeOrderDto">

                    <label>First name* </label>
                    <form:input path="firstName" class="form-control" type="text" cssStyle="width: 400px;"/>
                    <c:choose>
                        <c:when test="${containErrors}">
                            <form:errors path="firstName"
                                         cssStyle="color: red"/>
                        </c:when>
                    </c:choose>
                    <br>

                    <label>Last name* </label>
                    <form:input path="lastName" class="form-control" type="text" cssStyle="width: 400px;"/>
                    <c:choose>
                        <c:when test="${containErrors}">
                            <form:errors path="lastName"
                                         cssStyle="color: red"/>
                        </c:when>
                    </c:choose>
                    <br>

                    <label>Address* </label>
                    <form:input path="address" class="form-control" type="text" cssStyle="width: 400px;"/>
                    <c:choose>
                        <c:when test="${containErrors}">
                            <form:errors path="address"
                                         cssStyle="color: red"/>
                        </c:when>
                    </c:choose>
                    <br>

                    <label>Phone* </label>
                    <form:input path="phone" class="form-control" type="text" cssStyle="width: 400px;"/>
                    <c:choose>
                        <c:when test="${containErrors}">
                            <form:errors path="phone"
                                         cssStyle="color: red"/>
                        </c:when>
                    </c:choose>
                    <br>

                    <label>Additional information</label>
                    <form:textarea path="info" class="form-control" type="text"  cssStyle="width: 400px; height: 150px"/>
                    <br>

                    <button type="submit" class="btn btn-primary">
                        Order
                    </button>
                </form:form>
            </c:when>
            <c:otherwise>
                <h3>
                    Your cart is empty. Can't place order.
                </h3>
            </c:otherwise>
        </c:choose>

    </div>
</tags:template>
