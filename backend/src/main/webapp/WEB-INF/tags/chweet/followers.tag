<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="chweet" tagdir="/WEB-INF/tags/chweet" %>
<%@attribute name="followers" required="true" type="java.util.Collection" %>

<div class="w-100 p-2 mb-2 mx-auto border border-dark">
    <div>Followers</div>
    <div class="followers">
        <c:if test="${not empty followers}">
            <c:forEach var="follower" items="${followers}">
                <chweet:follower follower="${follower}"></chweet:follower>
            </c:forEach>
        </c:if>
    </div>
</div>