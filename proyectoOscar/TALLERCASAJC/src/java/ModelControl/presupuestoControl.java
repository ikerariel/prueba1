/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.presupuestoDAO;
import ModelDAOIMPL.presupuestoDAOIMPL;
import ModelDTO.presupuestoDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
@WebServlet(name = "presupuestoControl", urlPatterns = {"/presupuestoControl"})
public class presupuestoControl extends HttpServlet {

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

        presupuestoDAO pDAO = new presupuestoDAOIMPL();
        presupuestoDTO pDTO = new presupuestoDTO();

        switch (opcion) {
            case 1:
                Integer vcaso = Integer.parseInt(request.getParameter("vcaso"));
                if (vcaso == 1) {
                    pDTO.setId_usuario(Integer.parseInt(request.getParameter("v_codusuario")));
                    pDTO.setId_proveedor(Integer.parseInt(request.getParameter("v_codproveedor")));
                    pDTO.setId_deposito(Integer.parseInt(request.getParameter("v_coddeposito")));
                    pDTO.setIdtipomoneda(Integer.parseInt(request.getParameter("v_codtipomoneda")));
                } else if (vcaso == 2) {
                    pDTO.setId_usuario(Integer.parseInt(request.getParameter("v_codusuario")));
                    pDTO.setId_proveedor(Integer.parseInt(request.getParameter("v_codproveedor")));
                    pDTO.setId_deposito(Integer.parseInt(request.getParameter("v_coddeposito")));
                    pDTO.setIdtipomoneda(Integer.parseInt(request.getParameter("v_codtipomoneda")));
                    pDTO.setId_presucompra(Integer.parseInt(request.getParameter("v_codpresupuesto")));
                }

                if (pDAO.insertarpresupuesto(pDTO, vcaso)) {
                    System.out.println("Mensaje del Servler...Insert Exitoso");
                } else {
                    System.out.println("Mensaje del Servler...Insert Error");
                }

                break;
            case 2:
                pDTO.setId_articulo(Integer.parseInt(request.getParameter("v_codarticulo")));
                pDTO.setCantidad(Integer.parseInt(request.getParameter("v_cantidad")));
                pDTO.setPreciounitario(Integer.parseInt(request.getParameter("v_preciounitario")));
                pDTO.setId_presucompra(Integer.parseInt(request.getParameter("v_codpresupuesto")));

                if (pDAO.insertardetallepresupuesto(pDTO)) {
                    System.out.println("Mensaje del Servler...Insert Exitoso");
                } else {
                    System.out.println("Mensaje del Servler...Insert Error");
                }

                break;

            case 3:
                out.println(pDAO.getproveedor());
                break;
            case 4:
                out.println(pDAO.gettipomoneda());
                break;
            case 5:
                out.println(pDAO.getpresupuesto());
                break;
            case 6:
                out.println(pDAO.getdetallepresupuesto(Integer.parseInt(request.getParameter("nropresupuesto"))));
                break;
            case 7:
                pDTO.setId_presucompra(Integer.parseInt(request.getParameter("v_codpresupuesto")));
                if (pDAO.deletedealle(pDTO)) {
                    System.out.println("Mensaje del Servler...Insert Exitoso");
                } else {
                    System.out.println("Mensaje del Servler...Insert Error");
                }

                break;
            case 8:
                pDTO.setId_estado(Integer.parseInt(request.getParameter("v_estado")));
                pDTO.setId_presucompra(Integer.parseInt(request.getParameter("v_presupuesto")));
                  if (pDAO.actualizarestado(pDTO)) {
                    System.out.println("Mensaje del Servler...Insert Exitoso");
                } else {
                    System.out.println("Mensaje del Servler...Insert Error");
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
