<%-- 
    Document   : validaciones
    Created on : 20/03/2019, 06:40:52 PM
    Author     : Carlos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

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
<script src="Recursos/js/jquery.backstretch.min.js"></script>
<script src="Recursos/js/ImagenFondo.js"></script>

<!---------------------------------------------------------------------------------->
<!--------------------------VISTA PREVIA DE LA FACTURA------------------------------->
<!---------------------------------------------------------------------------------->
<div class="modal fade" id="facView" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" style="width: 1000px">
        <div class="modal-content">
            <!--HEADER DE LA VENTANA--->
            <div class="modal-header">
                <h2 class="modal-title" style="text-align: center;" id="textM"></h2>
            </div>
            <!--CONTENIDO DE LA VENTANA--->
            <div class="panel panel-footer">
                <a class="close btn btn-md btn-danger" data-dismiss="modal" aria-hidden="true" title="Salir">X</a>
            </div>
            <DIV class="modal-body">
                <form class="form-horizontal" id="miForm" >
                    <div class="form-group" >
                        <div  class="col-md-5"  >
                            <div style="border: 1px black solid; border-radius: 4%" id="car1">
                                <img class="col-md-5" src="Recursos/img/descarga1.jpg" width="120" height="110">
                                <h5><b>Electronica en Gral</b></h5>
                                <p><b>Casa JC</b></p>
                                <p>Petirosi 751 c/ Padre Levera  - Cel: 0981264974</p>
                            </div>
                        </div>
                        <div  class="col-md-5 col-md-push-2" style="text-align: center">
                            <div style="border: 1px black solid; border-radius: 4%"id="car2">
                                <h5><b>Timbrado Nº 12698787</b></h5>
                                <h6>Fecha Inicio: 02/09/2019</h6>
                                <h6>Fecha fin  02/12/2019</h6>
                                <h6><b>RUC 80034889-4</b></h6>
                                <h6 id="fac"><b>001-001 Nº 0025987</b></h6>

                            </div>
                        </div>
                    </div>
                    <div class="form-group" >
                        <div  class="col-md-12">
                            <div style="border: 1px black solid; border-radius: 4%"id="car2">
                                <div >
                                    <div class="col-md-1">
                                        <h5><b>Fecha:</b></h5>
                                    </div>
                                    <div class="col-md-3">
                                        <h5 id="fccha">02 de Setiembre del 2019</h5>
                                    </div>
                                    <div class="col-md-3">
                                        <h5><b>Condición de Venta:</b></h5>

                                    </div>
                                    <div style="padding: 5px">
                                        <label for="radio1">Contado</label> <input type="radio" name="opciono" id="radio1" > 
                                        <label for="radio1">Crédito</label> <input type="radio" name="opciono" id="radio1" > 
                                    </div>
                                    <div class="col-md-3">
                                        <h5><b>Nombre o Razon Social:</b></h5>
                                    </div>
                                    <div class="col-md-3">
                                        <h5 id="cliente">Oscar Fernandez</h5>
                                    </div>
                                    <div class="col-md-1">
                                        <h5><b>Ruc/o:</b></h5>
                                    </div>
                                    <div class="col-md-3">
                                        <h5 id="ruc">4782668</h5>
                                    </div>
                                    <div class="col-md-3" >
                                        <h5><b>Dirección:</b></h5>
                                    </div> 
                                    <div class="col-md-3 col-md-offset-0" >
                                        <h5>Dr. Rodriguez de Francia 200</h5>
                                    </div>


                                </div>




                            </div>
                            <div style="border: 1px black solid; border-radius: 4%"id="">
                                <div id="scrollfac" class="table-responsive">
                                    <table class="table table-striped table-bordered table-hover table input-sm" id="_tablefact" onclick="seleccionarFacturasCompras()">
                                        <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                        <thead>
                                            <tr>
                                                <th>Cant.</th>
                                                <th>Descripcion</th>
                                                <th>P. Unitario</th>
                                                <th>Exentas</th>
                                                <th>5%</th>
                                                <th>10%</th>
                                            </tr>
                                        </thead>
                                        <tbody id="table_deta" style="background: #ffffff; font-size: 12px;font-weight: bold"></tbody>
                                    </table>



                                </div>

                            </div>
                            <div class="col-md-8">
                                <h5 id=""><b>Subtotal:</b></h5>
                            </div>
                            <div class="col-md-1">
                                <h5 id="">0</h5>
                            </div>
                            <div class="col-md-1">
                                <h5 id="">0</h5>
                            </div>
                            <div class="col-md-1">
                                <h5 id="">0</h5>
                            </div>
                            <div class="col-md-2">
                                <h5 id="">Total a pagar </h5>
                            </div>
                            <div class="col-md-10">
                                <h5 id="">monto en letras</h5>
                            </div>
                            <br>
                            <div class="col-md-2">
                                <h5 id="">Liquidación IVA </h5>
                            </div>
                            <div class="col-md-2">
                                <h5 id="">(5%)</h5>
                            </div>
                            <div class="col-md-2">
                                <h5 id="">(10%)</h5>
                            </div>
                            <div class="col-md-2">
                                <h5 id="">Total Iva</h5>
                            </div>
                        </div>
                    </div>

                </form>   
            </div>
        </div>
    </div> 
</div>


<!---------------------------------------------------------------------------------->
<!--------------------------AGREGAR CLIENTE NUEVO----------------------------------->
<!---------------------------------------------------------------------------------->


<div class="modal fade" id="viewCliente" data-backdrop="static" data-keyboard="false">
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
                            <input class="form-control " id="ruc_v" type="text">
                        </div>
                        <div class="col-md-7">
                            <input class="form-control " id="cliente_v" type="text">
                        </div>
                    </div>
                    <div class="form-group" >
                        <div class="col-md-12">
                            <a class="btn btn-lg btn-default btn-block " onclick="grabarcliente()" >
                                <span class="glyphicon glyphicon-check"></span> Guardar</a>
                        </div>

                    </div>

                </form>   
            </div>
        </div>
    </div> 
</div>

<!---------------------------------------------------------------------------------->
<!--------------------------ANULAR ---------- FACTURA------------------------------->
<!---------------------------------------------------------------------------------->

<div class="modal fade" id="anufactura" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" style="width: 800px">
        <div class="modal-content">
            <!--HEADER DE LA VENTANA--->
            <div class="modal-header">
                <h4 class="modal-title" style="text-align: center;" id="textM">Anular Factura</h4>
            </div>
            <!--CONTENIDO DE LA VENTANA--->
            <div class="panel panel-footer">
                <a class="close btn btn-md btn-danger" data-dismiss="modal" aria-hidden="true" title="Salir">X</a>
            </div>
            <DIV class="modal-body">
                <form class="form-horizontal" id="miForm" >
                    <div class="form-group" >
                        <label class="control-label col-md-2">Nro.Factura :</label>
                        <div class="col-md-4">
                            <input class="form-control " id="nrofactura_v" type="text">
                        </div>

                    </div>
                    <div class="form-group" >
                        <div class="col-md-12">
                            <a class="btn btn-lg btn-danger btn-block " onclick="anularfactura()" >
                                <span class="glyphicon glyphicon-check"></span> Anular</a>
                        </div>

                    </div>

                </form>   
            </div>
        </div>
    </div> 
</div>

<!---------------------------------------------------------------------------------->
<!--------------------------APERTURA DE CAJA---------------------------------------->
<!---------------------------------------------------------------------------------->


<div class="modal fade" id="aperturacaja" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" style="width: 800px">
        <div class="modal-content">
            <!--HEADER DE LA VENTANA--->
            <div class="modal-header">
                <h4 class="modal-title" style="text-align: center;" id="textM">Apertua Caja</h4>
            </div>
            <!--CONTENIDO DE LA VENTANA--->
            <div class="panel panel-footer">
                <a class="close btn btn-md btn-danger" data-dismiss="modal" aria-hidden="true" title="Salir">X</a>
            </div>
            <DIV class="modal-body">
                <form class="form-horizontal" id="miForm" >
                    <div class="form-group" >
                        <label class="control-label col-md-3">Monto Apertura :</label>
                        <div class="col-md-3">
                            <input class="form-control " id="montoapertura_ap" onkeyup="verificarvariable('montoapertura_ap');puntodecimal('montoapertura_ap')" type="text">
                        </div>
                        <label class="control-label col-md-2">Caja :</label>
                        <div class="col-md-2">
                            <select class="form-control" id="codigocaja_ap">
                                <option value="1">CAJA 1</option>
                                <option value="2">CAJA 2</option>
                            </select>
                        </div>

                    </div>
                    <div class="form-group" >
                        <label class="control-label col-md-1">Cajero:</label>
                        <div class="col-md-3">
                            <select class="form-control" id="codigocajero_ap">
                                <option value="1">ivanfernandez</option>
                                <option value="2">Alberto</option>
                            </select>
                        </div>
                        <label class="control-label col-md-3">Timbrado Nro:</label>
                        <div class="col-md-2">
                            <input list="listatimbrado"  name="listatimbrado "id="idtimbrado_vp" type="text" 
                                   placeholder="Cod" class="form-control"
                                   onkeydown="
                                       if (event.keyCode === 13) {
                                           obtnertimbrado();
                                       }">
                            <datalist id="listatimbrado">
                            </datalist>
                        </div>
                        <div class="col-md-3">
                            <input class="form-control " id="idtimbrado_ap" type="text" readonly="">
                        </div>


                    </div>
                    <div class="form-group" >
                        <div class="col-md-12">
                            <a class="btn btn-lg btn-primary btn-block " onclick="insertarApetura()" >
                                <span class="glyphicon glyphicon-check"></span> Guardar</a>
                        </div>

                    </div>

                </form>   
            </div>
        </div>
    </div> 
</div>

<!---------------------------------------------------------------------------------->
<!--------------------------CIERRE DE CAJA------------------------------------------>
<!---------------------------------------------------------------------------------->


<div class="modal fade" id="cierrecaja" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" style="width: 800px">
        <div class="modal-content">
            <!--HEADER DE LA VENTANA--->
            <div class="modal-header">
                <h4 class="modal-title" style="text-align: center;" id="textM">Cierre Caja</h4>
            </div>
            <!--CONTENIDO DE LA VENTANA--->
            <div class="panel panel-footer">
                <a class="btn btn-md btn-primary"  onclick="insertarMovimientoapertura()"  title="guardar">Guardar</a>
                <a class="close btn btn-md btn-danger" data-dismiss="modal" aria-hidden="true" title="Salir">X</a>
            </div>
            <DIV class="modal-body">
                <form class="form-horizontal" id="miForm" >
                    <div class="form-group" >
                        <label class="control-label col-md-2">Monedas :</label>
                        <div class="col-md-4">
                            <select class="form-control" id="tiposmonedasarqueo" onchange="seleccionchequetarjeta()">

                                <option value="1">MONEDA 50</option>
                                <option value="2">MONEDA 100</option>
                                <option value="3">MONEDA 500</option>
                                <option value="4">MONEDA 1.000</option>
                                <option value="5">BILLETE 2.000</option>
                                <option value="6">BILLETE 5.000</option>
                                <option value="7">BILLETE 10.000</option>
                                <option value="8">BILLETE 20.000</option>
                                <option value="9">BILLETE 50.000</option>
                                <option value="10">BILLETE 100.000</option>
                                <option value="11" style="font-weight: bold">CHEQUE</option>
                                <option value="12" style="font-weight: bold">TARJETA CREDITO/DEBITO</option>
                            </select>
                        </div><label class="control-label col-md-1 col-xs-pull-1">Gs.</label>
                        <label class="control-label col-md-2">Cant :</label>
                        <div class="col-md-2">
                            <input class="form-control " id="cantimoneda_v" type="number"
                                   onkeydown="
                                           if (event.keyCode === 13) {
                                               agregarfilaarqueo();
                                           }">
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2" id="textchetarjeta_v">Nro. Cheque :</label>
                        <div class="col-md-3">
                            <input class="form-control" type="text" id="nrotarjetacheque_v" disabled="">
                        </div>
                        <label class="control-label col-md-2">Monto :</label>
                        <div class="col-md-3">
                            <input class="form-control" type="text" id="montochquetarjeta_v" disabled=""
                                   onkeydown="if (event.keyCode === 13) {
                                               agregarfilaarqueo();
                                           }">
                        </div>
                    </div>
                    <div class="form-group" id="scrollcierre">
                        <div >
                            <table class="table table-hover table-bordered table-striped" id="tablearqueo" >
                                <thead>
                                    <tr>
                                        <th>idMoneda</th>
                                        <th>Moneda</th>
                                        <th>Cant.</th>
                                        <th>Nro. Cheque/Tarjeta</th>
                                        <th>Total.</th>
                                    </tr>
                                </thead>

                                <tbody>

                                </tbody>

                            </table>


                        </div>

                    </div>
                    <div class="form-group" >
                        <label class="control-label col-md-2 col-lg-offset-7">Total :</label>
                        <div class="col-md-3">
                            <input class="form-control " id="totalarqueo_v" type="text">
                        </div>

                    </div>

                    <div class="form-group" >
                        <div class="col-md-12">
                            <a class="btn btn-lg btn-danger btn-block " onclick="anularfactura()" >
                                <span class="glyphicon glyphicon-check"></span> Cerrar</a>
                        </div>

                    </div>

                </form>   
            </div>
        </div>
    </div> 
</div>

<!---------------------------------------------------------------------------------->
<!---------------------------------------------COBROS------------------------------->
<!---------------------------------------------------------------------------------->


<div class="modal fade" id="cobroview" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" style="width: 1000px">
        <div class="modal-content">
            <!--HEADER DE LA VENTANA--->
            <div class="modal-header">
                <h4 class="modal-title" style="text-align: center;" id="textM">Cobros</h4>
            </div>
            <!--CONTENIDO DE LA VENTANA--->
            <div class="panel panel-footer">
                <a class="close btn btn-md btn-danger" data-dismiss="modal" aria-hidden="true" title="Salir">X</a>
                <a class="btn btn-md btn-primary" onclick=" guardarventa()"><span class="glyphicon glyphicon-floppy-save"></span> Guardar</a>
            </div>
            <DIV class="modal-body">
                <form class="form-horizontal" id="miForm" >
                    <div class="form-group">
                        <label class="control-label col-md-2">Fact Nro.</label>
                        <div class="col-md-2">
                            <input class="form-control" type="text" id="factura_cobro" disabled="">
                        </div>
                        <label class="control-label col-md-2">Tipo de Cobro</label>
                        <div class="col-md-3">
                            <select class="form-control" id="v_tipocobro" onchange="condicionCobro()">
                                <option value="1">Efectivo</option>
                                <option value="2">Tarjeta</option>
                                <option value="3">Cheque</option>
                            </select>
                        </div>
                        <label class="control-label col-md-1">Total.</label>
                        <div class="col-md-2">
                            <input class="form-control" type="text" id="v_totalcobro" disabled="">
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">Cliente.</label>
                        <div class="col-md-2">
                            <input class="form-control" type="text" id="v_clienteci" disabled="">
                        </div>
                        <div class="col-md-6">
                            <input class="form-control" type="text" id="v_clientenombre" disabled="">
                        </div>

                    </div>
                    <div class="">
                        <strong class="alert-warning" id="texcobro_v">Cobro por Cheque</strong>

                        <div class="form-group panel panel-footer" id="v_chque" style="display:none">
                            <label class="control-label col-md-2 ">Nro.Cheque.</label>
                            <div class="col-md-2">
                                <input class="form-control" type="text" id="nrochque_ch" >
                            </div>
                            <label class="control-label col-md-1">Banco.</label>
                            <div class="col-md-3">
                                <select class="form-control"  id="banco_che">
                                    <option value="1">ITAU</option>
                                    <option value="2">VISION</option>
                                    <option value="3">FAMILIAR</option>
                                    <option value="4">REGIONAL</option>
                                    <option value="5">CONTINENTAL</option>
                                </select>
                            </div>
                            <label class="control-label col-md-2">Tipo Cheque.</label>
                            <div class="col-md-2">
                                <select class="form-control" id="tipocheque_ch" >
                                    <option value="1">AL DIA</option>
                                    <option value="2">DIFERIDO</option>

                                </select>
                            </div>
                        </div>
                        <div class="form-group panel panel-footer" id="v_tarjeta" style="display:none">
                            <label class="control-label col-md-2">Ent. Emisora</label>
                            <div class="col-md-2">
                                <select class="form-control" id="entemisora_t"  >
                                    <option value="4">VISA</option>
                                    <option value="5">MASTERCARD</option>
                                    <option value="6">AMERICA ESPRESS</option>
                                </select>
                            </div>
                            <label class="control-label col-md-1">Tipo Tarjeta.</label>
                            <div class="col-md-3">
                                <select class="form-control" id="tarjettipo_t"  >
                                    <option value="1">CRÉDITO</option>
                                    <option value="2">DÉDBITO</option>

                                </select>
                            </div>
                            <label class="control-label col-md-2">Nro.Boleta.</label>
                            <div class="col-md-2">
                                <input class="form-control" type="text" id="nroboleta_t">
                            </div>
                        </div>

                        <div class="form-group panel panel-primary panel-footer">
                            <label class="control-label col-md-3">Monto a Cobrar.</label>
                            <div class="col-md-3">
                                <input class="form-control" type="text" id="v_montocobrar" onkeyup=" valores('v_montocobrar')"
                                       onkeydown="
                                               if (event.keyCode === 13) {
                                                   agregarfilacobro();
                                               }">
                            </div>
                            <div class="col-md-2">
                                <a class="btn btn-block btn-primary"><span class="glyphicon glyphicon-download"></span></a>
                            </div>

                        </div>
                        <div class="form-group" id="scrollcobro">
                            <div >
                                <table class="table table-hover table-bordered table-striped" id="tabladetallecobros">
                                    <thead>
                                        <tr>
                                            <td style="display: none">idtipocobro</td>
                                            <td >Tipo Cobro</td>
                                            <td>Nro. Cheque</td>
                                            <td style="display: none">idbancocheque</td>
                                            <td>Banco Chque</td>
                                            <td style="display: none">idtipotarjeta</td>
                                            <td style="display: none">identidademisora</td>
                                            <td>entidademisora</td>
                                            <td>Tipo Tarjeta</td>
                                            <td>Nro. Boleta</td>
                                            <td>Monto</td>
                                            <td>Estado</td>
                                            <td style="display: none">idtipochque</td>
                                            <td style="display: none">tipocheque</td>
                                        </tr>
                                    </thead>

                                    <tbody></tbody>
                                </table>
                            </div>

                        </div>
                    </div>

                    <div class="form-group" >
                        <label class="control-label col-md-6 col-lg-pull-0">Total Cobro :</label>
                        <div class="col-md-2">
                            <input class="form-control " id="totalcobro_v" type="text" disabled="">
                        </div>
                        <label class="control-label col-md-2 col-lg-offset-0">Diferencia :</label>
                        <div class="col-md-2">
                            <input class="form-control " id="diferencia_v" type="text" disabled="">
                        </div>



                    </div>

                </form>   
            </div>
        </div>
    </div> 
</div>