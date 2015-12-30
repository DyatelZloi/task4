<%--
  Created by IntelliJ IDEA.
  User: DiZi
  Date: 24.11.2015
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/servlet" method="post">
            <input type="hidden" name="action" value="find-all-courses">
            <button type="submit">Find</button>
        </form>
    </body>
</html>