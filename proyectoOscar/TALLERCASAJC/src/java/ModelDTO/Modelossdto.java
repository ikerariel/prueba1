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
public class Modelossdto {

    private Integer id_modelo;
    private String mod_descripcion;
    private Integer id_marca;
    private String mar_descripcion;

    private Integer id_color;
    private String colo_descripcion;

    public Modelossdto(Integer id_modelo, String mod_descripcion, Integer id_marca, Integer id_color) {

        this.id_modelo = id_modelo;
        this.mod_descripcion = mod_descripcion;
        this.id_marca = id_marca;
        this.id_color = id_color;
    }

    public Modelossdto(Integer id_modelo, String mod_descripcion, String mar_descripcion, String colo_descripcion) {

        this.id_modelo = id_modelo;
        this.mod_descripcion = mod_descripcion;
        this.mar_descripcion = mar_descripcion;
        this.colo_descripcion = colo_descripcion;
    }

    public Modelossdto() {
    }

    public Integer getId_modelo() {
        return id_modelo;
    }

    public void setId_modelo(Integer id_modelo) {
        this.id_modelo = id_modelo;
    }

    public String getMod_descripcion() {
        return mod_descripcion;
    }

    public void setMod_descripcion(String mod_descripcion) {
        this.mod_descripcion = mod_descripcion;
    }

    public Integer getId_marca() {
        return id_marca;
    }

    public void setId_marca(Integer id_marca) {
        this.id_marca = id_marca;
    }

    public String getMar_descripcion() {
        return mar_descripcion;
    }

    public void setMar_descripcion(String mar_descripcion) {
        this.mar_descripcion = mar_descripcion;
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
