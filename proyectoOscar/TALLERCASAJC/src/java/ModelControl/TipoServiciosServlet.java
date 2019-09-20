/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.TipoServiciosDAO;
import ModelDAOIMPL.TipoServiciosDAOIMPL;
import ModelDTO.TipoServiciosDTO;
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
@WebServlet(name = "TipoServiciosServlet", urlPatterns = {"/TipoServiciosServlet"})
public class TipoServiciosServlet extends HttpServlet {

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

        TipoServiciosDAO TISERdao = new TipoServiciosDAOIMPL();

        TipoServiciosDTO TISERdto = new TipoServiciosDTO();
        
        switch (opcion) {
            case 1:
                TISERdto.setId_tiposerv(Integer.parseInt(request.getParameter("idtiposervicio")));
                TISERdto.setDescripcion(request.getParameter("descri"));
                if (TISERdao.insertarTipoServicios(TISERdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 2:
                TISERdto.setId_tiposerv(Integer.parseInt(request.getParameter("idtiposervicio")));
                TISERdto.setDescripcion(request.getParameter("descri"));
                if (TISERdao.modificarTipoServicios(TISERdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 3:
                if (TISERdao.eliminarTipoServicios(Integer.parseInt(request.getParameter("idtiposervicio")))) {
                    out.println("Exitoso");
                }
                break;
                
            case 4:
                out.println(TISERdao.getmostrarTipoServicios());
                break;
                
            case 5:
                System.out.println("codigo " + TISERdao.getUltimoCodigoTipoServicios());
                if (TISERdao.getUltimoCodigoTipoServicios()> 0) {
                    out.println(TISERdao.getUltimoCodigoTipoServicios());
                }
                break;
                
            case 6:
                
                if (TISERdao.getmostrarTipoServiciosFiltro(Integer.parseInt(request.getParameter("idtiposervicio"))) != null) {
                    out.println(TISERdao.getmostrarTipoServiciosFiltro(Integer.parseInt(request.getParameter("idtiposervicio"))));
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
