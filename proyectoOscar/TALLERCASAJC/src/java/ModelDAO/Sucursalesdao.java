/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.Sucursalesdto;

/**
 *
 * @author user
 */
public interface Sucursalesdao {
    
    boolean insertarSucursales(Sucursalesdto Dto);

    boolean modificarSucursales(Sucursalesdto Dto);

    boolean eliminarSucursales(Integer id);

    String getmostrarSucursales();
    //String getmostrarCiudades();

    Integer getUltimoCodigoSucursales();

    String getmostrarSucursalesFiltro(Integer idFiltro);
    
    String listarCiudades();
    
    String listarBarrios();
    
}
