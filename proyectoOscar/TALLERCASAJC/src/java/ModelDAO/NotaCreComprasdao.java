/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.NotaCreComprasdto;

/**
 *
 * @author user
 */
public interface NotaCreComprasdao {

    String getNotaCreCompras();

    String getDetNotaCreCompras(Integer id);

    boolean insertarNC(NotaCreComprasdto Dto);

    boolean updateCabeceraNC(NotaCreComprasdto Dto);

    boolean updateNC(Integer _estadoNC, Integer _idNC);

    boolean insertarDetalleNC(NotaCreComprasdto Dto);

    boolean insertarDetllaNc(NotaCreComprasdto DTO);

    Integer getUltimoCodigo();
    
    String getfactura(Integer facturaNRO);

//    Integer getUltimoCodigoNotaCre1();
//    boolean insertarCabeceraNotaCre8(NotaCreComprasdto dto);
//    boolean insertarDetalleNotaCre9(NotaCreComprasdto dto);
//    boolean confirmarNotaCre11(NotaCreComprasdto dto);
//    
//    String listarArticulosNotaCre7();
//    String listarEstadoNotaCre2();
//    String listarUsuarioNotaCre3();
//    String listarSucursalNotaCre4();
//    
//    String listarfacturaNotaCre5();
//    String listarDetalleFactura6(Integer id);
//    
//    String listarNotaCre10();
//    String listarDetalleNotaCre12(Integer id);  
}
