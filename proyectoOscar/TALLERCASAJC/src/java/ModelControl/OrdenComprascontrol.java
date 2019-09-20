/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

//import ModelDAO.accesoDAO;
//import ModelDTO.accesoDTO;
//import ModelDAOIMPL.accesoDATOIMPL;
import ModelDAOIMPL.OrdenComprasdaoimpl;
import ModelDAO.OrdenComprasdao;
import ModelDTO.OrdenComprasdto;

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
@WebServlet(name = "OrdenComprascontrol", urlPatterns = {"/OrdenComprascontrol"})
public class OrdenComprascontrol extends HttpServlet {

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

        OrdenComprasdao ORCOMDdao = new OrdenComprasdaoimpl();
        OrdenComprasdto ORCOMdto = new OrdenComprasdto();

//        accesoDATOIMPL accDAO = new accesoDATOIMPL();
//        accesoDTO accDTO = new accesoDTO();
        switch (opcion) {
            case 1:
                System.out.println("codigo" + ORCOMDdao.getUltimoCodigo());
                if (ORCOMDdao.getUltimoCodigo() > 0) {
                    out.println(ORCOMDdao.getUltimoCodigo());
                }
                break;
            case 2:
                ORCOMdto.setId_sucursal(Integer.parseInt(request.getParameter("sucurC")));
                ORCOMdto.setId_proveedor(Integer.parseInt(request.getParameter("proveeC")));
                ORCOMdto.setId_pedidocompra(Integer.parseInt(request.getParameter("PcompC")));
                ORCOMdto.setId_usuario(Integer.parseInt(request.getParameter("usuaC")));
                ORCOMdto.setId_estado(Integer.parseInt(request.getParameter("estadoC")));
                if (ORCOMDdao.insertarOrdenCompras(ORCOMdto)) {
                    out.println("Exitoso");
                }
                break;
            case 3:
                ORCOMdto.setId_ordcompraD(Integer.parseInt(request.getParameter("codigoD")));
                ORCOMdto.setId_articulo(Integer.parseInt(request.getParameter("idartiD")));
                ORCOMdto.setCantidad_detorden(Integer.parseInt(request.getParameter("cantiD")));
                ORCOMdto.setPrecio_detorden(Integer.parseInt(request.getParameter("precioD")));
                if (ORCOMDdao.insertarDetOrdenCompras(ORCOMdto)) {
                    out.println("Exitoso");
                }
                break;
            case 4:
                ORCOMdto.setId_ordcompraD(Integer.parseInt(request.getParameter("codigoD")));
                ORCOMdto.setId_articulo(Integer.parseInt(request.getParameter("idartiD")));
                ORCOMdto.setCantidad_detorden(Integer.parseInt(request.getParameter("cantiD")));
                ORCOMdto.setPrecio_detorden(Integer.parseInt(request.getParameter("precioD")));
                if (ORCOMDdao.modificarDetOrdenCompras(ORCOMdto)) {
                    out.println("Exitoso");
                }
                break;
            case 5:
                if (ORCOMDdao.getCabeseraFiltroOrdenCompras(Integer.parseInt(request.getParameter("codigoC"))) != null) {
                    out.println(ORCOMDdao.getCabeseraFiltroOrdenCompras(Integer.parseInt(request.getParameter("codigoC"))));
                }
                break;
            case 6:
                if (ORCOMDdao.getDetallesFiltroOrdenCompras(Integer.parseInt(request.getParameter("codigoD"))) != null) {
                    out.println(ORCOMDdao.getDetallesFiltroOrdenCompras(Integer.parseInt(request.getParameter("codigoD"))));
                }
                break;
            case 7:
                out.println(ORCOMDdao.listarUsuarios());
                break;

            case 8:
                out.println(ORCOMDdao.listarEstados());
                break;
            case 10:
                out.println(ORCOMDdao.listarPedidosCompras());
                break;
            case 9:
                out.println(ORCOMDdao.listarArticulos());
                break;
            case 11:
                if (ORCOMDdao.listarDetPedidosCompras(Integer.parseInt(request.getParameter("nroPedido"))) != null) {
                    out.println(ORCOMDdao.listarDetPedidosCompras(Integer.parseInt(request.getParameter("nroPedido"))));
                    System.out.println(ORCOMDdao.listarDetPedidosCompras(Integer.parseInt(request.getParameter("nroPedido"))));
                }
                break;
//            case 12:
//                if (accDAO.getUser(request.getParameter("user")) != null) {
//                    out.println(accDAO.getUser(request.getParameter("user")));
//                }
//                break;
            case 13:
                ORCOMdto.setId_estado(Integer.parseInt(request.getParameter("CambioEstado")));
                ORCOMdto.setId_ordcompra(Integer.parseInt(request.getParameter("nroOrdenCo")));
                if (ORCOMDdao.confirmarOrdenCompras(ORCOMdto)) {
                    out.println("Exitoso");
                }
                break;
            case 14:
                out.println(ORCOMDdao.listarProveedores());
                break;
            case 15:
                out.println(ORCOMDdao.listarOrdenCompras());
                break;
            case 16:
                if (ORCOMDdao.listarDetOrdenCompras(Integer.parseInt(request.getParameter("nroOrden"))) != null) {
                    out.println(ORCOMDdao.listarDetOrdenCompras(Integer.parseInt(request.getParameter("nroOrden"))));
                    System.out.println(ORCOMDdao.listarDetOrdenCompras(Integer.parseInt(request.getParameter("nroOrden"))));
                }
                break;
            case 17:
                ORCOMdto.setPrecio_detorden(Integer.parseInt(request.getParameter("Artiprecio")));
                ORCOMdto.setArt_descripcion(request.getParameter("Artidescri"));
                ORCOMdto.setCodigenerico(request.getParameter("Artigenerico"));
                if (ORCOMDdao.insertarArticulos(ORCOMdto)) {
                    out.println("Exitoso");
                }
                break;
            case 18:
                System.out.println("codigoM" + ORCOMDdao.getUltimoCodigoArticulos());
                if (ORCOMDdao.getUltimoCodigoArticulos() > 0) {
                    out.println(ORCOMDdao.getUltimoCodigoArticulos());
                }
                break;
            case 19:
                if (ORCOMDdao.getArticulo(Integer.parseInt(request.getParameter("codArticulo")), Integer.parseInt(request.getParameter("coddepos"))) != null) {
                    out.println(ORCOMDdao.getArticulo(Integer.parseInt(request.getParameter("codArticulo")), Integer.parseInt(request.getParameter("coddepos"))));
                    System.out.println("Mensaje del Servler...recuperacion de filtro Exitoso OK");
                                            
                    }
                    break;
                
        
        case 20:
                if (ORCOMDdao.productosPorDeposito(Integer.parseInt(request.getParameter("codDepo"))) != null) {
                    out.println(ORCOMDdao.productosPorDeposito(Integer.parseInt(request.getParameter("codDepo"))));
                    System.out.println(ORCOMDdao.productosPorDeposito(Integer.parseInt(request.getParameter("codDepo"))));
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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
