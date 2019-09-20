$(document).ready(function () {
    listatimbrados();
    getfacturacion();
    getapertura();

});

function verificarvariable(campo) {
    var variable = $('#' + campo).val();
    var resultado = isNaN(variable);
    switch (resultado) {
        case true:
            $('#' + campo).focus();
            $('#' + campo).val(null);
            break;
        case false:
            break;
        default :
            break;
    }

}

function puntodecimal(numero) {
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

function getapertura() {
    aperturaDatos = {
        'opcion': 1
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/aperturacierrecajaSERVLET",
        type: 'POST',
        data: aperturaDatos,
        cache: false,
        success: function (resp) {
            console.log(resp);
            $.each(resp, function (indice, valor) {
                $("#v_tablaapertura").append($("<tr>").append($(
                        "<td>" + valor.id_apcica + "</td>" +
                        "<td>" + valor.apertura_fecha + "</td>" +
                        "<td>" + valor.caja + "</td>" +
                        "<td>" + valor.cajero + "</td>" +
                        "<td bgcolor='#d9edf7'>" + valor.supervisor + "</td>" +
                        "<td bgcolor='#d9edf7'>" + valor.estado + "</td>")));
            });


        }

    });
}
function getfacturacion() {
    $('#cajero_v').val($('#usertext_v').val());
    json = {
        'opcion': 2,
        'logueo': $('#cajero_v').val()
    };
    $.ajax({
        url: "/TALLERCASAJC/aperturacierrecajaSERVLET",
        type: 'POST',
        data: json,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, valor) {
                $('#nrocaja_v').val(valor.caja);
                $('#nrofac_v').val(valor.factura);
                $('#idfactura_v').val(valor.idfactura);
                $('#codapertura_ap').val(valor.id_apcica);
                $('#cajero_v').val($('#usertext_v').val());
            });


        }

    });
}
function totalfacturdo() {
    facturado = {
        'opcion': 5,
        'totalfacturado_fact1': $('#codarqueo_ap').val(),
        'totalfacturado_fact2': $('#codarqueo_ap').val()
    };
    $.ajax({
        url: "/TALLERCASAJC/aperturacierrecajaSERVLET",
        type: 'POST',
        data: facturado,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, valor) {
                $('#totalfactu_fac').val(valor.id_apcica);

            });


        }

    });
}

function insertarApetura() {
    if ($('#montoapertura_ap').val() === "" || $('#idtimbrado_vp').val() === "" || $('#idtimbrado_ap').val() === "") {
        alert('Algunos campos no fueron cargados correctamente...');
    } else {
        var res = confirm("Desea guardar la apertura ?");
        if (res) {
            apertura = {
                'opcion': 3,
                'montoapertura': $('#montoapertura_ap').val(),
                'codigocaja': $('#codigocaja_ap').val(),
                'codigosucursal': $('#codsucursal_v').val(),
                'codigodeposito': $('#coddeposito_v').val(),
                'codigosupervisor': $('#idsuperv_v').val(),
                'codigocajero': $('#codigocajero_ap').val(),
                'codigotimbrado': $('#idtimbrado_vp').val()
            };

            $.ajax({
                url: "/TALLERCASAJC/aperturacierrecajaSERVLET",
                type: 'POST',
                data: apertura,
                cache: false,
                success: function () {
                    alert('guardado');
                }
            });
        } else {

        }
    }






}
function insertarMovimientoapertura() {
    var montototal = $('#totalfactu_fac').val();
    var montoarqueo = $('#totalarqueo_v').val().replace(/\./g, '');

    if (montototal === montoarqueo) {
        $('#tablearqueo').find('tbody').find('tr').each(function () {
            movimiento = {
                'opcion': 4,
                'cantmoneda_ap': $(this).find("td").eq(2).html(),
                'montouni_ap': $(this).find("td").eq(4).html(),
                'codiapertura_ap': $('#codarqueo_ap').val(),
                'denominacionmoneda_ap': $(this).find("td").eq(0).html(),
                'nrochque_ap': $(this).find("td").eq(3).html()
            };

            $.ajax({
                url: "/TALLERCASAJC/aperturacierrecajaSERVLET",
                type: 'POST',
                data: movimiento,
                cache: false,
                success: function () {
                    alert('guardado');
                }
            });
        });
    } else {
        var diferencia = montototal - montoarqueo;
        alert('Hay una diferencia de : ' + diferencia);
    }




}

var indice = 0;
function seleccionchequetarjeta() {
    var v_moneda = $('#tiposmonedasarqueo').val();
    var v_monedadescrip = $('#tiposmonedasarqueo option:selected').text();
    if (parseInt(v_moneda) === 11) {
        $("#nrotarjetacheque_v").removeAttr('disabled', true);
        $("#montochquetarjeta_v").removeAttr('disabled', true);
        $("#cantimoneda_v").prop('disabled', true);
        $('#nrotarjetacheque_v').focus();
    } else if (parseInt(v_moneda) === 12) {
        $("#nrotarjetacheque_v").removeAttr('disabled', true);
        $("#montochquetarjeta_v").removeAttr('disabled', true);
        $("#cantimoneda_v").prop('disabled', true);
        $("#textchetarjeta_v").html('Nro. Boleta');
        $('#nrotarjetacheque_v').focus();
    } else {
        $("#nrotarjetacheque_v").prop('disabled', true);
        $("#montochquetarjeta_v").prop('disabled', true);
        $("#cantimoneda_v").prop('disabled', false);
        $('#cantimoneda_v').focus();
    }

}
function agregarfilaarqueo() {
    var resul = 0;
    var suma = 0;
    var v_nroBoleta = 0;
    var v_moneda = $('#tiposmonedasarqueo').val();
    var v_monedadescrip = $('#tiposmonedasarqueo option:selected').text();
    var v_cant = $('#cantimoneda_v').val();

    switch (parseInt(v_moneda)) {
        case 1:
            resul = 50 * v_cant;
            break;
        case 2:
            resul = 100 * v_cant;
            break;
        case 3:
            resul = 500 * v_cant;
            break;
        case 4:
            resul = 1000 * v_cant;
            break;
        case 5:
            resul = 2000 * v_cant;
            break;
        case 6:
            resul = 5000 * v_cant;
            break;
        case 7:
            resul = 10000 * v_cant;
            break;
        case 8:
            resul = 20000 * v_cant;
            break;
        case 9:
            resul = 50000 * v_cant;
            break;
        case 10:
            resul = 100000 * v_cant;
            break;
        case 11:
            v_nroBoleta = $('#nrotarjetacheque_v').val();
            resul = $('#montochquetarjeta_v').val();
            v_cant = 1;
            break;
        case 12:
            v_nroBoleta = $('#nrotarjetacheque_v').val();
            resul = $('#montochquetarjeta_v').val();
            v_cant = 1;
            break;
    }


    indice++;
    $('#tablearqueo').append("<tr id=\'prod" + indice + "\'>\
            <td>" + v_moneda + "</td>\n\
            <td>" + v_monedadescrip + "</td>\n\
            <td>" + v_cant + "</td>\n\
            <td>" + v_nroBoleta + "</td>\n\
            <td>" + resul + "</td>\n\
          <td ><button type=button title='Quitar el registro de la lista' style=text-align:center class='btn btn-sm btn-danger' onclick=\"$(\'#prod" + indice + "\').remove();totalarqueo()\">Quitar</button></td>\n\
            </tr>");


    totalarqueo();
}


function totalarqueo() {
    var total = 0;
    $('#tablearqueo tbody').find('tr').each(function (i, el) {
        total += parseFloat($(this).find('td').eq(4).text());
    });
    $('#totalarqueo_v').val(total);
    valores('totalarqueo_v');
    indice++;
}

function seleccionarqueo() {
    $('#v_tablaapertura tr').click(function () {
        $('#codarqueo_ap').val($(this).find("td").eq(0).html());
        $('#estadoapertura_ap').val($(this).find("td").eq(5).html());

    });
}
function cerrarCaja() {

    var estado_ap = $('#estadoapertura_ap').val();

    if (estado_ap === 'ABIERTA') {
        $('#cierrecaja').modal('show');
        totalfacturdo();
    } else if (estado_ap === 'CERRADA') {
        alert('la caja esta cerrada');
    } else if (estado_ap === "") {
        alert('Selecciona un registro');
    }


}
function reportefacturado() {

    var estado_ap = $('#estadoapertura_ap').val();

    if (estado_ap === "") {
        alert('Selecciona un registro');
    } else {
        resportefacturacion();
    }


}
function reportearqueo() {

    var estado_ap = $('#estadoapertura_ap').val();
    if (estado_ap === 'ABIERTA') {
        alert('La caja aún esta abierta');
    } else if (estado_ap === 'CERRADA') {
        resportearqueo();
    } else if (estado_ap === "") {
        alert('Selecciona un registro');
    }


}
function resportefacturacion() {
    if ($('#codarqueo_ap').val() === "") {
        alert('Debes seleccionar una caja..');
        $('#codarqueo_ap').focus();
    } else {
        valor = $('#codarqueo_ap').val();
        window.open("reportesfacturacion.jsp?cod=" + valor + "", "_blank");
    }

}
function resportearqueo() {
    if ($('#codarqueo_ap').val() === "") {
        alert('Debes seleccionar una caja..');
        $('#codarqueo_ap').focus();
    } else {
        valor = $('#codarqueo_ap').val();
        window.open("reportesarqueo.jsp?cod=" + valor + "", "_blank");
    }

}

function listatimbrados() {
    timbrado = {
        "opcion": 6
    };
    $.ajax({
        url: "/TALLERCASAJC/aperturacierrecajaSERVLET",
        type: 'POST',
        data: timbrado,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#listatimbrado").append("<option value= \"" + value.id_apcica + "\"> " + value.apertura_fecha + "</option>");

            });

        }
    });
}
function obtnertimbrado() {
    codtimbrado = {
        "opcion": 7,
        "codtimbrado": $('#idtimbrado_vp').val()
    };
    $.ajax({
        url: "/TALLERCASAJC/aperturacierrecajaSERVLET",
        type: 'POST',
        data: codtimbrado,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $('#idtimbrado_ap').val(value.numero);
            });

        }
    });
}