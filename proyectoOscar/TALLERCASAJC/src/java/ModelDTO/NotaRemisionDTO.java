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
public class NotaRemisionDTO {
    
    Integer id_notaremi;
    String fecha_notaremi;
    Integer nro_notaremi;
    String obser_notaremi;
    String estado;
    String usuario;
    Integer factura;

    Integer id_estado;
    String est_descripcion;
    Integer id_usuario;
    String usu_nombre;
    Integer id_compra;
    Integer co_nrofact;

    Integer id_notaremidet;
    Integer id_articulo;
    Integer cantinotaremi;
    Integer precionotaremi;

    public Integer getCantinotaremi() {
        return cantinotaremi;
    }

    public void setCantinotaremi(Integer cantinotaremi) {
        this.cantinotaremi = cantinotaremi;
    }

    public Integer getPrecionotaremi() {
        return precionotaremi;
    }

    public void setPrecionotaremi(Integer precionotaremi) {
        this.precionotaremi = precionotaremi;
    }
    
    public NotaRemisionDTO() {
    }

    public NotaRemisionDTO(Integer id_notaremi, String fecha_notaremi, Integer id_estado, String est_descripcion) {
         this.id_notaremi=id_notaremi;
        this.fecha_notaremi=fecha_notaremi;
        this.id_estado=id_estado;
        this.est_descripcion=est_descripcion;
    }

    public NotaRemisionDTO(String fecha_notaremi, Integer nro_notaremi, String obser_notaremi, Integer id_estado, String estado, Integer id_usuario, String usuario, Integer id_compra, Integer factura, Integer id_articulo, Integer cantinotaremi, Integer precionotaremi) {
       this.fecha_notaremi=fecha_notaremi;
        this.nro_notaremi=nro_notaremi;
        this.obser_notaremi=obser_notaremi;
        this.id_estado=id_estado;
        this.estado=estado;
        this.id_usuario=id_usuario;
        this.usuario=usuario;
        this.id_compra=id_compra;
        this.factura=factura;
        this.id_articulo=id_articulo;
        this.cantinotaremi=cantinotaremi;
        this.precionotaremi=precionotaremi;
    }

    public NotaRemisionDTO(Integer id_compra, Integer co_nrofact) {
        this.id_compra=id_compra;
        this.co_nrofact=co_nrofact; 
    }

    public Integer getId_notaremi() {
        return id_notaremi;
    }

    public void setId_notaremi(Integer id_notaremi) {
        this.id_notaremi = id_notaremi;
    }

    public String getFecha_notaremi() {
        return fecha_notaremi;
    }

    public void setFecha_notaremi(String fecha_notaremi) {
        this.fecha_notaremi = fecha_notaremi;
    }

    public Integer getNro_notaremi() {
        return nro_notaremi;
    }

    public void setNro_notaremi(Integer nro_notaremi) {
        this.nro_notaremi = nro_notaremi;
    }

    public String getObser_notaremi() {
        return obser_notaremi;
    }

    public void setObser_notaremi(String obser_notaremi) {
        this.obser_notaremi = obser_notaremi;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getFactura() {
        return factura;
    }

    public void setFactura(Integer factura) {
        this.factura = factura;
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

    public Integer getId_compra() {
        return id_compra;
    }

    public void setId_compra(Integer id_compra) {
        this.id_compra = id_compra;
    }

    public Integer getCo_nrofact() {
        return co_nrofact;
    }

    public void setCo_nrofact(Integer co_nrofact) {
        this.co_nrofact = co_nrofact;
    }

    public Integer getId_notaremidet() {
        return id_notaremidet;
    }

    public void setId_notaremidet(Integer id_notaremidet) {
        this.id_notaremidet = id_notaremidet;
    }

    public Integer getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(Integer id_articulo) {
        this.id_articulo = id_articulo;
    }
    
}
