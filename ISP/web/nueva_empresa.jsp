
<%-- 
    Document   : nueva_empresa
    Created on : 26-11-2017, 21:35:17
    Author     : ProfeFran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NUEVA EMPRESA</title>
    </head>
    <body>
        <form action="SAgregarUsuario" method="POST">
            <table border="1" align="center">
                <tr>
                    <td colspan="2" align="center"><h3>NUEVA EMPRESA</h3></td>
                </tr>
                <tr>
                    <td>Nombre Empresa:</td>
                    <td><input type="text" name="txtNombreEmpresa" /></td>
                </tr>
                <tr>
                    <td>Rut Empresa:</td>
                    <td><input type="text" name="txtRutEmpresa" /></td>
                </tr>
                <tr>
                    <td>Contraseña:</td>
                    <td><input type="password" name="txtContrasena" /></td>
                </tr>
                <tr>
                    <td>Direccion:</td>
                    <td><input type="text" name="txtDireccion" /></td>
                </tr>
                <tr>
                    <td>Rut de Contacto:</td>
                    <td><input type="text" name="txtRutContacto" /></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" name="btnAgregarEmpresa" value="Agregar"></td>
                </tr>
            </table>
        </form>
        <a href="index.jsp">Volver al Inicio</a>
    </body>
</html>

