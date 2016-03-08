<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Malkov Nikifor
  Date: 25.11.2015
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<html>
    <head>
        <link type="text/javascript" href="webjars/bootstrap/3.3.6/dist/css/bootstrap.min.css" type="text/css"/>
        <script type="text/javascript" src="webjars/bootstrap/3.3.6/dist/js/bootstrap.js"></script>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
        <link rel="stylesheet" href="css/style.css" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <form action="${pageContext.request.contextPath}/servlet" method="post">
                <input type="hidden" name="action" value="log-in">
                <input type="text" name="login" id="login" placeholder="${bundle.getObject("login")}">
                <input type="password" name="password" id="password" placeholder="${bundle.getObject("password")}">
                <button type="submit" class="btn">${bundle.getObject("logIn")}</button>
            </form>
        </div>
    </body>
</html>
