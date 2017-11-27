/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ProfeFran
 */
public class Solicitudes_EmpresasADO {
    private Conexion conexion;
    private PreparedStatement statement;
    private ResultSet resultset;
    
    public Solicitudes_EmpresasADO(){
        conexion = new Conexion();
        conexion.abrirConexion();
    }
    
    public void agregarEmpresa(Solicitudes_Empresas empresa){
        try {
            statement = conexion.getCnn().prepareStatement("INSERT INTO solicitudes_empresas(nombre, rut, contraseña, direccion, contacto) VALUES (?,?,?,?,?)");

            statement.setString(1, empresa.getNombre());
            statement.setString(2, empresa.getRut());
            statement.setString(3, empresa.getContraseña());
            statement.setString(4, empresa.getDireccion());
            statement.setString(5, empresa.getContacto());
            statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ContactoADO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
