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
public class EntidadEmisorasdto {

    private Integer id_entiemi;
    private String entiemi_descripcion;

    public EntidadEmisorasdto(Integer id_entiemi, String entiemi_descripcion) {
        this.id_entiemi = id_entiemi;
        this.entiemi_descripcion = entiemi_descripcion;
    }
    
   public EntidadEmisorasdto(){
       
   }

    public Integer getId_entiemi() {
        return id_entiemi;
    }

    public void setId_entiemi(Integer id_entiemi) {
        this.id_entiemi = id_entiemi;
    }

    public String getEntiemi_descripcion() {
        return entiemi_descripcion;
    }

    public void setEntiemi_descripcion(String entiemi_descripcion) {
        this.entiemi_descripcion = entiemi_descripcion;
    }

}
