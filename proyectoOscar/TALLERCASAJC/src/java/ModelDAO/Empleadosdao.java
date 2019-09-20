/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.Empleadosdto;

/**
 *
 * @author user
 */
public interface Empleadosdao {

    boolean insertarEmpleados(Empleadosdto Dto);

    boolean modificarEmpleados(Empleadosdto Dto);

    boolean eliminarEmpleados(Integer id);

    String getmostrarEmpleados();
    //String getmostrarCiudades();

    Integer getUltimoCodigoEmpleados();

    String getmostrarEmpleadosFiltro(Integer idFiltro);

    String listarBarrios();

    String listarCiudades();

}
