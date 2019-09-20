/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.Empleadosdao;
import ModelDAOIMPL.Empleadosdaoimpl;
import ModelDTO.Empleadosdto;
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
@WebServlet(name = "Empleadoscontrol", urlPatterns = {"/Empleadoscontrol"})
public class Empleadoscontrol extends HttpServlet {

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

        Empleadosdao EMPdao = new Empleadosdaoimpl();

        Empleadosdto EMPdto = new Empleadosdto();

        switch (opcion) {
            case 1:
                EMPdto.setId_empleado(Integer.parseInt(request.getParameter("codempleado")));
                EMPdto.setNombre(request.getParameter("empnombre"));
                EMPdto.setApellido(request.getParameter("empapellido"));
                EMPdto.setCi(Integer.parseInt(request.getParameter("empci")));
                EMPdto.setTel(Integer.parseInt(request.getParameter("emptel")));
                EMPdto.setDireccion(request.getParameter("empdireccion"));
                EMPdto.setId_barrio(Integer.parseInt(request.getParameter("codbarrio")));
                EMPdto.setId_ciudad(Integer.parseInt(request.getParameter("codciudad")));
                if (EMPdao.insertarEmpleados(EMPdto)) {
                    out.println("Exitoso");
                }

                break;

            case 2:
                EMPdto.setId_empleado(Integer.parseInt(request.getParameter("codempleado")));
                EMPdto.setNombre(request.getParameter("empnombre"));
                EMPdto.setApellido(request.getParameter("empapellido"));
                EMPdto.setCi(Integer.parseInt(request.getParameter("empci")));
                EMPdto.setTel(Integer.parseInt(request.getParameter("emptel")));
                EMPdto.setDireccion(request.getParameter("empdireccion"));
                EMPdto.setId_barrio(Integer.parseInt(request.getParameter("codbarrio")));
                EMPdto.setId_ciudad(Integer.parseInt(request.getParameter("codciudad")));
                if (EMPdao.modificarEmpleados(EMPdto)) {
                    out.println("Exitoso");
                }
                break;

            case 3:
                if (EMPdao.eliminarEmpleados(Integer.parseInt(request.getParameter("codempleado")))) {
                    out.println("Exitoso");
                }
                break;

            case 4:
                out.println(EMPdao.getmostrarEmpleados());
                break;

            case 5:
                System.out.println("codigo" + EMPdao.getUltimoCodigoEmpleados());
                if (EMPdao.getUltimoCodigoEmpleados() > 0) {
                    out.println(EMPdao.getUltimoCodigoEmpleados());
                }
                break;

            case 6:

                if (EMPdao.getmostrarEmpleadosFiltro(Integer.parseInt(request.getParameter("codempleado"))) != null) {
                    out.println(EMPdao.getmostrarEmpleadosFiltro(Integer.parseInt(request.getParameter("codempleado"))));
                }
                break;

            case 7:
                out.println(EMPdao.listarBarrios());
                break;

            case 8:
                out.println(EMPdao.listarCiudades());
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
