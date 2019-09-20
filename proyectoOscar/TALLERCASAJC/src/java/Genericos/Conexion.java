/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genericos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class Conexion {
    
    private Connection Conexion = null;
    private String miError;
    private final String ControladorBD = "org.postgresql.Driver";
    //private final String ControladorBD = "com.mysql.jdbc.Driver";
    
    private final String baseDatos = "oscar";
//    private final String baseDatos = "bd_casajctesiss";
    private final String usuario = "postgres";
//    private final String password = "123456";
    private final String password = "carlos123";


    private ResultSet ResultSet;
    public boolean conectado = false;
    
    //constructor que recibe los parametros para conectarse a la base de datos
    public Conexion() {
        String url = "jdbc:postgresql://localhost:5432/" + baseDatos;
        // String url = "jdbc:mysql://localhost/" + baseDatos;
        try {
            Class.forName(ControladorBD);
            Conexion = DriverManager.getConnection(url, usuario, password);
            conectado = true;
            Conexion.setAutoCommit(false);
            //JOptionPane.showMessageDialog(null, "Conexion Ok", "AVISO", JOptionPane.INFORMATION_MESSAGE);
        } catch (ClassNotFoundException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            JOptionPane.showMessageDialog(null, "Conexion Fallida", "AVISO", JOptionPane.INFORMATION_MESSAGE);
            conectado = false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Conexion Fallida", "AVISO", JOptionPane.INFORMATION_MESSAGE);
            conectado = false;
        }
    }
    
    public Connection getConexion() {
        return Conexion;
    }

    public void desConectarBD() {
        try {
            Conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void comit() {
        try {
            Conexion.commit();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void rollback() {
        try {
            Conexion.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
