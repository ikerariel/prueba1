<%-- 
    Document   : EntidadEmisoras
    Created on : 31/07/2018, 11:23:09 AM
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
        <link rel="stylesheet" href="../Recursos/css/bootstrap1.css">
        <link rel="stylesheet" href="../Recursos/css/font-mfizz.css">
        <link rel="stylesheet" href="../Recursos/css/font-awesome.css">
        <link rel="stylesheet" href="../Recursos/css/bootstrap.css">
        <script type="text/javascript" src="../Recursos/js/jquery.js"></script>
        <script type="text/javascript" src="../validador/EntidadEmisorasvalidad.js"></script>
        <script src="../Recursos/js/jquery.backstretch.min.js"></script>
        <script src="../Recursos/js/ImagenFondo.js"></script> 
        <script src="../Recursos/js/bootstrap.js"></script>
        <script src="../Recursos/js/main.js"></script>

        <title>ENTIDAD EMISORAS</title>
    </head>
    <body>
        <center>
        <section>
            <form class="form-horizontal"  id="defaultForm">
                <div class="panel panel-default">              
                    <div class="panel-footer" style="font-weight: bold">GESTIONAR ENTIDAD EMISORAS</div>



                    <div class="table-responsive" > 
                        <div class="headercontainer" >
                            <div class="tablecontainer">
                                <table class=" table-striped table-bordered table-hover table input-md" id="miTablaEntidadEmisoras" onclick="recuperar()">
                                    <thead>
                                        <tr>
                                            <th><div>ID</div></th>
                                            <th><div>DESCRIPCION</div></th>
                                        </tr>
                                    </thead>
                                    <tbody class="buscarEntidadEmisoras"></tbody>
                                </table>
                            </div>
                        </div>     
                    </div>

                    <div class="input-group">
                        <span class="input-group-addon">Buscar</span>
                        <input class="form-control" type="text" id="filtrarEntidadEmisoras" placeholder="Ingrese Descripcion a Buscar"
                               style="text-transform: uppercase; font-weight: bold; font-size:12pt; background-color: #d9edf7 ">
                    </div>
                    <br>
                    <div class="">
                        <div class="form-horizontal">
                            <div class="form-group">

                                <label class="col-md-2 control-label" style=" font-weight: bold">ID</label>  
                                <div class="col-md-4">
                                    <input disabled="" type="" id="codentiemi" style="text-transform: uppercase; font-weight: bold; font-size: 12pt;
                                            background-color: #d9edf7 " type="text" placeholder="Registro" class="form-control input-sm" required=""
                                            onkeydown=" if (event.keyCode === 13) { recuperarEntidadEmisorasporID(); }">
                                    
                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">DECRIPCION</label>  
                                <div class="col-md-4">
                                    <input id="descrentiemi"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;
                                           background-color: #d9edf7" placeholder="Ingrese descripcion" class="form-control input-sm" required autofocus="">
                                </div>
                            </div>
                        </div> 
                    </div> 
                    
                    <!-- desde Aca Reporte -->
                        <div id="Reporte" class="w3-container w3-border city" style="display:none">
                            <div class="w3-container">
                                EntidadEmisoras <br>
                                <input type="button" value=" PDF" onclick="loadDocPdf(1, 0);" />
                            </div>
                        </div>
                        <!-- hasta Aca Reporte -->
                </div>

                <a id="btnNuevo" class="btn btn-lg btn-success" style=" font-weight: bold" onclick="getUltimoCodigoEntidadEmisoras()">Nuevo </a>
                <a id="btnInsertar" class="btn btn-lg btn-primary" style=" font-weight: bold" onclick="ControlarCampoEntidadEmisoras(), campovacioEntidadEmisoras()">Insertar </a>
                <a id="btnModificar" class="btn btn-lg btn-info" style=" font-weight: bold" title="Modificar" onclick="ambEntidadEmisoras(2)">Modificar </a>
                <a id="btnAnular" class="btn btn-lg btn-danger" style=" font-weight: bold" title="Borrar" onclick="ambEntidadEmisoras(3)">Borrar*</a>
                <a id="btnreporteP" class="btn btn-lg btn-success" style=" font-weight: bold" title="Reporte" onclick="reportesEntidadEmisoras()">Reporte*</a>

            </form>
        </section>
    </center>
    </body>
</html>
