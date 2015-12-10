<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create course</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/servlet" method="post">
    <input type="hidden" name="action" value="update-lecturer">
    <input type="text" name="name" id="title" placeholder="Course name">
    <textarea name="course-description" id="text" cols="30" rows="10" placeholder="Course Description"></textarea>
    <input type="text" name="id" id="id" placeholder="set id">
    <button type="submit">Update lecturer</button>
</form>
</body>
</html>

