/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import ModelDTO.accesoDTO;
import ModelDAO.accesoDAO;
import Genericos.Conexion;
import ModelDTO.Empleadosdto;
import static java.lang.System.out;
import java.sql.PreparedStatement;
import com.google.gson.Gson;
import static java.lang.System.out;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.CallableStatement;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class accesoDAOIMPL implements accesoDAO {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;

    @Override
    public String accesoUsuario(String user) {
        ResultSet rs;
        ArrayList<accesoDTO> alluser = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT u.id_usuario, u.usu_nombre, u.usu_clave, u.id_empleado, u.id_sucursal, \n" +
"                           u.id_deposito, s.suc_descripcion,u.id_perfil, d.dep_descripcion, p.idsupervisor,v.idvendedor\n" +
"                     FROM public.usuarios u		   \n" +
"                       left join supervisor p on u.id_usuario=p.id_usuario\n" +
"                   left join depositos d on u.id_deposito = d.id_deposito\n" +
"                   left join sucursales s on d.id_sucursal = s.id_sucursal \n" +
"		   left join empleados em on u.id_empleado=em.id_empleado\n" +
"		   left join vendedor v on em.id_empleado=v.id_empleado 	\n" +
"                         where usu_nombre= ?  and u.id_estado=7";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setString(1, user);

            rs = preparedStatement.executeQuery();
            if (rs.next()) {

                alluser.add(new accesoDTO(
                        rs.getInt("id_usuario"),
                        rs.getString("usu_nombre"),
                        rs.getInt("id_sucursal"),
                        rs.getInt("id_deposito"),
                        rs.getString("suc_descripcion"),
                        rs.getString("dep_descripcion"),
                        rs.getInt("id_perfil"),
                        rs.getInt("idvendedor"),
                        rs.getInt("idsupervisor")));
            }
            conexion.desConectarBD();
        } catch (SQLException ex) {
            Logger.getLogger(accesoDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alluser);
    }

    @Override
    public boolean accesoUsuario(accesoDTO accesoDTO) {

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT u.id_usuario, u.usu_nombre, u.usu_clave, u.id_empleado, u.id_sucursal, \n" +
"                             u.id_deposito, s.suc_descripcion, d.dep_descripcion\n" +
"                      FROM public.usuarios u\n" +
"                    inner join depositos d on u.id_deposito = d.id_deposito\n" +
"                    inner join sucursales s on d.id_sucursal = s.id_sucursal\n" +
"                    where usu_nombre= ? and usu_clave=md5(?) and u.id_estado=7";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, accesoDTO.getUsu_nombre());
            preparedStatement.setObject(2, accesoDTO.getUsu_clave());

            resultado = preparedStatement.executeQuery();
            if (resultado.next()) {

                return true;
            }
            conexion.desConectarBD();
        } catch (SQLException ex) {
            Logger.getLogger(accesoDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
  
    }

}
