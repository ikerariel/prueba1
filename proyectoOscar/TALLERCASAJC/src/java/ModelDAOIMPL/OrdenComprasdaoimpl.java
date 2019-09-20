/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDAO.OrdenComprasdao;
import ModelDTO.Estadosdto;
import ModelDTO.OrdenComprasdto;
import ModelDTO.PedidosComprasDTO;
import ModelDTO.Proveedoresdto;
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
public class OrdenComprasdaoimpl implements OrdenComprasdao {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public Integer getUltimoCodigo() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(id_ordcompra),0 )+ 1 as codigo  FROM ordencompras;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdenComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public boolean insertarOrdenCompras(OrdenComprasdto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO ordencompras(id_sucursal, id_proveedor,"
                    + " id_pedidocompra, id_usuario, id_estado) VALUES (?, ?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getId_sucursal());
            preparedStatement.setObject(2, dto.getId_proveedor());
            preparedStatement.setObject(3, dto.getId_pedidocompra());
            preparedStatement.setObject(4, dto.getId_usuario());
            preparedStatement.setObject(5, dto.getId_estado());
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
            Logger.getLogger(OrdenComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean insertarDetOrdenCompras(OrdenComprasdto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO detordencompras(\n"
                    + " id_ordcompra, id_articulo, cantidad_detorden, precio_detorden)\n"
                    + " VALUES (?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getId_ordcompraD());
            preparedStatement.setObject(2, dto.getId_articulo());
            preparedStatement.setObject(3, dto.getCantidad_detorden());
            preparedStatement.setObject(4, dto.getPrecio_detorden());
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
            Logger.getLogger(OrdenComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getCabeseraFiltroOrdenCompras(Integer idFiltro) {
        ResultSet rs;
        ArrayList<OrdenComprasdto> allOrdenCompras = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT o.id_ordcompra, o.ordenc_fecha, s.suc_descripcion, p.ras_social, pc.id_pedidocompra, u.usu_nombre, e.est_descripcion\n"
                    + " FROM ordencompras o ,sucursales s, proveedores p, pedidoscompras pc, usuarios u, estados e\n"
                    + " WHERE o.id_sucursal=s.id_sucursal AND o.id_proveedor=p.id_proveedor AND o.id_pedidocompra=pc.id_pedidocompra AND o.id_usuario=u.id_usuario AND o.id_estado=e.id_estado\n"
                    + " ORDER BY o.id_ordcompra;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, idFiltro);
            rs = preparedStatement.executeQuery();
//            if (rs.next()) {
//                allPedidoC.add(new pedidocompradto(rs.getInt("pcomp_nro"),
//                        rs.getString("pcomp_fecha"),
//                        rs.getString("pcomp_estado"),
//                        rs.getString("observacion"),
//                        rs.getString("usu_nombre"),
//                        rs.getString("descripcion")));
//            } else {
//                return null;
//            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdenComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allOrdenCompras);
    }

    @Override
    public String getDetallesFiltroOrdenCompras(Integer idFiltro) {
        ResultSet rs;
        ArrayList<OrdenComprasdto> allOrdenComprasD = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT , id_ordcompra, id_articulo, cantidad_detorden, precio_detorden\n"
                    + "  FROM detordencompras WHERE id_ordcompra=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, idFiltro);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                allOrdenComprasD.add(new OrdenComprasdto(
                        rs.getInt("id_ordcompra"),
                        rs.getInt("id_articulo"),
                        rs.getInt("cantidad_detorden"),
                        rs.getInt("precio_detorden")));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdenComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allOrdenComprasD);
    }

    @Override
    public boolean modificarOrdenCompras(OrdenComprasdto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE ordencompras SET id_ordcompra=?, ordenc_fecha=?, id_sucursal=?, id_proveedor=?, \n"
                    + "id_pedidocompra=?, id_usuario=?, id_estado=? WHERE id_ordcompra=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getOrdenc_fecha());
            preparedStatement.setObject(2, dto.getId_sucursal());
            preparedStatement.setObject(3, dto.getId_proveedor());
            preparedStatement.setObject(4, dto.getId_pedidocompra());
            preparedStatement.setObject(5, dto.getId_usuario());
            preparedStatement.setObject(6, dto.getId_estado());
            preparedStatement.setObject(7, dto.getId_ordcompra());
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
            Logger.getLogger(OrdenComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean confirmarOrdenCompras(OrdenComprasdto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE ordencompras SET id_estado=? WHERE id_ordcompra=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getId_estado());
            preparedStatement.setObject(2, dto.getId_ordcompra());

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
            Logger.getLogger(OrdenComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean modificarDetOrdenCompras(OrdenComprasdto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE detordencompras SET id_ordcompra=?, id_articulo=?, cantidad_detorden=?,"
                    + " precio_detorden=?,id_estado=4 WHERE id_ordcompra=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getId_articulo());
            preparedStatement.setObject(2, dto.getCantidad_detorden());
            preparedStatement.setObject(3, dto.getPrecio_detorden());
            preparedStatement.setObject(4, dto.getId_ordcompraD());
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
            Logger.getLogger(OrdenComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String listarOrdenCompras() {
        ResultSet rs;
        ArrayList<OrdenComprasdto> allOrdenCompras = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT o.id_ordcompra, o.ordenc_fecha, s.suc_descripcion, p.ras_social, pc.id_pedidocompra, u.usu_nombre, e.est_descripcion\n"
                    + "  FROM ordencompras o \n"
                    + "  inner join sucursales s on o.id_sucursal=s.id_sucursal\n"
                    + "  inner join proveedores p on o.id_proveedor=p.id_proveedor\n"
                    + "  inner join pedidoscompras pc on o.id_pedidocompra=pc.id_pedidocompra\n"
                    + "  inner join usuarios u on o.id_usuario=u.id_usuario\n"
                    + "  inner join estados e on o.id_estado=e.id_estado \n"
                    + "  ORDER BY o.id_ordcompra desc;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allOrdenCompras.add(new OrdenComprasdto(
                        rs.getInt("id_ordcompra"),
                        rs.getString("ordenc_fecha"),
                        rs.getString("suc_descripcion"),
                        rs.getString("ras_social"),
                        rs.getInt("id_pedidocompra"),
                        rs.getString("usu_nombre"),
                        rs.getString("est_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdenComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allOrdenCompras);
    }

    @Override
    public String listarSucursales() {
        ResultSet rs;
        ArrayList<Sucursalesdto> allSucursales = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_sucursal, suc_descripcion, id_ciudad,"
                    + " id_barrio FROM sucursales WHERE id_sucursal=2;";
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
            Logger.getLogger(OrdenComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allSucursales);
    }

    @Override
    public String listarProveedores() {
        ResultSet rs;
        ArrayList<Proveedoresdto> allProveedores = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "Select *from proveedores";
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
                        rs.getInt("id_ciudad")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdenComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allProveedores);
    }

    @Override
    public String listarPedidosCompras() {
        ResultSet rs;
        ArrayList<OrdenComprasdto> allPediCom = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT p.id_pedidocompra, p.pcompra_fecha, p.id_usuario,u.usu_nombre , p.id_estado, p.observacion, e.est_descripcion\n"
                    + "  FROM pedidoscompras p\n"
                    + "  inner join estados e on p.id_estado = e.id_estado\n"
                    + "  inner join usuarios u on p.id_usuario = u.id_usuario";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allPediCom.add(new OrdenComprasdto(
                        rs.getInt("id_pedidocompra"),
                        rs.getString("pcompra_fecha"),
                        rs.getString("usu_nombre"),
                        rs.getString("est_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdenComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allPediCom);
    }

    @Override
    public String listarUsuarios() {
        ResultSet rs;
        ArrayList<Usuariosdto> allUsuarios = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_usuario, usu_nombre, usu_clave, id_empleado, id_sucursal, id_perfil\n"
                    + "FROM usuarios WHERE id_usuario=1";
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
            Logger.getLogger(OrdenComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allUsuarios);
    }

    @Override
    public String listarArticulos() {
        ResultSet rs;
        ArrayList<OrdenComprasdto> allArticulos = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT a.id_articulo, a.art_descripcion,s.cantidad, a.preccompras, a.precventas, a.id_impuesto, \n"
                    + "                          a.id_marca, a.codigenerico, (i.imp_descripcion) as impuesto\n"
                    + "                    FROM articulos a\n"
                    + "		INNER JOIN impuestos i on a.id_impuesto=i.id_impuesto\n"
                    + "		inner join stock s on a.id_articulo = s.id_articulo";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allArticulos.add(new OrdenComprasdto(
                        rs.getInt("id_articulo"),
                        rs.getString("art_descripcion"),
                        rs.getInt("cantidad"),
                        rs.getInt("preccompras"),
                        rs.getInt("precventas"),
                        rs.getInt("id_impuesto"),
                        rs.getInt("id_marca"),
                        rs.getString("impuesto")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdenComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allArticulos);
    }

    @Override
    public String listarEstados() {
        ResultSet rs;
        ArrayList<Estadosdto> allEstados = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_estado, est_descripcion\n"
                    + "FROM estados WHERE id_estado=1;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allEstados.add(new Estadosdto(
                        rs.getInt("id_estado"),
                        rs.getString("est_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdenComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allEstados);
    }

    @Override
    public String listarDetPedidosCompras(Integer id) {
        ResultSet rs;
        ArrayList<OrdenComprasdto> alldeallepresupuesto = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();

            sintaxiSql = "SELECT p.id_presucompra,pc.id_pedidocompra, p.fecha::date, p.id_estado, p.id_usuario,\n" +
"                     p.id_proveedor, (v.ras_social) as proveedor, p.id_deposito,\n" +
"                     (d.dep_descripcion)as deposito,\n" +
"                          p.idtipomoneda, (m.descripcion) as moneda,dp.id_articulo,\n" +
"                    (a.art_descripcion) as articulo, dp.cantidad, dp.preciounitario,dp.iddetpresuompras  \n" +
"                     FROM presupuestocompra p\n" +
"                     inner join pedidoscompras pc on p.id_pedidocompra=pc.id_pedidocompra              \n" +
"     inner join proveedores v on p.id_proveedor=v.id_proveedor\n" +
"                    inner join depositos d on p.id_deposito=d.id_deposito             \n" +
"      inner join tipomoneda m on p.idtipomoneda=m.idtipomoneda\n" +
"                  inner join detpresuompras dp on p.id_presucompra= dp.id_presucompra\n" +
"                  inner join articulos a on dp.id_articulo= a.id_articulo\n" +
"                  where p.id_presucompra=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alldeallepresupuesto.add(new OrdenComprasdto(
                        rs.getInt("id_presucompra"),
                        rs.getInt("id_estado"),
                        rs.getInt("id_proveedor"),
                        rs.getString("proveedor"),
                        rs.getInt("id_articulo"),
                        rs.getString("articulo"),
                        rs.getInt("preciounitario"),
                        rs.getInt("cantidad")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdenComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new Gson().toJson(alldeallepresupuesto);
    }

    @Override
    public Integer getUltimoCodigoArticulos() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(id_articulo),0 )+ 1 as codigoA  FROM articulos;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigoA");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdenComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public boolean insertarArticulos(OrdenComprasdto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO articulos(art_descripcion, preccompras, codigenerico) VALUES (?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getArt_descripcion());
            preparedStatement.setObject(2, dto.getPreccompras());
            preparedStatement.setObject(3, dto.getCodigenerico());
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
            Logger.getLogger(OrdenComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String listarDetOrdenCompras(Integer id) {
        ResultSet rs;
        ArrayList<OrdenComprasdto> allDetOrdenCompras = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();

            sintaxiSql = "SELECT o.ordenc_fecha, s.suc_descripcion, p.ras_social, pc.id_pedidocompra, u.usu_nombre, e.est_descripcion,\n"
                    + " d.id_articulo, d.cantidad_detorden, d.precio_detorden, a.codigenerico, a.art_descripcion\n"
                    + " FROM ordencompras o\n"
                    + " inner join detordencompras d on o.id_ordcompra = d.id_ordcompra\n"
                    + " inner join sucursales s on o.id_sucursal = s.id_sucursal\n"
                    + " inner join proveedores p on o.id_proveedor = p.id_proveedor\n"
                    + " inner join usuarios u on o.id_usuario = u.id_usuario\n"
                    + " inner join pedidoscompras pc on o.id_pedidocompra = pc.id_pedidocompra\n"
                    + " inner join estados e on o.id_estado = e.id_estado\n"
                    + " inner join articulos a on a.id_articulo=d.id_articulo\n"
                    + " where o.id_ordcompra=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allDetOrdenCompras.add(new OrdenComprasdto(
                        rs.getString("ordenc_fecha"),
                        rs.getString("suc_descripcion"),
                        rs.getString("ras_social"),
                        rs.getInt("id_pedidocompra"),
                        rs.getString("usu_nombre"),
                        rs.getString("est_descripcion"),
                        rs.getInt("id_articulo"),
                        rs.getInt("cantidad_detorden"),
                        rs.getInt("precio_detorden"),
                        rs.getString("codigenerico"),
                        rs.getString("art_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdenComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new Gson().toJson(allDetOrdenCompras);
    }

    @Override
    public String getArticulo(Integer cod, Integer coddepo) {
        ResultSet rs;
        ArrayList<OrdenComprasdto> allarticulos = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();

            sintaxiSql =  "SELECT a.id_articulo, a.art_descripcion,s.cantidad, a.preccompras, a.precventas, a.id_impuesto, \n" +
"                    a.id_marca, a.codigenerico, (i.imp_descripcion) as impuesto\n" +
"                    FROM articulos a\n" +
"                    INNER JOIN impuestos i on a.id_impuesto=i.id_impuesto\n" +
"                    inner join stock s on a.id_articulo = s.id_articulo\n" +
"                    where s.id_articulo = ? and s.id_deposito=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, cod);
            preparedStatement.setInt(2, coddepo);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allarticulos.add(new OrdenComprasdto(
                        rs.getInt("id_articulo"),
                        rs.getString("art_descripcion"),
                        rs.getInt("cantidad"),
                        rs.getInt("preccompras"),
                        rs.getInt("precventas"),
                        rs.getInt("id_impuesto"),
                        rs.getInt("id_marca"),
                        rs.getString("impuesto")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdenComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new Gson().toJson(allarticulos);
    }

    public boolean actualizarestados(OrdenComprasdto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE ordencompras\n"
                    + "   SET id_estado=?\n"
                    + " WHERE id_ordcompra=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, 4);
            preparedStatement.setObject(2, dto.getId_ordcompra());
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
            Logger.getLogger(OrdenComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String productosPorDeposito(Integer id) {
        ResultSet rs;
        ArrayList<OrdenComprasdto> allproductos = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT a.id_articulo, a.art_descripcion,s.cantidad, a.preccompras, a.precventas, a.id_impuesto, \n"
                    + "a.id_marca, a.codigenerico, (i.imp_descripcion) as impuesto\n"
                    + "FROM articulos a\n"
                    + "INNER JOIN impuestos i on a.id_impuesto=i.id_impuesto\n"
                    + "inner join stock s on a.id_articulo = s.id_articulo\n"
                    + "where s.id_deposito = ?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allproductos.add(new OrdenComprasdto(
                      rs.getInt("id_articulo"),
                        rs.getString("art_descripcion"),
                        rs.getInt("cantidad"),
                        rs.getInt("preccompras"),
                        rs.getInt("precventas"),
                        rs.getInt("id_impuesto"),
                        rs.getInt("id_marca"),
                        rs.getString("impuesto")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdenComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allproductos);
    }

}
