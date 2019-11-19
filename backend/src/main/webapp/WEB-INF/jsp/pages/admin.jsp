<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="table" tagdir="/WEB-INF/tags/table"%>

<template:page title="Admin" pageCssClass="admin-page">
    <div class="admin-container flex-grow-1 d-flex justify-content-center align-items-center">
        <table:userTable userList="${userList}"></table:userTable>
    </div>
</template:page>