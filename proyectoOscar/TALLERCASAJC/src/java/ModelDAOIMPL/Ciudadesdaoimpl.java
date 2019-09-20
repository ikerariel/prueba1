/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDAO.Ciudadesdao;
import ModelDTO.Ciudadesdto;
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
public class Ciudadesdaoimpl implements Ciudadesdao {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public boolean insertarCiudades(Ciudadesdto Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO ciudades(id_ciudad, ciu_descripcion)\n"
                       + " VALUES (?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getId_ciudad());
            preparedStatement.setObject(2, Dto.getCiu_descripcion());
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
            Logger.getLogger(Ciudadesdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean modificarCiudades(Ciudadesdto Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE ciudades SET ciu_descripcion=? WHERE id_ciudad=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getCiu_descripcion());
            preparedStatement.setObject(2, Dto.getId_ciudad());
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
            Logger.getLogger(Ciudadesdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean eliminarCiudades(Integer id) {
         try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM ciudades WHERE id_ciudad=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, id);
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("Comit() Realizado");
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ciudadesdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public String getmostrarCiudades() {
        ResultSet rs;
        ArrayList<Ciudadesdto> allCaiudades = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_ciudad, ciu_descripcion FROM ciudades;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allCaiudades.add(new Ciudadesdto(
                        rs.getInt("id_ciudad"),
                        rs.getString("ciu_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ciudadesdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allCaiudades);
    }

    @Override
    public Integer getUltimoCodigoCiudades() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(id_ciudad),0 )+ 1 as codigo  FROM ciudades;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ciudadesdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public String getmostrarCiudadesFiltro(Integer idFiltro) {
        ResultSet rs;
        ArrayList<Ciudadesdto> allCaiudades = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_ciudad, ciu_descripcion FROM ciudades WHERE id_ciudad=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, idFiltro);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                allCaiudades.add(new Ciudadesdto(rs.getInt("id_ciudad"),
                                         rs.getString("ciu_descripcion")));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ciudadesdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       return new Gson().toJson(allCaiudades);     
    }

}
