/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import Genericos.Conexion;
import ModelDTO.accesoDTO;
import ModelDAOIMPL.accesoDAOIMPL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface accesoDAO {
 
  boolean accesoUsuario(accesoDTO accesoDTO);
    String accesoUsuario(String user);

}
