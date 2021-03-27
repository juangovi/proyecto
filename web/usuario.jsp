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


        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            //Add password bytes to digest
            md.update(pass.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
    String drv="com.mysql.jdbc.Driver";
    String db="jdbc:mysql://localhost:3306/vaquerosjd";
    Connection cn;
    ResultSet rs=null;
    Statement sm=null;
    PreparedStatement pst=null;
    Class.forName(drv);
    String json="";
    cn =DriverManager.getConnection(db,"root","");
     String sql="SELECT * FROM usuarios WHERE (nick='"+user+"' OR email='"+user+"') AND password='"+generatedPassword+"'";
        pst=cn.prepareStatement(sql);
        rs=pst.executeQuery();
        boolean primero=true;
        while (rs.next()) {
            if(!primero){
                json+=",";
            }
            primero=false;
            json+="{usuario:'"+rs.getString("id")+"'}";
        }
    if (rs!=null) {
            rs.close();
        }
        if (sm!=null) {
            sm.close();
        }
        if (pst!=null) {
            pst.close();
        }
        if (cn!=null) {
            cn.close();
        }
        out.print("["+json+"]");
%>
