/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.Articulosdao;
import ModelDAOIMPL.Articulosdaoimpl;
import ModelDTO.Articulosdto;
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
@WebServlet(name = "Articuloscontrol", urlPatterns = {"/Articuloscontrol"})
public class Articuloscontrol extends HttpServlet {

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

        Articulosdao ARTDao = new Articulosdaoimpl();

        Articulosdto ARTDto = new Articulosdto();

        switch (opcion) {
            case 1:
                ARTDto.setId_articulo(Integer.parseInt(request.getParameter("codarticulo")));
                ARTDto.setArt_descripcion(request.getParameter("descriart"));
                ARTDto.setPreccompras(Integer.parseInt(request.getParameter("comprasart")));
                ARTDto.setPrecventas(Integer.parseInt(request.getParameter("ventasart")));
                ARTDto.setId_impuesto(Integer.parseInt(request.getParameter("codimpuesto")));
                ARTDto.setId_marca(Integer.parseInt(request.getParameter("codmarca")));
                ARTDto.setCodigenerico(request.getParameter("codigeneri"));
                if (ARTDao.insertarArticulos(ARTDto)) {
                    out.println("Exitoso");
                }
                break;

            case 2:
                ARTDto.setId_articulo(Integer.parseInt(request.getParameter("codarticulo")));
                ARTDto.setArt_descripcion(request.getParameter("descriart"));
                ARTDto.setPreccompras(Integer.parseInt(request.getParameter("comprasart")));
                ARTDto.setPrecventas(Integer.parseInt(request.getParameter("ventasart")));
                ARTDto.setId_impuesto(Integer.parseInt(request.getParameter("codimpuesto")));
                ARTDto.setId_marca(Integer.parseInt(request.getParameter("codmarca")));
                ARTDto.setCodigenerico(request.getParameter("codigeneri"));
                if (ARTDao.modificarArticulos(ARTDto)) {
                    out.println("Exitoso");
                }
                break;

            case 3:
                if (ARTDao.eliminarArticulos(Integer.parseInt(request.getParameter("codarticulo")))) {
                    out.println("Exitoso");
                }
                break;

            case 4:
                out.println(ARTDao.getmostrarArticulos());
                break;

            case 5:
                System.out.println("codigo" + ARTDao.getUltimoCodigoArticulos());
                if (ARTDao.getUltimoCodigoArticulos()> 0) {
                    out.println(ARTDao.getUltimoCodigoArticulos());
                }
                break;

            case 6:
                if (ARTDao.getmostrarArticulosFiltro(Integer.parseInt(request.getParameter("codarticulo"))) != null) {
                    out.println(ARTDao.getmostrarArticulosFiltro(Integer.parseInt(request.getParameter("codarticulo"))));
                }
                break;
            case 7:
                out.println(ARTDao.listarImpuestos());
                break;

            case 8:
                out.println(ARTDao.listarMarcas());
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
