<%-- 
    Document   : menu
    Created on : 23/01/2019, 01:30:18 PM
    Author     : Oscar
--%>
<script src="validador/getSession.js"></script>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
        <nav class="navbar  alert-warning"  role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a href="" class="navbar-brand" title="Volver a Página de Inicio">Casa JC</a>
                </div>

                <div id="navbarCollapse" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li role="presentation" class="dropdown">
                            <a role="button" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="display: none" id="mc_ventas">Ventas<span class="caret"></span></a>
                            <ul class="dropdown-menu" >
                                <ul class="dropdown-menu">
                                     <li><a href="">SubMenu2</a></li>
                                </ul>
                                <li><a style="display: none" href="PedidosVenta.jsp" id="m_pedidoventas">Pedido de Ventas</a></li>
                                <li><a style="display: none" href="aperturacaja.jsp" id="m_aperturacaja">Apertura de Caja</a></li>
                                <li><a style="display: none" href="aperturacaja.jsp"id="m_pedidoventas">Pedidos Ventas</a></li>
                                <li><a style="display: none" href="FacturasVenta.jsp" id="m_facturacion">Facturación</a></li>
                                <li><a style="display: none"    href="parametros.jsp" id="m_parametros">Parámetros</a></li>
                            </ul>
                        </li>
                    </ul>
                    
                    <ul class="nav navbar-nav">
                        <li role="presentation" class="dropdown">
                            <a role="button" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="display: none" id="mc_compras">Compras<span class="caret"></span></a>
                            <ul class="dropdown-menu" >
                                <ul class="dropdown-menu">
                                     <li><a href="">SubMenu2</a></li>
                                </ul>
                                <li><a style="display: none" id="m_pedidoscompras" href="PedidosCompras.jsp">PedidosCompras</a></li>
                                <li><a style="display: none" id="m_presupuestocompras" href="Presupuesto.jsp">Registrar Presupuesto</a></li>
                                <li><a style="display: none" id="m_ordencompra" href="OrdenCompras.jsp">Orden Compras</a></li>
                                <li><a style="display: none" id="m_facturacompra" href="FacturasComprasvalidad.jsp">FacturasCompras</a></li>
                                <li><a style="display: none" id="m_notaremision" href="FacturasComprasvalidad.jsp">Nota Remision</a></li>
                                <li><a style="display: none" id="m_notacredito" href="FacturasComprasvalidad.jsp">Nota Creditos</a></li>
                                <li><a style="display: none" id="m_notadebito" href="FacturasComprasvalidad.jsp">Nota Debitos</a></li>
                                <li><a style="display: none" id="m_ajustes"href="Ajustes.jsp">Ajustes</a></li>
                                
                               
                             
                            </ul>
                        </li>
                    </ul>
                    
                    <ul class="nav navbar-nav">
                        <li role="presentation" class="dropdown">
                            <a role="button" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="display: none" id="mc_Servicios_Tecnicos">Servicios Tecnico<span class="caret"></span></a>
                            <ul class="dropdown-menu" >
                                <ul class="dropdown-menu">
                                     <li><a href="">SubMenu2</a></li>
                                </ul>
                                <li><a style="display: none" id="m_recepcion" href="sRecepcion.jsp">Recepcion</a></li>
                                <li><a style="display: none" id= "m_diagnostico" href="sDiagnostico.jsp">Diagnostico</a></li>
                                <li><a style="display: none" id= "m_presupuestos_servicios"href="Presupuestos_Servicios.jsp">Presupuesto Servicios</a></li>
                                <li><a style="display: none" id= "m_orden_trabajos"href="Orden_Trabajos.jsp">Orden Trabajos</a></li>
                                <li><a style="display: none" id= "m_facturas_compras"href="Entrega_Equipos.jsp">Entrega Equipos</a></li>
                             
                            </ul>
                        </li>
                    </ul>
                    
                    
                    
                    <ul class="nav navbar-nav">
                        <li role="presentation" class="dropdown">
                            <a role="button" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="display: none" id="r_ReferencialesCompras">Referenciales Compras<span class="caret"></span></a>
                            <ul class="dropdown-menu" >
                                <ul class="dropdown-menu">
                                     <li><a href="">SubMenu2</a></li>
                                </ul>
                                <li><a style="display: none" id="r_articulos" href="Vista/Articulos.jsp">Articulos</a></li>
                                <li><a style="display: none" id= "r_barrios" href="Barrios.jsp">Barrios</a></li>
                                <li><a style="display: none" id= "r_ciudades"href="Ciudades.jsp">Ciudades</a></li>
                                <li><a style="display: none" id= "r_depositos"href="Depositos.jsp">Depositos</a></li>
                                <li><a style="display: none" id= "r_Empleados"href="Empleados.jsp">Empleados</a></li>
                                <li><a style="display: none" id= "r_entidademisoras"href="EntidadEmisoras.jsp">EntidadEmisoras</a></li>
                                <li><a style="display: none" id= "r_impuestos"href="Impuestos.jsp">Impuestos</a></li>
                                <li><a style="display: none" id= "r_marcas"href="Marcas.jsp">Marcas</a></li>
                                <li><a style="display: none" id= "r_proveedores"href="Proveedores.jsp">Proveedores</a></li>
                                <li><a style="display: none" id= "r_sucursales"href="Sucursales.jsp">Sucursales</a></li>
                             
                            </ul>
                        </li>
                    </ul>
                    
                    <ul class="nav navbar-nav">
                        <li role="presentation" class="dropdown">
                            <a role="button" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="display: none" id="rv_ReferencialesVentas">Referenciales Ventas<span class="caret"></span></a>
                            <ul class="dropdown-menu" >
                                <ul class="dropdown-menu">
                                     <li><a href="">SubMenu2</a></li>
                                </ul>
                                <li><a style="display: none" id="rv_clientes" href="Clientes.jsp">Clientes</a></li>
                                <li><a style="display: none" id= "rv_cajas" href="Cajas.jsp">Cajas</a></li>
                                <li><a style="display: none" id= "rv_timbrados"href="Timbrados.jsp">Timbrados</a></li>
                                <li><a style="display: none" id= "rv_tipocheques"href="TipoCheques.jsp">TipoCheques</a></li>
                                <li><a style="display: none" id= "rv_tipomoneda"href="TipoMoneda.jsp">TipoMoneda</a></li>
                                <li><a style="display: none" id= "rv_tipostarjetas"href="TiposTarjetas.jsp">TiposTarjetas</a></li>
                                <li><a style="display: none" id= "rv_vendedor"href="Vendedor.jsp">Vendedor</a></li>
                                <li><a style="display: none" id= "rv_bancocheque"href="BancoCheque.jsp">BancoCheque</a></li>
                             
                            </ul>
                        </li>
                    </ul>
                    
                    <ul class="nav navbar-nav">
                        <li role="presentation" class="dropdown">
                            <a role="button" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="display: none" id="rst_ReferencialesServiciosTecnicos">Referenciales Servicios Tecnicos<span class="caret"></span></a>
                            <ul class="dropdown-menu" >
                                <ul class="dropdown-menu">
                                     <li><a href="">SubMenu2</a></li>
                                </ul>
                                <li><a style="display: none" id="rst_modelos" href="Modelos.jsp">Modelos</a></li>
                                <li><a style="display: none" id= "rst_colores" href="Colores.jsp">Colores</a></li>
                                <li><a style="display: none" id= "rst_tiposervicios"href="TipoServicios.jsp">Tipo Servicios</a></li>
                             
                            </ul>
                        </li>
                    </ul>
                    

        <ul class=" nav navbar-nav navbar-right">
                                 
                <li class="dropdown">
                    <a style="font-weight: bold;" href="#" class="dropdown-toggle" id="textUser_v"  data-toggle="dropdown" role="button">
                        <span class="glyphicon glyphicon-user"  ></span>   <%=session.getAttribute("user")%> <span class="caret"></span>
                    </a>
                    <input id="usertext_v" value="${sessionScope.user}" style="font-weight: bold; display: none">
                    <input id="codsucursal_v" value="${sessionScope.Codsucursal}"  style="font-weight: bold; display:none">
                    <input id="sucur_v"value="<%=session.getAttribute("Sucursal")%>" style="font-weight: bold; display: none">
                    <input id="coddeposito_v"value="${sessionScope.Coddeposito}" style="font-weight: bold; display: none"> 
                    <input id="depos_v"value="${sessionScope.Deposito}" style="font-weight: bold; display:none">
                    <input id="idusersession_v" value="${sessionScope.Coduser}" style="font-weight: bold; display: none">
                    <input id="idsuperv_v"value="${sessionScope.Codsuperv}" style="font-weight: bold; display: none">
                    <input id="idvend_v"value="${sessionScope.Codvendedor}" style="font-weight: bold; display: none">
                    <ul class="dropdown-menu">
                        <li> <a style="font-weight: bold;" href="#"><span class="glyphicon glyphicon-refresh"></span> Contraseña</a></li>
                        <li class="divider"></li>
                        <li> <a style="font-weight: bold;" href="acceso.jsp"><span class="glyphicon glyphicon-off"></span> Cerrar Sessión</a></li>
                        <li class="divider"></li>

                    </ul>
                </li>


            </ul>

                </div>
          
            </div>

        </nav>
