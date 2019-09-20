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
public class CargosDTO {
    
     private Integer idcargo;
    private String descripcion;
    
    public CargosDTO() {
    }

    public CargosDTO(Integer idcargo, String descripcion) {
        this.idcargo=idcargo;
        this.descripcion=descripcion;
         }

    public Integer getIdcargo() {
        return idcargo;
    }

    public void setIdcargo(Integer idcargo) {
        this.idcargo = idcargo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
