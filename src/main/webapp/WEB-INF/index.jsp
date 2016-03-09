<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%--
  Created by IntelliJ IDEA.
  User: Malkov Nikifor
  Date: 24.11.2015
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<html>
    <t:head title="Привет ${user.getLogin()}"/>
    <body>
        <div>
            <div>
                <a href="${pageContext.request.contextPath}/servlet?action=move-to&directory=registration">${bundle.getObject("registration")}</a>
            </div>
            <div>
                <c:if test = "${user.getLogin().equals('guest')}">
                    <a href="${pageContext.request.contextPath}/servlet?action=move-to&directory=log-in">${bundle.getObject("logIn")}</a>
                </c:if>
                <c:if test = "${user.getLogin() != null}">
                    <a href="${pageContext.request.contextPath}/servlet?action=move-to&directory=user-home">${bundle.getObject("userHome")}</a>
                    <a href="${pageContext.request.contextPath}/servlet?action=log-out">${bundle.getObject("logOut")}</a>
                </c:if>
            </div>
            <div class = "btn">
                <a href="${pageContext.request.contextPath}/servlet?action=find-all-courses">${bundle.getObject("viewAvailableCourses")}</a>
            </div>
        </div>
    </body>
</html>
