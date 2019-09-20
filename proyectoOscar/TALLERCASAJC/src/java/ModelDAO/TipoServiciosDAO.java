/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.TipoServiciosDTO;

/**
 *
 * @author Oscar
 */
public interface TipoServiciosDAO {
    
     boolean insertarTipoServicios(TipoServiciosDTO Dto);

    boolean modificarTipoServicios(TipoServiciosDTO Dto);

    boolean eliminarTipoServicios(Integer id);

    String getmostrarTipoServicios();
    //String getmostrarCiudades();

    Integer getUltimoCodigoTipoServicios();

    String getmostrarTipoServiciosFiltro(Integer idFiltro);
    
}
