/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDTO.TiposTarjetasdto;
import com.google.gson.Gson;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ModelDAO.TiposTarjetasdao;

/**
 *
 * @author user
 */
public class TiposTarjetasdaoimpl implements TiposTarjetasdao{

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public boolean insertarTiposTarjetas(TiposTarjetasdto Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO tipostarjetas( id_tipotarjeta, tarj_tipo) VALUES (?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getId_tipotarjeta());
            preparedStatement.setObject(2, Dto.getTarj_tipo());
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
            Logger.getLogger(TiposTarjetasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean modificarTiposTarjetas(TiposTarjetasdto Dto) {
         try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = " UPDATE tipostarjetas SET tarj_tipo=? WHERE id_tipotarjeta=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getTarj_tipo());
            preparedStatement.setObject(2, Dto.getId_tipotarjeta());
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
            Logger.getLogger(TiposTarjetasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean eliminarTiposTarjetas(Integer id) {
         try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM tipostarjetas WHERE id_tipotarjeta=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, id);
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("Comit() Realizado");
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TiposTarjetasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getmostrarTiposTarjetas() {
        ResultSet rs;
        ArrayList<TiposTarjetasdto> allTiposTarjetas = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_tipotarjeta, tarj_tipo FROM tipostarjetas;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allTiposTarjetas.add(new TiposTarjetasdto(
                        rs.getInt("id_tipotarjeta"),
                        rs.getString("tarj_tipo")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TiposTarjetasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allTiposTarjetas);
    }

    @Override
    public Integer getUltimoCodigoTiposTarjetas() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(id_tipotarjeta),0 )+ 1 as codigo  FROM tipostarjetas;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TiposTarjetasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public String getmostrarTiposTarjetasFiltro(Integer idFiltro) {
        ResultSet rs;
        ArrayList<TiposTarjetasdto> allTiposTarjetas = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_tipotarjeta, tarj_tipo FROM tipostarjetas WHERE id_tipotarjeta=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, idFiltro);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                allTiposTarjetas.add(new TiposTarjetasdto(
                        rs.getInt("id_tipotarjeta"),
                        rs.getString("tarj_tipo")));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TiposTarjetasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allTiposTarjetas);
    }

  

}
