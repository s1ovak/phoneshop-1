<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<jsp:useBean id="cartItems" class="java.util.ArrayList" scope="request"/>
<tags:template>
    <c:url value="/resources/js/deleteCartItem.js" var="deleteCartItem"/>
    <script src="${deleteCartItem}"></script>

    <div class="container">
        <header class="clearfix">
            <h1 class="float-left">Phonify</h1>
            <div class="float-right">
                <a href="#">Login</a>
                <jsp:include page="../fragments/minicart.jsp"/>
            </div>
        </header>
        <hr>
        <a href="${pageContext.request.contextPath}/productList" class="btn btn-primary"
           style="margin-bottom: 10px">
            Back to product list
        </a>

        <c:choose>
            <c:when test="${not empty cartItems}">
                <form:form action="${pageContext.request.contextPath}/cart/update" method="post"
                           modelAttribute="updateCartDto">
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>Brand</th>
                            <th>Model</th>
                            <th>Colors</th>
                            <th>Display size</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Action</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="cartItem" varStatus="count" items="${cartItems}">
                            <jsp:useBean id="cartItem" class="com.es.core.cart.CartItem" scope="request"/>

                            <tr>
                                <td>
                                    <tags:checkNullJasper element="${cartItem.phone.brand}"/>
                                </td>
                                <td>
                                    <tags:checkNullJasper element="${cartItem.phone.model}"/>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty cartItem.phone.colors}">
                                            <c:forEach var="color" items="${cartItem.phone.colors}">
                                                ${color.code}
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            Unknown
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <tags:checkNullJasper element="${cartItem.phone.displaySizeInches}"/>
                                </td>
                                <td>
                                    <tags:checkNullJasper element="${cartItem.phone.price}" isPrice="true"/>
                                </td>
                                <td>
                                    <form:input path="items['${cartItem.phone.id}']" class="form-control"
                                                value="${cartItem.quantity}" type="text"
                                                cssStyle="width: 160px; text-align: right"/>
                                    <br>
                                    <c:choose>
                                        <c:when test="${containErrors}">
                                            <form:errors path="items['${cartItem.phone.id}']"
                                                         cssStyle="color: red"/>
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td>
                                    <button class="btn btn-danger"
                                            onclick="deleteCartItem(
                                                    '${pageContext.request.contextPath}/cart/delete/${cartItem.phone.id}')">
                                        Delete
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div style="float: right">
                        <button type="submit" class="btn btn-primary">
                            Update
                        </button>
                        <a href="${pageContext.request.contextPath}/order" class="btn btn-primary">
                            Order
                        </a>
                    </div>
                </form:form>
            </c:when>
            <c:otherwise>
                <h3>
                    Cart is empty.
                </h3>
            </c:otherwise>
        </c:choose>

    </div>
</tags:template>