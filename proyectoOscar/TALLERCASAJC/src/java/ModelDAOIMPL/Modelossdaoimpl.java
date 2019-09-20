/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDTO.Coloresdto;
import ModelDTO.Marcasdto;
import ModelDTO.Modelossdto;
import com.google.gson.Gson;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ModelDAO.Modelossdao;

/**
 *
 * @author Oscar
 */
public class Modelossdaoimpl implements Modelossdao {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public boolean insertarModelos(Modelossdto Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO modelos(id_modelo, mod_descripcion, id_marca, id_color)\n"
                    + "VALUES (?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getId_modelo());
            preparedStatement.setObject(2, Dto.getMod_descripcion());
            preparedStatement.setObject(3, Dto.getId_marca());
            preparedStatement.setObject(4, Dto.getId_color());
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
            Logger.getLogger(Modelossdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean modificarModelos(Modelossdto Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE modelos SET  mod_descripcion=?, id_marca=?, id_color=?\n"
                    + " WHERE id_modelo=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getMod_descripcion());
            preparedStatement.setObject(2, Dto.getId_marca());
            preparedStatement.setObject(3, Dto.getId_color());
            preparedStatement.setObject(4, Dto.getId_modelo());
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
            Logger.getLogger(Modelossdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean eliminarModelos(Integer id) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = " DELETE FROM modelos WHERE id_modelo=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, id);
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("Comit() Realizado");
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Modelossdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getmostrarModelos() {
        ResultSet rs;
        ArrayList<Modelossdto> allModelos = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT mo.id_modelo, mo.mod_descripcion, mar.mar_descripcion, colo.colo_descripcion\n"
                    + " FROM modelos mo, marcas mar, colores colo\n"
                    + " WHERE  mo.id_marca =  mar.id_marca  and mo.id_color = colo.id_color\n"
                    + " ORDER BY mo.id_modelo;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allModelos.add(new Modelossdto(
                        rs.getInt("id_modelo"),
                        rs.getString("mod_descripcion"),
                        rs.getString("mar_descripcion"),
                        rs.getString("colo_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Modelossdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allModelos);
    }

    @Override
    public Integer getUltimoCodigoModelos() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(id_modelo),0 )+ 1 as codigo FROM modelos;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Modelossdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public String getmostrarModelosFiltro(Integer idFiltro) {
        ResultSet rs;
        ArrayList<Modelossdto> allModelos = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_modelo, mod_descripcion, id_marca, id_color FROM modelos WHERE id_modelo=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, idFiltro);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                allModelos.add(new Modelossdto(
                        rs.getInt("id_modelo"),
                        rs.getString("mod_descripcion"),
                        rs.getInt("id_marca"),
                        rs.getInt("id_color")));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Modelossdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allModelos);
    }

    @Override
    public String listarMarcas() {
        ResultSet rs;
        ArrayList<Marcasdto> allMarcas = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_marca, mar_descripcion FROM marcas;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allMarcas.add(new Marcasdto(
                        rs.getInt("id_marca"),
                        rs.getString("mar_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Marcasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allMarcas);
    }

    @Override
    public String listarColores() {
       ResultSet rs;
        ArrayList<Coloresdto> allColores = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_color, colo_descripcion FROM colores;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allColores.add(new Coloresdto(
                        rs.getInt("id_color"),
                        rs.getString("colo_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Coloresdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allColores);
    }

}
