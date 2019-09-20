/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;
import ModelDAO.Cajasdao;
import ModelDAOIMPL.Cajasdaoimpl;
import ModelDTO.Cajasdto;
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
@WebServlet(name = "Cajascontrol", urlPatterns = {"/Cajascontrol"})
public class Cajascontrol extends HttpServlet {

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
        
        Cajasdao CAJdao=new Cajasdaoimpl();
        
        Cajasdto CAJdto = new Cajasdto();
        
        switch (opcion) {
            case 1:
                CAJdto.setId_caja(Integer.parseInt(request.getParameter("idcaja")));
                CAJdto.setDescripcion(request.getParameter("descricaja"));
                if (CAJdao.insertarcaja(CAJdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 2:
                CAJdto.setId_caja(Integer.parseInt(request.getParameter("idcaja")));
                CAJdto.setDescripcion(request.getParameter("descricaja"));
                if (CAJdao.modificarcaja(CAJdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 3:
                if (CAJdao.eliminarcaja(Integer.parseInt(request.getParameter("idcaja")))) {
                    out.println("Exitoso");
                }
                break;
                
            case 4:
                out.println(CAJdao.getmostrarcaja());
                break;
                
            case 5:
                System.out.println("codigo " + CAJdao.getUltimoCodigocaja());
                if (CAJdao.getUltimoCodigocaja()> 0) {
                    out.println(CAJdao.getUltimoCodigocaja());
                }
                break;
                
            case 6:
                
                if (CAJdao.getmostrarcajaFiltro(Integer.parseInt(request.getParameter("idcaja"))) != null) {
                    out.println(CAJdao.getmostrarcajaFiltro(Integer.parseInt(request.getParameter("idcaja"))));
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
