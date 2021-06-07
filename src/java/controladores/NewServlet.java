/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author juana
 */
public class NewServlet extends HttpServlet {

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
            Random rand = new Random();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
           for (int i = 1; i <= 100; i++) {
                //INSERT INTO `tallas` (`id`, `talla`, `cantidad`, `producto`) VALUES (NULL, 'XXS', '23', '1'), (NULL, 'XS', '14', '1'), (NULL, 'S', '32', '1'), (NULL, 'M', '48', '1'), (NULL, 'L', '36', '1'), (NULL, 'XL', '12', '1'), (NULL, 'XL', '44', '1');
                out.println("INSERT INTO `tallas` (`id`, `talla`, `cantidad`, `producto`) VALUES (NULL, 'XXS', '"+rand.nextInt(50)+"', '"+i+"'), (NULL, 'XS', '"+rand.nextInt(50)+"', '"+i+"'), (NULL, 'S', '"+rand.nextInt(50)+"', '"+i+"'), (NULL, 'M', '"+rand.nextInt(50)+"', '"+i+"'), (NULL, 'L', '"+rand.nextInt(50)+"', '"+i+"'), (NULL, 'XL', '"+rand.nextInt(50)+"', '"+i+"'), (NULL, 'XL', '"+rand.nextInt(50)+"', '"+i+"');<br>");  
            }
            out.println("</body>");
            out.println("</html>");
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
