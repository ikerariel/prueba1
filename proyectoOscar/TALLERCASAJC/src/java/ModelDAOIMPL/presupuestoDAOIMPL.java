/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import ModelDAO.presupuestoDAO;
import ModelDTO.presupuestoDTO;
import Genericos.Conexion;
import com.google.gson.Gson;
import static java.lang.System.out;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class presupuestoDAOIMPL implements presupuestoDAO {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public boolean insertarpresupuesto(presupuestoDTO Dto, Integer v_caso) {
        switch (v_caso) {
            case 1:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = " INSERT INTO presupuestocompra(id_estado,id_usuario,id_proveedor,id_deposito,idtipomoneda)\n"
                            + "    VALUES (3,?,?,?,?);";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, Dto.getId_usuario());
                    preparedStatement.setObject(2, Dto.getId_proveedor());
                    preparedStatement.setObject(3, Dto.getId_deposito());
                    preparedStatement.setObject(4, Dto.getIdtipomoneda());
                    filasAfectadas = preparedStatement.executeUpdate();
                    if (filasAfectadas > 0) {
                        conexion.comit();
                        return true;
                    } else {
                        conexion.rollback();
                        System.out.println("Rollback() Realizado");
                        return false;
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(presupuestoDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);

                }

                break;
            case 2:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "UPDATE presupuestocompra\n"
                            + "   SET  fecha=now(), id_estado=3, id_usuario=?, id_proveedor=?, \n"
                            + "       id_deposito=?, idtipomoneda=?\n"
                            + " WHERE id_presucompra=?";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, Dto.getId_usuario());
                    preparedStatement.setObject(2, Dto.getId_proveedor());
                    preparedStatement.setObject(3, Dto.getId_deposito());
                    preparedStatement.setObject(4, Dto.getIdtipomoneda());
                    preparedStatement.setObject(5, Dto.getId_presucompra());
                    filasAfectadas = preparedStatement.executeUpdate();
                    if (filasAfectadas > 0) {
                        conexion.comit();
                        return true;
                    } else {
                        conexion.rollback();
                        System.out.println("Rollback() Realizado");
                        return false;
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(presupuestoDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);

                }

                break;
        }
        return false;
    }

    @Override
    public String getproveedor() {
        ResultSet rs;
        ArrayList<presupuestoDTO> allproveedor = new ArrayList<>();

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_proveedor, ras_social, direccion, pag_web, telefono, ruc, \n"
                    + "       id_ciudad\n"
                    + "  FROM proveedores;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allproveedor.add(new presupuestoDTO(rs.getInt("id_proveedor"),
                        rs.getString("ras_social")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(presupuestoDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allproveedor);
    }

    @Override
    public String gettipomoneda() {
        ResultSet rs;
        ArrayList<presupuestoDTO> allmoneda = new ArrayList<>();

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idtipomoneda, descripcion\n"
                    + "  FROM tipomoneda;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allmoneda.add(new presupuestoDTO(rs.getInt("idtipomoneda"),
                        rs.getString("descripcion")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(presupuestoDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allmoneda);
    }

    @Override
    public String getpresupuesto() {
        ResultSet rs;
        ArrayList<presupuestoDTO> allpresupuesto = new ArrayList<>();

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT p.id_presucompra, p.fecha::date, (e.est_descripcion)as estado,\n"
                    + " (u.usu_nombre)as usuario, (v.ras_social) as preveedor\n"
                    + "  FROM presupuestocompra p\n"
                    + "  INNER JOIN proveedores v on p.id_proveedor=v.id_proveedor\n"
                    + "  INNER JOIN estados e on p.id_estado=e.id_estado\n"
                    + "  INNER JOIN usuarios u on p.id_usuario = u.id_usuario   where p.id_estado in(1,3)\n"
                    + "  order by p.id_presucompra desc";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allpresupuesto.add(new presupuestoDTO(rs.getInt("id_presucompra"),
                        rs.getString("fecha"),
                        rs.getString("estado"),
                        rs.getString("preveedor"),
                        rs.getString("usuario")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(presupuestoDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allpresupuesto);
    }

    @Override
    public boolean insertardetallepresupuesto(presupuestoDTO Dto) {

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO detpresuompras(id_articulo,cantidad,preciounitario,id_presucompra) \n"
                    + "             VALUES (?,?,?,\n"
                    + "             ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getId_articulo());
            preparedStatement.setObject(2, Dto.getCantidad());
            preparedStatement.setObject(3, Dto.getPreciounitario());
            preparedStatement.setObject(4, Dto.getId_presucompra());
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {

                conexion.comit();
                System.out.println("DETALLE GAURDARO");
                return true;

            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(presupuestoDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);

        }

        return false;
    }

    @Override
    public String getdetallepresupuesto(Integer codpresupuesto) {
        ResultSet rs;
        ArrayList<presupuestoDTO> alldetallep = new ArrayList<>();

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT p.id_presucompra,pc.id_pedidocompra, p.fecha::date, p.id_estado, p.id_usuario,\n" +
"                     p.id_proveedor, (v.ras_social) as proveedor, p.id_deposito,\n" +
"                     (d.dep_descripcion)as deposito,\n" +
"                           p.idtipomoneda, (m.descripcion) as moneda,dp.id_articulo,\n" +
"                    (a.art_descripcion) as articulo, dp.cantidad, dp.preciounitario,dp.iddetpresuompras  \n" +
"                     FROM presupuestocompra p\n" +
"                     inner join pedidoscompras pc on p.id_pedidocompra=pc.id_pedidocompra\n" +
"                    inner join proveedores v on p.id_proveedor=v.id_proveedor\n" +
"                    inner join depositos d on p.id_deposito=d.id_deposito\n" +
"                    inner join tipomoneda m on p.idtipomoneda=m.idtipomoneda\n" +
"                    inner join detpresuompras dp on p.id_presucompra= dp.id_presucompra\n" +
"                    inner join articulos a on dp.id_articulo= a.id_articulo\n" +
"                    where p.id_presucompra=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, codpresupuesto);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alldetallep.add(new presupuestoDTO(rs.getInt("id_presucompra"),
                        rs.getInt("id_pedidocompra"),
                        rs.getString("fecha"),
                        rs.getString("proveedor"),
                        rs.getInt("id_deposito"),
                        rs.getString("deposito"),
                        rs.getString("moneda"),
                        rs.getInt("id_articulo"),
                        rs.getString("articulo"),
                        rs.getInt("preciounitario"),
                        rs.getInt("iddetpresuompras"),
                        rs.getInt("cantidad")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(presupuestoDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alldetallep);
    }

    @Override
    public boolean deletedealle(presupuestoDTO Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM detpresuompras\n"
                    + " WHERE id_presucompra=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getId_presucompra());
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("DETALLE GAURDARO");
                return true;

            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(presupuestoDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);

        }

        return false;
    }

    @Override
    public boolean actualizarestado(presupuestoDTO Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE public.presupuestocompra\n"
                    + "   SET  id_estado=?\n"
                    + " WHERE id_presucompra=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getId_estado());
            preparedStatement.setObject(2, Dto.getId_presucompra());
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {

                conexion.comit();
                System.out.println("DETALLE GAURDARO");
                return true;

            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(presupuestoDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);

        }

        return false;
    }

}
