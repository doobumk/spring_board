<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2017-05-04
  Time: 오후 3:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/document/update" method="post" enctype="multipart/form-data">
<p><spring:message code="title"></spring:message><br>
    <input type="text" name="title" value="${document.title}"/>
</p>
<p>
    <spring:message code="level"/> <input type="text"  name="writer_level" value="${document.writer_level}" readonly/>
    <spring:message code="writer"/> <input type="text" name="writer_name" value="${document.writer_name}" readonly/>
    <br>
</p>
<p><spring:message code="content"/> <br>
    <textarea name="content" rows="5" cols="30">${document.content}</textarea>
</p>

<p><spring:message code="type"/> <br>
    <c:forEach var="type" items="${type}" varStatus="status">
        ${type}<input type="radio" name="type" value="${status.index+1}"/>
    </c:forEach>
    <form:errors path="type"/>
</p>
    <p>
        <input type="file" name="multipartFile"></p>
    </p>
<input type="submit" value="<spring:message code="update"/> "/>
</form>
</body>
</html>
