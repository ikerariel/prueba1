/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDAO.AjustesDAO;
import ModelDTO.AjustesDTO;
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
public class AjustesDAOIMPL implements AjustesDAO{
    
    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public String getTraerAjustes() {
        ResultSet rs;
        ArrayList<AjustesDTO> allAjustes = new ArrayList<>();
        try {

            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT a.id_ajuste, a.fecha_ajustes::date, a.id_sucursal, a.id_deposito, a.id_usuario, a.id_estado, e.est_descripcion\n"
                    + "FROM ajustes a\n"
                    + "INNER JOIN estados e ON a.id_estado=e.id_estado order by a.id_ajuste desc";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allAjustes.add(new AjustesDTO(
                        rs.getInt("id_ajuste"),
                        rs.getString("fecha_ajustes"),
                        rs.getString("est_descripcion")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AjustesDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allAjustes);
    }

    @Override
    public Integer getUltimoCodigoAjustes() {
        ResultSet rs;
        try {

            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(id_ajuste),0 )+ 1 as codigo  FROM ajustes;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }

        } catch (SQLException ex) {
            Logger.getLogger(AjustesDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public String getTipoAjustes() {
         ResultSet rs;
        ArrayList<AjustesDTO> allTipoAjustes = new ArrayList<>();
        try {

            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_tipajuste, descripcion FROM tipoajustes;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allTipoAjustes.add(new AjustesDTO(
                        rs.getInt("id_tipajuste"),
                        rs.getString("descripcion")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AjustesDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allTipoAjustes);
    }

    @Override
    public String getAjustes(Integer codAjustes) {
       ResultSet rs;
        ArrayList<AjustesDTO> allAjustes = new ArrayList<>();
        try {

            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT a.id_ajuste, a.fecha_ajustes::date, a.id_sucursal, a.id_deposito, a.id_usuario, \n"
                    + "       a.id_estado, a.observacion, a.id_tipajuste, t.descripcion, d.id_articulo, ar.art_descripcion, d.cantexistencia\n"
                    + "FROM ajustes a\n"
                    + "INNER JOIN detajustes d on a.id_ajuste = d.id_ajuste\n"
                    + "Inner join articulos ar on d.id_articulo = ar.id_articulo\n"
                    + "Inner Join tipoajustes t on a.id_tipajuste = t.id_tipajuste\n"
                    + "where a.id_ajuste =?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, codAjustes);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allAjustes.add(new AjustesDTO(
                        rs.getString("fecha_ajustes"),
                        rs.getInt("id_tipajuste"),
                        rs.getString("descripcion"),
                        rs.getString("observacion"),
                        rs.getInt("id_articulo"),
                        rs.getString("art_descripcion"),
                        rs.getInt("cantexistencia")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AjustesDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allAjustes);
    } 

    @Override
    public boolean insertarAjustes(AjustesDTO Dto) {
         try {
            sintaxiSql = null;
            conexion = new Conexion();

            sintaxiSql = "INSERT INTO ajustes(id_sucursal, id_deposito, id_usuario, id_estado, observacion, id_tipajuste)\n"
                    + "VALUES (?, ?, ?, 3, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getId_sucursal());
            preparedStatement.setObject(2, Dto.getId_deposito());
            preparedStatement.setObject(3, Dto.getId_usuario());
            preparedStatement.setObject(4, Dto.getObservacion());
            preparedStatement.setObject(5, Dto.getId_tipajuste());
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
            Logger.getLogger(AjustesDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean insertarDetallesAjustes(AjustesDTO Dto) {
       try {
            sintaxiSql = null;
            conexion = new Conexion();

            sintaxiSql = "INSERT INTO detajustes(id_articulo, id_ajuste, cantexistencia)\n"
                    + "VALUES (?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getId_articulo());
            preparedStatement.setObject(2, Dto.getId_ajuste());
            preparedStatement.setObject(3, Dto.getCantexistencia());
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
            Logger.getLogger(AjustesDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean aprobarAjustes(AjustesDTO Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();

            sintaxiSql = "UPDATE ajustes\n"
                    + "   SET id_estado=?\n"
                    + " WHERE id_ajuste=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getId_estado());
            preparedStatement.setObject(2, Dto.getId_ajuste());
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
            Logger.getLogger(AjustesDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
