$(document).ready(function () {
    (function ($) {
        $('#filtrarColores').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.buscarColores tr').hide();
            $('.buscarColores tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        });
    }(jQuery));
});
$(document).ready(function () {
    allColores();
    $(":text").val("");
});

function reportesColores()
{
        window.open("reportes.jsp?cod=" + 1 + " ", "_blank");
        
}

function reportesCOLO() {
//    valor = $("#v_nropedido").val();
    window.open("reportesColor_v.jsp?_blank");

}

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "idcolor": $('#codcolor').val(),
        "descricolor": $('#descrcolor').val()
//conecta con jsp "codcolor" naranja
//conecta con control "idcolor" naranja
    };
}

function  getUltimoCodigo() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "Colorescontrol",
        url: "http://localhost:8084/TALLERCASAJC/Colorescontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#codcolor").val(resp);
            $("#descrcolor").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  recuperarColoresporID() {
    // alert('Llega recuperarColoresporID ');
    crearJSON(6);

    $.ajax({
        // url: "Colorescontrol",
        url: "http://localhost:8084/TALLERCASAJC/Colorescontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#descrcolor").val(value.colo_descripcion);
                $("#descrcolor").val.focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}


function  ambColores(id) {
    crearJSON(id);
    $.ajax({
        // url: "Colorescontrol",
        url: "http://localhost:8084/TALLERCASAJC/Colorescontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function () {
            alert('Registro Realizado Correctamente...!!!');
            location.reload();
            //allColores();
        },
        error: function () {
            alert('Error Durante la Insercion del Registro...!!!');
        }
    });
}

function recuperar() {
    $('#miTablaColores tr').click(function () {
        $('#codcolor').val($(this).find("td").eq(0).html());
        $('#descrcolor').val($(this).find("td").eq(1).html());
    });
}

//function modificar() {
//    $('#miTabla tr').click(function () {
//        $('#cod_caja').val($(this).find("td").eq(0).html());
//        $('#descr_caja').val($(this).find("td").eq(1).html());
//    });
//}

function allColores() {
//alert("Llega allColores");
    crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Colorescontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            //alert(resp);
            // var jparse=JSON.parse(resp);
            // alert(jparse);
            $.each(resp, function (indice, value) {
                $("#miTablaColores").append($("<tr>").append($("<td>" + value.id_color + "</td>" +
                        "<td>" + value.colo_descripcion + "</td>")));

            });

            //$('#miTableHtml').html(resp);
        }
    });
}

function campovacioColores() { //Para verificar campos vacio
    var a = $('#descrcolor').val(); //nombre del campos
    if (a === "") {
        alert('campo vacio');
        $('#descrcolor');
    } else {
        ambColores(1);
    }
}

function ControlarCampoColores(){  // Para que no se repita nombre
    var dato;
    var Colores = $('#descrcolor').val();
    // alert(ciudades);
    $('#miTablaColores tr').each(function () {
        dato = $(this).find('td').eq(1).html();
        if (dato === Colores) {
            alert('ESTE COLOR YA EXISTE');
            $('#descrcolor').val(null); //Vaciar Campos
            $('#descrcolor').focus(); 
        } else {
        }
    });
}
function reportesColores() {
    window.open("reportesColores.jsp");
}


