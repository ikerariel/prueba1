<%-- 
    Document   : reportesLlamadas
    Created on : 07/11/2018, 08:46:58 AM
    Author     : Oscar
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

        File reporFile = new File(application.getRealPath("/reportes/pedidos.jasper"));
        Map parameters = new HashMap();
        parameters.put("nro" , Integer.parseInt(request.getParameter("cod")));
        byte[] bytes = JasperRunManager.runReportToPdf(reporFile.getPath(), parameters, cn.getConexion());
        response.setContentLength(bytes.length);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes, 0, bytes.length);
        outputStream.flush();
        outputStream.close();
%>

