<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@ taglib prefix="chweet" tagdir="/WEB-INF/tags/chweet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<template:page title="Tweets" pageCssClass="chweets-page">
    <div class="col-lg-10 col-sm-12 flex-grow-1 d-flex flex-row mx-auto">
        <div class="flex-grow-1 p-4 d-flex flex-column flex-basis-0">
            <chweet:followees followees="${followees}"></chweet:followees>
            <chweet:followers followers="${followers}"></chweet:followers>
        </div>
        <div class="flex-grow-2 p-4 d-flex flex-column flex-basis-0">
            <chweet:newChweet showEditChweet="${showEditChweet}"></chweet:newChweet>
            <c:forEach items="${chweets}" var="chweet">
                <chweet:chweet showEditChweet="${showEditChweet}" chweet="${chweet}"></chweet:chweet>
            </c:forEach>
        </div>
        <div class="flex-grow-1 p-4 d-flex flex-column flex-basis-0">
            <chweet:whoToFollow></chweet:whoToFollow>
        </div>
    </div>
</template:page>