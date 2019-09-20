/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDAO.notaDebitoDAO;
import ModelDTO.notaDebitoDTO;
import com.google.gson.Gson;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class notaDebitoDAOIMPLE implements notaDebitoDAO {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public String getNotasDebitos() {
        ResultSet rs;
        ArrayList<notaDebitoDTO> allnotasD = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = " SELECT n.id_notadecompra, n.fecha::date,\n"
                    + " n.id_estado, e.est_descripcion\n"
                    + " FROM public.notadecompras n\n"
                    + " inner join estados e on n.id_estado = e.id_estado\n"
                    + " order by id_notadecompra desc ";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allnotasD.add(new notaDebitoDTO(
                        rs.getInt("id_notadecompra"),
                        rs.getString("fecha"),
                        rs.getInt("id_estado"),
                        rs.getString("est_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(notaDebitoDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allnotasD);
    }

    @Override
    public boolean insertarND(notaDebitoDTO Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO notadecompras( nro_notadebito, nro_timbradonotadebito, \n"
                    + "id_compra, id_estado, id_usuario)\n"
                    + "VALUES (?, ?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getNro_notadebito());
            preparedStatement.setObject(2, Dto.getNro_timbradonotadebito());
            preparedStatement.setObject(3, Dto.getId_compra());
            preparedStatement.setObject(4, Dto.getId_estado());
            preparedStatement.setObject(5, Dto.getIdusuario());

            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("Comit() Realizado");
                return true;
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
            }
            conexion.desConectarBD();
        } catch (SQLException ex) {
            Logger.getLogger(notaDebitoDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean insertarDetalleND(notaDebitoDTO Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO detnotadecompras(id_notadecompra, importe, conceptos)\n"
                    + "VALUES (?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getId_notadecompra());
            preparedStatement.setObject(2, Dto.getImporte());
            preparedStatement.setObject(3, Dto.getConceptos());

            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("Comit() Realizado");
                return true;
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
            }
            conexion.desConectarBD();
        } catch (SQLException ex) {
            Logger.getLogger(notaDebitoDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Integer getUltimoCodigo() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(id_notadecompra),0 )+ 1 as codigo  FROM notadecompras;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(notaDebitoDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    @Override
    public boolean insertarDetllaNd(notaDebitoDTO DTO) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO detnotadecompras(id_notadecompra, importe, conceptos)\n"
                    + "VALUES (?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, DTO.getId_notadecompra());
            preparedStatement.setObject(2, DTO.getImporte());
            preparedStatement.setObject(3, DTO.getConceptos());

            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("Comit() Realizado");
                return true;
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(notaDebitoDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getDetalleDebtio(Integer id) {
        ResultSet rs;
        ArrayList<notaDebitoDTO> alldetalleND = new ArrayList<>();

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT n.id_notadecompra, n.fecha::date, n.nro_notadebito, n.nro_timbradonotadebito, \n"
                    + "n.id_compra, (f.co_nrofact) as factura, n.id_estado, (e.est_descripcion) as estado,\n"
                    + " n.id_usuario, (u.usu_nombre) as usuario, d.importe, d.conceptos\n"
                    + "FROM notadecompras n\n"
                    + "INNER JOIN detnotadecompras d on n.id_notadecompra = d.id_notadecompra\n"
                    + "INNER JOIN facturascompras f on n.id_compra = f.id_compra\n"
                    + "INNER JOIN usuarios u on n.id_usuario = u.id_usuario\n"
                    + "INNER JOIN estados e on n.id_estado = e.id_estado\n"
                    + "where n.id_notadecompra = ?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alldetalleND.add(new notaDebitoDTO(
                        rs.getString("fecha"),
                        rs.getInt("nro_notadebito"),
                        rs.getInt("nro_timbradonotadebito"),
                        rs.getInt("id_compra"),
                        rs.getInt("factura"),
                        rs.getInt("id_usuario"),
                        rs.getString("usuario"),
                        rs.getInt("id_estado"),
                        rs.getString("estado"),
                        rs.getString("conceptos"),
                        rs.getInt("importe")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(notaDebitoDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alldetalleND);
    }

    @Override
    public String getfactura(Integer facturaNRO) {
        ResultSet rs;
        ArrayList<notaDebitoDTO> consultarfactura = new ArrayList<>();

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_compra, \n"
                    + "       co_nrofact\n"
                    + "  FROM facturascompras\n"
                    + "  where co_nrofact=?  and id_estado=1";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, facturaNRO);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                consultarfactura.add(new notaDebitoDTO(
                        rs.getInt("id_compra"),
                        rs.getInt("co_nrofact")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(notaDebitoDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(consultarfactura);
    }

    @Override
    public boolean updateND(Integer _estadoND, Integer _idND) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE notadecompras\n"
                    + "   SET  id_estado=?\n"
                    + " WHERE id_notadecompra=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, _estadoND);
            preparedStatement.setInt(2, _idND);

            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("Comit() Realizado");
                return true;
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
            }
            conexion.desConectarBD();
        } catch (SQLException ex) {
            Logger.getLogger(notaDebitoDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    @Override
    public boolean updateCabeceraND(notaDebitoDTO Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE notadecompras\n"
                    + "   SET nro_notadebito=?, nro_timbradonotadebito=?, \n"
                    + "       id_compra=?, id_estado=3, id_usuario=?\n"
                    + " WHERE id_notadecompra=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getNro_notadebito());
            preparedStatement.setObject(2, Dto.getNro_timbradonotadebito());
            preparedStatement.setObject(3, Dto.getId_compra());
            preparedStatement.setObject(4, Dto.getIdusuario());
            preparedStatement.setObject(5, Dto.getId_notadecompra());

            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("Comit() Realizado");
                return true;
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
            }
            conexion.desConectarBD();
        } catch (SQLException ex) {
            Logger.getLogger(notaDebitoDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean eliminarDetalle(Integer id) {

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM detnotadecompras\n"
                    + " WHERE id_notadecompra = ?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, id);
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("Comit() Realizado");
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(notaDebitoDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
