<%--
  Created by IntelliJ IDEA.
  User: DiZi
  Date: 03.01.2016
  Time: 23:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create course</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/servlet" method="post">
    <input type="hidden" name="action" value="create-list">
    <input type="text" name="id-student" id="id-student" placeholder="id student">
    <input type="hidden" name="id-course" value="${course.getId()}">
    <button type="submit">Update list</button>
</form>
</body>
</html>