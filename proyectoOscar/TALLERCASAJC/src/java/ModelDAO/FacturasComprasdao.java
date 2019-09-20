/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.FacturasComprasdto;

/**
 *
 * @author user
 */
public interface FacturasComprasdao {

    Integer getUltimoCodigoCompras();

    String ListarEstadosCompras2();

    String ListarUsuariosCompras3();

    String ListarProveedoresCompras4();

    String ListarOrdenCompras5();

    String ListarDetOrdenCompras6(Integer id);

    String ListarSucursalesCompras7();

    String ListarArticulosCompras8();

    boolean insertarCabeceraCompras9(FacturasComprasdto dto);

    boolean modificarCabeceraCompras9(FacturasComprasdto dto);

    boolean insertarDetCompras10(FacturasComprasdto dto);

    String ListarFacturasCompras11();

    boolean confirmarFacturasCompras12(FacturasComprasdto dto);

    String listarDetCompras13(Integer id);

}
