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
public class Cajasdto {
    private Integer id_caja;
    private String descripcion;
    
    public Cajasdto (){}
    
    public Cajasdto(Integer id_caja, String descripcion) {
        this.id_caja = id_caja;
        this.descripcion = descripcion;
    }

    public Integer getId_caja() {
        return id_caja;
    }

    public void setId_caja(Integer id_caja) {
        this.id_caja = id_caja;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
