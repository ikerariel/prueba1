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
        <script src="validador/PedidosComprasvalidad.js"></script>
        <title>Pedidos de Compras</title>
        <style>
               #scrollPlanilla{
                overflow: scroll;
                height:200px;
            }  
        </style>  
    </head>
    <body>
         <%@include file="viwmenu.jsp" %>
        <section>
            <form class="form-horizontal"  id="defaultForm">

                <div class="col-md-9" id="botonPedido">
                    <a id="btnNuevo" href="#ventanaPedido" class="btn btn-lg btn-success" style=" font-weight: bold"  title="Nuevo Pedido" data-toggle="modal" onclick="getcodigo()">Nuevo </a>
                    <a id="btnModificar" class="btn btn-lg btn-info" style=" font-weight: bold" title="Modificar Pedido" data-toggle="modal" onclick="recuperarDetalle()">Modificar </a>
                    <a id="btnAnular" class="btn btn-lg btn-danger" style=" font-weight: bold" title="Anular Pedido">Anular*</a>
                    <a id="btnconfpedido" class="btn btn-lg btn-warning glyphicon glyphicon-ok" style=" font-weight: bold" title="Confirmar Pedido" onclick=></a>
                    <a id="btnRevertir" class="btn btn-lg btn-danger glyphicon glyphicon-minus-sign" style=" font-weight: bold" title="Revertir Confirmacion de Pedido" onclick=""></a>
                    <a id="btnReporte" class="btn btn-lg btn-primary glyphicon glyphicon-print" title="Reporte de Panilla" onclick="reportesPedido()"></a>

                </div>
                <br>
                <br>
                <br>

                <div class="panel panel-default">
                    <div class="panel-footer" style="font-weight: bold">Planilla de Pedidos</div>

                    <div class="">
                        <div class="input-group  input-sm">
                            <span class="input-group-addon" style=" font-weight: bold">Pedido Nro*</span>
                            <input id="v_nropedido" type="text" style="background-color: #d9edf7" class="form-control" disabled="" placeholder="Numero de Pedido">
                            <span class="input-group-addon" style=" font-weight: bold" >Estado*</span>
                            <input id="v_estado" type="text" style="" class="form-control" disabled="" placeholder="Estado de Pedido">
                        </div>
                        <div class="input-group  input-sm">
                            <span class="input-group-addon" style=" font-weight: bold" >Obs.*</span>
                            <input id="v_obs" type="text" style="" class="form-control" disabled="" placeholder="Observaciones">
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
                            <table class="table table-striped table-bordered table-hover table input-md" id="miTablaPedidos" onclick="seleccion()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                <thead>
                                    <tr class="alert-dismissable" >
                                        <th>NRO. PEDIDO</th>
                                        <th>FECHA</th>
                                        <th>USUARIO</th>
                                        <th>ESTADO</th>
                                        <th class="alert-info">COMENTARIO</th>
                                        <th></th>
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
        <div class="modal fade" id="ventanaPedido">
            <div class="modal-dialog" style="width: 80%;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <a class="close  btn btn-lg btn-danger glyphicon glyphicon-off" data-dismiss="modal" aria-hidden="true" title="Salir"></a>
                    </div>
                    <!--CONTENIDO DE LA VENTANA--->
                    <div class="modal-body">
                        <a class="btn btn-lg btn-danger col-md-1"  id="btnGuardar" style="display: none" title="" onclick="InsertarPedidoCompra()" >Guardar*</a>
                        <a class="btn btn-lg btn-success col-md-1"  id="btnmodificarpedido"  style="display: none"title="" onclick="updatecompra()" >Guardar*</a>
                    </div>
                    <br>
                    <br>
                    <div class="panel">
                        <div class="panel panel-default">
                            <br>
                            <div class="panel-footer" style="font-weight: bold">Nueva Orden de Pedido</div>
                            <br> 
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Codigo</label>  
                                    <div class="col-md-2">
                                        <input  disabled="" id="codigo" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" name="codigo" type="text" placeholder="Registro" class="form-control input-sm" required=""  autofocus >
                                    </div>
                                    <label class="col-md-1 control-label">Fecha</label>  
                                    <div class="col-md-3">
                                        <input disabled id="fechapedido" type="datetime" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               placeholder="Ingrese fecha" class="form-control input-sm alert-danger">
                                    </div>
                                    <label class="col-md-1 control-label">Estado</label>  
                                    <div class="col-md-3">
                                        <input disabled id="estado"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" placeholder="PENDIENTE" value="PENDIENTE" class="form-control input-sm alert-danger">
                                    </div>
                                </div>
                            </div>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Usuario</label>  
                                    <div class="col-md-2">
                                        <input disabled  id="usuario" type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"  class="form-control input-sm alert-danger">
                                    </div>
                                    <label class="col-md-1 control-label">Obs.*</label>  
                                    <div class="col-md-7">
                                        <input maxlength="60" id="observ" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"  class="form-control input-sm" required=""  autofocus onkeydown="">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label">Cod*</label>
                                <div class="col-md-1">
                                    <input id="idmaterialGenerico" type="text" placeholder="Cod" data-toogle="modal" class="form-control input-sm" onclick="abrigrillArti()"
                                           style="text-transform: uppercase; font-weight: bold; font-size: 12pt"
                                           onkeyup="ValidacionSoloNumeros(this)" onchange="ValidacionSoloNumeros(this)">
                                </div>
                                <label class="col-md-1 control-label">Desciprción</label>
                                <div class="col-md-3">
                                    <input class="form-control input-sm" disabled="" id="iddescripcion" type="text" placeholder="Descirpcion del articulo"
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6;font-size: 12pt"
                                           onkeyup="separadorMiles(this)" onchange="separadorMiles(this)">
                                </div>
                                <label class="col-md-1 control-label">Cant*</label>
                                <div class="col-md-2">
                                    <input id="idcantidad" type="text" placeholder="Cant" maxlength="3" class="form-control input-sm" 
                                           style="text-transform: uppercase; font-weight: bold; font-size: 12pt"
                                           onkeyup="separadorMiles(this)" onchange="separadorMiles(this)"
                                           onkeydown="
                                                   if (event.keyCode === 13) {
                                                       CargarArticulos();
                                                   }">
                                </div>
                                
                                <div class="col-md-2">

                                    <a href="#ventanaAgregarArticulos" data-toggle="modal" class="form-control input-sm btn btn-danger" id="idpreci" type="text" placeholder="Precio"
                                          >nuevo</a>
                                </div>


                                <div class="col-md-1">
                                    <input disabled="" id="idmaterial" type="text" placeholder="" maxlength="3" class="form-control input-sm" 
                                           style="text-transform: uppercase;visibility: hidden; font-weight: bold; font-size: 12pt"
                                           onkeyup="separadorMiles(this)" onchange="separadorMiles(this)">
                                </div>
                            </div>
                        </div>
                        <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div class="table-responsive" style="height: 180px">
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaDetalleMercaderia" onclick="selecTablaArticulosDet()">
                                    <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                       
                                            <th>CODIGO</th>
                                            <th>DESCRIPCION</th>
                                         
                                            <th>CANTIDAD</th>
                                        
                                            <th style="text-align:  center">OPCION</th>
                                        </tr>
                                    </thead>
                                    <tbody id="table_deta" style="font-weight: bold;font-size: 10pt">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div> 
        </div>
        
        <!--------------Exten Articulo------------------------------------------------------>
        
        <div class="modal fade" id="ventanaAgregarArticulos">
            <div class="modal-dialog" style="width: 600px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <a class="close  btn btn-lg btn-danger glyphicon glyphicon-off" data-dismiss="modal" aria-hidden="true" title="Salir"></a>
                    </div>
                    <!--CONTENIDO DE LA VENTANA--->
                    <div class="modal-body">
                        <a class="btn btn-lg btn-danger  col-md-3"  id="btnGuardar" title="" onclick="saveArticulo()" >Guardar*</a>
                    </div>
                    <br>
                    <br>
                    <div class="panel">
                        <div class="panel panel-default">
                            <br>
                            <div class="panel-footer" style="font-weight: bold">Nuevo Articulo</div>
                            <br> 
                            <div class="form-horizontal">
                      
                            </div>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    
                                    <label class="col-md-1 control-label">Obs.*</label>  
                                    <div class="col-md-7">
                                        <input maxlength="60" id="articulodescripPedido" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"  class="form-control input-sm" required=""  autofocus onkeydown="">
                                    </div>
                                </div>
                            </div>
                        </div>

                <!-------------- HASTA ACA Exten Articulo------------------------------------------------------>        
                        
                    </div>
                </div>
            </div> 
        </div>
        
        
        
        <div class="modal fade" id="grillaArtic">
            <div class="modal-dialog" style="width: 800px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <DIV class="modal-body">
                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrarArtic" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20"
                                       class="form-control input-md" placeholder="Buscar Articulos..." onkeyup="buscarTablaArtic()">
                            </div>
                        </div>

                        <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scroll" class="table-responsive" style="height: 200px" >
                                <table class="table table-striped table-bordered table-hover table input-md" id="TablaArtic" onclick="seleccionArticulosS()">
                                    <thead>
                                        <tr class="alert-dismissable" >
                                   
                                            <th>Cod.Articulo</th>
                                            <th>Articulo</th>
                                    
                                        </tr>
                                    </thead>
                                    <tbody id="table_deta"></tbody>
                                </table>
                            </div>
                        </div>                       
                    </div>
                </div>
            </div> 
        </div>
    </body>
</html>
