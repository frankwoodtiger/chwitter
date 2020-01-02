<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="chweet" tagdir="/WEB-INF/tags/chweet" %>

<c:forEach var="follower" items="${followers}">
    <chweet:follower follower="${follower}"></chweet:follower>
</c:forEach>