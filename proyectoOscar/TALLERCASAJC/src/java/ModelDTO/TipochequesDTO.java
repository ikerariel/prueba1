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
public class TipochequesDTO {
    
    private Integer id_tipocheque;
    private String descripcion;

    public TipochequesDTO(Integer id_tipocheque, String descripcion) {
        this.id_tipocheque=id_tipocheque;
        this.descripcion=descripcion;
    }

    public TipochequesDTO() {
         }

    public Integer getId_tipocheque() {
        return id_tipocheque;
    }

    public void setId_tipocheque(Integer id_tipocheque) {
        this.id_tipocheque = id_tipocheque;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
