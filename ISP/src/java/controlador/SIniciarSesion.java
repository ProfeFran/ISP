/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Development
 */
@WebServlet(name = "SIniciarSesion", urlPatterns = {"/SIniciarSesion"})
public class SIniciarSesion extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        
        if (request.getParameter("btnNuevoUsuario") != null) 
        {
            response.sendRedirect("tipo_usuario.jsp");
        }
        else if (request.getParameter("btnIngresar") != null) 
        {
            String rut = request.getParameter("txtUsuario");
            String contrasena = request.getParameter("txtContrasena");
            
            String mensajeLog = "(000): No Identificado";
            
            //Mejorar Session y cerrarla
            //Implementar Log4J
            //Formulario para montar Muestra
            //Formulario para resultado
            //Formulario para dar de baja a un usuario
            //Listar muestras por rut
            
            HttpSession actualSesion = request.getSession(true);
            actualSesion.setAttribute("usuario", rut);
            actualSesion.setAttribute("contrasena", contrasena);
            
            if (!rut.trim().isEmpty() && !contrasena.trim().isEmpty())
            {
                Conexion con = new Conexion();
                
                if (esEmpresa(rut))
                {
                    try {
                        actualSesion.setAttribute("tipoCliente", 0);
                        
                        String queryEmpresa = "SELECT rut, contraseña "
                                + "FROM solicitudes_empresas "
                                + "WHERE rut = " + rut + ";";
                        
                        PreparedStatement pse = con.getCnn().prepareStatement(queryEmpresa);
                        
                        ResultSet rse = pse.executeQuery();
                        rse.next();
                        
                        String rutEmpresa = rse.getString("rut");
                        String contraseñaEmpresa = rse.getString("contraseña");
                        
                        try 
                        {
                            if (autentificar(rut, contrasena, rutEmpresa, contraseñaEmpresa))
                            {
                                response.sendRedirect("home.jsp");
                            }
                        } catch (Exception ex) {
                            mensajeLog = ex.getMessage();
                        }
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(SIniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                } 
                else
                {
                    try {
                        actualSesion.setAttribute("tipoCliente", 1);
                        
                        String queryParticular = "SELECT rut, contraseña "
                                + "FROM solicitudes_particulares "
                                + "WHERE rut = " + rut + ";";
                        
                        PreparedStatement psp = con.getCnn().prepareStatement(queryParticular);
                        
                        ResultSet rsp = psp.executeQuery();
                        rsp.next();
                        
                        String rutParticular = rsp.getString("rut");
                        String contraseñaParticular = rsp.getString("contraseña");
                        
                        try 
                        {
                            if (autentificar(rut, contrasena, rutParticular, contraseñaParticular))
                            {
                                response.sendRedirect("home.jsp");
                            }
                        } catch (Exception ex) {
                            mensajeLog = ex.getMessage();
                        }
                        
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(SIniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } 
            else
            {
                mensajeLog = "(003): Campos no pueden estar vacíos";
            }
            
            actualSesion.setAttribute("mensajeLog", mensajeLog);
            response.sendRedirect("error_inicio_sesion.jsp");
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SIniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SIniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private boolean autentificar(String usuario, String contraseña, String usuarioBDD, String contraseñaBDD) throws Exception
    {
        if (!usuario.trim().isEmpty()){
            if (usuario.equalsIgnoreCase(usuarioBDD) && contraseña.equals(contraseñaBDD))
            {
                return true;
            } else {
                throw new Exception("(002): Contraseña equivocada");
            }
        } else {
            throw new Exception("(001): Usuario desconocido");
        }
    }
    
    private boolean esEmpresa(String rut) 
    {
        Conexion con = new Conexion();
        
        String query = "SELECT rut FROM solicitudes_empresas "
                + "WHERE rut = " + rut + ";";
        
        try
        {
            PreparedStatement ps = con.getCnn().prepareStatement(query);   
            ResultSet rs = ps.executeQuery();
            rs.next();
            
            return (rs.getString(1) != null) ? true : false;
        }
        catch (Exception ex)
        {
            Logger.getLogger(SIniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
