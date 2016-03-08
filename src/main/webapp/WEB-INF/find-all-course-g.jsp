<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Malkov Nikifor
  Date: 29.12.2015
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<html>
    <t:head title="Привет ${user.getLogin()}"/>
    <body>
        <div>
            <a href="${pageContext.request.contextPath}/servlet?action=move-to&directory=index"> На галавную страницу </a>
        </div>
        <h4>Доступные курсы</h4>
        <table  border = "1" >
            <tr>
                <th>Название курса</th>
                <th>Преподователь</th>
                <th>Опиание</th>
            </tr>
            <c:forEach var="course" items="${en}">
                <tr>
                    <td>${course.getName()}</td>
                    <td>${course.getLecturer()}</td>
                    <td>${course.getCourseDescription()}</td>
                </tr>
                <c:if test="${user.getRole()!='guest'}">
                    <tr>
                        <td colspan = "3">
                            <form action="${pageContext.request.contextPath}/servlet" method="post">
                                <input type="hidden" name="action" value="create-list">
                                <input type="hidden" name="id-student" value="${user.getId()}">
                                <input type="hidden" name="id-course" value="${course.getId()}">
                                <button type="submit">${bundle.getObject("register")}</button>
                            </form>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </body>
</html>
