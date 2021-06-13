/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import coneccion.coneccion;
import controladores.utiles.Encriptar;
import controladores.utiles.enviarcorreo;
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
import modelo.Usuario;

/**
 *
 * @author juana
 */
public class nuevousuarioadmin extends HttpServlet {

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
        Encriptar encriptar = new Encriptar();
        coneccion con = new coneccion();
        ServletContext contexto = getServletContext();
        RequestDispatcher rd;
        enviarcorreo correo = new enviarcorreo();
        try (PrintWriter out = response.getWriter()) {
            String usuario = request.getParameter("usuario");
            String Email = request.getParameter("Email");
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String password = request.getParameter("password");
            String geo = request.getParameter("geo");
            String direccion = request.getParameter("direccion");
            String enpass = encriptar.encriptacion(password);
            int rol = Integer.parseInt(request.getParameter("rol"));
            Usuario user = new Usuario(nombre, apellido, usuario, Email, enpass, direccion, geo, encriptar.encriptacion(usuario + enpass + Email), rol);

            if (con.nuevousuario(user)) {
                correo.enviarverificacion(Email, user.getToken());
                rd = contexto.getRequestDispatcher("/usuariocreado.html");
                rd.forward(request, response);
            } else {

                rd = contexto.getRequestDispatcher("/iniciarSession.jsp?error=0");
                rd.forward(request, response);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(nuevousuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(nuevousuario.class.getName()).log(Level.SEVERE, null, ex);
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
