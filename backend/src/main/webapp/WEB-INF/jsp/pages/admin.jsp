<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="table" tagdir="/WEB-INF/tags/table"%>

<template:page title="Admin" pageCssClass="admin-page">
    <div class="admin-container">
        <div><a href="/h2-console">H2 DB Console</a></div>
        <div class="break"></div>
        <table:userTable userList="${userList}"></table:userTable>
    </div>
</template:page>