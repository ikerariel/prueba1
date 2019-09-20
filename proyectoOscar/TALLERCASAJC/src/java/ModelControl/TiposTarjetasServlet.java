/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.TiposTarjetasdao;
import ModelDAOIMPL.TiposTarjetasdaoimpl;
import ModelDTO.TiposTarjetasdto;
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
@WebServlet(name = "TiposTarjetasServlet", urlPatterns = {"/TiposTarjetasServlet"})
public class TiposTarjetasServlet extends HttpServlet {

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

        TiposTarjetasdao TITARdao = new TiposTarjetasdaoimpl();

        TiposTarjetasdto TITARdto = new TiposTarjetasdto();
        
        switch (opcion) {
            case 1:
                TITARdto.setId_tipotarjeta(Integer.parseInt(request.getParameter("idtipotarjeta")));
                TITARdto.setTarj_tipo(request.getParameter("tarjtipo"));
                if (TITARdao.insertarTiposTarjetas(TITARdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 2:
                TITARdto.setId_tipotarjeta(Integer.parseInt(request.getParameter("idtipotarjeta")));
                TITARdto.setTarj_tipo(request.getParameter("tarjtipo"));
                if (TITARdao.modificarTiposTarjetas(TITARdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 3:
                if (TITARdao.eliminarTiposTarjetas(Integer.parseInt(request.getParameter("idtipotarjeta")))) {
                    out.println("Exitoso");
                }
                break;
                
            case 4:
                out.println(TITARdao.getmostrarTiposTarjetas());
                break;
                
            case 5:
                System.out.println("codigo " + TITARdao.getUltimoCodigoTiposTarjetas());
                if (TITARdao.getUltimoCodigoTiposTarjetas()> 0) {
                    out.println(TITARdao.getUltimoCodigoTiposTarjetas());
                }
                break;
                
            case 6:
                
                if (TITARdao.getmostrarTiposTarjetasFiltro(Integer.parseInt(request.getParameter("idtipotarjeta"))) != null) {
                    out.println(TITARdao.getmostrarTiposTarjetasFiltro(Integer.parseInt(request.getParameter("idtipotarjeta"))));
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
