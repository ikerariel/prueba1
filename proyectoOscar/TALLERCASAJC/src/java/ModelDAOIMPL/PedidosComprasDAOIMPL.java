/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDAO.PedidosComprasDAO;
import ModelDTO.Estadosdto;
import ModelDTO.PedidosComprasDTO;
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
 * @author Oscar
 */
public class PedidosComprasDAOIMPL implements PedidosComprasDAO {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public Integer getUltimoCodigo() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(id_pedidocompra),0 )+ 1 as codigo  FROM pedidoscompras;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidosComprasDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public boolean insertar(PedidosComprasDTO dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO pedidoscompras(id_usuario, id_estado,"
                    + " observacion) VALUES (?, 3, upper(?));";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getId_usuario());
            preparedStatement.setObject(2, dto.getObservacion());
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
            Logger.getLogger(PedidosComprasDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean insertarDetalles(PedidosComprasDTO dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO detpedidoscompras(\n"
                    + "id_pedidocompra, id_articulo, cantidad)\n"
                    + "VALUES (?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getId_pedidocompra());
            preparedStatement.setObject(2, dto.getId_articulo());
            preparedStatement.setObject(3, dto.getCantidad());
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
            Logger.getLogger(PedidosComprasDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getcabeseraFiltro(Integer idFiltro) {
        ResultSet rs;
        ArrayList<PedidosComprasDTO> allPedidosComprasC = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT p.id_pedidocompra, p.pcompra_fecha, u.usu_nombre, e.est_descripcion, p.observacion\n"
                    + " FROM pedidoscompras p, usuarios u, estados e\n"
                    + " WHERE p.id_usuario=u.id_usuario AND p.id_estado=e.id_estado\n"
                    + " ORDER BY p.id_pedidocompra;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, idFiltro);
            rs = preparedStatement.executeQuery();
//            if (rs.next()) {
//                allPedidosComprasC.add(new PedidosComprasdto(
//                        rs.getInt("id_pedidocompraid_pedidocompra"),
//                        rs.getString("fecha"),
//                        rs.getString("id_estado"),
//                        rs.getString("observacion"),
//                        rs.getString("usu_nombre"),
//                        rs.getString("est_descripcion")));
//            } else {
//                return null;
//            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidosComprasDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allPedidosComprasC);
    }

    @Override
    public String getdetalleFiltro(Integer idFiltro) {
        ResultSet rs;
        ArrayList<PedidosComprasDTO> allPedidosComprasD = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_pedidocompra, id_articulo, cantidad, precio "
                    + " FROM detpedidoscompras WHERE id_pedidocompra=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, idFiltro);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                allPedidosComprasD.add(new PedidosComprasDTO(
                        rs.getInt("id_pedidocompra"),
                        rs.getInt("id_articulo"),
                        rs.getInt("cantidad"),
                        rs.getInt("precio")));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidosComprasDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allPedidosComprasD);
    }

    @Override
    public boolean modificar(PedidosComprasDTO dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE pedidoscompras SET  id_usuario=?"
                    + ", id_estado=?, observacion=? WHERE  id_pedidocompra=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);

            preparedStatement.setObject(1, dto.getId_usuario());
            preparedStatement.setObject(2, dto.getId_estado());
            preparedStatement.setObject(3, dto.getObservacion());
            preparedStatement.setObject(4, dto.getId_pedidocompra());
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
            Logger.getLogger(PedidosComprasDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean confirmar(PedidosComprasDTO dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE pedidoscompras SET id_estado=? WHERE id_pedidocompra=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getId_estado());
            preparedStatement.setObject(2, dto.getId_pedidocompra());

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
            Logger.getLogger(PedidosComprasDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean modificarDetalles(PedidosComprasDTO dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE detpedidoscompras SET id_articulo=?, cantidad=?,"
                    + " precio=? WHERE id_pedidocompra=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getId_articulo());
            preparedStatement.setObject(2, dto.getCantidad());
            preparedStatement.setObject(3, dto.getPrecio());
            preparedStatement.setObject(4, dto.getId_pedidocompra());
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
            Logger.getLogger(PedidosComprasDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String listarUsuarios() {
        ResultSet rs;
        ArrayList<Usuariosdto> allUsuarios = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_usuario, usu_nombre, usu_clave, id_empleado,\n"
                    + " id_sucursal, id_perfil FROM usuarios where id_sucursal=1";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allUsuarios.add(new Usuariosdto(
                        rs.getInt("id_usuario"),
                        rs.getString("usu_nombre"),
                        rs.getString("usu_clave"),
                        rs.getInt("id_empleado"),
                        rs.getInt("id_sucursal"),
                        rs.getInt("id_perfil")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidosComprasDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allUsuarios);
    }

    @Override
    public String listarArticulos() {
        ResultSet rs;
        ArrayList<PedidosComprasDTO> allArtico = new ArrayList<>();
        try {

            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "Select *from articulos";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allArtico.add(new PedidosComprasDTO(
                        rs.getInt("id_articulo"),
                        rs.getString("art_descripcion"),
                        rs.getInt("preccompras"),
                        rs.getInt("precventas"),
                        rs.getInt("id_impuesto"),
                        rs.getInt("id_marca"),
                        rs.getString("codigenerico")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidosComprasDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allArtico);
    }

    @Override
    public String listarEstados() {
        ResultSet rs;
        ArrayList<Estadosdto> allEstados = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_estado, est_descripcion\n"
                    + "  FROM estados WHERE id_estado=1;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allEstados.add(new Estadosdto(
                        rs.getInt("id_estado"),
                        rs.getString("est_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidosComprasDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allEstados);
    }

    @Override
    public String listarPedidosCompras() {
        ResultSet rs;
        ArrayList<PedidosComprasDTO> allPedidosCompras = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT p.id_pedidocompra, p.pcompra_fecha::date, u.usu_nombre, e.est_descripcion, p.observacion\n" +
"                     FROM pedidoscompras p\n" +
"                    INNER JOIN usuarios u on p.id_usuario=u.id_usuario\n" +
"                     INNER JOIN estados e on p.id_estado=e.id_estado\n" +
"		 WHERE p.id_estado in(1,3) ORDER BY p.id_pedidocompra desc";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allPedidosCompras.add(new PedidosComprasDTO(
                        rs.getInt("id_pedidocompra"),
                        rs.getString("pcompra_fecha"),
                        rs.getString("usu_nombre"),
                        rs.getString("est_descripcion"),
                        rs.getString("observacion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidosComprasDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allPedidosCompras);
    }

    @Override
    public String listarDetallesCompras(Integer id) {
        ResultSet rs;
        ArrayList<PedidosComprasDTO> allDetallesPediCompras = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT p.pcompra_fecha, p.id_estado,u.usu_nombre, e.est_descripcion, p.observacion,\n"
                    + " d.id_articulo,d.cantidad,d.precio, a.codigenerico, a.art_descripcion\n"
                    + " from pedidoscompras p\n"
                    + " inner join detpedidoscompras d on p.id_pedidocompra=d.id_pedidocompra\n"
                    + " inner join estados e on e.id_estado=p.id_estado\n"
                    + " inner join usuarios u on u.id_usuario=p.id_usuario\n"
                    + " inner join articulos a on a.id_articulo=d.id_articulo\n"
                    + " where p.id_pedidocompra=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allDetallesPediCompras.add(new PedidosComprasDTO(
                        rs.getString("pcompra_fecha"),
                        rs.getString("usu_nombre"),
                        rs.getString("est_descripcion"),
                        rs.getString("observacion"),
                        rs.getInt("id_articulo"),
                        rs.getInt("cantidad"),
                        rs.getInt("precio"),
                        rs.getString("codigenerico"),
                        rs.getInt("id_estado"),
                        rs.getString("art_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidosComprasDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allDetallesPediCompras);
    }

    @Override
    public boolean deledetalle(Integer nropedido) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM detpedidoscompras\n"
                    + " WHERE id_pedidocompra=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, nropedido);
  
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
            Logger.getLogger(PedidosComprasDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean insertarArtpedido(String pArticulo) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();

            sintaxiSql = "INSERT INTO articulos( art_descripcion) VALUES (upper(?))";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1,pArticulo);
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
            Logger.getLogger(PedidosComprasDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
          return false;
    }

}
