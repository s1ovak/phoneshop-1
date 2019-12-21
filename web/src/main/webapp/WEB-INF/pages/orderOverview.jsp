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
        </header>
        <hr>
        <h2>Thank for your order!</h2>
        <h3>Order number ${order.id}</h3>

        <tags:orderDataTable order="${order}"/>

        <div class="left-side">
            <label>First name: ${order.firstName}</label>
            <br>
            <label>Last name: ${order.lastName}</label>
            <br>
            <label>Address: ${order.deliveryAddress}</label>
            <br>
            <label>Phone: ${order.contactPhoneNo}</label>
            <br>
            <label>Your comments: ${order.additionalInfo}</label>
            <br>
            <a href="${pageContext.request.contextPath}/productList" class="btn btn-primary"
               style="margin-bottom: 10px">
                Back to shopping
            </a>
        </div>
    </div>
</tags:template>
