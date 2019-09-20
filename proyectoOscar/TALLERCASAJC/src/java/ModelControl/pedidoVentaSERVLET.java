/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.pedidoVentaDAO;
import ModelDAOIMPL.pedidoVentaDAOIMPL;
import ModelDTO.pedidoVentaDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
public class pedidoVentaSERVLET extends HttpServlet {

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

        pedidoVentaDAO pdDAO = new pedidoVentaDAOIMPL();
        pedidoVentaDTO pdDTO = new pedidoVentaDTO();

        switch (opcion) {
            case 1:
                Integer pdCod = Integer.parseInt(request.getParameter("pdValor"));
                if (pdCod == 1) {
                    pdDTO.setId_cliente(Integer.parseInt(request.getParameter("pdCliente")));
                    pdDTO.setId_sucursal(Integer.parseInt(request.getParameter("pdSucursal")));
                    pdDTO.setIdvendedor(Integer.parseInt(request.getParameter("pdVendedor")));
                    pdDTO.setObservacion(request.getParameter("pdObservacion"));
                } else if (pdCod == 2) {
                    pdDTO.setId_cliente(Integer.parseInt(request.getParameter("pdCliente")));
                    pdDTO.setId_sucursal(Integer.parseInt(request.getParameter("pdSucursal")));
                    pdDTO.setIdvendedor(Integer.parseInt(request.getParameter("pdVendedor")));
                    pdDTO.setObservacion(request.getParameter("pdObservacion"));
                    pdDTO.setId_pedidoven(Integer.parseInt(request.getParameter("pdCodpedido")));
                }
                if (pdDAO.insertarpedidoventa(pdDTO, pdCod)) {
                    out.println("Exitoso");
                }
                break;

            case 2:
                pdDTO.setId_pedidoven(Integer.parseInt(request.getParameter("pdCodpedido")));
                if (pdDAO.deletedetallepedidoventa(pdDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 3:

                Integer pdVr = Integer.parseInt(request.getParameter("opDetallepd"));
                if (pdVr == 1) {
                    pdDTO.setId_articulo(Integer.parseInt(request.getParameter("pdCodarticulo")));
                    pdDTO.setCantidad(Integer.parseInt(request.getParameter("pdCantidad")));
                    pdDTO.setPrecio(Integer.parseInt(request.getParameter("pdPrecio")));
                } else if (pdVr == 2) {
                    pdDTO.setId_pedidoven(Integer.parseInt(request.getParameter("pdCodpedido")));
                    pdDTO.setId_articulo(Integer.parseInt(request.getParameter("pdCodarticulo")));
                    pdDTO.setCantidad(Integer.parseInt(request.getParameter("pdCantidad")));
                    pdDTO.setPrecio(Integer.parseInt(request.getParameter("pdPrecio")));
                }

                if (pdDAO.insertardetallepedidoventa(pdDTO, pdVr)) {
                    out.println("Exitoso");
                }
                break;

            case 4:
                out.println(pdDAO.getpedidoVenta());
                System.out.println(pdDAO.getpedidoVenta());
                break;
            case 5:
                out.println(pdDAO.getpedidoVentaDetalle(Integer.parseInt(request.getParameter("nropedidoventa"))));
                break;

            case 6:
                pdDTO.setId_estado(Integer.parseInt(request.getParameter("pdEstado")));
                pdDTO.setId_pedidoven(Integer.parseInt(request.getParameter("pdCodpedido")));
                if (pdDAO.updatepedidoventa(pdDTO)) {
                    out.println("Exitoso");
                }
                break;
        }

//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet pedidoVentaSERVLET</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet pedidoVentaSERVLET at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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
