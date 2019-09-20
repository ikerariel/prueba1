
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDAO.sRecepcionDAO;
import ModelDTO.presupuestoDTO;
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
public class sRecepcionDAOIMPL implements sRecepcionDAO {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public boolean insertarRecepcion(sRecepcionDTO dto, Integer cod) {
        switch (cod) {
            case 1: //INSERTAR CABACERA
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "INSERT INTO public.recepciones(id_cliente, id_estado, id_usuario,observacion)\n"
                            + "    VALUES (?, 3, ?,?);";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getId_cliente());
                    preparedStatement.setObject(2, dto.getId_usuario());
                    preparedStatement.setObject(3, dto.getObservacion());
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
            case 2: //ACTUALIZARCABECERA
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "UPDATE public.recepciones\n"
                            + "   SET fecha=now(), id_cliente=?, id_estado=3, id_usuario=?, \n"
                            + "       observacion=?\n"
                            + " WHERE id_recepcion=?";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getId_cliente());
                    preparedStatement.setObject(2, dto.getId_usuario());
                    preparedStatement.setObject(3, dto.getObservacion());
                    preparedStatement.setObject(4, dto.getId_recepcion());
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
    public boolean insertarDetalleRecepcion(sRecepcionDTO dto, Integer sRecepcion) {
        switch (sRecepcion) {
            case 1:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "INSERT INTO public.detrecepciones(\n"
                            + "            id_recepcion, cantidad, id_articulo)\n"
                            + "    VALUES ((select id_recepcion from recepciones order by id_recepcion desc limit 1), ?, ?);";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getCantidad());
                    preparedStatement.setObject(2, dto.getId_articulo());
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
                    sintaxiSql = "INSERT INTO public.detrecepciones(\n"
                            + "            id_recepcion, cantidad, id_articulo)\n"
                            + "    VALUES (?, ?, ?);";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getId_recepcion());
                    preparedStatement.setObject(2, dto.getCantidad());
                    preparedStatement.setObject(3, dto.getId_articulo());
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
    public boolean deleteDetalleRecepcion(sRecepcionDTO dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM public.detrecepciones\n"
                    + " WHERE id_recepcion=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getId_recepcion());
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
        return false;
    }

    @Override
    public String getdetallesRecepcion(Integer codSrecpcion) {
        ResultSet rs;
        ArrayList<sRecepcionDTO> alldet = new ArrayList<>();

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT r.id_recepcion, r.fecha::date, r.id_cliente, r.id_estado, r.id_usuario, r.observacion,\n"
                    + " rd.id_articulo, a.art_descripcion, rd.cantidad, e.est_descripcion,\n"
                    + "c.ruc, c.razonsocial\n"
                    + "  FROM public.recepciones r\n"
                    + "  inner join detrecepciones rd on r.id_recepcion=rd.id_recepcion\n"
                    + "  inner join clientes c on r.id_cliente = c.id_cliente\n"
                    + "  inner join articulos a on rd. id_articulo=a.id_articulo \n"
                    + "  inner join estados e on r.id_estado=e.id_estado where r.id_recepcion = ?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, codSrecpcion);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alldet.add(new sRecepcionDTO(rs.getInt("id_recepcion"),
                        rs.getString("fecha"),
                        rs.getString("ruc"),
                        rs.getString("razonsocial"),
                        rs.getString("observacion"),
                        rs.getInt("id_articulo"),
                        rs.getString("art_descripcion"),
                        rs.getInt("id_cliente"),
                        rs.getInt("cantidad")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(sRecepcionDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alldet);
    }

    @Override
    public String getsRecepcion() {
        ResultSet rs;
        ArrayList<sRecepcionDTO> allrerecpcion = new ArrayList<>();

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT r.id_recepcion, r.fecha::date, r.id_cliente, r.id_estado, r.id_usuario, \n"
                    + " e.est_descripcion,u.usu_nombre,\n"
                    + "c.ruc, c.razonsocial\n"
                    + "  FROM public.recepciones r\n"
                    + "  inner join clientes c on r.id_cliente = c.id_cliente\n"
                    + "  inner join usuarios u on r.id_usuario = u.id_usuario\n"
                    + "  inner join estados e on r.id_estado=e.id_estado where r.id_estado in(1,3) order by r.id_recepcion desc";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allrerecpcion.add(new sRecepcionDTO(rs.getInt("id_recepcion"),
                        rs.getString("fecha"),
                        rs.getString("razonsocial"),
                        rs.getString("est_descripcion"),
                        rs.getString("usu_nombre"),
                        rs.getInt("id_estado")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(sRecepcionDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allrerecpcion);
    }

    @Override
    public boolean actualizarEstado(sRecepcionDTO dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE public.recepciones\n"
                    + "   SET  id_estado=?\n"
                    + " WHERE id_recepcion=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getId_estado());
            preparedStatement.setObject(2, dto.getId_recepcion());
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
        return false;
    }

    @Override
    public String CalcularDV(String p_numero, Integer p_basemax) {
        ResultSet rs;
        ArrayList<sRecepcionDTO> allrerecpcion = new ArrayList<>();

        Integer v_total, v_resto, k, v_numero_aux, v_digit;
        String v_numero_al = "";

        for (int i = 0; i < p_numero.length(); i++) {
            char c = p_numero.charAt(i);
            if (Character.isDigit(c)) {
                v_numero_al += c;
            } else {
                v_numero_al += (int) c;
            }
        }

        k = 2;
        v_total = 0;

        for (int i = v_numero_al.length() - 1; i >= 0; i--) {
            k = k > p_basemax ? 2 : k;
            v_numero_aux = v_numero_al.charAt(i) - 48;
            v_total += v_numero_aux * k++;
        }

        v_resto = v_total % 11;
        v_digit = v_resto > 1 ? 11 - v_resto : 0;

        return new Gson().toJson(v_digit);
    }

}
