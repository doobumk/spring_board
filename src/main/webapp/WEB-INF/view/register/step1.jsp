<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2017-04-26
  Time: 오후 8:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title><spring:message code="member.register"/></title>
</head>
<body>
    <h2><spring:message code="term"></spring:message></h2>
    <p><spring:message code="term.content"></spring:message> </p>
    <form action="/member/register/step2" method="post">
        <label>
            <input type="checkbox" name="agree" value="true"><spring:message code="term.agree"/>
        </label>
        <input type="submit" value="<spring:message code="next.btn"/> "/>
    </form>
</body>
</html>
