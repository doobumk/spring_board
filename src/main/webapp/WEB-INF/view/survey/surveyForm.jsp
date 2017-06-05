<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2017-04-27
  Time: 오후 2:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>설문조사</title>
</head>
<body>
    <h2>설문조사</h2>
    <%--<form  method="post">
        <p>
            1.당신의 역할은?<br/>
            <label><input type="radio" name="response[0]" value="서버">서버 개발자</label>
            <label><input type="radio" name="response[0]" value=프론트>프론트 개발자</label>
            <label><input type="radio" name="response[0]" value="풀스택">풀스택 개발자</label>
        </p>
        <p>
            2.가장 많이 사용하는 개발도구는?<br/>
            <label><input type="radio" name="response[1]" value="이클립스">이클립스</label>
            <label><input type="radio" name="response[1]" value="인텔리J">인텔리J</label>
            <label><input type="radio" name="response[1]" value="서브라임">서브라임</label>
        </p>
        <p>
            3.하고 싶은 말<br/>
            <input type="text" name="response[2]">
        </p>
        <p>
            <label>응답자위치:<br>
            <input type="text" name="respondent.location">
            </label>
        </p>
        <p>
            <label>응답자 나이:<br>
                <input type="text" name="respondent.age">
            </label>
        </p>
        <input type="submit" value="전송">
    </form>--%>
    <form method="post">
        <c:forEach var="question" items="${question}" varStatus="status">
            <p>
                ${status.index+1}.${question.title}<br/>
                <c:if test="${question.choice}">
                    <c:forEach var="option" items="${question.option}">
                        <label>
                            <input type="radio" name="response[${status.index}]" value="${option}">
                            ${option}
                        </label>
                    </c:forEach>
                </c:if>
                    <c:if test="${!question.choice}">
                    <input type="text" name="response[${status.index}]"/>
                </c:if>
            </p>
        </c:forEach>
            <p>
                <label>응답자위치:<br>
                    <input type="text" name="respondent.location">
                </label>
            </p>
            <p>
                <label>응답자 나이:<br>
                    <input type="text" name="respondent.age">
                </label>
            </p>
            <input type="submit" value="전송">
    </form>
</body>
</html>
