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
    <%-- only load css when it is not dev profile as we configure webpack to load css in style tag via app.js --%>
    <c:if test="${activeProfile != 'dev'}">
        <header:css/>
    </c:if>
    <header:js/>
    <title>${title}</title>
</head>
<body>
<div class="${pageCssClass} base d-flex flex-column">
    <jsp:include page="/WEB-INF/jsp/fragments/header.jsp"/>
    <%-- see more on: https://stackoverflow.com/questions/1296235/jsp-tricks-to-make-templating-easier --%>
    <jsp:doBody/>
    <jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
</div>
</body>