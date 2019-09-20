
<%-- 
    Document   : OrdenCompras
    Created on : 28/08/2018, 10:05:06 AM
    Author     : user
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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="Recursos/css/estilos_1.css">
        <link rel="stylesheet" href="Recursos/css/iconos.css">
        <link rel="stylesheet" href="Recursos/css/bootstrap.css">
        <link rel="stylesheet" href="Recursos/css/bootstrap-theme.min.css"/>
        <link rel="stylesheet" href="Recursos/css/bootstrap-select.css"/>
        <link rel="stylesheet" href="Recursos/css/bootstrap-select.min.css"/>
        <script src="Recursos/js/main.js"></script>
        <script src="Recursos/js/jquery.js"></script>
        <script src="Recursos/js/bootstrap.js"></script>
        <script src="Recursos/js/jquery.backstretch.min.js"></script>
        <script src="Recursos/js/jquery.dataTables.min.js"></script>
        <script src="Recursos/js/bootstrapValidator.js"></script>
        <script src="Recursos/js/bootstrap-select.js"></script>
        <script src="Recursos/js/bootstrap-select.min.js"></script>
        <script src="Recursos/js/ImagenFondo.js"></script> 
        <script src="validador/OrdenComprasValidad.js"></script>
        <script src="validador/genericoJS.js"></script>
        <script src="Recursos/js/jquery.backstretch.min.js"></script>

        <title>ORDEN DE COMPRAS</title>
             <style>
               #scrollPlanilla{
                overflow: scroll;
                height:200px;
            }  
        </style>  
    </head>
    <body>
        <%@ include file="viwmenu.jsp"%> 
        <section>
            <form class="form-horizontal"  id="defaultForm">

                <div class="col-md-9" id="botonesOrdenCompra">
                    <a id="btnNuevoOrden"  class="btn btn-lg btn-success" style=" font-weight: bold"  title="Nuevo Orden de Compras" data-toggle="modal"
                       >Nuevo </a>
                    <a id="btnRecuperar" class="btn btn-lg btn-info" style=" font-weight: bold" title="Recuperar Orden de Compras" data-toggle="modal" onclick="recuperarDetOrdenCompras()">Recuperar </a>
                    <a id="btnAnular" class="btn btn-lg btn-danger" style=" font-weight: bold" title="Anular Pedido">Anular*</a>
                    <a id="btnConfirmarr" class="btn btn-lg btn-warning glyphicon glyphicon-ok" style=" font-weight: bold" title="Confirmar Orden de Compras" onclick="actualizarestados()"</a>
                    <a id="btnRevertir" class="btn btn-lg btn-danger glyphicon glyphicon-minus-sign" style=" font-weight: bold" title="Revertir Confirmacion Orden de Compras" onclick=""></a>
                    <a id="btnReporte" class="btn btn-lg btn-primary glyphicon glyphicon-print" title="Reporte de Panilla" onclick="reportesOrdenCompras()"></a>

                </div>
                <br>
                <br>
                <br>

                <div class="panel panel-default">
                    <div class="panel-footer" style="font-weight: bold">Planilla Orden de Compras</div>

                    <div class="">
                        <div class="input-group  input-sm">
                            <span class="input-group-addon" style=" font-weight: bold">Nro. Registro*</span>
                            <input id="ordenNro" type="text" style="background-color: #d9edf7" class="form-control" disabled="" placeholder="Numero de Pedido">
                            <span class="input-group-addon" style=" font-weight: bold" >Estado*</span>
                            <input id="estadOrdenP" type="text" style="" class="form-control" disabled="" placeholder="Estado de Pedido">
                        </div>
                    </div>

                    <div class="">
                        <div class="input-group input-sm">
                            <span class="input-group-addon" style=" font-weight: bold">Buscar</span>
                            <input id="filtrarOrdenC" type="text" style="text-transform: uppercase; font-weight: bold" 
                                   class="form-control " maxlength="20" onkeyup="buscadorPlanillaOrdenComprasS()"placeholder="Buscar Pedido...">
                        </div>
                    </div>
                    <div class="panel-body">
                        <!-- Tabla detalle -->
                        <div id="scrollPlanilla" class="table-responsive">
                            <table class="table table-striped table-bordered table-hover table input-md" id="miTablaOrdenC" onclick="seleccionOrdenComprasS()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                <thead>
                                    <tr class="alert-dismissable" >
                                        <th class="alert-success">ID</th>
                                        <th class="alert-info">FECHA</th>
                                        <th class="alert-success">PROVEEDOR</th>
                                        <th class="alert-info">C. PEDIDO</th>
                                        <th class="alert-success">USUARIO</th>
                                        <th class="alert-danger">ESTADO</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody id="table_deta"></tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </form>
        </section>
        <div class="modal fade" id="ventanaOrdenCompra">
            <div class="modal-dialog" style="width: 1200px;">
                <div class="modal-content">

                    <!--HEADER Encabezamiento DE LA VENTANA--->

                    <div class="modal-header">
                        <a class="btn btn-lg btn-success col-md-1"  id="btnGuardar" title="" onclick="InsertarOrdenComprasS()" >Guardar</a>
                        <a class="close  btn btn-lg btn-danger glyphicon glyphicon-off" data-dismiss="modal" aria-hidden="true" title="Salir"></a>
                    </div>




                    <!--CONTENIDO DE LA VENTANA--->

                    <div class="panel">
                        <div class="panel panel-default">
                            <div class="panel-footer" style="font-weight: bold">Nueva Orden de Compra</div>
                            <br> 
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Nro.</label>  
                                    <div class="col-md-2">
                                        <input disabled="" id="codigo" style="text-transform: uppercase; font-weight: bold; font-size: 12pt" 
                                               name="codigo" type="text" placeholder="Registro" class="form-control input-sm alert-danger">
                                    </div>
                                    <label class="col-md-1 control-label">Fecha</label>  
                                    <div class="col-md-3">
                                        <input disabled id="fechaOrden" type="datetime" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               placeholder="Ingrese fecha" class="form-control input-sm alert-danger">
                                    </div>
                                    <label class="col-md-1 control-label">Estado</label>  
                                    <div class="col-md-3">
                                        <input disabled id="estadoOrden"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               placeholder="Ingrese estado" class="form-control input-sm alert-danger">
                                    </div>
                                    <div class="col-md-2">
                                        <input id="idestadOrden" style="visibility: hidden;" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Usuario</label>  
                                    <div class="col-md-2">
                                        <input disabled id="usuarioOrden" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               type="text" placeholder="Ingrese Usuario" class="form-control input-sm alert-danger">
                                    </div>
                                    <label class="col-md-1 control-label">Proveedor</label>  
                                    <div class="col-md-3">
                                        <input disabled="" id="proveeOrden" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" 
                                               type="text" placeholder="Ingrese Proveedor" class="form-control input-sm alert-danger" >
                                    </div>

                                    <label class="col-md-2 control-label">Nro. Presupuesto</label>  
                                    <div class="col-md-2">
                                        <input id="nroPresupuesto" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" 
                                               type="text" placeholder="Ingrese Pedidos" class="form-control input-sm " 
                                               onkeydown="
                                                       if (event.keyCode === 13) {
                                                           recuperarPresupuestoDetalle();
                                                       }">
                                    </div>

                                    <div class="col-md-2">
                                        <input id="idproveedor" style="visibility: hidden;" type="text">
                                    </div>
                                    <div class="col-md-1">
                                        <input  id="idusuaOrden" type="text" style="visibility: hidden;">
                                    </div>
                                </div>
                            </div>
                        </div>


                        <!--PARA CARGAR DETALLE DE ARTICULOS DE CABECERA--->
                        <!--DESDE PARA CARGAR DETALLE DE ARTICULOS DE CABECERA DE TIPO LISTA DE ARTICULO--->

                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label">Cod*</label>
                                <div class="col-md-1">
                                    <input list="listaarticulos"  name="listaarticulos "id="idartiGenerico" type="text" placeholder="Cod" class="form-control input-sm" onclick=""
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumerosS()" onchange="ValidacionesSoloNumerosS()"
                                           onkeydown="
                                                   if (event.keyCode === 13) {
                                                       getArti();
                                                   }">
                                    <datalist id="listaarticulos">
                                    </datalist>
                                </div>

                                <!--HASTA ACA PARA CARGAR DETALLE DE ARTICULOS DE CABECERA DE TIPO LISTA DE ARTICULO--->

                                <label class="col-md-1 control-label">Desciprción</label>
                                <div class="col-md-3">
                                    <input class="form-control input-sm" disabled="" id="iddescrip" type="text" placeholder="Descirpcion del articulo"
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6;font-size: 12pt"
                                           onkeyup="separadorMiles(this)" onchange="separadorMiles(this)">
                                </div>
                                <label class="col-md-1 control-label">Precio</label>
                                <div class="col-md-2">
                                    <input id="PrecioArti" disabled="" class="form-control input-sm" type="text" placeholder="Ingrese precio"
                                           style="text-transform: uppercase; font-weight: bold; background-color: #e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumerosS(this)" onchange="ValidacionesSoloNumerosS(this)">
                                </div>

                                <label class="col-md-1 control-label">Cant*</label>
                                <div class="col-md-1">
                                    <input id="idcanti" type="text" placeholder="Cant" maxlength="3" class="form-control input-sm" 
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumerosS(this)" onchange="ValidacionesSoloNumerosS(this)"
                                           onkeydown="
                                                   if (event.keyCode === 13) {
                                                       CargarArticulosGrilla();
                                                   }">
                                </div>
                                <!-- <div class="col-md-1">
                                    <input disabled="" id="idarti" type="text" placeholder="" maxlength="3" class="form-control input-sm" 
                                           style="visibility: hidden;">
                                </div>-->
                            </div>
                        </div>
                        <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div class="table-responsive" style="height: 180px">
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaDetOrdenCompras" onclick="">
                                    <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th>CODIGO</th>
                                            <th>DESCRIPCION</th>
                                            <th>PRECIO</th>
                                            <th>CANTIDAD</th>
                                            <th>SUB TOTAL</th>
                                            <th>OPCION</th>
                                        </tr>
                                    </thead>

                                </table>
                            </div>
                        </div>

                        <div class="col-xs-3 col-xs-offset-9 input-group input-group-sm">
                            <span class="input-group-addon">TOTAL :</span>
                            <input class="form-control" id="total" style="font-size: 15px" type="text">
                        </div>

                    </div>
                </div>
            </div> 
        </div>

        <!--modal ´para cargar VENTANA de articulos descripcion cantidad precios--->

        <div class="modal fade" id="grillaArti">
            <div class="modal-dialog" style="width: 600px;">
                <div class="modal-content">

                    <!--HEADER Encabezamiento DE LA VENTANA--->

                    <div class="modal-header">
                        <input class="btn btn-sm btn-success" type="button" value="INSERTAR" onclick="insertarArticulos()" />
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <DIV class="modal-body">
                        <div class="col-md-2">
                            <input id="codarti" style="font-weight: bold; font-size: 12pt" 
                                   type="text" placeholder="cod." class="form-control input-sm alert-success">
                        </div>
                        <div class="col-md-2">
                            <input id="codgenericoarti" style="font-weight: bold; font-size: 12pt" 
                                   type="text" placeholder="codG." class="form-control input-sm alert-success">
                        </div>
                        <div class="col-md-6">
                            <input id="descriarti" style="font-weight: bold; font-size: 12pt" 
                                   type="text" placeholder="Articulo descripcion.." class="form-control input-sm alert-success">
                        </div>
                        <div class="col-md-2">
                            <input id="precioarti" style="font-weight: bold; font-size: 12pt" 
                                   type="text" placeholder="Precio" class="form-control input-sm alert-success">
                        </div>
                        <br>
                        <br>

                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrarArti" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20" 
                                       class="form-control input-md" placeholder="Buscar Articulos..." onkeyup="buscadorTablaArticulos()">
                            </div>
                        </div>
                        <div class="panel-body">


                            <!-- Tabla detalle -->


                            <div id="scroll" class="table-responsive" style="height: 300px" >
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaArti"
                                       onclick="seleccionArticulosSS()">
                                    <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th style="display: none">Cod.</th>
                                            <th>Cod.Material</th>
                                            <th>Matarial</th>
                                            <th>Precio</th>
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
        <div class="modal fade" id="grillaProveed">
            <div class="modal-dialog" style="width: 400px;">
                <div class="modal-content">

                    <!--HEADER ENCABEZAMIENTOS DE LA VENTANA--->


                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <DIV class="modal-body">
                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrarProveedor" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20"
                                       class="form-control input-md" placeholder="Buscar Articulo..." onkeyup="buscadorTablaProveedoresS()">
                            </div>
                        </div>
                        <div class="panel-body">

                            <!-- Tabla detalle -->

                            <div id="scroll" class="table-responsive" style="height: 200px" >
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaProveedores" onclick="seleccionProveedoresS()">
                                    <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th>Proveedor</th>
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
        <div class="modal fade" id="grillaPedidos">
            <div class="modal-dialog" style="width: 600px;">
                <div class="modal-content">

                    <!--HEADER DE LA VENTANA--->

                    <div class="modal-header">
                        <div>Planilla de Pedidos</div>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>

                    <!--CONTENIDO DE LA VENTANA--->

                    <DIV class="modal-body">

                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrarPedi" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20" 
                                       class="form-control input-md" placeholder="Buscar Pediod..." onkeyup="buscadorTablaPedi())">
                            </div>
                        </div>
                        <div class="panel-body">

                            <!-- Tabla detalle -->
                            <div id="scrollPlanilla" class="table-responsive">
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaPedidos" onclick="seleccionPedidos()">
                                    <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th>ID</th>
                                            <th>FECHA</th>
                                            <th>USUARIO</th>
                                            <th class="alert-info">ESTADO</th>
                                        </tr>
                                    </thead>
                                    <tbody id="table_deta"></tbody>
                                </table>
                            </div>


                        </div>
                    </div>
                </div>
            </div> 
    </body>
</html>