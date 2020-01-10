<%@ taglib prefix="chweet" tagdir="/WEB-INF/tags/chweet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="chweets" required="true" type="java.util.List<com.chi.chwitter.entity.Chweet>" %>
<%@attribute name="showEditChweet" required="true" type="java.lang.Boolean" %>

<c:forEach items="${chweets}" var="chweet">
    <chweet:chweet showEditChweet="${showEditChweet}" chweet="${chweet}"></chweet:chweet>
</c:forEach>