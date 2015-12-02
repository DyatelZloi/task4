<%--@elvariable id="textA" type="Servlet"--%>
<%--@elvariable id="textB" type="Servlet"--%>
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
        <title></title>
        <link href="webjars/bootstrap/3.3.6/dist/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="webjars/bootstrap/3.3.6/dist/js/bootstrap.js"></script>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    </head>
    <body>
        ${textA}
        ${textB}
        <div class="btn btn-primary">
            <a class="btn btn-success" href="create-course.jsp">Create course</a>
        </div>
        <div>
            <a href="create-student.jsp">Create student</a>
        </div>
        <div>
            <a href="lecturer.jsp">Create lecturer</a>
        </div>
        <div>
            <a href="participiant-list.jsp">Registration on the course</a>
        </div>
    </body>
</html>
