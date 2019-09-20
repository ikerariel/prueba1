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
public class Depositosdto {

    private Integer id_deposito;
    private String dep_descripcion;
    private Integer id_sucursal;
     private String suc_descripcion;
     
    public Depositosdto(Integer id_deposito, String dep_descripcion, Integer id_sucursal) {
        this.id_deposito = id_deposito;
        this.dep_descripcion = dep_descripcion;
        this.id_sucursal = id_sucursal;
    }
    public Depositosdto(Integer id_deposito, String dep_descripcion, String suc_descripcion) {
        this.id_deposito = id_deposito;
        this.dep_descripcion = dep_descripcion;
        this.suc_descripcion = suc_descripcion;
    }
public Depositosdto(){}

    public Integer getId_deposito() {
        return id_deposito;
    }

    public void setId_deposito(Integer id_deposito) {
        this.id_deposito = id_deposito;
    }

    public String getDep_descripcion() {
        return dep_descripcion;
    }

    public void setDep_descripcion(String dep_descripcion) {
        this.dep_descripcion = dep_descripcion;
    }

    public Integer getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(Integer id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public String getSuc_descripcion() {
        return suc_descripcion;
    }

    public void setSuc_descripcion(String suc_descripcion) {
        this.suc_descripcion = suc_descripcion;
    }
}
