/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDAO.Articulosdao;
import ModelDTO.Articulosdto;
import ModelDTO.Impuestosdto;
import ModelDTO.Marcasdto;
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
public class Articulosdaoimpl implements Articulosdao {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public boolean insertarArticulos(Articulosdto Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();

            sintaxiSql = "INSERT INTO articulos( id_articulo, art_descripcion,"
                    + " preccompras, precventas, id_impuesto, id_marca, codigenerico)"
                    + " VALUES (?, ?, ?, ?, ?, ?,?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getId_articulo());
            preparedStatement.setObject(2, Dto.getArt_descripcion());
            preparedStatement.setObject(3, Dto.getPreccompras());
            preparedStatement.setObject(4, Dto.getPrecventas());
            preparedStatement.setObject(5, Dto.getId_impuesto());
            preparedStatement.setObject(6, Dto.getId_marca());
            preparedStatement.setObject(7, Dto.getCodigenerico());
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
            Logger.getLogger(Articulosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean modificarArticulos(Articulosdto Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE articulos SET  art_descripcion=?, preccompras=?,"
                    + " precventas=?, id_impuesto=?, id_marca=?, codigenerico=?"
                    + " WHERE id_articulo=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getArt_descripcion());
            preparedStatement.setObject(2, Dto.getPreccompras());
            preparedStatement.setObject(3, Dto.getPrecventas());
            preparedStatement.setObject(4, Dto.getId_impuesto());
            preparedStatement.setObject(5, Dto.getId_marca());
            preparedStatement.setObject(6, Dto.getCodigenerico());
            preparedStatement.setObject(7, Dto.getId_articulo());

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
            Logger.getLogger(Articulosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean eliminarArticulos(Integer id) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM articulos WHERE id_articulo=?;";
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
            Logger.getLogger(Articulosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getmostrarArticulos() {
        ResultSet rs;
        ArrayList<Articulosdto> allArticulos = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT ar.id_articulo, ar.art_descripcion, ar.preccompras, ar.precventas,\n"
                    + "                     im.imp_descripcion, ma.mar_descripcion, ar.codigenerico\n"
                    + "                    FROM articulos ar\n"
                    + "		LEFT JOIN marcas ma on ar.id_marca=ma.id_marca\n"
                    + "		 LEFT JOIN impuestos im on ar.id_impuesto=im.id_impuesto\n"
                    + "                                   ORDER BY ar.id_articulo";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allArticulos.add(new Articulosdto(
                        rs.getInt("id_articulo"),
                        rs.getString("art_descripcion"),
                        rs.getInt("preccompras"),
                        rs.getInt("precventas"),
                        rs.getString("imp_descripcion"),
                        rs.getString("mar_descripcion"),
                        rs.getString("codigenerico")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Articulosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allArticulos);
    }

    @Override
    public Integer getUltimoCodigoArticulos() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(id_articulo),0 )+ 1 as codigo  FROM articulos;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Articulosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public String getmostrarArticulosFiltro(Integer idFiltro) {
        ResultSet rs;
        ArrayList<Articulosdto> allArticulos = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_articulo, art_descripcion, preccompras, precventas, id_impuesto,  id_marca, codigenerico\n"
                    + "FROM articulos WHERE id_articulo=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, idFiltro);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                allArticulos.add(new Articulosdto(
                        rs.getInt("id_articulo"),
                        rs.getString("art_descripcion"),
                        rs.getInt("preccompras"),
                        rs.getInt("precventas"),
                        rs.getInt("id_impuesto"),
                        rs.getInt("id_marca"),
                        rs.getString("codigenerico")));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Articulosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allArticulos);
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
    public String listarImpuestos() {
        ResultSet rs;
        ArrayList<Impuestosdto> allImpuestos = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_impuesto, imp_descripcion FROM impuestos;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allImpuestos.add(new Impuestosdto(
                        rs.getInt("id_impuesto"),
                        rs.getString("imp_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Impuestosdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allImpuestos);
    }

}
