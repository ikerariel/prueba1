<%-- 
    Document   : Proveedores
    Created on : 13/08/2018, 02:45:15 AM
    Author     : user
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
        <script type="text/javascript" src="../validador/Proveedoresvalidad.js"></script>
        <script src="../Recursos/js/ImagenFondo.js"></script> 
        <script src="../Recursos/js/bootstrap.js"></script>
        <script src="../Recursos/js/main.js"></script>
        <title>PROVEEDORES</title>
    </head>
    <body>
       <center>
        <section>
            <form class="form-horizontal"  id="defaultForm">
                <div class="panel panel-default">              
                    <div class="panel-footer" style="font-weight: bold">GESTIONAR PROVEEDORES</div>

                    <div class="table-responsive" > 
                        <div class="headercontainer" >
                            <div class="tablecontainer">
                                <table class=" table-striped table-bordered table-hover table input-md" id="miTablaProveedores" onclick="recuperar()">
                                    <thead>
                                        <tr>
                                            <th><div>ID</div></th>
                                            <th><div>RASON SOCIAL</div></th>
                                            <th><div>DIRECCION</div></th>
                                            <th><div>PAG WEB</div></th>
                                            <th><div>TELEFONO</div></th>
                                            <th><div>RUC</div></th>
                                            <th><div>CIUDADES</div></th>
                                        </tr>
                                    </thead>
                                    <tbody class="buscarProveedores"></tbody>
                                </table>
                            </div>
                        </div>     
                    </div>

                    <div class="input-group">
                        <span class="input-group-addon">Buscar</span>
                        <input class="form-control" type="text" id="filtrarProveedores" placeholder="Ingrese Descripcion a Buscar"
                               style="text-transform: uppercase; font-weight: bold; font-size:12pt; background-color: #d9edf7 ">
                    </div>
                    <br>
                    <div class="">
                        <div class="form-horizontal">

                            <div class="form-group">
                              
                                    <label class="col-md-1 control-label" style=" font-weight: bold">ID: </label>  
                                    <div class="col-md-3">
                                        <input disabled="" type="" id="idproveedor" style="text-transform: uppercase; font-weight: bold; font-size: 12pt;
                                                background-color: #d9edf7 " type="text" placeholder="Registro" class="form-control input-sm" required=""
                                                onkeydown=" if (event.keyCode === 13) {
                                                            getmostrarProveedoresFiltro();
                                                        }">

                                    </div>
                                    <label class="col-md-1 control-label" style=" font-weight: bold">RAS_SOCIAL: </label>  
                                    <div class="col-md-3">
                                        <input id="provrassocial"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 10pt;
                                               background-color: #d9edf7" placeholder="Ingrese ras_social" class="form-control input-sm" required autofocus="">
                                    </div>
                               
                              
                                    <label class="col-md-1 control-label" style=" font-weight: bold">DIRECCION: </label>  
                                    <div class="col-md-3">
                                        <input id="provdireccion"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 10pt;
                                               background-color: #d9edf7" placeholder="Ingrese direccion" class="form-control input-sm" required autofocus="">
                                    </div>

                                    <label class="col-md-1 control-label" style=" font-weight: bold">PAG WEB: </label>  
                                    <div class="col-md-3">
                                        <input id="provpagweb"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 10pt;
                                               background-color: #d9edf7" placeholder="Ingrese pagweb" class="form-control input-sm" required autofocus="">
                                    </div>
                               
                                
                               
                                    <label class="col-md-1 control-label" style=" font-weight: bold">TELEFONO: </label>  
                                    <div class="col-md-3">
                                        <input id="provtelefono"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 10pt;
                                               background-color: #d9edf7" placeholder="Ingrese telefono" class="form-control input-sm" required autofocus="">
                                    </div>

                                    <label class="col-md-1 control-label" style=" font-weight: bold">RUC: </label>  
                                    <div class="col-md-3">
                                        <input id="provruc"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 10pt;
                                               background-color: #d9edf7" placeholder="Ingrese ruc" class="form-control input-sm" required autofocus="">
                                    </div>
                               

                                <label class="col-md-1 control-label" style=" font-weight: bold">CIUDADES: </label>  
                                <div class="col-md-3">
                                    <select id="idciudad" type="text" style="text-transform: uppercase; font-weight: bold;font-size: 10pt;
                                            background-color: #d9edf7" class="form-control input-sm"></select>
                                </div>
                            </div>
                        </div> 
                    </div> 
                    <%--   COMBO CIUDADES HASTA ACA--%>
                </div>

                <a id="btnNuevo" class="btn btn-lg btn-success" style=" font-weight: bold" onclick="getUltimoCodigoProveedores()">Nuevo </a>
                <a id="btnInsertar" class="btn btn-lg btn-primary" style=" font-weight: bold" onclick="ControlarCampoProveedores(), campovacioproveedores() ">Guardar </a>
                <a id="btnModificar" class="btn btn-lg btn-info" style=" font-weight: bold" title="Modificar" onclick="ambProveedores(2)">Modificar </a>
                <a id="btnAnular" class="btn btn-lg btn-danger" style=" font-weight: bold" title="Borrar" onclick="ambProveedores(3)">Borrar*</a>
                <a id="btnreporteP" class="btn btn-lg btn-success" style=" font-weight: bold" title="Reporte" onclick="reportesProveedor()">Reporte*</a>

            </form>
        </section>
    </center>
    </body>
</html>
