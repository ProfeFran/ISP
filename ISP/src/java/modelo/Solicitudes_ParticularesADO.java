
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

public class Solicitudes_ParticularesADO {
    private Conexion conexion;
    private PreparedStatement statement;
    private ResultSet resultset;
    
    public Solicitudes_ParticularesADO(){
        conexion = new Conexion();
        conexion.abrirConexion();
    }
    
    public void agregarParticular(Solicitudes_Particulares particular){
        try {
            statement = conexion.getCnn().prepareStatement("INSERT INTO solicitudes_particulares(nombre, rut, contraseña, direccion, telefono) VALUES (?,?,?,?,?)");

            statement.setString(1, particular.getNombre());
            statement.setString(2, particular.getRut());
            statement.setString(3, particular.getContraseña());
            statement.setString(4, particular.getDireccion());
            statement.setInt(5, particular.getTelefono()); 
            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ContactoADO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
