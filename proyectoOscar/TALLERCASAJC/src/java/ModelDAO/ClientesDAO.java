/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.ClientesDTO;

/**
 *
 * @author Oscar
 */
public interface ClientesDAO {
    
    boolean insertarClientes(ClientesDTO Dto);

    boolean modificarClientes(ClientesDTO Dto);

    boolean eliminarClientes(Integer id);

    String getmostrarClientes();

    Integer getUltimoCodigoClientes();

    String getmostrarClientesFiltro(Integer idFiltro);

    String listarBarrios();

    String listarCiudades();
    
}
