/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.sDiagnosticoDAO;
import ModelDAOIMPL.sDiagnosticoDAOIMPL;
import ModelDTO.sDiagnosticoDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class sDiagnosticoSERVLET extends HttpServlet {

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

        sDiagnosticoDAO sdDAO = new sDiagnosticoDAOIMPL();
        sDiagnosticoDTO sdDTO = new sDiagnosticoDTO();

        switch (opcion) {
            case 1:

                out.println(sdDAO.getsDiagnostico());

                break;

            case 2:
                Integer sCod = Integer.parseInt(request.getParameter("sValor"));
//                if (sCod == 1) {
//                    sdDTO.setId_recepcion(Integer.parseInt(request.getParameter("sCliente")));
//                    sdDTO.setId_usuario(Integer.parseInt(request.getParameter("sUsuario")));
//                    sdDTO.setDiganostico(request.getParameter("sObserv"));
//                } else if (sCod == 2) {
//                    sdDTO.setId_cliente(Integer.parseInt(request.getParameter("sCliente")));
//                    sdDTO.setId_usuario(Integer.parseInt(request.getParameter("sUsuario")));
//                    sdDTO.setObservacion(request.getParameter("sObserv"));
//                    sdDTO.setId_recepcion(Integer.parseInt(request.getParameter("sCodrecp")));
//                }
//                if (sdDAO.insertarDiagnostico(sdDTO, sCod)) {
//                    out.println("Exitoso");
//                }
                break;

        }

//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet sDiagnosticoSERVLET</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet sDiagnosticoSERVLET at " + request.getContextPath() + "</h1>");
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
