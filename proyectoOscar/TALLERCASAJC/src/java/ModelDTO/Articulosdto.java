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
public class Articulosdto {

    private Integer id_articulo;
    private String art_descripcion;
    private Integer preccompras;
    private Integer precventas;

    private Integer id_impuesto;
    private String imp_descripcion;
    private Integer id_marca;
    private String mar_descripcion;
    private String codigenerico;

    public Articulosdto() {
    }

    public Articulosdto(Integer id_articulo, String art_descripcion, Integer preccompras, Integer precventas,
            Integer id_impuesto, Integer id_marca, String codigenerico) {
        this.id_articulo = id_articulo;
        this.art_descripcion = art_descripcion;
        this.preccompras = preccompras;
        this.precventas = precventas;
        this.id_impuesto = id_impuesto;
        this.id_marca = id_marca; 
        this.codigenerico = codigenerico; }

    public Articulosdto(Integer id_articulo, String art_descripcion, Integer preccompras, Integer precventas, 
            String imp_descripcion, String mar_descripcion, String codigenerico) {
        this.id_articulo = id_articulo;
        this.art_descripcion = art_descripcion;
        this.preccompras = preccompras;
        this.precventas = precventas;
        this.imp_descripcion = imp_descripcion;
        this.mar_descripcion = mar_descripcion;  
        this.codigenerico = codigenerico; }

    public Integer getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(Integer id_articulo) {
        this.id_articulo = id_articulo;
    }

    public String getArt_descripcion() {
        return art_descripcion;
    }

    public void setArt_descripcion(String art_descripcion) {
        this.art_descripcion = art_descripcion;
    }

    public Integer getPreccompras() {
        return preccompras;
    }

    public void setPreccompras(Integer preccompras) {
        this.preccompras = preccompras;
    }

    public Integer getPrecventas() {
        return precventas;
    }

    public void setPrecventas(Integer precventas) {
        this.precventas = precventas;
    }

    public Integer getId_impuesto() {
        return id_impuesto;
    }

    public void setId_impuesto(Integer id_impuesto) {
        this.id_impuesto = id_impuesto;
    }

    public String getImp_descripcion() {
        return imp_descripcion;
    }

    public void setImp_descripcion(String imp_descripcion) {
        this.imp_descripcion = imp_descripcion;
    }

    public Integer getId_marca() {
        return id_marca;
    }

    public void setId_marca(Integer id_marca) {
        this.id_marca = id_marca;
    }

    public String getMar_descripcion() {
        return mar_descripcion;
    }

    public void setMar_descripcion(String mar_descripcion) {
        this.mar_descripcion = mar_descripcion;
    }

    public String getCodigenerico() {
        return codigenerico;
    }

    public void setCodigenerico(String codigenerico) {
        this.codigenerico = codigenerico;
    }
}
