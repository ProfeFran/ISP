
package modelo;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ProfeFran
 */

public class Conexion {
    private Connection cnn;
    private Conexion instancia;
    
    public Conexion() {
        String driver="com.mysql.jdbc.Driver";
        String url="jdbc:mysql://localhost:3306/ISP";
        String usu="root";
        String pass="";
        
        try {          
            Class.forName(driver);
            cnn = (Connection) DriverManager.getConnection(url, usu, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection getCnn() {
        return cnn;
    }
    
    public Conexion abrirConexion(){
        if (instancia == null){
            instancia = new Conexion();
        } 
        return instancia;
    }
    
    public void cerrarConexion(){
        try {
            cnn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
