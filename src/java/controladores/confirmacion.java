/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import coneccion.coneccion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Linea_pedido;
import modelo.Pedido;
import modelo.Usuario;

/**
 *
 * @author juana
 */
public class confirmacion extends HttpServlet {

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
            HttpSession sesion = request.getSession();
            ServletContext contexto=getServletContext();
            RequestDispatcher rd;
            coneccion con=new coneccion();
            if (sesion.getAttribute("user") == null) {
                rd=contexto.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }
            Usuario user=(Usuario) sesion.getAttribute("user");
            if (sesion.getAttribute("carrito") == null) {
                rd=contexto.getRequestDispatcher("/catalogo.jsp");
                rd.forward(request, response);
            }
            List<Linea_pedido> lista = (List<Linea_pedido>) sesion.getAttribute("carrito");
            double total=(double) sesion.getAttribute("total");
            Pedido pedido=new Pedido(user.getId(), lista,total);
            pedido.generarcodigopedido();
            
            try {
                con.hacerpedido(pedido);
            } catch (ClassNotFoundException ex) {
                System.out.println("ojo");
                Logger.getLogger(confirmacion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                System.out.println(pedido.getCodigopedido());
                Logger.getLogger(confirmacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            sesion.setAttribute("carrito", null);
            rd=contexto.getRequestDispatcher("/pedidos.jsp");
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
