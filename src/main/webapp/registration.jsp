<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Create user</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/servlet" method="post">
            <input type="hidden" name="action" value="registration">
            <input type="text" name="login" id="login" placeholder="User login">
            <input type="password" name="password" id="password" placeholder="">
            <button type="submit">Create user</button>
        </form>
    </body>
</html>