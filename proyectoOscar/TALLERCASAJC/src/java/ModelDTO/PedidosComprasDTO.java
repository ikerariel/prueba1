/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDTO;

/**
 *
 * @author Oscar
 */
public class PedidosComprasDTO {

    private Integer id_pedidocompra;
    private String pcompra_fecha;
    private String observacion;
    private Integer id_usuario;
    private String usu_nombre;
    private String est_descripcion;
    private Integer id_estado;

    private Integer id_pedidocompraD;
    private Integer id_articulo;
    private Integer cantidad;
    private Integer precio;

    private String art_descripcion;
    private Integer preccompras;
    private Integer precventas;
    private Integer id_impuesto;
    private Integer id_marca;
    private String codigenerico;

    public PedidosComprasDTO(Integer id_pedidocompraD, Integer id_articulo, Integer cantidad, Integer precio) {
        this.id_pedidocompraD = id_pedidocompraD;
        this.id_articulo = id_articulo;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public PedidosComprasDTO(Integer id_articulo, String art_descripcion, Integer preccompras, Integer precventas, Integer id_impuesto, Integer id_marca, String codigenerico) {
        this.id_articulo = id_articulo;
        this.art_descripcion = art_descripcion;
        this.preccompras = preccompras;
        this.precventas = precventas;
        this.id_impuesto = id_impuesto;
        this.id_marca = id_marca;
        this.codigenerico = codigenerico;
    }

    public PedidosComprasDTO(Integer id_pedidocompra, String pcompra_fecha, String usu_nombre, String est_descripcion, String observacion) {
        this.id_pedidocompra = id_pedidocompra;
        this.pcompra_fecha = pcompra_fecha;
        this.usu_nombre = usu_nombre;
        this.est_descripcion = est_descripcion;
        this.observacion = observacion;
    }

    public PedidosComprasDTO(String pcompra_fecha, String usu_nombre, String est_descripcion, String observacion, Integer id_articulo, Integer cantidad, Integer precio, String codigenerico, Integer id_estado,String art_descripcion) {
        this.pcompra_fecha = pcompra_fecha;
        this.usu_nombre = usu_nombre;
        this.est_descripcion = est_descripcion;
        this.observacion = observacion;
        this.id_articulo = id_articulo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.codigenerico = codigenerico;
        this.id_estado = id_estado;
        this.art_descripcion = art_descripcion;
    }

    public PedidosComprasDTO() {
        }

    public Integer getId_pedidocompra() {
        return id_pedidocompra;
    }

    public void setId_pedidocompra(Integer id_pedidocompra) {
        this.id_pedidocompra = id_pedidocompra;
    }

    public String getPcompra_fecha() {
        return pcompra_fecha;
    }

    public void setPcompra_fecha(String pcompra_fecha) {
        this.pcompra_fecha = pcompra_fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsu_nombre() {
        return usu_nombre;
    }

    public void setUsu_nombre(String usu_nombre) {
        this.usu_nombre = usu_nombre;
    }

    public String getEst_descripcion() {
        return est_descripcion;
    }

    public void setEst_descripcion(String est_descripcion) {
        this.est_descripcion = est_descripcion;
    }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public Integer getId_pedidocompraD() {
        return id_pedidocompraD;
    }

    public void setId_pedidocompraD(Integer id_pedidocompraD) {
        this.id_pedidocompraD = id_pedidocompraD;
    }

    public Integer getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(Integer id_articulo) {
        this.id_articulo = id_articulo;
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

    public String getArt_descripcion() {
        return art_descripcion;
    }

    public void setArt_descripcion(String art_descripcion) {
        this.art_descripcion = art_descripcion;
    }

    public Integer getPreccompras() {
        return preccompras;
    }

    public void setPreccompras(Integer preccompras) {
        this.preccompras = preccompras;
    }

    public Integer getPrecventas() {
        return precventas;
    }

    public void setPrecventas(Integer precventas) {
        this.precventas = precventas;
    }

    public Integer getId_impuesto() {
        return id_impuesto;
    }

    public void setId_impuesto(Integer id_impuesto) {
        this.id_impuesto = id_impuesto;
    }

    public Integer getId_marca() {
        return id_marca;
    }

    public void setId_marca(Integer id_marca) {
        this.id_marca = id_marca;
    }

    public String getCodigenerico() {
        return codigenerico;
    }

    public void setCodigenerico(String codigenerico) {
        this.codigenerico = codigenerico;
    }

}
