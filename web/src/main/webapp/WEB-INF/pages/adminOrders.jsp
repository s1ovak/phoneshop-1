<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="orders" class="java.util.ArrayList" scope="request"/>
<tags:template>
    <div class="container">
        <header class="clearfix">
            <h1 class="float-left">Phonify</h1>
            <div class="float-right">
                <tags:loginHeader/>
            </div>
        </header>
        <hr>
        <h2>Orders</h2>
        <br>

        <c:choose>
            <c:when test="${not empty orders}">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>Order number</th>
                        <th>Customer</th>
                        <th>Phone</th>
                        <th>Address</th>
                        <th>Date</th>
                        <th>Total price</th>
                        <th>Status</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach var="order" varStatus="count" items="${orders}">
                        <jsp:useBean id="order" class="com.es.core.model.order.Order" scope="request"/>

                        <tr>
                            <td>
                                <a href="${pageContext.request.contextPath}/admin/orders/${order.id}">
                                    <tags:checkNullJasper element="${order.id}"/>
                                </a>
                            </td>
                            <td>
                                <tags:checkNullJasper element="${order.firstName}"/>
                                <tags:checkNullJasper element="${order.lastName}"/>
                            </td>
                            <td>
                                <tags:checkNullJasper element="${order.contactPhoneNo}"/>
                            </td>
                            <td>
                                <tags:checkNullJasper element="${order.deliveryAddress}"/>
                            </td>
                            <td>
                                <tags:checkNullJasper element="${order.date}"/>
                            </td>
                            <td>
                                <tags:checkNullJasper element="${order.totalPrice}" isPrice="true"/>
                            </td>
                            <td>
                                <tags:checkNullJasper element="${order.status}"/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <h3>No orders.</h3>
            </c:otherwise>
        </c:choose>

    </div>
</tags:template>
