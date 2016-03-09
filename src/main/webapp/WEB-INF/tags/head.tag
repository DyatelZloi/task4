<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Tag used to pages with centered, ex: auth, reg." pageEncoding="UTF-8" %>
<%@attribute name="title" required="true" %>
<head>
    <title>
        ${title}
    </title>
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.6/dist/css/bootstrap.min.css">
    <script type="text/javascript" src="webjars/bootstrap/3.3.6/dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <style><%@include file="/WEB-INF/css/style.css" %></style>
</head>
