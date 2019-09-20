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
public class pedidoVentaDTO {

    private Integer id_pedidoven;
    private String fechapedido;
    private String cliente;
    private String est_descripcion;
    private String vendedor;
    private Integer id_cliente;
    private String cedula;
    private Integer id_articulo;
    private Integer precio;
    private String articulo;
    private Integer id_estado;

    private String razonsocial;
    private Integer id_sucursal;
    private Integer idvendedor;
    private Integer cantidad;

    public pedidoVentaDTO(Integer id_pedidoven,String fechapedido,String observacion, Integer idvendedor, String cliente, String est_descripcion, String vendedor, Integer id_cliente, String cedula, Integer id_articulo, Integer precio,Integer cantidad, String articulo, Integer id_estado) {
        this.id_pedidoven = id_pedidoven;
        this.fechapedido = fechapedido;
        this.observacion = observacion;
        this.idvendedor = idvendedor;
        this.cliente = cliente;
        this.est_descripcion = est_descripcion;
        this.vendedor = vendedor;
        this.id_cliente = id_cliente;
        this.cedula = cedula;
        this.id_articulo = id_articulo;
        this.precio = precio;
        this.cantidad = cantidad;
        this.articulo = articulo;
        this.id_estado = id_estado;
    }

    public pedidoVentaDTO(Integer id_pedidoven, String fechapedido, String cliente, String est_descripcion, String vendedor, Integer id_estado) {
        this.id_pedidoven = id_pedidoven;
        this.fechapedido = fechapedido;
        this.cliente = cliente;
        this.est_descripcion = est_descripcion;
        this.vendedor = vendedor;
        this.id_estado = id_estado;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(Integer id_articulo) {
        this.id_articulo = id_articulo;
    }
    private String observacion;

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getId_pedidoven() {
        return id_pedidoven;
    }

    public void setId_pedidoven(Integer id_pedidoven) {
        this.id_pedidoven = id_pedidoven;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Integer getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(Integer id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public Integer getIdvendedor() {
        return idvendedor;
    }

    public void setIdvendedor(Integer idvendedor) {
        this.idvendedor = idvendedor;
    }

    public String getFechapedido() {
        return fechapedido;
    }

    public void setFechapedido(String fechapedido) {
        this.fechapedido = fechapedido;
    }

    public pedidoVentaDTO() {
    }

}
