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
public class Usuariosdto {
    
    private Integer id_usuario;
    private String usu_nombre;
    private String usu_clave;
    private Integer id_empleado;
    private String nombre;
    private String apellido;
    private Integer id_sucursal;
    private String suc_descripcion;
    private Integer id_perfil;
    private String perf_descripcion;
    
    public Usuariosdto() {} 
    
    public Usuariosdto(Integer id_usuario, String usu_nombre, String usu_clave, String nombre,
            String apellido, String suc_descripcion, String perf_descripcion) {
        this.id_usuario = id_usuario;
        this.usu_nombre = usu_nombre;
        this.usu_clave = usu_clave;
        this.nombre = nombre;
        this.apellido = apellido;
        this.suc_descripcion = suc_descripcion;
        this.perf_descripcion = perf_descripcion;
    }
    public Usuariosdto(Integer id_usuario, String usu_nombre, String usu_clave, Integer id_empleado,
            Integer id_sucursal, Integer id_perfil) {
        this.id_usuario = id_usuario;
        this.usu_nombre = usu_nombre;
        this.usu_clave = usu_clave;
        this.id_empleado = id_empleado;
        this.id_sucursal = id_sucursal;
        this.id_perfil = id_perfil;
    }
    
    public Usuariosdto(Integer id_empleado, String nombre, String apellido){
        this.id_empleado=id_empleado;
        this.nombre=nombre;
        this.apellido=apellido;
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

    public Integer getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(Integer id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public Integer getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(Integer id_perfil) {
        this.id_perfil = id_perfil;
    }

    public String getPerf_descripcion() {
        return perf_descripcion;
    }

    public void setPerf_descripcion(String perf_descripcion) {
        this.perf_descripcion = perf_descripcion;
    }
    
}
