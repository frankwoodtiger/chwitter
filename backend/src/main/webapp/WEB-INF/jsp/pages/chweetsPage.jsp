<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@ taglib prefix="chweet" tagdir="/WEB-INF/tags/chweet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<template:page title="Chweets" pageCssClass="chweets-page">
    <div class="col-lg-10 col-sm-12 flex-grow-1 d-flex flex-row mx-auto">
        <div class="flex-grow-1 p-4 d-flex flex-column flex-basis-0">
            <chweet:followees followees="${followees}"></chweet:followees>
            <chweet:followers followers="${followers}"></chweet:followers>
        </div>
        <div class="flex-grow-2 p-4 d-flex flex-column flex-basis-0">
            <chweet:newChweet showEditChweet="${showEditChweet}"></chweet:newChweet>
            <div class="chweets">
                <chweet:chweets chweets="${chweets}" showEditChweet="${showEditChweet}"></chweet:chweets>
            </div>
            <c:if test="${chweets.size() < chweetTotalCount}">
                <div class="mx-auto"><span class="show-more pointer">show more</span></div>
            </c:if>
        </div>
        <div class="flex-grow-1 p-4 d-flex flex-column flex-basis-0">
            <chweet:whoToFollow></chweet:whoToFollow>
        </div>
    </div>
    <input id="paging-url" type="hidden" value="${pagingURL}" />
</template:page>