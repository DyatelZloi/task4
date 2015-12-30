<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DiZi
  Date: 29.12.2015
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Привет ${login}</title>
    </head>
    <body>
        <c:if test="${role=='admin'}">
            <a href="update-user.jsp"> Редактировать профиль </a>
            <form action="${pageContext.request.contextPath}/servlet" method="post">
                <input type="hidden" name="action" value="find-all-users">
                <button type="submit">${bundle.getObject("showAllU")}</button>
            </form>
            <a href="find-user.jsp"> ${bundle.getObject("showU")}</a>
        </c:if>
        <c:if test="${role=='user'}">
            <a href="update-user.jsp"> Редактировать профиль </a>
            2
        </c:if>
        <c:if test="${role=='teacher'}">
            <a href="update-user.jsp"> Редактировать профиль </a>
            <a href="create-course.jsp"> Создать курс </a>
            <a href="update-list.jsp"> Поставить оценку </a>
            3
        </c:if>
    </body>
</html>
