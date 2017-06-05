<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2017-05-02
  Time: 오후 9:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action="write" commandName="documentWriteCommand" enctype="multipart/form-data">
    <p><spring:message code="title"></spring:message><br>
        <%--<input type="text" name="title" value="${title}"/>--%>
        <form:input path="title"/>
        <form:errors path="title"/>
    </p>
    <p>
       <spring:message code="level"/> <input type="text"  name="writer_level" value="${authInfo.level}" readonly/>
        <spring:message code="writer"/> <input type="text" name="writer_name" value="${authInfo.name}" readonly/>
        <br>
    </p>
    <p><spring:message code="content"/> <br>
        <textarea name="content" rows="5" cols="30">${content}</textarea>
        <form:errors path="content"/>
    </p>
    <input type="hidden" name="approval_level" value="${authInfo.level+1}"/>

    <p><spring:message code="type"/> <br>
        <c:forEach var="type" items="${type}" varStatus="status">
            ${type}<input type="radio" name="type" value="${status.index+1}"/>
        </c:forEach>

        <form:errors path="type"/>
    </p>
    <p>
        <input type="file" name="multipartFile"></p>
    </p>
    <input type="submit" value="<spring:message code="submit"/> "/>
</form:form>

</body>
</html>
