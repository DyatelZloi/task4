<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <ul>
            <c:forEach var="course" items="${createdcourses}">
                <li /><c:out value="${course}"></c:out>
            </c:forEach>
        </ul>
    </body>
</html>
