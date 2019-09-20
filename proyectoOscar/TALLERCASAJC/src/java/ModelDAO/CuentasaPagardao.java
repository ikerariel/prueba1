/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.CuentasaPagardto;

/**
 *
 * @author user
 */
public interface CuentasaPagardao {
    
    boolean insertarCuentasaPagar(CuentasaPagardto Dto);

    boolean modificarCuentasaPagar(CuentasaPagardto Dto);

    boolean eliminarCuentasaPagar(Integer id);

    String getmostrarCuentasaPagar();
    //String getmostrarArticulos();

    Integer getUltimoCodigoCuentasaPagar();

    String getmostrarCuentasaPagarFiltro(Integer idFiltro);

    String listarFacturasCompras();

    String listarEstados();
    
}
