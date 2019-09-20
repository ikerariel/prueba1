/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDTO;

public class accesoDTO {

    private Integer id_usuario;
    private String usu_nombre;
    private String usu_clave;
    private Integer idestado;
    private Integer idempleado;
    private Integer idrolesusuario;
    private Integer id_sucursal;
    private String suc_descripcion;
    private String dep_descripcion;
    private Integer id_deposito;
    private Integer idsupervisor;
    private Integer id_perfil;
    private Integer idvendedor;

    public Integer getIdvendedor() {
        return idvendedor;
    }

    public void setIdvendedor(Integer idvendedor) {
        this.idvendedor = idvendedor;
    }

    public accesoDTO() {
    }

    public Integer getIdsupervisor() {
        return idsupervisor;
    }

    public void setIdsupervisor(Integer idsupervisor) {
        this.idsupervisor = idsupervisor;
    }

    public accesoDTO(Integer id_usuario, String usu_nombre, Integer id_sucursal,
            Integer id_deposito, String suc_descripcion, String dep_descripcion,Integer id_perfil,Integer idvendedor, Integer idsupervisor) {
        this.id_usuario = id_usuario;
        this.usu_nombre = usu_nombre;
        this.id_sucursal = id_sucursal;
        this.id_deposito = id_deposito;
        this.suc_descripcion = suc_descripcion;
        this.dep_descripcion = dep_descripcion;
        this.id_perfil = id_perfil;
        this.idvendedor = idvendedor;
        this.idsupervisor = idsupervisor;

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

    public String getUsu_clave() {
        return usu_clave;
    }

    public void setUsu_clave(String usu_clave) {
        this.usu_clave = usu_clave;
    }

    public Integer getIdestado() {
        return idestado;
    }

    public void setIdestado(Integer idestado) {
        this.idestado = idestado;
    }

    public Integer getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Integer idempleado) {
        this.idempleado = idempleado;
    }

    public Integer getIdrolesusuario() {
        return idrolesusuario;
    }

    public void setIdrolesusuario(Integer idrolesusuario) {
        this.idrolesusuario = idrolesusuario;
    }

    public Integer getIdsucursal() {
        return id_sucursal;
    }

    public void setIdsucursal(Integer id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public String getSucursal() {
        return suc_descripcion;
    }

    public void setSucursal(String suc_descripcion) {
        this.suc_descripcion = suc_descripcion;
    }

    public String getDeposito() {
        return dep_descripcion;
    }

    public void setDeposito(String dep_descripcion) {
        this.dep_descripcion = dep_descripcion;
    }

    public Integer getIddeposito() {
        return id_deposito;
    }

    public void setIddeposito(Integer id_deposito) {
        this.id_deposito = id_deposito;
    }

}
