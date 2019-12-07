<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="phone" class="com.es.core.model.phone.Phone" scope="request"/>
<tags:template>
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
                    <h3>Price: ${phone.price}$</h3>
                    <div>
                        <input class="form-control left-side" id="${phone.id}" name="quantity"
                               value="1" style="width: 60px; text-align: right" type="text">
                        <button class="btn btn-info" style="margin-left: 5px">
                            Add to cart
                        </button>
                    </div>
                </div>
            </div>

            <div class="right-side">
                <h3>Display</h3>
                <table class="table table-bordered table-striped">
                    <tr>
                        <td>Size</td>
                        <td>
                            <tags:checkNull element="${phone.displaySizeInches}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Resolution</td>
                        <td>
                            <tags:checkNull element="${phone.displayResolution}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Technology</td>
                        <td>
                            <tags:checkNull element="${phone.displayTechnology}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Pixel density</td>
                        <td>
                            <tags:checkNull element="${phone.pixelDensity}"/>
                        </td>
                    </tr>
                </table>

                <h3>Dimensions & weight</h3>
                <table class="table table-hover table-bordered">
                    <tr>
                        <td>Length</td>
                        <td>
                            <tags:checkNull element=" ${phone.lengthMm}" text="mm"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Width</td>
                        <td>
                            <tags:checkNull element=" ${phone.widthMm}" text="mm"/>
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
                            <tags:checkNull element="${phone.weightGr}"/>
                        </td>
                    </tr>
                </table>

                <h3>Camera</h3>
                <table class="table table-hover table-bordered">
                    <tr>
                        <td>Front</td>
                        <td>
                            <tags:checkNull element="${phone.frontCameraMegapixels}" text=" megapixels"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Back</td>
                        <td>
                            <tags:checkNull element="${phone.backCameraMegapixels}" text=" megapixels"/>
                        </td>
                    </tr>
                </table>

                <h3>Battery</h3>
                <table class="table table-hover table-bordered">
                    <tr>
                        <td>Talk time</td>
                        <td>
                            <tags:checkNull element="${phone.talkTimeHours}" text=" hours"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Stand by time</td>
                        <td>
                            <tags:checkNull element="${phone.standByTimeHours}" text=" hours"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Battery capacity</td>
                        <td>
                            <tags:checkNull element="${phone.batteryCapacityMah}" text=" mAh"/>
                        </td>
                    </tr>
                </table>

                <h3>Other</h3>
                <table class="table table-hover table-bordered">
                    <tr>
                        <td>Device type</td>
                        <td>
                            <tags:checkNull element="${phone.deviceType}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Bluetooth</td>
                        <td>
                            <tags:checkNull element="${phone.bluetooth}"/>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</tags:template>
