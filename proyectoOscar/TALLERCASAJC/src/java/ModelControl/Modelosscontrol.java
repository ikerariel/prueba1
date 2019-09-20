/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAOIMPL.Modelossdaoimpl;
import ModelDTO.Modelossdto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ModelDAO.Modelossdao;

/**
 *
 * @author Oscar
 */
@WebServlet(name = "Modeloscontrol", urlPatterns = {"/Modeloscontrol"})
public class Modelosscontrol extends HttpServlet {

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

        Modelossdao MOdao = new Modelossdaoimpl();

        Modelossdto MOdto = new Modelossdto();

        switch (opcion) {
            case 1:
                MOdto.setId_modelo(Integer.parseInt(request.getParameter("codmodelo")));
                MOdto.setMod_descripcion(request.getParameter("modelodescri"));
                MOdto.setId_marca(Integer.parseInt(request.getParameter("codmarca")));
                MOdto.setId_color(Integer.parseInt(request.getParameter("codcolor")));
                if (MOdao.insertarModelos(MOdto)) {
                    out.println("Exitoso");
                }
                break;

            case 2:
                MOdto.setId_modelo(Integer.parseInt(request.getParameter("codmodelo")));
                MOdto.setMod_descripcion(request.getParameter("modelodescri"));
                MOdto.setId_marca(Integer.parseInt(request.getParameter("codmarca")));
                MOdto.setId_color(Integer.parseInt(request.getParameter("codcolor")));
                if (MOdao.modificarModelos(MOdto)) {
                    out.println("Exitoso");
                }
                break;

            case 3:
                if (MOdao.eliminarModelos(Integer.parseInt(request.getParameter("codmodelo")))) {
                    out.println("Exitoso");
                }
                break;

            case 4:
                out.println(MOdao.getmostrarModelos());
                break;

            case 5:
                System.out.println("codigo" + MOdao.getUltimoCodigoModelos());
                if (MOdao.getUltimoCodigoModelos() > 0) {
                    out.println(MOdao.getUltimoCodigoModelos());
                }
                break;

            case 6:

                if (MOdao.getmostrarModelosFiltro(Integer.parseInt(request.getParameter("codmodelo"))) != null) {
                    out.println(MOdao.getmostrarModelosFiltro(Integer.parseInt(request.getParameter("codmodelo"))));
                }
                break;

            case 7:
                out.println(MOdao.listarMarcas());
                break;

            case 8:
                out.println(MOdao.listarColores());
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
