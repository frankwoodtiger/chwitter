<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<template:page title="${title}" pageCssClass="home">
    <div class="flex-grow-1 d-flex justify-content-center align-items-center">
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-warning" role="alert">${errorMessage}</div>
        </c:if>
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success mt-1" role="alert">${successMessage}</div>
        </c:if>
    </div>
</template:page>