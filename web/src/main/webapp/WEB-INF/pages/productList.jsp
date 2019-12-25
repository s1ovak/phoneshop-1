<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<tags:template>
    <c:url value="/resources/js/addToCart.js" var="addToCart"/>
    <script src="${addToCart}"></script>

    <div class="container">
        <header class="clearfix">
            <h1 class="float-left">Phonify</h1>
            <div class="float-right">
                <a href="${pageContext.request.contextPath}/authentication/login">Login</a>
                <jsp:include page="../fragments/minicart.jsp"/>
            </div>
        </header>
        <hr>

        <div class="clearfix">
            <h3 class="float-left">Phones</h3>
            <form class="form-inline float-right" action="<c:url value="/productList"/>">
                <input name="query" class="form-control" type="text" value="${param.query}"
                       placeholder="Search...">
                <button class="btn btn-light">Search</button>
            </form>
        </div>

        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>
                    Image
                </th>
                <th>
                    Brand
                    <tags:sort sort="brand" order="asc"/>
                    <tags:sort sort="brand" order="desc"/>
                </th>
                <th>
                    Model
                    <tags:sort sort="model" order="asc"/>
                    <tags:sort sort="model" order="desc"/>
                </th>
                <th>
                    Color
                </th>
                <th>
                    Display size
                    <tags:sort sort="displaySize" order="asc"/>
                    <tags:sort sort="displaySize" order="desc"/>
                </th>
                <th>
                    Price
                    <tags:sort sort="price" order="asc"/>
                    <tags:sort sort="price" order="desc"/>
                </th>
                <th>Quantity</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="phone" items="${phones}">
                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/productDetails/${phone.id}">
                            <img class="img-size-plp"
                                 src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${phone.imageUrl}">
                        </a>
                    </td>
                    <td>${phone.brand}</td>
                    <td>${phone.model}</td>
                    <td>
                        <c:forEach var="color" items="${phone.colors}" varStatus="loop">
                            ${color.code}
                            <c:if test="${not loop.last}">, </c:if>
                        </c:forEach>
                    </td>
                    <td>${phone.displaySizeInches}&#34;</td>
                    <td>
                        <fmt:formatNumber type="currency" currencySymbol="$" value="${phone.price}"/>
                    </td>
                    <td>
                        <input class="form-control" value="1" id="${phone.id}" name="quantity" type="text">
                        <span id="error${phone.id}" style="display: none; color: red"></span>
                    </td>
                    <td>
                        <button class="btn btn-outline-primary" name="add2cartButton"
                                onclick="addToCart(${phone.id}, '${pageContext.request.contextPath}/ajaxCart')">
                            Add to cart
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <tags:pagination activePage="${not empty param.page ? param.page : 1}"/>
    </div>
</tags:template>