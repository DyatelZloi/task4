<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DiZi
  Date: 29.12.2015
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <div>
            <a href="../index.jsp"> На галавную страницу </a>
        </div>
        <ul>
            <c:forEach var="course" items="${en}">
                <li /><c:out value="${course}"></c:out>
            </c:forEach>
        </ul>
    </body>
</html>
