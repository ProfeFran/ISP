<%-- 
    Document   : tipo_usuario
    Created on : 26-nov-2017, 15:25:41
    Author     : Development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TIPO USUARIO</title>
    </head>
    <body>
        <form action="SElegirUsuario" method="POST">
            <table border="1" align="center">
                <tr>
                    <td colspan="2" align="center"><h3>SELECCIONE TIPO DE USUARIO</h3></td>
                </tr>
                <tr>
                    <td><input type="submit" name="btnParticular" value="Particular"/></td>
                    <td><input type="submit" name="btnEmpresa" value="Empresa"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>
