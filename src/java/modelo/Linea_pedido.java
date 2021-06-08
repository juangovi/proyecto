/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import coneccion.coneccion;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juana
 */
public class Linea_pedido {
    int id;
    String codigopedido;
    int talla;
    int cantidad;
    productos producto;

    public Linea_pedido(int id, String codigopedido, int producto, int cantidad) {
        this.id = id;
        this.codigopedido = codigopedido;
        this.talla = producto;
        this.cantidad = cantidad;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Linea_pedido other = (Linea_pedido) obj;
        if (this.talla != other.talla) {
            return false;
        }
        return true;
    }

    public Linea_pedido(int producto,int productoid) {
        this.talla = producto;
        this.cantidad = 1;
        setproducto(productoid);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigopedido() {
        return codigopedido;
    }

    public void setCodigopedido(String codigopedido) {
        this.codigopedido = codigopedido;
    }

    public int getTalla() {
        return talla;
    }

    public void setTalla(int producto) {
        this.talla = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public void setproducto(int id){
        coneccion con=new coneccion();
        try {
            this.producto=con.obtenerproducto(""+id);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Linea_pedido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Linea_pedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public productos getProducto(){
        return this.producto;
    }
}
