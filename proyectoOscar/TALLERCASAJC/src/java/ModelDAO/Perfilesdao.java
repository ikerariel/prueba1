/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.Perfilesdto;

/**
 *
 * @author user
 */
public interface Perfilesdao {

   boolean insertarPerfiles(Perfilesdto Dto);

    boolean modificarPerfiles(Perfilesdto Dto);

    boolean eliminarPerfiles(Integer id);

    String getmostrarPerfiles();
    //String getmostrarCiudades();

    Integer getUltimoCodigoPerfiles();

    String getmostrarPerfilesFiltro(Integer idFiltro);

}
