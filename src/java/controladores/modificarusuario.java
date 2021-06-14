/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import coneccion.coneccion;
import controladores.utiles.Encriptar;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Usuario;

/**
 *
 * @author juana
 */
public class modificarusuario extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String sql = "UPDATE usuarios SET ";
            HttpSession sesion = request.getSession();
            coneccion con = new coneccion();
            ServletContext contexto=getServletContext();
            RequestDispatcher rd;
            Encriptar en=new Encriptar();
            boolean first=true;
             if (sesion.getAttribute("user") == null) {
                rd=contexto.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }
             Usuario user=(Usuario) sesion.getAttribute("user");
            if (request.getParameter("nombre") != null && !request.getParameter("nombre").equals("")) {
                if(!first){
                    sql+=",";
                }
                first=false;
                sql += "nombre='" + request.getParameter("nombre") + "' ";
                user.setNombre(request.getParameter("nombre"));
            }
            if (request.getParameter("apellido") != null && !request.getParameter("apellido").equals("")) {
                if(!first){
                    sql+=",";
                }
                first=false;
                sql += "apellidos='" + request.getParameter("apellido") + "' ";
                user.setApellidos(request.getParameter("apellido"));
            }
            if (request.getParameter("direccion") != null && !request.getParameter("direccion").equals("")) {
                if(!first){
                    sql+=",";
                }
                first=false;
                sql += "direccion='" + request.getParameter("direccion") + "' ";
                user.setDireccion(request.getParameter("direccion"));
            }
            if (request.getParameter("password") != null && !request.getParameter("password").equals("")) {
                if(!first){
                    sql+=",";
                }
                first=false;
                sql += "password='" + en.encriptacion(request.getParameter("password")) + "' ";
            }
            sql += "WHERE id=" + user.getId();
            System.out.println(sql);
            try {
                con.modificacion(sql);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(modificarusuario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(modificarusuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            rd=contexto.getRequestDispatcher("/modificarperfil.jsp");
            rd.forward(request, response);
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
