/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controladores.utiles.Encriptar;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author juana
 */
public class Pedido {
    int id;
    int usuario;
    Date fecha;
    String estado;
    String codigopedido;
    List<Linea_pedido> linea_pedidos;

    public Pedido(int usuario, List<Linea_pedido> lista) {
        this.usuario = usuario;
        this.fecha= new Date(Calendar.getInstance().getTime().getTime());
        this.estado="activo";
        this.linea_pedidos=lista;
    }

    public Pedido(int id, int usuario, Date fecha, String estado, String codigopedido) {
        this.id = id;
        this.usuario = usuario;
        this.fecha = fecha;
        this.estado = estado;
        this.codigopedido = codigopedido;
    }

    public Pedido(int usuario, Date fecha, String estado) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigopedido() {
        return codigopedido;
    }

    public void setCodigopedido(String codigopedido) {
        this.codigopedido = codigopedido;
    }

    public List<Linea_pedido> getLinea_pedidos() {
        return linea_pedidos;
    }

    public void setLinea_pedidos(List<Linea_pedido> linea_pedidos) {
        this.linea_pedidos = linea_pedidos;
    }
    public void generarcodigopedido(){
        Encriptar en=new Encriptar();
        this.setCodigopedido(en.encriptacion(this.getFecha().toString()+this.usuario)+new Timestamp(System.currentTimeMillis()));
        for (Linea_pedido linea_pedido : linea_pedidos) {
            linea_pedido.setCodigopedido(codigopedido);
        }
        
    }
}
