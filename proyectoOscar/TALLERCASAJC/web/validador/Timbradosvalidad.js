$(document).ready(function () {
    (function ($) {
        $('#filtrarTimbrados').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.buscarTimbrados tr').hide();
            $('.buscarTimbrados tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        });
    }(jQuery));
});
$(document).ready(function () {
    allTimbrados();
    nuevoListarEstados();
    
    $(":text").val("");
});

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "codtimbrado": $('#idtimbrado').val(),
        "numer": $('#num').val(),
        "inifech": $('#fechini').val(),
        "finfech": $('#fechfin').val(),
        "vencimient": $('#venci').val(),
        "codestado": $('#idestado').val()
    };

}

function  getUltimoCodigoTimbrados() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "Timbradoscontrol",
        url: "http://localhost:8084/TALLERCASAJC/Timbradoscontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#idtimbrado").val(resp);
//            $("#codciudad").val(resp);           
            $("#num").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  getmorstraTimbradosFiltro() {
    crearJSON(6);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Timbradoscontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#num").val(value.barr_descripcion);
                //  para tirar
                $('#idestado> option[value=' + value.id_estado + ']').attr('selected', 'selected');
                $("#num").focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}

//function  recuperarBarriosporID() {
//    // alert('Llega recuperarBarriosporID ');
//    crearJSON(6);
//
//    $.ajax({
//        // url: "Barrioscontrol",
//        url: "http://localhost:8084/TALLERCASAJC/Timbradoscontrol",
//        data: datosJSON,
//        type: 'POST',
//        success: function (resp) {
//            $.each(resp, function (indice, value) {
//                $("#descrbarrio").val(value.barr_descripcion);
//                $("#descrbarrio").val.focus();
//            });
//            return true;
//        },
//        error: function () {
//            alert('No existe registro segun codigo ingresado!!!');
//            return false;
//        }
//    });
//}


function  ambTimbrados(id) {
    crearJSON(id);
    $.ajax({
        // url: "Timbradoscontrol",
        url: "http://localhost:8084/TALLERCASAJC/Timbradoscontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function () {
            alert('Registro Realizado Correctamente...!!!');
            location.reload();
            //allTimbrados();
        },
        error: function () {
            alert('Error Durante la Insercion del Registro...!!!');
        }
    });
}

function recuperar() {
    $('#miTablaTimbrados tr').click(function () {
        $('#idtimbrado').val($(this).find("td").eq(0).html());
        $('#num').val($(this).find("td").eq(1).html());
        $('#fechini').val($(this).find("td").eq(2).html());
        $('#fechfin').val($(this).find("td").eq(3).html());
        $('#venci').val($(this).find("td").eq(4).html());
        $('#idestado').val($(this).find("td").eq(5).html());
    });
}

function allTimbrados() {
//alert("Llega allTimbrados");
    crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Timbradoscontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            //alert(resp);
            // var jparse=JSON.parse(resp);
            // alert(jparse);
            $.each(resp, function (indice, value) {
                $("#miTablaTimbrados").append($("<tr>").append($(
                        "<td>" + value.id_timbrado + "</td>" +
                        "<td>" + value.numero + "</td>" +
                        "<td>" + value.inicio_fecha + "</td>" +
                        "<td>" + value.final_fecha + "</td>" +
                        "<td>" + value.vencimientos + "</td>" +
                        "<td>" + value.est_descripcion + "</td>")));

            });
        }
    });
}
function nuevoListarEstados() {
    crearJSON(7);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Timbradoscontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (data) {
            $.each(data, function (indice, value) {
                $("#idestado").append("<option value= \"" + value.id_estado + "\"> " + value.est_descripcion + "</option>");
            });
        }
    });
}

function reportesTimbrados() {
    window.open("reportesTimbrados.jsp");

}
//$(document).ready(function () {
//    (function ($) {
//        $('#filtraridciudad').keyup(function () {
//            var rex = new RegExp($(this).val(), 'i');
//            $('.buscaridciudad tr').hide();
//            $('.buscaridciudad tr').filter(function () {
//                return rex.test($(this).text());
//            }).show();
//        });
//    }(jQuery));
//});
//
//function abrirCiudad() {
//    if ($('#idciudad').val() === "") {
//        $('#grillaCiudades').modal('show');
//        $('#miTablaC').find('tbody').find('tr').empty();
//        nuevoListarCiudades();
//    } else {
//    }
//}
//function seleccionCiudades() {
//    $('#miTablaC tr').click(function () {
//        $('#idciu').val($(this).find("td").eq(0).html());
//        $('#idciudad').val($(this).find("td").eq(1).html());
//        $('#grillaCiudades').modal('hide');
//    });
//}
//function nuevoListarCiudades() {
//    crearJSON(7);
//    $.ajax({
//        url: "http://localhost:8084/TALLERCASAJC/Timbradoscontrol",
//        type: 'POST',
//        data: datosJSON,
//        cache: false,
//        success: function (resp) {
//            $.each(resp, function (indice, value) {
//                $("#miTablaC").append($("<tr>").append($("<td>" + value.id_ciudad + "</td>" +
//                        "<td>" + value.ciu_descripcion + "</td>")));
//            });
//        }
//    });
//}


