<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<tags:template>
    <div class="container">
        <header class="clearfix">
            <h1 class="float-left">Phonify</h1>
            <div class="float-right">
                <label>admin</label>
                <a href="#">Logout</a>
            </div>
        </header>
        <hr>
        <c:choose>
            <c:when test="${not empty order}">
                <h2 style="float: left">Order number: ${order.id}</h2>
                <h2 style="float: right">Order status: ${order.status}</h2>
                <br>
                <tags:orderDataTable order="${order}"/>
                <div class="left-side">
                    <label>First name: <tags:checkNullJasper element="${order.firstName}"/></label>
                    <br>
                    <label>Last name: <tags:checkNullJasper element="${order.lastName}"/></label>
                    <br>
                    <label>Address: <tags:checkNullJasper element="${order.deliveryAddress}"/></label>
                    <br>
                    <label>Phone: <tags:checkNullJasper element="${order.contactPhoneNo}"/></label>
                    <br>
                    <label>Your comments: <tags:checkNullJasper element="${order.additionalInfo}"/></label>
                    <br>

                    <a href="${pageContext.request.contextPath}/admin/orders" class="btn btn-primary">Back</a>
                    <form style="display: inline-block">
                        <button type="submit" class="btn btn-primary"
                                formaction="${pageContext.request.contextPath}/admin/orders/${order.id}?status=delivered"
                                formmethod="post">
                            Delivered
                        </button>

                        <button type="submit" class="btn btn-primary"
                                formaction="${pageContext.request.contextPath}/admin/orders/${order.id}?status=rejected"
                                formmethod="post">
                            Rejected
                        </button>
                    </form>
                </div>
            </c:when>
            <c:otherwise>
                <h2>Order not found.</h2>
            </c:otherwise>
        </c:choose>
    </div>
</tags:template>
