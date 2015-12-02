<%--
  Created by IntelliJ IDEA.
  User: DiZi
  Date: 26.11.2015
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Create participiant list</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/servlet" method="post">
            <input type="hidden" name="action" value="participiant-list-create">
            <input type="text" name="id-course" id="id-course" placeholder="Course id">
            <input type="text" name="id-student" id="id-student" placeholder="Student id">
            <input type="text" name="score" id="score" placeholder="Score">
            <textarea name="short-comment" id="text" cols="30" rows="10" placeholder="Short comment"></textarea>
            <button type="submit">Create participiant list</button>
        </form>
    </body>
</html>
