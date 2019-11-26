<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@ taglib prefix="chweet" tagdir="/WEB-INF/tags/chweet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<template:page title="Tweets" pageCssClass="chweets-page">
    <div class="flex-grow-1 p-4 d-flex flex-column">
        <chweet:newChweet></chweet:newChweet>
        <c:forEach items="${chweets}" var="chweet">
            <chweet:chweet chweet="${chweet}"></chweet:chweet>
        </c:forEach>
    </div>
</template:page>