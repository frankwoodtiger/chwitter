<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>
<div>
    <jsp:include page="../fragments/header.jsp"/>
</div>
<p>This is the home page, ${welcomeMsg}</p>
<div>
    <jsp:include page="../fragments/footer.jsp"/>
</div>
</body>