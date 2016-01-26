<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Malkov Nikifor
  Date: 25.11.2015
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<html>
    <t:head title="Привет ${user.getLogin()}"/>
    <body>
        <form action="${pageContext.request.contextPath}/servlet" method="post">
            <input type="hidden" name="action" value="create-course">
            <input type="hidden" name="directory" value="index">
            <input type="text" name="name" id="title" placeholder="${bundle.getObject("courseName")}">
            <textarea name="course-description" id="text" cols="30" rows="10" placeholder="${bundle.getObject("courseDescription")}"></textarea>
            <input type="hidden" name="teacher-id" value="${user.getId()}">
            <button type="submit">${bundle.getObject("createcourse")}</button>
        </form>
    </body>
</html>
