<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ attribute name="order" required="true" type="com.es.core.model.order.Order" %>

<table class="table table-bordered table-striped" style="border-collapse: separate; empty-cells: hide">
    <thead>
    <tr>
        <th>Brand</th>
        <th>Model</th>
        <th>Colors</th>
        <th>Display size</th>
        <th>Quantity</th>
        <th>Price</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="orderItem" varStatus="count" items="${order.orderItems}">
        <jsp:useBean id="orderItem" class="com.es.core.model.order.OrderItem" scope="request"/>

        <tr>
            <td>
                <tags:checkNullJasper element="${orderItem.phone.brand}"/>
            </td>
            <td>
                <tags:checkNullJasper element="${orderItem.phone.model}"/>
            </td>
            <td>
                <c:choose>
                    <c:when test="${not empty orderItem.phone.colors}">
                        <c:forEach var="color" items="${orderItem.phone.colors}">
                            <c:out value="${color.code}"/>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        Unknown
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <tags:checkNullJasper element="${orderItem.phone.displaySizeInches}"/>
            </td>
            <td>
                <tags:checkNullJasper element="${orderItem.quantity}"/>
            </td>
            <td>
                <tags:checkNullJasper element="${orderItem.phone.price}" isPrice="true"/>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td></td>
        <td></td>
        <td></td>
        <td></td>

        <td>
            Subtotal
        </td>
        <td>
            <tags:checkNullJasper element="${order.subtotal}" isPrice="true"/>
        </td>

    </tr>
    <tr>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td>
            Delivery
        </td>
        <td>
            <tags:checkNullJasper element="${order.deliveryPrice}" isPrice="true"/>
        </td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td>
            TOTAL
        </td>
        <td>
            <tags:checkNullJasper element="${order.totalPrice}" isPrice="true"/>
        </td>
    </tr>
    </tbody>
</table>