/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.ModelosDTO;

/**
 *
 * @author Oscar
 */
public interface ModelosDAO {
    
    boolean insertarModelos(ModelosDTO Dto);

    boolean modificarModelos(ModelosDTO Dto);

    boolean eliminarModelos(Integer id);

    String getmostrarModelos();
    //String getClientesHTML() throws mierror;

    Integer getUltimoCodigoModelos();

    String getmostrarModelosFiltro(Integer idFiltro);
    
}
