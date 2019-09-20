/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.Proveedoresdto;

/**
 *
 * @author user
 */
public interface Proveedoresdao {

    boolean insertarProveedores(Proveedoresdto Dto);

    boolean modificarProveedores(Proveedoresdto Dto);

    boolean eliminarProveedores(Integer id);

    String getmostrarProveedores();
    //String getClientesHTML() throws mierror;

    Integer getUltimoCodigoProveedores();

    String getmostrarProveedoresFiltro(Integer idFiltro);

    String listarCiudades();

}
