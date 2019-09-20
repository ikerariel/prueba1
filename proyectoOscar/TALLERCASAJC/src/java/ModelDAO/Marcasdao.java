/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.Marcasdto;

/**
 *
 * @author user
 */
public interface Marcasdao {

    boolean insertarMarcas(Marcasdto Dto);

    boolean modificarMarcas(Marcasdto Dto);

    boolean eliminarMarcas(Integer id);

    String getmostrarMarcas();
    //String getmostrarCiudades();

    Integer getUltimoCodigoMarcas();

    String getmostrarMarcasFiltro(Integer idFiltro);

}
