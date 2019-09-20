/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genericos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.Query;
import static javax.management.Query.value;

/**
 *
 * @author user
 */
public class Genericos_combos {
    private ArrayList<registrosTabla> datos;
    private PreparedStatement ps;
    private Conexion conexion;
    ResultSet cursor;
    
    public Genericos_combos(String sql) {
        this.conexion = new Conexion();
        try {
            datos = new ArrayList<>();
            ps = conexion.getConexion().prepareStatement(sql);
            
            cursor= ps.executeQuery();
            while (cursor.next()) {
                
                datos.add(new registrosTabla(cursor.getInt(1), 
                            
                            cursor.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Genericos_combos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<registrosTabla> getDatosTabla() {
        return datos;
    }

    public Integer getCodigoActual(int indice) {
        if (indice != -1) {
            return datos.get(indice).getCodCombo();
        }
        return 0;
    }

    public class registrosTabla {

        private final Integer codCombo;
        private final String desCombo;

        public registrosTabla(Integer codCombo, String desCombo) {
            this.codCombo = codCombo;
            this.desCombo = desCombo;
        }

        public Integer getCodCombo() {
            return codCombo;
        }

        public String getDesCombo() {
            return desCombo;
        }
    }
    
}
