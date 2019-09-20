/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.PedidosComprasDAO;
import ModelDAOIMPL.PedidosComprasDAOIMPL;
import ModelDTO.PedidosComprasDTO;
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
@WebServlet(name = "PedidosComprasServlet", urlPatterns = {"/PedidosComprasServlet"})
public class PedidosComprasServlet extends HttpServlet {

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

        PedidosComprasDAO PEDCOMDAO = new PedidosComprasDAOIMPL();
        PedidosComprasDTO PEDCOMDTO = new PedidosComprasDTO();

        switch (opcion) {
            case 1:
                System.out.println("codigo" + PEDCOMDAO.getUltimoCodigo());
                if (PEDCOMDAO.getUltimoCodigo() > 0) {
                    out.println(PEDCOMDAO.getUltimoCodigo());
                }
                break;

            case 2:

                PEDCOMDTO.setId_usuario(Integer.parseInt(request.getParameter("codUse")));
                PEDCOMDTO.setObservacion(request.getParameter("observacionv"));

                if (PEDCOMDAO.insertar(PEDCOMDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 3:
                PEDCOMDTO.setId_pedidocompra(Integer.parseInt(request.getParameter("codigoD")));
                PEDCOMDTO.setId_articulo(Integer.parseInt(request.getParameter("idmercaV")));
                PEDCOMDTO.setCantidad(Integer.parseInt(request.getParameter("cantidadv")));

                if (PEDCOMDAO.insertarDetalles(PEDCOMDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 4:
                PEDCOMDTO.setId_pedidocompra(Integer.parseInt(request.getParameter("codigoD")));
                PEDCOMDTO.setId_articulo(Integer.parseInt(request.getParameter("idartiV")));
                PEDCOMDTO.setCantidad(Integer.parseInt(request.getParameter("cantidadv")));
//                pedidoDTO.setPrecio(Integer.parseInt(request.getParameter("preciov")));

                if (PEDCOMDAO.modificarDetalles(PEDCOMDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 5:
                if (PEDCOMDAO.getcabeseraFiltro(Integer.parseInt(request.getParameter("codigov"))) != null) {
                    out.println(PEDCOMDAO.getcabeseraFiltro(Integer.parseInt(request.getParameter("codigov"))));
                }
                break;
            case 6:
                if (PEDCOMDAO.getdetalleFiltro(Integer.parseInt(request.getParameter("codigoD"))) != null) {
                    out.println(PEDCOMDAO.getdetalleFiltro(Integer.parseInt(request.getParameter("codigoD"))));
                }
                break;
            case 7:
                out.println(PEDCOMDAO.listarUsuarios());
                break;
            case 8:
                out.println(PEDCOMDAO.listarEstados());
                break;
            case 9:
                out.println(PEDCOMDAO.listarPedidosCompras());
                break;
            case 15:
                out.println(PEDCOMDAO.listarArticulos());
                System.out.println((PEDCOMDAO.listarArticulos()));
                break;
            case 11:
                if (PEDCOMDAO.listarDetallesCompras(Integer.parseInt(request.getParameter("nropedidov"))) != null) {
                    out.println(PEDCOMDAO.listarDetallesCompras(Integer.parseInt(request.getParameter("nropedidov"))));
                    System.out.println(PEDCOMDAO.listarDetallesCompras(Integer.parseInt(request.getParameter("nropedidov"))));
                }
                break;

            case 13:
                PEDCOMDTO.setId_estado(Integer.parseInt(request.getParameter("CDestado")));
                PEDCOMDTO.setId_pedidocompra(Integer.parseInt(request.getParameter("nroPd")));
                if (PEDCOMDAO.confirmar(PEDCOMDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 14:
                PEDCOMDTO.setId_usuario(Integer.parseInt(request.getParameter("coduser")));
                PEDCOMDTO.setId_estado(Integer.parseInt(request.getParameter("codestado")));
                PEDCOMDTO.setObservacion(request.getParameter("codobserv"));
                PEDCOMDTO.setId_pedidocompra(Integer.parseInt(request.getParameter("codpediod")));
//                pedidoDTO.setPrecio(Integer.parseInt(request.getParameter("preciov")));

                if (PEDCOMDAO.modificar(PEDCOMDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 16:

                out.println(PEDCOMDAO.deledetalle(Integer.parseInt(request.getParameter("nropedidov"))));

                break;
            case 17:

                out.println(PEDCOMDAO.insertarArtpedido(request.getParameter("pArticulo")));

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
