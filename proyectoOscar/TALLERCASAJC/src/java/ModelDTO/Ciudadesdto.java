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
public class Ciudadesdto {

    private Integer id_ciudad;
    private String ciu_descripcion;

    public Ciudadesdto(Integer id_ciudad, String ciu_descripcion) {
        this.id_ciudad = id_ciudad;
        this.ciu_descripcion = ciu_descripcion;
    }

    public Ciudadesdto() {
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

