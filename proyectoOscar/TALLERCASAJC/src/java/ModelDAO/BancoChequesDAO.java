/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.BancoChequesDTO;

/**
 *
 * @author Oscar
 */
public interface BancoChequesDAO {
    
    boolean insertarBancoCheques(BancoChequesDTO Dto);

    boolean modificarBancoCheques(BancoChequesDTO Dto);

    boolean eliminarBancoCheques(Integer id);

    String getmostrarBancoCheques();
    //String getmostrarCiudades();

    Integer getUltimoCodigoBancoCheques();

    String getmostrarBancoChequesFiltro(Integer idFiltro);
    
}
