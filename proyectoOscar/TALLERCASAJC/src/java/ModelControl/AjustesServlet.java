/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.AjustesDAO;
import ModelDAOIMPL.AjustesDAOIMPL;
import ModelDTO.AjustesDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oscar
 */
@WebServlet(name = "AjustesServlet", urlPatterns = {"/AjustesServlet"})
public class AjustesServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json, charset=UTF-8");
        PrintWriter out = response.getWriter();
        Integer opcion = Integer.parseInt(request.getParameter("opcion"));

        AjustesDAO ajDAO = new AjustesDAOIMPL();
        AjustesDTO ajDTO = new AjustesDTO();

        switch (opcion) {
            case 1:
                out.println(ajDAO.getTraerAjustes());
                break;
            case 2:

                System.out.println("codigo" + ajDAO.getUltimoCodigoAjustes());
                if (ajDAO.getUltimoCodigoAjustes() > 0) {
                    out.println(ajDAO.getUltimoCodigoAjustes());
                }
                break;
            case 3:
                out.println(ajDAO.getTipoAjustes());
                break;
            case 4:
                ajDTO.setId_sucursal(Integer.parseInt(request.getParameter("codsucursal")));
                ajDTO.setId_deposito(Integer.parseInt(request.getParameter("coddeposito")));
                ajDTO.setId_usuario(Integer.parseInt(request.getParameter("codusuario")));
                ajDTO.setObservacion(request.getParameter("observa"));
                ajDTO.setId_tipajuste(Integer.parseInt(request.getParameter("codtipoajuste")));
                if (ajDAO.insertarAjustes(ajDTO)) {

                }
                break;
            case 5:
                ajDTO.setId_articulo(Integer.parseInt(request.getParameter("codarticulo")));
                ajDTO.setId_ajuste(Integer.parseInt(request.getParameter("codajuste")));
                ajDTO.setCantexistencia(Integer.parseInt(request.getParameter("Cantexis")));
                if (ajDAO.insertarDetallesAjustes(ajDTO)) {

                }
                break;
            case 6:
                out.println(ajDAO.getAjustes(Integer.parseInt(request.getParameter("cod_ajustes"))));
                break;

            case 7:
                ajDTO.setId_estado(Integer.parseInt(request.getParameter("codestado")));
                ajDTO.setId_ajuste(Integer.parseInt(request.getParameter("codajuste")));
                if (ajDAO.aprobarAjustes(ajDTO)) {

                }
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
