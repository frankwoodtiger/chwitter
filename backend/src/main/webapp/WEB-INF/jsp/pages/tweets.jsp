<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@ taglib prefix="tweet" tagdir="/WEB-INF/tags/tweet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<template:page title="Tweets" pageCssClass="tweets-page">
    <div class="flex-grow-1 p-4 d-flex flex-column">
        <tweet:newTweet></tweet:newTweet>
        <c:forEach items="${tweets}" var="tweet">
            <tweet:tweet tweet="${tweet}"></tweet:tweet>
        </c:forEach>
    </div>
</template:page>