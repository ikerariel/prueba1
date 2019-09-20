/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDTO;

/**
 *
 * @author user
 */
public class OrdenComprasdto {

    private Integer id_presucompra;
    private Integer id_estado;
    private Integer id_proveedor;
    private String proveedor;
    private Integer id_articulo;
    private String articulo;
    private Integer preciounitario;
    private Integer cantidad;

    private Integer id_ordcompra;
    private String ordenc_fecha;
    private Integer id_sucursal;
    private String suc_descripcion;

    private String ras_social;
    private Integer id_pedidocompra;
    private String pcompra_fecha;
    private Integer id_usuario;
    private String usu_nombre;

    private String est_descripcion;

    private Integer id_ordcompraD;

    private Integer cantidad_detorden;
    private Integer precio_detorden;

    private Integer preccompras;
    private Integer precventas;
    private String art_descripcion;
    private Integer id_marca;
    private Integer id_impuesto;
    private String codigenerico;
    private String impuesto;


    public OrdenComprasdto() {
    }

    public OrdenComprasdto(Integer id_ordcompra, String ordenc_fecha, String suc_descripcion, String ras_social,
            Integer id_pedidocompra, String usu_nombre, String est_descripcion) {
        this.id_ordcompra = id_ordcompra;
        this.ordenc_fecha = ordenc_fecha;
        this.suc_descripcion = suc_descripcion;
        this.ras_social = ras_social;
        this.id_pedidocompra = id_pedidocompra;
        this.usu_nombre = usu_nombre;
        this.est_descripcion = est_descripcion;
    }

    public OrdenComprasdto(Integer id_ordcompraD, Integer id_articulo, Integer cantidad_detorden,
            Integer precio_detorden) {
        this.id_ordcompraD = id_ordcompraD;
        this.id_articulo = id_articulo;
        this.cantidad_detorden = cantidad_detorden;
        this.precio_detorden = precio_detorden;
    }

    public OrdenComprasdto(Integer id_articulo, String art_descripcion, Integer cantidad, Integer preccompras, Integer precventas,
            Integer id_impuesto, Integer id_marca, String impuesto) {
        this.id_articulo = id_articulo;
        this.art_descripcion = art_descripcion;
        this.cantidad = cantidad;
        this.preccompras = preccompras;
        this.precventas = precventas;
        this.id_impuesto = id_impuesto;
        this.id_marca = id_marca;
        this.impuesto = impuesto;

    }

    public OrdenComprasdto(String ordenc_fecha, String suc_descripcion, String ras_social,
            Integer id_pedidocompra, String usu_nombre, String est_descripcion, Integer id_articulo,
            Integer cantidad_detorden, Integer precio_detorden, String codigenerico, String art_descripcion) {
        this.ordenc_fecha = ordenc_fecha;
        this.suc_descripcion = suc_descripcion;
        this.ras_social = ras_social;
        this.id_pedidocompra = id_pedidocompra;
        this.usu_nombre = usu_nombre;
        this.est_descripcion = est_descripcion;
        this.id_articulo = id_articulo;
        this.cantidad_detorden = cantidad_detorden;
        this.precio_detorden = precio_detorden;
        this.codigenerico = codigenerico;
        this.art_descripcion = art_descripcion;
    }

    public OrdenComprasdto(Integer id_pedidocompra, String pcompra_fecha, String usu_nombre,
            String est_descripcion) {
        this.id_pedidocompra = id_pedidocompra;
        this.pcompra_fecha = pcompra_fecha;
        this.usu_nombre = usu_nombre;
        this.est_descripcion = est_descripcion;
    }

    public OrdenComprasdto(Integer id_presucompra, Integer id_estado, Integer id_proveedor, String proveedor, Integer id_articulo, String articulo, Integer preciounitario, Integer cantidad) {
          this.id_presucompra = id_presucompra;
        this.id_estado = id_estado;
        this.id_proveedor = id_proveedor;
        this.proveedor = proveedor;
        this.id_articulo = id_articulo;
        this.articulo = articulo;
        this.preciounitario = preciounitario;
        this.cantidad = cantidad;
    }

    public String getPcompra_fecha() {
        return pcompra_fecha;
    }

    public void setPcompra_fecha(String pcompra_fecha) {
        this.pcompra_fecha = pcompra_fecha;
    }

    public Integer getId_ordcompra() {
        return id_ordcompra;
    }

    public void setId_ordcompra(Integer id_ordcompra) {
        this.id_ordcompra = id_ordcompra;
    }

    public String getOrdenc_fecha() {
        return ordenc_fecha;
    }

    public void setOrdenc_fecha(String ordenc_fecha) {
        this.ordenc_fecha = ordenc_fecha;
    }

    public Integer getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(Integer id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public String getSuc_descripcion() {
        return suc_descripcion;
    }

    public void setSuc_descripcion(String suc_descripcion) {
        this.suc_descripcion = suc_descripcion;
    }

    public Integer getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(Integer id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getRas_social() {
        return ras_social;
    }

    public void setRas_social(String ras_social) {
        this.ras_social = ras_social;
    }

    public Integer getId_pedidocompra() {
        return id_pedidocompra;
    }

    public void setId_pedidocompra(Integer id_pedidocompra) {
        this.id_pedidocompra = id_pedidocompra;
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

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public String getEst_descripcion() {
        return est_descripcion;
    }

    public void setEst_descripcion(String est_descripcion) {
        this.est_descripcion = est_descripcion;
    }

    public Integer getId_ordcompraD() {
        return id_ordcompraD;
    }

    public void setId_ordcompraD(Integer id_ordcompraD) {
        this.id_ordcompraD = id_ordcompraD;
    }

    public Integer getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(Integer id_articulo) {
        this.id_articulo = id_articulo;
    }

    public Integer getCantidad_detorden() {
        return cantidad_detorden;
    }

    public void setCantidad_detorden(Integer cantidad_detorden) {
        this.cantidad_detorden = cantidad_detorden;
    }

    public Integer getPrecio_detorden() {
        return precio_detorden;
    }

    public void setPrecio_detorden(Integer precio_detorden) {
        this.precio_detorden = precio_detorden;
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

    public String getArt_descripcion() {
        return art_descripcion;
    }

    public void setArt_descripcion(String art_descripcion) {
        this.art_descripcion = art_descripcion;
    }

    public Integer getId_marca() {
        return id_marca;
    }

    public void setId_marca(Integer id_marca) {
        this.id_marca = id_marca;
    }

    public Integer getId_impuesto() {
        return id_impuesto;
    }

    public void setId_impuesto(Integer id_impuesto) {
        this.id_impuesto = id_impuesto;
    }

    public String getCodigenerico() {
        return codigenerico;
    }

    public void setCodigenerico(String codigenerico) {
        this.codigenerico = codigenerico;
    }
}
