/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.Sucursalesdao;
import ModelDAOIMPL.Sucursalesdaoimpl;
import ModelDTO.Sucursalesdto;
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
@WebServlet(name = "Sucursalescontrol", urlPatterns = {"/Sucursalescontrol"})
public class Sucursalescontrol extends HttpServlet {

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

        Sucursalesdao SUCdao = new Sucursalesdaoimpl();

        Sucursalesdto SUCdto = new Sucursalesdto();

        switch (opcion) {
            case 1:
                SUCdto.setId_sucursal(Integer.parseInt(request.getParameter("codsucursal")));
                SUCdto.setSuc_descripcion(request.getParameter("sucursaldescri"));
                SUCdto.setId_ciudad(Integer.parseInt(request.getParameter("codciudad")));
                SUCdto.setId_barrio(Integer.parseInt(request.getParameter("codbarrio")));
                if (SUCdao.insertarSucursales(SUCdto)) {
                    out.println("Exitoso");
                }
                break;

            case 2:
                SUCdto.setId_sucursal(Integer.parseInt(request.getParameter("codsucursal")));
                SUCdto.setSuc_descripcion(request.getParameter("sucursaldescri"));
                SUCdto.setId_ciudad(Integer.parseInt(request.getParameter("codciudad")));
                SUCdto.setId_barrio(Integer.parseInt(request.getParameter("codbarrio")));
                if (SUCdao.modificarSucursales(SUCdto)) {
                    out.println("Exitoso");
                }
                break;

            case 3:
                if (SUCdao.eliminarSucursales(Integer.parseInt(request.getParameter("codsucursal")))) {
                    out.println("Exitoso");
                }
                break;

            case 4:
                out.println(SUCdao.getmostrarSucursales());
                break;

            case 5:
                System.out.println("codigo " + SUCdao.getUltimoCodigoSucursales());
                if (SUCdao.getUltimoCodigoSucursales() > 0) {
                    out.println(SUCdao.getUltimoCodigoSucursales());
                }
                break;

            case 6:

                if (SUCdao.getmostrarSucursalesFiltro(Integer.parseInt(request.getParameter("codsucursal"))) != null) {
                    out.println(SUCdao.getmostrarSucursalesFiltro(Integer.parseInt(request.getParameter("codsucursal"))));
                }
                break;

            case 7:
                out.println(SUCdao.listarCiudades());
                break;

            case 8:
                out.println(SUCdao.listarBarrios());
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
