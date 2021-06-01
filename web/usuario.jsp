<%@page import="controladores.utiles.Encriptar"%>
<%@page import="coneccion.coneccion"%>
<%@page import="java.security.NoSuchAlgorithmException"%>
<%@page import="java.security.MessageDigest"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%
    String generatedPassword = null;
String user=request.getParameter("email");
String pass=request.getParameter("pass");
Encriptar encriptar=new Encriptar();
        coneccion con=new coneccion();
        String json=con.verificaruser(user,encriptar.encriptacion(pass));
        out.print(json);
%>
