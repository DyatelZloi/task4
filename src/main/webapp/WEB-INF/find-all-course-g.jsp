<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DiZi
  Date: 29.12.2015
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <div>
            <a href="${pageContext.request.contextPath}/servlet?action=move-to&directory=index"> На галавную страницу </a>
        </div>
        <ul>
            <c:forEach var="course" items="${en}">
                <li /><c:out value="${course}"></c:out>
                <c:if test="${user.getRole()!='guest'}">
                    <form action="${pageContext.request.contextPath}/servlet" method="post">
                        <input type="hidden" name="action" value="create-list">
                        <input type="hidden" name="directory" value="index">
                        <input type="hidden" name="id-student" value="${user.getId()}">
                        <input type="hidden" name="id-course" value="${course.getId()}">
                        <button type="submit">${bundle.getObject("register")}</button>
                    </form>
                </c:if>
            </c:forEach>
        </ul>
    </body>
</html>
