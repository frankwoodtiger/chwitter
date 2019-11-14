<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="userList" required="true" type="java.util.List" %>
<div class="table-container">
    <table class="table table-dark">
        <thead>
        <tr>
            <th>Username</th>
            <th>Roles</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td>${user.username}</td>
                <td><c:forEach var="role" items="${user.roles}">${role.name} </c:forEach></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>