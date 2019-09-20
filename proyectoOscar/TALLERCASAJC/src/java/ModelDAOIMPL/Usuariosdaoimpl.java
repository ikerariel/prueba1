/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDAO.Usuariosdao;
import ModelDTO.Perfilesdto;
import ModelDTO.Sucursalesdto;
import ModelDTO.Usuariosdto;
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
public class Usuariosdaoimpl implements Usuariosdao {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public boolean insertarUsuarios(Usuariosdto Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO usuarios(id_usuario, usu_nombre, usu_clave, id_empleado,\n"
                    + "  id_sucursal, id_perfil) VALUES (?, ?, md5(?), ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getId_usuario());
            preparedStatement.setObject(2, Dto.getUsu_nombre());
            preparedStatement.setObject(3, Dto.getUsu_clave());
            preparedStatement.setObject(4, Dto.getId_empleado());
            preparedStatement.setObject(5, Dto.getId_sucursal());
            preparedStatement.setObject(6, Dto.getId_perfil());
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
            Logger.getLogger(Usuariosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean modificarUsuarios(Usuariosdto Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE usuarios  SET usu_nombre=?, usu_clave=md5(?), id_empleado=?,"
                    + " id_sucursal=?, id_perfil=?  WHERE  id_usuario=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getUsu_nombre());
            preparedStatement.setObject(2, Dto.getUsu_clave());
            preparedStatement.setObject(3, Dto.getId_empleado());
            preparedStatement.setObject(4, Dto.getId_sucursal());
            preparedStatement.setObject(5, Dto.getId_perfil());
            preparedStatement.setObject(6, Dto.getId_usuario());
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
            Logger.getLogger(Usuariosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean eliminarUsuarios(Integer id) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM usuarios WHERE id_usuario=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, id);
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("Comit() Realizado");
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuariosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getmostrarUsuarios() {
        ResultSet rs;
        ArrayList<Usuariosdto> allUsuarios = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT u.id_usuario, u.usu_nombre, u.usu_clave, e.nombre, e.apellido, s.suc_descripcion, p.perf_descripcion\n"
                    + " FROM usuarios u, empleados e, sucursales s, perfiles p\n"
                    + " WHERE u.id_empleado = e.id_empleado AND u.id_sucursal = s.id_sucursal AND u.id_perfil = p.id_perfil\n"
                    + " ORDER BY u.id_usuario;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allUsuarios.add(new Usuariosdto(
                        rs.getInt("id_usuario"),
                        rs.getString("usu_nombre"),
                        rs.getString("usu_clave"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("suc_descripcion"),
                        rs.getString("perf_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuariosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allUsuarios);
    }

    @Override
    public Integer getUltimoCodigoUsuarios() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(id_usuario),0 )+ 1 as codigo FROM usuarios;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuariosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public String getmostrarUsuariosFiltro(Integer idFiltro) {
        ResultSet rs;
        ArrayList<Usuariosdto> allUsuarios = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = " SELECT id_usuario, usu_nombre, usu_clave, id_empleado, id_sucursal,"
                    + " id_perfil FROM usuarios WHERE id_usuario=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, idFiltro);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                allUsuarios.add(new Usuariosdto(
                        rs.getInt("id_usuario"),
                        rs.getString("usu_nombre"),
                        rs.getString("usu_clave"),
                        rs.getInt("id_empleado"),
                        rs.getInt("id_sucursal"),
                        rs.getInt("id_perfil")));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuariosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allUsuarios);
    }

    @Override
    public String listarEmpleados() {
        ResultSet rs;
        ArrayList<Usuariosdto> allEmpleados = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_empleado, nombre, apellido FROM empleados;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allEmpleados.add(new Usuariosdto(
                        rs.getInt("id_empleado"),
                        rs.getString("nombre"),
                        rs.getString("apellido")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuariosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allEmpleados);
    }

    @Override
    public String listarSucursales() {
        ResultSet rs;
        ArrayList<Sucursalesdto> allSucursales = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_sucursal, suc_descripcion, id_ciudad, id_barrio FROM sucursales;";
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

    @Override
    public String listarPerfiles() {
        ResultSet rs;
        ArrayList<Perfilesdto> allPerfiles = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_perfil, perf_descripcion FROM perfiles;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allPerfiles.add(new Perfilesdto(
                        rs.getInt("id_perfil"),
                        rs.getString("perf_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Perfilesdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allPerfiles);
    }

}
