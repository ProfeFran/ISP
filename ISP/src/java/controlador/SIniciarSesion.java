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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        if (request.getParameter("btnNuevoUsuario") != null) 
        {
            response.sendRedirect("tipo_usuario.jsp");
        }
        else if (request.getParameter("btnIngresar") != null) 
        {
            String rut = request.getParameter("txtUsuario");
            String contraseña = request.getParameter("txtContraseña");
            
            String mensajeLog = "(000): No Identificado";
            
            HttpSession actualSesion = request.getSession(true);
            actualSesion.setAttribute("usuario", rut);
            actualSesion.setAttribute("contraseña", contraseña);
            
            if (!rut.trim().isEmpty() || !contraseña.trim().isEmpty())
            {
                Conexion con = new Conexion();
                
                try
                {
                    String queryEmpresa = "SELECT rut, contraseña "
                            + "FROM solicitudes_empresas "
                            + "WHERE rut = '" + rut + "';";
                    String queryParticular = "SELECT rut, contraseña "
                            + "FROM solicitudes_particulares "
                            + "WHERE rut = '" + rut + "';";
                    
                    PreparedStatement pse = con.getCnn().prepareStatement(queryEmpresa);
                    PreparedStatement psp = con.getCnn().prepareStatement(queryParticular);
                    
                    ResultSet rse = pse.executeQuery();
                    rse.next();
                    ResultSet rsp = psp.executeQuery();
                    rsp.next();
                    
                    String rutEmpresa = rse.getString("rut");
                    String rutParticular = rsp.getString("rut");
                    
                    String contraseñaEmpresa = rse.getString("contraseña");
                    String contraseñaParticular = rsp.getString("contraseña");
                    
                    if (!rutEmpresa.trim().isEmpty() || !rutParticular.trim().isEmpty())
                    {
                        if ((rut.equalsIgnoreCase(rutEmpresa) && contraseña.equals(contraseñaEmpresa))
                                || (rut.equalsIgnoreCase(rutParticular) && contraseña.equals(contraseñaParticular)))
                        {
                            // SI ES EMPRESA LA VARIABLE tipoCliente ES 0, SINO ES 1
                            // CON ESO PODEMOS HACER TODAS LAS VALIDACIONES MAS ADELANTE
                            if (esEmpresa(rut))
                            {
                                actualSesion.setAttribute("tipoCliente", 0);
                            } 
                            else
                            {
                                actualSesion.setAttribute("tipoCliente", 1);
                            }
                            response.sendRedirect("home.jsp");
                        } 
                        else 
                        {
                            mensajeLog = "(002): Contraseña equivocada";
                        }
                    } 
                    else
                    {
                        mensajeLog = "(001): Usuario desconocido";
                    }  
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(SIniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } 
            else
            {
                mensajeLog = "(003): Campos no pueden estar vacíos";
            }
            
            actualSesion.setAttribute("mensajeLog", mensajeLog);
            response.sendRedirect("error.jsp");
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

    private boolean esEmpresa(String rut) 
    {
        Conexion con = new Conexion();
        
        String query = "SELECT rut FROM solicitudes_empresas "
                + "WHERE rut = '" + rut + "';";
        
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
