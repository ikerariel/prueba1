$(document).ready(function () {
    (function ($) {
        $('#filtrarBarrios').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.buscarBarrios tr').hide();
            $('.buscarBarrios tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        });
    }(jQuery));
});
$(document).ready(function () {
    allBarrios();
    nuevoListarCiudades();
    $(":text").val("");
});

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "codbarrio": $('#idbarrio').val(),
        "barriodescri": $('#descrbarrio').val(),
//        "codciudad": $('#idciu').val()
        "codciudad": $('#idciudad').val()
    };
}

function  getUltimoCodigoBarrios() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "Barrioscontrol",
        url: "http://localhost:8084/TALLERCASAJC/Barrioscontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#idbarrio").val(resp);
//            $("#codciudad").val(resp);           
            $("#descrbarrio").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  getmorstraBarriosFiltro() {
    crearJSON(6);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Barrioscontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#descrbarrio").val(value.barr_descripcion);
                //  para tirar
                $('#idciudad> option[value=' + value.id_ciudad + ']').attr('selected', 'selected');
                $("#descrbarrio").focus();
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
//        url: "http://localhost:8084/TALLERCASAJC/Barrioscontrol",
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


function  ambBarrios(id) {
    crearJSON(id);
    $.ajax({
        // url: "Barrioscontrol",
        url: "http://localhost:8084/TALLERCASAJC/Barrioscontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function () {
            alert('Registro Realizado Correctamente...!!!');
            location.reload();
            //allBarrios();
        },
        error: function () {
            alert('Error Durante la Insercion del Registro...!!!');
        }
    });
}

function recuperar() {
    $('#miTablaBarrios tr').click(function () {
        $('#idbarrio').val($(this).find("td").eq(0).html());
        $('#descrbarrio').val($(this).find("td").eq(1).html());
        $('#idciudad').val($(this).find("td").eq(2).html());
    });
}

function allBarrios() {
//alert("Llega allBarrios");
    crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Barrioscontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            //alert(resp);
            // var jparse=JSON.parse(resp);
            // alert(jparse);
            $.each(resp, function (indice, value) {
                $("#miTablaBarrios").append($("<tr>").append($(
                        "<td>" + value.id_barrio + "</td>" +
                        "<td>" + value.barr_descripcion + "</td>" +
                        "<td>" + value.ciu_descripcion + "</td>")));

            });
        }
    });
}
function nuevoListarCiudades() {
    crearJSON(7);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Barrioscontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (data) {
            $.each(data, function (indice, value) {
                $("#idciudad").append("<option value= \"" + value.id_ciudad + "\"> " + value.ciu_descripcion + "</option>");
            });
        }
    });
}
function campovacioBarrios() { //Para verificar campos vacio
    var a = $('#descrbarrio').val(); //nombre del campos
    if (a === "") {
        alert('campo vacio');
        $('#descrbarrio');
    } else {
        ambBarrios(1);
    }
}

function ControlarCampoBarrios(){  // Para que no se repita nombre
    var dato;
    var Barrios = $('#descrbarrio').val();
    // alert(Barrios);
    $('#miTablaBarrios tr').each(function () {
        dato = $(this).find('td').eq(1).html();
        if (dato === Barrios) {
            alert('EL BARRIO YA EXISTE');
            $('#descrbarrio').val(null); //Vaciar Campos
            $('#descrbarrio').focus(); 
        } else {
        }
    });
}
function reportesBarrios() {
    window.open("reportesBarrios.jsp");

}

