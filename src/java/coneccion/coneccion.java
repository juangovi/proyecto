/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coneccion;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Categorias;
import modelo.Usuario;
import modelo.productos;

/**
 *
 * @author juana
 */
public class coneccion {
    private final static String drv="com.mysql.jdbc.Driver";
	private final static String db="jdbc:mysql://localhost:3306/vaquerosjd";
	private final static String usserandpass="root";
	private Connection cn;
	private ResultSet rs;
	private Statement sm;
	private PreparedStatement pst;
        public coneccion() {
    }
    private void abriscon() throws ClassNotFoundException, SQLException {
		Class.forName(drv);
		cn =DriverManager.getConnection(db,"root","");
		System.out.println("conectado");	
    }
    
    private void cerrarconeccion() throws SQLException {
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
                    String estado=rs.getString("estado");
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
    
    public String verificaruser(String user, String passwordcrip) {
        String json="";
        try {
            abriscon();
            
            String sql="SELECT * FROM usuarios WHERE (nick='"+user+"' OR email='"+user+"') AND password='"+passwordcrip+"'";
            pst=cn.prepareStatement(sql);
            rs=pst.executeQuery();
            boolean primero=true;
        while (rs.next()) {
            if(!primero){
                json+=",";
            }
            primero=false;
            json+="{usuario:'"+rs.getString("id")+"',estado:'"+rs.getString("estado")+"'}";
        }
            cerrarconeccion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(coneccion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(coneccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "["+json+"]";
        
        
    }
    public boolean nuevousuario(Usuario user) throws SQLException, ClassNotFoundException {
       abriscon();
        try {
            
         
        String sql = "INSERT INTO usuarios(nombre, apellidos, nick, email, password, rol, direccion, geoloc, token) VALUES (?,?,?,?,?,?,?,?,?)";
        pst = cn.prepareStatement(sql);
        pst.setString(1, user.getNombre());
        pst.setString(2, user.getApellidos());
        pst.setString(3, user.getNick());
        pst.setString(4, user.getEmail());
        
        pst.setString(5, user.getPassword());
        
        pst.setInt(6, user.getRol());
        pst.setString(7, user.getDireccion());
        pst.setString(8, user.getGeoloc());
        
        pst.setString(9, user.getToken());
        
        
        pst.executeUpdate();
        
        } catch (MySQLIntegrityConstraintViolationException e) {
            cerrarconeccion();
            return false;
        }
        cerrarconeccion();
        return true;
    }
    
    public boolean desbloquearusuariotoken(String token) throws ClassNotFoundException, SQLException{
         abriscon();
        try {
            
         
        String sql = "UPDATE usuarios SET estado=0 WHERE token=?";
        pst = cn.prepareStatement(sql);
        pst.setString(1, token);
        
        
        pst.executeUpdate();
        
        } catch (MySQLIntegrityConstraintViolationException e) {
            cerrarconeccion();
            return false;
        }
        cerrarconeccion();
        return true;
        
    }
    public List<productos> obtenerultimospedidos() throws ClassNotFoundException, SQLException{
        List<productos> listapro=new ArrayList<productos>();
        abriscon();
        String sql="SELECT * FROM productos ORDER BY id DESC LIMIT 0,4;";
         pst=cn.prepareStatement(sql);
         rs=pst.executeQuery();
         while(rs.next()){
             int id=rs.getInt("id");
             int categoria=rs.getInt("categoria");
             String descripcion=rs.getString("descripcion");
             double precio=rs.getDouble("precio");
             String imagen=rs.getString("imagen");
             productos pro=new productos(id, categoria, descripcion, precio, imagen);
             listapro.add(pro);
         }
         cerrarconeccion();
         
        return listapro;
    }
    
    public List<Categorias> obtenercategorias() throws ClassNotFoundException, SQLException{
        List<Categorias> categorias=new ArrayList<Categorias>();
        abriscon();
        String sql="SELECT * FROM categorias";
        pst=cn.prepareStatement(sql);
        rs=pst.executeQuery();
        while (rs.next()) {            
            int id=rs.getInt("id");
            String nombre=rs.getString("nombre");
            String img=rs.getString("img");
            Categorias cat=new Categorias(id, nombre, img);
            categorias.add(cat);
        }
        cerrarconeccion();
        return categorias;
    }
}

