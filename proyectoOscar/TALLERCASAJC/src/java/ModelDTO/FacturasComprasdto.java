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
public class FacturasComprasdto {

    private Integer id_compra;
    private Integer co_cantcuota;
    private Integer co_monto;
    private String co_nrofact;
    private String co_intervalo;
    private String co_fecha;
    private String co_tipo;

    private Integer id_proveedor;
    private String ras_social;
    private Integer id_sucursal;
    private String suc_descripcion;
    private Integer id_usuario;
    private String usu_nombre;
    private Integer id_estado;
    private String est_descripcion;
    private Integer id_ordencompra;
    private String ordenc_fecha;

    private Integer id__compraD;
    private Integer id_articulo;
    private Integer cantidad_detcomp;
    private Integer precio_detcomp;

    private Integer preccompras;
    private Integer precventas;
    private String art_descripcion;
    private Integer id_marca;
    private Integer id_impuesto;
    private String codigenerico;

    private Integer idcompra;
    private String nrofactura;
    private String fecha;

    public FacturasComprasdto() {

    }

    public FacturasComprasdto(Integer id_ordencompra, String ordenc_fecha, String usu_nombre, String est_descripcion) {
        this.id_ordencompra = id_ordencompra;
        this.ordenc_fecha = ordenc_fecha;
        this.usu_nombre = usu_nombre;
        this.est_descripcion = est_descripcion;
    }

    public FacturasComprasdto(Integer id_sucursal, String suc_descripcion) {
        this.id_sucursal = id_sucursal;
        this.suc_descripcion = suc_descripcion;
    }

    public FacturasComprasdto(Integer id_articulo, Integer preccompras, Integer precventas, String art_descripcion,
            Integer id_marca, Integer id_impuesto, String codigenerico) {
        this.id_articulo = id_articulo;
        this.preccompras = preccompras;
        this.precventas = precventas;
        this.art_descripcion = art_descripcion;
        this.id_marca = id_marca;
        this.id_impuesto = id_impuesto;
        this.codigenerico = codigenerico;
    }

    public FacturasComprasdto(Integer id_compra, String co_nrofact, String co_fecha, String co_tipo,
            String ras_social, String suc_descripcion, String usu_nombre, String est_descripcion) {
        this.id_compra = id_compra;
        this.co_nrofact = co_nrofact;
        this.co_fecha = co_fecha;
        this.co_tipo = co_tipo;
        this.ras_social = ras_social;
        this.suc_descripcion = suc_descripcion;
        this.usu_nombre = usu_nombre;
        this.est_descripcion = est_descripcion;

    }

    public FacturasComprasdto(Integer id_compra, Integer co_cantcuota, Integer co_monto, String co_nrofact,
            String co_intervalo, String co_fecha, String co_tipo, Integer id_proveedor, String ras_social, Integer id_sucursal,String suc_descripcion, Integer id_usuario,
            String usu_nombre, String est_descripcion, Integer id_ordencompra, Integer id_articulo, Integer cantidad_detcomp,
            Integer precio_detcomp, String codigenerico, String art_descripcion) {
        this.id_compra = id_compra;
        this.co_cantcuota = co_cantcuota;
        this.co_monto = co_monto;
        this.co_nrofact = co_nrofact;
        this.co_intervalo = co_intervalo;
        this.co_fecha = co_fecha;
        this.co_tipo = co_tipo;
        this.id_proveedor = id_proveedor;
        this.ras_social = ras_social;
        this.id_sucursal = id_sucursal;
        this.suc_descripcion = suc_descripcion;
        this.id_usuario = id_usuario;
        this.usu_nombre = usu_nombre;
        this.est_descripcion = est_descripcion;
        this.id_ordencompra = id_ordencompra;
        this.id_articulo = id_articulo;
        this.cantidad_detcomp = cantidad_detcomp;
        this.precio_detcomp = precio_detcomp;
        this.codigenerico = codigenerico;
        this.art_descripcion = art_descripcion;
    }



    public Integer getId_compra() {
        return id_compra;
    }

    public void setId_compra(Integer id_compra) {
        this.id_compra = id_compra;
    }

    public Integer getCo_cantcuota() {
        return co_cantcuota;
    }

    public void setCo_cantcuota(Integer co_cantcuota) {
        this.co_cantcuota = co_cantcuota;
    }

    public Integer getCo_monto() {
        return co_monto;
    }

    public void setCo_monto(Integer co_monto) {
        this.co_monto = co_monto;
    }

    public String getCo_nrofact() {
        return co_nrofact;
    }

    public void setCo_nrofact(String co_nrofact) {
        this.co_nrofact = co_nrofact;
    }

    public String getCo_intervalo() {
        return co_intervalo;
    }

    public void setCo_intervalo(String co_intervalo) {
        this.co_intervalo = co_intervalo;
    }

    public String getCo_fecha() {
        return co_fecha;
    }

    public void setCo_fecha(String co_fecha) {
        this.co_fecha = co_fecha;
    }

    public String getCo_tipo() {
        return co_tipo;
    }

    public void setCo_tipo(String co_tipo) {
        this.co_tipo = co_tipo;
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

    public Integer getId_ordencompra() {
        return id_ordencompra;
    }

    public void setId_ordencompra(Integer id_ordencompra) {
        this.id_ordencompra = id_ordencompra;
    }

    public String getOrdenc_fecha() {
        return ordenc_fecha;
    }

    public void setOrdenc_fecha(String ordenc_fecha) {
        this.ordenc_fecha = ordenc_fecha;
    }

    public Integer getId__compraD() {
        return id__compraD;
    }

    public void setId__compraD(Integer id__compraD) {
        this.id__compraD = id__compraD;
    }

    public Integer getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(Integer id_articulo) {
        this.id_articulo = id_articulo;
    }

    public Integer getCantidad_detcomp() {
        return cantidad_detcomp;
    }

    public void setCantidad_detcomp(Integer cantidad_detcomp) {
        this.cantidad_detcomp = cantidad_detcomp;
    }

    public Integer getPrecio_detcomp() {
        return precio_detcomp;
    }

    public void setPrecio_detcomp(Integer precio_detcomp) {
        this.precio_detcomp = precio_detcomp;
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
