/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.Impuestosdto;

/**
 *
 * @author user
 */
public interface Impuestosdao {

    boolean insertarImpuestos(Impuestosdto Dto);

    boolean modificarImpuestos(Impuestosdto Dto);

    boolean eliminarImpuestos(Integer id);

    String getmostrarImpuestos();
    //String getmostrarImpuestos();

    Integer getUltimoCodigoImpuestos();

    String getmostrarImpuestosFiltro(Integer idFiltro);

}
