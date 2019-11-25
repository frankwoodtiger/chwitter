<%-- https://stackoverflow.com/questions/21928366/jstl1-2-according-to-tld-or-attribute-directive-in-tag-file-attribute-var-does --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ tag trimDirectiveWhitespaces="true" %>
<%@attribute name="tweet" required="true" type="com.chi.twitter.entity.Tweet" %>
<div class="row">
    <div class="tweet col-xs-10 col-md-5 p-2 m-2 mx-auto border border-dark">
        <div class="p-2"><fmt:formatDate value="${tweet.createdDate}" type="date" pattern="MM/dd/yyyy HH:mm:ss"/></div>
        <div class="tweet-msg p-2 m-1 border border-dark">${tweet.message} <i class="tweet-menu-arrow fas fa-chevron-down"></i>
            <input type="hidden" name="tweetId" value="${tweet.id}">
            <div class="tweet-menu hide">
                <div class="tweet-menu-item delete border border-dark p-2">Delete</div>
                <%-- <div class="tweet-menu-item edit border border-top-0 border-dark p-2">Edit</div> --%>
            </div>
        </div>
        <%-- <div class="tweet-img p-2 m-1 border border-dark">image text</div> --%>
    </div>
</div>