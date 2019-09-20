/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDAO.NotaRemisionDAO;
import ModelDTO.NotaRemisionDTO;
import com.google.gson.Gson;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oscar
 */
public class NotaRemisionDAOIMPL implements NotaRemisionDAO{
    
    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public String getNotaRemision() {
        ResultSet rs;
        ArrayList<NotaRemisionDTO> allNotaRemision = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT r.id_notaremi, r.fecha_notaremi::date, r.id_estado, e.est_descripcion\n"
                    + "FROM notaremision r\n"
                    + "inner join estados e on r.id_estado = e.id_estado\n"
                    + "order by id_notaremi desc";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allNotaRemision.add(new NotaRemisionDTO(
                        rs.getInt("id_notaremi"),
                        rs.getString("fecha_notaremi"),
                        rs.getInt("id_estado"),
                        rs.getString("est_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotaRemisionDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allNotaRemision);
    }

    @Override
    public String getDetalleNotaRemision(Integer id) {
        ResultSet rs;
        ArrayList<NotaRemisionDTO> allDetNotaRemision = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT r.id_notaremi, r.fecha_notaremi::date, r.nro_notaremi, r.obser_notaremi,\n"
                    + "r.id_compra, (f.co_nrofact) as factura, r.id_estado, (e.est_descripcion) as estado,\n"
                    + "r.id_usuario, (u.usu_nombre) as usuario, d.id_articulo, d.cantinotaremi, d.precionotaremi\n"
                    + "FROM notaremision r\n"
                    + "INNER JOIN detnoremision d on r.id_notaremi = d.id_notaremi\n"
                    + "INNER JOIN estados e on r.id_estado = e.id_estado\n"
                    + "INNER JOIN usuarios u on r.id_usuario = u.id_usuario\n"
                    + "INNER JOIN facturascompras f on r.id_compra = f.id_compra\n"
                    + "where r.id_notaremi = ?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allDetNotaRemision.add(new NotaRemisionDTO(
                        rs.getString("fecha_notaremi"),
                        rs.getInt("nro_notaremi"),
                        rs.getString("obser_notaremi"),
                        rs.getInt("id_estado"),
                        rs.getString("estado"),
                        rs.getInt("id_usuario"),
                        rs.getString("usuario"),
                        rs.getInt("id_compra"),
                        rs.getInt("factura"),
                        rs.getInt("id_articulo"),
                        rs.getInt("cantinotaremi"),
                        rs.getInt("precionotaremi")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotaRemisionDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allDetNotaRemision);
    }

    @Override
    public String getfacturas(Integer facturaNRO) {
         ResultSet rs;
        ArrayList<NotaRemisionDTO> consultarfacturas = new ArrayList<>();

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_compra, \n"
                    + " co_nrofact\n"
                    + " FROM facturascompras\n"
                    + " where co_nrofact=?  and id_estado=1";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, facturaNRO);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                consultarfacturas.add(new NotaRemisionDTO(
                        rs.getInt("id_compra"),
                        rs.getInt("co_nrofact")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotaRemisionDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(consultarfacturas);
    }

    @Override
    public boolean eliminarDetalleNotaRemision(Integer id) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM detnoremision\n"
                    + "  WHERE id_notaremi= ?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, id);
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("Comit() Realizado");
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotaRemisionDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean insertarNotaRemision(NotaRemisionDTO Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO notaremision(nro_notaremi, obser_notaremi, id_estado, id_usuario, id_compra)\n"
                    + "VALUES (?, ?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getNro_notaremi());
            preparedStatement.setObject(2, Dto.getObser_notaremi());
            preparedStatement.setObject(3, Dto.getId_estado());
            preparedStatement.setObject(4, Dto.getId_usuario());
            preparedStatement.setObject(5, Dto.getId_compra());

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
            Logger.getLogger(NotaRemisionDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean updateCabeceraNotaRemision(NotaRemisionDTO Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();

            sintaxiSql = "UPDATE notaremision\n"
                    + " SET nro_notaremi=?, obser_notaremi=?, id_estado=3, id_usuario=?, id_compra=?\n"
                    + " WHERE id_notaremi=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getNro_notaremi());
            preparedStatement.setObject(2, Dto.getObser_notaremi());
            preparedStatement.setObject(3, Dto.getId_usuario());
            preparedStatement.setObject(4, Dto.getId_compra());
            preparedStatement.setObject(5, Dto.getId_notaremi());

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
            Logger.getLogger(NotaRemisionDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean updateNotaRemision(Integer _estadoNR, Integer _idNR) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE notaremision\n"
                    + " SET id_estado=?\n"
                    + " WHERE id_notaremi=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, _estadoNR);
            preparedStatement.setInt(2, _idNR);

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
            Logger.getLogger(NotaRemisionDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    @Override
    public boolean insertarDetalleNotaRemision(NotaRemisionDTO Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO detnoremision(id_notaremi, id_articulo, cantinotaremi, precionotaremi)\n"
                    + "VALUES (?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getId_notaremi());
            preparedStatement.setObject(2, Dto.getId_articulo());
            preparedStatement.setObject(3, Dto.getCantinotaremi());
            preparedStatement.setObject(4, Dto.getPrecionotaremi());

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
            Logger.getLogger(NotaRemisionDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean insertarDetllaNr(NotaRemisionDTO DTO) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO detnoremision(id_notaremi, id_articulo, cantinotaremi, precionotaremi)\n"
                    + "VALUES (?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, DTO.getId_notaremi());
            preparedStatement.setObject(2, DTO.getId_articulo());
            preparedStatement.setObject(3, DTO.getCantinotaremi());
            preparedStatement.setObject(4, DTO.getPrecionotaremi());

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
            Logger.getLogger(NotaRemisionDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Integer getUltimoCodigoNotaRemision() {
       ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(id_notaremi),0 )+ 1 as codigo  FROM notaremision;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotaRemisionDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }
    
}
