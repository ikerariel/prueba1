/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDAO.ClientesDAO;
import ModelDTO.Barriosdto;
import ModelDTO.Ciudadesdto;
import ModelDTO.ClientesDTO;
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
public class ClientesDAOIMPL implements ClientesDAO {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public boolean insertarClientes(ClientesDTO Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO clientes(id_cliente, ruc, razonsocial, \n"
                    + "telefono, direccion, web, id_barrio, id_ciudad)\n"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getId_cliente());
            preparedStatement.setObject(2, Dto.getRuc());
            preparedStatement.setObject(3, Dto.getRazonsocial());
            preparedStatement.setObject(4, Dto.getTelefono());
            preparedStatement.setObject(5, Dto.getDireccion());
            preparedStatement.setObject(6, Dto.getWeb());
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
            Logger.getLogger(ClientesDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean modificarClientes(ClientesDTO Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE clientes SET ruc=?, razonsocial=?, telefono=?, \n"
                    + "direccion=?, web=?, id_barrio=?, id_ciudad=?\n"
                    + "WHERE id_cliente=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getRuc());
            preparedStatement.setObject(2, Dto.getRazonsocial());
            preparedStatement.setObject(3, Dto.getTelefono());
            preparedStatement.setObject(4, Dto.getDireccion());
            preparedStatement.setObject(5, Dto.getWeb());
            preparedStatement.setObject(6, Dto.getId_barrio());
            preparedStatement.setObject(7, Dto.getId_ciudad());
            preparedStatement.setObject(8, Dto.getId_cliente());
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
            Logger.getLogger(ClientesDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean eliminarClientes(Integer id) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM clientes WHERE id_cliente=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, id);
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("Comit() Realizado");
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getmostrarClientes() {
        ResultSet rs;
        ArrayList<ClientesDTO> allClientes = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT cl.id_cliente, cl.ruc, cl.razonsocial, cl.telefono,\n"
                    + " cl.direccion, cl.web, b.barr_descripcion, c.ciu_descripcion\n"
                    + " FROM clientes cl, barrios b, ciudades c\n"
                    + " WHERE cl.id_barrio =  b.id_barrio  and cl.id_ciudad = c.id_ciudad\n"
                    + " ORDER BY cl.id_cliente;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allClientes.add(new ClientesDTO(
                        rs.getInt("id_cliente"),
                        rs.getString("ruc"),
                        rs.getString("razonsocial"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getString("web"),
                        rs.getString("barr_descripcion"),
                        rs.getString("ciu_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allClientes);
    }

    @Override
    public Integer getUltimoCodigoClientes() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(id_cliente),0 )+ 1 as codigo  FROM clientes;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public String getmostrarClientesFiltro(Integer idFiltro) {
        ResultSet rs;
        ArrayList<ClientesDTO> allClientes = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_cliente, ruc, razonsocial, telefono, direccion, web, id_barrio, id_ciudad\n"
                    + " FROM clientes WHERE id_cliente=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, idFiltro);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                allClientes.add(new ClientesDTO(
                        rs.getInt("id_cliente"),
                        rs.getString("ruc"),
                        rs.getString("razonsocial"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getString("web"),
                        rs.getInt("id_barrio"),
                        rs.getInt("id_ciudad")));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allClientes);
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
