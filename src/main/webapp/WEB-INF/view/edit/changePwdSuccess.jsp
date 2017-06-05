<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2017-04-29
  Time: 오후 3:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="edit.changePassword.title"/> </title>
</head>
<body>
<p>
    <spring:message code="edit.changePassword.success"/>
</p>
<p>
    <a href="<c:url value='/hello'/>">[<spring:message code="go.main"/> </a>
</p>
</body>
</html>

