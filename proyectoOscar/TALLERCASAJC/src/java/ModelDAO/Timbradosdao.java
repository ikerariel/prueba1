/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.Timbradosdto;

/**
 *
 * @author Oscar
 */
public interface Timbradosdao {
    
    boolean insertarTimbrados(Timbradosdto Dto);

    boolean modificarTimbrados(Timbradosdto Dto);

    boolean eliminarTimbrados(Integer id);

    String getmostrarTimbrados();
    //String getClientesHTML() throws mierror;

    Integer getUltimoCodigoTimbrados();

    String getmostrarTimbradosFiltro(Integer idFiltro);

//    public boolean getperfiles();
//    public boolean modificarCiudades(Barriosdto BARdto);
    String listarEstados();
    
}
