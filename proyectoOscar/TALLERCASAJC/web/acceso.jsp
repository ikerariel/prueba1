<%-- 
    Document   : acceso
    Created on : 01/04/2019, 01:54:51 PM
    Author     : Carlos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Iniciar Session</title>
        <script src="validador/validadorAcceso.js"></script>
        <%@include file="validaciones.jsp" %>
        <style>
            body{
                padding-top: 50px;
                padding-bottom: 10px;

            }

            #services{
                max-width: 450px;
                height: 280px;
                opacity: 20%;
                webkit-box-shadow: 0px 0px 18px 0px rgba(48, 50, 50, 0.48);
                -moz-box-shadow: 0px 0px 18px 0px rgba(48, 50, 50, 0.48);
                box-shadow: 0px 0px 18px 0px rgba(48, 50, 50, 0.48);
                border-radius: 6%;
                border: #cccccc;
            }
        </style>
    </head>
    <body>
        <section class="container well parallax-section" id="services">


            <div class="single-service">
                <%-- <i class="glyphicon glyphicon-user"></i>--%>
                <h3>Login</h3><hr style="border: 1px solid #cccccc"> 
                <form class="login" action="accesoSERVLET" method="POST">
                    <div class="form-group">
                        <input id="username_v" maxlength="15" onblur="accesouser()" name="username_v" style="font-weight: bold;font-size: 13pt" type="text" title="Ingrese su Usuario" placeholder="Usuario" class="form-control input-md"
                               >
                    </div>
                    <div class="form-group">
                        <input id="password_v"  name="password_v"  disabled="" style="font-weight: bold;font-size: 13pt" type="password" title="Ingrese su Contraseña" class="form-control"placeholder="Contraseña" >
                    </div>
                    <div>
                        <input class="btn btn-lg btn-primary"value="Acceder" type="submit">

                    </div>
                    <input id="iduser_v" name="iduser_v" style="display: none"type="text" class="form-control input-sm">
                    <input id="idsucu_v" name="idsucu_v" style="display: none"class="form-control input-sm">
                    <input id="descripsucu_v" name="descripsucu_v" style="display: none" class="form-control input-sm">
                    <input id="iddepo_v" name="iddepo_v" style="display: none" class="form-control input-sm">
                    <input id="descripdepo_v"name="descripdepo_v"  style="display: none"class="form-control input-sm">
                    <input id="idsupervisor_v"name="idsupervisor_v" style="display: none" class="form-control input-sm">
                    <input id="idvendedor_v"name="idvendedor_v" style="" class="form-control input-sm">
                   





                </form>


            </div>


        </section>
    </body>
</html>
