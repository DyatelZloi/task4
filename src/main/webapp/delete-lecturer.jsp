<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/servlet" method="post">
    <input type="hidden" name="action" value="delete-lecturer">
    <input type="text" name="id" id="title" placeholder="id">
    <button type="submit">Delete lecturer</button>
</form>
</body>
</html>
