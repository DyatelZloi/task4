<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Malkov Nikifor
  Date: 06.01.2016
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<html>
    <t:head title="Привет ${user.getLogin()}"/>
    <body>
        <h4>Все пользователи</h4>
        <table  border = "1" class="table">
            <tr>
                <th>Логин</th>
                <th>Имя</th>
                <th>Фамилия</th>
                <th>Роль</th>
            </tr>
            <c:forEach var="user" items="${createdusers}">
                <tr>
                    <td>${user.getLogin()}</td>
                    <td>${user.getName()}</td>
                    <td>${user.getSurname()}</td>
                    <td>${user.getRole()}</td>
                </tr>
                <tr>
                    <td colspan="4">
                        <form action="${pageContext.request.contextPath}/servlet" method="post">
                            <input type="hidden" name="action" value="delete-user">
                            <input type="hidden" name="id" value="${user.getId()}">
                            <button type="submit"> ${bundle.getObject("deleteUser")} </button>
                        </form>
                        <form action="${pageContext.request.contextPath}/servlet" method="post">
                            <input type="hidden" name="action" value="delete-user">
                            <input type="hidden" name="id" value="${user.getId()}">
                            <button type="submit"> Редактировать пользователя </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
