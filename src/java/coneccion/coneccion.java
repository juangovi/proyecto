/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coneccion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;

/**
 *
 * @author juana
 */
public class coneccion {
    private final static String drv="com.mysql.jdbc.Driver";
	private final static String db="jdbc:mysql://localhost:3306/trebushop";
	private final static String usserandpass="root";
	private Connection cn;
	private ResultSet rs;
	private Statement sm;
	private PreparedStatement pst;
        public coneccion() {
    }
    public void abriscon() throws ClassNotFoundException, SQLException {
		Class.forName(drv);
		cn =DriverManager.getConnection(db,"root","");
		System.out.println("conectado");	
    }
    
    public void cerrarconeccion() throws SQLException {
		// TODO Auto-generated method stub
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
        System.out.println("Conexion cerrada");
    }

    public Usuario getUser(String user, String passwordcrip) {
        Usuario usuario=null;
        try {
            abriscon();
            String sql="SELECT * FROM usuarios WHERE (nick='"+user+"' OR email='"+user+"') AND password='"+passwordcrip+"'";
            pst=cn.prepareStatement(sql);
            rs=pst.executeQuery();
            rs.next();
                    int id=rs.getInt("id");
                    String nombre=rs.getString("nombre");
                    String apellidos=rs.getString("apellidos");
                    String nick=rs.getString("nick");
                    String email=rs.getString("email");
                    String password=rs.getString("password");
                    int rol=rs.getInt("rol");
                    String direccion=rs.getString("direccion");
                    String geoloc=rs.getString("geoloc");
                    String estado=rs.getString("estaado");
                    String imagen=rs.getString("imagen");
                    String token=rs.getString("token");
                    usuario=new Usuario(id, nombre, apellidos, nick, email, password, rol, direccion, geoloc, id, imagen, token);
                    
                    
            cerrarconeccion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(coneccion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(coneccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }
}

