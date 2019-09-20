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
public class Proveedoresdto {

    private Integer id_proveedor;
    private String ras_social;
    private String direccion;
    private String pag_web;
    private String telefono;
    private String ruc;
    private Integer id_ciudad;
    private String ciu_descripcion;

    public Proveedoresdto() {
    }

    public Proveedoresdto(Integer id_proveedor, String ras_social, String direccion, String pag_web,
            String telefono, String ruc, String ciu_descripcion) {
        this.id_proveedor = id_proveedor;
        this.ras_social = ras_social;
        this.direccion = direccion;
        this.pag_web = pag_web;
        this.telefono = telefono;
        this.ruc = ruc;
        this.ciu_descripcion = ciu_descripcion;
    }

    public Proveedoresdto(Integer id_proveedor, String ras_social, String direccion, String pag_web,
            String telefono, String ruc, Integer id_ciudad) {
        this.id_proveedor = id_proveedor;
        this.ras_social = ras_social;
        this.direccion = direccion;
        this.pag_web = pag_web;
        this.telefono = telefono;
        this.ruc = ruc;
        this.id_ciudad = id_ciudad;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPag_web() {
        return pag_web;
    }

    public void setPag_web(String pag_web) {
        this.pag_web = pag_web;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
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
