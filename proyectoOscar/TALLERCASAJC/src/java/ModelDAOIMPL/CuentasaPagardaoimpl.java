/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDAO.CuentasaPagardao;
import ModelDTO.CuentasaPagardto;
import ModelDTO.Estadosdto;
import ModelDTO.FacturasComprasdto;
import com.google.gson.Gson;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
//public class CuentasaPagardaoimpl implements CuentasaPagardao {
//
//    private String sintaxiSql;
//    private PreparedStatement preparedStatement;
//    private ResultSet resultado;
//    private Conexion conexion;
//    private int filasAfectadas;
//
//    @Override
//    public boolean insertarCuentasaPagar(CuentasaPagardto Dto) {
//        try {
//            sintaxiSql = null;
//            conexion = new Conexion();
//
//            sintaxiSql = "INSERT INTO cuentasapagar(id_cuentpag, fechavence, saldo, monto, id_compra, id_estado)\n"
//                    + "VALUES (?, ?, ?, ?, ?, ?);";
//            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
//            preparedStatement.setObject(1, Dto.getId_cuentpag());
//            preparedStatement.setObject(2, Dto.getFechavence());
//            preparedStatement.setObject(3, Dto.getSaldo());
//            preparedStatement.setObject(4, Dto.getMonto());
//            preparedStatement.setObject(5, Dto.getId_compra());
//            preparedStatement.setObject(6, Dto.getId_estado());
//            filasAfectadas = preparedStatement.executeUpdate();
//            if (filasAfectadas > 0) {
//                conexion.comit();
//                System.out.println("Comit() Realizado");
//                return true;
//            } else {
//                conexion.rollback();
//                System.out.println("Rollback() Realizado");
//            }
//            conexion.desConectarBD();
//        } catch (SQLException ex) {
//            Logger.getLogger(CuentasaPagardaoimpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
//
//    @Override
//    public boolean modificarCuentasaPagar(CuentasaPagardto Dto) {
//        try {
//            sintaxiSql = null;
//            conexion = new Conexion();
//            sintaxiSql = " UPDATE cuentasapagar\n"
//                    + " SET id_cuentpag=?, fechavence=?, saldo=?, monto=?, id_compra=?, id_estado=?\n"
//                    + " WHERE id_cuentpag=?;";
//            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
//            preparedStatement.setObject(1, Dto.getFechavence());
//            preparedStatement.setObject(2, Dto.getSaldo());
//            preparedStatement.setObject(3, Dto.getMonto());
//            preparedStatement.setObject(4, Dto.getId_compra());
//            preparedStatement.setObject(5, Dto.getId_estado());
//            preparedStatement.setObject(6, Dto.getId_cuentpag());
//
//            filasAfectadas = preparedStatement.executeUpdate();
//            if (filasAfectadas > 0) {
//                conexion.comit();
//                System.out.println("Comit() Realizado");
//                return true;
//            } else {
//                conexion.rollback();
//                System.out.println("Rollback() Realizado");
//            }
//            conexion.desConectarBD();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(CuentasaPagardaoimpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
//
//    @Override
//    public boolean eliminarCuentasaPagar(Integer id) {
//        try {
//            sintaxiSql = null;
//            conexion = new Conexion();
//            sintaxiSql = "DELETE FROM cuentasapagar WHERE id_cuentpag=?;";
//            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
//            preparedStatement.setObject(1, id);
//            filasAfectadas = preparedStatement.executeUpdate();
//            if (filasAfectadas > 0) {
//                conexion.comit();
//                System.out.println("Comit() Realizado");
//                return true;
//            } else {
//                conexion.rollback();
//                System.out.println("Rollback() Realizado");
//            }
//            conexion.desConectarBD();
//        } catch (SQLException ex) {
//            Logger.getLogger(CuentasaPagardaoimpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
//
//    @Override
//    public String getmostrarCuentasaPagar() {
//        ResultSet rs;
//        ArrayList<CuentasaPagardto> allCuentasaPagar = new ArrayList<>();
//        try {
//            sintaxiSql = null;
//            conexion = new Conexion();
//            sintaxiSql = "SELECT cp.id_cuentpag, cp.fechavence, cp.saldo, cp.monto, fc.co_cantcuota, e.est_descripcion\n"
//                    + " FROM cuentasapagar cp, facturascompras fc, estados e        \n"
//                    + " WHERE cp.id_compra = fc.id_compra AND cp.id_estado = e.id_estado\n"
//                    + " ORDER BY cp.id_cuentpag; ";
//            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
//            rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                allCuentasaPagar.add(new CuentasaPagardto(
//                        rs.getInt("id_cuentpag"),
//                        rs.getString("fechavence"),
//                        rs.getInt("saldo"),
//                        rs.getInt("monto"),
//                        rs.getString("co_cantcuota"),
//                        rs.getString("est_descripcion")
//                ));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(CuentasaPagardaoimpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return new Gson().toJson(allCuentasaPagar);
//    }
//
//    @Override
//    public Integer getUltimoCodigoCuentasaPagar() {
//        ResultSet rs;
//        try {
//            sintaxiSql = null;
//            conexion = new Conexion();
//            sintaxiSql = "SELECT coalesce(max(id_cuentpag),0 )+ 1 as codigo  FROM cuentasapagar;";
//            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
//            rs = preparedStatement.executeQuery();
//            if (rs.next()) {
//                return rs.getInt("codigo");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(CuentasaPagardaoimpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return 0;
//    }
//
//    @Override
//    public String getmostrarCuentasaPagarFiltro(Integer idFiltro) {
//        ResultSet rs;
//        ArrayList<CuentasaPagardto> allCuentasaPagar = new ArrayList<>();
//        try {
//            sintaxiSql = null;
//            conexion = new Conexion();
//            sintaxiSql = "SELECT id_cuentpag, fechavence, saldo, monto, id_compra, id_estado\n"
//                    + " FROM cuentasapagar WHERE id_cuentpag=?;";
//            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
//            preparedStatement.setInt(1, idFiltro);
//            rs = preparedStatement.executeQuery();
//            if (rs.next()) {
//                allCuentasaPagar.add(new CuentasaPagardto(
//                        rs.getInt("id_cuentpag"),
//                        rs.getString("fechavence"),
//                        rs.getInt("saldo"),
//                        rs.getInt("monto"),
//                        rs.getInt("id_compra"),
//                        rs.getInt("id_estado")));
//            } else {
//                return null;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(CuentasaPagardaoimpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return new Gson().toJson(allCuentasaPagar);
//    }
//
//    @Override
//    public String listarFacturasCompras() {
//        ResultSet rs;
//        ArrayList<FacturasComprasdto> allFacturasCompras = new ArrayList<>();
//        try {
//            sintaxiSql = null;
//            conexion = new Conexion();
//            sintaxiSql = "SELECT id_compra, co_cantcuota, co_monto, co_nrofact, co_intervalo, co_fecha, \n"
//                    + " co_tipo, id_proveedor, id_sucursal, id_usuario, id_estado, id_ordencompra\n"
//                    + " FROM facturascompras;";
//            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
//            rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                allFacturasCompras.add(new FacturasComprasdto(
//                        rs.getInt("id_compra"),
//                        rs.getInt("co_cantcuota"),
//                        rs.getInt("co_monto"),
//                        rs.getString("co_nrofact"),
//                        rs.getString("co_intervalo"),
//                        rs.getString("co_fecha"),
//                        rs.getString("co_tipo"),
//                        rs.getInt("id_proveedor"),
//                        rs.getInt("id_sucursal"),
//                        rs.getInt("id_usuario"),
//                        rs.getInt("id_estado"),
//                        rs.getInt("id_ordencompra")));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(FacturasComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return new Gson().toJson(allFacturasCompras);
//    }
//
//    @Override
//    public String listarEstados() {
//        ResultSet rs;
//        ArrayList<Estadosdto> allEstados = new ArrayList<>();
//        try {
//            sintaxiSql = null;
//            conexion = new Conexion();
//            sintaxiSql = "SELECT id_estado, est_descripcion\n"
//                    + "  FROM estados WHERE id_estado=1;";
//            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
//            rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                allEstados.add(new Estadosdto(
//                        rs.getInt("id_estado"),
//                        rs.getString("est_descripcion")));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(CuentasaPagardaoimpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return new Gson().toJson(allEstados);
//    }
//
//}
