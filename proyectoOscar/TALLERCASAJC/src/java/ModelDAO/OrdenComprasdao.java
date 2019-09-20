/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.OrdenComprasdto;

/**
 *
 * @author user
 */
public interface OrdenComprasdao {

    Integer getUltimoCodigo();

    boolean insertarOrdenCompras(OrdenComprasdto dto);

    boolean insertarDetOrdenCompras(OrdenComprasdto dto);

    String getCabeseraFiltroOrdenCompras(Integer idFiltro);

    String getArticulo(Integer cod, Integer coddepo);

    String getDetallesFiltroOrdenCompras(Integer idFiltro);

    boolean modificarOrdenCompras(OrdenComprasdto dto);

    boolean confirmarOrdenCompras(OrdenComprasdto dto);

    boolean modificarDetOrdenCompras(OrdenComprasdto dto);

    String listarOrdenCompras();

    String listarSucursales();

    String listarProveedores();

    String listarPedidosCompras();

    String listarUsuarios();

    String listarArticulos();

    String listarEstados();

    String listarDetPedidosCompras(Integer id);

    String productosPorDeposito(Integer id);

    String listarDetOrdenCompras(Integer id);

    Integer getUltimoCodigoArticulos();

    boolean insertarArticulos(OrdenComprasdto dto);

}
