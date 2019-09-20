/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDAO.ventaDAO;
import ModelDTO.Coloresdto;
import ModelDTO.notaDebitoDTO;
import ModelDTO.ventaDTO;
import com.google.gson.Gson;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ventaDAOIMPLE implements ventaDAO {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public String getfactura(String caja) {
        ResultSet rs;
        ArrayList<ventaDTO> allfactura = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT f.idfactura, f.numerofac, f.id_estado, f.id_timbrado, t.fac_establecimiento, t.fac_caja,\n"
                    + "                    (t.fac_establecimiento||'-'||t.fac_caja||'-'||f.numerofac)as factura\n"
                    + "                    FROM public.factura f\n"
                    + "                    INNER JOIN timbrados t on f.id_timbrado=t.id_timbrado\n"
                    + "                    WHERE t.id_estado=7 and f.id_estado=5 and fac_caja = ?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setString(1, caja);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                allfactura.add(new ventaDTO(rs.getInt("idfactura"),
                        rs.getString("factura")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allfactura);
    }

    @Override
    public String getvendedor() {
        ResultSet rs;
        ArrayList<ventaDTO> allvendedor = new ArrayList<>();

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT v.idvendedor, v.fechaentrada, v.fechasalida, v.id_estado, v.id_empleado,\n"
                    + "(e.nombre||' '||e.apellido) vendedor\n"
                    + "  FROM public.vendedor v\n"
                    + "  INNER JOIN empleados e on e.id_empleado=v.id_empleado\n"
                    + "where v.id_estado=7";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allvendedor.add(new ventaDTO(rs.getInt("idvendedor"),
                        rs.getString("vendedor")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allvendedor);
    }

    @Override
    public String gettipopago() {
        ResultSet rs;
        ArrayList<ventaDTO> alltipago = new ArrayList<>();

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idtipopag, descripcion\n"
                    + "  FROM public.tipopago;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alltipago.add(new ventaDTO(rs.getInt("idtipopag"),
                        rs.getString("descripcion")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alltipago);
    }

    @Override
    public boolean insertarventa(ventaDTO Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO public.ventas(\n"
                    + "             nrofactura, idtipopag, id_cliente,id_deposito, \n"
                    + "             id_estado, id_usuario,idvendedor,id_apcica)\n"
                    + "    VALUES (?, ?, ?, ?, 1, ?, ?, ?)";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getNumerofac());
            preparedStatement.setObject(2, Dto.getTipoog());
            preparedStatement.setObject(3, Dto.getId_cliente());
            preparedStatement.setObject(4, Dto.getId_deposito());
            preparedStatement.setObject(5, Dto.getId_usuario());
            preparedStatement.setObject(6, Dto.getIdvendedor());
            preparedStatement.setObject(7, Dto.getId_apcica());
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
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean insertardetalle(ventaDTO Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO public.detventas(\n"
                    + "            id_articulo, id_venta, cantidad, precio, id_impuesto)\n"
                    + "    VALUES (?, ?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getId_articulo());
            preparedStatement.setObject(2, Dto.getId_venta());
            preparedStatement.setObject(3, Dto.getCantidad());
            preparedStatement.setObject(4, Dto.getPreciounitario());
            preparedStatement.setObject(5, Dto.getId_impuesto());
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
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean actualizarfactura(ventaDTO Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE public.factura\n"
                    + "   SET  id_estado=?\n"
                    + " WHERE idfactura=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getId_estado());
            preparedStatement.setObject(2, Dto.getId_factura());

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
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getcliente(String cedula) {
        ResultSet rs;
        ArrayList<ventaDTO> allcliente = new ArrayList<>();
        try {

            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_cliente, (ruc||'-'||cv) as cedula, razonsocial, telefono, direccion, web, id_barrio, \n" +
"                          id_ciudad\n" +
"                      FROM public.clientes\n" +
"                     where ruc = ?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setString(1, cedula);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allcliente.add(new ventaDTO(rs.getInt("id_cliente"),
                        rs.getString("cedula"),
                        rs.getString("razonsocial")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allcliente);
    }

    @Override
    public String getidvendedor(Integer codVendedor) {
        ResultSet rs;
        ArrayList<ventaDTO> allcodvendedor = new ArrayList<>();
        try {

            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT v.idvendedor, v.fechaentrada, v.fechasalida, v.id_estado, v.id_empleado,\n"
                    + "(e.nombre||' '||e.apellido) vendedor\n"
                    + "  FROM public.vendedor v\n"
                    + "  INNER JOIN empleados e on e.id_empleado=v.id_empleado\n"
                    + "where v.id_estado=7 and  v.idvendedor=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, codVendedor);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allcodvendedor.add(new ventaDTO(rs.getInt("idvendedor"),
                        rs.getString("vendedor")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allcodvendedor);
    }

    @Override
    public Integer getcodigo() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(id_venta),0 )+ 1 as codigo  FROM ventas;";
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

    public boolean grabarcliente(ventaDTO Dto) {

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO public.clientes(\n"
                    + "            ruc, razonsocial, telefono,direccion,cv)\n"
                    + "    VALUES (?, ?,?,?,?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getRuc());
            preparedStatement.setObject(2, Dto.getRazonsocial());
            preparedStatement.setObject(3, Dto.getTelefono());
            preparedStatement.setObject(4, Dto.getDireccion());
            preparedStatement.setObject(5, Dto.getCv());

            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("Comit() Realizado");
                return true;
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean anularfactura(ventaDTO Dto) {

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE public.ventas\n"
                    + "   SET id_estado=?\n"
                    + " WHERE nrofactura=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getId_estado());
            preparedStatement.setObject(2, Dto.getNumerofac());

            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("Comit() Realizado");
                return true;
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String gettimbrado() {
        ResultSet rs;
        ArrayList<ventaDTO> alltimbrado = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT t.id_timbrado, t.numero, t.inicio_fecha::date, t.final_fecha, t.vencimientos, \n"
                    + "       t.id_estado, t.id_usuario, t.fac_establecimiento, t.fac_caja, t.fac_desde, \n"
                    + "       t.fac_hasta, e.est_descripcion\n"
                    + "  FROM public.timbrados t\n"
                    + "  INNER JOIN estados e on t.id_estado = e.id_estado";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alltimbrado.add(new ventaDTO(rs.getInt("id_timbrado"),
                        rs.getInt("numero"),
                        rs.getString("inicio_fecha"),
                        rs.getString("vencimientos"),
                        rs.getString("est_descripcion"),
                        rs.getString("fac_caja")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alltimbrado);
    }

    public boolean insertartimbrado(ventaDTO Dto) {

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO public.timbrados(\n"
                    + "            numero, vencimientos,id_estado, \n"
                    + "            id_usuario, fac_establecimiento, fac_caja, fac_desde, \n"
                    + "            fac_hasta)\n"
                    + "    VALUES (?, ?::date, 7, ?, ?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getNumero());
            preparedStatement.setObject(2, Dto.getVencimientos());
            preparedStatement.setObject(3, Dto.getId_usuario());
            preparedStatement.setObject(4, Dto.getFac_establecimiento());
            preparedStatement.setObject(5, Dto.getFac_caja());
            preparedStatement.setObject(6, Dto.getFac_desde());
            preparedStatement.setObject(7, Dto.getFac_hasta());

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
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean insertardetallefacturas(ventaDTO Dto) {

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO public.factura(\n"
                    + "      id_estado, id_timbrado, numerofac)\n"
                    + "    VALUES (5, (select id_timbrado from timbrados where numero=?), ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getNumero());
            preparedStatement.setObject(2, Dto.getNumerofac());

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
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getfac(String user) {
        ResultSet rs;
        ArrayList<ventaDTO> allfac = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT a.id_apcica, a.apertura_fecha::date\n"
                    + "FROM public.aperturacierrecajas a\n"
                    + "INNER JOIN cajero c  on a.idcajero = c.idcajero\n"
                    + "INNER JOIN cajas j     on a.id_caja = j.id_caja\n"
                    + "INNER JOIN usuarios u  on c.id_usuario= u.id_usuario\n"
                    + "WHERE  u.usu_nombre=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setString(1, user);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                allfac.add(new ventaDTO(rs.getInt("id_apcica"),
                        rs.getString("apertura_fecha")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allfac);
    }

    @Override
    public boolean insertarcobro(ventaDTO Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO public.cobros(\n"
                    + "            importe, idtipopag,id_venta)\n"
                    + "    VALUES (?, ?, ?)";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getImporte());
            preparedStatement.setObject(2, Dto.getId_cobro());
            preparedStatement.setObject(3, Dto.getId_venta());

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
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean insertarcobrotarjeta(ventaDTO Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO public.cobrostarjetas(\n"
                    + "            id_cobro, tarjnrobol, \n"
                    + "            id_entiemi, id_tipotarjeta)\n"
                    + "    VALUES ((select id_cobro from cobros where id_venta=? and idtipopag = 2), ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getId_venta());
            preparedStatement.setObject(2, Dto.getTarjnrobol());
            preparedStatement.setObject(3, Dto.getId_entiemi());
            preparedStatement.setObject(4, Dto.getId_tipotarjeta());

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
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    @Override
    public boolean insertarcobrocheque(ventaDTO Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO public.cobroscheques(\n"
                    + "             nrochque, id_tipocheque, \n"
                    + "            id_cobro, id_bancocheque)\n"
                    + "    VALUES (?, ?, (select id_cobro from cobros where id_venta=? and idtipopag = 3), ? )";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getNrochque());
            preparedStatement.setObject(2, Dto.getId_tipocheque());
            preparedStatement.setObject(3, Dto.getId_venta());
            preparedStatement.setObject(4, Dto.getId_bancocheque());

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
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
