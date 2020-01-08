<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:template>
    <div class="container">
        <header class="clearfix">
            <h1 class="float-left">Phonify</h1>
            <div class="float-right">
                <tags:loginHeader/>
                <jsp:include page="../fragments/minicart.jsp"/>
            </div>
        </header>
        <hr>

        <a href="${pageContext.request.contextPath}/productList" class="btn btn-primary"
           style="margin-bottom: 10px">
            Back to product list
        </a>

        <c:choose>
            <c:when test="${not empty phones}">

                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>
                            Model
                        </th>
                        <c:forEach var="phone" items="${phones}">
                            <th>
                                    ${phone.model}
                                <img class="img-size-plp"
                                     src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${phone.imageUrl}">
                            </th>
                        </c:forEach>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            Description
                        </td>
                        <c:forEach var="phone" items="${phones}">
                            <td>
                                <tags:checkNullJasper element="${phone.description}"/>
                            </td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td>
                            Width
                        </td>
                        <c:forEach var="phone" items="${phones}">
                            <td>
                                <tags:checkNullJasper element="${phone.widthMm}"/>
                            </td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td>
                            Height
                        </td>
                        <c:forEach var="phone" items="${phones}">
                            <td>
                                <tags:checkNullJasper element="${phone.heightMm}"/>
                            </td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td>
                            Price
                        </td>
                        <c:forEach var="phone" items="${phones}">
                            <td>
                                <tags:checkNullJasper element="${phone.price}" isPrice="true"/>
                            </td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td>
                            Action
                        </td>
                        <c:forEach var="phone" items="${phones}">
                            <td>
                                <form:form action="${pageContext.request.contextPath}/compare/addToCart" method="post"
                                           modelAttribute="addToCartDto">
                                    <input class="form-control" value="1" id="${phone.id}" name="quantity" type="text">
                                    <input class="form-control" value="${phone.id}" name="phoneId" type="hidden">
                                    <button class="btn btn-outline-primary" type="submit">
                                        Add to cart
                                    </button>
                                </form:form>
                                <c:choose>
                                    <c:when test="${not empty errorPhoneId && errorPhoneId == phone.id}">
                                        <label style="color: red">
                                            ${errorMessage}
                                        </label>
                                    </c:when>
                                </c:choose>
                            </td>
                        </c:forEach>
                    </tr>
                    </tbody>
                </table>

            </c:when>
            <c:otherwise>
                <h3>
                    Nothing to compare!
                </h3>
            </c:otherwise>
        </c:choose>

    </div>
</tags:template>