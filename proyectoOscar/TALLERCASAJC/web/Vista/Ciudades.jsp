<%-- 
    Document   : Ciudades
    Created on : 23/07/2018, 12:06:12 AM
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
        <script type="text/javascript" src="../validador/Ciudadesvalidad.js"></script>
        <script src="../Recursos/js/jquery.backstretch.min.js"></script>
        <script src="../Recursos/js/ImagenFondo.js"></script> 
        <script src="../Recursos/js/bootstrap.js"></script>
        <script src="../Recursos/js/main.js"></script>
        <script src="../reportesCiudad_v.jsp"></script>

        <title>CIUDADES</title>

        <script type="text/javascript">

            function loadDocPdf(bandera, valor) {
                var datos;
                var xhr = new XMLHttpRequest();

                xhr.open("GET", "/TRABAJOOSCAR/gestorReportesCTRL?bandera=" + bandera + "&valor=" + valor + "");
                xhr.responseType = "arraybuffer";

                xhr.onload = function () {
                    if (this.status === 200) {
                        var blob = new Blob([xhr.response], {type: "application/pdf"});
                        var objectUrl = URL.createObjectURL(blob);
                        window.open(objectUrl, '_blank');
                    }
                };
                xhr.send();
                // xhr.send(JSON.stringify(datos = {bandera: bandera}));
                // xhr.send(JSON.stringify(datos = {bandera: 2, id: $('#codigo_presupuesto').val()}));
            }
        </script>
    </head>
    <body>
    <center>
        <section>
            <form class="form-horizontal"  id="defaultForm">
                <div class="panel panel-default">              
                    <div class="panel-footer" style="font-weight: bold">GESTIONAR CIUDADES</div>



                    <div class="table-responsive" > 
                        <div class="headercontainer" >
                            <div class="tablecontainer">
                                <table class=" table-striped table-bordered table-hover table input-md" id="miTablaCiudades" onclick="recuperar()">
                                    <thead>
                                        <tr>
                                            <th><div>ID</div></th>
                                            <th><div>DESCRIPCION</div></th>
                                        </tr>
                                    </thead>
                                    <tbody class="buscarCiudades"></tbody>
                                </table>
                            </div>
                        </div>     
                    </div>

                    <div class="input-group">
                        <span class="input-group-addon">Buscar</span>
                        <input class="form-control" type="text" id="filtrarCiudades" placeholder="Ingrese Descripcion a Buscar"
                               style="text-transform: uppercase; font-weight: bold; font-size:12pt; background-color: #d9edf7 ">
                    </div>
                    <br>
                    <div class="">
                        <div class="form-horizontal">
                            <div class="form-group">

                                <label class="col-md-2 control-label" style=" font-weight: bold">ID</label>  
                                <div class="col-md-4">
                                    <%-- <input disabled="" type="" id="codciudad" name="codciudad" style="display: none">--%>
                                    <%-- <input disabled="" type="" id="codciudad" name="codciudad" style="display: none">--%>
                                    <%-- <input  id="codciudad" style="text-transform: uppercase; font-weight: bold; font-size: 12pt; --%>
                                    <input disabled="" type="" id="codciudad" style="text-transform: uppercase; font-weight: bold; font-size: 12pt; 
                                            background-color: #d9edf7 " type="text" placeholder="Registro" class="form-control input-sm" required=""
                                            onkeydown=" if (event.keyCode === 13) {
                                                        recuperarCiudadesporID();
                                                    }">

                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">DECRIPCION</label>  
                                <div class="col-md-4">
                                    <input id="descrciudad"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;
                                           background-color: #d9edf7" placeholder="Ingrese descripcion" class="form-control input-sm" required autofocus="">
                                </div>
                            </div>
                        </div> 
                    </div> 

                    <!-- desde Aca Reporte -->
                    <div id="Reporte" class="w3-container w3-border city" style="display:none">
                        <div class="w3-container">
                            Ciudades <br>
                            <input type="button" value=" PDF" onclick="loadDocPdf(1, 0);" />
                        </div>
                    </div>
                    <!-- hasta Aca Reporte -->
                </div>

                <a id="btnNuevo" class="btn btn-lg btn-success" style=" font-weight: bold" onclick="getUltimoCodigo()">Nuevo </a>
                <a id="btnInsertar" class="btn btn-lg btn-primary" style=" font-weight: bold" onclick="ControlarCampoCiudades(), campovaciociudad()">Guardar </a>
                <a id="btnModificar" class="btn btn-lg btn-info" style=" font-weight: bold" title="Modificar" onclick="ambCiudades(2)">Modificar </a>
                <a id="btnAnular" class="btn btn-lg btn-danger" style=" font-weight: bold" title="Borrar" onclick="ambCiudades(3)">Borrar*</a>
                <a id="btnreportePp" class="btn btn-lg btn-success" style=" font-weight: bold" title="Reporte" onclick="reportesCiudades(4)">Reporte*</a>

            </form>
        </section>
    </center>
</body>
</html>
