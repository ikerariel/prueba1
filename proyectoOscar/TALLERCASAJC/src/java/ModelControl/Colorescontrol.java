package ModelControl;

import ModelDAO.Coloresdao;
import ModelDAOIMPL.Coloresdaoimpl;
import ModelDTO.Coloresdto;
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
@WebServlet(name = "Colorescontrol", urlPatterns = {"/Colorescontrol"})
public class Colorescontrol extends HttpServlet {

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

        Coloresdao COLdao = new Coloresdaoimpl();

        Coloresdto COLdto = new Coloresdto();
//        Coloresdto COLdto = new Coloresdto();
    
         switch (opcion) {
            case 1:
                COLdto.setId_color(Integer.parseInt(request.getParameter("idcolor")));
                COLdto.setColo_descripcion(request.getParameter("descricolor"));
                if (COLdao.insertarColores(COLdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 2:
                COLdto.setId_color(Integer.parseInt(request.getParameter("idcolor")));
                COLdto.setColo_descripcion(request.getParameter("descricolor"));
                if (COLdao.modificarColores(COLdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 3:
                if (COLdao.eliminarColores(Integer.parseInt(request.getParameter("idcolor")))) {
                    out.println("Exitoso");
                }
                break;
                
            case 4:
                out.println(COLdao.getmostrarColores());
                break;
                
            case 5:
                System.out.println("codigo " + COLdao.getUltimoCodigoColores());
                if (COLdao.getUltimoCodigoColores()> 0) {
                    out.println(COLdao.getUltimoCodigoColores());
                }
                break;
                
            case 6:
                
                if (COLdao.getmostrarColoresFiltro(Integer.parseInt(request.getParameter("idcolor"))) != null) {
                    out.println(COLdao.getmostrarColoresFiltro(Integer.parseInt(request.getParameter("idcolor"))));
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
