/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDAO.Proveedoresdao;
import ModelDTO.Ciudadesdto;
import ModelDTO.Proveedoresdto;
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
public class Proveedoresdaoimpl implements Proveedoresdao {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public boolean insertarProveedores(Proveedoresdto Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO proveedores(id_proveedor, ras_social, \n"
                    + "direccion, pag_web, telefono, ruc, id_ciudad)\n"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getId_proveedor());
            preparedStatement.setObject(2, Dto.getRas_social());
            preparedStatement.setObject(3, Dto.getDireccion());
            preparedStatement.setObject(4, Dto.getPag_web());
            preparedStatement.setObject(5, Dto.getTelefono());
            preparedStatement.setObject(6, Dto.getRuc());
            preparedStatement.setObject(7, Dto.getId_ciudad());
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
            Logger.getLogger(Proveedoresdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean modificarProveedores(Proveedoresdto Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE proveedores SET  ras_social=?, direccion=?, \n"
                    + "pag_web=?, telefono=?, ruc=?, id_ciudad=?\n"
                    + "WHERE id_proveedor=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getRas_social());
            preparedStatement.setObject(2, Dto.getDireccion());
            preparedStatement.setObject(3, Dto.getPag_web());
            preparedStatement.setObject(4, Dto.getTelefono());
            preparedStatement.setObject(5, Dto.getRuc());
            preparedStatement.setObject(6, Dto.getId_ciudad());
            preparedStatement.setObject(7, Dto.getId_proveedor());
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
            Logger.getLogger(Proveedoresdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean eliminarProveedores(Integer id) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = " DELETE FROM proveedores WHERE id_proveedor=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, id);
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
            Logger.getLogger(Proveedoresdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getmostrarProveedores() {
        ResultSet rs;
        ArrayList<Proveedoresdto> allProveedores = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT p.id_proveedor, p.ras_social, p.direccion, p.pag_web, \n"
                    + "		p.telefono, p.ruc, c.ciu_descripcion\n"
                    + "		FROM proveedores p, ciudades c\n"
                    + "		WHERE p.id_ciudad = c.id_ciudad\n"
                    + "		ORDER BY p.id_proveedor;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allProveedores.add(new Proveedoresdto(
                        rs.getInt("id_proveedor"),
                        rs.getString("ras_social"),
                        rs.getString("direccion"),
                        rs.getString("pag_web"),
                        rs.getString("telefono"),
                        rs.getString("ruc"),
                        rs.getString("ciu_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Proveedoresdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allProveedores);
    }

    @Override
    public Integer getUltimoCodigoProveedores() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(id_proveedor),0 )+ 1 as codigo  FROM proveedores;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Proveedoresdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public String getmostrarProveedoresFiltro(Integer idFiltro) {
        ResultSet rs;
        ArrayList<Proveedoresdto> allProveedores = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_proveedor, ras_social, direccion, pag_web, telefono, ruc, id_ciudad\n"
                    + "FROM proveedores WHERE id_proveedor=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, idFiltro);

            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                allProveedores.add(new Proveedoresdto(
                        rs.getInt("id_proveedor"),
                        rs.getString("ras_social"),
                        rs.getString("direccion"),
                        rs.getString("pag_web"),
                        rs.getString("telefono"),
                        rs.getString("ruc"),
                        rs.getInt("id_ciudad")));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Proveedoresdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allProveedores);
    }

    @Override
    public String listarCiudades() {
        ResultSet rs;
        ArrayList<Ciudadesdto> allCiudades = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_ciudad, ciu_descripcion FROM ciudades;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allCiudades.add(new Ciudadesdto(
                        rs.getInt("id_ciudad"),
                        rs.getString("ciu_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ciudadesdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allCiudades);
    }

}
