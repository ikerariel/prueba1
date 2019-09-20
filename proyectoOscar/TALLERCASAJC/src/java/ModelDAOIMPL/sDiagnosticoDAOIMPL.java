
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDAO.sDiagnosticoDAO;
import ModelDTO.sDiagnosticoDTO;
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
public class sDiagnosticoDAOIMPL implements sDiagnosticoDAO {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public String getsDiagnostico() {
        ResultSet rs;
        ArrayList<sDiagnosticoDTO> alldiagnostico = new ArrayList<>();

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT d.id_diagnostico, d.fecha::date, r.id_cliente, d.id_estado, d.id_usuario, \n"
                    + "  e.est_descripcion,u.usu_nombre,d.diagnostico,\n"
                    + "  c.ruc, c.razonsocial\n"
                    + " FROM public.diagnosticos d\n"
                    + "	INNER JOIN recepciones r ON d.id_recepcion = r.id_recepcion\n"
                    + " inner join clientes c on r.id_cliente = c.id_cliente\n"
                    + " inner join usuarios u on r.id_usuario = u.id_usuario\n"
                    + " inner join estados e on d.id_estado=e.id_estado \n"
                    + "	 where r.id_estado in(1,3) order by r.id_recepcion desc";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alldiagnostico.add(new sDiagnosticoDTO(rs.getInt("id_diagnostico"),
                        rs.getString("fecha"),
                        rs.getString("razonsocial"),
                        rs.getString("est_descripcion"),
                        rs.getString("usu_nombre"),
                        rs.getInt("id_estado")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(sDiagnosticoDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alldiagnostico);
    }

    @Override
    public boolean insertarDiagnostico(sDiagnosticoDTO dto, Integer cod) {

        switch (cod) {
            case 1: //INSERTAR CABACERA
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "INSERT INTO public.diagnosticos(\n"
                            + "          fecentreg, id_recepcion, id_usuario, id_estado, \n"
                            + "            diagnostico)\n"
                            + "    VALUES (now(), ?, ?, 3, \n"
                            + "            ?);";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getId_recepcion());
                    preparedStatement.setObject(2, dto.getId_usuario());
                    preparedStatement.setObject(3, dto.getDiganostico());
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
                    Logger.getLogger(sDiagnosticoDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case 2: //ACTUALIZARCABECERA
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "UPDATE public.diagnosticos\n"
                            + "   SET fecha=now(), fecentreg=now(), id_recepcion=?, id_usuario=?, \n"
                            + "       id_estado=3, diagnostico=?\n"
                            + " WHERE id_diagnostico=?";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getId_recepcion());
                    preparedStatement.setObject(2, dto.getId_usuario());
                    preparedStatement.setObject(3, dto.getDiganostico());
                    preparedStatement.setObject(4, dto.getId_diagnostico());
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
                    Logger.getLogger(sDiagnosticoDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
        return false;

    }

    @Override
    public boolean insertarDetalleDiagnostico(sDiagnosticoDTO dto, Integer sRecepcion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteDetalleDiagnostico(sDiagnosticoDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean actualizarEstado(sDiagnosticoDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getdetallesDiagnostico(Integer codSrecpcion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
