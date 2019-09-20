$(document).ready(function () {

    Opciones();
    (function ($) {
        $('#filtrarCiudades').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.buscarCiudades tr').hide();
            $('.buscarCiudades tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        });
    }(jQuery));
});
$(document).ready(function () {
    allCiudades();

});


function reportesCiudades()
{
    window.open("reportes.jsp?cod=" + 1 + " ", "_blank");

}

function reportesCIU() {
//    valor = $("#v_nropedido").val();
    window.open("reportesCiudad_v.jsp?_blank");

}

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "idciudad": $('#codciudad').val(),
        "descriciudad": $('#descrciudad').val()
//conecta con jsp "codciudad" naranja
//conecta con control "idciudad" naranja
    };
}

function  getUltimoCodigo() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "Ciudadescontrol",
        url: "http://localhost:8084/TALLERCASAJC/Ciudadescontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#codciudad").val(resp);
            $("#descrciudad").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  recuperarCiudadesporID() {
    // alert('Llega recuperarCiudadesporID ');
    crearJSON(6);

    $.ajax({
        // url: "Ciudadescontrol",
        url: "http://localhost:8084/TALLERCASAJC/Ciudadescontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#descrciudad").val(value.ciu_descripcion);
                $("#descrciudad").val.focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}


function  ambCiudades(id) {
    crearJSON(id);
    $.ajax({
        // url: "Ciudadescontrol",
        url: "http://localhost:8084/TALLERCASAJC/Ciudadescontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function () {
            alert('Registro Realizado Correctamente...!!!');
            location.reload();
            //allCiudades();
        },
        error: function () {
            alert('Error Durante la Insercion del Registro...!!!');
        }
    });
}

function recuperar() {
    $('#miTablaCiudades tr').click(function () {
        $('#codciudad').val($(this).find("td").eq(0).html());
        $('#descrciudad').val($(this).find("td").eq(1).html());
    });
}

//function modificar() {
//    $('#miTabla tr').click(function () {
//        $('#cod_caja').val($(this).find("td").eq(0).html());
//        $('#descr_caja').val($(this).find("td").eq(1).html());
//    });
//}

function allCiudades() {
//alert("Llega allCiudades");
    crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Ciudadescontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            //alert(resp);
            // var jparse=JSON.parse(resp);
            // alert(jparse);
            $.each(resp, function (indice, value) {
                $("#miTablaCiudades").append($("<tr>").append($("<td>" + value.id_ciudad + "</td>" +
                        "<td>" + value.ciu_descripcion + "</td>")));

            });

            //$('#miTableHtml').html(resp);
        }
    });
}

function campovaciociudad() { //Para verificar campos vacio
    var a = $('#descrciudad').val(); //nombre del campos
    if (a === "") {
        alert('campo vacio');
        $('#descrciudad');
    } else {
        ambCiudades(1);
    }
}

function ControlarCampoCiudades() {  // Para que no se repita nombre
    var dato;
    var Ciudades = $('#descrciudad').val();
    // alert(ciudades);
    $('#miTablaCiudades tr').each(function () {
        dato = $(this).find('td').eq(1).html();
        if (dato === Ciudades) {
            alert('EL NOMBRE YA EXISTE');
            $('#descrciudad').val(null); //Vaciar Campos
            $('#descrciudad').focus();
        } else {
        }
    });
}

function reportesCiudades() {
    window.open("reportesCiudades.jsp");
}



