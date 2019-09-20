/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.ventaDTO;

/**
 *
 * @author Carlos
 */
public interface ventaDAO {

    String getfactura(String caja);

    String gettimbrado();

    Integer getcodigo();

    String getvendedor();

    String gettipopago();

    boolean insertarventa(ventaDTO Dto);

    boolean insertarcobro(ventaDTO Dto);

    boolean insertarcobrotarjeta(ventaDTO Dto);

    boolean insertarcobrocheque(ventaDTO Dto);

    boolean insertardetalle(ventaDTO Dto);

    boolean actualizarfactura(ventaDTO Dto);

    String getcliente(String cedula);

    String getfac(String user);

    String getidvendedor(Integer codVendedor);

}
