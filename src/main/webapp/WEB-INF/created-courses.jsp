<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DiZi
  Date: 05.01.2016
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <c:forEach var="anycourse" items="${listcourse}">
            <form action="${pageContext.request.contextPath}/servlet" method="post">
                <input type="hidden" name="action" value="update-course">
                <input type="hidden" name="directory" value="index">
                <input type="text" name="name" id="title" value="${anycourse.getName()}">
                <textarea name="course-description" id="text" cols="30" rows="10">${anycourse.getCourseDescription()}</textarea>
                <input type="hidden" name="id" value="${anycourse.getId()}">
                <button type="submit">${bundle.getObject("updateCourse")}</button>
            </form>
            <form action="${pageContext.request.contextPath}/servlet" method="post">
                <input type="hidden" name="action" value="course-delete">
                <input type="hidden" name="directory" value="index">
                <input type="hidden" name="id" value="${anycourse.getId()}">
                <button type="submit">${bundle.getObject("deleteCourse")}</button>
            </form>
            <form action="${pageContext.request.contextPath}/servlet" method="post">
                <input type="hidden" name="action" value="find-by-id-course">
                <input type="hidden" name="directory" value="registred-students">
                <input type="hidden" name="id-course" value="${anycourse.getId()}">
                <button type="submit"> ${bundle.getObject("viewRegisteredStudents")} </button>
            </form>
        </c:forEach>
    </body>
</html>
