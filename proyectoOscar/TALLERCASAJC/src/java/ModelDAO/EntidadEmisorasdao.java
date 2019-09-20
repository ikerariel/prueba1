/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.EntidadEmisorasdto;

/**
 *
 * @author user
 */
public interface EntidadEmisorasdao {

    boolean insertarCiudades(EntidadEmisorasdto Dto);

    boolean modificarEntidadEmisoras(EntidadEmisorasdto Dto);

    boolean eliminarEntidadEmisoras(Integer id);

    String getmostrarEntidadEmisoras();
    //String getmostrarCiudades();

    Integer getUltimoCodigoEntidadEmisoras();

    String getmostrarEntidadEmisorasFiltro(Integer idFiltro);

}
