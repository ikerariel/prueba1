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
public class TiposPagosDTO {
    
     private Integer idtipopag;
    private String descripcion;

    public TiposPagosDTO(Integer idtipopag, String descripcion) {
        this.idtipopag=idtipopag;
        this.descripcion=descripcion;
    }

    public TiposPagosDTO() {
         }

    public Integer getIdtipopag() {
        return idtipopag;
    }

    public void setIdtipopag(Integer idtipopag) {
        this.idtipopag = idtipopag;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
