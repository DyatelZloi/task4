<%--
  Created by IntelliJ IDEA.
  User: DiZi
  Date: 25.11.2015
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Регистрация</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/servlet" method="post">
            <input type="hidden" name="action" value="create-course">
            <input type="text" name="login" id="login" placeholder="login">
            <input type="password" name="password" id="password" placeholder="">
            <button type="submit">Зарегестрироваться</button>
        </form>
    </body>
</html>
