/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.Marcasdao;
import ModelDAOIMPL.Marcasdaoimpl;
import ModelDTO.Marcasdto;
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
@WebServlet(name = "Marcascontrol", urlPatterns = {"/Marcascontrol"})
public class Marcascontrol extends HttpServlet {

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

        Marcasdao MARdao = new Marcasdaoimpl();

        Marcasdto MARdto = new Marcasdto();
        
        switch (opcion) {
            case 1:
                MARdto.setId_marca(Integer.parseInt(request.getParameter("idmarca")));
                MARdto.setMar_descripcion(request.getParameter("descrimarca"));
                if (MARdao.insertarMarcas(MARdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 2:
                MARdto.setId_marca(Integer.parseInt(request.getParameter("idmarca")));
                MARdto.setMar_descripcion(request.getParameter("descrimarca"));
                if (MARdao.modificarMarcas(MARdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 3:
                if (MARdao.eliminarMarcas(Integer.parseInt(request.getParameter("idmarca")))) {
                    out.println("Exitoso");
                }
                break;
                
            case 4:
                out.println(MARdao.getmostrarMarcas());
                break;
                
            case 5:
                System.out.println("codigo " + MARdao.getUltimoCodigoMarcas());
                if (MARdao.getUltimoCodigoMarcas()> 0) {
                    out.println(MARdao.getUltimoCodigoMarcas());
                }
                break;
                
            case 6:
                
                if (MARdao.getmostrarMarcasFiltro(Integer.parseInt(request.getParameter("idmarca"))) != null) {
                    out.println(MARdao.getmostrarMarcasFiltro(Integer.parseInt(request.getParameter("idmarca"))));
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
