<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="potentialUser" items="${potentialUsers}">
    <div class="d-flex p-2 m-2 align-items-center">
        <div>${potentialUser.username}</div>
        <button data-user-id="${potentialUser.id}" type='button' class='ml-auto btn btn-success follow-user'>Follow</button>
    </div>
</c:forEach>