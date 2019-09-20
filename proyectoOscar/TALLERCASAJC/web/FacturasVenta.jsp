
<%-- 
    Document   : FacturasCompras
    Created on : 05/09/2018, 10:29:16 AM
    Author     : user
--%>    

<!DOCTYPE html>
<html>
    <%
        HttpSession sessionActivaUser = request.getSession();
        if (sessionActivaUser.getAttribute("user") == null) {
            response.sendRedirect("/TALLERCASAJC/acceso.jsp");
        }

    %>
    <head>
        <%@include file="validaciones.jsp" %>

        <script src="validador/validadorFacturaventaa.js"></script>

        <title>FACTURA VENTA</title>
        <style type="text/css">
            #scrollPlanilla{
                overflow: scroll;
                height:170px;
            }
              #scrollcobro{
                overflow: scroll;
                height:130px;
            }
            #scrollfac{
                overflow: scroll;
                height:120px;
            }
            #car1,#car2{
                height: 120px;
            }
            hr {
                height: 1px;
                background-color: black;
                position: relative;
                top: -20px;
            }


        </style>
    </head>
    <body>
        <%@include file="viwmenu.jsp" %>
        <section style="background: white">
            <form class="form-horizontal "  id="defaultForm">
                <div class="panel panel-footer" id="">
                    <div class="form-group">
                        <div class="col-md-4">
                            <a  data-toggle="modal"  title="Imprimir Factura"  id="btnNuevo" class="btn btn-lg btn-success" onclick="abricobro()" ><span class="glyphicon glyphicon-print"></span> IMPRIMIR</a>
                            <a  data-toggle="modal"  title="Imprimir Factura" href="#anufactura"  id="btnNuevo" class="btn btn-lg btn-danger" onclick="getfacturacion()" ><span class="glyphicon glyphicon-remove"></span> ANULAR</a>
                            <a href="#facView" data-toggle="modal" title="Vista Previa"  id="btnver" class="btn btn-lg btn-primary" onclick="cargarFactura()" ><span class="glyphicon glyphicon-search"></span></a>
                        </div>


                        <label class="control-label col-md-6" style="
                               font-weight: bold; font-size: 13pt"> Total :</label>
                        <div class="col-md-2">
                            <input class="form-control" id="subtotal_v"
                                   value="0"  style="left: 200px; text-align: center;
                                   color: red; font-weight: bold; font-size: 20pt" type="text">
                        </div>
                    </div>

                </div>
                <div class="panel panel-footer">
                    <div class="form-group">
                        <label class="control-label col-md-1">Caja</label>
                        <div class="col-md-1">
                            <input class="form-control " type="text" id="nrocaja_v" readonly="">
                     
                        </div>
                        <label class="control-label col-md-2">Condición de Venta </label>
                        <div class="col-md-2">
                            <select class="form-control" disabled="" id="condventa_v"onchange="condicionventa()">
                                <option value="1">Contado</option>
                                <option value="2">Crédito</option>
                            </select>
                        </div>
                        <label class="control-label col-md-1">Cajero</label>
                        <div class="col-md-2">
                            <input class="form-control" id="cajero_v"   disabled=""type="text">
                        </div>
                        <div class="col-md-2">
                            <a  data-toggle="modal" href="#cobroview" class="btn btn-md btn-block btn-primary" style="display: none"> Cobrar</a>
                            <input class="form-control" type="text" id="codapertura_ap" style="display: none">
                        </div>

                    </div>
                    <div class="">
                        <div class="input-group  input-sm">
                            <span class="input-group-addon" style=" font-weight: bold">Fac. Nro:*</span>
                            <input id="nrofac_v" type="text" style="background-color: #d9edf7;width: 180px" class="form-control" >
                            <span class="input-group-addon" style=" font-weight: bold" >Fecha*</span>
                            <input id="v_fechafac" type="text" style="width: 150px" class="form-control" disabled="">
                            <span class="input-group-addon" style=" font-weight: bold" >Cliente*</span>
                            <input id="cedula_v" style="width: 180px" type="text"class="form-control" 
                                   onblur=""
                                   onkeydown="if (event.keyCode === 13) {
                                               obtenerCliente();
                                           }">
                            <span class="input-group-addon" style=" font-weight: bold" ></span>
                            <input id="razonsocial_v"  style="width: 400px" type="text"class="form-control" disabled="" >
                            <span class="input-group-addon" style=" font-weight: bold" ></span>
                            <a id="btnnewcliente_v" data-toggle="modal" href="#viewCliente"class="form-control btn btn-md btn-primary" >
                                <span class="glyphicon glyphicon-check"></span>
                            </a>
                            <span class="input-group-addon" style=" font-weight: bold;display: none" ></span>
                            <input id="idcliente_v"  style="display: none" type="text"class="form-control" disabled="" >
                            <span class="input-group-addon" style=" font-weight: bold;display: none" ></span>
                            <input id="idventa_v"  style="display:none" type="text"class="form-control" disabled="" >

                        </div>
                    </div>

                    <div class="input-group  input-sm">
                        <span class="input-group-addon" style=" font-weight: bold" >Vendedor.*</span>
                        <input list="listavendedor"  name="listavendedor "id="vendedor_v" type="text" 
                               class="form-control"
                               onkeydown="if (event.keyCode === 13) {
                                           obtenervendedor();
                                       }">
                        <datalist id="listavendedor">
                        </datalist>
                        <span class="input-group-addon" style=" font-weight: bold" ></span>
                        <input id="vendedormombre_v" type="text" readonly=""
                               style="width: 1000px" class="form-control">
                        <span class="input-group-addon" style=" font-weight: bold;display: none" ></span>
                        <input id="idfactura_v" type="text" readonly=""
                               style="display:none" class="form-control">

                    </div>
                    <div class="input-group  input-sm">
                        <span class="input-group-addon" style=" font-weight: bold" >Cod.*</span>
                        <input list="listaarti"  name="listaarti "id="articulo_v" type="text" 
                               placeholder="Cod" class="form-control"
                               onkeydown="
                                       if (event.keyCode === 13) {
                                           obtenerarticulos();
                                       }">
                        <datalist id="listaarti">
                        </datalist>
                        <span class="input-group-addon" style=" font-weight: bold" >Cant.*</span>
                        <input id="canti_v" type="number" style="" class="form-control" 
                               onkeydown="
                                       if (event.keyCode === 13) {
                                           agregarfilaventas();
                                       }">
                        <span class="input-group-addon" style=" font-weight: bold" >P.Unitario.*</span>
                        <input id="punitario_v" type="number" style="" class="form-control" readonly="">

                        <span class="input-group-addon" style=" font-weight: bold" >Impuesto.*</span>
                        <input id="impuesto_v" type="text" style="" class="form-control" disabled="">
                        <span class="input-group-addon" style=" font-weight: bold" ></span>
                        <input id="idimpuesto_v" type="text" style="display: none" class="form-control" disabled="">


                    </div>
                    <div class="input-group  input-sm">
                        <span class="input-group-addon" style=" font-weight: bold" >Producto.*</span>
                        <input id="producto_v"  style="width: 380px" type="text" class="form-control" readonly=""  >
                        <span class="input-group-addon" style=" font-weight: bold" >Cant.Cuota.*</span>
                        <input id="cantcuota_v" type="text" class="form-control" >
                        <span class="input-group-addon" style=" font-weight: bold" >Monto Cuota.*</span>
                        <input id="montocuota_v" type="text" class="form-control"  >
                        <span class="input-group-addon" style=" font-weight: bold" >Fecha Vto.*</span>
                        <input id="fechavto_v" type="date" class="form-control" >
                        <span class="input-group-addon" style=" font-weight: bold" ></span>
                        <a id="btnagregar_v" class="form-control btn btn-lg btn-default"  ><span
                                class="glyphicon glyphicon-plus"></span></a>

                    </div>


                    <div class="panel-body">

                        <!-- TABLAS DETALLES DE PLANILLA -->

                        <div id="scrollPlanilla" class="table-responsive">
                            <table class="table table-striped table-bordered table-hover table input-md" id="v_tablaDetalle">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                <thead>
                                    <tr>
                                        <th>Cod.</th>
                                        <th>Producto</th>
                                        <th>Cant.</th>
                                        <th>P. Unitario</th>
                                        <th style="display: none">codImp</th>
                                        <th>Iva 5%</th>
                                        <th>Iva 10%</th>
                                        <th>Exentas</th>
                                        <th id="tdsubtotal">Sub Total</th>
                                        <th>Opcion</th>
                                    </tr>
                                </thead>
                                <tbody id="table_deta" style="background: #ffffff; font-size: 12px;font-weight: bold"></tbody>
                            </table>
                        </div>
                    </div>
                </div>


            </form>
        </section>

        <!--/////////////  CABECERAS VENTANA DE FACTURAS COMPRAS //////////////////////////////////////////--->
        <script src="validador/validadorAperturacierrecaja.js"></script>
    </body>
</html>
