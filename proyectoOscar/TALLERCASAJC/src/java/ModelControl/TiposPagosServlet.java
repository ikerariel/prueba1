/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.TiposPagosDAO;
import ModelDAOIMPL.TiposPagosDAOIMPL;
import ModelDTO.TiposPagosDTO;
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
@WebServlet(name = "TiposPagosServlet", urlPatterns = {"/TiposPagosServlet"})
public class TiposPagosServlet extends HttpServlet {

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

        TiposPagosDAO TIPAdao = new TiposPagosDAOIMPL();

        TiposPagosDTO TIPAdto = new TiposPagosDTO();
        
        switch (opcion) {
            case 1:
                TIPAdto.setIdtipopag(Integer.parseInt(request.getParameter("idtippag")));
                TIPAdto.setDescripcion(request.getParameter("descr"));
                if (TIPAdao.insertarTiposPagos(TIPAdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 2:
                TIPAdto.setIdtipopag(Integer.parseInt(request.getParameter("idtippag")));
                TIPAdto.setDescripcion(request.getParameter("descr"));
                if (TIPAdao.modificarTiposPagos(TIPAdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 3:
                if (TIPAdao.eliminarTiposPagos(Integer.parseInt(request.getParameter("idtippag")))) {
                    out.println("Exitoso");
                }
                break;
                
            case 4:
                out.println(TIPAdao.getmostrarTiposPagos());
                break;
                
            case 5:
                System.out.println("codigo " + TIPAdao.getUltimoCodigoTiposPagos());
                if (TIPAdao.getUltimoCodigoTiposPagos()> 0) {
                    out.println(TIPAdao.getUltimoCodigoTiposPagos());
                }
                break;
                
            case 6:
                
                if (TIPAdao.getmostrarTiposPagosFiltro(Integer.parseInt(request.getParameter("idtippag"))) != null) {
                    out.println(TIPAdao.getmostrarTiposPagosFiltro(Integer.parseInt(request.getParameter("idtippag"))));
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
