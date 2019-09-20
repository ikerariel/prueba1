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
public class Sucursalesdto {
    
    private Integer id_sucursal;
    private String suc_descripcion;
    private Integer id_ciudad;
    private String ciu_descripcion;
    private Integer id_barrio;
    private String barr_descripcion;
    
    public Sucursalesdto(Integer id_sucursal, String suc_descripcion, Integer id_ciudad, Integer id_barrio) {
        this.id_sucursal = id_sucursal;
        this.suc_descripcion = suc_descripcion;
        this.id_ciudad = id_ciudad;
        this.id_barrio = id_barrio;
    }

    public Sucursalesdto(Integer id_sucursal, String suc_descripcion, String ciu_descripcion, String barr_descripcion) {
        this.id_sucursal = id_sucursal;
        this.suc_descripcion = suc_descripcion;
        this.ciu_descripcion = ciu_descripcion;
        this.barr_descripcion = barr_descripcion;
    }
    
    public Sucursalesdto() {} 

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
    
}
