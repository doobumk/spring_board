<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<form:form commandName="changePwdCommand">
    <p>
        <label><spring:message code="oldPassword"/>:<br>
            <form:input path="oldPassword"/>
            <form:errors path="oldPassword"/>
        </label>
    </p>
    <p>
        <label>
            <spring:message code="newPassword"/>:<br>
            <form:password path="newPassword"/>
            <form:errors path="newPassword"/>
        </label>
    </p>
    <input type="submit" value="<spring:message code="edit.changePassword"/> ">
</form:form>
</body>
</html>
