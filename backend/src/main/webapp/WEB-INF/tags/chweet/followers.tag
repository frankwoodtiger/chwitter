<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="followers" required="true" type="java.util.Collection" %>

<div class="w-100 p-2 mb-2 mx-auto border border-dark">
    <div>Followers</div>
    <div class="followers">
        <c:if test="${not empty followers}">
            <c:forEach var="follower" items="${followers}">
                <div class="d-flex p-2 m-2 align-items-center">
                    <div>${follower.username}</div>
                </div>
            </c:forEach>
        </c:if>
    </div>
</div>