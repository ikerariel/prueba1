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
public class Estadosdto {

    private Integer id_estado;
    private String est_descripcion;

    public Estadosdto(Integer id_estado, String est_descripcion) {
        this.id_estado = id_estado;
        this.est_descripcion = est_descripcion;
    }

    public Estadosdto() {

    }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public String getEst_descripcion() {
        return est_descripcion;
    }

    public void setEst_descripcion(String est_descripcion) {
        this.est_descripcion = est_descripcion;
    }

}
