<%-- 
    Document   : index
    Created on : 26-nov-2017, 15:09:04
    Author     : Development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>INDEX</title>
    </head>
    <body>       
        <form action="SIniciarSesion" method="POST">
            <table border="1" align="center">
                <tr>
                    <td colspan="2" align="center"><h3>BIENVENIDO A ISP</h3></td>
                </tr>
                <tr>
                    <td>Rut: </td>
                    <td><input type="text" name="txtUsuario" /></td>
                </tr>
                <tr>
                    <td>Contraseña: </td>
                    <td><input type="password" name="txtContrasena" /></td>
                </tr>
                <tr>
                    <td><input type="submit" name="btnNuevoUsuario" value="Nuevo Usuario"/></td>
                    <td><input type="submit" name="btnIngresar" value="Ingresar"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>
