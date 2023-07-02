<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Users</title>

    </head>
    <body>
      <table>
    <c:forEach var="users" items="${users}">
                <tr>
                <td><a href='/users/show?id=${users.get("id")}'>${users.get("id")}</a></td>
                <td>${users.get("firstName")}</td>
                <td>${users.get("lastName")}</td>
                <td>${users.get("email")}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>

<!-- END -->
