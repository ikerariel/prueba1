$(document).ready(function () {

    $(":text").val("");
    fechaactual();
    allNotasCreditos();
     document.getElementById('btnGuardarM').style.display = 'none';
});
function fechaactual() {
    var fecha = new Date();
    $('#fechanotacredito').val(fecha
            .getDate() + "/" + (fecha.getMonth() + 1) + "/" + fecha.getFullYear());
}

function abrirVentanaModal() {
    getcodNC();
}

var tindex = 0;

// °°°°°Funcion Agregar filas y Validaciones°°°°"

function agregarFilaNC() {
    var _notaNC = $('#observNC').val();

    if (_notaNC === "") {
        alert('No se encuentra algun concepto cargado..!!');
    } else {
        if (_importe === "") {
            alert('No se ecuentra importe cargado..!!');
        } else {
            var v_nrofac = $('#nrofacturaNC').val();
            var v_obs = $('#observNC').val();

            // °°°°style=display:none°°° PARA ocultar ej. "Nro Factura"

            $('#miTablaDetalleNC').append("<tr id=\'prod" + tindex + "\'>\
            <td style=display:none>" + v_nrofac + "</td>\n\
            <td>" + v_obs + "</td>\n\
            <td><img onclick=\"$(\'#prod" + tindex + "\').remove()\n\
            \" src='Recursos/img/delete.png' width=14 height=14/></td></tr>");
            $('#observND').val(null);
            $('#observND').focus;
        }
    }
}//-----------------------

function crearJSON(id) {
    datosJSON = {
        "opcion": id
    };
}
function allNotasCreditos() {
    crearJSON(1);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/NotaCreComprascontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaNotaCredito").append($("<tr>").append($(
                        "<td style=display;none>" + value.id_notacrecompra + "</td>" +
                        "<td>" + value.fecha_nocred + "</td>" +
                        "<td bgcolor='#d9edf7'>" + value.est_descripcion + "</td>")));
            });
        }
    });
}

function  insertarNCredito() {
    var _nfilas = $('#miTablaDetalleNC tr').length - 1; // funcion para contar filas de una tabla
    if (parseInt(_nfilas) <= 0) {
        alert('No hay registros para guardar..!!');
    } else {
//        var notad = $('#NroNotaCreditos').val();
//        var nrot = $('#NroTimbrados').val();

        if ($('#NroNotaCreditos').val() === "" || $('#TipoMonedas').val() === "" || $('#NroTimbrados').val() === "") {
            alert('Algunos datos no fueron cargados correctamente..');
        } else {
            var opcion = confirm('Desea Guardar el registro..?');
            if (opcion === true) {
                datos = {
                    "opcion": 2,
                    "_nronocred": $('#NroNotaCreditos').val(),
                    "_nrotimbrado": $('#NroTimbrados').val(),
                    "_obsnocred": $('#ObsNoCred').val(),
                    "_codcompra": $('#IdCompra').val(),
                    "_codestado": 3,
                    "_codusuario": 1
                };

                $.ajax({
                    url: "http://localhost:8084/TALLERCASAJC/NotaCreComprascontrol",
                    type: 'POST',
                    data: datos,
                    cache: false,
                    dataType: 'text',
                    success: function () {


                        alert("Registro guardado correctamente.!!");
                    },
                    error: function () {
                    }
                });
            } else {
            }
            insetarDetalleNC();
        }
    }
}

function  insetarDetalleNC() {
    setTimeout(function () {
        $('#miTablaDetalleNC').find('tbody').find('tr').each(function () {
            datosDetalleNC = {
                "opcion": 4,
                "DtNC_codigoNC": $('#codigoNC').val(),
                "DtNC_codarticulo": $(this).find("td").eq(2).html(),
                "DtNC_cantidaddetnocre": $(this).find("td").eq(3).html(),
                "DtMC_montounidetnocre": $(this).find("td").eq(4).html()
            };
            $.ajax({
                url: "http://localhost:8084/TALLERCASAJC/NotaCreComprascontrol",
                type: 'POST',
                data: datosDetalleNC,
                cache: false,
                dataType: 'text',
                success: function () {
                    window.location.reload();
                },
                error: function () {
                }
            });
        });
    }, 2000);
}

function getcodNC() {
  
    crearJSON(3);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/NotaCreComprascontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            $("#codigoNC").val(resp);
            $("#nrofacturaNC").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function selectDetalleNc() {
    $('#miTablaNotaCrdedito tr').click(function () {
        $('#v_nroNC').val($(this).find("td").eq(0).html());
        $('#v_estado').val($(this).find("td").eq(2).html());

    });
}

function recuperarDetalleNC() {
    if ($('#v_nroNC').val() === "") {
        alert('Seleecione una Nota de Credito para visualizar..');
    } else {
        if ($('#v_estado').val() === 'Aprobado') {
            alert('La nota ya fue APROBADA..');
        } else {
            json = {
                "opcion": 5,
                "_nroNC": $('#v_nroNC').val()
            };
            $.ajax({
                url: "http://localhost:8084/TALLERCASAJC/NotaCreComprascontrol",
                type: 'POST',
                data: json,
                cache: false,
                success: function (resp) {
//                  alert(resp);
                    if (JSON.stringify(resp) != '[]') {
                        $('#ventanaNotaCredito').modal('show');
                        $('#miTablaDetalleNC').find('tbody').find('tr').empty(); //codigo para vaciar una tabla     
                        $.each(resp, function (indice, value) {

                            ///RECUPERA LA CABECERA/////////

                            $("#codigoNC").val($("#v_nroNC").val());
                            $("#fechanotacredito").val(value.fecha);
                            $("#estadoNC").val(value.estado);
                            $("#NroNotaCreditos").val(value.nro_notadebito);
                            $("#NroTimbrados").val(value.nro_timbradonotadebito);
                            $("#nrofacturaNC").val(value.factura);
                            $("#idcompraNC").val(value.id_compra);

                            $("#miTablaDetalleNC").append($("<tr>").append($(
                                    "<td>" + value.conceptos + "</td>")));
                                   

                        });
                    } else {
                        alert('Datos no encontrados..');
                    }
                }
            });
        }
    }
}

function consultaFactura() {
    jsonfactura = {
        "opcion": 6,
        "_nroFactura": $('#nrofacturaNC').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/NotaCreComprascontrol",
        type: 'POST',
        data: jsonfactura,
        cache: false,
        success: function (resp) {
            if (JSON.stringify(resp) != '[]') {
                $.each(resp, function (indice, value) {
                    $('#observNC').focus();
                    $('#idcompraNC').val(value.id_compra);
                    alert('Factura emitida');
                });
            } else {
                alert('Factura NO emitida..');
                $('#nrofacturaNC').val(null);
//                    $("#nrosolicitud").focus();
            }
        }
    });

}

function actualizarNC(_valor) {
//    var sms = "";
//    if (parseInt(_valor) === 1) {
//        var sms = 'Desea Aprobar Registro..?';
//    } else {
//        if (parseInt(_valor) === 3) {
//            var sms = 'Desea Revertir el estado del  Registro..?';
//        }
//    }

    valorEstado = $('#v_estado').val();

    if (valorEstado === "") {
        alert('No se ha seleccionado ningún registro');
    } else {
        if (valorEstado === 'Aprobado') {
            alert('El registro ya fue aprobado.!!');
        } else {
            if (valorEstado === 'Pendiente') {
                var opcion = confirm('Desea Aprobar Registro..? : ' + $('#v_nroNC').val());

                if (opcion === true) {
                    jsonEstado = {
                        "opcion": 7,
                        "_estado": _valor,
                        "_idNC": $('#v_nroNC').val()
                    };
                    $.ajax({
                        url: "http://localhost:8084/TALLERCASAJC/NotaCreComprascontrol",
                        type: 'POST',
                        data: jsonEstado,
                        cache: false,
                        success: function () {

                        }
                    });
                    $('#miTablaNotaCredito').find('tbody').find('tr').empty();
                    allnotasCreditos();
                } else {
                }
            }
        }
    }
}

function revertirNC(_valor) {
//    var sms = "";
//    if (parseInt(_valor) === 1) {
//        var sms = 'Desea Aprobar Registro..?';
//    } else {
//        if (parseInt(_valor) === 3) {
//            var sms = 'Desea Revertir el estado del  Registro..?';
//        }
//    }

    valorEstado = $('#v_estado').val();

    if (valorEstado === "") {
        alert('No se ha seleccionado ningún registro');
    } else {
        if (valorEstado === 'Pendiente') {
            alert('El registro ya fue revertido.!!');
        } else {
            if (valorEstado === 'Aprobado') {
                var opcion = confirm('Desea Revertir Registro..? : ' + $('#v_nroNC').val());

                if (opcion === true) {
                    jsonEstado = {
                        "opcion": 7,
                        "_estado": _valor,
                        "_idNC": $('#v_nroNC').val()
                    };
                    $.ajax({
                        url: "http://localhost:8084/TALLERCASAJC/NotaCreComprascontrol",
                        type: 'POST',
                        data: jsonEstado,
                        cache: false,
                        success: function () {

                        }
                    });
                    $('#miTablaNotaCredito').find('tbody').find('tr').empty();
                    allnotasCreditos();
                } else {
                }
            }
        }
    }
}

function anularNC(_valor) {
//    var sms = "";
//    if (parseInt(_valor) === 1) {
//        var sms = 'Desea Aprobar Registro..?';
//    } else {
//        if (parseInt(_valor) === 3) {
//            var sms = 'Desea Revertir el estado del  Registro..?';
//        }
//    }

    valorEstado = $('#v_estado').val();

    if (valorEstado === "") {
        alert('No se ha seleccionado ningún registro');
    } else {
        if (valorEstado === 'Aprobado') {
            alert('No se puede anular por que esta aprobado.!!');
        } else {
            if (valorEstado === 'Pendiente') {
                var opcion = confirm('Desea Anular Registro..? : ' + $('#v_nroNC').val());

                if (opcion === true) {
                    jsonEstado = {
                        "opcion": 7,
                        "_estado": _valor,
                        "_idNC": $('#v_nroNC').val()
                    };
                    $.ajax({
                        url: "http://localhost:8084/TALLERCASAJC/NotaCreComprascontrol",
                        type: 'POST',
                        data: jsonEstado,
                        cache: false,
                        success: function () {
                        }
                    });
                    $('#miTablaNotaCredito').find('tbody').find('tr').empty();
                    allnotasCreditos();
                } else {
                }
            }
        }
    }
}

function revertirNC(_valor) {
//    var sms = "";
//    if (parseInt(_valor) === 1) {
//        var sms = 'Desea Aprobar Registro..?';
//    } else {
//        if (parseInt(_valor) === 3) {
//            var sms = 'Desea Revertir el estado del  Registro..?';
//        }
//    }
    valorEstado = $('#v_estado').val();
    if (valorEstado === "") {
        alert('No se ha seleccionado ningún registro');
    } else {
        if (valorEstado === 'Pendiente') {
            alert('El registro ya fue revertido.!!');
        } else {
            if (valorEstado === 'Aprobado') {
                var opcion = confirm('Desea Revertir Registro..? : ' + $('#v_nroNC').val());
                if (opcion === true) {
                    jsonEstado = {
                        "opcion": 7,
                        "_estado": _valor,
                        "_idNC": $('#v_nroNC').val()
                    };
                    $.ajax({
                        url: "http://localhost:8084/TALLERCASAJC/NotaCreComprascontrol",
                        type: 'POST',
                        data: jsonEstado,
                        cache: false,
                        success: function () {
                        }
                    });
                    $('#miTablaNotaCredito').find('tbody').find('tr').empty();
                    allnotasCreditos();
                } else {
                }
            }
        }
    }
}

function updateCabeceraNotaCreditos() {
    var opcion = confirm('Desea Modificar el registro..?');
    if (opcion === true) {
        datosCabecera = {
            "opcion": 8,
            "_nronocred": $('#NroNotaCreditos').val(),
            "_nrotimbrado": $('#NroTimbrados').val(),
            "_obsnocred": $('#ObsNoCred').val(),
            "_codcompra": $('#IdCompra').val(),
            "_codusuario": 1,
            "_codNC": $('#codigoNC').val()
        };
        $.ajax({
            url: "http://localhost:8084/TALLERCASAJC/NotaCreComprascontrol",
            type: 'POST',
            data: datosCabecera,
            cache: false,
            dataType: 'text',
            success: function () {


                alert("Registro modificado correctamente.!!");
            },
            error: function () {
            }

        });

    } else {

    }
}


