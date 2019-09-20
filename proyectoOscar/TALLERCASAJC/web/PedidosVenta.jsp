<%-- 
    Document   : PedidosCompras
    Created on : 29/08/2019, 09:55:59 AM
    Author     : Oscar
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
        <%@include file="validaciones.jsp" %>
        <script src="validador/pedidoVentavalidad.js"></script>
        <script src="validador/genericoJS.js"></script>
        <title>Pedidos de Ventas</title>
        <style>
            #scrollPlanilla{
                overflow: scroll;
                height:200px;
            }  
        </style>  
    </head>
    <body onload="inactividad()">
        <%@include file="viwmenu.jsp" %>
        <section>
            <form class="form-horizontal"  id="defaultForm">

                <div class="col-md-9" id="botonPedido">
                    <a id="btnNuevopedidoventa" href="#ventanaPedido" class="btn btn-lg btn-success" style=" font-weight: bold"  title="Nuevo Pedido" data-toggle="modal">Nuevo </a>
                    <a id="btnconfpedidopedidoventa" class="btn btn-lg btn-warning glyphicon glyphicon-ok" style=" font-weight: bold" title="Confirmar Pedido" onclick=></a>
                    <a id="btnRevertirpedidoventa" class="btn btn-lg btn-danger glyphicon glyphicon-minus-sign" style=" font-weight: bold" title="Revertir Confirmacion de Pedido" onclick=""></a>
                    <a id="btnReportepedidoventa" class="btn btn-lg btn-primary glyphicon glyphicon-print" title="Reporte de Panilla" onclick=""></a>

                </div>
                <br>
                <br>
                <br>

                <div class="panel panel-default">
                    <div class="panel-footer" style="font-weight: bold">Planilla de Pedidos</div>

                    <div class="">
                        <div class="input-group  input-sm">
                            <span class="input-group-addon" style=" font-weight: bold">Pedido Nro*</span>
                            <input id="v_nropedidoventa" type="text" style="background-color: #d9edf7" class="form-control" disabled="" placeholder="Numero de Pedido">
                            <span class="input-group-addon" style=" font-weight: bold" >Estado*</span>
                            <input id="v_estadopedidov" type="text" style="" class="form-control" disabled="" placeholder="Estado de Pedido">
                        </div>
                        <div class="input-group  input-sm">
                            <span class="input-group-addon" style=" font-weight: bold" >Obs.*</span>
                            <input id="v_obspedidoventa" type="text" style="" class="form-control" disabled="" placeholder="Observaciones">
                        </div>
                    </div>
                    <div class="">
                        <div class="input-group input-sm">
                            <span class="input-group-addon" style=" font-weight: bold">Buscar</span>
                            <input id="filtrarPedido" type="text" style="text-transform: uppercase; font-weight: bold" class="form-control " maxlength="20" onkeyup="buscadorTablaPedido()"placeholder="Buscar Pediod...">
                        </div>
                    </div>

                    <div class="panel-body">


                        <!-- Tabla detalle plailla -->


                        <div id="scrollPlanilla" class="table-responsive">
                            <table class="table table-striped table-bordered table-hover table input-md" id="miTablaPedidoventa" >
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                <thead>
                                    <tr class="alert-dismissable" >
                                        <th>NRO. PEDIDO</th>
                                        <th>FECHA</th>
                                        <th>CLIENTE</th>
                                        <th>ESTADO</th>
                                        <th>USUARIO</th>
                                    <th class="" style="text-align: center">OPCIÓN</th>
                                    </tr>
                                </thead>
                                <tbody id="table_deta">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!--<div class="form-group">
                    <div class="col-md-12" >
                        <div class=" col-md-4" id="datos">
                             <input id="usernameD" value="" style="text-transform: uppercase;background: #919292; border: 1px solid #919292; font-weight: bold"  type="text"  class="form-control">
                        </div>
                        <div class=" col-md-4">
                             <input id="" readonly=""style="text-transform: uppercase; font-weight: bold"  type="text"  class="form-control">
                        </div>
                        <div class=" col-md-4">
                             <input id="" readonly=""style="text-transform: uppercase; font-weight: bold"  type="text"  class="form-control">
                        </div>              
                    </div>
                </div>-->
            </form>
        </section>
        <div class="modal fade" id="ventanaPedidoventa">
            <div class="modal-dialog" style="width: 80%;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <a class="btn btn-md btn-danger col-md-1"  id="btnGuardarpedidoventa" style="display: none" title="" >Guardar*</a>
                        <a class="btn btn-md btn-success col-md-1"  id="btnmodificarpedidoventa"  style="display: none"title=""  >Guardar*</a>
                        <a class="close  btn btn-md btn-danger glyphicon glyphicon-off" id="btnsalirpventa" data-dismiss="modal" aria-hidden="true" title="Salir"></a>
                    </div><br>

                    <div class="panel">
                        <div class="panel panel-default">
                            <div class="panel-footer" style="font-weight: bold">Nuevo Pedido de Venta</div>    
                            <div class="form-horizontal">

                                <div class="form-group">
                                    <br>
                                    <label class="col-md-2 control-label">Nº P.Venta.</label>  
                                    <div class="col-md-1">
                                        <input  disabled="" id="nropedidoventa" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" name="codigo" type="text" placeholder="Registro" class="form-control input-sm" required=""  autofocus >
                                    </div>
                                    <label class="col-md-1 control-label">Cod.Vend.</label>  
                                    <div class="col-md-1">
                                        <input  disabled="" id="codigovendedor" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" name="codigo" type="text" placeholder="Registro" class="form-control input-sm" required=""  autofocus >
                                    </div>
                                    <div class="col-md-3">
                                        <input  disabled="" id="nombrevendedor" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" name="codigo" type="text" placeholder="Registro" class="form-control input-sm" required=""  autofocus >
                                    </div>
                                    <label class="col-md-2 control-label">Fecha Pedido</label>  
                                    <div class="col-md-2">
                                        <input disabled id="fechapedidoventa" type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               placeholder="Ingrese fecha" class="form-control input-sm alert-danger">
                                    </div>

                                </div>
                            </div>


                            <div class="form-horizontal">
                                <div class="form-group">

                                    <label class="col-md-1 control-label">Cliente</label>  
                                    <div class="col-md-2">
                                        <input  id="clintepedidoventa" type="text" style="font-weight: bold;font-size: 12pt"
                                                class="form-control input-sm ">
                                    </div>
                                    <div class="col-md-4">
                                        <input disabled id="clinteNombrepedidoventa" type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               class="form-control input-sm ">
                                        <input disabled id="idclientepedidoventa" type="text" style="display: none"
                                               class="form-control input-sm ">
                                    </div>
                                       <label class="col-md-1 control-label">Obs.*</label>  
                                    <div class="col-md-4">
                                        <input maxlength="60" id="comentpedidoventa" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"  class="form-control input-sm" required=""  autofocus onkeydown="">
                                    </div>

                                </div>
                            </div>

                        </div>

                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label">Cod*</label>
                                <div class="col-md-2">
                                    <input list="lisarticulo"  name="lisarticulo "id="codarticulopedidoventa" type="text" 
                                           placeholder="Cod" class="form-control"
                                           onkeydown="
                                                   if (event.keyCode === 13) {
                                                       traerarticulos();
                                                   }">
                                    <datalist id="lisarticulo">
                                    </datalist>
                                </div>
                                <label class="col-md-2 control-label">Precio Unitario.*</label>
                                <div class="col-md-2">
                                    <input disabled="" style="text-align: center" id="preciounitarioprecioventa" type="text" 
                                           class="form-control">

                                </div>

                                <label class="col-md-1 control-label">Cant*</label>
                                <div class="col-md-2" >
                                    <input id="cantidadpedidoventa" type="text" placeholder="Cant" maxlength="3" class="form-control input-sm" 
                                           style="text-transform: uppercase; font-weight: bold; font-size: 12pt"
                                           onkeyup="separadorMiles(this)" onchange="separadorMiles(this)"
                                           onkeydown="
                                                   if (event.keyCode === 13) {
                                                       cargarfilapVenta();
                                                   }">
                                </div>
                                <div class="col-md-1">
                                    <a class="btn btn-block btn-success" id="btnagregarpedidodetalle"> <span class="glyphicon glyphicon-check"></span></a>
                                </div>

                            </div>
                            <div class="form-group">
                                <label class="col-md-1 control-label">Desciprción</label>
                                <div class="col-md-10">
                                    <input class="form-control input-sm" disabled="" id="articulopedidopedidoventa" type="text" placeholder="Descirpcion del articulo"
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6;font-size: 12pt"
                                           onkeyup="separadorMiles(this)" onchange="separadorMiles(this)">
                                </div>
                            </div>
                        </div>

                        <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div class="table-responsive" style="height: 130px">
                                <table class="table table-striped table-bordered table-hover table input-sm" id="miTablaDetallepedidoventa" >
                                    <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class=""  >
                                            <th> <span class="glyphicon glyphicon-th-list"></span> CODIGO</th>
                                            <th><span class="glyphicon glyphicon-th-list"></span>  DESCRIPCION</th>
                                            <th><span class="glyphicon glyphicon-th-list"></span>  CANTIDAD</th>
                                            <th><span class="glyphicon glyphicon-th-list"></span>  PRECIO U.</th>
                                            <th><span class="glyphicon glyphicon-th-list"></span>  SUB TOTAL</th>
                                            <th style="text-align:  center"><span class="glyphicon glyphicon-th-list"></span> OPCION</th>
                                        </tr>
                                    </thead>
                                    <tbody id="table_deta" style="font-weight: bold;font-size: 10pt">
                                    </tbody>
                                </table>

                            </div>

                            <div class="form-horizontal">
                                <div class="form-group">

                                    <label class="control-label col-md-1 col-md-offset-9" >Total:</label>
                                    <div class="col-md-2">
                                        <input style="border: transparent; text-align: center; font-size: 15pt;color: blue" class="form-control" id="montototalpventa">
                                    </div>


                                </div>
                            </div>

                        </div>


                    </div>
                </div>
            </div> 
        </div>

        <!--------------Exten Articulo------------------------------------------------------>

        



        <div class="modal fade" id="viewClientepedidoventa" data-backdrop="static" data-keyboard="false">
            <div class="modal-dialog" style="width: 800px">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <h4 class="modal-title" style="text-align: center;" id="textM">Nuevo Cliente</h4>
                    </div>
                    <!--CONTENIDO DE LA VENTANA--->
                    <div class="panel panel-footer">
                        <a class="close btn btn-md btn-danger" data-dismiss="modal" aria-hidden="true" title="Salir">X</a>
                    </div>
                    <DIV class="modal-body">
                        <form class="form-horizontal" id="miForm" >
                            <div class="form-group" >
                                <label class="control-label col-md-2">Cliente :</label>
                                <div class="col-md-3">
                                    <input disabled="" class="form-control " id="clienteModalpedidoventa"style="font-weight: bold;font-size: 10pt" type="text">
                                </div>
                                <div class="col-md-1">
                                    <input  id="clienteCVpedidoventa" disabled="" type="text" style="font-weight: bold;font-size: 12pt"
                                            class="form-control input-sm ">
                                </div>
                                <div class="col-md-6">
                                    <input class="form-control " id="clienteNombreModalpedidoventa" style="font-weight: bold;font-size: 10pt"type="text">
                                </div>
                            </div>
                            <div class="form-group" >
                                <label class="control-label col-md-2">Tel:</label>
                                <div class="col-md-2">
                                    <input  class="form-control " id="telefonoModalpedidoventa"style="font-weight: bold;font-size: 10pt" type="text">
                                </div>
                                <label class="control-label col-md-2">Direccion:</label>
                                <div class="col-md-6">
                                    <input class="form-control " maxlength="80" id="direccioneModalpedidoventa" style="font-weight: bold;font-size: 10pt"type="text">
                                </div>
                            </div>
                            <div class="form-group" >
                                <div class="col-md-12">
                                    <a class="btn btn-lg btn-default btn-block " id="tbnsaveclientepedidoventa"  >
                                        <span class="glyphicon glyphicon-check"></span> Guardar</a>
                                </div>

                            </div>

                        </form>   
                    </div>
                </div>
            </div> 
        </div>
    </body>
</html>
