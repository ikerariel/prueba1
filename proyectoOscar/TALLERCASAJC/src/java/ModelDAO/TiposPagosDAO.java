/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.TiposPagosDTO;

/**
 *
 * @author Oscar
 */
public interface TiposPagosDAO {
    
    boolean insertarTiposPagos(TiposPagosDTO Dto);

    boolean modificarTiposPagos(TiposPagosDTO Dto);

    boolean eliminarTiposPagos(Integer id);

    String getmostrarTiposPagos();

    Integer getUltimoCodigoTiposPagos();

    String getmostrarTiposPagosFiltro(Integer idFiltro);
    
}
