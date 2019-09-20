/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDTO.accesoDTO;
import ModelDAO.accesoDAO;
import ModelDAOIMPL.accesoDAOIMPL;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 */
@WebServlet(name = "accesoSERVLET", urlPatterns = {"/accesoSERVLET"})
public class accesoSERVLET extends HttpServlet {

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

        accesoDAO aDAO = new accesoDAOIMPL();
        accesoDTO aDTO = new accesoDTO();
 Integer cont=0;
        aDTO.setUsu_nombre(request.getParameter("username_v"));
        aDTO.setUsu_clave(request.getParameter("password_v"));

        aDTO.setId_usuario(Integer.parseInt(request.getParameter("iduser_v")));
        aDTO.setIddeposito(Integer.parseInt(request.getParameter("iddepo_v")));
        aDTO.setDeposito(request.getParameter("descripdepo_v"));
        aDTO.setIdsucursal(Integer.parseInt(request.getParameter("idsucu_v")));
        aDTO.setSucursal(request.getParameter("descripsucu_v"));
        aDTO.setIdsupervisor(Integer.parseInt(request.getParameter("idsupervisor_v")));
        aDTO.setIdvendedor(Integer.parseInt(request.getParameter("idvendedor_v")));

       
        if (aDAO.accesoUsuario(aDTO)) {

            HttpSession sessionActivaSu = request.getSession();
            HttpSession sessionActivaCodUser = request.getSession();
            HttpSession sessionActivaDe = request.getSession();
            HttpSession sessionActivaCodde = request.getSession();
            HttpSession sessionActivaCodsucu = request.getSession();
            HttpSession sessionActivaUser = request.getSession();
            HttpSession sessionActivaSuperv = request.getSession();
            String UsuarioLogueado = aDTO.getUsu_nombre();
            Integer codusuario = aDTO.getId_usuario();
            String sucursal = aDTO.getSucursal();
            Integer codsucursal = aDTO.getIdsucursal();
            String deposito = aDTO.getDeposito();
            Integer coddeposito = aDTO.getIddeposito();
            Integer codsuperv = aDTO.getIdsupervisor();
            Integer codvendedor = aDTO.getIdvendedor();

            sessionActivaUser.setAttribute("user", UsuarioLogueado);
            sessionActivaDe.setAttribute("Deposito", deposito);
            sessionActivaSu.setAttribute("Sucursal", sucursal);
            sessionActivaCodsucu.setAttribute("Codsucursal", codsucursal);
            sessionActivaCodde.setAttribute("Coddeposito", coddeposito);
            sessionActivaCodUser.setAttribute("Coduser", codusuario);
            sessionActivaSuperv.setAttribute("Codsuperv", codsuperv);
            sessionActivaSuperv.setAttribute("Codvendedor", codvendedor);

            response.sendRedirect("/TALLERCASAJC/paginaprincipal.jsp");

        } else {
            response.sendRedirect("/TALLERCASAJC/acceso.jsp");
//            JOptionPane.showMessageDialog(null, "Contrasea inválida..!");
            System.out.println("error al verificar el usuario");
            cont++;
            System.out.println(cont);

        }
//        switch (opcion) {
//            case 1:
//
//                if (aDAO.accesoUsuario(request.getParameter("usuario"), request.getParameter("usuario_clave")) != null) {
//                    out.println(aDAO.accesoUsuario(request.getParameter("usuario"), request.getParameter("usuario_clave")));
//                } else {
//                    out.println("UNUSARIO INVALIDO");
//
//                    break;
//                }
//        }

//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet accesoSERVLET</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet accesoSERVLET at " + request.getContextPath() + "</h1>");
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
