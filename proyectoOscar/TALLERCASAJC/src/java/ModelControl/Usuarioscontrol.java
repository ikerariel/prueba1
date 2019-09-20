/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.Usuariosdao;
import ModelDAOIMPL.Usuariosdaoimpl;
import ModelDTO.Usuariosdto;
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
@WebServlet(name = "Usuarioscontrol", urlPatterns = {"/Usuarioscontrol"})
public class Usuarioscontrol extends HttpServlet {

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

        Usuariosdao USUdao = new Usuariosdaoimpl();

        Usuariosdto USUdto = new Usuariosdto();

        switch (opcion) {
            case 1:
                USUdto.setId_usuario(Integer.parseInt(request.getParameter("codusuario")));
                USUdto.setUsu_nombre(request.getParameter("usunombre"));
                USUdto.setUsu_clave(request.getParameter("usuclave"));
                USUdto.setId_empleado(Integer.parseInt(request.getParameter("codempleado")));
                USUdto.setId_sucursal(Integer.parseInt(request.getParameter("codsucursal")));
                USUdto.setId_perfil(Integer.parseInt(request.getParameter("codperfil")));
                if (USUdao.insertarUsuarios(USUdto)) {
                    out.println("Exitoso");
                }

                break;

            case 2:
                USUdto.setId_usuario(Integer.parseInt(request.getParameter("codusuario")));
                USUdto.setUsu_nombre(request.getParameter("usunombre"));
                USUdto.setUsu_clave(request.getParameter("usuclave"));
                USUdto.setId_empleado(Integer.parseInt(request.getParameter("codempleado")));
                USUdto.setId_sucursal(Integer.parseInt(request.getParameter("codsucursal")));
                USUdto.setId_perfil(Integer.parseInt(request.getParameter("codperfil")));
                if (USUdao.modificarUsuarios(USUdto)) {
                    out.println("Exitoso");
                }
                break;

            case 3:
                if (USUdao.eliminarUsuarios(Integer.parseInt(request.getParameter("codusuario")))) {
                    out.println("Exitoso");
                }
                break;

            case 4:
                out.println(USUdao.getmostrarUsuarios());
                break;

            case 5:
                System.out.println("codigo" + USUdao.getUltimoCodigoUsuarios());
                if (USUdao.getUltimoCodigoUsuarios() > 0) {
                    out.println(USUdao.getUltimoCodigoUsuarios());
                }
                break;

            case 6:

                if (USUdao.getmostrarUsuariosFiltro(Integer.parseInt(request.getParameter("codusuario"))) != null) {
                    out.println(USUdao.getmostrarUsuariosFiltro(Integer.parseInt(request.getParameter("codusuario"))));
                }
                break;

            case 7:
                out.println(USUdao.listarEmpleados());
                break;

            case 8:
                out.println(USUdao.listarSucursales());
                break;

            case 9:
                out.println(USUdao.listarPerfiles());
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
