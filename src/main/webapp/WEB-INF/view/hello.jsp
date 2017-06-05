<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>초기화면</title>
</head>
<body>
안녕하세요
<c:if test="${empty authInfo}">
<p><a href="<c:url value='/member/register/step1'/>">가입화면</a></p>
<p><a href="<c:url value='/member/login'/>">로그인</a></p>
<p><a href="<c:url value='dataTransForm'/>">JSON TEST</a></p>

</c:if>
<c:if test="${! empty authInfo}">
    <p>
    <p>${authInfo.name}<spring:message code="welcome"/> </p>
    <p><a href="<c:url value="/member/edit/changePassword"/>">[<spring:message code="edit.changePassword"/>] </a>
    </p>
    <p>
        <a href="<c:url value="/logout"/>">[<spring:message code="logout"/>] </a>
    </p>
    <p><a href="<c:url value='/document/writeForm'/>">글쓰기</a></p>
    <p><a href="<c:url value='/document/listAll'/>">문서목록</a></p>


</c:if>
<a href="<c:url value="/member/list" />">[<spring:message code="member.inquire"/>] </a>
</body>
</html>