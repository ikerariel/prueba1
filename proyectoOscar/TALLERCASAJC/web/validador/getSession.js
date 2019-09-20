
$(document).ready(function () {
    getSession();
});

function getSession() {
    userdatos = {
        'opcion': 1,
        'usuario': $('#usertext_v').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/acceSERVLET",
        type: 'POST',
        data: userdatos,
        cache: false,
        success: function (resp) {
            if (JSON.stringify(resp) != '[]') {
                $.each(resp, function (indice, valor) {
                    let vPerfil = valor.id_perfil;
                    permisos(vPerfil);

                });
            } else {

            }

        },
        error: function () {
        }
    });
}
function permisos(valor) {
    switch (valor) {
        case 1:
            $('#mc_compras').show();
            $('#m_pedidoscompras').show();
            $('#m_presupuestocompras').show();
            $('#m_pedidocompras').show();
            $('#m_pedidocompras').show();
            $('#btnNuevo').show();
            break;


        case 2:
            $('#mc_ventas').show();
            $('#m_aperturacaja').show();
            $('#btnNuevo').show();
            break;
        case 6:  //Administrador de sistema los tres movimientos y referenciales
            $('#mc_ventas').show();
            $('#m_aperturacaja').show();
            $('#m_pedidoventas').show();
            $('#m_facturacion').show();
            $('#m_parametros').show();
            $('#mc_compras').show();
            $('#m_pedidocompras').show();
            $('#m_registrarpresupuesto').show();
            $('#m_ordencompra').show();
            $('#m_facturacompra').show();
            $('#m_notaremision').show();
            $('#m_notacredito').show();
            $('#m_notadebito').show();
            $('#m_ajustes').show();
            $('#mc_Servicios_Tecnicos').show();
            $('#m_recepcion').show();
            $('#m_diagnostico').show();
            $('#m_presupuestos_servicios').show();
            $('#m_orden_trabajos').show();
            $('#m_facturas_compras').show();
            $('#r_ReferencialesCompras').show();
            $('#r_articulos').show();
            $('#r_barrios').show();
            $('#r_ciudades').show();
            $('#r_depositos').show();
            $('#r_Empleados').show();
            $('#r_entidademisoras').show();
            $('#r_impuestos').show();
            $('#r_marcas').show();
            $('#r_proveedores').show();
            $('#r_sucursales').show();
            $('#rv_ReferencialesVentas').show();
            $('#rv_clientes').show();
            $('#rv_cajas').show();
            $('#rv_timbrados').show();
            $('#rv_tipocheques').show();
            $('#rv_tipomoneda').show();
            $('#rv_tipostarjetas').show();
            $('#rv_vendedor').show();
            $('#rv_bancocheque').show();
            $('#rst_ReferencialesServiciosTecnicos').show();
            $('#rst_modelos').show();
            $('#rst_colores').show();
            $('#rst_tiposervicios').show();
            break;
        case 7:
            $('#mc_Servicios_Tecnicos').show();
            $('#m_recepcion').show();
            $('#m_diagnostico').show();
            $('#mc_compras').show();
            $('#m_pedidoscompras').show();
            $('#m_presupuestocompras').show();
            $('#m_ordencompra').show();
            $('#mc_ventas').show();
            $('#m_pedidoventas').show();

            break;
    }
}
