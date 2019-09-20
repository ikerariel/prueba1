/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDAO.aperturacierrecajaDAO;
import ModelDTO.NotaCreComprasdto;
import ModelDTO.OrdenComprasdto;
import ModelDTO.aperturacierrecajaDTO;
import com.google.gson.Gson;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class aperturacierrecajaDAOIMPL implements aperturacierrecajaDAO {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public boolean insertarApertura(aperturacierrecajaDTO Dto) {

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO public.aperturacierrecajas(\n"
                    + "apcica_apermonto,id_caja, id_sucursal, id_estado, \n"
                    + "id_deposito, idsupervisor, idcajero, \n"
                    + "id_timbrado)\n"
                    + " VALUES (?, ?, ?, 9, ?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getApcica_apermonto());
            preparedStatement.setObject(2, Dto.getId_caja());
            preparedStatement.setObject(3, Dto.getId_sucursal());
            preparedStatement.setObject(4, Dto.getId_deposito());
            preparedStatement.setObject(5, Dto.getIdsupervisor());
            preparedStatement.setObject(6, Dto.getIdcajero());
            preparedStatement.setObject(7, Dto.getId_timbrado());
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
            Logger.getLogger(aperturacierrecajaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    @Override
    public String getaperturascierres() {
        ResultSet rs;
        ArrayList<aperturacierrecajaDTO> allaperturacierrecaja = new ArrayList<>();

        try {

            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT a.id_apcica, a.apcica_ciermonto, a.apcica_apermonto, a.apcica_ciefecha, \n"
                    + "a.id_caja, (c.descripcion) as caja, a.id_sucursal, a.id_estado, (e.est_descripcion) as estado, a.id_deposito,\n"
                    + " a.idsupervisor,(us.usu_nombre)as supervisor, a.idcajero, \n"
                    + "(u.usu_nombre) cajero, a.id_timbrado, a.apertura_fecha\n"
                    + "  FROM public.aperturacierrecajas a\n"
                    + "  INNER JOIN cajas c on a.id_caja = c.id_caja\n"
                    + "  INNER JOIN cajero r on a.idcajero = r.idcajero\n"
                    + "  INNER JOIN usuarios u on r.id_usuario = u.id_usuario\n"
                    + "  INNER JOIN supervisor s on a.idsupervisor = s.idsupervisor\n"
                    + "  INNER JOIN usuarios us on s.id_usuario = us.id_usuario\n"
                    + "  INNER JOIN estados e on a.id_estado = e.id_estado order by a.id_apcica desc ";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allaperturacierrecaja.add(new aperturacierrecajaDTO(
                        rs.getInt("id_apcica"),
                        rs.getString("apertura_fecha"),
                        rs.getString("caja"),
                        rs.getString("cajero"),
                        rs.getString("supervisor"),
                        rs.getString("estado")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(aperturacierrecajaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allaperturacierrecaja);
    }

    @Override
    public String facturacion(String usuario) {
        ResultSet rs;
        ArrayList<aperturacierrecajaDTO> allfacturacion = new ArrayList<>();

        try {

            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT a.id_apcica, a.apcica_ciermonto, a.apcica_apermonto, a.apcica_ciefecha, \n"
                    + "                    a.id_caja, (j.descripcion) as caja, a.id_sucursal, a.id_estado, a.id_deposito, a.idsupervisor, a.idcajero, \n"
                    + "                   a.id_timbrado, a.apertura_fecha,(t.fac_establecimiento||'-'||t.fac_caja||'-'||f.numerofac)as factura, f.idfactura\n"
                    + "                    FROM public.aperturacierrecajas a\n"
                    + "                    INNER JOIN cajero c    on a.idcajero = c.idcajero\n"
                    + "                    INNER JOIN cajas j     on a.id_caja = j.id_caja\n"
                    + "                    INNER JOIN usuarios u  on c.id_usuario= u.id_usuario\n"
                    + "                   INNER JOIN timbrados t on a.id_timbrado=t.id_timbrado\n"
                    + "                    INNER JOIN factura f   on t.id_timbrado=f.id_timbrado\n"
                    + "                    WHERE t.id_estado=7 and a.id_estado=9 and u.usu_nombre=? and f.id_estado=5 and t.id_estado=7 order by factura asc";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setString(1, usuario);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                allfacturacion.add(new aperturacierrecajaDTO(
                        rs.getString("caja"),
                        rs.getInt("id_apcica"),
                        rs.getInt("idfactura"),
                        rs.getString("factura")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(aperturacierrecajaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allfacturacion);

    }

    @Override
    public String getfacturacion(String loguin) {
        ResultSet rs;
        ArrayList<aperturacierrecajaDTO> allfac = new ArrayList<>();
        try {

            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT a.id_apcica\n"
                    + "FROM public.aperturacierrecajas a\n"
                    + "INNER JOIN cajero c on a.idcajero = c.idcajero\n"
                    + "INNER JOIN cajas j on a.id_caja = j.id_caja\n"
                    + "INNER JOIN usuarios u on c.id_usuario= u.id_usuario\n"
                    + "WHERE u.usu_nombre=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setString(1, loguin);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allfac.add(new aperturacierrecajaDTO(
                        rs.getInt("id_apcica")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(aperturacierrecajaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allfac);
    }

    @Override
    public boolean insertarmovimientoarqueo(aperturacierrecajaDTO Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO public.movimientoarrqueo(\n"
                    + "            cantimoneda, montounitario, id_apcica, iddenominacionmoneda, \n"
                    + "            nrochequevouchertarjeta)\n"
                    + "    VALUES (?, ?, ?, ?, ?)";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getCantimoneda());
            preparedStatement.setObject(2, Dto.getMontounitario());
            preparedStatement.setObject(3, Dto.getId_apcica());
            preparedStatement.setObject(4, Dto.getIddenominacionmoneda());
            preparedStatement.setObject(5, Dto.getNrocheque());
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
            Logger.getLogger(aperturacierrecajaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String facturado(Integer codapertura1, Integer codapertura2) {
        ResultSet rs;
        ArrayList<aperturacierrecajaDTO> alltotalfacturado = new ArrayList<>();

        try {

            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT v.id_venta, v.nrofactura, v.fecha, v.idtipopag, v.id_timbrado, v.id_cliente, \n"
                    + "v.id_deposito, v.id_pedidoven, v.id_estado, v.id_usuario, v.idvendedor, \n"
                    + "v.id_apcica, sum(d.cantidad*d.precio) as subtotal, (select sum(d.cantidad*d.precio) \n"
                    + "as total from detventas d inner join ventas v on d.id_venta=v.id_venta where v.id_apcica = ? ) \n"
                    + "FROM public.ventas v\n"
                    + "inner join detventas d on v.id_venta = d.id_venta\n"
                    + "where id_apcica = ? group by v.id_venta, v.nrofactura, v.fecha, v.idtipopag, v.id_timbrado, v.id_cliente, \n"
                    + "v.id_deposito, v.id_pedidoven, v.id_estado, v.id_usuario, v.idvendedor,\n"
                    + "v.id_apcica";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, codapertura1);
            preparedStatement.setInt(2, codapertura2);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                alltotalfacturado.add(new aperturacierrecajaDTO(
                        rs.getInt("total")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(aperturacierrecajaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alltotalfacturado);
    }

    @Override
    public String getlistatimbrado() {
        ResultSet rs;
        ArrayList<aperturacierrecajaDTO> alltimbrados = new ArrayList<>();

        try {

            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_timbrado, numero, inicio_fecha, final_fecha, vencimientos, \n"
                    + "       id_estado, id_usuario, fac_establecimiento, fac_caja, fac_desde, \n"
                    + "       fac_hasta\n"
                    + "  FROM public.timbrados\n"
                    + "  where id_timbrado = 7";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alltimbrados.add(new aperturacierrecajaDTO(
                        rs.getInt("id_timbrado"),
                        rs.getString("numero"),
                        rs.getString("inicio_fecha"),
                        rs.getString("vencimientos"),
                        rs.getString("fac_caja"),
                        rs.getString("fac_desde")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(aperturacierrecajaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alltimbrados);
    }

    @Override
    public String gettimbrado(Integer timbrado) {
         ResultSet rs;
        ArrayList<aperturacierrecajaDTO> alltimb = new ArrayList<>();

        try {

            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_timbrado, numero, inicio_fecha, final_fecha, vencimientos, \n"
                    + "       id_estado, id_usuario, fac_establecimiento, fac_caja, fac_desde, \n"
                    + "       fac_hasta\n"
                    + "  FROM public.timbrados\n"
                    + "  where id_timbrado = ?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
              preparedStatement.setInt(1, timbrado);
            rs = preparedStatement.executeQuery();
          
            while (rs.next()) {
                alltimb.add(new aperturacierrecajaDTO(
                        rs.getString("numero")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(aperturacierrecajaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alltimb);
    }

}
