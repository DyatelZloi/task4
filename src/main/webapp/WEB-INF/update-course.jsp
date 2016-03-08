<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
    <t:head title="Привет ${user.getLogin()}"/>
    <body>
        <form action="${pageContext.request.contextPath}/servlet" method="post">
            <input type="hidden" name="action" value="update-course">
            <input type="text" name="name" id="title" placeholder="${bundle.getObject("courseName")}">
            <textarea name="course-description" id="text" cols="30" rows="10" placeholder="${bundle.getObject("courseDescription")}"></textarea>
            <input type="hidden" name="id" value="${anycourse.getId()}">
            <button type="submit">${bundle.getObject("updateCourse")}</button>
        </form>
    </body>
</html>
