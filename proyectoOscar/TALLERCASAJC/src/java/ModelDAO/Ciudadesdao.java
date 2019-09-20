/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.Ciudadesdto;

/**
 *
 * @author user
 */
public interface Ciudadesdao {

    boolean insertarCiudades(Ciudadesdto Dto);

    boolean modificarCiudades(Ciudadesdto Dto);

    boolean eliminarCiudades(Integer id);

    String getmostrarCiudades();
    //String getmostrarCiudades();

    Integer getUltimoCodigoCiudades();

    String getmostrarCiudadesFiltro(Integer idFiltro);

}
