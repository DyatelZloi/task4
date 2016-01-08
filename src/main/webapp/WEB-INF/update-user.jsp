<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Create course</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/servlet" method="post">
            <input type="hidden" name="action" value="update-user-2">
            <input type="hidden" name="directory" value="update-user">
            <input type="text" name="id" id="id" placeholder="id" value="${user.getId()}">
            <input type="text" name="login" id="login" placeholder="${bundle.getObject("userLogin")}" value="${user.getLogin()}">
            <input type="password" name="password" id="password" placeholder="${bundle.getObject("userPassword")}" value="${user.getPassword()}">
            <input type="text" name="name" id="name" placeholder="${bundle.getObject("userName")}" value="${user.getName()}">
            <input type="text" name="surname" id="surname" placeholder="${bundle.getObject("userSurname")}" value="${user.getSurname()}">
            <input type="text" name="email" id="email" placeholder="${bundle.getObject("userEmail")}" value="${user.getEmail()}">
            <button type="submit">${bundle.getObject("updateUser")}</button>
        </form>
    </body>
</html>
