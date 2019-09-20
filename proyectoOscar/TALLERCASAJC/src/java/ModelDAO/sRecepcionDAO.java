/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.sRecepcionDTO;

/**
 *
 * @author Usuario
 */
public interface sRecepcionDAO {

    boolean insertarRecepcion(sRecepcionDTO dto, Integer cod);

    boolean insertarDetalleRecepcion(sRecepcionDTO dto,Integer sRecepcion);
    String CalcularDV(String numero, Integer p_basemax);

    boolean deleteDetalleRecepcion(sRecepcionDTO dto);

    boolean actualizarEstado(sRecepcionDTO dto);

    String getdetallesRecepcion(Integer codSrecpcion);

    String getsRecepcion();

}
