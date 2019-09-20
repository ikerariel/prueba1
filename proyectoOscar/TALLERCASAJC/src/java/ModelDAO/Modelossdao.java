package ModelDAO;

import ModelDTO.Modelossdto;

/**
 *
 * @author Oscar
 */
public interface Modelossdao {
    
    boolean insertarModelos(Modelossdto Dto);

    boolean modificarModelos(Modelossdto Dto);

    boolean eliminarModelos(Integer id);

    String getmostrarModelos();
    //String getClientesHTML() throws mierror;

    Integer getUltimoCodigoModelos();

    String getmostrarModelosFiltro(Integer idFiltro);

//    public boolean getperfiles();
//    public boolean modificarCiudades(Barriosdto BARdto);
    String listarMarcas();
    
    String listarColores();
    
}
