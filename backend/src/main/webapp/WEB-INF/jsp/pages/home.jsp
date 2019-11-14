<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tags-security" tagdir="/WEB-INF/tags/security" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<template:page title="Login" pageCssClass="home">
    <sec:authorize access="!isAuthenticated()">
    <div class="login-form-container">
        <tags-security:loginForm/>
    </div>
    </sec:authorize>
</template:page>