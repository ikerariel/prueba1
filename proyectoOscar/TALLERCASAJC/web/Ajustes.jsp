<%-- 
    Document   : Ajustes
    Created on : 18/08/2019, 11:41:48 PM
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


        <title>AJUSTES</title>
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
                    <a id="btnNuevo" href="#ventanaajuste" class="btn btn-lg btn-success" style=" font-weight: bold"  title="Nuevo Pedido" data-toggle="modal" onclick="abrirVentanas()">Nuevo </a>
                    <a id="btnModificar" class="btn btn-lg btn-info" style=" font-weight: bold" title="Modificar Pedido" data-toggle="modal" onclick="recuperarDetAjustessss()">Modificar </a>
                    <a id="btnAnular" class="btn btn-lg btn-danger" style=" font-weight: bold" title="Anular Pedido" onclick="anularND(2)">Anular*</a>
                    <a id="btnConfirmar" class="btn btn-lg btn-warning glyphicon glyphicon-ok" style=" font-weight: bold" title="Confirmar Nota de Débito" onclick="AprobarAjustes(1)"></a>
                    <a id="btnRevertir" class="btn btn-lg btn-danger glyphicon glyphicon-minus-sign" style=" font-weight: bold" title="Revertir Confirmacion" onclick="AprobarAjustes(3)"></a>
                    <a id="btnReporte" class="btn btn-lg btn-primary glyphicon glyphicon-print" title="Reporte de Panilla" onclick="reportesNotasDebitos()"></a>

                </div>
                <br>
                <br>
                <br>

                <div class="panel panel-default">
                    <div class="panel-footer" style="font-weight: bold">Planilla de Ajustes</div>

                    <div class="">
                        <div class="input-group  input-sm">
                            <span class="input-group-addon" style=" font-weight: bold">Nro. Nota*</span>
                            <input id="v_nroAjustes" type="text" style="background-color: #d9edf7" class="form-control" disabled="" placeholder="Numero de Pedido">
                            <span class="input-group-addon" style=" font-weight: bold" >Estado*</span>
                            <input id="v_estadoAjustes" type="text" style="" class="form-control" disabled="" placeholder="Estado de Pedido">
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
                            <table class="table table-striped table-bordered table-hover table input-lg" id="miTablaAjustes" onclick="selectDetalleAjustes()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                <thead>
                                    <tr class="alert-dismissable" >
                                        <th>NRO. AJUSTES</th>
                                        <th>FECHA</th>
                                        <th style="background: #003eff">ESTADO</th>

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
        <div class="modal fade" id="ventanaajuste">
            <div class="modal-dialog" style="width: 1200px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <a class="close  btn btn-lg btn-danger glyphicon glyphicon-off" data-dismiss="modal" aria-hidden="true" title="Salir"></a>
                    </div>
                    <!--CONTENIDO DE LA VENTANA--->
                    <div class="modal-body">
                        <a class="btn btn-lg btn-primary col-lg-1"  id="btnGuardar" title="" onclick="InsertarAjustes()" >Guardar*</a>
                        <a class="btn btn-lg btn-success col-lg-1"  id="btnM" title="" onclick="updateCabecera()" >Modificar*</a>
                    </div>
                    <br>
                    <br>
                    <div class="panel">
                        <div class="panel panel-default">
                            <br>
                            <div class="panel-footer" style="font-weight: bold">Nueva Nota Debito</div>
                            <br> 
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label id="texND" class="col-md-1 control-label">Codigo</label>  
                                    <div class="col-md-2">
                                        <input  disabled="" id="codigoAjuste" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" name="codigo" type="text" placeholder="Registro" class="form-control input-sm" required=""  autofocus >
                                    </div>
                                    <label class="col-md-1 control-label">Fecha</label>  
                                    <div class="col-md-3">
                                        <input readonly="" id="fechaajuste" type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               placeholder="Ingrese fecha" class="form-control input-sm alert-danger">
                                    </div>
                                    <label class="col-md-1 control-label">Estado</label>  
                                    <div class="col-md-3">
                                        <input disabled id="estadoND"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" placeholder="PENDIENTE" value="PENDIENTE" class="form-control input-sm alert-danger">
                                    </div>
                                </div>





                            </div>

                            <div class="form-horizontal">
                                <div class="form-group">

                                    <label class="col-md-2 control-label">Tipo Ajuste.*</label>  
                                    <div class="col-md-5">
                                        <select class="form-control" id="TipoAjustes">
                                        </select>
                                    </div>

                                </div>

                                <div class="form-group">

                                    <label class="col-md-1 control-label">Obs.*</label>  
                                    <div class="col-md-7">
                                        <textarea maxlength="98" id="observAjustes" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"  class="form-control input-sm" required=""  autofocus onkeydown=""></textarea>
                                    </div>

                                </div>
                            </div>
                        </div>



                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label">Codigo</label>  
                                <div class="col-md-2">
                                    <input list="productoAjustes"  id="codproductoAjustes" type="text" 
                                           onkeydown="
                                                   if (event.keyCode === 13) {
                                                       getProdAjustesDescripcion();
                                                   }"
                                           style="text-transform: uppercase; font-weight: bold;font-size: 12pt" placeholder="" value="" class="form-control input-sm ">

                                    <datalist id="productoAjustes">
                                    </datalist>
                                </div>
                                <label class="col-md-2 control-label">Cantidad.*</label>  
                                <div class="col-md-3">
                                    <input maxlength="60" id="CantidadAjustes" type="number" 
                                           onkeydown="
                                                   if (event.keyCode === 13) {
                                                       agregarFilasAjustes();
                                                   }"
                                           style="text-transform: uppercase; font-weight: bold;font-size: 12pt"  class="form-control input-sm" required=""  autofocus onkeydown="">
                                </div>
                                <div class="col-md-1">
                                    <input  id="NCIdUsuario" type="text" style="visibility: hidden;">
                                </div>
                                <div class="col-md-1">
                                    <input  id="idcompraNC" type="text" style="visibility:hidden">
                                </div>
                            </div>
                        </div>


                        <div class="form-horizontal">
                            <div class="form-group">

                                <label class="col-md-2 control-label">Descripcion.*</label>  
                                <div class="col-md-5">
                                    <input maxlength="98" id="descriproductov" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"  class="form-control input-sm" required="" 
                                           readonly="">
                                </div>


                                <!--  -->

                            </div>
                        </div>

                        <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div class="table-responsive" style="height: 180px">
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaDetallesAjustes">
                                    <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th>CODIGO</th>
                                            <th>PRODUCTOS</th>
                                            <th>CANTIDAD</th>    
                                            <th style="display: none"></th>    
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
        <script src="validador/Ajustesvalidad.js"></script> 
    </body>
</html>
