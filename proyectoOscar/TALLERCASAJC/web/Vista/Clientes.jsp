<%-- 
    Document   : Clientes
    Created on : 28/08/2019, 09:39:42 AM
    Author     : Oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="stylesheet" href="../Recursos/css/estilos_1.css">
        <link rel="stylesheet" href="../Recursos/css/iconos.css">
        <link rel="stylesheet" href="../Recursos/css/bootstrap.css">
        <link rel="stylesheet" href="../Recursos/css/font-mfizz.css">
        <link rel="stylesheet" href="../Recursos/css/font-awesome.css">
        <link rel="stylesheet" href="../Recursos/css/bootstrap.css">
        <script type="text/javascript" src="../Recursos/js/jquery.js"></script>
        <script type="text/javascript" src="../validador/CLientesvalidad.js"></script>
        <script src="../Recursos/js/ImagenFondo.js"></script> 
        <script src="../Recursos/js/bootstrap.js"></script>
        <script src="../Recursos/js/main.js"></script>
        <title>CLIENTES</title>
    </head>
    <body>
        <center>
        <h1>CLIENTES</h1>
        <section>
            <form class="form-horizontal"  id="defaultForm">
                <div class="panel panel-default">              
                    <div class="panel-footer" style="font-weight: bold">GESTIONAR CLIENTES</div>

                    <div class="table-responsive" > 
                        <div class="headercontainer" >
                            <div class="tablecontainer">
                                <table class=" table-striped table-bordered table-hover table input-md" id="miTablaClientes" onclick="recuperar()">
                                    <thead>
                                        <tr>
                                            <th><div>ID</div></th>
                                            <th><div>RUC</div></th>
                                            <th><div>RAZON SOCIAL</div></th>
                                            <th><div>TELEFONO</div></th>
                                            <th><div>DIRECCION</div></th>
                                            <th><div>WEB</div></th>
                                            <th><div>BARRIOS</div></th>
                                            <th><div>CIUDADES</div></th>
                                        </tr>
                                    </thead>
                                    <tbody class="buscarClientes"></tbody>
                                </table>
                            </div>
                        </div>     
                    </div>

                    <div class="input-group">
                        <span class="input-group-addon">Buscar</span>
                        <input class="form-control" type="text" id="filtrarClientes" placeholder="Ingrese Descripcion a Buscar"
                               style="text-transform: uppercase; font-weight: bold; font-size:12pt; background-color: #d9edf7 ">
                    </div>
                    <br>
                    <div class="">
                        <div class="form-horizontal">
                            <div class="form-group">

                                <label class="col-md-1 control-label" style=" font-weight: bold">ID</label>  
                                <div class="col-md-3">
                                    <input disabled="" type="" id="idcliente" style="text-transform: uppercase; font-weight: bold; font-size: 12pt;
                                            background-color: #d9edf7 " type="text" placeholder="Registro" class="form-control input-sm" required=""
                                            onkeydown=" if (event.keyCode === 13) {
                                                        getmostrarClientesFiltro();
                                                    }">

                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">RUC</label>  
                                <div class="col-md-3">
                                    <input id="ruccli"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;
                                           background-color: #d9edf7" placeholder="Ingrese nombre" class="form-control input-sm" required autofocus="">
                                </div>

                                <label class="col-md-1 control-label" style=" font-weight: bold">RAZONSOCIAL</label>  
                                <div class="col-md-3">
                                    <input id="razonsocialcli"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;
                                           background-color: #d9edf7" placeholder="Ingrese apellido" class="form-control input-sm" required autofocus="">
                                </div>

                                <label class="col-md-1 control-label" style=" font-weight: bold">TELEFONO</label>  
                                <div class="col-md-3">
                                    <input id="telefonocli"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;
                                           background-color: #d9edf7" placeholder="Ingrese ci" class="form-control input-sm" required autofocus="">
                                </div>

                                <label class="col-md-1 control-label" style=" font-weight: bold">DIRECCION</label>  
                                <div class="col-md-3">
                                    <input id="direccioncli"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;
                                           background-color: #d9edf7" placeholder="Ingrese descripcion" class="form-control input-sm" required autofocus="">
                                </div>

                                <label class="col-md-1 control-label" style=" font-weight: bold">WEB</label>  
                                <div class="col-md-3">
                                    <input id="webcli"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;
                                           background-color: #d9edf7" placeholder="Ingrese descripcion" class="form-control input-sm" required autofocus="">
                                </div>

                                   <%--   COMBO BARRIOS y CIUDADES ACA EMPIEZA--%>
                                   
                                <label class="col-md-1 control-label" style=" font-weight: bold">BARRIOS</label>  
                                <div class="col-md-3" id="comboBarrios">
                                    <select id="idbarrio" type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;
                                            background-color: #d9edf7" class="form-control input-sm"></select>
                                </div>

                                <label class="col-md-1 control-label" style=" font-weight: bold">CIUDADES</label>  
                                <div class="col-md-3" id="comboCiudades">
                                    <select id="idciudad" type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;
                                            background-color: #d9edf8" class="form-control input-sm"></select>
                                </div>
                            </div>
                        </div> 
                    </div> 
                    <%--   COMBO BARRIOS y CIUDADES HASTA ACA--%>
                </div>

                <a id="btnNuevo" class="btn btn-lg btn-success" style=" font-weight: bold" onclick="getUltimoCodigoClientes()">Nuevo </a>
                <a id="btnInsertar" class="btn btn-lg btn-primary" style=" font-weight: bold" onclick="ControlarCampoClientes(), campovacioclientes()">Guardar </a>
                <a id="btnModificar" class="btn btn-lg btn-info" style=" font-weight: bold" title="Modificar" onclick="ambClientes(2)">Modificar </a>
                <a id="btnAnular" class="btn btn-lg btn-danger" style=" font-weight: bold" title="Borrar" onclick="ambClientes(3)">Borrar*</a>
                <a id="btnreporteP" class="btn btn-lg btn-success" style=" font-weight: bold" title="Reporte" onclick="reportesClientes()">Reporte*</a>

            </form>
        </section>
    </center>
    </body>
</html>
