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
public class TiposTarjetasdto {

    private Integer id_tipotarjeta;
    private String tarj_tipo;

    public TiposTarjetasdto(Integer id_tipotarjeta, String tarj_tipo) {
        this.id_tipotarjeta = id_tipotarjeta;
        this.tarj_tipo = tarj_tipo;
    }

    public Integer getId_tipotarjeta() {
        return id_tipotarjeta;
    }

    public void setId_tipotarjeta(Integer id_tipotarjeta) {
        this.id_tipotarjeta = id_tipotarjeta;
    }

    public String getTarj_tipo() {
        return tarj_tipo;
    }

    public void setTarj_tipo(String tarj_tipo) {
        this.tarj_tipo = tarj_tipo;
    }

    public TiposTarjetasdto() {

    }



}
