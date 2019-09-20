
<%-- 
    Document   : FacturasCompras
    Created on : 05/09/2018, 10:29:16 AM
    Author     : user
--%>    

<!DOCTYPE html>
<html>
    <head>
        <%@include file="validaciones.jsp" %>
        <script src="validador/validadorFacturaventaa.js"></script>
        <title>PARAMETROS</title>
        <style type="text/css">
            #scrollPlanilla{
                overflow: scroll;
                height:100px;
            }
            #scroll{
                overflow: scroll;
                height:250px;
            }
            #scroll01{
                overflow: scroll;
                height:150px;
            }
            #scrollfac{
                overflow: scroll;
                height:120px;
            }
            #car1,#car2{
                height: 120px;
            }
            hr {
                height: 1px;
                background-color: black;
                position: relative;
                top: -20px;
            }


        </style>
    </head>
    <body>
        <%@include file="viwmenu.jsp" %>
        <section style="background: white">
            <form class="form-horizontal "  id="defaultForm">
                <div class="panel panel-footer" id="">
                    <div class="form-group">
                        <div class="col-md-4">
                            <a  data-toggle="modal"  title="Imprimir Factura"  id="btnNuevo" class="btn btn-lg btn-primary" onclick="grabartimbrado()" ><span class="glyphicon glyphicon-floppy-save"></span> Guardar</a>
                        </div>

                    </div>

                </div>
                <div class="panel panel-body">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label class="control-label col-md-2">Timbrado</label>
                            <div class="col-md-4">
                                <input class="form-control" id="_timbrado" maxlength="8" type="number">
                            </div>
                            <label class="control-label col-md-2">Fecha</label>
                            <div class="col-md-4">
                                <input class="form-control" id="_fecha" type="text" readonly="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-2">Nro. Expe.</label>
                            <div class="col-md-4">
                                <input class="form-control" id="_nroexpe" type="text">
                            </div>
                             <label class="control-label col-md-2">Fecha Vto.</label>
                            <div class="col-md-4">
                                <input class="form-control" id="_fechavto" type="date">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-2">Nro. Caja.</label>
                            <div class="col-md-4">
                                <input class="form-control" id="_nrocaja" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-2">Fact.Desde.</label>
                            <div class="col-md-4">
                                <input class="form-control" id="_facdesde" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-2">Fact.Hasta.</label>
                            <div class="col-md-4">
                                <input class="form-control" id="_fachasta" type="text">
                            </div>
                            <div class="col-md-4">
                                <a class="btn btn-lg btn-block btn-success" style="border-radius: 28%" onclick="generarparametros()">
                                AGREGAR =>>
                                </a>
                            </div>
                        </div>

                    </div>
                    <div class="col-md-6">
                       <div id="scroll" class="panel panel-footer">
                                <table class="table table-striped table-bordered table-hover table input-md"  id="tablaparametros"  onclick="seleccion()">
                                    <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr>
                                            <th>Secuencia *</th>
                                            <th>Nro. Esxpedición *</th>
                                            <th>Nro. Caja *</th>
                                            <th>Numeración *</th>


                                        </tr>
                                    </thead>
                                    <tbody id="table_deta" style="font-weight: bold; font-size: 13pt" >
                                    </tbody>
                                </table>
                            </div>
                    </div>
                            <div class="col-md-12">
                       <div id="scroll01" class="panel panel-footer">
                                <table class="table table-striped table-bordered table-hover table input-sm"  id="tabladetalleparametros"  onclick="seleccion()">
                                    <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr>
                                            <th>Nro Registro *</th>
                                            <th>Nro. Timbrado</th>
                                            <th>Fecha Alta *</th>
                                            <th>Fecha Vencimiento *</th>
                                            <th>Estado *</th>
                                            <th>Caja*</th>


                                        </tr>
                                    </thead>
                                    <tbody id="table_deta" style="font-weight: bold; font-size: 13pt" >
                                    </tbody>
                                </table>
                            </div>
                    </div>
                </div>
               


            </form>
        </section>

        <!--/////////////  CABECERAS VENTANA DE FACTURAS COMPRAS //////////////////////////////////////////--->

    </body>
</html>
