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
public class BancoChequesDTO {
    
     private Integer id_bancocheque;
    private String descripcion;

    public BancoChequesDTO(Integer id_bancocheque, String descripcion) {
       this.id_bancocheque=id_bancocheque;
       this.descripcion=descripcion;
    }

    public BancoChequesDTO() {
         }

    public Integer getId_bancocheque() {
        return id_bancocheque;
    }

    public void setId_bancocheque(Integer id_bancocheque) {
        this.id_bancocheque = id_bancocheque;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
