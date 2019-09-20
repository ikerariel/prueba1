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
public class Marcasdto {

    private Integer id_marca;
    private String mar_descripcion;

    public Marcasdto(Integer id_marca, String mar_descripcion) {
        this.id_marca = id_marca;
        this.mar_descripcion = mar_descripcion;
    }

    public Marcasdto() {
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

}
