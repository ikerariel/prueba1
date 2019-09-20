/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.ClientesDAO;
import ModelDAOIMPL.ClientesDAOIMPL;
import ModelDTO.ClientesDTO;
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
@WebServlet(name = "ClientesServlet", urlPatterns = {"/ClientesServlet"})
public class ClientesServlet extends HttpServlet {

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

        ClientesDAO CLIdao = new ClientesDAOIMPL();

        ClientesDTO CLIdto = new ClientesDTO();
        
        switch (opcion) {
            case 1:
                CLIdto.setId_cliente(Integer.parseInt(request.getParameter("codcliente")));
                CLIdto.setRuc(request.getParameter("cliruc"));
                CLIdto.setRazonsocial(request.getParameter("clirazonsocial"));
                CLIdto.setTelefono(request.getParameter("clitelefono"));
                CLIdto.setDireccion(request.getParameter("clidireccion"));
                CLIdto.setWeb(request.getParameter("cliweb"));
                CLIdto.setId_barrio(Integer.parseInt(request.getParameter("codbarrio")));
                CLIdto.setId_ciudad(Integer.parseInt(request.getParameter("codciudad")));
                if (CLIdao.insertarClientes(CLIdto)) {
                    out.println("Exitoso");
                }

                break;

            case 2:
                CLIdto.setId_cliente(Integer.parseInt(request.getParameter("codcliente")));
                CLIdto.setRuc(request.getParameter("cliruc"));
                CLIdto.setRazonsocial(request.getParameter("clirazonsocial"));
                CLIdto.setTelefono(request.getParameter("clitelefono"));
                CLIdto.setDireccion(request.getParameter("clidireccion"));
                CLIdto.setWeb(request.getParameter("cliweb"));
                CLIdto.setId_barrio(Integer.parseInt(request.getParameter("codbarrio")));
                CLIdto.setId_ciudad(Integer.parseInt(request.getParameter("codciudad")));
                if (CLIdao.modificarClientes(CLIdto)) {
                    out.println("Exitoso");
                }
                break;

            case 3:
                if (CLIdao.eliminarClientes(Integer.parseInt(request.getParameter("codcliente")))) {
                    out.println("Exitoso");
                }
                break;

            case 4:
                out.println(CLIdao.getmostrarClientes());
                break;

            case 5:
                System.out.println("codigo" + CLIdao.getUltimoCodigoClientes());
                if (CLIdao.getUltimoCodigoClientes()> 0) {
                    out.println(CLIdao.getUltimoCodigoClientes());
                }
                break;

            case 6:

                if (CLIdao.getmostrarClientesFiltro(Integer.parseInt(request.getParameter("codcliente"))) != null) {
                    out.println(CLIdao.getmostrarClientesFiltro(Integer.parseInt(request.getParameter("codcliente"))));
                }
                break;

            case 7:
                out.println(CLIdao.listarBarrios());
                break;

            case 8:
                out.println(CLIdao.listarCiudades());
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
