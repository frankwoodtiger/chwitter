<%@ tag description="Generic Page Template" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/header" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@attribute name="title" required="false" %>
<c:set var="title" value="${(empty title) ? '' : title}" />

<%@attribute name="pageCssClass" required="false" %>
<c:set var="pageCssClass" value="${(empty pageCssClass) ? '' : pageCssClass}" />

<!DOCTYPE html>
<html>
<head>
    <header:css/>
    <header:js/>
    <title>${title}</title>
</head>
<body>
<div class="${pageCssClass}">
    <div>
        <jsp:include page="/WEB-INF/jsp/fragments/header.jsp"/>
    </div>
    <div>
        <%-- see more on: https://stackoverflow.com/questions/1296235/jsp-tricks-to-make-templating-easier --%>
        <jsp:doBody/>
    </div>
    <div>
        <jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
    </div>
</div>
</body>