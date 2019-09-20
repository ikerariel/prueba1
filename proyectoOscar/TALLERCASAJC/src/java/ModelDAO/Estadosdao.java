/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.Estadosdto;

/**
 *
 * @author user
 */
public interface Estadosdao {

    boolean insertarEstados(Estadosdto Dto);

    boolean modificarEstados(Estadosdto Dto);

    boolean eliminarEstados(Integer id);

    String getmostrarEstados();
    //String getmostrarCiudades();

    Integer getUltimoCodigoEstados();

    String getmostrarEstadosFiltro(Integer idFiltro);

}
