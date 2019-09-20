

$(document).ready(function () {

    $(":text").val("");
    fechaactual();
    allnotasDebitos();
});
function fechaactual() {
    var fecha = new Date();
    $('#fechanotadebito').val(fecha.getDate() + "/" + (fecha.getMonth() + 1) + "/" + fecha.getFullYear());
}

function abrirVentana() {
    $('#btnM').hide();
    $('#btnGuardar').show();
    getcodND();
}

var tindex = 0;

function agregarFilaND() {
    var _notaD = $('#observND').val();
    var _importe = $('#importeND').val();

    if (_notaD === "") {
        alert('No se encuentra algun concepto cargado..!!');
    } else {
        if (_importe === "") {
            alert('No se ecuentra importe cargado..!!');
        } else {
            var v_nrofac = $('#nrofacturaND').val();
            var v_obs = $('#observND').val();
            var v_importe = $('#importeND').val();

            // °°°°style=display:none°°° PARA ocultar ej. "Nro Factura"
            $('#miTablaDetalleND').append("<tr id=\'prod" + tindex + "\'>\
            <td style=display:none>" + v_nrofac + "</td>\n\
            <td>" + v_obs + "</td>\n\
            <td>" + v_importe + "</td>\n\
            <td><img onclick=\"$(\'#prod" + tindex + "\').remove()\n\
            \" src='Recursos/img/delete.png' width=14 height=14/></td></tr>");
            $('#importeND').val(null);
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
function allnotasDebitos() {
    crearJSON(1);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/notaDebitocontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaNotaDebito").append($("<tr>").append($(
                        "<td style=display;none>" + value.id_notadecompra + "</td>" +
                        "<td>" + value.fecha + "</td>" +
                        "<td bgcolor='#d9edf7'>" + value.est_descripcion + "</td>")));
            });
        }
    });
}

function  insertarNremision() {
    var _nfilas = $('#miTablaDetalleND tr').length - 1; // funcion para contar filas de una tabla
    if (parseInt(_nfilas) <= 0) {
        alert('No hay registros para guardar..!!');
    } else {
//        var notad = $('#NroNotaDebitos').val();
//        var nrot = $('#NroTimbrados').val();

        if ($('#NroNotaDebitos').val() === "" || $('#NroTimbrados').val() === "" || $('#nrofacturaND').val() === "") {
            alert('Algunos datos no fueron cargados correctamente..');
        } else {
            var opcion = confirm('Desea Guardar el registro..?');
            if (opcion === true) {
                datos = {
                    "opcion": 2,
                    "_nrodebito": $('#NroNotaDebitos').val(),
                    "_nrotimbradoDebito": $('#NroTimbrados').val(),
                    "_codcompra": $('#idcompraND').val(),
                    "_codestado": 3,
                    "_codusuario": 1
                };

                $.ajax({
                    url: "http://localhost:8084/TALLERCASAJC/notaDebitocontrol",
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
            insetarDetalleND();
        }
    }
}

function  insetarDetalleND() {
    setTimeout(function () {
        $('#miTablaDetalleND').find('tbody').find('tr').each(function () {
            datosDetalleND = {
                "opcion": 4,
                "ND_codigoD": $('#codigoND').val(),
                "ND_importe": $(this).find("td").eq(2).html(),
                "NT_comentario": $(this).find("td").eq(1).html()
            };
            $.ajax({
                url: "http://localhost:8084/TALLERCASAJC/notaDebitocontrol",
                type: 'POST',
                data: datosDetalleND,
                cache: false,
                dataType: 'text',
                success: function () {

                },
                error: function () {
                }
            });
        });
    }, 2000);
}

function getcodND() {
    crearJSON(3);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/notaDebitocontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            $("#codigoND").val(resp);
            $("#nrofacturaND").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function selectDetalleNr() {
    $('#miTablaNotaRemision tr').click(function () {
        $('#v_nroNR').val($(this).find("td").eq(0).html());
        $('#v_estado').val($(this).find("td").eq(2).html());

    });
}

function recuperarDetalleND() {
    $('#btnGuardar').hide();
    $('#btnM').show();
    if ($('#v_nroNR').val() === "") {
        alert('Seleecione una Nota de Debito para visualizar..');
    } else {
        if ($('#v_estado').val() === 'Aprobado') {
            alert('La nota ya fue APROBADA..');
        } else {
            json = {
                "opcion": 5,
                "_nroND": $('#v_nroND').val()
            };
            $.ajax({
                url: "http://localhost:8084/TALLERCASAJC/notaDebitocontrol",
                type: 'POST',
                data: json,
                cache: false,
                success: function (resp) {
//                  alert(resp);
                    if (JSON.stringify(resp) != '[]') {
                        $('#ventanaNotaDebito').modal('show');
                        $('#miTablaDetalleND').find('tbody').find('tr').empty(); //codigo para vaciar una tabla     
                        $.each(resp, function (indice, value) {
                            ///RECUPERA LA CABECERA/////////
                            $("#codigoND").val($("#v_nroND").val());
                            $("#fechanotadebito").val(value.fecha);
                            $("#estadoND").val(value.estado);
                            $("#NroNotaDebitos").val(value.nro_notadebito);
                            $("#NroTimbrados").val(value.nro_timbradonotadebito);
                            $("#nrofacturaND").val(value.factura);
                            $("#idcompraND").val(value.id_compra);

                            tindex++;
                            $('#miTablaDetalleND').append("<tr id=\'prod" + tindex + "\'>\
                             <td style=display:none>" + '0' + "</td>\n\
                             <td>" + value.conceptos + "</td>\n\
                                <td>" + value.importe + "</td>\n\
                               <td><img onclick=\"$(\'#prod" + tindex + "\').remove()\" src='Recursos/img/delete.png' width=14 height=14/></td>\n\
                                </tr>");

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
        "_nroFactura": $('#nrofacturaND').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/notaDebitocontrol",
        type: 'POST',
        data: jsonfactura,
        cache: false,
        success: function (resp) {
            if (JSON.stringify(resp) != '[]') {
                $.each(resp, function (indice, value) {
                    $('#observND').focus();
                    $('#idcompraND').val(value.id_compra);
                    alert('Factura emitida');
                });
            } else {
                alert('Factura NO emitida..');
                $('#nrofacturaND').val(null);
//                    $("#nrosolicitud").focus();
            }
        }
    });

}

function actualizarND(_valor) {
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
                var opcion = confirm('Desea Aprobar Registro..? : ' + $('#v_nroND').val());

                if (opcion === true) {
                    jsonEstado = {
                        "opcion": 7,
                        "_estado": _valor,
                        "_idND": $('#v_nroND').val()
                    };
                    $.ajax({
                        url: "http://localhost:8084/TALLERCASAJC/notaDebitocontrol",
                        type: 'POST',
                        data: jsonEstado,
                        cache: false,
                        success: function () {

                        }
                    });
                    $('#miTablaNotaDebito').find('tbody').find('tr').empty();
                    allnotasDebitos();
                } else {
                }
            }
        }
    }
}

function revertirND(_valor) {
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
                var opcion = confirm('Desea Revertir Registro..? : ' + $('#v_nroND').val());

                if (opcion === true) {
                    jsonEstado = {
                        "opcion": 7,
                        "_estado": _valor,
                        "_idND": $('#v_nroND').val()
                    };
                    $.ajax({
                        url: "http://localhost:8084/TALLERCASAJC/notaDebitocontrol",
                        type: 'POST',
                        data: jsonEstado,
                        cache: false,
                        success: function () {

                        }
                    });
                    $('#miTablaNotaDebito').find('tbody').find('tr').empty();
                    allnotasDebitos();
                } else {
                }
            }
        }
    }
}

function anularND(_valor) {
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
                var opcion = confirm('Desea Anular Registro..? : ' + $('#v_nroND').val());

                if (opcion === true) {
                    jsonEstado = {
                        "opcion": 7,
                        "_estado": _valor,
                        "_idND": $('#v_nroND').val()
                    };
                    $.ajax({
                        url: "http://localhost:8084/TALLERCASAJC/notaDebitocontrol",
                        type: 'POST',
                        data: jsonEstado,
                        cache: false,
                        success: function () {
                        }
                    });
                    $('#miTablaNotaDebito').find('tbody').find('tr').empty();
                    allnotasDebitos();
                } else {
                }
            }
        }
    }
}

function revertirND(_valor) {
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
                var opcion = confirm('Desea Revertir Registro..? : ' + $('#v_nroND').val());
                if (opcion === true) {
                    jsonEstado = {
                        "opcion": 7,
                        "_estado": _valor,
                        "_idND": $('#v_nroND').val()
                    };
                    $.ajax({
                        url: "http://localhost:8084/TALLERCASAJC/notaDebitocontrol",
                        type: 'POST',
                        data: jsonEstado,
                        cache: false,
                        success: function () {
                        }
                    });
                    $('#miTablaNotaDebito').find('tbody').find('tr').empty();
                    allnotasDebitos();
                } else {
                }
            }
        }
    }
}

function updateCabecera() {
    var opcion = confirm('Desea Modificar el registro..?');
    if (opcion === true) {
        datosCabecera = {
            "opcion": 8,
            "_nrodebito": $('#NroNotaDebitos').val(),
            "_nrotimbradoDebito": $('#NroTimbrados').val(),
            "_codcompra": $('#idcompraND').val(),
            "_codusuario": 1,
            "_codND": $('#codigoND').val()
        };

        $.ajax({
            url: "http://localhost:8084/TALLERCASAJC/notaDebitocontrol",
            type: 'POST',
            data: datosCabecera,
            cache: false,
            dataType: 'text',
            success: function () {

                alert("Registro modificado correctamente.!!");
                updateDetalle();
            },
            error: function () {
            }

        });

    } else {

    }

    insetarDetalleND();
}


function updateDetalle() {
    jsonDetalle = {
        "opcion": 9,
        "nroND": $('#codigoND').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/notaDebitocontrol",
        type: 'POST',
        data: jsonDetalle,
        cache: false,
        success: function () {

        }
    });

}