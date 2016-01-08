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
        <c:if test="${user.getRole() == 'admin'}">
            <a href="${pageContext.request.contextPath}/servlet?action=move-to&directory=update-user">${bundle.getObject("editProfile")}</a>
            <form action="${pageContext.request.contextPath}/servlet" method="post">
                <input type="hidden" name="action" value="find-all-users">
                <input type="hidden" name="directory" value="created-users">
                <button type="submit">${bundle.getObject("showAllU")}</button>
            </form>
            <a href="${pageContext.request.contextPath}/servlet?action=move-to&directory=find-user"> ${bundle.getObject("showU")}</a>
        </c:if>
        <c:if test="${user.getRole() == 'user'}">
            <a href="${pageContext.request.contextPath}/servlet?action=move-to&directory=update-user">${bundle.getObject("editProfile")}</a>
        </c:if>
        <c:if test="${user.getRole() == 'teacher'}">
            <a href="${pageContext.request.contextPath}/servlet?action=move-to&directory=update-user"> ${bundle.getObject("editProfile")}</a>
            <a href="${pageContext.request.contextPath}/servlet?action=move-to&directory=create-course"> ${bundle.getObject("createcourse")}</a>
            <a href="${pageContext.request.contextPath}/servlet?action=move-to&directory=update-list"> ${bundle.getObject("putTheEstimate")}</a>
            <form action="${pageContext.request.contextPath}/servlet" method="post">
                <input type="hidden" name="action" value="find-by-id-teacher">
                <input type="hidden" name="directory" value="created-courses">
                <input type="hidden" name="id-teacher" value="${user.getId()}">
                <button type="submit">${bundle.getObject("viewСreatedСourses")}</button>
            </form>

        </c:if>
    </body>
</html>
