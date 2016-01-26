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
    <head>
        <link rel="stylesheet" href="webjars/bootstrap/3.3.6/dist/css/bootstrap.min.css" type="text/css"/>
        <script type="text/javascript" src="webjars/bootstrap/3.3.6/dist/js/bootstrap.js"></script>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
        <link rel="stylesheet" href="css/style.css" type="text/css"/>
    </head>
    <body>
        <div>
            <div>
                <a href="${pageContext.request.contextPath}/servlet?action=move-to&directory=registration">${bundle.getObject("registration")}</a>
            </div>
            <div>

                <a href="${pageContext.request.contextPath}/servlet?action=move-to&directory=log-in">${bundle.getObject("logIn")}</a>

                <c:if test = "${user.getLogin() != null}">
                    <a href="${pageContext.request.contextPath}/servlet?action=move-to&directory=user-home">${bundle.getObject("userHome")}</a>
                    <c:if test = "${user.getLogin().equals('guest')}">
                        <a href="${pageContext.request.contextPath}/servlet?action=log-out&directory=index">${bundle.getObject("logOut")}</a>
                    </c:if>
                </c:if>
            </div>
            <div>
                <a href="${pageContext.request.contextPath}/servlet?action=find-all-courses&directory=find-all-course-g">${bundle.getObject("viewAvailableCourses")}</a>
            </div>
        </div>
    </body>
</html>
