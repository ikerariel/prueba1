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
public class notaDebitoDTO {

    Integer id_notadecompra;
    String fecha;
    Integer id_estado;
    String est_descripcion;

    Integer numerofac;
    Integer importe;
    Integer factura;
    Integer id_usuario;
    String usu_nombre;
    String estado;
    Integer idusuario;
    String comentario;
    Integer nro_notadebito;
    Integer nro_timbradonotadebito;
    String conceptos;
    Integer id_compra;
    Integer co_nrofact;

    public notaDebitoDTO(Integer id_notadecompra, String fecha, Integer id_estado, String est_descripcion) {
        this.id_notadecompra = id_notadecompra;
        this.fecha = fecha;
        this.id_estado = id_estado;
        this.est_descripcion = est_descripcion;

    }

    public notaDebitoDTO(String fecha, Integer nro_notadebito, Integer nro_timbradonotadebito,
            Integer id_compra, Integer factura, Integer id_usuario, String usu_nombre,
            Integer id_estado, String estado, String conceptos, Integer importe) {
        this.fecha = fecha;
        this.nro_notadebito = nro_notadebito;
        this.nro_timbradonotadebito = nro_timbradonotadebito;
        this.id_compra = id_compra;
        this.factura = factura;
        this.id_usuario = id_usuario;
        this.usu_nombre = usu_nombre;
        this.id_estado = id_estado;
        this.estado = estado;
        this.conceptos = conceptos;
        this.importe = importe;

    }

    public notaDebitoDTO(Integer id_compra, Integer co_nrofact) {
        this.id_compra = id_compra;
        this.co_nrofact=co_nrofact;
    }

    public Integer getNro_notadebito() {
        return nro_notadebito;
    }

    public void setNro_notadebito(Integer nro_notadebito) {
        this.nro_notadebito = nro_notadebito;
    }

    public Integer getNro_timbradonotadebito() {
        return nro_timbradonotadebito;
    }

    public void setNro_timbradonotadebito(Integer nro_timbradonotadebito) {
        this.nro_timbradonotadebito = nro_timbradonotadebito;
    }

    public String getConceptos() {
        return conceptos;
    }

    public void setConceptos(String conceptos) {
        this.conceptos = conceptos;
    }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public Integer getId_compra() {
        return id_compra;
    }

    public void setId_compra(Integer id_compra) {
        this.id_compra = id_compra;
    }

    public notaDebitoDTO() {
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getId_notadecompra() {
        return id_notadecompra;
    }

    public void setId_notadecompra(Integer id_notadecompra) {
        this.id_notadecompra = id_notadecompra;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Integer getNumerofac() {
        return numerofac;
    }

    public void setNumerofac(Integer numerofac) {
        this.numerofac = numerofac;
    }

    public Integer getImporte() {
        return importe;
    }

    public void setImporte(Integer importe) {
        this.importe = importe;
    }

}
