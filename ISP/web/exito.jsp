<%-- 
    Document   : exito
    Created on : 20-11-2017, 16:22:07
    Author     : ProfeFran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EXITO</title>
    </head>
    <form>
        <table border="1">
            <tr>
                <td align="center"><h3>EXITO</h3></td>
            </tr>
            <tr>
                <td>EXITO: <c:out value="${sessionScope.mensajeLog}"/></td>
            </tr>
        </table>
    </form>
    <a href="index.jsp">Volver al Inicio</a>
</html>
