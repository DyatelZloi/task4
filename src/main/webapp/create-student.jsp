<%--
  Created by IntelliJ IDEA.
  User: DiZi
  Date: 26.11.2015
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Create student</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/servlet" method="post">
            <input type="hidden" name="action" value="create-student">
            <input type="text" name="name" id="name" placeholder="Student name">
            <input type="text" name="surname" id="surname" placeholder="Student surname">
            <button type="submit">Create student</button>
        </form>
    </body>
</html>
