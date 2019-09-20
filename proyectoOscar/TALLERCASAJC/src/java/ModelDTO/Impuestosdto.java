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
public class Impuestosdto {

    private Integer id_impuesto;
    private String imp_descripcion;

    public Impuestosdto(Integer id_impuesto, String imp_descripcion) {
        this.id_impuesto = id_impuesto;
        this.imp_descripcion = imp_descripcion;

    }

    public Impuestosdto() {

    }

    public Integer getId_impuesto() {
        return id_impuesto;
    }

    public void setId_impuesto(Integer id_impuesto) {
        this.id_impuesto = id_impuesto;
    }

    public String getImp_descripcion() {
        return imp_descripcion;
    }

    public void setImp_descripcion(String imp_descripcion) {
        this.imp_descripcion = imp_descripcion;
    }

}
