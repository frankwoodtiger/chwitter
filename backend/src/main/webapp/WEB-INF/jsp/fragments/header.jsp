<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="header">
    <div class="app-title">
        <h1>Custom Twitter</h1>
    </div>

    <sec:authorize access="isAuthenticated()">
        <div class="logout-link">
            <h6><a href="<c:url value="/logout" />">Log Out</a></h6>
        </div>
    </sec:authorize>
</div>