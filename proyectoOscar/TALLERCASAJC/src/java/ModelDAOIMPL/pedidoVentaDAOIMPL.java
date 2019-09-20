/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDAO.pedidoVentaDAO;
import ModelDTO.pedidoVentaDTO;
import ModelDTO.sRecepcionDTO;
import com.google.gson.Gson;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class pedidoVentaDAOIMPL implements pedidoVentaDAO {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public boolean insertarpedidoventa(pedidoVentaDTO dto, Integer cod) {
        switch (cod) {
            case 1: //INSERTAR CABACERA
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "INSERT INTO public.pedidosventas(\n"
                            + "            id_cliente, id_sucursal, id_estado, idvendedor,observacion)\n"
                            + "    VALUES (?, ?, 3, ?,?);";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getId_cliente());
                    preparedStatement.setObject(2, dto.getId_sucursal());
                    preparedStatement.setObject(3, dto.getIdvendedor());
                    preparedStatement.setObject(4, dto.getObservacion());
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
                    Logger.getLogger(pedidoVentaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);

                }

                break;

            case 2:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "UPDATE public.pedidosventas\n"
                            + "   SET id_cliente=?, id_sucursal=?, id_estado=3, idvendedor=?, \n"
                            + "       fechapedido=now(), observacion=?\n"
                            + " WHERE id_pedidoven=?";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getId_cliente());
                    preparedStatement.setObject(2, dto.getId_sucursal());
                    preparedStatement.setObject(3, dto.getIdvendedor());
                    preparedStatement.setObject(4, dto.getObservacion());
                    preparedStatement.setObject(5, dto.getId_pedidoven());
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
                    Logger.getLogger(pedidoVentaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);

                }

                break;
        }
        return false;

    }

    @Override
    public boolean deletedetallepedidoventa(pedidoVentaDTO dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM public.detpedidosventas\n"
                    + " WHERE id_pedidoven=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getId_pedidoven());
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
            Logger.getLogger(pedidoVentaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    @Override
    public boolean insertardetallepedidoventa(pedidoVentaDTO dto, Integer detalle) {
        switch (detalle) {
            case 1:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "INSERT INTO public.detpedidosventas(\n"
                            + "            id_pedidoven, id_articulo, cantidad, precio)\n"
                            + "    VALUES ((select id_pedidoven from pedidosventas order by id_pedidoven desc limit 1), ?, ?, ?);";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getId_articulo());
                    preparedStatement.setObject(2, dto.getCantidad());
                    preparedStatement.setObject(3, dto.getPrecio());
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
                    Logger.getLogger(pedidoVentaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);

                }
                break;

            case 2:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "INSERT INTO public.detpedidosventas(\n"
                            + "            id_pedidoven, id_articulo, cantidad, precio)\n"
                            + "    VALUES (?, ?, ?, ?);";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getId_pedidoven());
                    preparedStatement.setObject(2, dto.getId_articulo());
                    preparedStatement.setObject(3, dto.getCantidad());
                    preparedStatement.setObject(4, dto.getPrecio());
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
                    Logger.getLogger(pedidoVentaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);

                }
                break;

        }

        return false;
    }

    @Override
    public String getpedidoVenta() {
        ResultSet rs;
        ArrayList<pedidoVentaDTO> allpventa = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = " SELECT p.id_pedidoven, p.fechapedido::date, p.id_cliente, p.id_estado, p.idvendedor, \n"
                    + "                                        e.est_descripcion,(u.usu_nombre) as vendedor,\n"
                    + "                                        (c.ruc) as cedula,c.cv, (c.ruc||'-'||c.cv||' / '||c.razonsocial) as cliente\n"
                    + "                                         FROM public.pedidosventas p\n"
                    + "                                        left join clientes c on p.id_cliente = c.id_cliente\n"
                    + "                                        left join vendedor v on p.idvendedor = v.idvendedor\n"
                    + "                    		left join empleados emp on v.id_empleado = emp.id_empleado\n"
                    + "                    		left join usuarios u on emp.id_empleado = u.id_empleado\n"
                    + "                    left join estados e on p.id_estado=e.id_estado\n"
                    + "                    where p.id_estado in(1,3) order by p.id_pedidoven desc";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allpventa.add(new pedidoVentaDTO(rs.getInt("id_pedidoven"),
                        rs.getString("fechapedido"),
                        rs.getString("cliente"),
                        rs.getString("est_descripcion"),
                        rs.getString("vendedor"),
                        rs.getInt("id_estado")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(pedidoVentaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allpventa);
    }

    @Override
    public String getpedidoVentaDetalle(Integer nropedidoventa) {
        ResultSet rs;
        ArrayList<pedidoVentaDTO> allpventadetalle = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = " SELECT p.id_pedidoven, p.fechapedido::date, p.id_cliente, p.id_estado, p.idvendedor, \n"
                    + "e.est_descripcion,(u.usu_nombre) as vendedor,p.observacion,\n"
                    + "(c.ruc) as cedula,c.cv, (c.ruc||'-'||c.cv||' / '||c.razonsocial) as cliente,\n"
                    + "d.id_articulo, d.cantidad, d.precio, (a.art_descripcion) as articulo\n"
                    + "FROM public.pedidosventas p\n"
                    + "left join detpedidosventas d on p.id_pedidoven=d.id_pedidoven\n"
                    + "left join articulos a on d.id_articulo=a.id_articulo\n"
                    + "left join clientes c on p.id_cliente = c.id_cliente\n"
                    + "left join vendedor v on p.idvendedor = v.idvendedor\n"
                    + "left join empleados emp on v.id_empleado = emp.id_empleado\n"
                    + "left join usuarios u on emp.id_empleado = u.id_empleado\n"
                    + "left join estados e on p.id_estado=e.id_estado\n"
                    + "where p.id_estado in(1,3) and p.id_pedidoven=? order by p.id_pedidoven desc";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, nropedidoventa);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allpventadetalle.add(new pedidoVentaDTO(rs.getInt("id_pedidoven"),
                        rs.getString("fechapedido"),
                        rs.getString("observacion"),
                        rs.getInt("idvendedor"),
                        rs.getString("cliente"),
                        rs.getString("est_descripcion"),
                        rs.getString("vendedor"),
                        rs.getInt("id_cliente"),
                        rs.getString("cedula"),
                        rs.getInt("id_articulo"),
                        rs.getInt("precio"),
                        rs.getInt("cantidad"),
                        rs.getString("articulo"),
                        rs.getInt("id_estado")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(pedidoVentaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allpventadetalle);
    }

    @Override
    public boolean updatepedidoventa(pedidoVentaDTO dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE public.pedidosventas\n"
                    + "   SET  id_estado=?\n"
                    + " WHERE id_pedidoven=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getId_estado());
            preparedStatement.setObject(2, dto.getId_pedidoven());
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
            Logger.getLogger(pedidoVentaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

}
