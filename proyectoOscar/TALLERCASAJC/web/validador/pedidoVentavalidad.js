$(document).ready(function () {
    getPedidoVenta();
    opcionespedidoVenta();
    cargararticulos();

});

function opcionespedidoVenta() {
    $('#btnNuevopedidoventa').click(function () {
        $('#ventanaPedidoventa').modal('show');
        $('#btnGuardarpedidoventa').show();
        $('#codigovendedor').val($('#idvend_v').val());
        $('#nombrevendedor').val($('#usertext_v').val());
        $('#comentpedidoventa').val(null);
        $('#codarticulopedidoventa').val(null);
        $('#articulopedidopedidoventa').val(null);
        $('#cantidadpedidoventa').val(null);
        $('#miTablaDetallepedidoventa').find('tbody').find('tr').empty();
        $('#montototalpventa').val('0');
        var nCell = $('#miTablaPedidoventa tr').length - 1;
        if (parseInt(nCell) < 1) {
            $('#nropedidoventa').val("1");
        } else {
            var num;
            $('#miTablaPedidoventa').each(function () {
                num = parseInt($(this).find("td").eq(0).html());
            });
            $('#nropedidoventa').val(parseInt(num) + 1);
        }
    });
    $('#clintepedidoventa').blur(function () {
        obtenerclientepventa();
    });
    $('#tbnsaveclientepedidoventa').click(function () {
        saveClientepedidoventa();
    });
    $('#btnGuardarpedidoventa').click(function () {
        insertarPedidoVenta(1, 1, 1);
    });
    $('#btnmodificarpedidoventa').click(function () {
        insertarPedidoVenta(1, 2, 2);
    });
    $('#btnagregarpedidodetalle').click(function () {
        cargarfilapVenta();
    });
    $('#cantidadpedidoventa').keyup(function () {
        solonumeros('cantidadpedidoventa');
    });
    $('#miTablaPedidoventa').click(function () {
        seleccionPedidoVenta();
    });
    $('#btnconfpedidopedidoventa').click(function () {
         updateestado(1);
    });
    $('#btnRevertirpedidoventa').click(function () {
         updateestado(3);
    });
    $('#btnsalirpventa').click(function () {
         location.reload();
    });
    $('#btnReportepedidoventa').click(function () {
        if ($('#v_nropedidoventa').val() === "") {
            alert('Debes seleccionar un registro.');
        } else {
            var v = $('#v_nropedidoventa').val();
            var valor = 3;
            window.open(`reportesgenericos.jsp?id_pedidoven=${v}&vCodigo=${valor}`, "_blank");
        }


    });



}
function cargararticulos() {
    articulos = {
        "opcion": 20,
        "codDepo": $('#coddeposito_v').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
        type: 'POST',
        data: articulos,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#lisarticulo").append("<option value= \"" + value.id_articulo + "\"> " + value.art_descripcion + "</option>");

            });

        }
    });
}
function traerarticulos() {
    art = {
        "opcion": 19,
        "codArticulo": $('#codarticulopedidoventa').val(),
        "coddepos": $('#coddeposito_v').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
        type: 'POST',
        data: art,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $('#articulopedidopedidoventa').val(value.art_descripcion);
                $('#cantidadpedidoventa').val(1);
                $('#preciounitarioprecioventa').val(value.preccompras);
                numeroDecimal('preciounitarioprecioventa');
                $('#cantidadpedidoventa').focus();

            });

        }
    });

}
function cargarfilapVenta() {
    var ban = false;
    if ($('#codarticulopedidoventa').val() === "" ||
            $('#preciounitarioprecioventa').val() === "" ||
            $('#cantidadpedidoventa').val() === "" ||
            $('#articulopedidopedidoventa').val() === "") {
        alert('DEBES INGRESAR UN ARTICULO');
    } else if (parseInt($('#cantidadpedidoventa').val()) <= 0) {
        alert("La cantidad debe ser mayor a CERO (0)");
    } else {
        var cod = $('#codarticulopedidoventa').val();
        var codigo;
        $('#miTablaDetallepedidoventa').find('tbody').find('tr').each(function () {
            codigo = $(this).find("td").eq(0).html();
            if (cod === codigo) {
                var sms = confirm('Articulo cargado, desea sustituirlo ??');
                if (sms === true) {
                    $(this).closest("tr").remove();
                    ban = true;
                    cargarGrillapVenta();
                } else {
                    ban = true;
                }

            } else {

            }

        });
        if (ban === false) {
            cargarGrillapVenta();
        }
    }


}
var ix = 0;
function cargarGrillapVenta() {
    var subtotal = 0;
    var tindex = 0;
    var v_codMaterialG = $('#codarticulopedidoventa').val();
    var v_descripcion = $('#articulopedidopedidoventa').val();
    var v_cant = $('#cantidadpedidoventa').val();
    var v_precio = $('#preciounitarioprecioventa').val().replace(/\./g, '');

    subtotal = v_precio * v_cant;

    $('#miTablaDetallepedidoventa').append("<tr id=\'prod" + ix + "\'>\
            <td>" + v_codMaterialG + "</td>\n\
            <td>" + v_descripcion + "</td>\n\
            <td>" + v_cant + "</td>\n\
            <td>" + v_precio + "</td>\n\
            <td>" + subtotal + "</td>\n\
            <td><button type=button title='Quitar el registro de la lista' \n\
            style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + ix + "\').remove();totalpventa()\">\n\
            <span class='glyphicon glyphicon-remove'></span></button></a></td>\n\
            </tr>");

    $('#codarticulopedidoventa').val(null);
    $('#articulopedidopedidoventa').val(null);
    $('#preciounitarioprecioventa').val(null);
    $('#cantidadpedidoventa').val(null);

    totalpventa();

}

function totalpventa() {
    monto = 0;
    acumu = 0;

    $('#miTablaDetallepedidoventa').find('tbody').find('tr').each(function () {
        monto = parseInt($(this).find("td").eq(4).html());
        acumu = acumu + monto;

    });
    $('#montototalpventa').val(acumu);
//    $('#montototalpventa').css({border:'transparent', align:'center'});
    $('#montototalpventa').css('font-weight:', 'bold');
    numeroDecimal('montototalpventa');
    $('#codarticulopedidoventa').focus;
}
function obtenerclientepventa() {

    jsonc = {
        "opcion": 2,
        "ci_v": $('#clintepedidoventa').val().replace(/\./g, '')
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
        type: 'POST',
        data: jsonc,
        cache: false,
        success: function (data) {
            if (JSON.stringify(data) != '[]') {
                $.each(data, function (indice, valor) {
                    $('#idclientepedidoventa').val(valor.idgenerico);
                    $('#clinteNombrepedidoventa').val(valor.descripgenerico);
                    $('#clintepedidoventa').val(valor.cedula);
                });

            } else {
                var consulta = confirm('Cliente no Registrado, desea agregar ?');
                if (consulta) {
                    calcularDVpedidoventa();
                    $('#viewClientepedidoventa').modal('show');
                    $('#clienteModalpedidoventa').val($('#clintepedidoventa').val());
                    $('#clienteNombreModalpedidoventa').focus();
                    $('#telefonoModalpedidoventa').val(null);
                    $('#direccioneModalpedidoventa').val(null);
                }
            }

        },
        error: function () {

        }

    });
}
function calcularDVpedidoventa() {
    estadojson = {
        'opcion': 7,
        'nroci': $('#clintepedidoventa').val().replace(/\./g, ''),
        'basemax': 11
    };
    $.ajax({
        url: "/TALLERCASAJC/sRecepcionSERVLET",
        type: 'POST',
        data: estadojson,
        cache: false,
        dataType: 'text',
        success: function (resp) {
            $('#clienteCVpedidoventa').val(resp);
        }

    });


}
function saveClientepedidoventa() {
    var nombre = $('#clienteNombreModalpedidoventa').val();
    var telefono = $('#telefonoModalpedidoventa').val();
    var direccion = $('#direccioneModalpedidoventa').val();
    if (nombre === "" || telefono === "" || direccion === "") {
        alert('Algunos datos no fueron cargados correctamente..');
    } else {
        cliente = {
            "opcion": 10,
            "ruc_cliente": $('#clienteModalpedidoventa').val(),
            "razonsocial_cliente": nombre,
            "telefon_cliente": telefono,
            "direccion_cliente": direccion,
            "cv_cliente": $('#clienteCVpedidoventa').val()
        };
        $.ajax({
            url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
            type: 'POST',
            data: cliente,
            cache: false,
            dataType: 'text',
            success: function () {
                alert('Cliente Guardado');
                $('#viewClientepedidoventa').modal('hide');
                obtenerclientepventa();
            },
            error: function () {

            }

        });

    }
}
function inactividad() {
    window.onbeforeunload = function (e) {
        var e = e || window.event;
        alert(e);
//            
//            var msg = "Do you really want to leave this page?";
//
//            // For IE and Firefox
//            if (e) {
//                e.returnValue = msg;
//            }
//
//            // For Safari / chrome
//            return msg;
    };
//    window.location.hash="no-back-button";
//    window.location.hash="Again-No-back-button";
//    window.onhashchange=function (){
//        window.location.hash="no-back-button";
//    };
//    
//    window.onbeforeunload=function (){
//        return  "desea salir de la pagina";
//    };
}

function insertarPedidoVenta(op, caso, dtalle) {

    jsonpventa = {
        'opcion': op,
        'pdValor': caso,
        'pdCliente': $('#idclientepedidoventa').val(),
        'pdSucursal': $('#codsucursal_v').val(),
        'pdVendedor': $('#idvend_v').val(),
        'pdCodpedido': $('#nropedidoventa').val(),
        'pdObservacion': $('#comentpedidoventa').val()
    };
    $.ajax({
        url: "/TALLERCASAJC/pedidoVentaSERVLET",
        type: 'POST',
        data: jsonpventa,
        cache: false,
        dataType: 'text',
        success: function () {
            deletejson = {
                'opcion': 2,
                'pdCodpedido': $('#nropedidoventa').val()
            };
            $.ajax({
                url: "/TALLERCASAJC/pedidoVentaSERVLET",
                type: 'POST',
                data: deletejson,
                cache: false,
                dataType: 'text',
                success: function (resp) {
                    setTimeout(function () {
                        $('#miTablaDetallepedidoventa').find('tbody').find('tr').each(function () {
                            jsonDetallepd = {
                                'opcion': 3,
                                'pdCodpedido': $('#nropedidoventa').val(),
                                'opDetallepd': dtalle,
                                'pdCodarticulo': $(this).find("td").eq(0).html(),
                                'pdCantidad': $(this).find("td").eq(2).html(),
                                'pdPrecio': $(this).find("td").eq(3).html()
                            };
                            $.ajax({
                                url: "/TALLERCASAJC/pedidoVentaSERVLET",
                                type: 'POST',
                                data: jsonDetallepd,
                                cache: false,
                                dataType: 'text',
                                success: function (resp) {

                                    $('#ventanaPedidoventa').modal('hide');
                                    location.reload();
                                }
                            });
                        });
                    }, 1200);

                }
            });

        }

    });


}

var idx = 0;
function getPedidoVenta() {
    $('#miTablaPedidoventa').find('tbody').find('tr').empty();
    spVentajosn = {
        'opcion': 4
    };
    $.ajax({
        url: "/TALLERCASAJC/pedidoVentaSERVLET",
        type: 'POST',
        data: spVentajosn,
        cache: false,
        success: function (resp) {
//            alert(resp);
            $.each(resp, function (indice, valor) {
                var id = valor.id_estado;
                var pdcolor;
                if (parseInt(id) === 3) {
                    pdcolor = '#d9edf7';
                    btnpd = "<a class='btn btn-md btn-danger glyphicon glyphicon-remove' title='Eliminar Registro' onclick=\"$(\'#prod" + idx + "\');deltePventa()\"></a>";
                    vbtpd = "<a class='btn btn-md btn-success glyphicon glyphicon-edit' title='Modificar Registro' onclick=\"$(\'#prod" + idx + "\');updatePedidoVenta()\"></a>";
                } else if (parseInt(id) === 1) {
                    pdcolor = '#acffac';
                    btnpd = "<a disabled='' class='btn btn-md btn-danger glyphicon glyphicon-remove' title='Eliminar Registro'></a>";
                    vbtpd = "<a disabled='' class='btn btn-md btn-success glyphicon glyphicon-edit' title='Modificar Registro' ></a>";
                }
                $("#miTablaPedidoventa").append($("<tr>").append($(
                        "<td>" + valor.id_pedidoven + "</td>" +
                        "<td>" + valor.fechapedido + "</td>" +
                        "<td>" + valor.cliente + "</td>" +
                        "<td bgcolor=" + pdcolor + ">" + valor.est_descripcion + "</td>" +
                        "<td>" + valor.vendedor + "</td>" +
                        "<td style='text-align: center'>" + vbtpd + " " + btnpd + " </td>")));
            });


        }

    });
}
function deltePventa() {
    var apd = 0;
    var est;
    $('#miTablaPedidoventa tr').click(function () {
        apd = parseInt($(this).find("td").eq(0).html());
        est = $(this).find("td").eq(3).html();
        ver(est, apd);
    });
    function ver(v1, v2) {
        var sms = confirm("Desea Anular el Registro ?");
        if (sms) {
            if (v1 === "PENDIENTE") {
                cargarEstado(v2, 2);

            } else {
                alert('Registro CONFIRMADO, no se puede ANULAR.!!');
            }
        }else{
            location.reload();
        }
    }


}
function updatePedidoVenta() {
    var pv = 0;
    $('#miTablaPedidoventa tr').click(function () {
        pv = parseInt($(this).find("td").eq(0).html());
        openViewDetalle(pv);
    });
    function openViewDetalle(vCod) {
        $('#btnGuardarpedidoventa').hide();
        $('#btnmodificarpedidoventa').show();
        $('#ventanaPedidoventa').modal('show');
        $('#fechapedidoventa').val(null);
        $('#nropedidoventa').val(null);
        $('#codigovendedor').val(null);
        $('#nombrevendedor').val(null);
        $('#clintepedidoventa').val(null);
        $('#clinteNombrepedidoventa').val(null);
        $('#idclientepedidoventa').val(null);
        $('#comentpedidoventa').val(null);
        $('#codarticulopedidoventa').val(null);
        $('#preciounitarioprecioventa').val(null);
        $('#cantidadpedidoventa').val(null);
        $('#articulopedidopedidoventa').val(null);
        $('#montototalpventa').val(null);
        $('#miTablaDetallepedidoventa').find('tbody').find('tr').empty();
        setTimeout(function () {
            getPedidoVentaDetalle(vCod);
        }, 1000);


    }
}
var idex = 0;
function getPedidoVentaDetalle(cdpv) {

    $('#nropedidoventa').val(cdpv);
    spVentajosnDetalle = {
        'opcion': 5,
        'nropedidoventa': cdpv
    };
    $.ajax({
        url: "/TALLERCASAJC/pedidoVentaSERVLET",
        type: 'POST',
        data: spVentajosnDetalle,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, valor) {
                $('#fechapedidoventa').val(valor.fechapedido);
                $('#clintepedidoventa').val(valor.cedula);
                obtenerclientepventa();
                $('#codigovendedor').val(valor.idvendedor);
                $('#nombrevendedor').val(valor.vendedor);
                $('#comentpedidoventa').val(valor.observacion);
                vSubtotal = valor.precio * valor.cantidad;
                $("#miTablaDetallepedidoventa").append($("<tr id=\'prod" + idex + "\'>").append($(
                        "<td>" + valor.id_articulo + "</td>" +
                        "<td>" + valor.articulo + "</td>" +
                        "<td>" + valor.cantidad + "</td>" +
                        "<td>" + valor.precio + "</td>" +
                        "<td>" + vSubtotal + "</td>" +
                        "<td><button type=button title='Quitar el registro de la lista' \n\
            style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + idex + "\').remove();totalpventa()\">\n\
            <span class='glyphicon glyphicon-remove'></span></button></a></td>")));
                totalpventa();
            });



        }

    });
}

function seleccionPedidoVenta() {
    $('#miTablaPedidoventa tr').click(function () {
        $('#v_nropedidoventa').val($(this).find("td").eq(0).html());
        $('#v_estadopedidov').val($(this).find("td").eq(3).html()); /*Extrae el valor de la fila seleccionada y lo muestra en el campo
         //         * v_nroPlanilla*/
        var estado = $('#v_estadopedidov').val();
        if (estado === 'PENDIENTE') {
            document.getElementById('estadoRecepcion').style.color = "#000000";
            document.getElementById('estadoRecepcion').style.background = "PaleGoldenrod";
        }
        if (estado === 'CONFIRMADO') {
            document.getElementById('estadoRecepcion').style.background = "firebrick";
            document.getElementById('estadoRecepcion').style.color = "#ffffff";
        }
    });
}


function updateestado(estado) {
    if ($('#v_nropedidoventa').val() === "") {
        alert('Debes seleccionar un registo..');
    } else {
        var sms;
        var confir;
        var codPventa = $('#v_nropedidoventa').val();
        var codestado = $('#v_estadopedidov').val();
        if (estado === 1) {
            sms = "Desea Confirmar el Registro??";
            confir = "El Registro ya esta CONFIRMADO..!";
        }
        if (estado === 2) {
            sms = "Desea Anulaar el Registro??";
            confir = "El Registro ya esta CONFIRMADO..!";
        }
        if (estado === 3) {
            sms = "Desea Revertir el Registro??";
            confir = "El Registro aún sigue sin CONFIRMAR..!";
        }

        var v_sms = confirm(sms);
        if (v_sms === true) {
            if (estado === 1) {
                if (codestado === "CONFIRMADO") {
                    alert(confir);
                } else {
                    cargarEstado(codPventa, estado);
                    alert('Registro  Confirmado');

                }

            }

            if (estado === 2) {
                if (codestado === "CONFIRMADO") {
                    alert(confir);
                } else {
                    cargarEstado(codPventa, estado);
                    alert('Registro  Anulado');
                }
            }
            if (estado === 3) {
                if (codestado === "PENDIENTE") {
                    alert(confir);
                } else {
                    cargarEstado(codPventa, estado);
                    alert('Registro  Revertido');
                }

            }

        } else {

        }
    }

}

function cargarEstado(codPedidoV, estado) {
    estadojson = {
        'opcion': 6,
        'pdEstado': estado,
        'pdCodpedido': codPedidoV
    };
    $.ajax({
        url: "/TALLERCASAJC/pedidoVentaSERVLET",
        type: 'POST',
        data: estadojson,
        cache: false,
        dataType: 'text',
        success: function () {
            location.reload();
        }

    });


}