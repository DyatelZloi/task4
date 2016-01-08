<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DiZi
  Date: 06.01.2016
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <c:forEach var="user" items="${createdusers}">
            <li /><c:out value="${user}"></c:out>
            <form action="${pageContext.request.contextPath}/servlet" method="post">
                <input type="hidden" name="action" value="update-user">
                <input type="hidden" name="directory" value="index">
                <input type="text" name="login" id="login" value="${user.getLogin()}">
                <input type="text" name="name" id="name" value="${user.getName()}">
                <input type="text" name="surname" id="surname" value="${user.getSurname()}">
                <input type="hidden" name="id" value="${user.getId()}">
                <input type="text" name="role" id="role" value="${user.getRole()}">
                <button type="submit">${bundle.getObject("updateRole")}</button>
            </form>
            <form action="${pageContext.request.contextPath}/servlet" method="post">
                <input type="hidden" name="action" value="delete-user">
                <input type="hidden" name="directory" value="index">
                <input type="hidden" name="id" value="${user.getId()}">
                <button type="submit"> ${bundle.getObject("deleteUser")} </button>
            </form>
        </c:forEach>
    </body>
</html>
