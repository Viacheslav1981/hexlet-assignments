<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- BEGIN -->
<html>
    <head>
        <meta charset="UTF-8">
        <title>Delete</title>

    </head>
    <body>
        <c:out value="${firstName}"/>
        <c:out value="${lastName}"/>

        <form action="/users/delete?id=${id}" method="post">
            <button type="submit" class="btn btn-danger">Удалить</button>
        </form>

    </body>
</html>

<!-- END -->
