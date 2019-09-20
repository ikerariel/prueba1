/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDAO.EntidadEmisorasdao;
import ModelDTO.EntidadEmisorasdto;
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
public class EntidadEmisorasdaoimpl implements EntidadEmisorasdao {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public boolean insertarCiudades(EntidadEmisorasdto Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO entidademisoras(id_entiemi, entiemi_descripcion)\n"
                    + "VALUES (?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getId_entiemi());
            preparedStatement.setObject(2, Dto.getEntiemi_descripcion());
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
            Logger.getLogger(EntidadEmisorasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean modificarEntidadEmisoras(EntidadEmisorasdto Dto) {
         try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE entidademisoras SET  entiemi_descripcion=?\n"
                    + "WHERE id_entiemi=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getEntiemi_descripcion());
            preparedStatement.setObject(2, Dto.getId_entiemi());
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
            Logger.getLogger(EntidadEmisorasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean eliminarEntidadEmisoras(Integer id) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM entidademisoras WHERE id_entiemi=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, id);
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("Comit() Realizado");
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EntidadEmisorasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getmostrarEntidadEmisoras() {
        ResultSet rs;
        ArrayList<EntidadEmisorasdto> allEntidadEmisoras = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = " SELECT id_entiemi, entiemi_descripcion FROM entidademisoras;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allEntidadEmisoras.add(new EntidadEmisorasdto(
                        rs.getInt("id_entiemi"),
                        rs.getString("entiemi_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EntidadEmisorasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allEntidadEmisoras);
    }

    @Override
    public Integer getUltimoCodigoEntidadEmisoras() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(id_entiemi),0 )+ 1 as codigo  FROM entidademisoras;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EntidadEmisorasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public String getmostrarEntidadEmisorasFiltro(Integer idFiltro) {
        ResultSet rs;
        ArrayList<EntidadEmisorasdto> allEntidadEmisoras = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_entiemi, entiemi_descripcion FROM entidademisoras WHERE id_entiemi=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, idFiltro);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                allEntidadEmisoras.add(new EntidadEmisorasdto(
                        rs.getInt("id_entiemi"),
                        rs.getString("entiemi_descripcion")));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EntidadEmisorasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allEntidadEmisoras);
    }


}
