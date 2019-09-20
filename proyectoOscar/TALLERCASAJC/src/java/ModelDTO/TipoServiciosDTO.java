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
public class TipoServiciosDTO {
    
    private Integer id_tiposerv;
    private String descripcion;

    public TipoServiciosDTO(Integer id_tiposerv, String descripcion) {
        this.id_tiposerv=id_tiposerv;
        this.descripcion=descripcion;
        }

    public TipoServiciosDTO() {
         }

    public Integer getId_tiposerv() {
        return id_tiposerv;
    }

    public void setId_tiposerv(Integer id_tiposerv) {
        this.id_tiposerv = id_tiposerv;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
