<%-- 
    Document   : Depositos
    Created on : 01/08/2018, 11:06:02 AM
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
        <script type="text/javascript" src="../validador/Depositosvalidad.js"></script>
        <script src="../Recursos/js/jquery.backstretch.min.js"></script>
        <script src="../Recursos/js/ImagenFondo.js"></script> 
        <script src="../Recursos/js/bootstrap.js"></script>
        <script src="../Recursos/js/main.js"></script>

        <title>DEPOSITOS</title>
    </head>
    <body>
    <center>
        <section>
            <form class="form-horizontal"  id="defaultForm">
                <div class="panel panel-default">              
                    <div class="panel-footer" style="font-weight: bold">GESTIONAR DEPOSITOS</div>

                    <div class="table-responsive" > 
                        <div class="headercontainer" >
                            <div class="tablecontainer">
                                <table class=" table-striped table-bordered table-hover table input-md" id="miTablaDepositos" onclick="recuperar()">
                                    <thead>
                                        <tr>
                                            <th><div>ID</div></th>
                                            <th><div>DEPOSITOS</div></th>
                                            <th><div>SUCURSALES</div></th>
                                        </tr>
                                    </thead>
                                    <tbody class="buscarDepositos"></tbody>
                                </table>
                            </div>
                        </div>     
                    </div>
                    <br>
                    <div class="input-group">
                        <span class="input-group-addon">Buscar</span>
                        <input class="form-control" type="text" id="filtrarDepositos" placeholder="Ingrese Descripcion a Buscar"
                               style="text-transform: uppercase; font-weight: bold; font-size:12pt; background-color: #d9edf7 ">
                    </div>
                    <br>
                    <div class="">
                        <div class="form-horizontal">
                            <div class="form-group">

                                <label class="col-md-1 control-label" style=" font-weight: bold">ID</label>  
                                <div class="col-md-3">
                                    <input disabled="" type="" id="iddeposito" style="text-transform: uppercase; font-weight: bold; font-size: 12pt;
                                            background-color: #d9edf7 " type="text" placeholder="Registro" class="form-control input-sm" required=""
                                            onkeydown=" if (event.keyCode === 13) {
                                                        getmostrarDepositosFiltro();
                                                    }">

                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">DECRIPCION</label>  
                                <div class="col-md-3">
                                    <input id="descrideposito"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;
                                           background-color: #d9edf7" placeholder="Ingrese descripcion" class="form-control input-sm" required autofocus="">
                                </div>
                                <%--   COMBO DEPOSITOS DESDE ACA--%>
                                <label class="col-md-1 control-label" style=" font-weight: bold">SUCURSALES</label>  
                                <div class="col-md-2" id="comboSucursales">
                                    <select id="idsucursal" type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;
                                            background-color: #d9edf7" class="form-control input-sm"></select>
                                </div>
                            </div>
                        </div> 
                    </div> 
                    <%--   COMBO DEPOSITOS HASTA ACA--%>
                </div>

                <a id="btnNuevo" class="btn btn-lg btn-success" style=" font-weight: bold" onclick="getUltimoCodigoDepositos()">Nuevo </a>
                <a id="btnInsertar" class="btn btn-lg btn-primary" style=" font-weight: bold" onclick="ControlarCampoDepositos(), campovacioDepositos()">Guardar </a>
                <a id="btnModificar" class="btn btn-lg btn-info" style=" font-weight: bold" title="Modificar" onclick="ambDepositos(2)">Modificar </a>
                <a id="btnAnular" class="btn btn-lg btn-danger" style=" font-weight: bold" title="Borrar" onclick="ambDepositos(3)">Borrar*</a>
                <a id="btnreporte" class="btn btn-lg btn-success" style=" font-weight: bold" title="Reporte" onclick="reportesDepositos()">Reporte*</a>

            </form>
        </section>
    </center>
</body>
</html>
