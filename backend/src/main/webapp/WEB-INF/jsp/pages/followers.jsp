<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="follower" items="${followers}">
    <div class="d-flex p-2 m-2 align-items-center">
        <div>${follower.username}</div>
    </div>
</c:forEach>