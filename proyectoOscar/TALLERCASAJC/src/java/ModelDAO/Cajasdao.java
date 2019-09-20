/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.Cajasdto;

/**
 *
 * @author user
 */
public interface Cajasdao {

    boolean insertarcaja(Cajasdto Dto);

    boolean modificarcaja(Cajasdto Dto);

    boolean eliminarcaja(Integer id);

    String getmostrarcaja();

    Integer getUltimoCodigocaja();

    String getmostrarcajaFiltro(Integer idFiltro);
}
