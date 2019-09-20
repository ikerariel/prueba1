/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.TipochequesDAO;
import ModelDAOIMPL.TipochequesDAOIMPL;
import ModelDTO.TipochequesDTO;
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
@WebServlet(name = "TipochequesServlet", urlPatterns = {"/TipochequesServlet"})
public class TipochequesServlet extends HttpServlet {

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

        TipochequesDAO TICHEdao = new TipochequesDAOIMPL();

        TipochequesDTO TICHEdto = new TipochequesDTO();
        
        switch (opcion) {
            case 1:
                TICHEdto.setId_tipocheque(Integer.parseInt(request.getParameter("idtipocheque")));
                TICHEdto.setDescripcion(request.getParameter("descri"));
                if (TICHEdao.insertarTipocheques(TICHEdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 2:
                TICHEdto.setId_tipocheque(Integer.parseInt(request.getParameter("idtipocheque")));
                TICHEdto.setDescripcion(request.getParameter("descri"));
                if (TICHEdao.modificarTipocheques(TICHEdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 3:
                if (TICHEdao.eliminarTipocheques(Integer.parseInt(request.getParameter("idtipocheque")))) {
                    out.println("Exitoso");
                }
                break;
                
            case 4:
                out.println(TICHEdao.getmostrarTipocheques());
                break;
                
            case 5:
                System.out.println("codigo " + TICHEdao.getUltimoCodigoTipocheques());
                if (TICHEdao.getUltimoCodigoTipocheques()> 0) {
                    out.println(TICHEdao.getUltimoCodigoTipocheques());
                }
                break;
                
            case 6:
                
                if (TICHEdao.getmostrarTipochequesFiltro(Integer.parseInt(request.getParameter("idtipocheque"))) != null) {
                    out.println(TICHEdao.getmostrarTipochequesFiltro(Integer.parseInt(request.getParameter("idtipocheque"))));
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
