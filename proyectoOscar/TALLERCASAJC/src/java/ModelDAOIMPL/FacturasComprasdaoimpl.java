/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDAO.FacturasComprasdao;
import ModelDTO.Articulosdto;
import ModelDTO.Estadosdto;
import ModelDTO.FacturasComprasdto;
import ModelDTO.OrdenComprasdto;
import ModelDTO.Proveedoresdto;
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
public class FacturasComprasdaoimpl implements FacturasComprasdao {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public Integer getUltimoCodigoCompras() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(id_compra),0 )+ 1 as codigo  FROM facturascompras;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacturasComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public String ListarEstadosCompras2() {
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
            Logger.getLogger(FacturasComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allEstados);
    }

    @Override
    public String ListarUsuariosCompras3() {
        ResultSet rs;
        ArrayList<Usuariosdto> allUsuarios = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_usuario, usu_nombre, usu_clave, id_empleado, id_sucursal, id_perfil\n"
                    + " FROM usuarios where id_usuario=3";
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
            Logger.getLogger(FacturasComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allUsuarios);
    }

    @Override
    public String ListarProveedoresCompras4() {
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
            Logger.getLogger(FacturasComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allProveedores);
    }

    @Override
    public String ListarOrdenCompras5() {
        ResultSet rs;
        ArrayList<OrdenComprasdto> allOrden = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT o.id_ordcompra, o.ordenc_fecha,  u.usu_nombre, e.est_descripcion\n"
                    + " FROM ordencompras o\n"
                    + " inner join usuarios u on o.id_usuario=u.id_usuario\n"
                    + " inner join estados e on o.id_estado=e.id_estado where e.id_estado=1";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allOrden.add(new OrdenComprasdto(
                        rs.getInt("id_ordcompra"),
                        rs.getString("ordenc_fecha"),
                        rs.getString("usu_nombre"),
                        rs.getString("est_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacturasComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allOrden);
    }

    @Override
    public String ListarDetOrdenCompras6(Integer id) {
        ResultSet rs;
        ArrayList<OrdenComprasdto> allDetalleOrden = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();

            sintaxiSql = "SELECT  o.ordenc_fecha, s.suc_descripcion, p.ras_social, pc.id_pedidocompra, u.usu_nombre, e.est_descripcion, d.id_articulo, d.cantidad_detorden, d.precio_detorden, a.codigenerico, a.art_descripcion\n"
                    + " FROM ordencompras o\n"
                    + " inner join detordencompras d on o.id_ordcompra = d.id_ordcompra\n"
                    + " inner join sucursales s on o.id_sucursal = s.id_sucursal\n"
                    + " inner join proveedores p on o.id_proveedor = p.id_proveedor\n"
                    + " inner join pedidoscompras pc on o.id_pedidocompra = pc.id_pedidocompra\n"
                    + " inner join usuarios u on o.id_usuario = u.id_usuario\n"
                    + " inner join estados e on o.id_estado = e.id_estado\n"
                    + " inner join articulos a on a.id_articulo=d.id_articulo\n"
                    + " where o.id_ordcompra=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allDetalleOrden.add(new OrdenComprasdto(
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
            Logger.getLogger(FacturasComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allDetalleOrden);
    }

    @Override
    public String ListarSucursalesCompras7() {
        ResultSet rs;
        ArrayList<FacturasComprasdto> allSucursales = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_sucursal, suc_descripcion\n"
                    + "FROM sucursales WHERE id_sucursal=1;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allSucursales.add(new FacturasComprasdto(
                        rs.getInt("id_sucursal"),
                        rs.getString("suc_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacturasComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allSucursales);
    }

    @Override
    public String ListarArticulosCompras8() {
        ResultSet rs;
        ArrayList<Articulosdto> allArticulos = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_articulo, art_descripcion, preccompras, precventas, id_impuesto, id_marca, codigenerico\n"
                    + "FROM articulos;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allArticulos.add(new Articulosdto(
                        rs.getInt("id_articulo"),
                        rs.getString("art_descripcion"),
                        rs.getInt("preccompras"),
                        rs.getInt("precventas"),
                        rs.getInt("id_impuesto"),
                        rs.getInt("id_marca"),
                        rs.getString("codigenerico")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacturasComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allArticulos);
    }

    @Override
    public boolean insertarCabeceraCompras9(FacturasComprasdto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO facturascompras(co_cantcuota, co_monto, co_nrofact,"
                    + " co_intervalo, co_fecha, co_tipo, id_proveedor, id_sucursal, "
                    + " id_usuario, id_estado, id_ordencompra)\n"
                    + " VALUES (?, ?, ?, ?, ?::date, ?, ?, ?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getCo_cantcuota());
            preparedStatement.setObject(2, dto.getCo_monto());
            preparedStatement.setObject(3, dto.getCo_nrofact());
            preparedStatement.setObject(4, dto.getCo_intervalo());
            preparedStatement.setObject(5, dto.getCo_fecha());
            preparedStatement.setObject(6, dto.getCo_tipo());
            preparedStatement.setObject(7, dto.getId_proveedor());
            preparedStatement.setObject(8, dto.getId_sucursal());
            preparedStatement.setObject(9, dto.getId_usuario());
            preparedStatement.setObject(10, dto.getId_estado());
            preparedStatement.setObject(11, dto.getId_ordencompra());
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
            Logger.getLogger(FacturasComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean insertarDetCompras10(FacturasComprasdto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO detfacturascompras(id__compra, id_articulo, cantidad_detcomp, precio_detcomp)\n"
                    + " VALUES (?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getId__compraD());
            preparedStatement.setObject(2, dto.getId_articulo());
            preparedStatement.setObject(3, dto.getCantidad_detcomp());
            preparedStatement.setObject(4, dto.getPrecio_detcomp());
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
            Logger.getLogger(FacturasComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String ListarFacturasCompras11() {
        ResultSet rs;
        ArrayList<FacturasComprasdto> allFacturasCompras = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT f.id_compra, f.co_nrofact, f.co_fecha, f.co_tipo,\n"
                    + " p.ras_social, s.suc_descripcion, u.usu_nombre, e.est_descripcion\n"
                    + " FROM facturascompras f \n"
                    + " inner join proveedores p on f.id_proveedor = p.id_proveedor\n"
                    + " inner join sucursales s on f.id_sucursal = s.id_sucursal\n"
                    + " inner join usuarios u on f.id_usuario = u.id_usuario\n"
                    + " inner join estados e on f.id_estado = e.id_estado\n"
                    + " order by id_compra desc;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allFacturasCompras.add(new FacturasComprasdto(
                        rs.getInt("id_compra"),
                        rs.getString("co_nrofact"),
                        rs.getString("co_fecha"),
                        rs.getString("co_tipo"),
                        rs.getString("ras_social"),
                        rs.getString("suc_descripcion"),
                        rs.getString("usu_nombre"),
                        rs.getString("est_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacturasComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allFacturasCompras);
    }

    @Override
    public boolean confirmarFacturasCompras12(FacturasComprasdto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE facturascompras SET id_estado=? WHERE id_compra=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getId_estado());
            preparedStatement.setObject(2, dto.getId_compra());
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
            Logger.getLogger(FacturasComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String listarDetCompras13(Integer id) {
        ResultSet rs;
        ArrayList<FacturasComprasdto> allDetFacturas = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();

            sintaxiSql = " SELECT f.id_compra, f.co_cantcuota, f.co_monto, f.co_nrofact, f.co_intervalo, f.co_fecha, f.co_tipo, f.id_proveedor,\n"
                    + "                     p.ras_social, f.id_sucursal, s.suc_descripcion, f.id_usuario, u.usu_nombre, e.est_descripcion, o.id_ordcompra, \n"
                    + "                     d.id_articulo, d.cantidad_detcomp, d.precio_detcomp, a.codigenerico, a.art_descripcion\n"
                    + "                    FROM facturascompras f\n"
                    + "                     inner join proveedores p on f.id_proveedor = p.id_proveedor\n"
                    + "                     inner join sucursales s on f.id_sucursal = s.id_sucursal\n"
                    + "                     inner join usuarios u on f.id_usuario = u.id_usuario\n"
                    + "                    inner join estados e on f.id_estado = e.id_estado\n"
                    + "                     inner join ordencompras o on f.id_ordencompra = o.id_ordcompra\n"
                    + "                     inner join detfacturascompras d on f.id_compra = d.id__compra\n"
                    + "                    inner join articulos a on d.id_articulo = a.id_articulo\n"
                    + "                     where f.id_compra=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allDetFacturas.add(new FacturasComprasdto(
                        rs.getInt("id_compra"),
                        rs.getInt("co_cantcuota"),
                        rs.getInt("co_monto"),
                        rs.getString("co_nrofact"),
                        rs.getString("co_intervalo"),
                        rs.getString("co_fecha"),
                        rs.getString("co_tipo"),
                        rs.getInt("id_proveedor"),
                        rs.getString("ras_social"),
                        rs.getInt("id_sucursal"),
                        rs.getString("suc_descripcion"),
                        rs.getInt("id_usuario"),
                        rs.getString("usu_nombre"),
                        rs.getString("est_descripcion"),
                        rs.getInt("id_ordcompra"),
                        rs.getInt("id_articulo"),
                        rs.getInt("cantidad_detcomp"),
                        rs.getInt("precio_detcomp"),
                        rs.getString("codigenerico"),
                        rs.getString("art_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacturasComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new Gson().toJson(allDetFacturas);
    }

    @Override
    public boolean modificarCabeceraCompras9(FacturasComprasdto dto) {

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE public.facturascompras\n"
                    + "   SET co_cantcuota=?, co_monto=?, co_nrofact=?, co_intervalo=?, \n"
                    + "       co_fecha=?::date, co_tipo=?, id_proveedor=?, id_sucursal=?, id_usuario=?, \n"
                    + "       id_estado=?, id_ordencompra=?\n"
                    + " WHERE id_compra=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getCo_cantcuota());
            preparedStatement.setObject(2, dto.getCo_monto());
            preparedStatement.setObject(3, dto.getCo_nrofact());
            preparedStatement.setObject(4, dto.getCo_intervalo());
            preparedStatement.setObject(5, dto.getCo_fecha());
            preparedStatement.setObject(6, dto.getCo_tipo());
            preparedStatement.setObject(7, dto.getId_proveedor());
            preparedStatement.setObject(8, dto.getId_sucursal());
            preparedStatement.setObject(9, dto.getId_usuario());
            preparedStatement.setObject(10, dto.getId_estado());
            preparedStatement.setObject(11, dto.getId_ordencompra());
            preparedStatement.setObject(12, dto.getId_compra());
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
            Logger.getLogger(FacturasComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
