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
public class Timbradosdto {
    
    private Integer id_timbrado;
    private Integer numero;
    private String inicio_fecha;
    private String final_fecha; 
    private String vencimientos; 
    private Integer id_estado;
    private String est_descripcion;
    
        public Timbradosdto(Integer id_timbrado, Integer numero, String inicio_fecha, String final_fecha,
           String vencimientos, Integer id_estado) {

        this.id_timbrado = id_timbrado;
        this.numero = numero;
        this.inicio_fecha = inicio_fecha;
        this.final_fecha = final_fecha;
        this.vencimientos = vencimientos;
        this.id_estado = id_estado;
    }

    public Timbradosdto(Integer id_timbrado, Integer numero, String inicio_fecha, String final_fecha,
           String vencimientos, String est_descripcion) {

        this.id_timbrado = id_timbrado;
        this.numero = numero;
        this.inicio_fecha = inicio_fecha;
        this.final_fecha = final_fecha;
        this.vencimientos = vencimientos;
        this.est_descripcion = est_descripcion;
    }

    public Timbradosdto() {}

    public Integer getId_timbrado() {
        return id_timbrado;
    }

    public void setId_timbrado(Integer id_timbrado) {
        this.id_timbrado = id_timbrado;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getInicio_fecha() {
        return inicio_fecha;
    }

    public void setInicio_fecha(String inicio_fecha) {
        this.inicio_fecha = inicio_fecha;
    }

    public String getFinal_fecha() {
        return final_fecha;
    }

    public void setFinal_fecha(String final_fecha) {
        this.final_fecha = final_fecha;
    }

    public String getVencimientos() {
        return vencimientos;
    }

    public void setVencimientos(String vencimientos) {
        this.vencimientos = vencimientos;
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