/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Linea_pedido;

/**
 *
 * @author juana
 */
public class eliminarcarrito extends HttpServlet {

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
            ServletContext contexto = getServletContext();
            RequestDispatcher rd;
            if (sesion.getAttribute("user") == null) {
                rd = contexto.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }
            if (sesion.getAttribute("carrito") == null) {
                List<Linea_pedido> lista = new ArrayList<Linea_pedido>();
                sesion.setAttribute("carrito", lista);
            }
            List<Linea_pedido> lista = (List<Linea_pedido>) sesion.getAttribute("carrito");
            int proid = Integer.parseInt(request.getParameter("del"));
            for (Linea_pedido lista1 : lista) {
                if (lista1.getTalla() == proid) {
                    if (lista1.getCantidad() > 1) {
                        lista1.setCantidad(lista1.getCantidad() - 1);
                    } else if (lista1.getCantidad() <= 1) {
                        lista.remove(lista1);
                    }
                }

                sesion.setAttribute("carrito", lista);
                rd = contexto.getRequestDispatcher("/carrito.jsp");
                rd.forward(request, response);
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
