<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
    <t:head title="Привет ${user.getLogin()}"/>
    <body>
        <form action="${pageContext.request.contextPath}/servlet" method="post">
            <input type="hidden" name="action" value="find-user">
            <input type="text" name="id" id="title" placeholder="${bundle.getObject("id")}">
            <button type="submit">${bundle.getObject("findUser")}</button>
        </form>
        ${createdcourses}
    </body>
</html>