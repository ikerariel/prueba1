/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.Timbradosdao;
import ModelDAOIMPL.Timbradosdaoimpl;
import ModelDTO.Timbradosdto;
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
@WebServlet(name = "Timbradoscontrol", urlPatterns = {"/Timbradoscontrol"})
public class Timbradoscontrol extends HttpServlet {

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
        
        Timbradosdao TIMdao = new Timbradosdaoimpl();
        
        Timbradosdto TIMdto = new Timbradosdto();
        
        switch (opcion) {
            case 1:
                TIMdto.setId_timbrado(Integer.parseInt(request.getParameter("codtimbrado")));
                TIMdto.setNumero(Integer.parseInt(request.getParameter("numer")));
                TIMdto.setInicio_fecha(request.getParameter("inifech"));
                TIMdto.setFinal_fecha(request.getParameter("finfech"));
                TIMdto.setVencimientos(request.getParameter("vencimient"));
                TIMdto.setId_estado(Integer.parseInt(request.getParameter("codestado")));
                if (TIMdao.insertarTimbrados(TIMdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 2:
                TIMdto.setId_timbrado(Integer.parseInt(request.getParameter("codtimbrado")));
                TIMdto.setNumero(Integer.parseInt(request.getParameter("numer")));
                TIMdto.setInicio_fecha(request.getParameter("inifech"));
                TIMdto.setFinal_fecha(request.getParameter("finfech"));
                TIMdto.setVencimientos(request.getParameter("vencimient"));
                TIMdto.setId_estado(Integer.parseInt(request.getParameter("codestado")));             
                if (TIMdao.modificarTimbrados(TIMdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 3:
                if (TIMdao.eliminarTimbrados(Integer.parseInt(request.getParameter("codtimbrado")))) {
                    out.println("Exitoso");
                }
                break;
                
            case 4:
                out.println(TIMdao.getmostrarTimbrados());
                break;
                
            case 5:
                System.out.println("codigo" + TIMdao.getUltimoCodigoTimbrados());
                if (TIMdao.getUltimoCodigoTimbrados()> 0) {
                    out.println(TIMdao.getUltimoCodigoTimbrados());
                }
                break;
                
            case 6:
                
                if (TIMdao.getmostrarTimbradosFiltro(Integer.parseInt(request.getParameter("codtimbrado"))) != null) {
                    out.println(TIMdao.getmostrarTimbradosFiltro(Integer.parseInt(request.getParameter("codtimbrado"))));
                }
                break;
                
            case 7:
                out.println(TIMdao.listarEstados());
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
