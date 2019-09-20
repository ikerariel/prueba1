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
public class Barriosdto {

    private Integer id_barrio;
    private String barr_descripcion;
    private Integer id_ciudad;
    private String ciu_descripcion;

    public Barriosdto(Integer id_barrio, String barr_descripcion, Integer id_ciudad) {

        this.id_barrio = id_barrio;
        this.barr_descripcion = barr_descripcion;
        this.id_ciudad = id_ciudad;
    }

    public Barriosdto(Integer id_barrio, String barr_descripcion, String ciu_descripcion) {

        this.id_barrio = id_barrio;
        this.barr_descripcion = barr_descripcion;
        this.ciu_descripcion = ciu_descripcion;
    }

    public Barriosdto() {}

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
