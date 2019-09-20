/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.Coloresdto;

/**
 *
 * @author Oscar
 */
public interface Coloresdao {
    
    boolean insertarColores(Coloresdto Dto);

    boolean modificarColores(Coloresdto Dto);

    boolean eliminarColores(Integer id);

    String getmostrarColores();
    //String getmostrarCiudades();

    Integer getUltimoCodigoColores();

    String getmostrarColoresFiltro(Integer idFiltro);
}
