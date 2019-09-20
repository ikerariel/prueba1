$(document).ready(function () {
    (function ($) {
        $('#filtrarModelos').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.buscarModelos tr').hide();
            $('.buscarModelos tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        });
    }(jQuery));
});
$(document).ready(function () {
    allModelos();
    nuevoListarMarcas();
    nuevoListarColores();
    $(":text").val("");
});

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "codmodelo": $('#idmodelo').val(),
        "modelodescri": $('#descrmodelo').val(),
        "codmarca": $('#idmarca').val(),
        "codcolor": $('#idcolor').val()

    };
}

function  getUltimoCodigo() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "Modeloscontrol",
        url: "http://localhost:8084/TALLERCASAJC/Modeloscontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#idmodelo").val(resp);
//            $("#codciudad").val(resp);           
            $("#descrmodelo").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  getmorstraModelosFiltro() {
    crearJSON(6);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Modeloscontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#descrmodelo").val(value.mod_descripcion);
                //  para tirar
                $('#idmarca> option[value=' + value.id_marca + ']').attr('selected', 'selected');
                $('#idcolor> option[value=' + value.id_color + ']').attr('selected', 'selected');
                $("#descrmodelo").focus();
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
//        url: "http://localhost:8084/TALLERCASAJC/Modeloscontrol",
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


function  ambModelos(id) {
    crearJSON(id);
    $.ajax({
        // url: "Modeloscontrol",
        url: "http://localhost:8084/TALLERCASAJC/Modeloscontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function () {
            alert('Registro Realizado Correctamente...!!!');
            location.reload();
            //allModelos();
        },
        error: function () {
            alert('Error Durante la Insercion del Registro...!!!');
        }
    });
}

function recuperar() {
    $('#miTablaModelos tr').click(function () {
        $('#idmodelo').val($(this).find("td").eq(0).html());
        $('#descrmodelo').val($(this).find("td").eq(1).html());
        $('#idmarca').val($(this).find("td").eq(2).html());
        $('#idcolor').val($(this).find("td").eq(3).html());
    });
}

function allModelos() {
//alert("Llega allModelos");
    crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Modeloscontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            //alert(resp);
            // var jparse=JSON.parse(resp);
            // alert(jparse);
            $.each(resp, function (indice, value) {
                $("#miTablaModelos").append($("<tr>").append($(
                        "<td>" + value.id_modelo + "</td>" +
                        "<td>" + value.mod_descripcion + "</td>" +
                        "<td>" + value.mar_descripcion + "</td>" +
                        "<td>" + value.colo_descripcion + "</td>")));

            });
        }
    });
}
function nuevoListarMarcas() {
    crearJSON(7);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Modeloscontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (data) {
            $.each(data, function (indice, value) {
                $("#idmarca").append("<option value= \"" + value.id_marca + "\"> " + value.mar_descripcion + "</option>");
            });
        }
    });
}

function nuevoListarColores() {
    crearJSON(8);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Modeloscontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (data) {
            $.each(data, function (indice, value) {
                $("#idcolor").append("<option value= \"" + value.id_color + "\"> " + value.colo_descripcion + "</option>");
            });
        }
    });
}

function reportesModelos() {
    window.open("reportesModelos.jsp");

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
//        url: "http://localhost:8084/TALLERCASAJC/Barrioscontrol",
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

