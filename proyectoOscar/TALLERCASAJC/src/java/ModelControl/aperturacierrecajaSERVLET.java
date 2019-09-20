/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.aperturacierrecajaDAO;
import ModelDAOIMPL.aperturacierrecajaDAOIMPL;
import ModelDTO.aperturacierrecajaDTO;
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
@WebServlet(name = "aperturacierrecajaSERVLET", urlPatterns = {"/aperturacierrecajaSERVLET"})
public class aperturacierrecajaSERVLET extends HttpServlet {

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
        response.setContentType("application/json,charset=UTF-8");

        PrintWriter out = response.getWriter();
        Integer opcion = Integer.parseInt(request.getParameter("opcion"));

        aperturacierrecajaDAO apDAO = new aperturacierrecajaDAOIMPL();
        aperturacierrecajaDTO apDTO = new aperturacierrecajaDTO();

        switch (opcion) {
            case 1:
                out.println(apDAO.getaperturascierres());
                break;
            case 2:
//                out.println(apDAO.getaperturascierres());
                if (apDAO.facturacion(request.getParameter("logueo")) != null) {
                    out.println(apDAO.facturacion(request.getParameter("logueo")));
                    System.out.println("Mensaje del Servler...recuperacion de filtro Exitoso OK");

                    break;
                } else {
                    System.out.println("Mensaje del Servler...Error al recuperar filtro");
                }

            case 3:
                apDTO.setApcica_apermonto(Integer.parseInt(request.getParameter("montoapertura")));
                apDTO.setId_caja(Integer.parseInt(request.getParameter("codigocaja")));
                apDTO.setId_sucursal(Integer.parseInt(request.getParameter("codigosucursal")));
                apDTO.setId_deposito(Integer.parseInt(request.getParameter("codigodeposito")));
                apDTO.setIdsupervisor(Integer.parseInt(request.getParameter("codigosupervisor")));
                apDTO.setIdcajero(Integer.parseInt(request.getParameter("codigocajero")));
                apDTO.setId_timbrado(Integer.parseInt(request.getParameter("codigotimbrado")));
                out.println(apDTO);
                if (apDAO.insertarApertura(apDTO)) {
                    out.println("Exitoso");
                }

                break;
            case 4:
                apDTO.setCantimoneda(Integer.parseInt(request.getParameter("cantmoneda_ap")));
                apDTO.setMontounitario(Integer.parseInt(request.getParameter("montouni_ap")));
                apDTO.setId_apcica(Integer.parseInt(request.getParameter("codiapertura_ap")));
                apDTO.setIddenominacionmoneda(Integer.parseInt(request.getParameter("denominacionmoneda_ap")));
                apDTO.setNrocheque(Integer.parseInt(request.getParameter("nrochque_ap")));
                if (apDAO.insertarmovimientoarqueo(apDTO)) {
                    out.println("Exitoso");
                }

                break;

            case 5:
                if (apDAO.facturado(Integer.parseInt(request.getParameter("totalfacturado_fact1")), Integer.parseInt(request.getParameter("totalfacturado_fact2"))) != null) {
                    out.println(apDAO.facturado(Integer.parseInt(request.getParameter("totalfacturado_fact1")), Integer.parseInt(request.getParameter("totalfacturado_fact2"))));
                    System.out.println("Mensaje del Servler...recuperacion de filtro Exitoso OK");

                    break;
                } else {
                    System.out.println("Mensaje del Servler...Error al recuperar filtro");
                }
            case 6:
                out.println(apDAO.getlistatimbrado());
                break;
            case 7:
//                out.println(apDAO.getaperturascierres());
                if (apDAO.gettimbrado(Integer.parseInt(request.getParameter("codtimbrado"))) != null) {
                    out.println(apDAO.gettimbrado(Integer.parseInt(request.getParameter("codtimbrado"))));
                    System.out.println("Mensaje del Servler...recuperacion de filtro Exitoso OK");

                    break;
                } else {
                    System.out.println("Mensaje del Servler...Error al recuperar filtro");
                }
        }

//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet aperturacierrecajaSERVLET</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet aperturacierrecajaSERVLET at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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
