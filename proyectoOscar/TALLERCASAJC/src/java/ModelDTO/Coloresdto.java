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
public class Coloresdto {

    private Integer id_color;
    private String colo_descripcion;

    public Coloresdto(Integer id_color, String colo_descripcion) {
        this.id_color = id_color;
        this.colo_descripcion = colo_descripcion;
    }

    public Coloresdto() {
            }

    public Integer getId_color() {
        return id_color;
    }

    public void setId_color(Integer id_color) {
        this.id_color = id_color;
    }

    public String getColo_descripcion() {
        return colo_descripcion;
    }

    public void setColo_descripcion(String colo_descripcion) {
        this.colo_descripcion = colo_descripcion;
    }
}
