/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.sRecepcionDAO;
import ModelDAOIMPL.sRecepcionDAOIMPL;
import ModelDTO.sRecepcionDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oscar
 */
public class sRecepcionSERVLET extends HttpServlet {

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
        
        
        sRecepcionDAO rDAO = new sRecepcionDAOIMPL();
        sRecepcionDTO rDTO = new sRecepcionDTO();

        switch (opcion) {
            case 1:
                Integer sCod = Integer.parseInt(request.getParameter("sValor"));
                if (sCod == 1) {
                    rDTO.setId_cliente(Integer.parseInt(request.getParameter("sCliente")));
                    rDTO.setId_usuario(Integer.parseInt(request.getParameter("sUsuario")));
                    rDTO.setObservacion(request.getParameter("sObserv"));
                } else if (sCod == 2) {
                    rDTO.setId_cliente(Integer.parseInt(request.getParameter("sCliente")));
                    rDTO.setId_usuario(Integer.parseInt(request.getParameter("sUsuario")));
                    rDTO.setObservacion(request.getParameter("sObserv"));
                    rDTO.setId_recepcion(Integer.parseInt(request.getParameter("sCodrecp")));
                }
                if (rDAO.insertarRecepcion(rDTO, sCod)) {
                    out.println("Exitoso");
                }

                break;

            case 2:
                Integer sVr = Integer.parseInt(request.getParameter("opDetalle"));
                if (sVr == 1) {
                    rDTO.setCantidad(Integer.parseInt(request.getParameter("sCant")));
                    rDTO.setId_articulo(Integer.parseInt(request.getParameter("sCodartic")));
                } else if (sVr == 2) {
                    rDTO.setId_recepcion(Integer.parseInt(request.getParameter("sNrorecpe")));
                    rDTO.setCantidad(Integer.parseInt(request.getParameter("sCant")));
                    rDTO.setId_articulo(Integer.parseInt(request.getParameter("sCodartic")));
                }

                if (rDAO.insertarDetalleRecepcion(rDTO,sVr)) {
                    out.println("Exitoso");
                }
                break;
            case 3:
                rDTO.setId_recepcion(Integer.parseInt(request.getParameter("sCodrecp")));
                if (rDAO.deleteDetalleRecepcion(rDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 4:
                out.println(rDAO.getsRecepcion());
                break;
            case 5:
                out.println(rDAO.getdetallesRecepcion(Integer.parseInt(request.getParameter("sNroRecepcion"))));
                break;
            case 6:
                rDTO.setId_estado(Integer.parseInt(request.getParameter("sEstado")));
                rDTO.setId_recepcion(Integer.parseInt(request.getParameter("sRececp")));
                if (rDAO.actualizarEstado(rDTO)) {
                    out.println("Exitoso");
                }
                break;
                 case 7:
                out.println(rDAO.CalcularDV(request.getParameter("nroci"),Integer.parseInt(request.getParameter("basemax"))));
                break;
        }
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet sRecepcionSERVLET</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet sRecepcionSERVLET at " + request.getContextPath() + "</h1>");
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
