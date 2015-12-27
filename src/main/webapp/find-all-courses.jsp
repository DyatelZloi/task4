<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/servlet" method="post">
    <input type="hidden" name="action" value="find-all-courses">
    <button type="submit">Find course</button>
</form>
${createdcourses}
</body>
</html>
