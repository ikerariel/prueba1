/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.TipochequesDTO;

/**
 *
 * @author Oscar
 */
public interface TipochequesDAO {
    
    boolean insertarTipocheques(TipochequesDTO Dto);

    boolean modificarTipocheques(TipochequesDTO Dto);

    boolean eliminarTipocheques(Integer id);

    String getmostrarTipocheques();
    //String getmostrarCiudades();

    Integer getUltimoCodigoTipocheques();

    String getmostrarTipochequesFiltro(Integer idFiltro);
    
}
