<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@ taglib prefix="tweet" tagdir="/WEB-INF/tags/tweet"%>

<template:page title="Tweets" pageCssClass="tweets-page">
    <div class="flex-grow-1 p-4 d-flex flex-column justify-content-center">
        <tweet:tweet message="message 1" imageText="image 1"></tweet:tweet>
        <tweet:tweet message="message 2" imageText="image 2"></tweet:tweet>
    </div>
</template:page>