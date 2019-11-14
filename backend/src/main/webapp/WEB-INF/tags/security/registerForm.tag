<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
    htmlEscape is for preventing XSS attack.
    Alternatively, we can use <spring:htmlEscape defaultHtmlEscape="true" /> also.
    See: https://stackoverflow.com/questions/2147958/how-do-i-prevent-people-from-doing-xss-in-spring-mvc
--%>
<c:set var="errorTextBootstrapCssClasses" value="alert alert-danger"/>
<form:form modelAttribute="userRegistrationForm" htmlEscape="true">
    <table>
        <tr><td>User ID:</td><td><form:input path="username" /></td></tr>
        <tr><td colspan="2"><form:errors path="username" element="div" cssClass="${errorTextBootstrapCssClasses}"/></td></tr>

        <tr><td>First Name:</td><td><form:input path="firstName" /></td></tr>
        <tr><td colspan="2"><form:errors path="firstName" element="div" cssClass="${errorTextBootstrapCssClasses}"/></td></tr>

        <tr><td>Middle Name:</td><td><form:input path="middleName" /></td></tr>
        <tr><td colspan="2"><form:errors path="middleName" element="div" cssClass="${errorTextBootstrapCssClasses}"/></td></tr>

        <tr><td>Last Name:</td><td><form:input path="lastName" /></td></tr>
        <tr><td colspan="2"><form:errors path="lastName" element="div" cssClass="${errorTextBootstrapCssClasses}"/></td></tr>

        <tr><td>Email:</td><td><form:input path="email" /></td></tr>
        <tr><td colspan="2"><form:errors path="email" element="div" cssClass="${errorTextBootstrapCssClasses}"/></td></tr>

        <tr><td>Password:</td><td><form:password path="password" /></td></tr>
        <tr><td colspan="2"><form:errors path="password" element="div" cssClass="${errorTextBootstrapCssClasses}"/></td></tr>

        <tr><td>Retype Password:</td><td><form:password path="retypePassword" /></td></tr>
        <tr><td colspan="2"><form:errors path="retypePassword" element="div" cssClass="${errorTextBootstrapCssClasses}"/></td></tr>

        <tr><td colspan="2"><input type="submit" value="Register User" /></td></tr>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </table>
</form:form>

