/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    $(":text").val("");
    fechaactual();
    allNotaRemision();
});
function fechaactual() {
    var fecha = new Date();
    $('#fechanotaremision').val(fecha.getDate() + "/" + (fecha.getMonth() + 1) + "/" + fecha.getFullYear());
}

function abrirVentanaRemision() {
    $('#btnM').hide();
    $('#btnGuardar').show();
    getcodNR();
}

var tindex = 0;

function agregarFilaNR() {
    var _notaD = $('#observNR').val();
    var _importe = $('#importeNR').val();

    if (_notaR === "") {
        alert('No se encuentra algun concepto cargado..!!');
    } else {
        if (_importe === "") {
            alert('No se ecuentra importe cargado..!!');
        } else {
            var v_nrofac = $('#nrofacturaNR').val();
            var v_obs = $('#observNR').val();
            var v_importe = $('#importeNR').val();

            // °°°°style=display:none°°° PARA ocultar ej. "Nro Factura"
            $('#miTablaDetalleNR').append("<tr id=\'prod" + tindex + "\'>\
            <td style=display:none>" + v_nrofac + "</td>\n\
            <td>" + v_obs + "</td>\n\
            <td>" + v_importe + "</td>\n\
            <td><img onclick=\"$(\'#prod" + tindex + "\').remove()\n\
            \" src='Recursos/img/delete.png' width=14 height=14/></td></tr>");
            $('#importeNR').val(null);
            $('#observNR').val(null);
            $('#observNR').focus;
        }
    }
}
//-----------------------

function crearJSON(id) {
    datosJSON = {
        "opcion": id
    };
}
function allNotaRemision() {
    crearJSON(1);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/NotaRemisionServlet",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaNotaRemision").append($("<tr>").append($(
                        "<td style=display;none>" + value.id_notaremi + "</td>" +
                        "<td>" + value.fecha_notaremi + "</td>" +
                        "<td bgcolor='#d9edf7'>" + value.est_descripcion + "</td>")));
            });
        }
    });
}

function  insertarNRemision() {
    var _nfilas = $('#miTablaDetalleNR tr').length - 1; // funcion para contar filas de una tabla
    if (parseInt(_nfilas) <= 0) {
        alert('No hay registros para guardar..!!');
    } else {
//        var notad = $('#NroNotaDebitos').val();
//        var nrot = $('#NroTimbrados').val();
     // if ($('#nronotaremi').val() === "" || $('#observ').val() === "") {
        if ($('#nronotaremi').val() === "" || $('#observ').val() === "" || $('#nrofacturaNR').val() === "") {
            alert('Algunos datos no fueron cargados correctamente..');
        } else {
            var opcion = confirm('Desea Guardar el registro..?');
            if (opcion === true) {
                datos = {
                    "opcion": 2,
                    "_nronotaremi": $('#nronotaremi').val(),
                    "_observ": $('#observ').val(),
                     "_codestado": 3,
                    "_codusuario": 1,
                    "_codcompra": $('#idcompraNR').val()
                   
                };

                $.ajax({
                    url: "http://localhost:8084/TALLERCASAJC/NotaRemisionServlet",
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
            insetarDetalleNR();
        }
    }
}

function  insetarDetalleNR() {
    setTimeout(function () {
        $('#miTablaDetalleNR').find('tbody').find('tr').each(function () {
            datosDetalleNR = {
                "opcion": 4,
                "NR_codigoR": $('#codigoNR').val(),
                "NR_articulo": $(this).find("td").eq(1).html(),
                "NR_cantidad": $(this).find("td").eq(2).html()
            };
            $.ajax({
                url: "http://localhost:8084/TALLERCASAJC/NotaRemisionServlet",
                type: 'POST',
                data: datosDetalleNR,
                cache: false,
                dataType: 'text',
                success: function () {

                },
                error: function () {
                }
            });
        });
    }, 4000);
}

function getcodNR() {
    crearJSON(3);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/NotaRemisionServlet",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            $("#codigoNR").val(resp);
            $("#nrofacturaNR").focus();
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

function recuperarDetalleNR() {
    $('#btnGuardar').hide();
    $('#btnM').show();
    if ($('#v_nroNR').val() === "") {
        alert('Seleecione una Nota Remision para visualizar..');
    } else {
        if ($('#v_estado').val() === 'Aprobado') {
            alert('La nota de Remision ya fue APROBADA..');
        } else {
            json = {
                "opcion": 5,
                "_nroNR": $('#v_nroNR').val()
            };
            $.ajax({
                url: "http://localhost:8084/TALLERCASAJC/NotaRemisionServlet",
                type: 'POST',
                data: json,
                cache: false,
                success: function (resp) {
//                  alert(resp);
                    if (JSON.stringify(resp) != '[]') {
                        $('#ventanaNotaRemision').modal('show');
                        $('#miTablaDetalleNR').find('tbody').find('tr').empty(); //codigo para vaciar una tabla     
                        $.each(resp, function (indice, value) {
                            
                            ///RECUPERA LA CABECERA/////////
                            
                            $("#codigoNR").val($("#v_nroNR").val());
                            $("#fechanotaremision").val(value.fecha_notaremi);
                            $("#estadoNR").val(value.estado);
                            $("#nronotaremi").val(value.nro_notaremi);
                            $("#observ").val(value.obser_notaremi);
                            $("#nrofacturaNR").val(value.factura);
                            $("#idcompraNR").val(value.id_compra);

                            tindex++;
                            $('#miTablaDetalleNR').append("<tr id=\'prod" + tindex + "\'>\
                             <td style=display:none>" + '0' + "</td>\n\
                             <td>" + value.id_notaremi + "</td>\n\
                             <td>" + value.id_articulo + "</td>\n\
                                <td>" + value.cantiarti + "</td>\n\
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

function consultaFacturas() {
    jsonfacturas = {
        "opcion": 6,
        "_nroFactura": $('#nrofacturaNR').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/NotaRemisionServlet",
        type: 'POST',
        data: jsonfacturas,
        cache: false,
        success: function (resp) {
            if (JSON.stringify(resp) != '[]') {
                $.each(resp, function (indice, value) {
                    $('#observNR').focus();
                    $('#idcompraNR').val(value.id_compra);
                    alert('Factura emitida');
                });
            } else {
                alert('Factura NO emitida..');
                $('#nrofacturaNR').val(null);
//                    $("#nrosolicitud").focus();
            }
        }
    });

}

function actualizarNR(_valor) {
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
                var opcion = confirm('Desea Aprobar Registro..? : ' + $('#v_nroNR').val());

                if (opcion === true) {
                    jsonEstado = {
                        "opcion": 7,
                        "_estado": _valor,
                        "_idNR": $('#v_nroNR').val()
                    };
                    $.ajax({
                        url: "http://localhost:8084/TALLERCASAJC/NotaRemisionServlet",
                        type: 'POST',
                        data: jsonEstado,
                        cache: false,
                        success: function () {

                        }
                    });
                    $('#miTablaNotaRemision').find('tbody').find('tr').empty();
                    allNotaRemision();
                } else {
                }
            }
        }
    }
}

function revertirNR(_valor) {
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
                var opcion = confirm('Desea Revertir Registro..? : ' + $('#v_nroNR').val());

                if (opcion === true) {
                    jsonEstado = {
                        "opcion": 7,
                        "_estado": _valor,
                        "_idNR": $('#v_nroNR').val()
                    };
                    $.ajax({
                        url: "http://localhost:8084/TALLERCASAJC/NotaRemisionServlet",
                        type: 'POST',
                        data: jsonEstado,
                        cache: false,
                        success: function () {

                        }
                    });
                    $('#miTablaNotaRemision').find('tbody').find('tr').empty();
                    allNotaRemision();
                } else {
                }
            }
        }
    }
}

function anularNR(_valor) {
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
                var opcion = confirm('Desea Anular Registro..? : ' + $('#v_nroNR').val());

                if (opcion === true) {
                    jsonEstado = {
                        "opcion": 7,
                        "_estado": _valor,
                        "_idNR": $('#v_nroNR').val()
                    };
                    $.ajax({
                        url: "http://localhost:8084/TALLERCASAJC/NotaRemisionServlet",
                        type: 'POST',
                        data: jsonEstado,
                        cache: false,
                        success: function () {
                        }
                    });
                    $('#miTablaNotaRemision').find('tbody').find('tr').empty();
                    allNotaRemision();
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
                var opcion = confirm('Desea Revertir Registro..? : ' + $('#v_nroNR').val());
                if (opcion === true) {
                    jsonEstado = {
                        "opcion": 7,
                        "_estado": _valor,
                        "_idNR": $('#v_nroNR').val()
                    };
                    $.ajax({
                        url: "http://localhost:8084/TALLERCASAJC/NotaRemisionServlet",
                        type: 'POST',
                        data: jsonEstado,
                        cache: false,
                        success: function () {
                        }
                    });
                    $('#miTablaNotaRemision').find('tbody').find('tr').empty();
                    allNotaRemision();
                } else {
                }
            }
        }
    }
}


//-----------------------------------------------------------------------


function updateCabecerassNotaRemisionnnn() {
    var opcion = confirm('Desea Modificar el registro..?');
    if (opcion === true) {
        datosCabeceraRemi = {
            "opcion": 8,
            "_nronotaremi": $('#nronotaremi').val(),
            "_observ": $('#observ').val(),
            
            "_codcompra": $('#idcompraNR').val(),
            "_codusuario": 1,
            "_codNR": $('#codigoNR').val()
        };

        $.ajax({
            url: "http://localhost:8084/TALLERCASAJC/NotaRemisionServlet",
            type: 'POST',
            data: datosCabeceraRemi,
            cache: false,
            dataType: 'text',
            success: function () {
                alert("Registro modificado correctamente.!!");
                updateDetalleNRemision();
            },
            error: function () {
            }
        });

    } else {
    }

    insetarDetalleNR();
}


function updateDetalleNRemision() {
    jsonDetalle = {
        "opcion": 9,
        "nroNR": $('#codigoNR').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/NotaRemisionServlet",
        type: 'POST',
        data: jsonDetalle,
        cache: false,
        success: function () {

        }
    });

}