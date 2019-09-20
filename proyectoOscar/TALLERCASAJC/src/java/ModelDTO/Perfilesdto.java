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
public class Perfilesdto {

    private Integer id_perfil;
    private String perf_descripcion;

    public Perfilesdto(Integer id_perfil, String perf_descripcion) {
        this.id_perfil = id_perfil;
        this.perf_descripcion = perf_descripcion;
    }

    public Perfilesdto() {

    }

    public Integer getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(Integer id_perfil) {
        this.id_perfil = id_perfil;
    }

    public String getPerf_descripcion() {
        return perf_descripcion;
    }

    public void setPerf_descripcion(String perf_descripcion) {
        this.perf_descripcion = perf_descripcion;
    }

}
