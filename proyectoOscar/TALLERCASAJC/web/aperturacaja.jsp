<%-- 
    Document   : aperturacaja
    Created on : 30/03/2019, 09:53:40 AM
    Author     : Carlos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%

        HttpSession sessionActivaUser = request.getSession();
        if (sessionActivaUser.getAttribute("user") == null) {
            response.sendRedirect("/TALLERCASAJC/acceso.jsp");
        }

    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Apertura de Caja</title>
        
        <%@include file="validaciones.jsp" %>
        <script src="validador/validadorAperturacierrecaja.js"></script>
        <script src="validador/validadorFacturaventaa.js"></script>
        <style>
            #scrolaper{
                overflow: scroll;
                height:350px;
            }
            #scrollcierre{
                overflow: scroll;
                height:230px;
            }
        
        </style> 
    </head>
    <body>
        <%@include file="viwmenu.jsp" %>
        <section style="background: white">
            <form class="form-horizontal "  id="defaultForm">
                <div class="panel panel-footer" id="">
                    <div class="form-group">
                        <div class="col-md-6">
                            <a style="display: none"  data-toggle="modal" href="#aperturacaja" title="Imprimir Factura"  id="btnNuevo" class="btn btn-lg btn-success" onclick="" ><span class="glyphicon glyphicon-check"></span> Nuevo</a>
                            <a style="display: none"  data-toggle="modal" title="Cerrar Caja"   id="btnNuevo" class="btn btn-lg btn-danger" onclick="cerrarCaja()" ><span class="glyphicon glyphicon-chevron-down"></span> Cierre</a>
                            <a style="display: none"  href="" data-toggle="modal" title="Vista Previa"  id="btnver" class="btn btn-lg btn-primary" onclick="reportefacturado()" ><span class="glyphicon glyphicon-print"></span> Facturacion</a>
                            <a style="display: none"  href="" data-toggle="modal" title="Vista Previa"  id="btnver" class="btn btn-lg btn-primary" onclick="reportearqueo()" ><span class="glyphicon glyphicon-print"></span> Arqueo</a>
                        </div>

                    </div>

                </div>
                <div class="panel panel-footer">

                    <div class="">
                        <div class="input-group  input-sm">
                            <span class="input-group-addon" style=" font-weight: bold">Nro. Registro*</span>
                            <input id="codarqueo_ap" type="text" style="background-color: #d9edf7" class="form-control" >
                            <span class="input-group-addon" style=" font-weight: bold" >Estado*</span>
                            <input id="estadoapertura_ap" type="text"  class="form-control" disabled="">
                            <span class="input-group-addon" style=" font-weight: bold;display: none" >Estado*</span>
                            <input id="totalfactu_fac" type="text" style="display:none" class="form-control" disabled="">

                        </div>
                    </div>

                    <div class="panel-body">
                        <!-- TABLAS DETALLES DE PLANILLA -->
                        <div id="scrolaper" class="table-responsive">
                            <table class="table table-striped table-bordered table-hover table input-md" id="v_tablaapertura" onclick="seleccionarqueo()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                <thead>
                                    <tr>
                                        <th>NRO REGISTRO.</th>
                                        <th>FECHA</th>
                                        <th>CAJA</th>
                                        <th>CAJERO</th>
                                        <th>SUPERVISOR</th>
                                        <th>ESTADO</th>
                                    </tr>
                                </thead>
                                <tbody id="table_deta" style="background: #ffffff; font-size: 12px;font-weight: bold"></tbody>
                            </table>
                        </div>
                    </div>
                </div>


            </form>
        </section>

    </body>
</html>
