package ModelDAO;

import ModelDTO.Barriosdto;

/**
 *
 * @author user
 */
public interface Barriosdao {

    boolean insertarBarrios(Barriosdto Dto);

    boolean modificarBarrios(Barriosdto Dto);

    boolean eliminarBarrios(Integer id);

    String getmostrarBarrios();
    //String getClientesHTML() throws mierror;

    Integer getUltimoCodigoBarrios();

    String getmostrarBarriosFiltro(Integer idFiltro);

//    public boolean getperfiles();
//    public boolean modificarCiudades(Barriosdto BARdto);
    String listarCiudades();
}
