<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2017-04-30
  Time: 오후 1:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="member.inquire"/> </title>
</head>
<body>
<a href="<c:url value="/member/list/regdate"></c:url>">[<spring:message code="member.regdate"/>]</a>
<a href="<c:url value="/member/list/id"></c:url>">[<spring:message code="member.id"/>]</a>
<table border="1" cellpadding="0" cellspacing="0" width="1500">
    <tr>
        <th width="100">회원번호</th>
        <th width="150">아이디</th>
        <th width="100">이름</th>
        <th width="200">등록일</th>
        <th width="100">부서</th>
        <th width="100">직위</th>
        <th width="100">가입승인</th>
    </tr>
    <c:forEach var="member" items="${memberList}">
        <tr>
            <td>${member.id}</td>
            <td>${member.email}</td>
            <td>${member.name}</td>
            <td><fmt:formatDate value="${member.registerDate}" pattern="yyyy-MM-dd HH:mm"/> </td>
            <td>
                <c:choose>
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
                </c:choose>
            </td>
            <td>
                ${member.groupcode}
            </td>
            <td>
                <c:choose>
                    <c:when test="${member.regaccept eq 1}">
                        <spring:message code="member.notyet"/>
                    </c:when>
                    <c:when test="${member.regaccept eq 2}">
                        <spring:message code="member.accept"/>
                    </c:when>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    <tr>

            <a href="/hello"><spring:message code="go.main"/> </a>


        <%--<c:forEach var="i" begin="${page.startPageNo}" end="${page.endPageNo}">
            <a href="/document/listAll?pageNo=${i}">${i}</a>
        </c:forEach>--%>
    </tr>
</table>
</body>
</html>
