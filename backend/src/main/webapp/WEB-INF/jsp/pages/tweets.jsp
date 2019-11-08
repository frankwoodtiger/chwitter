<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/header" %>
<%@ taglib prefix="tags-security" tagdir="/WEB-INF/tags/security" %>
<!DOCTYPE html>
<html>
<head>
    <header:css/>
    <header:js/>
    <title></title>
</head>
<body>
<div class="home">
    <div>
        <jsp:include page="../fragments/header.jsp"/>
    </div>
    <div>dummy tweets page</div>
    <div>
        <jsp:include page="../fragments/footer.jsp"/>
    </div>
</div>
</body>