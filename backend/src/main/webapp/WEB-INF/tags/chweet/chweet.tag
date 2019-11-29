<%-- https://stackoverflow.com/questions/21928366/jstl1-2-according-to-tld-or-attribute-directive-in-tag-file-attribute-var-does --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ tag trimDirectiveWhitespaces="true" %>
<%@attribute name="chweet" required="true" type="com.chi.chwitter.entity.Chweet" %>
<div class="row">
    <div class="chweet col-xs-10 col-md-5 p-2 m-2 mx-auto border border-dark">
        <div class="p-2"><fmt:formatDate value="${chweet.createdDate}" type="date" pattern="MM/dd/yyyy HH:mm:ss"/></div>
        <div class="chweet-msg p-2 m-1 border border-dark">${chweet.message} <i class="chweet-menu-arrow fas fa-chevron-down"></i>
            <input type="hidden" name="chweetId" value="${chweet.id}">
            <div class="chweet-menu hide">
                <div class="chweet-menu-item delete border border-dark p-2"  title="Delete">Delete</div>
                <%-- <div class="chweet-menu-item edit border border-top-0 border-dark p-2">Edit</div> --%>
            </div>
        </div>
        <c:if test="${not empty chweet.image}">
            <div class="chweet-img p-2 m-1 border border-dark">
                <img src="data:image/jpeg;base64,${chweet.image.base64Image}"/>
            </div>
        </c:if>
    </div>
</div>