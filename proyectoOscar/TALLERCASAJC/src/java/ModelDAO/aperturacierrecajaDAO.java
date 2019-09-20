/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.aperturacierrecajaDTO;

/**
 *
 * @author Carlos
 */
public interface aperturacierrecajaDAO {

    boolean insertarApertura(aperturacierrecajaDTO Dto);

    boolean insertarmovimientoarqueo(aperturacierrecajaDTO Dto);

    String getaperturascierres();

    String facturacion(String usuario);

    String facturado(Integer codapertura1, Integer codapertura2);

    String getfacturacion(String loguin);

    String getlistatimbrado();

    String gettimbrado(Integer timbrado);
}
