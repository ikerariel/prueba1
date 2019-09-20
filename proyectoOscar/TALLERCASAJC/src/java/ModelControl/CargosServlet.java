/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.CargosDAO;
import ModelDAOIMPL.CargosDAOIMPL;
import ModelDTO.CargosDTO;
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
@WebServlet(name = "CargosServlet", urlPatterns = {"/CargosServlet"})
public class CargosServlet extends HttpServlet {

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

        CargosDAO CARdao = new CargosDAOIMPL();

        CargosDTO CARdto = new CargosDTO();
        
        switch (opcion) {
            case 1:
                CARdto.setIdcargo(Integer.parseInt(request.getParameter("idcargo")));
                CARdto.setDescripcion(request.getParameter("descri"));
                if (CARdao.insertarCargos(CARdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 2:
                CARdto.setIdcargo(Integer.parseInt(request.getParameter("idcargo")));
                CARdto.setDescripcion(request.getParameter("descri"));
                if (CARdao.modificarCargos(CARdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 3:
                if (CARdao.eliminarCargos(Integer.parseInt(request.getParameter("idcargo")))) {
                    out.println("Exitoso");
                }
                break;
                
            case 4:
                out.println(CARdao.getmostrarCargos());
                break;
                
            case 5:
                System.out.println("codigo " + CARdao.getUltimoCodigoCargos());
                if (CARdao.getUltimoCodigoCargos()> 0) {
                    out.println(CARdao.getUltimoCodigoCargos());
                }
                break;
                
            case 6:
                
                if (CARdao.getmostrarCargosFiltro(Integer.parseInt(request.getParameter("idcargo"))) != null) {
                    out.println(CARdao.getmostrarCargosFiltro(Integer.parseInt(request.getParameter("idcargo"))));
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
