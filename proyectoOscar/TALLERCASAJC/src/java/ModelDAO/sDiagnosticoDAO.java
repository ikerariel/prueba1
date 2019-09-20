/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.sDiagnosticoDTO;

/**
 *
 * @author Usuario
 */
public interface sDiagnosticoDAO {

    boolean insertarDiagnostico(sDiagnosticoDTO dto, Integer cod);

    boolean insertarDetalleDiagnostico(sDiagnosticoDTO dto,Integer sRecepcion);

    boolean deleteDetalleDiagnostico(sDiagnosticoDTO dto);

    boolean actualizarEstado(sDiagnosticoDTO dto);

    String getdetallesDiagnostico(Integer codSrecpcion);

    String getsDiagnostico();

}
