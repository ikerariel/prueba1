$(document).ready(function () {
    listaparametros();
    condicionCobro();
    listartipopago();
    obtenerventa();
    listarvendedor();
    fechafac();
    condicionventa();
    listararticulos();



});
function fechafac() {
    var fv = new Date();
    $('#v_fechafac').val(fv.getDate() + "/" + (fv.getMonth() + 1) + "/" + fv.getFullYear());
    $('#_fecha').val(fv.getDate() + "/" + (fv.getMonth() + 1) + "/" + fv.getFullYear());
}

//function obtenerNroFactura() {
//    json = {
//        "opcion": 1,
//        "caja_v": $('#nrocaja_v option:selected').text()
//    };
//    $.ajax({
//        url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
//        type: 'POST',
//        data: json,
//        cache: false,
//        success: function (data) {
//            if (JSON.stringify(data) != '[]') {
//                     $.each(data, function (indice, valor) {
//                    $('#idventa_v').val(valor.idgenerico);
//                    $('#nrofac_v').val(valor.descripgenerico);
//                });
//              
//            } else {
//              $('#nrofac_v').val(null);
//            }
//
//
//        },
//        error: function () {
//        }
//
//    });
//}
function obtenerventa() {
    jsonv = {
        "opcion": 9
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
        type: 'POST',
        data: jsonv,
        cache: false,
        success: function (data) {

            $('#idventa_v').val(data);


        },
        error: function () {
        }

    });
}
function listararticulos() {
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
                $("#listaarti").append("<option value= \"" + value.id_articulo + "\"> " + value.art_descripcion + " Cantidad : " + value.cantidad + "</option>");

            });

        }
    });
}
function listaparametros() {
    parametros = {
        "opcion": 12

    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
        type: 'POST',
        data: parametros,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {

                $('#tabladetalleparametros').append("<tr id=\'prod" + tindex + "\'>\
            <td>" + value.id_timbrado + "</td>\n\
            <td>" + value.numero + "</td>\n\
            <td>" + value.inicio_fecha + "</td>\n\
            <td>" + value.vencimientos + "</td>\n\
            <td>" + value.est_descripcion + "</td>\n\
            <td>" + value.fac_caja + "</td></tr>");


            });

        }
    });
}
function listarvendedor() {
    vendedor = {
        "opcion": 3
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
        type: 'POST',
        data: vendedor,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#listavendedor").append("<option value= \"" + value.idgenerico + "\"> " + value.descripgenerico + "</option>");

            });

        }
    });
}
function listartipopago() {
    tipopago = {
        "opcion": 6
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
        type: 'POST',
        data: tipopago,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#tipopago_v").append("<option value= \"" + value.idgenerico + "\"> " + value.descripgenerico + "</option>");

            });

        }
    });
}
function obtenerarticulos() {
    var exis = 0;
    art = {
        "opcion": 19,
        "codArticulo": $('#articulo_v').val(),
        "coddepos":$('#coddeposito_v').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
        type: 'POST',
        data: art,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                exis = value.cantidad;
                if (exis > 0) {
                    $('#producto_v').val(value.art_descripcion);
                    $('#punitario_v').val(value.preccompras);
                    $('#impuesto_v').val(value.impuesto);
                    $('#idimpuesto_v').val(value.id_impuesto);
                    $('#canti_v').focus();
                    $('#canti_v').val(1);
                    valores('punitario_v');

                }else{
                    alert("PRODUCTO SIN STOCK...");
                }

            });

        }
    });

}
function obtenervendedor() {
    _vendedor = {
        "opcion": 4,
        "cod_vend": $('#vendedor_v').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
        type: 'POST',
        data: _vendedor,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $('#vendedormombre_v').val(value.descripgenerico);
                $('#articulo_v').focus();


            });

        }
    });
}
function obtenerCliente() {
    jsonc = {
        "opcion": 2,
        "ci_v": $('#cedula_v').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
        type: 'POST',
        data: jsonc,
        cache: false,
        success: function (data) {
            if (JSON.stringify(data) != '[]') {
                $.each(data, function (indice, valor) {
                    $('#idcliente_v').val(valor.idgenerico);
                    $('#razonsocial_v').val(valor.descripgenerico);
                    $('#vendedor_v').focus();
                });

            } else {
                var consulta = confirm('Cliente no Registrado, desea agregar ?');
                if (consulta) {
                    $('#viewCliente').modal('show');
                    $('#ruc_v').val($('#cedula_v').val());
                    $('#cliente_v').focus();
                }
            }

        },
        error: function () {

        }

    });
}
function actufactura() {
    jsoncfac = {
        "opcion": 7,
        "fac": $('#idfactura_v').val(),
        "estado_v": 6
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
        type: 'POST',
        data: jsoncfac,
        cache: false,
        success: function () {

        },
        error: function () {

        }

    });


    setTimeout(function () {
        guardarDetalle();
        guardarCobro();

    }, 2000);


}
function anularfactura() {
    anufac = {
        "opcion": 11,
        "numerofac_v": $('#nrofactura_v').val(),
        "codestado_v": 2
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
        type: 'POST',
        data: anufac,
        cache: false,
        success: function (resp) {
            alert('FACTURA ANULADA');
        },
        error: function () {

        }

    });


}

function grabarcliente() {
    cliente = {
        "opcion": 10,
        "ruc_cliente": $('#ruc_v').val(),
        "razonsocial_cliente": $('#cliente_v').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
        type: 'POST',
        data: cliente,
        cache: false,
        success: function () {
            alert('GUARDADOR');
        },
        error: function () {

        }

    });

}
function grabartimbrado() {
    timbrados = {
        "opcion": 13,
        "nrotimbrado_v": $('#_timbrado').val(),
        "fvto_v": $('#_fechavto').val(),
        "coduser_v": 1,
        "establecimiento_v": $('#_nroexpe').val(),
        "caja_v": $('#_nrocaja').val(),
        "fdesde_v": $('#_facdesde').val(),
        "fhasta_v": $('#_fachasta').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
        type: 'POST',
        data: timbrados,
        cache: false,
        success: function () {
        },
        error: function () {

        }

    });
    setTimeout(function () {
        guardarDetallefactura();
        alert('Guardado correctamente');
    }, 2000);

}

function abricobro() {
    valores('subtotal_v');
    $('#cobroview').modal('show');
    $('#v_totalcobro').val($('#subtotal_v').val());
    $('#factura_cobro').val($('#nrofac_v').val());
    $('#v_clienteci').val($('#cedula_v').val());
    $('#v_clientenombre').val($('#razonsocial_v').val());
    $('#diferencia_v').val($('#v_totalcobro').val());

}
function guardarventa() {
    var filas = $('#v_tablaDetalle tr').length - 1;
    var fac = $('#nrofac_v').val();
    var cedu = $('#cedula_v').val();
    var rzonsocial = $('#razonsocial_v').val();
    var vende = $('#vendedor_v').val();
    var vendenombre = $('#vendedormombre_v').val();
    if (parseInt(filas) <= 0) {
        alert('No hay detalle a guardar...');
    } else {
        if (fac === "" || cedu === "" || rzonsocial === "" || vende === "" || vendenombre === "") {
            alert('Algunos datos no fueron cargados correctamente');
        } else {
            var resp = confirm("Desea guardar la VENTA ?");
            if (resp) {
                venta = {
                    "opcion": 5,
                    "nrofac_v": $('#nrofac_v').val(),
                    "tipopag_v": 1,
                    "idcliente_v": $('#idcliente_v').val(),
                    "idusuario_v": $('#idusersession_v').val(),
                    "iddeposito_v": $('#coddeposito_v').val(),
                    "idvendendor_v": $('#vendedor_v').val(),
                    "codapertura": $('#codapertura_ap').val()
                };
                $.ajax({
                    url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
                    type: 'POST',
                    data: venta,
                    cache: false,
                    success: function () {

                    },
                    error: function () {

                    }

                });
            } else {

            }
            actufactura();

        }

    }


}
var indx = 0;
function agregarfilacobro() {

    var v_tcobro = $('#v_tipocobro').val();
    var v_tcobrodescrip = $('#v_tipocobro option:selected').text();
    var v_nrocheque = $('#nrochque_ch').val();
    var v_bcocheque = $('#banco_che').val();
    var v_bcochequedescripcion = $('#banco_che option:selected').text();
    var v_idtipochque = $('#tipocheque_ch').val();
    var v_tipocheque = $('#tipocheque_ch option:selected').text();
    var v_tipotarjeta = $('#tarjettipo_t').val();
    var v_tipotarjetadescripcion = $('#tarjettipo_t option:selected').text();
    var v_identidademisora = $('#entemisora_t').val();
    var v_entidademisora = $('#entemisora_t option:selected').text();
    var v_nroboletatarjeta = $('#nroboleta_t').val();
    var v_montocobrar = $('#v_montocobrar').val();


    switch (parseInt(v_tcobro)) {
        case 1:
            v_bcocheque = 0;
            v_nrocheque = 0;
            v_bcochequedescripcion = 0;
            v_tipotarjeta = 0;
            v_tipotarjetadescripcion = 0;
            v_nroboletatarjeta = 0;
            v_identidademisora = 0;
            v_entidademisora = 0;
            v_idtipochque = 0;
            v_tipocheque = 0;
            break;
        case 2:
            v_bcocheque = 0;
            v_nrocheque = 0;
            v_bcochequedescripcion = 0;
            v_idtipochque = 0;
            v_tipocheque = 0;
            break;
        case 3:
            v_tipotarjeta = 0;
            v_tipotarjetadescripcion = 0;
            v_nroboletatarjeta = 0;
            v_identidademisora = 0;
            v_entidademisora = 0;
            break;
    }

    indx++;
    $('#tabladetallecobros').append("<tr id=\'prod" + indx + "\'>\
            <td style='display: none'>" + v_tcobro + "</td>\n\
            <td>" + v_tcobrodescrip + "</td>\n\
            <td>" + v_nrocheque + "</td>\n\
            <td style='display: none'>" + v_bcocheque + "</td>\n\
            <td>" + v_bcochequedescripcion + "</td>\n\
            <td style='display: none'>" + v_tipotarjeta + "</td>\n\
            <td style='display: none'>" + v_identidademisora + "</td>\n\
            <td>" + v_entidademisora + "</td>\n\
            <td>" + v_tipotarjetadescripcion + "</td>\n\
            <td>" + v_nroboletatarjeta + "</td>\n\
            <td>" + v_montocobrar + "</td>\n\
            <td ><button type=button title='Quitar el registro de la lista' style=text-align:center class='btn btn-sm btn-danger' onclick=\"$(\'#prod" + indx + "\').remove(),totalcobro()\">Quitar</button></td>\n\
            <td style='display: none'>" + v_idtipochque + "</td>\n\
            <td style='display: none'>" + v_tipocheque + "</td>\n\
            </tr>");

    totalcobro();
}
function totalcobro() {
    var total = 0;
    $('#tabladetallecobros tbody').find('tr').each(function (i, el) {
        total += parseFloat($(this).find('td').eq(10).text().replace(/\./g, ''));
    });
    $('#totalcobro_v').val(total);
    valores('diferencia_v');
    valores('totalcobro_v');
    var _acobrar = $('#v_totalcobro').val().replace(/\./g, '');
    var _cobrado = $('#totalcobro_v').val().replace(/\./g, '');
    var dif = parseInt(_acobrar) - parseInt(_cobrado);
    $('#diferencia_v').val(dif);
    valores('diferencia_v');
    indx++;

}

function guardarDetalle() {
    $('#v_tablaDetalle').find('tbody').find('tr').each(function () {
        datosdetalle = {
            "opcion": 8,
            "codarticulo_v": $(this).find("td").eq(0).html(),
            "codventa_v": $('#idventa_v').val(),
            "cantidad_v": $(this).find("td").eq(2).html(),
            "preciou_v": $(this).find("td").eq(3).html(),
            "codimpuesto_v": $(this).find("td").eq(4).html()
        };
        $.ajax({
            url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
            type: 'POST',
            data: datosdetalle,
            cache: false,
            dataType: 'text',
            success: function () {
            },
            error: function () {
            }
        });
    });
}
function guardarDetallefactura() {
    $('#tablaparametros').find('tbody').find('tr').each(function () {
        datosdetallefac = {
            "opcion": 14,
            "codtimbrado_v": $('#_timbrado').val(),
            "numfactura_v": $(this).find("td").eq(3).html()
        };
        $.ajax({
            url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
            type: 'POST',
            data: datosdetallefac,
            cache: false,
            dataType: 'text',
            success: function () {
            },
            error: function () {
            }
        });
    });
}
function condicionventa() {
    var valor = $('#condventa_v').val();


    if (parseInt(valor) === 1) {
        $("#cantcuota_v").prop('disabled', true);
        $("#montocuota_v").prop('disabled', true);
        $("#fechavto_v").prop('disabled', true);
    } else {
        if (parseInt(valor) === 2) {
            $("#cantcuota_v").removeAttr('disabled', true);
            $("#montocuota_v").removeAttr('disabled', true);
            $("#fechavto_v").removeAttr('disabled', true);
        }
    }

}
function condicionCobro() {
    var valor = $('#v_tipocobro').val();

    switch (parseInt(valor)) {
        case 1:
            $('#v_montocobrar').focus();
            $("#v_chque").hide();
            $("#v_tarjeta").hide();
            $('#texcobro_v').html('Cobro en Efectivo..');
            break;
        case 2:
            $("#v_tarjeta").show();
            $("#v_chque").hide();
            $('#texcobro_v').html('Cobro en Tarjeta..');
            break;

        case 3:
            $("#v_chque").show();
            $("#v_tarjeta").hide();
            $('#texcobro_v').html('Cobro en Cheque..');
            break;

    }
}
var tindex = 0;
function agregarfilaventas() {

    var v_cod = $('#articulo_v').val();
    var v_producto = $('#producto_v').val();
    var v_cantidad = $('#canti_v').val();
    var v_precioUnitario = $('#punitario_v').val().replace(/\./g, '');
    var v_subtotal = v_cantidad * v_precioUnitario;
    var v_impuesto = $('#impuesto_v').val();
    var v_idimpuesto = $('#idimpuesto_v').val();
    var v_impu10 = "0";
    var v_impu5 = "0";
    var v_impuexe = "0";
    if (v_impuesto === 'IVA 10%') {
        v_impu10 = v_impuesto;
    } else {
        if (v_impuesto === 'IVA 5%') {
            v_impu5 = v_impuesto;
        } else {
            if (v_impuesto === 'EXENTA') {
                v_impuexe = v_impuesto;
            }
        }

    }
    tindex++;
    $('#v_tablaDetalle').append("<tr id=\'prod" + tindex + "\'>\
            <td>" + v_cod + "</td>\n\
            <td>" + v_producto + "</td>\n\
            <td>" + v_cantidad + "</td>\n\
            <td>" + v_precioUnitario + "</td>\n\
            <td style='display: none'>" + v_idimpuesto + "</td>\n\
            <td>" + v_impu5 + "</td>\n\
            <td>" + v_impu10 + "</td>\n\
            <td>" + v_impuexe + "</td>\n\
            <td>" + v_subtotal + "</td>\n\
          <td ><button type=button title='Quitar el registro de la lista' style=text-align:center class='btn btn-sm btn-danger' onclick=\"$(\'#prod" + tindex + "\').remove(),subtotal()\">Quitar</button></td>\n\
            </tr>");

    $('#canti_v').focus();
    subtotal();

}

function subtotal() {
    monto = 0;
    acumu = 0;

    $('#v_tablaDetalle').find('tbody').find('tr').each(function () {
        monto = parseInt($(this).find("td").eq(8).html());
        acumu = acumu + monto;

    });
    $('#subtotal_v').val(acumu);
    valores('subtotal_v');
    tindex++;
}

function valores(numero) {
    var num = document.getElementById(numero).value.replace(/\./g, '');
    if (!isNaN(num)) {
        num = num.toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g, '$1.');
        num = num.split('').reverse().join('').replace(/^[\.]/, '');
        document.getElementById(numero).value = num;
    } else {
        alert('Solo se permiten numeros');
        document.getElementById(numero).value = document.getElementById(numero).value.replace(/[^\d\.]*/g, '');
    }

}

function cargarFactura() {
    $('#_tablefact').find('tbody').find('tr').empty();
    $('#fac').html($('#nrofac_v').val());
    $('#ruc').html($('#cedula_v').val());
    $('#cliente').html($('#razonsocial_v').val());
    $('#fccha').html($('#v_fechafac').val());

    $('#v_tablaDetalle').find('tbody').find('tr').each(function () {
        datosTabla = {
            "_cant": $(this).find("td").eq(2).html(),
            "_descrip": $(this).find("td").eq(1).html(),
            "_punitario": $(this).find("td").eq(3).html(),
            "_exent": $(this).find("td").eq(7).html(),
            "_iva5": $(this).find("td").eq(5).html(),
            "_iva10": $(this).find("td").eq(6).html()
        };
        $("#_tablefact").append($("<tr>").append($(
                "<td>" + datosTabla._cant + "</td>" +
                "<td>" + datosTabla._descrip + "</td>" +
                "<td>" + datosTabla._punitario + "</td>" +
                "<td>" + datosTabla._exent + "</td>" +
                "<td>" + datosTabla._iva5 + "</td>" +
                "<td style='background: #ffffcc'>" + datosTabla._iva10 + "</td>")));
    });
}
_index = 0;
function generarparametros() {
    var cont = 0;
    var _expe = $('#_nroexpe').val();
    var _caja = $('#_nrocaja').val();
    var _facdesde = $('#_facdesde').val();
    var _fachasta = $('#_fachasta').val();
    for (var i = 0, i = parseInt(_facdesde); i <= parseInt(_fachasta); i++) {
        cont++;
        $('#tablaparametros').append("<tr id=\'prod" + _index + "\'>\
                 <td>" + cont + "</td>\n\
                 <td>" + _expe + "</td>\n\
                 <td>" + _caja + "</td>\n\
                 <td>" +
                [i] + "</td>\n\
                </tr>");

    }
}

function guardarCobro() {

    $('#tabladetallecobros').find('tbody').find('tr').each(function () {

        detallecobro = {
            "opcion": 16,
            "impote_v": $(this).find("td").eq(10).html().replace(/\./g, ''),
            "idtipopago_v": $(this).find("td").eq(0).html(),
            "codigoventa_v": $('#idventa_v').val()
        };
        $.ajax({
            url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
            type: 'POST',
            data: detallecobro,
            cache: false,
            success: function () {
            },
            error: function () {
            }
        });

    });
    setTimeout(function () {
        cobrodetalle();
    }, 2000);

}
function cobrodetalle() {
    var cobro = 0;
    $('#tabladetallecobros').find('tbody').find('tr').each(function () {
        cobro = $(this).find("td").eq(0).html();
        switch (parseInt(cobro)) {
            case 2:
                tarjeta = {
                    "opcion": 17,
                    "codigoventa_v": $('#idventa_v').val(),
                    "nroboleta_v": $(this).find("td").eq(9).html(),
                    "entidademisora_v": $(this).find("td").eq(6).html(),
                    "tipotarjeta_v": $(this).find("td").eq(5).html()
                };
                $.ajax({
                    url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
                    type: 'POST',
                    data: tarjeta,
                    cache: false,
                    success: function () {
                    },
                    error: function () {
                    }
                });
                break;
            case 3:
                Cheque = {
                    "opcion": 18,
                    "codigoventa_vv": $('#idventa_v').val(),
                    "nrocheque_v": $(this).find("td").eq(2).html(),
                    "tipocheque_v": $(this).find("td").eq(12).html(),
                    "banco_v": $(this).find("td").eq(3).html()
                };
                $.ajax({
                    url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
                    type: 'POST',
                    data: Cheque,
                    cache: false,
                    success: function () {
                    },
                    error: function () {
                    }
                });
                break;
            default :
                break;

        }
    });
    alert('Venta facturada');
    location.reload();
}