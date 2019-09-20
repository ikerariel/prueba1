/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.Usuariosdto;

/**
 *
 * @author user
 */
public interface Usuariosdao {
    
    boolean insertarUsuarios(Usuariosdto Dto);

    boolean modificarUsuarios(Usuariosdto Dto);

    boolean eliminarUsuarios(Integer id);
    //ArrayList<productosdto> getAllProductos () throws mierror;

    String getmostrarUsuarios();

    Integer getUltimoCodigoUsuarios();

    String getmostrarUsuariosFiltro(Integer idFiltro);

    String listarEmpleados();

    String listarSucursales();

    String listarPerfiles();
    
}
