<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="header d-flex align-items-center">
    <div class="app-title p-3 mr-auto">
        <h1><a href="/">Chwitter</a></h1>
    </div>
    <div class="links d-inline-flex align-items-center">
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <div class="h2-link p-3">
                <h6><a href="<c:url value="/h2-console" />">H2 DB Console</a></h6>
            </div>
            <div class="admin-link p-3">
                <h6><a href="<c:url value="/admin" />">Admin</a></h6>
            </div>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <div class="logout-link p-3">
                <h6><a href="<c:url value="/logout" />">Log Out</a></h6>
            </div>
        </sec:authorize>
    </div>
</div>