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
public class NotaCreComprasdto {

    private Integer id_notacrecompra;
    private String fecha_nocred;
    private Integer nro_nocred;
    private Integer nro_timbrado;
    private String obs_nocred;
    private Integer id_compra;
    private Integer id_usuario;
    private String usu_nombre;
    private Integer id_estado;
    private String est_descripcion;

    private Integer id_notacrecompraD;
    private Integer id_articulo;
    private Integer cantidad_detnocre;
    private Integer montouni_detnocre;
    private String art_descripcion;
    private Integer co_nrofact;
    private Integer factura;
    private String usuarios;
    private String estados;

    public NotaCreComprasdto() {
    }

    public NotaCreComprasdto(Integer id_notacrecompra, String fecha_nocred, Integer nro_nocred, Integer nro_timbrado,
            String obs_nocred, Integer id_compra, Integer id_usuario,
            String usu_nombre, Integer id_estado, Integer est_descripcion) {
        this.id_notacrecompra = id_notacrecompra;
        this.fecha_nocred = fecha_nocred;
        this.nro_nocred = nro_nocred;
        this.nro_timbrado = nro_timbrado;
        this.obs_nocred = obs_nocred;
        this.id_compra = id_compra;
        this.id_usuario = id_usuario;
        this.usu_nombre = usu_nombre;
        this.id_usuario = id_estado;
        this.id_usuario = est_descripcion;
    }

    public NotaCreComprasdto(Integer id_notacrecompraD, Integer id_articulo, Integer cantidad_detnocre, String art_descripcion) {
        this.id_notacrecompraD = id_notacrecompraD;
        this.id_articulo = id_articulo;
        this.cantidad_detnocre = cantidad_detnocre;
        this.art_descripcion = art_descripcion;

    }

    public NotaCreComprasdto(Integer id_notacrecompra, String fecha_nocred, Integer id_estado, String est_descripcion) {
        this.id_notacrecompra = id_notacrecompra;
        this.fecha_nocred = fecha_nocred;
        this.id_estado = id_estado;
        this.est_descripcion = est_descripcion;

    }

    public NotaCreComprasdto(String fecha_nocred, Integer nro_nocred, Integer nro_timbrado,String obs_nocred,
            Integer id_compra, Integer factura, Integer id_usuario,String usuarios, Integer id_estado,
            String estados, Integer id_articulo, Integer cantidad_detnocre, Integer montouni_detnocre) {
        this.fecha_nocred = fecha_nocred;
        this.nro_nocred = nro_nocred;
        this.nro_timbrado = nro_timbrado;
        this.id_compra = id_compra;
        this.factura = factura;
        this.id_usuario = id_usuario;
        this.usuarios = usuarios;
        this.id_estado = id_estado;
        this.estados = estados;
        this.id_articulo = id_articulo;
        this.cantidad_detnocre = cantidad_detnocre;
        this.montouni_detnocre = montouni_detnocre;
    }

    public NotaCreComprasdto(Integer id_compra, Integer co_nrofact) {
        this.id_compra = id_compra;
        this.co_nrofact = co_nrofact;
    }

    public Integer getId_notacrecompra() {
        return id_notacrecompra;
    }

    public void setId_notacrecompra(Integer id_notacrecompra) {
        this.id_notacrecompra = id_notacrecompra;
    }

    public String getFecha_nocred() {
        return fecha_nocred;
    }

    public void setFecha_nocred(String fecha_nocred) {
        this.fecha_nocred = fecha_nocred;
    }

    public Integer getNro_nocred() {
        return nro_nocred;
    }

    public void setNro_nocred(Integer nro_nocred) {
        this.nro_nocred = nro_nocred;
    }

    public Integer getNro_timbrado() {
        return nro_timbrado;
    }

    public void setNro_timbrado(Integer nro_timbrado) {
        this.nro_timbrado = nro_timbrado;
    }

    public String getObs_nocred() {
        return obs_nocred;
    }

    public void setObs_nocred(String obs_nocred) {
        this.obs_nocred = obs_nocred;
    }

    public Integer getId_compra() {
        return id_compra;
    }

    public void setId_compra(Integer id_compra) {
        this.id_compra = id_compra;
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

    public Integer getId_notacrecompraD() {
        return id_notacrecompraD;
    }

    public void setId_notacrecompraD(Integer id_notacrecompraD) {
        this.id_notacrecompraD = id_notacrecompraD;
    }

    public Integer getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(Integer id_articulo) {
        this.id_articulo = id_articulo;
    }

    public Integer getCantidad_detnocre() {
        return cantidad_detnocre;
    }

    public void setCantidad_detnocre(Integer cantidad_detnocre) {
        this.cantidad_detnocre = cantidad_detnocre;
    }

    public Integer getMontouni_detnocre() {
        return montouni_detnocre;
    }

    public void setMontouni_detnocre(Integer montouni_detnocre) {
        this.montouni_detnocre = montouni_detnocre;
    }

    public String getArt_descripcion() {
        return art_descripcion;
    }

    public void setArt_descripcion(String art_descripcion) {
        this.art_descripcion = art_descripcion;
    }
}
