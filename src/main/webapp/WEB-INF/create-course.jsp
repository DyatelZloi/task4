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
        <title>Create course</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/servlet" method="post">
            <input type="hidden" name="action" value="create-course">
            <input type="text" name="name" id="title" placeholder="Course name">
            <textarea name="course-description" id="text" cols="30" rows="10" placeholder="Course Description"></textarea>
            <button type="submit">Create course</button>
        </form>
    </body>
</html>
