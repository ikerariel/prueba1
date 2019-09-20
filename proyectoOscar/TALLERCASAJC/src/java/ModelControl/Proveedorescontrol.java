/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.Proveedoresdao;
import ModelDAOIMPL.Proveedoresdaoimpl;
import ModelDTO.Proveedoresdto;
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
@WebServlet(name = "Proveedorescontrol", urlPatterns = {"/Proveedorescontrol"})
public class Proveedorescontrol extends HttpServlet {

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

        Proveedoresdao ProveedoresDAO = new Proveedoresdaoimpl();
//        //USO PATRON DTO      
        Proveedoresdto ProveedoresDTO = new Proveedoresdto();

        switch (opcion) {
            case 1:
                ProveedoresDTO.setId_proveedor(Integer.parseInt(request.getParameter("codproveedor")));
                ProveedoresDTO.setRas_social(request.getParameter("rassocialprov"));
                ProveedoresDTO.setDireccion(request.getParameter("direccionprov"));
                ProveedoresDTO.setPag_web(request.getParameter("pagwebprov"));
                ProveedoresDTO.setTelefono(request.getParameter("telefonoprov"));
                ProveedoresDTO.setRuc(request.getParameter("rucprov"));
                ProveedoresDTO.setId_ciudad(Integer.parseInt(request.getParameter("codciudad")));
                if (ProveedoresDAO.insertarProveedores(ProveedoresDTO)) {
                    out.println("Exitoso");
                }
                break;

            case 2:
                ProveedoresDTO.setId_proveedor(Integer.parseInt(request.getParameter("codproveedor")));
                ProveedoresDTO.setRas_social(request.getParameter("rassocialprov"));
                ProveedoresDTO.setDireccion(request.getParameter("direccionprov"));
                ProveedoresDTO.setPag_web(request.getParameter("pagwebprov"));
                ProveedoresDTO.setTelefono(request.getParameter("telefonoprov"));
                ProveedoresDTO.setRuc(request.getParameter("rucprov"));
                ProveedoresDTO.setId_ciudad(Integer.parseInt(request.getParameter("codciudad")));
                if (ProveedoresDAO.modificarProveedores(ProveedoresDTO)) {
                    out.println("Exitoso");
                }
                break;

            case 3:
                if (ProveedoresDAO.eliminarProveedores(Integer.parseInt(request.getParameter("codproveedor")))) {
                    out.println("Exitoso");
                }
                break;

            case 4:
                out.println(ProveedoresDAO.getmostrarProveedores());
                break;

            case 5:
                System.out.println("codigo " + ProveedoresDAO.getUltimoCodigoProveedores());
                if (ProveedoresDAO.getUltimoCodigoProveedores() > 0) {
                    out.println(ProveedoresDAO.getUltimoCodigoProveedores());
                }
                break;

            case 6:

                if (ProveedoresDAO.getmostrarProveedoresFiltro(Integer.parseInt(request.getParameter("codproveedor"))) != null) {
                    out.println(ProveedoresDAO.getmostrarProveedoresFiltro(Integer.parseInt(request.getParameter("codproveedor"))));
                }
                break;

            case 7:
                out.println(ProveedoresDAO.listarCiudades());
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
