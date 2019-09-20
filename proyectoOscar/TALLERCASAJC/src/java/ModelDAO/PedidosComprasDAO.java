/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.PedidosComprasDTO;

/**
 *
 * @author Oscar
 */
public interface PedidosComprasDAO {
    
    Integer getUltimoCodigo();

    boolean insertar(PedidosComprasDTO dto);
    boolean insertarDetalles(PedidosComprasDTO dto);
    boolean insertarArtpedido(String pArticulo);
    boolean deledetalle(Integer nropedido);

    String getcabeseraFiltro(Integer idFiltro);
    String getdetalleFiltro(Integer idFiltro);

    boolean modificar(PedidosComprasDTO dto);
    boolean confirmar(PedidosComprasDTO dto);
    boolean modificarDetalles(PedidosComprasDTO dto);

    String listarUsuarios();
    String listarArticulos();
    String listarEstados();
    String listarPedidosCompras();
    String listarDetallesCompras(Integer id);
    
}
