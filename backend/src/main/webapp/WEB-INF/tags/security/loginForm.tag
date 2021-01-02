<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/login" var="loginUrl"/>
<form action="${loginUrl}" method="post">
    <table>
        <tbody>
            <tr>
                <td>User ID:</td>
                <td><input type="text" id="username" name="username" /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" id="password" name="password" /></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Log in" />
                </td>
            </tr>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </tbody>
    </table>
</form>