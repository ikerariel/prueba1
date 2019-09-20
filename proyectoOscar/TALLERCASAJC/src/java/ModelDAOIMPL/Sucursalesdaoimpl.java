/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDAO.Sucursalesdao;
import ModelDTO.Barriosdto;
import ModelDTO.Ciudadesdto;
import ModelDTO.Sucursalesdto;
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
public class Sucursalesdaoimpl implements Sucursalesdao {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public boolean insertarSucursales(Sucursalesdto Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO sucursales(id_sucursal, suc_descripcion, id_ciudad, id_barrio)\n"
                    + "VALUES (?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getId_sucursal());
            preparedStatement.setObject(2, Dto.getSuc_descripcion());
            preparedStatement.setObject(3, Dto.getId_ciudad());
            preparedStatement.setObject(4, Dto.getId_barrio());
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
            Logger.getLogger(Sucursalesdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean modificarSucursales(Sucursalesdto Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE sucursales  SET  suc_descripcion=?, id_ciudad=?, id_barrio=?\n"
                    + "WHERE id_sucursal=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getSuc_descripcion());
            preparedStatement.setObject(2, Dto.getId_ciudad());
            preparedStatement.setObject(3, Dto.getId_barrio());
            preparedStatement.setObject(4, Dto.getId_sucursal());
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
            Logger.getLogger(Sucursalesdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean eliminarSucursales(Integer id) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM sucursales WHERE id_sucursal=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, id);
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("Comit() Realizado");
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sucursalesdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getmostrarSucursales() {
        ResultSet rs;
        ArrayList<Sucursalesdto> allSucursales = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT su.id_sucursal, su.suc_descripcion, c.ciu_descripcion, b.barr_descripcion \n"
                    + " FROM sucursales su, ciudades c, barrios b \n"
                    + " WHERE su.id_ciudad = c.id_ciudad AND su.id_barrio = b.id_barrio \n"
                    + " ORDER BY su.id_sucursal;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allSucursales.add(new Sucursalesdto(
                        rs.getInt("id_sucursal"),
                        rs.getString("suc_descripcion"),
                        rs.getString("ciu_descripcion"),
                        rs.getString("barr_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sucursalesdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allSucursales);
    }

    @Override
    public Integer getUltimoCodigoSucursales() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(id_sucursal),0 )+ 1 as codigo  FROM sucursales;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sucursalesdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public String getmostrarSucursalesFiltro(Integer idFiltro) {
        ResultSet rs;
        ArrayList<Sucursalesdto> allSucursales = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_sucursal, suc_descripcion, id_ciudad, id_barrio "
                    + "FROM sucursales WHERE id_sucursal=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, idFiltro);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                allSucursales.add(new Sucursalesdto(
                        rs.getInt("id_sucursal"),
                        rs.getString("suc_descripcion"),
                        rs.getInt("id_ciudad"),
                        rs.getInt("id_barrio")));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sucursalesdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allSucursales);
    }

    @Override
    public String listarCiudades() {
        ResultSet rs;
        ArrayList<Ciudadesdto> allCiudad = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_ciudad, ciu_descripcion FROM ciudades;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allCiudad.add(new Ciudadesdto(
                        rs.getInt("id_ciudad"),
                        rs.getString("ciu_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ciudadesdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allCiudad);
    }

    @Override
    public String listarBarrios() {
        ResultSet rs;
        ArrayList<Barriosdto> allBarrios = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_barrio, barr_descripcion, id_ciudad FROM barrios;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allBarrios.add(new Barriosdto(
                        rs.getInt("id_barrio"),
                        rs.getString("barr_descripcion"),
                        rs.getInt("id_ciudad")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Barriosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allBarrios);
    }

}
