<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Malkov Nikifor
  Date: 29.12.2015
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<html>
    <t:head title="Привет ${user.getLogin()}"/>
    <body>
        <li>${bundle.getObject("userLogin")} : ${user.getLogin()}</li>
        <li>${bundle.getObject("userName")} : ${user.getName()}</li>
        <li>${bundle.getObject("userSurname")} : ${user.getSurname()}</li>
        <li>${bundle.getObject("userPassword")} : ${user.getPassword()}</li>
        <c:if test="${user.getRole().equals('admin')}">
            <a href="${pageContext.request.contextPath}/servlet?action=move-to&directory=update-user">${bundle.getObject("editProfile")}</a>
            <form action="${pageContext.request.contextPath}/servlet" method="post">
                <input type="hidden" name="action" value="find-all-users">
                <input type="hidden" name="directory" value="created-users">
                <button type="submit">${bundle.getObject("showAllU")}</button>
            </form>
            <a href="${pageContext.request.contextPath}/servlet?action=move-to&directory=find-user"> ${bundle.getObject("showU")}</a>
        </c:if>
        <c:if test="${user.getRole().equals('user')}">
            <a href="${pageContext.request.contextPath}/servlet?action=move-to&directory=update-user">${bundle.getObject("editProfile")}</a>
            <form action="${pageContext.request.contextPath}/servlet" method="post">
                <input type="hidden" name="action" value="find-by-all-id-user">
                <input type="hidden" name="directory" value="sheet-registrations">
                <input type="hidden" name="id-user" value="${user.getId()}">
                <button type="submit">${bundle.getObject("viewScore")}</button>
            </form>
        </c:if>
        <c:if test="${user.getRole().equals('teacher')}">
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
