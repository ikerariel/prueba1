/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDTO;

/**
 *
 * @author Usuario
 */
public class sRecepcionDTO {

    Integer id_recepcion;
    String fecha;
    String ruc;
    String cliente;
    String observacion;
    Integer id_articulo;
    String articulo;
    Integer cantidad;

    String estado;
    String usuario;

    Integer id_cliente;
    Integer id_estado;
    Integer id_usuario;

    public sRecepcionDTO(Integer id_recepcion, String fecha, String cliente, String estado, String usuario, Integer id_estado) {
        this.id_recepcion = id_recepcion;
        this.fecha = fecha;
        this.cliente = cliente;
        this.estado = estado;
        this.usuario = usuario;
        this.id_estado = id_estado;

    }

    public sRecepcionDTO(Integer id_recepcion, String fecha, String ruc, String cliente, String observacion, Integer id_articulo, String articulo, Integer id_cliente, Integer cantidad) {
        this.id_recepcion = id_recepcion;
        this.fecha = fecha;
        this.ruc = ruc;
        this.cliente = cliente;
        this.observacion = observacion;
        this.id_articulo = id_articulo;
        this.articulo = articulo;
        this.id_cliente = id_cliente;
        this.cantidad = cantidad;
    }

    public sRecepcionDTO() {
    }

    public Integer getId_recepcion() {
        return id_recepcion;
    }

    public void setId_recepcion(Integer id_recepcion) {
        this.id_recepcion = id_recepcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(Integer id_articulo) {
        this.id_articulo = id_articulo;
    }

}
