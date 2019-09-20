/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDAO.Depositosdao;
import ModelDTO.Depositosdto;
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
public class Depositosdaoimpl implements Depositosdao {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public boolean insertarDepositos(Depositosdto Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO depositos(id_deposito, dep_descripcion, id_sucursal)\n"
                    + "VALUES (?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getId_deposito());
            preparedStatement.setObject(2, Dto.getDep_descripcion());
            preparedStatement.setObject(3, Dto.getId_sucursal());
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
            Logger.getLogger(Depositosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean modificarDepositos(Depositosdto Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE depositos SET  dep_descripcion=?, id_sucursal=?\n"
                    + "WHERE id_deposito=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getDep_descripcion());
            preparedStatement.setObject(2, Dto.getId_sucursal());
            preparedStatement.setObject(3, Dto.getId_deposito());
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
            Logger.getLogger(Depositosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean eliminarDepositos(Integer id) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM depositos WHERE id_deposito=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, id);
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("Comit() Realizado");
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Depositosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getmostrarDepositos() {
        ResultSet rs;
        ArrayList<Depositosdto> allDepositos = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT d.id_deposito, d.dep_descripcion, s.suc_descripcion \n"
                    + " FROM depositos d, sucursales s \n"
                    + " WHERE d.id_sucursal = s.id_sucursal \n"
                    + " ORDER BY d.id_deposito;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allDepositos.add(new Depositosdto(
                        rs.getInt("id_deposito"),
                        rs.getString("dep_descripcion"),
                        rs.getString("suc_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Depositosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allDepositos);
    }

    @Override
    public Integer getUltimoCodigoDepositos() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(id_deposito),0 )+ 1 as codigo  FROM depositos;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Depositosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public String getmostrarDepositosFiltro(Integer idFiltro) {
        ResultSet rs;
        ArrayList<Depositosdto> allDepositos = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_deposito, dep_descripcion, id_sucursal FROM depositos WHERE id_deposito=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, idFiltro);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                allDepositos.add(new Depositosdto(
                        rs.getInt("id_deposito"),
                        rs.getString("dep_descripcion"),
                        rs.getInt("id_sucursal")));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Depositosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allDepositos);
    }

    @Override
    public String nuevoListarSucursales() {
        ResultSet rs;
        ArrayList<Sucursalesdto> allSucursales = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_sucursal, suc_descripcion, id_ciudad,\n"
                    + "id_barrio FROM sucursales;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allSucursales.add(new Sucursalesdto(
                        rs.getInt("id_sucursal"),
                        rs.getString("suc_descripcion"),
                        rs.getInt("id_ciudad"),
                        rs.getInt("id_barrio")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sucursalesdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allSucursales);
    }

}
