<%-- 
    Document   : masterPage
    Created on : 23-nov-2017, 22:50:18
    Author     : Development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MASTER PAGE</title>
    </head>
    <body>
        <form action="SMaster" method="POST">
        <table>
            <tr>
                <td><input type="submit" name="btnAgregarMuestra" value="Agregar Muestra"></td>
                <td><input type="submit" name="btnListarMuestras" value="Listar Muestras"></td>
                <td><input type="submit" name="btnSalir" value="Salir"></td>
            </tr>
        </table>
        </form>
    </body>
</html>
