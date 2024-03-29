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
public class inicio extends HttpServlet {
    Encriptar encriptar=new Encriptar();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            ServletContext contexto=getServletContext();
            RequestDispatcher rd;
            try {
                
            
            
            HttpSession sesion=request.getSession();
            if (sesion.getAttribute("user")!=null) {
                rd=contexto.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }
             
            String pag=request.getParameter("volver");
            String user=request.getParameter("log");
            String password=request.getParameter("pass");
            coneccion con=new coneccion();
            
            
            Usuario usuario=con.getUser(user,encriptar.encriptacion(password));
            
            if(usuario!=null){
                sesion.setAttribute("user", usuario);
            }
            rd=contexto.getRequestDispatcher("/"+pag);
            rd.forward(request, response);
            } catch (Exception e) {
                rd=contexto.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }
        }
    } 

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
