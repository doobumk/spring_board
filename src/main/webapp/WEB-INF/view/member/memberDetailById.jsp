<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2017-04-30
  Time: 오후 3:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="member.info"/> </title>
</head>
<body>
<form action="/member/list/id/search" method="post">
    <input type="text" name="id">
    <input type="submit" value="<spring:message code="inquire"/>"/>
</form>
<c:if test="${! empty member}">

    <p><spring:message code="member.id"/>:${member.id}</p>
    <p><spring:message code="member.email"/>:${member.email}</p>
    <p><spring:message code="member.name"/>:${member.name}</p>
    <p><spring:message code="member.regdate"/>:<fmt:formatDate value="${member.registerDate}" pattern="yyyy-MM-dd HH:mm"/></p>
    <p><spring:message code="groupcode"/>:${member.groupcode}</p>
    <p><spring:message code="level"/>:${member.level}</p>

</c:if>
<c:if test="${! empty member}">

    <p><spring:message code="member.id"/>:${member.id}</p>
    <p><spring:message code="member.email"/>:${member.email}</p>
    <p><spring:message code="member.name"/>:${member.name}</p>
    <p><spring:message code="member.regdate"/>:<fmt:formatDate value="${member.registerDate}" pattern="yyyy-MM-dd HH:mm"/></p>
    <p><spring:message code="groupcode"/>:${member.groupcode}</p>
    <p><spring:message code="level"/>:<c:choose>
        <c:when test="${member.level eq 1}">
            <spring:message code="level.first"/>
        </c:when>
        <c:when test="${member.level eq 2}">
            <spring:message code="level.second"/>
        </c:when>
        <c:when test="${member.level eq 3}">
            <spring:message code="level.third"/>
        </c:when>
        <c:when test="${member.level eq 4}">
            <spring:message code="level.fourth"/>
        </c:when>
    </c:choose></p>

</c:if>
</body>
</html>
