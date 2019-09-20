/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.Depositosdto;

/**
 *
 * @author user
 */
public interface Depositosdao {

    boolean insertarDepositos(Depositosdto Dto);

    boolean modificarDepositos(Depositosdto Dto);

    boolean eliminarDepositos(Integer id);

    String getmostrarDepositos();
    //String getmostrarCiudades();

    Integer getUltimoCodigoDepositos();

    String getmostrarDepositosFiltro(Integer idFiltro);

    String nuevoListarSucursales();

}
