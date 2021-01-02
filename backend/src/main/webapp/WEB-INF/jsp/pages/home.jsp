<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tags-security" tagdir="/WEB-INF/tags/security" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<template:page title="Login" pageCssClass="home">
    <sec:authorize access="!isAuthenticated()">
        <div class="login-form-container flex-grow-1 d-flex flex-column justify-content-center align-items-center">
            <div>
                <div>
                    <tags-security:loginForm/>
                </div>
                <div>
                    <table>
                        <tbody>
                            <tr>
                                <td colspan="2">
                                    <a href="/user/register">Register as new user</a>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <a href="/user/refreshRegistrationToken" id="confirm-registration">Resend registration confirmation</a>
                                    <i class="fas fa-caret-up"></i>
                                    <i class="fas fa-caret-down hide"></i>
                                </td>
                            </tr>
                            <tr class="hide confirm-registration-form">
                                <td>
                                    <div>
                                        <div>Enter User ID:</div>
                                        <input type="text" id="refresh-username"/>
                                        <input id="confirm-registration-submit" type="submit" value="Submit"/>
                                        <div id="confirm-registration-msg" class="hide" role="alert"></div>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <c:if test="${not empty errorMessage}">
                        <div id="login-msg" class="alert alert-warning" role="alert">${errorMessage}</div>
                    </c:if>
                </div>
            </div>
        </div>
    </sec:authorize>
</template:page>