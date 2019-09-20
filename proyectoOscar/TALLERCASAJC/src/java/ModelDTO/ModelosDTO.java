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
public class ModelosDTO {

    private Integer id_modelo;
    private String model_descri;

    public ModelosDTO(Integer id_modelo, String model_descri) {
        this.id_modelo=id_modelo;
        this.model_descri=model_descri;
         }

    public ModelosDTO() {
            }

    public Integer getId_modelo() {
        return id_modelo;
    }

    public void setId_modelo(Integer id_modelo) {
        this.id_modelo = id_modelo;
    }

    public String getModel_descri() {
        return model_descri;
    }

    public void setModel_descri(String model_descri) {
        this.model_descri = model_descri;
    }
   

}
