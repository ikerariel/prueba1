/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.Impuestosdao;
import ModelDAOIMPL.Impuestosdaoimpl;
import ModelDTO.Impuestosdto;
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
@WebServlet(name = "Impuestoscontrol", urlPatterns = {"/Impuestoscontrol"})
public class Impuestoscontrol extends HttpServlet {

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
        
        Impuestosdao IMPdao=new Impuestosdaoimpl();
        
        Impuestosdto IMPdto = new Impuestosdto();
        
        switch (opcion) {
            case 1:
                IMPdto.setId_impuesto(Integer.parseInt(request.getParameter("idimpuesto")));
                IMPdto.setImp_descripcion(request.getParameter("descriimpuesto"));
                if (IMPdao.insertarImpuestos(IMPdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 2:
                IMPdto.setId_impuesto(Integer.parseInt(request.getParameter("idimpuesto")));
                IMPdto.setImp_descripcion(request.getParameter("descriimpuesto"));
                if (IMPdao.modificarImpuestos(IMPdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 3:
                if (IMPdao.eliminarImpuestos(Integer.parseInt(request.getParameter("idimpuesto")))) {
                    out.println("Exitoso");
                }
                break;
                
            case 4:
                out.println(IMPdao.getmostrarImpuestos());
                break;
                
            case 5:
                System.out.println("codigo " + IMPdao.getUltimoCodigoImpuestos());
                if (IMPdao.getUltimoCodigoImpuestos()> 0) {
                    out.println(IMPdao.getUltimoCodigoImpuestos());
                }
                break;
                
            case 6:
                
                if (IMPdao.getmostrarImpuestosFiltro(Integer.parseInt(request.getParameter("idimpuesto"))) != null) {
                    out.println(IMPdao.getmostrarImpuestosFiltro(Integer.parseInt(request.getParameter("idimpuesto"))));
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
