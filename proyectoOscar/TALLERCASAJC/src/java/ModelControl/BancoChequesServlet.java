/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.BancoChequesDAO;
import ModelDAOIMPL.BancoChequesDAOIMPL;
import ModelDTO.BancoChequesDTO;
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
@WebServlet(name = "BancoChequesServlet", urlPatterns = {"/BancoChequesServlet"})
public class BancoChequesServlet extends HttpServlet {

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

        BancoChequesDAO BACHEdao = new BancoChequesDAOIMPL();

        BancoChequesDTO BACHEdto = new BancoChequesDTO();
        
        switch (opcion) {
            case 1:
                BACHEdto.setId_bancocheque(Integer.parseInt(request.getParameter("idbancheque")));
                BACHEdto.setDescripcion(request.getParameter("descri"));
                if (BACHEdao.insertarBancoCheques(BACHEdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 2:
                BACHEdto.setId_bancocheque(Integer.parseInt(request.getParameter("idbancheque")));
                BACHEdto.setDescripcion(request.getParameter("descri"));
                if (BACHEdao.modificarBancoCheques(BACHEdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 3:
                if (BACHEdao.eliminarBancoCheques(Integer.parseInt(request.getParameter("idbancheque")))) {
                    out.println("Exitoso");
                }
                break;
                
            case 4:
                out.println(BACHEdao.getmostrarBancoCheques());
                break;
                
            case 5:
                System.out.println("codigo " + BACHEdao.getUltimoCodigoBancoCheques());
                if (BACHEdao.getUltimoCodigoBancoCheques()> 0) {
                    out.println(BACHEdao.getUltimoCodigoBancoCheques());
                }
                break;
                
            case 6:
                
                if (BACHEdao.getmostrarBancoChequesFiltro(Integer.parseInt(request.getParameter("idbancheque"))) != null) {
                    out.println(BACHEdao.getmostrarBancoChequesFiltro(Integer.parseInt(request.getParameter("idbancheque"))));
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
