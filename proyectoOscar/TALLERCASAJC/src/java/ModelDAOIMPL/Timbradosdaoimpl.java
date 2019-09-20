/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDAO.Timbradosdao;
import ModelDTO.Estadosdto;
import ModelDTO.Timbradosdto;
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
public class Timbradosdaoimpl implements Timbradosdao {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public boolean insertarTimbrados(Timbradosdto Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO timbrados(id_timbrado, numero, inicio_fecha, final_fecha, vencimientos, id_estado)\n"
                    + " VALUES (?, ?, ?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getId_timbrado());
            preparedStatement.setObject(2, Dto.getNumero());
            preparedStatement.setObject(3, Dto.getInicio_fecha());
            preparedStatement.setObject(4, Dto.getFinal_fecha());
            preparedStatement.setObject(5, Dto.getVencimientos());
            preparedStatement.setObject(6, Dto.getId_estado());
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
            Logger.getLogger(Timbradosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean modificarTimbrados(Timbradosdto Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE timbrados SET  numero=?, inicio_fecha=?, final_fecha=?, vencimientos=?, id_estado=?\n"
                    + " WHERE id_timbrado=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);

            preparedStatement.setObject(1, Dto.getNumero());
            preparedStatement.setObject(2, Dto.getInicio_fecha());
            preparedStatement.setObject(3, Dto.getFinal_fecha());
            preparedStatement.setObject(4, Dto.getVencimientos());
            preparedStatement.setObject(5, Dto.getId_estado());
            preparedStatement.setObject(6, Dto.getId_timbrado());
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
            Logger.getLogger(Timbradosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean eliminarTimbrados(Integer id) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM timbrados WHERE id_timbrado=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, id);
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("Comit() Realizado");
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Timbradosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getmostrarTimbrados() {
        ResultSet rs;
        ArrayList<Timbradosdto> allTimbrados = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT tim.id_timbrado, tim.numero, tim.inicio_fecha, tim.final_fecha, tim.vencimientos, esta.est_descripcion\n"
                    + " FROM timbrados tim, estados esta\n"
                    + " WHERE  tim.id_estado =  esta.id_estado\n"
                    + " ORDER BY tim.id_timbrado;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allTimbrados.add(new Timbradosdto(
                        rs.getInt("id_timbrado"),
                        rs.getInt("numero"),
                        rs.getString("inicio_fecha"),
                        rs.getString("final_fecha"),
                        rs.getString("vencimientos"),
                        rs.getInt("est_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Timbradosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allTimbrados);
    }

    @Override
    public Integer getUltimoCodigoTimbrados() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(id_timbrado),0 )+ 1 as codigo FROM timbrados;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Timbradosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public String getmostrarTimbradosFiltro(Integer idFiltro) {
        ResultSet rs;
        ArrayList<Timbradosdto> allTimbrados = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_timbrado, numero, inicio_fecha, final_fecha,\n"
                    + " vencimientos, id_estado FROM timbrados WHERE id_timbrado=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, idFiltro);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                allTimbrados.add(new Timbradosdto(
                        rs.getInt("id_timbrado"),
                        rs.getInt("numero"),
                        rs.getString("inicio_fecha"),
                        rs.getString("final_fecha"),
                        rs.getString("vencimientos"),
                        rs.getInt("id_estado")));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Timbradosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allTimbrados);
    }

    @Override
    public String listarEstados() {
        ResultSet rs;
        ArrayList<Estadosdto> allEstados = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_estado, est_descripcion FROM estados;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allEstados.add(new Estadosdto(
                        rs.getInt("id_estado"),
                        rs.getString("est_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Estadosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allEstados);
    }

}
