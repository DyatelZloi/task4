<%--@elvariable id="textA" type="Servlet"--%>
<%--@elvariable id="textB" type="Servlet"--%>
<%--
  Created by IntelliJ IDEA.
  User: DiZi
  Date: 25.11.2015
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/servlet" method="post">
        <input type="hidden" name="action" value="course-created">
        <input type="text" name="id" id="title" placeholder="id">
        <button type="submit">Find course</button>
    </form>
    <p>${textA}</p>
    <p>Course created</p>
</body>
</html>
