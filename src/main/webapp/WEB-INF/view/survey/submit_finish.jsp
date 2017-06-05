<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2017-04-27
  Time: 오후 2:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>응답내용</title>
</head>
<body>
    <p>응답내용</p>
    <ul>
        <c:forEach var="response" items="${answerData.response}" varStatus="status">
        <li>${status.index+1}번 문항:${response}</li>
            </c:forEach>
    </ul>
    <p>응답자 위치:${answerData.respondent.location}</p>
    <p>응답자 나이:${answerData.respondent.age}</p>
</body>
</html>
