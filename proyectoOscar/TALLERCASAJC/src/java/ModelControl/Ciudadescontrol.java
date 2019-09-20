/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;
import ModelDAO.Ciudadesdao;
import ModelDAOIMPL.Ciudadesdaoimpl;
import ModelDTO.Ciudadesdto;
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
@WebServlet(name = "Ciudadescontrol", urlPatterns = {"/Ciudadescontrol"})
public class Ciudadescontrol extends HttpServlet {

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
        
        Ciudadesdao CIUdao=new Ciudadesdaoimpl();
        
        Ciudadesdto CIUdto = new Ciudadesdto();
        
        switch (opcion) {
            case 1:
                CIUdto.setId_ciudad(Integer.parseInt(request.getParameter("idciudad")));
                CIUdto.setCiu_descripcion(request.getParameter("descriciudad"));
                if (CIUdao.insertarCiudades(CIUdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 2:
                CIUdto.setId_ciudad(Integer.parseInt(request.getParameter("idciudad")));
                CIUdto.setCiu_descripcion(request.getParameter("descriciudad"));
                if (CIUdao.modificarCiudades(CIUdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 3:
                if (CIUdao.eliminarCiudades(Integer.parseInt(request.getParameter("idciudad")))) {
                    out.println("Exitoso");
                }
                break;
                
            case 4:
                out.println(CIUdao.getmostrarCiudades());
                break;
                
            case 5:
                System.out.println("codigo " + CIUdao.getUltimoCodigoCiudades());
                if (CIUdao.getUltimoCodigoCiudades()> 0) {
                    out.println(CIUdao.getUltimoCodigoCiudades());
                }
                break;
                
            case 6:
                
                if (CIUdao.getmostrarCiudadesFiltro(Integer.parseInt(request.getParameter("idciudad"))) != null) {
                    out.println(CIUdao.getmostrarCiudadesFiltro(Integer.parseInt(request.getParameter("idciudad"))));
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
