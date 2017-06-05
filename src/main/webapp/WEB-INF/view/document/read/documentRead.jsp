<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spirng" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2017-05-03
  Time: 오후 4:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${document}
<table border="1" cellpadding="0" cellspacing="0">
    <tr>
        <td><spring:message code="title"/> </td>
        <td>${document.title }
    </tr>
    <tr>
        <td><spring:message code="level"/></td>
        <td>
        <c:choose>
            <c:when test="${document.writer_level eq 1}">
                <spring:message code="level.first"/>
            </c:when>
            <c:when test="${document.writer_level eq 2}">
                <spring:message code="level.second"/>
            </c:when>
            <c:when test="${document.writer_level eq 3}">
                <spring:message code="level.third"/>
            </c:when>
            <c:when test="${document.writer_level eq 4}">
                <spring:message code="level.fourth"/>
            </c:when>
        </c:choose>
        </td>
    </tr>
    <tr>
        <td><spring:message code="writer"/> </td>
        <td>${document.writer_name}</td>
    </tr>
    <tr>
        <td><spring:message code="state"/> </td>
        <td>
            <c:choose>
            <c:when test="${document.state eq 1}">
                <spring:message code="state.first"/>
            </c:when>
            <c:when test="${document.state eq 2}">
                <spring:message code="state.second"/>
            </c:when>
            <c:when test="${document.state eq 3}">
                <spring:message code="state.third"/>
            </c:when>
                <c:when test="${document.state eq 4}">
                    <spring:message code="state.fourth"/>
                </c:when>
        </c:choose>
        </td>
    </tr>
    <tr>
        <td><spring:message code="type"/></td>
        <td><c:choose>
            <c:when test="${document.type eq 1}">
                <spring:message code="type.first"/>
            </c:when>
            <c:when test="${document.type eq 2}">
                <spring:message code="type.second"/>
            </c:when>
            <c:when test="${document.type eq 3}">
                <spring:message code="type.third"/>
            </c:when>
            <c:when test="${document.type eq 4}">
                <spring:message code="type.fourth"/>
            </c:when>
        </c:choose>
        </td>
    </tr>
    <tr>
        <td><spring:message code="content"/> </td>
        <td>${document.content}</td>
    </tr>
    <tr>
        <td><spring:message code="createDate"/> </td>
        <td><fmt:formatDate value="${document.createDate}" pattern="yyyy-MM-dd HH:mm"/> </td>
    </tr>
    <tr>
        <td><spring:message code="modifiedDate"/> </td>
        <td><fmt:formatDate value="${document.modifiedDate}" pattern="yyyy-MM-dd HH:mm"/> </td>
    </tr>
    <tr>
        <td><spring:message code="approvalDate"/> </td>
        <td><fmt:formatDate value="${document.approvalDate}" pattern="yyyy-MM-dd HH:mm"/> </td>
    </tr>
    <tr>
        <td><spring:message code="file"/></td>
        <td><a href="/document/download?id=${document.id}">${file}</a> </td>
    </tr>
</table>
<br>
<c:if test="${authInfo.name eq document.writer_name}">
<form action="/document/delete" method="post">
    <input type="hidden" name = "id" value="${document.id}"/>
    <input type="submit" value="<spring:message code="delete"/> "/>
</form>
<form action="/document/update">
    <input type="hidden" name = "id" value="${document.id}"/>
    <input type="submit" value="<spring:message code="update"/> "/>
</form>
</c:if>
<form action="/document/listAll">
    <input type="submit" value="<spring:message code="documentlist"/> "/>
</form>
<c:if test="${authInfo.level eq document.approval_level}">
    ${authInfo.level}
    ${document.approval_level}
<form action="/document/approval">
    <input type="hidden" name="id" value="${document.id}"/>
    <c:if test="${authInfo.level <= 3}">
        <input type="hidden" name="state" value="2"/>
    </c:if>
    <c:if test="${authInfo.level eq 4}">
        <input type="hidden" name="state" value="3"/>
    </c:if>
    <input type="hidden" name="approval" value="${authInfo.name}"/>
    <input type="hidden" name="approval_level" value="${authInfo.level+1}"/>
    <input type="submit" value="<spring:message code="approval"/>"/>
</form>
 <form action="/document/reject">
     <input type="submit" value="<spring:message code="reject"/> "/>
 </form>
</c:if>
<form:form action="/comment/write" commandName="commentWriteCommand">
    <input type="hidden" value="${document.id}" name="id"/>
    <input type="hidden" value="${authInfo.name}" name="writer"/>
    <textarea name="content" cols="30"></textarea>
    <form:errors path="content"/></p>

    <input type="submit" value="<spring:message code="comment.submit"/>"/>
</form:form>
<table border="1" cellpadding="0" cellspacing="0">
    <tr>
        <th><spring:message code="content"/> </th>
        <th><spring:message code="createDate"/> </th>
        <th><spring:message code="writer"/></th>
        <th></th>
    </tr>
    <c:forEach var="commentList" items="${commentList}">
        <tr>
            <td width="250">
                <c:if test="${commentList.depth > 0}" >
                <c:forEach begin="1" end="${commentList.depth}">&nbsp;</c:forEach><spring:message code="re"/></c:if>
                <a href="/comment/childWriteForm/${commentList.commentId}">${commentList.content}</a>
            </td>
            <td><fmt:formatDate value="${commentList.date}" pattern="yyyy-MM-dd HH:mm"/> </td>
            <td>${commentList.writer}</td>
            <td>
                <c:choose>
                    <c:when test="${commentList.depth eq 0}">
                        <form action="/comment/delete/${commentList.group_ID}">
                            <input type="hidden" name="id" value="${commentList.id}"/>
                            <input type="submit" value="<spring:message code="delete"/> "/>
                         </form>
                    </c:when>
                    <c:otherwise>
                        <form action="/comment/deleteChild">
                            <input type="hidden" name="id" value="${commentList.id}"/>
                            <input type="hidden" name="groupId" value="${commentList.group_ID}"/>
                            <input type="hidden" name="depth" value="${commentList.depth}"/>
                            <input type="hidden" name="commentId" value="${commentList.commentId}"/>
                            <input type="submit" value="<spring:message code="delete"/> "/>
                        </form>
                    </c:otherwise>
                </c:choose>
                <a href="/comment/updateForm/${commentList.commentId}"><spring:message code="update"/></a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>