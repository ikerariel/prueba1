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
public class Empleadosdto {

    private Integer id_empleado;
    private String nombre;
    private String apellido;
    private Integer ci;
    private Integer tel;
    private String direccion;
    private Integer id_barrio;
    private String barr_descripcion;
    private Integer id_ciudad;
    private String ciu_descripcion;
    
        
     public Empleadosdto(Integer id_empleado, String nombre, String apellido, Integer ci, Integer tel,
             String direccion, Integer id_barrio, Integer id_ciudad) {
        this.id_empleado = id_empleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.ci = ci;
        this.tel = tel;
        this.direccion = direccion;
        this.id_barrio = id_barrio;
        this.id_ciudad = id_ciudad;
    }

    public Empleadosdto(Integer id_empleado, String nombre, String apellido, Integer ci, Integer tel,
            String direccion, String barr_descripcion, String ciu_descripcion) {
        this.id_empleado = id_empleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.ci = ci;
        this.tel = tel;
        this.direccion = direccion;
        this.barr_descripcion = barr_descripcion;
        this.ciu_descripcion = ciu_descripcion;
    }
    public Empleadosdto() {}

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

    public Integer getCi() {
        return ci;
    }

    public void setCi(Integer ci) {
        this.ci = ci;
    }

    public Integer getTel() {
        return tel;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getId_barrio() {
        return id_barrio;
    }

    public void setId_barrio(Integer id_barrio) {
        this.id_barrio = id_barrio;
    }

    public String getBarr_descripcion() {
        return barr_descripcion;
    }

    public void setBarr_descripcion(String barr_descripcion) {
        this.barr_descripcion = barr_descripcion;
    }

    public Integer getId_ciudad() {
        return id_ciudad;
    }

    public void setId_ciudad(Integer id_ciudad) {
        this.id_ciudad = id_ciudad;
    }

    public String getCiu_descripcion() {
        return ciu_descripcion;
    }

    public void setCiu_descripcion(String ciu_descripcion) {
        this.ciu_descripcion = ciu_descripcion;
    }
}

  
    