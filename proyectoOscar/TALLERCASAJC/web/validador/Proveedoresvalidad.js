$(document).ready(function () {
    (function ($) {
        $('#filtrarProveedores').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.buscarProveedores tr').hide();
            $('.buscarProveedores tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        });
    }(jQuery));
});
$(document).ready(function () {
    allProveedores();
    nuevoListarCiudades();
    $(":text").val("");

});
//1. conecta con control "codproveedor"naranja
//conecta con jsp "codproveedor" verde
function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "codproveedor": $('#idproveedor').val(),
        "rassocialprov": $('#provrassocial').val(),
        "direccionprov": $('#provdireccion').val(),
        "pagwebprov": $('#provpagweb').val(),
        "telefonoprov": $('#provtelefono').val(),
        "rucprov": $('#provruc').val(),
        "codciudad": $('#idciudad').val()

    };
}
function  getUltimoCodigoProveedores() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "Proveedorescontrol",
        url: "http://localhost:8084/TALLERCASAJC/Proveedorescontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#idproveedor").val(resp);
            $("#provrassocial").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  getmostrarProveedoresFiltro() {
    // alert('Llega listarProveedoresSegunFiltro ');
    crearJSON(6);

    $.ajax({
        // url: "Proveedorescontrol",
        url: "http://localhost:8084/TALLERCASAJC/Proveedorescontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#provrassocial").val(value.ras_social);
//                $("#provdireccion").val(value.direccion);
//                $("#provpagweb").val(value.pag_web);
//                $("#provtelefono").val(value.telefono);
//                $("#provruc").val(value.ruc);
                $('#idciudad> option[value=' + value.id_ciudad + ']').attr('selected', 'selected');
                $("#provrassocial").focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}

function  ambProveedores(id) {
    crearJSON(id);
    $.ajax({
        // url: "Proveedorescontrol",
        url: "http://localhost:8084/TALLERCASAJC/Proveedorescontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function () {
            alert('Registro Insertado Correctamente...!!!');
            location.reload();
            //allProveedores();
        },
        error: function () {
            alert('Error Durante la Insercion del Registro...!!!');
        }
    });
}

function recuperar() {
    $('#miTablaProveedores tr').click(function () {
        $('#idproveedor').val($(this).find("td").eq(0).html());
        $('#provrassocial').val($(this).find("td").eq(1).html());
        $('#provdireccion').val($(this).find("td").eq(2).html());
        $('#provpagweb').val($(this).find("td").eq(3).html());
        $('#provtelefono').val($(this).find("td").eq(4).html());
        $('#provruc').val($(this).find("td").eq(5).html());
        $('#idciudad').val($(this).find("td").eq(6).html());
    });
}

//alert("Llega allProveedores");
function allProveedores() {
    crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Proveedorescontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaProveedores").append($("<tr>").append($("<td>" + value.id_proveedor + "</td>" +
                        "<td>" + value.ras_social + "</td>" + "<td>" + value.direccion + "</td>" +
                        "<td>" + value.pag_web + "</td>" + "<td>" + value.telefono + "</td>" +
                        "<td>" + value.ruc + "</td>" + "<td>" + value.ciu_descripcion + "</td>")));
            });
        }
    });
}

function nuevoListarCiudades() {
    crearJSON(7);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Proveedorescontrol",
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


function campovacioproveedores() { //Para verificar campos vacio
    var a = $('#provrassocial').val(); //nombre del campos
    var a = $('#provdireccion').val(); //nombre del campos
    var a = $('#provpagweb').val(); //nombre del campos
    var a = $('#provtelefono').val(); //nombre del campos
    var a = $('#provruc').val(); //nombre del campos
    if (a === "") {
        alert('campo vacio');
        $('#provrassocial');
        $('#provdireccion');
        $('#provpagweb');
        $('#provtelefono');
        $('#provruc');
    } else {
        aambProveedores(1);
    }
}
    
function ControlarCampoProveedores(){  // Para que no se repita nombre
    var dato;
    var Proveedores = $('#provruc').val();
    // alert(ciudades);
    $('#miTablaProveedores tr').each(function () {
        dato = $(this).find('td').eq(5).html();
        if (dato === Proveedores) {
            alert('ESTE RUC YA EXISTE');
            $('#provruc').val(null); //Vaciar Campos
            $('#provruc').focus(); 
        } else {
        }
    });
}

function reportesProveedor() {
    window.open("reportesProveedor.jsp");
}