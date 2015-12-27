<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/servlet" method="post">
    <input type="hidden" name="action" value="find-by-login">
    <input type="text" name="login" id="login" placeholder="login">
    <input type="password" name="password" id="password">
    <button type="submit">Find</button>
</form>
${createdcourses}
</body>
</html>
