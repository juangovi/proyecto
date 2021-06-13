/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coneccion;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import modelo.Categorias;
import modelo.Linea_pedido;
import modelo.Paginacion;
import modelo.Pedido;
import modelo.Tallas;
import modelo.Usuario;
import modelo.productos;

/**
 *
 * @author juana
 */
public class coneccion {

    private final static String drv = "com.mysql.jdbc.Driver";
    private final static String db = "jdbc:mysql://localhost:3306/vaquerosjd";
    private final static String usserandpass = "root";
    private Connection cn;
    private ResultSet rs;
    private Statement sm;
    private PreparedStatement pst;

    public coneccion() {
    }

    private void abriscon() throws ClassNotFoundException, SQLException {
        Class.forName(drv);
        //cn = DriverManager.getConnection(db, "super", "trebujena");
        cn = DriverManager.getConnection(db, "root", "");
        System.out.println("conectado");
    }

    private void cerrarconeccion() throws SQLException {
        // TODO Auto-generated method stub
        if (rs != null) {
            rs.close();
        }
        if (sm != null) {
            sm.close();
        }
        if (pst != null) {
            pst.close();
        }
        if (cn != null) {
            cn.close();
        }
        System.out.println("Conexion cerrada");
    }

    public Usuario getUser(String user, String passwordcrip) {
        Usuario usuario = null;
        try {
            abriscon();
            String sql = "SELECT * FROM usuarios WHERE (nick='" + user + "' OR email='" + user + "') AND password='" + passwordcrip + "'";
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String apellidos = rs.getString("apellidos");
            String nick = rs.getString("nick");
            String email = rs.getString("email");
            String password = rs.getString("password");
            int rol = rs.getInt("rol");
            String direccion = rs.getString("direccion");
            String geoloc = rs.getString("geoloc");
            String estado = rs.getString("estado");
            String imagen = rs.getString("imagen");
            String token = rs.getString("token");
            usuario = new Usuario(id, nombre, apellidos, nick, email, password, rol, direccion, geoloc, id, imagen, token);

            cerrarconeccion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(coneccion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(coneccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

    public String verificaruser(String user, String passwordcrip) {
        String json = "";
        try {
            abriscon();

            String sql = "SELECT * FROM usuarios WHERE (nick='" + user + "' OR email='" + user + "') AND password='" + passwordcrip + "'";
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            boolean primero = true;
            while (rs.next()) {
                if (!primero) {
                    json += ",";
                }
                primero = false;
                json += "{usuario:'" + rs.getString("id") + "',estado:'" + rs.getString("estado") + "'}";
            }
            cerrarconeccion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(coneccion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(coneccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "[" + json + "]";

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

    public boolean desbloquearusuariotoken(String token) throws ClassNotFoundException, SQLException {
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

    public List<productos> obtenerultimospedidos() throws ClassNotFoundException, SQLException {
        List<productos> listapro = new ArrayList<productos>();
        abriscon();
        String sql = "SELECT * FROM productos ORDER BY id DESC LIMIT 0,4;";
        pst = cn.prepareStatement(sql);
        rs = pst.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            int categoria = rs.getInt("categoria");
            String descripcion = rs.getString("descripcion");
            double precio = rs.getDouble("precio");
            String imagen = rs.getString("imagen");
            productos pro = new productos(id, categoria, descripcion, precio, imagen);
            listapro.add(pro);
        }
        cerrarconeccion();

        return listapro;
    }

    public List<Categorias> obtenercategorias() throws ClassNotFoundException, SQLException {
        List<Categorias> categorias = new ArrayList<Categorias>();
        abriscon();
        String sql = "SELECT * FROM categorias";
        pst = cn.prepareStatement(sql);
        rs = pst.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String img = rs.getString("img");
            Categorias cat = new Categorias(id, nombre, img);
            categorias.add(cat);
        }
        cerrarconeccion();
        return categorias;
    }

    public Paginacion todoslosproductospaginados(HttpServletRequest request, List<Categorias> categorias) throws ClassNotFoundException, SQLException {
        List<productos> listapro = new ArrayList<productos>();
        abriscon();
        int resporpag = 9;
        int pag;
        String filtros = "";
        if (request.getParameter("boton") != null) {
            filtros += "WHERE";
            boolean primero = true;
            for (Categorias cat : categorias) {
                if (request.getParameter("categoria" + cat.getId()) != null) {
                    if (primero) {
                        primero = false;
                    } else {
                        filtros += "OR";
                    }
                    String id = request.getParameter("categoria" + cat.getId());
                    filtros += " categoria=" + id + " ";
                }

            }
            if (request.getParameter("busqueda") != null) {
                if (!primero) {
                    filtros += "AND";
                }
                String busqueda = request.getParameter("busqueda");
                filtros += " descripcion LIKE '%" + busqueda + "%' ";
            }
        }
        if (request == null || request.getParameter("pag") == null) {
            pag = 1;
        } else {
            pag = Integer.parseInt(request.getParameter("pag"));
        }
        String sql = "SELECT COUNT(*) res FROM productos " + filtros;
        System.out.println(sql);
        pst = cn.prepareStatement(sql);
        rs = pst.executeQuery();
        rs.next();
        int numpag = rs.getInt("res") / resporpag;
        if ((rs.getInt("res") % resporpag) > 0) {
            numpag++;
        }
        int firspag = (pag - 1) * resporpag;
        sql = "SELECT * FROM productos " + filtros + " LIMIT " + firspag + " , " + resporpag;
        pst = cn.prepareStatement(sql);
        rs = pst.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            int categoria = rs.getInt("categoria");
            String descripcion = rs.getString("descripcion");
            double precio = rs.getDouble("precio");
            String imagen = rs.getString("imagen");
            productos pro = new productos(id, categoria, descripcion, precio, imagen);
            listapro.add(pro);
        }
        cerrarconeccion();
        Paginacion paginacion = new Paginacion(listapro, numpag, pag);
        return paginacion;
    }

    public productos obtenerproducto(String id) throws ClassNotFoundException, SQLException {
        productos pro = null;
        abriscon();
        String sql = "SELECT * FROM productos WHERE id = " + id;
        pst = cn.prepareStatement(sql);
        rs = pst.executeQuery();
        while (rs.next()) {
            int id2 = rs.getInt("id");
            int categoria = rs.getInt("categoria");
            String descripcion = rs.getString("descripcion");
            double precio = rs.getDouble("precio");
            String imagen = rs.getString("imagen");
            pro = new productos(id2, categoria, descripcion, precio, imagen);

        }
        cerrarconeccion();
        return pro;
    }

    public List<Tallas> obtenertallas(int id) throws ClassNotFoundException, SQLException {
        abriscon();
        String sql = "SELECT id,talla FROM tallas WHERE producto=" + id + " and cantidad>0 GROUP BY talla";
        pst = cn.prepareStatement(sql);
        rs = pst.executeQuery();
        List<Tallas> lista = new ArrayList<Tallas>();

        while (rs.next()) {

            Tallas tallas = new Tallas(rs.getInt("id"), rs.getString("talla"));
            lista.add(tallas);
        }
        cerrarconeccion();
        return lista;
    }

    public Tallas obtenertalla(int id) throws ClassNotFoundException, SQLException {
        abriscon();
        String sql = "SELECT * FROM tallas WHERE id=" + id + "";
        pst = cn.prepareStatement(sql);
        rs = pst.executeQuery();
        List<Tallas> lista = new ArrayList<Tallas>();
        Tallas tallas = null;
        while (rs.next()) {

            tallas = new Tallas(rs.getInt("id"), rs.getString("talla"));

        }
        cerrarconeccion();
        return tallas;
    }

    public boolean hacerpedido(Pedido pedido) throws ClassNotFoundException, SQLException {
        abriscon();
        String sql = "INSERT INTO pedidos(usuario, fecha, estado, codigopedido, total) VALUES (?,?,?,?,?)";
        pst = cn.prepareStatement(sql);
        pst.setInt(1, pedido.getUsuario());
        pst.setDate(2, pedido.getFecha());
        pst.setString(3, pedido.getEstado());
        pst.setString(4, pedido.getCodigopedido());
        pst.setDouble(5, pedido.getTotal());
        pst.executeUpdate();
        for (Linea_pedido lp : pedido.getLinea_pedidos()) {
            sql = "INSERT INTO `linea_pedidos`(codigopedido, producto, cantidad) VALUES (?,?,?)";
            pst = cn.prepareStatement(sql);
            pst.setString(1, lp.getCodigopedido());
            pst.setInt(2, lp.getTalla());
            pst.setInt(3, lp.getCantidad());
            pst.executeUpdate();
            sql = "UPDATE tallas SET cantidad=(SELECT cantidad FROM tallas WHERE id = " + lp.getTalla() + ")-1 WHERE id =" + lp.getTalla();
            pst = cn.prepareStatement(sql);
            pst.executeUpdate();
        }

        cerrarconeccion();
        return true;
    }

    public List<Pedido> obtenerpedidoscliente(int usu) throws ClassNotFoundException, SQLException {
        abriscon();
        String sql = "SELECT * FROM pedidos WHERE usuario =" + usu + " ORDER BY fecha DESC";
        System.out.println(sql);
        pst = cn.prepareStatement(sql);
        rs = pst.executeQuery();
        List<Pedido> lista = new ArrayList<Pedido>();

        while (rs.next()) {
            int id = rs.getInt("id");
            int usuario = rs.getInt("usuario");
            Date fecha = rs.getDate("fecha");
            String estado = rs.getString("estado");
            String codigopedido = rs.getString("codigopedido");
            double total = rs.getDouble("total");
            Pedido pedido = new Pedido(id, usuario, fecha, estado, codigopedido, total);
            lista.add(pedido);
        }
        for (Pedido lista1 : lista) {
            sql = "SELECT * FROM linea_pedidos WHERE codigopedido='" + lista1.getCodigopedido() + "'";
            System.out.println(sql);
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            List<Linea_pedido> listalp = new ArrayList<Linea_pedido>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String codigopedido = rs.getString("codigopedido");
                int talla = rs.getInt("producto");
                int cantidad = rs.getInt("cantidad");
                Linea_pedido lp = new Linea_pedido(id, codigopedido, talla, cantidad);
                listalp.add(lp);
            }
            lista1.setLinea_pedidos(listalp);
        }
        for (Pedido lista1 : lista) {
            for (Linea_pedido listalp1 : lista1.getLinea_pedidos()) {
                sql = "SELECT * FROM tallas WHERE id=" + listalp1.getTalla();
                System.out.println(sql);
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                rs.next();
                listalp1.setproducto(rs.getInt("producto"));
            }
        }

        cerrarconeccion();
        return lista;
    }

    public boolean cancelarped(int id) throws ClassNotFoundException, SQLException {
        abriscon();
        try {

            String sql = "UPDATE pedidos SET estado='cancelado' WHERE id=?";
            pst = cn.prepareStatement(sql);
            pst.setInt(1, id);

            pst.executeUpdate();

        } catch (MySQLIntegrityConstraintViolationException e) {
            cerrarconeccion();
            return false;
        }
        cerrarconeccion();
        return true;

    }

    public boolean modificacion(String sql) throws ClassNotFoundException, SQLException {
        abriscon();
        try {

            pst = cn.prepareStatement(sql);
            pst.executeUpdate();

        } catch (MySQLIntegrityConstraintViolationException e) {
            cerrarconeccion();
            return false;
        }
        cerrarconeccion();
        return true;
    }

    public List<Pedido> obtenerpedidos(HttpServletRequest request) throws ClassNotFoundException, SQLException {
        abriscon();
        String filtro = "";
        if (request.getParameter("buqueda") != null) {

            String fil = "";
            List<String> filtros = new ArrayList<String>();
            if (request.getParameter("fecha1") != null && !request.getParameter("fecha1").equals("")) {
                filtros.add(request.getParameter("fecha1"));
            }
            if (request.getParameter("fecha2") != null && !request.getParameter("fecha2").equals("")) {
                filtros.add(request.getParameter("fecha2"));
            }
            System.out.println(filtros.size());
            if (filtros.size() > 1) {
                fil += "fecha BETWEEN '" + request.getParameter("fecha1") + "' AND '" + request.getParameter("fecha2") + "'";
            } else if (!filtros.isEmpty()) {
                fil += "fecha ='" + filtros.get(0) + "'";
            }
            if (request.getParameter("usuario") != null && !request.getParameter("usuario").equals("")) {
                if (!filtros.isEmpty()) {
                    fil += " AND ";
                }
                fil += "usuario=(SELECT id FROM usuarios WHERE nick LIKE '%" + request.getParameter("usuario") + "%')";
            }
            if (!fil.equals("")) {
                filtro = " WHERE " + fil;
            }
        }
        String sql = "SELECT * FROM pedidos" + filtro + " ORDER BY fecha DESC";
        System.out.println(sql);
        pst = cn.prepareStatement(sql);
        rs = pst.executeQuery();
        List<Pedido> lista = new ArrayList<Pedido>();

        while (rs.next()) {
            int id = rs.getInt("id");
            int usuario = rs.getInt("usuario");
            Date fecha = rs.getDate("fecha");
            String estado = rs.getString("estado");
            String codigopedido = rs.getString("codigopedido");
            double total = rs.getDouble("total");
            Pedido pedido = new Pedido(id, usuario, fecha, estado, codigopedido, total);
            lista.add(pedido);
        }
        for (Pedido lista1 : lista) {
            sql = "SELECT * FROM linea_pedidos WHERE codigopedido='" + lista1.getCodigopedido() + "'";
            System.out.println(sql);
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            List<Linea_pedido> listalp = new ArrayList<Linea_pedido>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String codigopedido = rs.getString("codigopedido");
                int talla = rs.getInt("producto");
                int cantidad = rs.getInt("cantidad");
                Linea_pedido lp = new Linea_pedido(id, codigopedido, talla, cantidad);
                listalp.add(lp);
            }
            lista1.setLinea_pedidos(listalp);
        }
        for (Pedido lista1 : lista) {
            for (Linea_pedido listalp1 : lista1.getLinea_pedidos()) {
                sql = "SELECT * FROM tallas WHERE id=" + listalp1.getTalla();
                System.out.println(sql);
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                rs.next();
                listalp1.setproducto(rs.getInt("producto"));
            }
        }

        cerrarconeccion();
        return lista;
    }

    public List<Usuario> gettodosusurarios(HttpServletRequest request) throws ClassNotFoundException, SQLException {
        abriscon();
        String filtro = "";
        if (request.getParameter("buqueda") != null) {

            if (request.getParameter("usuario") != null && !request.getParameter("usuario").equals("")) {
                filtro = " WHERE nick LIKE '%" + request.getParameter("usuario") + "%'";
            }
        }
        List<Usuario> lista = new ArrayList<Usuario>();
        String sql = "SELECT * FROM usuarios" + filtro;
        System.out.println(sql);
        pst = cn.prepareStatement(sql);
        rs = pst.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String apellidos = rs.getString("apellidos");
            String nick = rs.getString("nick");
            String email = rs.getString("email");
            String password = rs.getString("password");
            int rol = rs.getInt("rol");
            String direccion = rs.getString("direccion");
            String geoloc = rs.getString("geoloc");
            int estado = rs.getInt("estado");
            String imagen = rs.getString("imagen");
            String token = rs.getString("token");
            Usuario usuario = new Usuario(id, nombre, apellidos, nick, email, password, rol, direccion, geoloc, estado, imagen, token);
            lista.add(usuario);
        }

        cerrarconeccion();
        return lista;
    }

    public boolean cambiarestado(String id, String st) throws ClassNotFoundException, SQLException {
        abriscon();
        try {
            String sql = "UPDATE usuarios SET estado=" + st + " WHERE id = " + id;
            pst = cn.prepareStatement(sql);
            pst.executeUpdate();

        } catch (MySQLIntegrityConstraintViolationException e) {
            cerrarconeccion();
            return false;
        }
        cerrarconeccion();
        return true;
    }

    public Usuario getallUser(String iduser) {
        Usuario usuario = null;
        try {
            abriscon();
            String sql = "SELECT * FROM usuarios WHERE id=" + iduser;
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String apellidos = rs.getString("apellidos");
            String nick = rs.getString("nick");
            String email = rs.getString("email");
            String password = rs.getString("password");
            int rol = rs.getInt("rol");
            String direccion = rs.getString("direccion");
            String geoloc = rs.getString("geoloc");
            int estado = rs.getInt("estado");
            String imagen = rs.getString("imagen");
            String token = rs.getString("token");
            usuario = new Usuario(id, nombre, apellidos, nick, email, password, rol, direccion, geoloc, id, imagen, token);

            cerrarconeccion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(coneccion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(coneccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

    public boolean añadirproducto(String categoria, String descripcion, String precio, String imagen) throws ClassNotFoundException, SQLException {
        abriscon();
        String sql = "INSERT INTO productos(categoria, descripcion, precio, imagen) VALUES (?,?,?,?)";
        pst = cn.prepareStatement(sql);
        pst.setInt(1, Integer.parseInt(categoria));
        pst.setString(2, descripcion);
        pst.setDouble(3, Double.parseDouble(precio));
        pst.setString(4, imagen);
        pst.executeUpdate();
        cerrarconeccion();
        return true;
    }

    public void adtallas(String t1, String t2, String t3, String t4, String t5, String t6, String t7, String name) throws SQLException, ClassNotFoundException {
        abriscon();
        String sql = "SELECT id FROM productos WHERE imagen= '" + name + "'";
        pst = cn.prepareStatement(sql);
        rs = pst.executeQuery();
        rs.next();
        int i = rs.getInt("id");
        sql = "INSERT INTO tallas (id, talla, cantidad, producto) VALUES (NULL, 'XXS', '" + t1 + "', '" + i + "'), (NULL, 'XS', '" + t2 + "', '" + i + "'), (NULL, 'S', '" + t3 + "', '" + i + "'), (NULL, 'M', '" + t4 + "', '" + i + "'), (NULL, 'L', '" + t5 + "', '" + i + "'), (NULL, 'XL', '" + t6 + "', '" + i + "'), (NULL, 'XXL', '" + t7 + "', '" + i + "')";
        System.out.println(sql);
        pst = cn.prepareStatement(sql);
         pst.executeUpdate();
        cerrarconeccion();
    }
    public List<Tallas> obtenertodatallas(String id) throws ClassNotFoundException, SQLException {
        abriscon();
        String sql = "SELECT * FROM tallas WHERE producto=" + id + " GROUP BY talla";
        pst = cn.prepareStatement(sql);
        rs = pst.executeQuery();
        List<Tallas> lista = new ArrayList<Tallas>();

        while (rs.next()) {

            Tallas tallas = new Tallas(rs.getInt("id"), rs.getString("talla"),rs.getInt("cantidad"));
            lista.add(tallas);
        }
        cerrarconeccion();
        
        return lista;
    }
}
