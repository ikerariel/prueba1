/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.TiposTarjetasdto;

/**
 *
 * @author user
 */
public interface TiposTarjetasdao {

    boolean insertarTiposTarjetas(TiposTarjetasdto Dto);

    boolean modificarTiposTarjetas(TiposTarjetasdto Dto);

    boolean eliminarTiposTarjetas(Integer id);

    String getmostrarTiposTarjetas();

    Integer getUltimoCodigoTiposTarjetas();

    String getmostrarTiposTarjetasFiltro(Integer idFiltro);

}
