<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Malkov Nikifor
  Date: 05.01.2016
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<html>
    <t:head title="Привет ${user.getLogin()}"/>
    <body>
        <c:forEach var="student" items="${liststudents}">
            <li /><c:out value="${student}"></c:out>
            <a href=""> Поставить оценку и оставить отзыв </a>
            <form action="${pageContext.request.contextPath}/servlet" method="post">
                <input type="hidden" name="action" value="update-list">
                <input type="hidden" name="id" value="${student.getId()}">
                <input type="text" name="name" id="name" value="${student.getName()}">
                <input type="text" name="surname"  id="surname" value="${student.getSurname()}">
                <input type="text" name="short-comment" id="short-comment" value="${student.getShortComment()}">
                <input type="text" name="score" id="score" placeholder="Score" value="${student.getScore()}">
                <button type="submit">${bundle.getObject("assessmentFeedback")}</button>
            </form>
        </c:forEach>
    </body>
</html>
