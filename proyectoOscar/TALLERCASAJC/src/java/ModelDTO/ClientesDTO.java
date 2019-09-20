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
public class ClientesDTO {

    private Integer id_cliente;
    private String ruc;
    private String razonsocial;
    private String telefono;
    private String direccion;
    private String web;
    private Integer id_barrio;
    private String barr_descripcion;
    private Integer id_ciudad;
    private String ciu_descripcion;

    public ClientesDTO(Integer id_cliente, String ruc, String razonsocial, String telefono, String direccion, String web, String barr_descripcion, String ciu_descripcion) {
        this.id_cliente = id_cliente;
        this.ruc = ruc;
        this.razonsocial = razonsocial;
        this.telefono = telefono;
        this.direccion = direccion;
        this.web = web;
        this.barr_descripcion = barr_descripcion;
        this.ciu_descripcion = ciu_descripcion;
    }
    
    public ClientesDTO(Integer id_cliente, String ruc, String razonsocial, String telefono, String direccion, String web, Integer id_barrio, Integer id_ciudad) {
        this.id_cliente = id_cliente;
        this.ruc = ruc;
        this.razonsocial = razonsocial;
        this.telefono = telefono;
        this.direccion = direccion;
        this.web = web;
        this.id_barrio = id_barrio;
        this.id_ciudad = id_ciudad;
    }

    public ClientesDTO() {
         }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
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
