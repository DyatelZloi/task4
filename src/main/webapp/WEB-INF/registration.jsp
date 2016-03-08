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
    <t:head title="Привет ${user.getLogin()}"/>
    <body>
        <form action="${pageContext.request.contextPath}/servlet" method="post">
            <input type="hidden" name="action" value="registration">
            <input type="text" name="login" id="login" placeholder="${bundle.getObject("login")}">
            <input type="password" name="password" id="password" placeholder="${bundle.getObject("password")}">
            <button type="submit">${bundle.getObject("register")}</button>
        </form>
    </body>
</html>
