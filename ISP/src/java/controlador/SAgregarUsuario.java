
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Conexion;
import modelo.Solicitudes_Empresas;
import modelo.Solicitudes_EmpresasADO;
import modelo.Solicitudes_Particulares;
import modelo.Solicitudes_ParticularesADO;

/**
 *
 * @author Development
 */

@WebServlet(name = "SAgregarUsuario", urlPatterns = {"/SAgregarUsuario"})
public class SAgregarUsuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession actualSesion = request.getSession(true);
        
        if (request.getParameter("btnAgregarEmpresa") != null)
        {
            String nombreEmpresa = request.getParameter("txtNombreEmpresa");
            String rutEmpresa = request.getParameter("txtRutEmpresa");
            String contrasenaEmpresa = request.getParameter("txtContrasena");
            String direccionEmpresa = request.getParameter("txtDireccion");
            String rutContacto = request.getParameter("txtRutContacto");
            
            String mensajeLog = "(000): No Identificado";
            
            if (!nombreEmpresa.trim().isEmpty() && !rutEmpresa.trim().isEmpty() && !contrasenaEmpresa.trim().isEmpty())
            {
                try
                {
                    Solicitudes_Empresas nuevaSolicitud = new Solicitudes_Empresas();
                    Solicitudes_EmpresasADO ad = new Solicitudes_EmpresasADO();
                    
                    nuevaSolicitud.setNombre(nombreEmpresa);
                    nuevaSolicitud.setRut(rutEmpresa);
                    nuevaSolicitud.setContraseña(contrasenaEmpresa);
                    nuevaSolicitud.setDireccion(direccionEmpresa);
                    nuevaSolicitud.setContacto(rutContacto);
                    
                    ad.agregarEmpresa(nuevaSolicitud);
                    
                    mensajeLog = "Nuevo Usuario registrado";
                    
                    actualSesion.setAttribute("mensajeLog", mensajeLog);
                    response.sendRedirect("exito.jsp");
                }
                catch (IOException | NumberFormatException ex)
                {
                    mensajeLog = ex.getMessage();
                }
            } else {
                mensajeLog = "Los campos no pueden estar vacíos";
                    
                actualSesion.setAttribute("mensajeLog", mensajeLog);
                response.sendRedirect("error_inicio_sesion.jsp");
            }
            
        } 
        else if (request.getParameter("btnAgregarParticular") != null)
        {
            String nombre = request.getParameter("txtNombre");
            String rut = request.getParameter("txtRut");
            String contrasena = request.getParameter("txtContrasena");
            String direccion = request.getParameter("txtDireccion");
            String telefono = request.getParameter("txtTelefono");
            
            String mensajeLog = "(000): No Identificado";
            
            if (!nombre.trim().isEmpty() && !rut.trim().isEmpty() && !contrasena.trim().isEmpty())
            {
                try
                {
                    Solicitudes_Particulares nuevaSolicitud = new Solicitudes_Particulares();
                    Solicitudes_ParticularesADO ad = new Solicitudes_ParticularesADO();
                    
                    nuevaSolicitud.setNombre(nombre);
                    nuevaSolicitud.setRut(rut);
                    nuevaSolicitud.setContraseña(contrasena);
                    nuevaSolicitud.setDireccion(direccion);
                    nuevaSolicitud.setTelefono(Integer.parseInt(telefono));
                    
                    ad.agregarParticular(nuevaSolicitud);
                    
                    mensajeLog = "Nuevo Usuario registrado";
                    
                    actualSesion.setAttribute("mensajeLog", mensajeLog);
                    response.sendRedirect("exito.jsp");
                }
                catch (IOException | NumberFormatException ex)
                {
                    mensajeLog = ex.getMessage();
                }
            } else {
                mensajeLog = "Los campos no pueden estar vacíos";
                    
                actualSesion.setAttribute("mensajeLog", mensajeLog);
                response.sendRedirect("error_inicio_sesion.jsp");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
