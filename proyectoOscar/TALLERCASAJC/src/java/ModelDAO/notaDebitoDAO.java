/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.notaDebitoDTO;

/**
 *
 * @author Oscar
 */
public interface notaDebitoDAO {

    String getNotasDebitos();

    String getDetalleDebtio(Integer id);

    String getfactura(Integer facturaNRO);
    
    public boolean eliminarDetalle(Integer id);

    boolean insertarND(notaDebitoDTO Dto);

    boolean updateCabeceraND(notaDebitoDTO Dto);

    boolean updateND(Integer _estadoND, Integer _idND);

    boolean insertarDetalleND(notaDebitoDTO Dto);

    boolean insertarDetllaNd(notaDebitoDTO DTO);

    Integer getUltimoCodigo();
}
