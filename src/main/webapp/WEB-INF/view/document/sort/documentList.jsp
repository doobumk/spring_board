<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2017-05-03
  Time: 오전 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/document/listAll">
<table border="1" cellpadding="0" cellspacing="0" width="700">
    <tr>
        <td align="right">
            <select name="searchCondition">
                <c:forEach items="${conditionMap}" var="option">
                    <option value="${option.value}">${option.key}</option>
                </c:forEach>
            </select>
            <input name="searchKeyword" type="text"/>

            <input type="submit" value="<spring:message code="inquire"></spring:message> "/>
        </td>
    </tr>
</table>
</form>
<table border="1" cellpadding="0" cellspacing="0" width="1500">
    <tr>
        <th width="100">번호</th>
        <th width="100"/>문서 종류</th>
        <th width="200">제목</th>
        <th width="150">작성자</th>
        <th width="150">결재자</th>
        <th width="150">결재 상태</th>
        <th width="150">등록일</th>
        <th width="150">수정일</th>
        <th width="150">결재일</th>
    </tr>
    <c:forEach var="docList" items="${documentList}">
    <tr>
            <td>${docList.id}</td>
        <td>
            <c:choose>
            <c:when test="${docList.type eq 1}">
                <spring:message code="type.first"/>
            </c:when>
            <c:when test="${docList.type eq 2}">
                <spring:message code="type.second"/>
            </c:when>
            <c:when test="${docList.type eq 3}">
                <spring:message code="type.third"/>
            </c:when>
            <c:when test="${docList.type eq 4}">
                <spring:message code="type.fourth"/>
            </c:when>
        </c:choose>
        </td>
            <td><a href="/document/read/${docList.id}">${docList.title}</a></td>
            <td>${docList.writer_name}</td>
            <td>${docList.approval}</td>
            <td>
                <c:choose>
                <c:when test="${docList.state eq 1}">
                    <spring:message code="state.first"/>
                </c:when>
                <c:when test="${docList.state eq 2}">
                    <spring:message code="state.second"/>
                </c:when>
                <c:when test="${docList.state eq 3}">
                    <spring:message code="state.third"/>
                </c:when>
                    <c:when test="${docList.state eq 4}">
                        <spring:message code="state.fourth"/>
                    </c:when>
            </c:choose>
            </td>
            <td><fmt:formatDate value="${docList.createDate}" pattern="yyyy-MM-dd HH:mm"/> </td>
        <td><fmt:formatDate value="${docList.modifiedDate}" pattern="yyyy-MM-dd HH:mm"/> </td>
        <td><fmt:formatDate value="${docList.approvalDate}" pattern="yyyy-MM-dd HH:mm"/> </td>
    </tr>
    </c:forEach>
    <tr>
        <td colspan="5">
        <c:if test="${page.startPage >5}">
            <a href="/document/listAll?pageNo=${page.startPage -5}">[${이전}]</a>
        </c:if>
            <c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
                <a href="/document/listAll?pageNo=${i}">[${i}]</a>
            </c:forEach>
        <c:if test="${page.endPage < page.totalPage}">
            <a href="/document/listAll?pageNo=${page.startPage +5}">[${다음}]</a>
        </c:if>
        </td>
    </tr>
</table>
<form action="/document/writeForm" method="post">
    <input type="submit" value="<spring:message code="submit"/> "/>
</form>
</body>
</html>
