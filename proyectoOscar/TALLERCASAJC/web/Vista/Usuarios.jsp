<%-- 
    Document   : Usuarios
    Created on : 02/09/2018, 06:51:14 PM
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
        <script type="text/javascript" src="../validador/Usuariosvalidad.js"></script>
        <script src="../Recursos/js/ImagenFondo.js"></script> 
        <script src="../Recursos/js/bootstrap.js"></script>
        <script src="../Recursos/js/main.js"></script>
        <title>USUARIOS</title>
    </head>
    <body>
        <center>
        <section>
            <form class="form-horizontal"  id="defaultForm">
                <div class="panel panel-default">              
                    <div class="panel-footer" style="font-weight: bold">GESTIONAR USUARIOS</div>

                    <div class="table-responsive" > 
                        <div class="headercontainer" >
                            <div class="tablecontainer">
                                <table class=" table-striped table-bordered table-hover table input-md" id="miTablaUsuarios" onclick="recuperar()">
                                    <thead>
                                        <tr>
                                            <th><div>ID</div></th>
                                            <th><div>NOMBRES</div></th>
                                            <th><div>CLAVE</div></th>
                                            <th><div>EMPLEADOS</div></th>
                                            <th><div>SUCURSALES</div></th>
                                            <th><div>PERFILES</div></th>
                                        </tr>
                                    </thead>
                                    <tbody class="buscarUsuarios"></tbody>
                                </table>
                            </div>
                        </div>     
                    </div>

                    <div class="input-group">
                        <span class="input-group-addon">Buscar</span>
                        <input class="form-control" type="text" id="filtrarUsuarios" placeholder="Ingrese Descripcion a Buscar"
                               style="text-transform: uppercase; font-weight: bold; font-size:12pt; background-color: #d9edf7 ">
                    </div>
                    <br>
                    <div class="">
                        <div class="form-horizontal">
                            <div class="form-group">

                                <label class="col-md-1 control-label" style=" font-weight: bold">ID</label>  
                                <div class="col-md-3">
                                    <input  id="idusuario" style="text-transform: uppercase; font-weight: bold; font-size: 12pt;
                                            background-color: #d9edf7 " type="text" placeholder="Registro" class="form-control input-sm" required=""
                                            onkeydown=" if (event.keyCode === 13) {
                                                        getmostrarUsuariosFiltro();
                                                    }">

                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">NOMBRES</label>  
                                <div class="col-md-3">
                                    <input id="nombreusu"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;
                                           background-color: #d9edf7" placeholder="Ingrese nombre" class="form-control input-sm" required autofocus="">
                                </div>

                                <label class="col-md-1 control-label" style=" font-weight: bold">CLAVE</label>  
                                <div class="col-md-3">
                                    <input id="claveusu"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;
                                           background-color: #d9edf7" placeholder="Ingrese clave" class="form-control input-sm" required autofocus="">
                                </div>

                                   <%--   COMBO BARRIOS y CIUDADES ACA EMPIEZA--%>
                                   
                                <label class="col-md-1 control-label" style=" font-weight: bold">EMPLEADOS</label>  
                                <div class="col-md-3">
                                    <select id="idempleado" type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;
                                            background-color: #d9edf7" class="form-control input-sm"></select>
                                </div>
                                
                                <label class="col-md-1 control-label" style=" font-weight: bold">SUCURSALES</label>  
                                <div class="col-md-3" id="comboSucursales">
                                    <select id="idsucursal" type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;
                                            background-color: #d9edf7" class="form-control input-sm"></select>
                                </div>

                                <label class="col-md-1 control-label" style=" font-weight: bold">PERFILES</label>  
                                <div class="col-md-3" id="comboPerfiles">
                                    <select id="idperfil" type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;
                                            background-color: #d9edf8" class="form-control input-sm"></select>
                                </div>
                            </div>
                        </div> 
                    </div> 
                    <%--   COMBO BARRIOS y CIUDADES HASTA ACA--%>
                </div>

                <a id="btnNuevo" class="btn btn-lg btn-success" style=" font-weight: bold" onclick="getUltimoCodigoUsuarios()">Nuevo </a>
                <a id="btnInsertar" class="btn btn-lg btn-primary" style=" font-weight: bold" onclick="ambUsuarios(1)">Insertar </a>
                <a id="btnModificar" class="btn btn-lg btn-info" style=" font-weight: bold" title="Modificar" onclick="ambUsuarios(2)">Modificar </a>
                <a id="btnAnular" class="btn btn-lg btn-danger" style=" font-weight: bold" title="Borrar" onclick="ambUsuarios(3)">Borrar*</a>
                <a id="btnreporteP" class="btn btn-lg btn-success" style=" font-weight: bold" title="Reporte" onclick="reportesUsuarios()">Reporte*</a>

            </form>
        </section>
    </center>
    </body>
</html>
