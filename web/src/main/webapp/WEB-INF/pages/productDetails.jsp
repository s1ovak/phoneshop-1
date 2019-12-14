<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="phone" class="com.es.core.model.phone.Phone" scope="request"/>
<tags:template>
    <c:url value="/resources/js/addToCart.js" var="addToCart"/>
    <script src="${addToCart}"></script>

    <div class="container">
        <header class="clearfix">
            <h1 class="float-left">Phonify</h1>
            <div class="float-right">
                <a href="#">Login</a>
                <jsp:include page="../fragments/minicart.jsp"/>
            </div>
        </header>
        <hr>

        <a href="${pageContext.request.contextPath}/productList" class="btn btn-primary">
            Back to product list
        </a>

        <div class="modal-body">
            <div class="left-side">
                <h1>${phone.model}</h1>
                <img src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${phone.imageUrl}">
                <p>${phone.description}</p>
                <div class="visible-print-inline-block">
                    <h3>Price:  <fmt:formatNumber type="currency" currencySymbol="$" value="${phone.price}"/></h3>
                    <div>
                        <input class="form-control left-side" value="1" id="${phone.id}" name="quantity"
                               type="text" style="width: 160px; text-align: right">
                        <button class="btn btn-outline-primary" name="add2cartButton" style="margin-left: 5px"
                                onclick="addToCart(${phone.id}, '${pageContext.request.contextPath}/ajaxCart')">
                            Add to cart
                        </button>
                    </div>
                    <span id="error${phone.id}" style="display: none; color: red"></span>
                </div>
            </div>

            <div class="right-side">
                <h3>Display</h3>
                <table class="table table-bordered table-striped">
                    <tr>
                        <td>Size</td>
                        <td>
                            <tags:checkNullJasper element="${phone.displaySizeInches}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Resolution</td>
                        <td>
                            <tags:checkNullJasper element="${phone.displayResolution}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Technology</td>
                        <td>
                            <tags:checkNullJasper element="${phone.displayTechnology}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Pixel density</td>
                        <td>
                            <tags:checkNullJasper element="${phone.pixelDensity}"/>
                        </td>
                    </tr>
                </table>

                <h3>Dimensions & weight</h3>
                <table class="table table-hover table-bordered">
                    <tr>
                        <td>Length</td>
                        <td>
                            <tags:checkNullJasper element=" ${phone.lengthMm}" text="mm"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Width</td>
                        <td>
                            <tags:checkNullJasper element=" ${phone.widthMm}" text="mm"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Color</td>
                        <td>
                            <c:forEach var="color" items="${phone.colors}">
                                ${color.code}
                            </c:forEach>
                            <c:if test="${empty phone.colors}">
                                Unknown
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>Weight</td>
                        <td>
                            <tags:checkNullJasper element="${phone.weightGr}"/>
                        </td>
                    </tr>
                </table>

                <h3>Camera</h3>
                <table class="table table-hover table-bordered">
                    <tr>
                        <td>Front</td>
                        <td>
                            <tags:checkNullJasper element="${phone.frontCameraMegapixels}" text=" megapixels"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Back</td>
                        <td>
                            <tags:checkNullJasper element="${phone.backCameraMegapixels}" text=" megapixels"/>
                        </td>
                    </tr>
                </table>

                <h3>Battery</h3>
                <table class="table table-hover table-bordered">
                    <tr>
                        <td>Talk time</td>
                        <td>
                            <tags:checkNullJasper element="${phone.talkTimeHours}" text=" hours"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Stand by time</td>
                        <td>
                            <tags:checkNullJasper element="${phone.standByTimeHours}" text=" hours"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Battery capacity</td>
                        <td>
                            <tags:checkNullJasper element="${phone.batteryCapacityMah}" text=" mAh"/>
                        </td>
                    </tr>
                </table>

                <h3>Other</h3>
                <table class="table table-hover table-bordered">
                    <tr>
                        <td>Device type</td>
                        <td>
                            <tags:checkNullJasper element="${phone.deviceType}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Bluetooth</td>
                        <td>
                            <tags:checkNullJasper element="${phone.bluetooth}"/>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</tags:template>
