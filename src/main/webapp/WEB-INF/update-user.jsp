<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Create course</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/servlet" method="post">

            <input type="hidden" name="action" value="update-user">
            <input type="text" name="id" id="id" placeholder="id">
            <input type="text" name="login" id="login" placeholder="User login">
            <input type="password" name="password" id="password" placeholder="User password">
            <button type="submit">Update user</button>
        </form>
    </body>
</html>
