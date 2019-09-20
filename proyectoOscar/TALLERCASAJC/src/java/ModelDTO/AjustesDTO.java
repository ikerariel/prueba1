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
public class AjustesDTO {
    Integer id_ajuste;
    String fecha_ajustes;
    String est_descripcion;
    Integer id_tipajuste;
    String descripcion;
    String art_descripcion;
    Integer id_sucursal;
    Integer id_deposito;
    Integer id_usuario;
    Integer id_estado;
    String observacion;
    Integer cantexistencia;
    
    Integer id_articulo;
    
    public AjustesDTO() {
    }

    public AjustesDTO(Integer id_ajuste, String fecha_ajustes, String est_descripcion) {
         this.id_ajuste=id_ajuste;
        this.fecha_ajustes=fecha_ajustes;
        this.est_descripcion=est_descripcion;
    }

    public AjustesDTO(Integer id_tipajuste, String descripcion) {
        this.id_tipajuste=id_tipajuste;
        this.descripcion=descripcion;
    }

    public AjustesDTO(String fecha_ajustes, Integer id_tipajuste, String descripcion, String observacion, Integer id_articulo, String art_descripcion, Integer cantexistencia) {
         this.fecha_ajustes=fecha_ajustes;
        this.id_tipajuste=id_tipajuste;
        this.descripcion=descripcion;
        this.observacion=observacion;
        this.id_articulo=id_articulo;
        this.art_descripcion=art_descripcion;
        this.cantexistencia=cantexistencia;
    }

    public Integer getId_ajuste() {
        return id_ajuste;
    }

    public void setId_ajuste(Integer id_ajuste) {
        this.id_ajuste = id_ajuste;
    }

    public String getFecha_ajustes() {
        return fecha_ajustes;
    }

    public void setFecha_ajustes(String fecha_ajustes) {
        this.fecha_ajustes = fecha_ajustes;
    }

    public String getEst_descripcion() {
        return est_descripcion;
    }

    public void setEst_descripcion(String est_descripcion) {
        this.est_descripcion = est_descripcion;
    }

    public Integer getId_tipajuste() {
        return id_tipajuste;
    }

    public void setId_tipajuste(Integer id_tipajuste) {
        this.id_tipajuste = id_tipajuste;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getArt_descripcion() {
        return art_descripcion;
    }

    public void setArt_descripcion(String art_descripcion) {
        this.art_descripcion = art_descripcion;
    }

    public Integer getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(Integer id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public Integer getId_deposito() {
        return id_deposito;
    }

    public void setId_deposito(Integer id_deposito) {
        this.id_deposito = id_deposito;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getCantexistencia() {
        return cantexistencia;
    }

    public void setCantexistencia(Integer cantexistencia) {
        this.cantexistencia = cantexistencia;
    }

    public Integer getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(Integer id_articulo) {
        this.id_articulo = id_articulo;
    }
    
}
