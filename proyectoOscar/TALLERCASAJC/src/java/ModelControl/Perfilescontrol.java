/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.Perfilesdao;
import ModelDAOIMPL.Perfilesdaoimpl;
import ModelDTO.Perfilesdto;
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
@WebServlet(name = "Perfilescontrol", urlPatterns = {"/Perfilescontrol"})
public class Perfilescontrol extends HttpServlet {

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

        Perfilesdao PERdao = new Perfilesdaoimpl();

        Perfilesdto PERdto = new Perfilesdto();
        
        switch (opcion) {
            case 1:
                PERdto.setId_perfil(Integer.parseInt(request.getParameter("idperfil")));
                PERdto.setPerf_descripcion(request.getParameter("descriperfil"));
                if (PERdao.insertarPerfiles(PERdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 2:
                PERdto.setId_perfil(Integer.parseInt(request.getParameter("idperfil")));
                PERdto.setPerf_descripcion(request.getParameter("descriperfil"));
                if (PERdao.modificarPerfiles(PERdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 3:
                if (PERdao.eliminarPerfiles(Integer.parseInt(request.getParameter("idperfil")))) {
                    out.println("Exitoso");
                }
                break;
                
            case 4:
                out.println(PERdao.getmostrarPerfiles());
                break;
                
            case 5:
                System.out.println("codigo " + PERdao.getUltimoCodigoPerfiles());
                if (PERdao.getUltimoCodigoPerfiles()> 0) {
                    out.println(PERdao.getUltimoCodigoPerfiles());
                }
                break;
                
            case 6:
                
                if (PERdao.getmostrarPerfilesFiltro(Integer.parseInt(request.getParameter("idperfil"))) != null) {
                    out.println(PERdao.getmostrarPerfilesFiltro(Integer.parseInt(request.getParameter("idperfil"))));
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
