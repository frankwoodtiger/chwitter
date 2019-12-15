<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="followees" required="true" type="java.util.Collection" %>

<div class="w-100 p-2 mb-2 mx-auto border border-dark">
    <div>Followees</div>
    <div class="followees">
        <c:if test="${not empty followees}">
            <c:forEach var="followee" items="${followees}">
                <div class="d-flex p-2 m-2 align-items-center">
                    <div><a href="/${followee.username}/chweets">${followee.username}</a></div>
                </div>
            </c:forEach>
        </c:if>
    </div>
</div>