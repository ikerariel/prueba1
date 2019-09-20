/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.Articulosdto;

/**
 *
 * @author user
 */
public interface Articulosdao {

    boolean insertarArticulos(Articulosdto Dto);

    boolean modificarArticulos(Articulosdto Dto);

    boolean eliminarArticulos(Integer id);

    String getmostrarArticulos();
    //String getmostrarArticulos();

    Integer getUltimoCodigoArticulos();

    String getmostrarArticulosFiltro(Integer idFiltro);

    String listarMarcas();

    String listarImpuestos();

}
