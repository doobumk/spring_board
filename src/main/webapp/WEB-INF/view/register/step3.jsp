<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2017-04-27
  Time: 오전 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p><strong>${formData.name}님</strong>회원가입을 완료했습니다.</p>
    <p><strong>${registerRequest.name}님</strong>회원가입을 완료했습니다.</p>
    <p><spring:message code="register.done" arguments="${formData.name}"/> </p>
    <p><spring:message code="register.done" arguments="${registerRequest.name}"/> </p>
    <p><a href="<c:url value='/hello'/>">[<spring:message code="go.main"/>]</a></p>
</body>
</html>