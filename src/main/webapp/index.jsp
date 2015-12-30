<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DiZi
  Date: 24.11.2015
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>
            Привет
            <c:if test = "${login != null}">
                ${login}
            </c:if>
        </title>
        <link href="webjars/bootstrap/3.3.6/dist/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="webjars/bootstrap/3.3.6/dist/js/bootstrap.js"></script>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    </head>
    <body>
        <div>
            <div>
                ${bundle.getObject("hello")}
            </div>
            <div>
                <a href="${pageContext.request.contextPath}/servlet?action=to-registration">${bundle.getObject("registration")}</a>
            </div>
            <div>
                <c:if test = "${login == null}">
                    <a href="${pageContext.request.contextPath}/servlet?action=to-log-in">${bundle.getObject("logIn")}</a>
                </c:if>
                <c:if test = "${login != null}">
                    <a href="${pageContext.request.contextPath}/servlet?action=to-user-home">${bundle.getObject("userHome")}</a>
                    <a href="${pageContext.request.contextPath}/servlet?action=log-out">${bundle.getObject("logOut")}</a>

                </c:if>
            </div>
            <div>
                <a href="${pageContext.request.contextPath}/servlet?action=find-all-courses">${bundle.getObject("viewAvailableCourses")}</a>
            </div>
        </div>
    </body>
</html>
