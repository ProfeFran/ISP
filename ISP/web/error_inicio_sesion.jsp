<%-- 
    Document   : error
    Created on : 26-nov-2017, 16:26:59
    Author     : Development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERROR SESSION</title>
    </head>
    <body>
        <form action="SError" method="POST">
            <table border="1">
                <tr>
                    <td align="center"><h3>DETALLE DE ERROR</h3></td>
                </tr>
                <tr>
                    <td>Error <c:out value="${sessionScope.mensajeLog}"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>
