
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

public class ContactoADO {
    private Conexion conexion;
    private PreparedStatement statement;
    private ResultSet resultset;
    
    public ContactoADO() {
        
    }
    
    public void agregarContacto(Contacto contacto){
        try {
            conexion.abrirConexion();
            
            statement = conexion.getCnn().prepareStatement("INSERT INTO contacto VALUES (0,?,?,?)");
            statement.setString(1, contacto.getRut());
            statement.setString(2, contacto.getEmail());
            statement.setInt(3, contacto.getTelefono());
            statement.executeUpdate();
            
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(ContactoADO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
