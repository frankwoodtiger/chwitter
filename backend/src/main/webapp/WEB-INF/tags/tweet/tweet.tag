<%@attribute name="message" required="true" %>
<%@attribute name="imageText" required="true" %>

<div class="row">
    <div class="tweet col-xs-10 col-md-5 p-2 m-2 mx-auto border border-dark">
        <div class="tweet-msg p-2 m-1 border border-dark">${message} <i class="tweet-menu fas fa-chevron-down"></i></div>
        <div class="tweet-img p-2 m-1 border border-dark">${imageText}</div>
    </div>
</div>