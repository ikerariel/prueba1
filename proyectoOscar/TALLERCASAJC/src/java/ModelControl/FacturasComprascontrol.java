/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAOIMPL.FacturasComprasdaoimpl;
import ModelDTO.FacturasComprasdto;
import ModelDAO.FacturasComprasdao;
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
@WebServlet(name = "FacturasComprascontrol", urlPatterns = {"/FacturasComprascontrol"})
public class FacturasComprascontrol extends HttpServlet {

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

        FacturasComprasdao FACTDAO = new FacturasComprasdaoimpl();
        FacturasComprasdto FACTDTO = new FacturasComprasdto();

        switch (opcion) {
            case 1:
                System.out.println("codigo" + FACTDAO.getUltimoCodigoCompras());
                if (FACTDAO.getUltimoCodigoCompras() > 0) {
                    out.println(FACTDAO.getUltimoCodigoCompras());
                }
                break;
            case 2:
                out.println(FACTDAO.ListarEstadosCompras2());
                break;
            case 3:
                out.println(FACTDAO.ListarUsuariosCompras3());
                break;
            case 4:
                out.println(FACTDAO.ListarProveedoresCompras4());
                break;
            case 5:
                out.println(FACTDAO.ListarOrdenCompras5());
                break;
            case 6:
                if (FACTDAO.ListarDetOrdenCompras6(Integer.parseInt(request.getParameter("id_ordencompraC"))) != null) {
                    out.println(FACTDAO.ListarDetOrdenCompras6(Integer.parseInt(request.getParameter("id_ordencompraC"))));
                    System.out.println(FACTDAO.ListarDetOrdenCompras6(Integer.parseInt(request.getParameter("id_ordencompraC"))));
                }
                break;
            case 7:
                out.println(FACTDAO.ListarSucursalesCompras7());
                break;
            case 8:
                out.println(FACTDAO.ListarArticulosCompras8());
                break;
            case 9:

                FACTDTO.setCo_cantcuota(Integer.parseInt(request.getParameter("Fco_cantcuota")));
                FACTDTO.setCo_monto(Integer.parseInt(request.getParameter("Fco_monto")));
                FACTDTO.setCo_nrofact(request.getParameter("Fco_nrofact"));
                FACTDTO.setCo_intervalo(request.getParameter("Fco_intervalo"));
                FACTDTO.setCo_fecha(request.getParameter("Fco_fecha"));
                FACTDTO.setCo_tipo(request.getParameter("Fco_tipo"));
                FACTDTO.setId_proveedor(Integer.parseInt(request.getParameter("Fco_proveedor")));
                FACTDTO.setId_sucursal(Integer.parseInt(request.getParameter("Fco_sucursal")));
                FACTDTO.setId_usuario(Integer.parseInt(request.getParameter("Fco_usuario")));
                FACTDTO.setId_estado(Integer.parseInt(request.getParameter("Fco_estado")));
                FACTDTO.setId_ordencompra(Integer.parseInt(request.getParameter("Fco_ordencompra")));
                if (FACTDAO.insertarCabeceraCompras9(FACTDTO)) {
                    out.println("Exitoso");
                }

                break;
            case 10:
                FACTDTO.setId__compraD(Integer.parseInt(request.getParameter("codigoD")));
                FACTDTO.setId_articulo(Integer.parseInt(request.getParameter("idartiD")));
                FACTDTO.setCantidad_detcomp(Integer.parseInt(request.getParameter("cantiD")));
                FACTDTO.setPrecio_detcomp(Integer.parseInt(request.getParameter("precioD")));
                if (FACTDAO.insertarDetCompras10(FACTDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 11:
                out.println(FACTDAO.ListarFacturasCompras11());
                break;
            case 12:
                FACTDTO.setId_estado(Integer.parseInt(request.getParameter("CambioEstadoC")));
                FACTDTO.setId_compra(Integer.parseInt(request.getParameter("FacturaCNro")));
                if (FACTDAO.confirmarFacturasCompras12(FACTDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 13:
                if (FACTDAO.listarDetCompras13(Integer.parseInt(request.getParameter("nroFacturaC"))) != null) {
                    out.println(FACTDAO.listarDetCompras13(Integer.parseInt(request.getParameter("nroFacturaC"))));
                    System.out.println(FACTDAO.listarDetCompras13(Integer.parseInt(request.getParameter("nroFacturaC"))));
                }
                break;
            case 14:

                FACTDTO.setCo_cantcuota(Integer.parseInt(request.getParameter("Fco_cantcuota")));
                FACTDTO.setCo_monto(Integer.parseInt(request.getParameter("Fco_monto")));
                FACTDTO.setCo_nrofact(request.getParameter("Fco_nrofact"));
                FACTDTO.setCo_intervalo(request.getParameter("Fco_intervalo"));
                FACTDTO.setCo_fecha(request.getParameter("Fco_fecha"));
                FACTDTO.setCo_tipo(request.getParameter("Fco_tipo"));
                FACTDTO.setId_proveedor(Integer.parseInt(request.getParameter("Fco_proveedor")));
                FACTDTO.setId_sucursal(Integer.parseInt(request.getParameter("Fco_sucursal")));
                FACTDTO.setId_usuario(Integer.parseInt(request.getParameter("Fco_usuario")));
                FACTDTO.setId_estado(Integer.parseInt(request.getParameter("Fco_estado")));
                FACTDTO.setId_ordencompra(Integer.parseInt(request.getParameter("Fco_ordencompra")));
                FACTDTO.setId_compra(Integer.parseInt(request.getParameter("Fco_idcompra")));
                if (FACTDAO.modificarCabeceraCompras9(FACTDTO)) {
                    out.println("Exitoso");
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
