/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.presupuestoDTO;

/**
 *
 * @author Carlos
 */
public interface presupuestoDAO {
    boolean insertarpresupuesto(presupuestoDTO Dto, Integer v_caso);
    boolean insertardetallepresupuesto(presupuestoDTO Dto);
    boolean deletedealle(presupuestoDTO Dto);
    boolean actualizarestado(presupuestoDTO Dto);
    String getproveedor();
    String getdetallepresupuesto(Integer codpresupuesto);
    String gettipomoneda();
    String getpresupuesto();
}
