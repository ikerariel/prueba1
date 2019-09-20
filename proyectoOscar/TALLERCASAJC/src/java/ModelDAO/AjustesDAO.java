/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.AjustesDTO;

/**
 *
 * @author Oscar
 */
public interface AjustesDAO {
    
    String getTraerAjustes();

    Integer getUltimoCodigoAjustes();

    String getTipoAjustes();

    String getAjustes(Integer codAjustes);

    boolean insertarAjustes(AjustesDTO Dto);

    boolean insertarDetallesAjustes(AjustesDTO Dto);

    boolean aprobarAjustes(AjustesDTO Dto);
    
}
