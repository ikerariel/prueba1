/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAOIMPL.notaDebitoDAOIMPLE;
import ModelDTO.notaDebitoDTO;
import ModelDAO.notaDebitoDAO;
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
@WebServlet(name = "notaDebitocontrol", urlPatterns = {"/notaDebitocontrol"})
public class notaDebitocontrol extends HttpServlet {

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

        notaDebitoDAO ndDAO = new notaDebitoDAOIMPLE();
        notaDebitoDTO ndDTO = new notaDebitoDTO();

        switch (opcion) {
            case 1:
                out.println(ndDAO.getNotasDebitos());
                break;
            case 2:
                ndDTO.setNro_notadebito(Integer.parseInt(request.getParameter("_nrodebito")));
                ndDTO.setNro_timbradonotadebito(Integer.parseInt(request.getParameter("_nrotimbradoDebito")));
                ndDTO.setId_compra(Integer.parseInt(request.getParameter("_codcompra")));
                ndDTO.setId_estado(Integer.parseInt(request.getParameter("_codestado")));
                ndDTO.setIdusuario(Integer.parseInt(request.getParameter("_codusuario")));

                if (ndDAO.insertarND(ndDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 3:
                System.out.println("codigo" + ndDAO.getUltimoCodigo());
                if (ndDAO.getUltimoCodigo() > 0) {
                    out.println(ndDAO.getUltimoCodigo());
                }
                break;
            case 4:
                ndDTO.setId_notadecompra(Integer.parseInt(request.getParameter("ND_codigoD")));
                ndDTO.setImporte(Integer.parseInt(request.getParameter("ND_importe")));
                ndDTO.setConceptos(request.getParameter("NT_comentario"));

                if (ndDAO.insertarDetalleND(ndDTO)) {
                    out.println("Exitoso");
                }
                break;

            case 5:
                if (ndDAO.getDetalleDebtio(Integer.parseInt(request.getParameter("_nroND"))) != null) {
                    out.println(ndDAO.getDetalleDebtio(Integer.parseInt(request.getParameter("_nroND"))));
                    System.out.println(ndDAO.getDetalleDebtio(Integer.parseInt(request.getParameter("_nroND"))));
                }
                break;

            case 6:
                if (ndDAO.getfactura(Integer.parseInt(request.getParameter("_nroFactura"))) != null) {
                    out.println(ndDAO.getfactura(Integer.parseInt(request.getParameter("_nroFactura"))));
                    System.out.println(ndDAO.getfactura(Integer.parseInt(request.getParameter("_nroFactura"))));
                }
                break;

            case 7:
                ndDAO.updateND(Integer.parseInt(request.getParameter("_estado")),
                        Integer.parseInt(request.getParameter("_idND")));
                break;

            case 8:
                ndDTO.setNro_notadebito(Integer.parseInt(request.getParameter("_nrodebito")));
                ndDTO.setNro_timbradonotadebito(Integer.parseInt(request.getParameter("_nrotimbradoDebito")));
                ndDTO.setId_compra(Integer.parseInt(request.getParameter("_codcompra")));
                ndDTO.setIdusuario(Integer.parseInt(request.getParameter("_codusuario")));
                ndDTO.setId_notadecompra(Integer.parseInt(request.getParameter("_codND")));

                if (ndDAO.updateCabeceraND(ndDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 9:
              if (ndDAO.eliminarDetalle(Integer.parseInt(request.getParameter("nroND")))) {
                    out.println("Exitoso");
                }
                break;
        }

//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet notaDebitocontrol</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet notaDebitocontrol at " + request.getContextPath() + "</h1>");
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
