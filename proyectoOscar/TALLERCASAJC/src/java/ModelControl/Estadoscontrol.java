/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.Estadosdao;
import ModelDAOIMPL.Estadosdaoimpl;
import ModelDTO.Estadosdto;
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
@WebServlet(name = "Estadoscontrol", urlPatterns = {"/Estadoscontrol"})
public class Estadoscontrol extends HttpServlet {

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
        
        Estadosdao ESTdao=new Estadosdaoimpl();
        
        Estadosdto ESTdto = new Estadosdto();
        
        switch (opcion) {
            case 1:
                ESTdto.setId_estado(Integer.parseInt(request.getParameter("idestado")));
                ESTdto.setEst_descripcion(request.getParameter("descriestado"));
                if (ESTdao.insertarEstados(ESTdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 2:
                ESTdto.setId_estado(Integer.parseInt(request.getParameter("idestado")));
                ESTdto.setEst_descripcion(request.getParameter("descriestado"));
                if (ESTdao.modificarEstados(ESTdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 3:
                if (ESTdao.eliminarEstados(Integer.parseInt(request.getParameter("idestado")))) {
                    out.println("Exitoso");
                }
                break;
                
            case 4:
                out.println(ESTdao.getmostrarEstados());
                break;
                
            case 5:
                System.out.println("codigo " + ESTdao.getUltimoCodigoEstados());
                if (ESTdao.getUltimoCodigoEstados()> 0) {
                    out.println(ESTdao.getUltimoCodigoEstados());
                }
                break;
                
            case 6:
                
                if (ESTdao.getmostrarEstadosFiltro(Integer.parseInt(request.getParameter("idestado"))) != null) {
                    out.println(ESTdao.getmostrarEstadosFiltro(Integer.parseInt(request.getParameter("idestado"))));
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
