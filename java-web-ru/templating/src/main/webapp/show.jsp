<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- BEGIN -->
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>User</title>

    </head>
    <body>
        <c:out value="${id}"/>
        <c:out value="${firstName}"/>
        <c:out value="${lastName}"/>
        <c:out value="${email}"/>

        <a href='/users/delete?id=${id}'>Удалить</a></td>
    </body>
</html>

<!-- END -->
