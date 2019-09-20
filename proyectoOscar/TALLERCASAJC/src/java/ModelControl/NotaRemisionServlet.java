/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.NotaRemisionDAO;
import ModelDAOIMPL.NotaRemisionDAOIMPL;
import ModelDTO.NotaRemisionDTO;
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
@WebServlet(name = "NotaRemisionServlet", urlPatterns = {"/NotaRemisionServlet"})
public class NotaRemisionServlet extends HttpServlet {

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
        
        NotaRemisionDAO nrDAO = new NotaRemisionDAOIMPL();
        NotaRemisionDTO nrDTO = new NotaRemisionDTO();
        
        switch (opcion) {
            case 1:
                out.println(nrDAO.getNotaRemision());
                break;
            case 2:
                nrDTO.setNro_notaremi(Integer.parseInt(request.getParameter("_nronotaremi")));
                nrDTO.setObser_notaremi(request.getParameter("_observ"));
                nrDTO.setId_estado(Integer.parseInt(request.getParameter("_codestado")));
                nrDTO.setId_usuario(Integer.parseInt(request.getParameter("_codusuario")));
                nrDTO.setId_compra(Integer.parseInt(request.getParameter("_codcompra")));
                if (nrDAO.insertarNotaRemision(nrDTO)) {
                    out.println("Exitoso");
                }
                break;

            case 3:
                System.out.println("codigo" + nrDAO.getUltimoCodigoNotaRemision());
                if (nrDAO.getUltimoCodigoNotaRemision() > 0) {
                    out.println(nrDAO.getUltimoCodigoNotaRemision());
                }
                break;

            case 4:
                nrDTO.setId_notaremi(Integer.parseInt(request.getParameter("NR_codigoD")));
                nrDTO.setId_articulo(Integer.parseInt(request.getParameter("NR_articulo")));
                nrDTO.setCantinotaremi(Integer.parseInt(request.getParameter("NR_cantidad")));
                nrDTO.setPrecionotaremi(Integer.parseInt(request.getParameter("NR_precio")));
                if (nrDAO.insertarDetalleNotaRemision(nrDTO)) {
                    out.println("Exitoso");
                }

                break;

            case 5:
                if (nrDAO.getDetalleNotaRemision(Integer.parseInt(request.getParameter("_nroNR"))) != null) {
                    out.println(nrDAO.getDetalleNotaRemision(Integer.parseInt(request.getParameter("_nroNR"))));
                    System.out.println(nrDAO.getDetalleNotaRemision(Integer.parseInt(request.getParameter("_nroNR"))));
                }
                break;

            case 6:
                if (nrDAO.getfacturas(Integer.parseInt(request.getParameter("_nroFacturas"))) != null) {
                    out.println(nrDAO.getfacturas(Integer.parseInt(request.getParameter("_nroFacturas"))));
                    System.out.println(nrDAO.getfacturas(Integer.parseInt(request.getParameter("_nroFacturas"))));
                }
                break;

            case 7:
                nrDAO.updateNotaRemision(Integer.parseInt(request.getParameter("_estado")),
                        Integer.parseInt(request.getParameter("_idNR")));
                break;

            case 8:
                nrDTO.setNro_notaremi(Integer.parseInt(request.getParameter("_nronotaremi")));
                nrDTO.setObser_notaremi(request.getParameter("_observ"));
                nrDTO.setId_usuario(Integer.parseInt(request.getParameter("_codusuario")));
                nrDTO.setId_compra(Integer.parseInt(request.getParameter("_codcompra")));
                nrDTO.setId_notaremi(Integer.parseInt(request.getParameter("_codNR")));

                if (nrDAO.updateCabeceraNotaRemision(nrDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 9:
                if (nrDAO.eliminarDetalleNotaRemision(Integer.parseInt(request.getParameter("nroNR")))) {
                    out.println("Exitoso");
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
