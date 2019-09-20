/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.pedidoVentaDTO;

/**
 *
 * @author Usuario
 */
public interface pedidoVentaDAO {

    boolean insertarpedidoventa(pedidoVentaDTO dto, Integer cod);

    boolean deletedetallepedidoventa(pedidoVentaDTO dto);

    boolean updatepedidoventa(pedidoVentaDTO dto);

    boolean insertardetallepedidoventa(pedidoVentaDTO dto, Integer detalle);

    String getpedidoVenta();

    String getpedidoVentaDetalle(Integer nropedidoventa);
}
