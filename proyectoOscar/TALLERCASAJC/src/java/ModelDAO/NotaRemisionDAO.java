/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.NotaRemisionDTO;

/**
 *
 * @author Oscar
 */
public interface NotaRemisionDAO {
    
    String getNotaRemision();

    String getDetalleNotaRemision(Integer id);

    String getfacturas(Integer facturaNRO);

    public boolean eliminarDetalleNotaRemision(Integer id);

    boolean insertarNotaRemision(NotaRemisionDTO Dto);

    boolean updateCabeceraNotaRemision(NotaRemisionDTO Dto);

    boolean updateNotaRemision(Integer _estadoNR, Integer _idNR);

    boolean insertarDetalleNotaRemision(NotaRemisionDTO Dto);

    boolean insertarDetllaNr(NotaRemisionDTO DTO);

    Integer getUltimoCodigoNotaRemision();
}
