/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.CargosDTO;

/**
 *
 * @author Oscar
 */
public interface CargosDAO {
    
    boolean insertarCargos(CargosDTO Dto);

    boolean modificarCargos(CargosDTO Dto);

    boolean eliminarCargos(Integer id);

    String getmostrarCargos();
    //String getmostrarCiudades();

    Integer getUltimoCodigoCargos();

    String getmostrarCargosFiltro(Integer idFiltro);
    
}
