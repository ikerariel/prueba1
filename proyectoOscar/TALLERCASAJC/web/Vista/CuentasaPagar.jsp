<%-- 
    Document   : CuentasaPagar
    Created on : 18/10/2018, 09:15:57 AM
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
        <script type="text/javascript" src="../validador/CuentasaPagarvalidad.js"></script>
        <script src="../Recursos/js/ImagenFondo.js"></script> 
        <script src="../Recursos/js/bootstrap.js"></script>
        <script src="../Recursos/js/main.js"></script>
        <title>CUENTAS A PAGAR</title>
    </head>
    <body>
    <center>
        <section>
            <form class="form-horizontal"  id="defaultForm">
                <div class="panel panel-default">              
                    <div class="panel-footer" style="font-weight: bold">GESTIONAR CUENTAS A PAGAR</div>

                    <div class="table-responsive" > 
                        <div class="headercontainer" >
                            <div class="tablecontainer">
                                <table class=" table-striped table-bordered table-hover table input-md" id="miTablaCuentasaPagar" onclick="recuperarCuentasaPagar()">
                                    <thead>
                                        <tr>
                                            <th><div>ID</div></th>
                                            <th><div>FECHA VENCIMIENTO</div></th>
                                            <th><div>SALDO</div></th>
                                            <th><div>MONTO</div></th>
                                            <th><div>ID COMPRA</div></th>
                                            <th><div>ID ESTADOS</div></th>
                                        </tr>
                                    </thead>
                                    <tbody class="buscarCuentasaPagar"></tbody>
                                </table>
                            </div>
                        </div>     
                    </div>

                    <div class="input-group">
                        <span class="input-group-addon">Buscar</span>
                        <input class="form-control" type="text" id="filtrarCuentasaPagar" placeholder="Ingrese Descripcion a Buscar"
                               style="text-transform: uppercase; font-weight: bold; font-size:12pt; background-color: #d9edf7 ">
                    </div>
                    <br>
                    <div class="">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label" style=" font-weight: bold">ID:</label>  
                                <div class="col-md-1">
                                    <input  id="idcuentpag" style="text-transform: uppercase; font-weight: bold; font-size: 8pt;
                                            background-color: #d9edf7 " type="text" placeholder="Registro" class="form-control input-sm" required=""
                                            onkeydown=" if (event.keyCode === 13) {
                                                        getmostrarCuentasaPagarFiltro();
                                                    }">
                                </div>

                                <label class="col-md-1 control-label" style=" font-weight: bold">FECHA_VENCE:  </label>  
                                <div class="col-md-3">
                                    <input id="cuentpagFevence"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 8pt;
                                           background-color: #d9edf7" placeholder="Ingrese fecha vencimiento" class="form-control input-sm" required autofocus="">
                                </div>

                                <label class="col-md-1 control-label" style=" font-weight: bold">SALDO:</label>  
                                <div class="col-md-2">
                                    <input id="cuentpagSaldo"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 8pt;
                                           background-color: #d9edf7" placeholder="Ingrese saldo" class="form-control input-sm" required autofocus="">
                                </div>

                                <label class="col-md-1 control-label" style=" font-weight: bold">MONTO:</label>  
                                <div class="col-md-2">
                                    <input id="cuentpagmonto"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 8pt;
                                           background-color: #d9edf7" placeholder="Ingrese monto" class="form-control input-sm" required autofocus="">
                                </div>

                                <%--   COMBO FACTURA Y ESTADOS DESDE ACA--%>

                                <label class="col-md-1 control-label" style=" font-weight: bold">   FAC_COMPRAS:  </label>  
                                <div class="col-md-2" id="comboFacturasCompras">
                                    <select id="idcompra" type="text" style="text-transform: uppercase; font-weight: bold;font-size: 8pt;
                                            background-color: #d9edf7" class="form-control input-sm"></select>
                                </div>

                                <label class="col-md-1 control-label" style=" font-weight: bold">ESTADOS:</label>  
                                <div class="col-md-2" id="comboMarcas">
                                    <select id="idestado" type="text" style="text-transform: uppercase; font-weight: bold;font-size: 8pt;
                                            background-color: #d9edf7" class="form-control input-sm"></select>
                                </div>
                            </div>
                        </div> 

                        <%--   COMBO CIUDADES HASTA ACA--%>

                    </div>
                </div>

                <a id="btnNuevo" class="btn btn-lg btn-success" style=" font-weight: bold" onclick="getUltimoCodigoCuentasaPagar()">Nuevo </a>
                <a id="btnInsertar" class="btn btn-lg btn-primary" style=" font-weight: bold" onclick="ambCuentasaPagar(1)">Insertar </a>
                <a id="btnModificar" class="btn btn-lg btn-info" style=" font-weight: bold" title="Modificar" onclick="ambCuentasaPagar(2)">Modificar </a>
                <a id="btnAnular" class="btn btn-lg btn-danger" style=" font-weight: bold" title="Borrar" onclick="ambCuentasaPagar(3)">Borrar*</a>

            </form>
        </section>
    </center>
</body>
</html>
