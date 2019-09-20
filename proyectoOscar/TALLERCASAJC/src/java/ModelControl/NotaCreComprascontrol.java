/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.NotaCreComprasdao;
import ModelDAOIMPL.NotaCreComprasdaoimpl;
import ModelDTO.NotaCreComprasdto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
@WebServlet(name = "NotaCreComprascontrol", urlPatterns = {"/NotaCreComprascontrol"})
public class NotaCreComprascontrol extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Integer opcion = Integer.parseInt(request.getParameter("opcion"));

        NotaCreComprasdao ncDAO = new NotaCreComprasdaoimpl();
        NotaCreComprasdto ncDTO = new NotaCreComprasdto();

        switch (opcion) {
            case 1:
                out.println(ncDAO.getNotaCreCompras());
                break;
            case 2:
                ncDTO.setNro_nocred(Integer.parseInt(request.getParameter("_nronocred")));
                ncDTO.setNro_timbrado(Integer.parseInt(request.getParameter("_nrotimbrado")));
                ncDTO.setObs_nocred(request.getParameter("_obsnocred"));
                ncDTO.setId_compra(Integer.parseInt(request.getParameter("_codcompra")));
                ncDTO.setId_usuario(Integer.parseInt(request.getParameter("_codusuario")));
                ncDTO.setId_estado(Integer.parseInt(request.getParameter("_codestado")));

                if (ncDAO.insertarNC(ncDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 3:
                System.out.println("codigo" + ncDAO.getUltimoCodigo());
                if (ncDAO.getUltimoCodigo() > 0) {
                    out.println(ncDAO.getUltimoCodigo());
                }
                break;
            case 4:
                ncDTO.setId_notacrecompra(Integer.parseInt(request.getParameter("DtNC_codigoNC")));
                ncDTO.setId_articulo(Integer.parseInt(request.getParameter("DtNC_codarticulo")));
                ncDTO.setCantidad_detnocre(Integer.parseInt(request.getParameter("DtNC_cantidaddetnocre")));
                ncDTO.setMontouni_detnocre(Integer.parseInt(request.getParameter("DtMC_montounidetnocre")));
                if (ncDAO.insertarDetalleNC(ncDTO)) {
                    out.println("Exitoso");
                }
                break;

            case 5:
                if (ncDAO.getDetNotaCreCompras(Integer.parseInt(request.getParameter("_nroNC"))) != null) {
                    out.println(ncDAO.getDetNotaCreCompras(Integer.parseInt(request.getParameter("_nroNC"))));
                    System.out.println(ncDAO.getDetNotaCreCompras(Integer.parseInt(request.getParameter("_nroNC"))));
                }
                break;

            case 6:
                if (ncDAO.getfactura(Integer.parseInt(request.getParameter("_nroFactura"))) != null) {
                    out.println(ncDAO.getfactura(Integer.parseInt(request.getParameter("_nroFactura"))));
                    System.out.println(ncDAO.getfactura(Integer.parseInt(request.getParameter("_nroFactura"))));
                }
                break;

            case 7:
                ncDAO.updateNC(Integer.parseInt(request.getParameter("_estado")),
                        Integer.parseInt(request.getParameter("_idNC")));
                break;

            case 8:
                ncDTO.setNro_nocred(Integer.parseInt(request.getParameter("_nronocred")));
                ncDTO.setNro_timbrado(Integer.parseInt(request.getParameter("_nrotimbrado")));
                ncDTO.setObs_nocred(request.getParameter("_obsnocred"));
                ncDTO.setId_compra(Integer.parseInt(request.getParameter("_codcompra")));
                ncDTO.setId_usuario(Integer.parseInt(request.getParameter("_codusuario")));
                ncDTO.setId_estado(Integer.parseInt(request.getParameter("_codestado")));
                ncDTO.setId_notacrecompra(Integer.parseInt(request.getParameter("_codNC")));

                if (ncDAO.updateCabeceraNC(ncDTO)) {
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
