/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class buscadordaoimple {
    private String sintaxiSql;
    private PreparedStatement ps;
    private ResultSet rs;
    private Conexion conexion;
    
    public String listarCaja(){
        String html = "";
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idcaja, caja_descripcion FROM cajas ORDER BY idcaja;";
            ps = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = ps.executeQuery();
            html += ("<table class=\"table\" id=\"tabAllCaja\">");
            html += ("<thead>");
            html += ("<tr>");
            html += ("<th>CODIGO</th>");
            html += ("<th>NOMBRE</th>");
            html += ("</tr>");
            html += ("</thead>");
            html += ("<tbody class=\"buscar\">");
            while (rs.next()) {
                html += ("<tr>");
                html += ("        <th>" + rs.getInt("idcaja") + "</th>");
                html += ("        <th>" + rs.getString("caja_descripcion") + "</th>");
                html += ("</tr>");
            }
            html += ("</tbody>");
            html += ("</table>");
        } catch (SQLException ex) {
            Logger.getLogger(buscadordaoimple.class.getName()).log(Level.SEVERE, null, ex);  
        }
        return html;
    }
    
}
