/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.Barriosdao;
import ModelDAOIMPL.Barriosdaoimpl;
import ModelDTO.Barriosdto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
@WebServlet(name = "Barrioscontrol", urlPatterns = {"/Barrioscontrol"})
public class Barrioscontrol extends HttpServlet {

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
        
        Barriosdao BARdao = new Barriosdaoimpl();
        
        Barriosdto BARdto = new Barriosdto();
        
        switch (opcion) {
            case 1:
                BARdto.setId_barrio(Integer.parseInt(request.getParameter("codbarrio")));
                BARdto.setBarr_descripcion(request.getParameter("barriodescri"));
                BARdto.setId_ciudad(Integer.parseInt(request.getParameter("codciudad")));
                if (BARdao.insertarBarrios(BARdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 2:
                BARdto.setId_barrio(Integer.parseInt(request.getParameter("codbarrio")));
                BARdto.setBarr_descripcion(request.getParameter("barriodescri"));
                BARdto.setId_ciudad(Integer.parseInt(request.getParameter("codciudad")));               
                if (BARdao.modificarBarrios(BARdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 3:
                if (BARdao.eliminarBarrios(Integer.parseInt(request.getParameter("codbarrio")))) {
                    out.println("Exitoso");
                }
                break;
                
            case 4:
                out.println(BARdao.getmostrarBarrios());
                break;
                
            case 5:
                System.out.println("codigo" + BARdao.getUltimoCodigoBarrios());
                if (BARdao.getUltimoCodigoBarrios()> 0) {
                    out.println(BARdao.getUltimoCodigoBarrios());
                }
                break;
                
            case 6:
                
                if (BARdao.getmostrarBarriosFiltro(Integer.parseInt(request.getParameter("codbarrio"))) != null) {
                    out.println(BARdao.getmostrarBarriosFiltro(Integer.parseInt(request.getParameter("codbarrio"))));
                }
                break;
                
            case 7:
                out.println(BARdao.listarCiudades());
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
