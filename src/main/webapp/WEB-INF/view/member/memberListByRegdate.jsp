<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2017-04-30
  Time: 오전 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="member.inquire"/> </title>
</head>
<body>
    <form:form commandName="memberListCommandByRegdate">
        <p>
            <label>
                <spring:message code="member.from"/><form:input path="from"/>
                <form:errors path="from"/>
            </label>
            ~
            <label>
                <spring:message code="member.to"/><form:input path="to"/>
                <form:errors path="to"/>
            </label>
            <input type="submit" value="<spring:message code="member.inquire"></spring:message>"/>
        </p>
    </form:form>
<c:if test="${! empty member}">
    <table>
        <tr>
            <th><spring:message code="member.id"/></th><th><spring:message code="member.email"/></th>
            <th><spring:message code="member.name"/></th><th><spring:message code="member.regdate"/> </th>
        </tr>
<c:forEach var="member" items="${member}">
    ${member}
    <tr>
        <td>${member.id}</td>
        <td><a href="<c:url value="/member/list/id/${member.id}"/>">${member.email}</a> </td>
        <td>${member.name}</td>
        <td><fmt:formatDate value="${member.registerDate}" pattern="yyyy-MM-dd"/></td>
    </tr>
</c:forEach>
    </table>
</c:if>
</body>
</html>
