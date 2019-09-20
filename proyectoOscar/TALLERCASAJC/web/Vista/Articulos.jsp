<%-- 
    Document   : Articulos
    Created on : 27/07/2018, 09:22:12 AM
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
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="stylesheet" href="../Recursos/css/estilos_1.css">
        <link rel="stylesheet" href="../Recursos/css/iconos.css">
        <link rel="stylesheet" href="../Recursos/css/bootstrap.css">
        <link rel="stylesheet" href="../Recursos/css/font-mfizz.css">
        <link rel="stylesheet" href="../Recursos/css/font-awesome.css">
        <link rel="stylesheet" href="../Recursos/css/bootstrap.css">
        <script type="text/javascript" src="../Recursos/js/jquery.js"></script>
        <script type="text/javascript" src="../validador/Articulosvalidad.js"></script>
        <script src="../Recursos/js/ImagenFondo.js"></script> 
        <script src="../validador/genericoJS.js"></script> 
        <script src="../Recursos/js/bootstrap.js"></script>
        <script src="../Recursos/js/main.js"></script>

        <title>ARTICULOS</title>

        <style>
            #scrolaper{
                overflow: scroll;
                height:350px;
            }
            #scrollcierre{
                overflow: scroll;
                height:230px;
            }
       
        </style>  
    </head>
    <body>
        <%@include file="../viwmenu.jsp" %> 
    <center>
        <section>
            <form class="form-horizontal"  id="defaultForm">
                <div class="panel panel-default">              
                    <div class="panel-footer" style="font-weight: bold">GESTIONAR ARTICULOS</div>

                    <div class="table-responsive" > 
                        <div class="headercontainer" >
                            <div class="tablecontainer">
                                <table class=" table-striped table-bordered table-hover table input-md" id="miTablaArticulos" onclick="recuperar()">
                                    <thead>
                                        <tr>
                                            <th><div>ID</div></th>
                                            <th><div>DESCRIPCION</div></th>
                                            <th><div>CODIGO</div></th>
                                            <th><div>PREC. COMPRAS</div></th>
                                            <th><div>PREC. VENTAS</div></th>
                                            <th><div>IMPUESTOS</div></th>
                                            <th><div>MARCAS</div></th>
                                        </tr>
                                    </thead>
                                    <tbody class="buscarArticulos"></tbody>
                                </table>
                            </div>
                        </div>     
                    </div>

                    <div class="input-group">
                        <span class="input-group-addon">Buscar</span>
                        <input class="form-control" type="text" id="filtrarArticulos" placeholder="Ingrese Descripcion a Buscar"
                               style="text-transform: uppercase; font-weight: bold; font-size:12pt; background-color: #d9edf7 ">
                    </div>
                    <br>
                    <div class="">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label" style=" font-weight: bold">ID:</label>  
                                <div class="col-md-1">
                                    <input disabled="" type="" id="idarticulo" style="text-transform: uppercase; font-weight: bold; font-size: 8pt;
                                            background-color: #d9edf7 " type="text" placeholder="Registro" class="form-control input-sm" required=""
                                            onkeydown=" if (event.keyCode === 13) {
                                                            getmostrarArticulosFiltro();
                                                        }">
                                </div>
                                
                                <label class="col-md-1 control-label" style=" font-weight: bold">DESCRIPCION:</label>  
                                <div class="col-md-3">
                                    <input id="descriarticulo"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 8pt;
                                               background-color: #d9edf7" placeholder="Ingrese descripcion" class="form-control input-sm" required autofocus="">
                                </div>
                                
                                <label class="col-md-1 control-label" style=" font-weight: bold">CODARTI:</label>  
                                <div class="col-md-2">
                                    <input id="generico"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 8pt;
                                               background-color: #d9edf7" placeholder="Ingrese cod articulos" class="form-control input-sm" required autofocus="">
                                </div>
                                
                                <label class="col-md-1 control-label" style=" font-weight: bold">P.COMPRAS:</label>  
                                <div class="col-md-2">
                                    <input id="artcompras" onkeyup="calcularprecioventa()" type="text" style="text-transform: uppercase; font-weight: bold;font-size: 8pt;
                                               background-color: #d9edf7" placeholder="Ingrese prec ventas" class="form-control input-sm" required autofocus="">
                                </div>
                            </div>
                        </div>
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label" style=" font-weight: bold">P.VENTAS:</label>  
                                <div class="col-md-3">
                                    <input id="artventas"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 8pt;
                                               background-color: #d9edf7" placeholder="Ingrese prec compras" class="form-control input-sm" required autofocus="">
                                </div>
                                
                                <label class="col-md-1 control-label" style=" font-weight: bold">IMPUESTOS:</label>  
                                <div class="col-md-3" id="comboImpuestos">
                                    <select id="idimpuesto" type="text" style="text-transform: uppercase; font-weight: bold;font-size: 8pt;
                                            background-color: #d9edf7" class="form-control input-sm"></select>
                                </div>
                                
                                <label class="col-md-1 control-label" style=" font-weight: bold">MARCAS:</label>  
                                <div class="col-md-3" id="comboMarcas">
                                    <select id="idmarca" type="text" style="text-transform: uppercase; font-weight: bold;font-size: 8pt;
                                            background-color: #d9edf7" class="form-control input-sm"></select>
                                </div>
                            </div>
                        </div> 
                    </div> 
                    <%--   COMBO CIUDADES HASTA ACA--%>
                </div>

                <a id="btnNuevo" class="btn btn-lg btn-success" style=" font-weight: bold" onclick="getUltimoCodigoArticulos()">Nuevo </a>
                <a id="btnInsertar" class="btn btn-lg btn-primary" style=" font-weight: bold" onclick="ambArticulos(1)">Guardar </a>
                <a id="btnModificar" class="btn btn-lg btn-info" style=" font-weight: bold" title="Modificar" onclick="ambArticulos(2)">Modificar </a>
                <a id="btnAnular" class="btn btn-lg btn-danger" style=" font-weight: bold" title="Borrar" onclick="ambArticulos(3)">Borrar*</a>
                <a id="btnreporteP" class="btn btn-lg btn-success" style=" font-weight: bold" title="Reporte" onclick="reportesArticulos()">Reporte*</a>

            </form>
        </section>
    </center>
</body>
</html>
