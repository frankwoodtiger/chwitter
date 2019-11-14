<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@ taglib prefix="tags-security" tagdir="/WEB-INF/tags/security" %>

<template:page title="User Registration" pageCssClass="register-form-page">
    <div class="register-form-container">
        <tags-security:registerForm/>
    </div>
</template:page>