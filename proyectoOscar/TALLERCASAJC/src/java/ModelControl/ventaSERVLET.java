/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.ventaDAO;
import ModelDAOIMPL.ventaDAOIMPLE;
import ModelDTO.ventaDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
@WebServlet(name = "ventaSERVLET", urlPatterns = {"/ventaSERVLET"})
public class ventaSERVLET extends HttpServlet {

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

        ventaDAO vDAO = new ventaDAOIMPLE();
        ventaDAOIMPLE vIMPL = new ventaDAOIMPLE();
        ventaDTO vDTO = new ventaDTO();

        switch (opcion) {
            case 1:
                out.println(vDAO.getfactura(request.getParameter("caja_v")));
                break;
            case 2:
                out.println(vDAO.getcliente(request.getParameter("ci_v")));
                break;
            case 3:
                out.println(vDAO.getvendedor());
                break;
            case 4:
                out.println(vDAO.getidvendedor(Integer.parseInt(request.getParameter("cod_vend"))));
                break;
            case 5:
                vDTO.setNumerofac(request.getParameter("nrofac_v"));
                vDTO.setTipoog(Integer.parseInt(request.getParameter("tipopag_v")));
                vDTO.setId_cliente(Integer.parseInt(request.getParameter("idcliente_v")));
                vDTO.setId_deposito(Integer.parseInt(request.getParameter("iddeposito_v")));
                vDTO.setId_usuario(Integer.parseInt(request.getParameter("idusuario_v")));
                vDTO.setIdvendedor(Integer.parseInt(request.getParameter("idvendendor_v")));
                vDTO.setId_apcica(Integer.parseInt(request.getParameter("codapertura")));
                if (vDAO.insertarventa(vDTO)) {
                    System.out.println("FACTURADO CON EXITO");
                } else {
                    System.out.println("NO SE PUDO FACTURAR");
                }
                break;
            case 6:
                out.println(vDAO.gettipopago());
                break;

            case 7:
                vDTO.setId_factura(Integer.parseInt(request.getParameter("fac")));
                vDTO.setId_estado(Integer.parseInt(request.getParameter("estado_v")));
                if (vDAO.actualizarfactura(vDTO)) {
                    System.out.println("ACTUALIZADO CON EXITO");
                } else {
                    System.out.println("NO SE PUDO ACTUALIZAR");
                }
                break;
            case 8:
                vDTO.setId_articulo(Integer.parseInt(request.getParameter("codarticulo_v")));
                vDTO.setId_venta(Integer.parseInt(request.getParameter("codventa_v")));
                vDTO.setCantidad(Integer.parseInt(request.getParameter("cantidad_v")));
                vDTO.setPreciounitario(Integer.parseInt(request.getParameter("preciou_v")));
                vDTO.setId_impuesto(Integer.parseInt(request.getParameter("codimpuesto_v")));
                if (vDAO.insertardetalle(vDTO)) {
                    System.out.println("DETALLE CON EXITO");
                } else {
                    System.out.println("NO SE PUDO FACTURAR");
                }
                break;
            case 9:
                out.println(vDAO.getcodigo());
                break;
            case 10:
                vDTO.setRuc(request.getParameter("ruc_cliente"));
                vDTO.setRazonsocial(request.getParameter("razonsocial_cliente"));
                vDTO.setTelefono(Integer.parseInt(request.getParameter("telefon_cliente")));
                vDTO.setDireccion(request.getParameter("direccion_cliente"));
                vDTO.setCv(Integer.parseInt(request.getParameter("cv_cliente")));
                if (vIMPL.grabarcliente(vDTO)) {
                    System.out.println("DETALLE CON EXITO");
                } else {
                    System.out.println("NO SE PUDO FACTURAR");
                }
                break;
            case 11:
                vDTO.setId_estado(Integer.parseInt(request.getParameter("codestado_v")));
                vDTO.setNumerofac(request.getParameter("numerofac_v"));
                if (vIMPL.anularfactura(vDTO)) {
                    System.out.println("DETALLE CON EXITO");
                } else {
                    System.out.println("NO SE PUDO FACTURAR");
                }
                break;
            case 12:
                out.println(vDAO.gettimbrado());
                break;

            case 13:
                vDTO.setNumero(Integer.parseInt(request.getParameter("nrotimbrado_v")));
                vDTO.setVencimientos(request.getParameter("fvto_v"));
                vDTO.setId_usuario(Integer.parseInt(request.getParameter("coduser_v")));
                vDTO.setFac_establecimiento(request.getParameter("establecimiento_v"));
                vDTO.setFac_caja(request.getParameter("caja_v"));
                vDTO.setFac_desde(request.getParameter("fdesde_v"));
                vDTO.setFac_hasta(request.getParameter("fhasta_v"));
                if (vIMPL.insertartimbrado(vDTO)) {
                    System.out.println("DETALLE CON EXITO");
                } else {
                    System.out.println("NO SE PUDO FACTURAR");
                }
                break;
            case 14:
                vDTO.setNumero(Integer.parseInt(request.getParameter("codtimbrado_v")));
                vDTO.setNumerofac(request.getParameter("numfactura_v"));

                if (vIMPL.insertardetallefacturas(vDTO)) {
                    System.out.println("DETALLE CON EXITO");
                } else {
                    System.out.println("NO SE PUDO FACTURAR");
                }
                break;
            case 15:
                out.println(vDAO.getfac(request.getParameter("logueo")));
                break;
                
            case 16:
                vDTO.setImporte(Integer.parseInt(request.getParameter("impote_v")));
                vDTO.setId_cobro(Integer.parseInt(request.getParameter("idtipopago_v")));
                vDTO.setId_venta(Integer.parseInt(request.getParameter("codigoventa_v")));
                if (vIMPL.insertarcobro(vDTO)) {
                    System.out.println("INSERTADO EL COBRO");
                } else {
                    System.out.println("NO SS PUDO INSERTAR EL COBRO");
                }
                break;
            case 17:
                vDTO.setId_venta(Integer.parseInt(request.getParameter("codigoventa_v")));
                vDTO.setTarjnrobol(Integer.parseInt(request.getParameter("nroboleta_v")));
                vDTO.setId_entiemi(Integer.parseInt(request.getParameter("entidademisora_v")));
                vDTO.setId_tipotarjeta(Integer.parseInt(request.getParameter("tipotarjeta_v")));
                if (vIMPL.insertarcobrotarjeta(vDTO)) {
                    System.out.println("INSERTADO EL COBRO TARJETA");
                } else {
                    System.out.println("NO SS PUDO INSERTAR EL COBRO DE TARJETA");
                }
                break;
            case 18:
                vDTO.setNrochque(Integer.parseInt(request.getParameter("nrocheque_v")));
                vDTO.setId_tipocheque(Integer.parseInt(request.getParameter("tipocheque_v")));
                vDTO.setId_venta(Integer.parseInt(request.getParameter("codigoventa_vv")));
                vDTO.setId_bancocheque(Integer.parseInt(request.getParameter("banco_v")));
                if (vIMPL.insertarcobrocheque(vDTO)) {
                    System.out.println("INSERTADO EL COBRO EN CHEQUE");
                } else {
                    System.out.println("NO SS PUDO INSERTAR EL COBRO DE CHEQUE");
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
