/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDAO.Empleadosdao;
import ModelDTO.Barriosdto;
import ModelDTO.Ciudadesdto;
import ModelDTO.Empleadosdto;
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
public class Empleadosdaoimpl implements Empleadosdao {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public boolean insertarEmpleados(Empleadosdto Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO empleados( id_empleado, nombre, apellido,\n"
                    + "ci, tel, direccion, id_barrio, id_ciudad)\n"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getId_empleado());
            preparedStatement.setObject(2, Dto.getNombre());
            preparedStatement.setObject(3, Dto.getApellido());
            preparedStatement.setObject(4, Dto.getCi());
            preparedStatement.setObject(5, Dto.getTel());
            preparedStatement.setObject(6, Dto.getDireccion());
            preparedStatement.setObject(7, Dto.getId_barrio());
            preparedStatement.setObject(8, Dto.getId_ciudad());
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
            Logger.getLogger(Empleadosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean modificarEmpleados(Empleadosdto Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE empleados SET nombre=?, apellido=?,\n"
                    + "ci=?, tel=?, direccion=?, id_barrio=?, id_ciudad=?\n"
                    + "WHERE id_empleado=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getNombre());
            preparedStatement.setObject(2, Dto.getApellido());
            preparedStatement.setObject(3, Dto.getCi());
            preparedStatement.setObject(4, Dto.getTel());
            preparedStatement.setObject(5, Dto.getDireccion());
            preparedStatement.setObject(6, Dto.getId_barrio());
            preparedStatement.setObject(7, Dto.getId_ciudad());
            preparedStatement.setObject(8, Dto.getId_empleado());
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
            Logger.getLogger(Empleadosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean eliminarEmpleados(Integer id) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM empleados WHERE id_empleado=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, id);
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("Comit() Realizado");
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Empleadosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getmostrarEmpleados() {
        ResultSet rs;
        ArrayList<Empleadosdto> allEmpleados = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT e.id_empleado, e.nombre, e.apellido, e.ci, e.tel,\n"
                    + "  e.direccion, b.barr_descripcion, c.ciu_descripcion \n"
                    + "  FROM empleados e, barrios b, ciudades c\n"
                    + "  WHERE  e.id_barrio =  b.id_barrio  and e.id_ciudad = c.id_ciudad\n"
                    + "  ORDER BY e.id_empleado;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allEmpleados.add(new Empleadosdto(
                        rs.getInt("id_empleado"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("ci"),
                        rs.getInt("tel"),
                        rs.getString("direccion"),
                        rs.getString("barr_descripcion"),
                        rs.getString("ciu_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Empleadosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allEmpleados);
    }

    @Override
    public Integer getUltimoCodigoEmpleados() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(id_empleado),0 )+ 1 as codigo  FROM empleados;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Empleadosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public String getmostrarEmpleadosFiltro(Integer idFiltro) {
        ResultSet rs;
        ArrayList<Empleadosdto> allEmpleados = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_empleado, nombre, apellido, ci, tel, \n"
                    + "direccion, id_barrio, id_ciudad FROM empleados WHERE id_empleado=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, idFiltro);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                allEmpleados.add(new Empleadosdto(
                        rs.getInt("id_empleado"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("ci"),
                        rs.getInt("tel"),
                        rs.getString("direccion"),
                        rs.getInt("id_barrio"),
                        rs.getInt("id_ciudad")));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Empleadosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allEmpleados);
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

}
