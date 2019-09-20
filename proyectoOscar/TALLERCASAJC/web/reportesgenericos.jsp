<%-- 
    Document   : reportes
    Created on : 14/10/2018, 05:00:34 PM
    Author     : user
--%>

<%@page import="javax.swing.JOptionPane"%>
<%@page import="net.sf.jasperreports.engine.*"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.io.File"%>
<%@page import="Genericos.Conexion"%>
<%@page import="java.sql.*"%>
<%@page contentType="application/pdf" pageEncoding="UTF-8"%>

<%
     Conexion cn = new Conexion();
    Integer codigo = Integer.parseInt(request.getParameter("vCodigo"));
    if (codigo == 1) {

        File reporFile = new File(application.getRealPath("/reportes/presupuesto.jasper"));
        Map parameters = new HashMap();
        parameters.put("id_presucompra", Integer.parseInt(request.getParameter("id_presucompra")));
        byte[] bytes = JasperRunManager.runReportToPdf(reporFile.getPath(), parameters, cn.getConexion());
        response.setContentLength(bytes.length);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes, 0, bytes.length);
        outputStream.flush();
        outputStream.close();
    } else if (codigo == 2) {

        File reporFile = new File(application.getRealPath("/reportes/recepcion.jasper"));
        Map parameters = new HashMap();
        parameters.put("id_recepcion", Integer.parseInt(request.getParameter("id_recepcion")));
        byte[] bytes = JasperRunManager.runReportToPdf(reporFile.getPath(), parameters, cn.getConexion());
        response.setContentLength(bytes.length);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes, 0, bytes.length);
        outputStream.flush();
        outputStream.close();
    }else if (codigo == 3) {

        File reporFile = new File(application.getRealPath("/reportes/pedidoVenta.jasper"));
        Map parameters = new HashMap();
        parameters.put("id_pedidoven", Integer.parseInt(request.getParameter("id_pedidoven")));
        byte[] bytes = JasperRunManager.runReportToPdf(reporFile.getPath(), parameters, cn.getConexion());
        response.setContentLength(bytes.length);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes, 0, bytes.length);
        outputStream.flush();
        outputStream.close();
    }


%>