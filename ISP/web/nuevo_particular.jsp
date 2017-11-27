
<%-- 
    Document   : nuevo_particular
    Created on : 26-11-2017, 21:34:56
    Author     : ProfeFran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NUEVO PARTICULAR</title>
    </head>
    <body>
        <form action="SAgregarUsuario" method="POST">
            <table border="1" align="center">
                <tr>
                    <td colspan="2" align="center"><h3>NUEVO PARTICULAR</h3></td>
                </tr>
                <tr>
                    <td>Nombre:</td>
                    <td><input type="text" name="txtNombre" /></td>
                </tr>
                <tr>
                    <td>Rut:</td>
                    <td><input type="text" name="txtRut" /></td>
                </tr>
                <tr>
                    <td>Contraseña:</td>
                    <td><input type="password" name="txtContrasena" /></td>
                </tr>
                <tr>
                    <td>Dirección:</td>
                    <td><input type="text" name="txtDireccion" /></td>
                </tr>
                <tr>
                    <td>Teléfono:</td>
                    <td><input type="text" name="txtTelefono" /></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" name="btnAgregarParticular" value="Agregar"></td>
                </tr>
            </table>
        </form>
        <a href="index.jsp">Volver al Inicio</a>
    </body>
</html>


