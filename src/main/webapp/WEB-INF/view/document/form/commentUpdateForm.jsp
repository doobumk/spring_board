<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2017-05-27
  Time: 오전 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action="/comment/update/${comment.commentId}" commandName="commentUpdateCommand">
    <input type="hidden" value="${document.id}" name="id"/>
    <input type="hidden" value="${authInfo.name}" name="writer"/>

    <input type="hidden" name="group_ID" value="${comment.group_ID}"/>
    <input type="hidden" name="commentId" value="${comment.commentId}"/>

    <textarea name="content" id="content" cols="30" rows="10"></textarea>
    <form:errors path="content"/></p>
    <input type="submit" value="<spring:message code="update"/> "/>
</form:form>
</body>
</html>
