<%--
  Created by IntelliJ IDEA.
  User: Malkov Nikifor
  Date: 26.01.2016
  Time: 2:59
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
    <t:head title="Привет ${user.getLogin()}"/>
    <body>
        <c:forEach var="sheet" items="${entity}">
            <li /><c:out value="${sheet}"></c:out>
        </c:forEach>
    </body>
</html>