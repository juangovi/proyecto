/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import coneccion.coneccion;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import modelo.Tallas;

/**
 *
 * @author juana
 */
@MultipartConfig
public class modificarproducto extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    static final int CHUNK_SIZE = 1024 * 1000;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
             coneccion con = new coneccion();
             String proid = request.getParameter("idpro");
            List<Tallas> tallas = con.obtenertodatallas(proid);
            if (request.getPart("foto").getSize() > 0) {
                //Nos aseguramos que el archivo es una imagen y que no excece de unos 8mb
                if (request.getPart("foto").getContentType().contains("image") == false
                        || request.getPart("foto").getSize() > 8388608) {
                    // TIPO DE ARCHIVO NO VALIDO
                    System.out.println("mal1");

                    return;
                } else {
                    String name = "/" + new Timestamp(System.currentTimeMillis()).toString();
                    name = name.replaceAll(" ", "");
                    name = name.replaceAll("\\.", "_");
                    name = name.replaceAll(":", "_");
                    if (request.getPart("foto").getContentType().contains("png")) {
                        name += ".png";
                    } else {
                        name += ".jpg";
                    }
                    //Obtenemos la ruta absoluta del sistema donde queremos guardar la imagen
                    String fileName = this.getServletContext().getRealPath("/img/productos");
                    System.out.println(fileName);
                    //Guardamos la imagen en disco con la ruta que hemos obtenido en el paso anterior
                    boolean ok = guardarImagenDeProdructoEnElSistemaDeFicheros(request.getPart("foto").getInputStream(), fileName + name);
                    if (ok == false) {
                        System.out.println("mal");

                        return;
                    }
                    
                   String sql = "UPDATE productos SET ";

                        boolean first = true;

                        if (request.getParameter("descripcion") != null && !request.getParameter("descripcion").equals("")) {
                            if (!first) {
                                sql += ",";
                            }
                            first = false;
                            sql += "descripcion='" + request.getParameter("descripcion") + "' ";

                        }
                        if (request.getParameter("categoria") != null && !request.getParameter("categoria").equals("")) {
                            if (!first) {
                                sql += ",";
                            }
                            first = false;
                            sql += "categoria='" + request.getParameter("categoria") + "' ";

                        }
                        if (request.getParameter("precio") != null && !request.getParameter("precio").equals("")) {
                            if (!first) {
                                sql += ",";
                            }
                            first = false;
                            sql += "precio='" + request.getParameter("precio") + "' ";

                        }
                        if (name != null && !name.equals("")) {
                            if (!first) {
                                sql += ",";
                            }
                            first = false;
                            sql += "imagen='" + "img/productos" + name+ "' ";

                        }

                        sql += "WHERE id=" + request.getParameter("idpro");
                        System.out.println(sql);
                        con.modificacion(sql);
                        for (Tallas talla : tallas) {
                            sql = "UPDATE tallas SET cantidad=" + request.getParameter("" + talla.getId()) + " WHERE id=" + talla.getId();
                            con.modificacion(sql);
                        }
                }
            }else{
                 String sql = "UPDATE productos SET ";

                        boolean first = true;

                        if (request.getParameter("descripcion") != null && !request.getParameter("descripcion").equals("")) {
                            if (!first) {
                                sql += ",";
                            }
                            first = false;
                            sql += "descripcion='" + request.getParameter("descripcion") + "' ";

                        }
                        if (request.getParameter("categoria") != null && !request.getParameter("categoria").equals("")) {
                            if (!first) {
                                sql += ",";
                            }
                            first = false;
                            sql += "categoria='" + request.getParameter("categoria") + "' ";

                        }
                        if (request.getParameter("precio") != null && !request.getParameter("precio").equals("")) {
                            if (!first) {
                                sql += ",";
                            }
                            first = false;
                            sql += "precio='" + request.getParameter("precio") + "' ";

                        }

                        sql += "WHERE id=" + request.getParameter("idpro");
                        System.out.println(sql);
                        con.modificacion(sql);
                        for (Tallas talla : tallas) {
                            sql = "UPDATE tallas SET cantidad=" + request.getParameter("" + talla.getId()) + " WHERE id=" + talla.getId();
                            con.modificacion(sql);
                        }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(modificarproducto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(modificarproducto.class.getName()).log(Level.SEVERE, null, ex);
        }    }

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

    private boolean guardarImagenDeProdructoEnElSistemaDeFicheros(InputStream input, String fileName) {
        FileOutputStream output = null;
        System.out.println(fileName);
        boolean ok = false;
        try {
            output = new FileOutputStream(fileName);
            int leido = 0;
            leido = input.read();
            while (leido != -1) {
                output.write(leido);
                leido = input.read();
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println("mal 2");
        } finally {
            try {
                output.flush();
                output.close();
                input.close();
                ok = true;
            } catch (IOException ex) {

            }
        }
        return ok;
    }

}
