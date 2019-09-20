/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    (function ($) {
        $('#filtrarClientes').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.buscarClientes tr').hide();
            $('.buscarClientes tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        });
    }(jQuery));
});
$(document).ready(function () {
    allClientes();
    nuevoListarBarrios();
    nuevoListarCiudades();
    $(":text").val("");
});

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "codcliente": $('#idcliente').val(),
        "cliruc": $('#ruccli').val(),
        "clirazonsocial": $('#razonsocialcli').val(),
        "clitelefono": $('#telefonocli').val(),
        "clidireccion": $('#direccioncli').val(),
        "cliweb": $('#webcli').val(),
        "codbarrio": $('#idbarrio').val(),
        "codciudad": $('#idciudad').val()
    };
}

function  getUltimoCodigoClientes() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "Empleadoscontrol",
        url: "http://localhost:8084/TALLERCASAJC/ClientesServlet",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#idcliente").val(resp);
            $("#ruccli").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  getmostrarClientesFiltro() {
    // alert('Llega recuperarClientesporID ');
    crearJSON(6);

    $.ajax({
        // url: "Empleadoscontrol",
        url: "http://localhost:8084/TALLERCASAJC/ClientesServlet",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#ruccli").val(value.nombre);
                //  para tirar
                $('#idbarrio> option[value=' + value.id_barrio + ']').attr('selected', 'selected');
                $('#idciudad> option[value=' + value.id_ciudad + ']').attr('selected', 'selected');
                $("#ruccli").focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}


function  ambClientes(id) {
    crearJSON(id);
    $.ajax({
        // url: "Empleadoscontrol",
        url: "http://localhost:8084/TALLERCASAJC/ClientesServlet",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function () {
            alert('Registro Realizado Correctamente...!!!');
            location.reload();
            //allEmpleados();
        },
        error: function () {
            alert('Error Durante la Insercion del Registro...!!!');
        }
    });
}

function recuperar() {
    $('#miTablaClientes tr').click(function () {
        $('#idcliente').val($(this).find("td").eq(0).html());
        $('#ruccli').val($(this).find("td").eq(1).html());
        $('#razonsocialcli').val($(this).find("td").eq(2).html());
        $('#telefonocli').val($(this).find("td").eq(3).html());
        $('#direccioncli').val($(this).find("td").eq(4).html());
        $('#webcli').val($(this).find("td").eq(5).html());
        $('#idbarrio').val($(this).find("td").eq(6).html());
        $('#idciudad').val($(this).find("td").eq(7).html());
    });
}

function allClientes() {
//alert("Llega allEmpleados");
    crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/ClientesServlet",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            //alert(resp);
            // var jparse=JSON.parse(resp);
            // alert(jparse);
            $.each(resp, function (indice, value) {
                $("#miTablaClientes").append($("<tr>").append($(
                        "<td>" + value.id_cliente + "</td>" +
                        "<td>" + value.ruc + "</td>" +
                        "<td>" + value.razonsocial + "</td>" +
                        "<td>" + value.telefono + "</td>" +
                        "<td>" + value.direccion + "</td>" +
                        "<td>" + value.web + "</td>" +
                        "<td>" + value.barr_descripcion + "</td>" +
                        "<td>" + value.ciu_descripcion + "</td>")));
            });
        }
    });
}

function nuevoListarBarrios() {
    crearJSON(7);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/ClientesServlet",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (data) {
            $.each(data, function (indice, value) {
                $("#idbarrio").append( "<option value= \"" + value.id_barrio + "\"> " + value.barr_descripcion + "</option>");
            });
        }
    });
}

function nuevoListarCiudades() {
    crearJSON(8);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/ClientesServlet",
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

function reportesClientes() {
    window.open("reportesClientes.jsp");
}

function campovacioclientes() { //Para verificar campos vacio
    var a = $('#ruccli').val(); //nombre del campos
    var a = $('#razonsocialcli').val(); //nombre del campos
    var a = $('#telefonocli').val(); //nombre del campos
    var a = $('#direccioncli').val(); //nombre del campos
    var a = $('#webcli').val(); //nombre del campos
    if (a === "") {
        alert('campo vacio');
        $('#ruccli');
        $('#razonsocialcli');
        $('#telefonocli');
        $('#direccioncli');
        $('#webcli');
    } else {
        ambClientes(1);
    }
}

function ControlarCampoClientes(){  // Para que no se repita ruc
    var dato;
    var Clientes = $('#ruccli').val();
    // alert(Clientes);
    $('#miTablaClientes tr').each(function () {
        dato = $(this).find('td').eq(1).html();
        if (dato === Clientes) {
            alert('ESTE RUC YA EXISTE');
            $('#ruccli').val(null); //Vaciar Campos
            $('#ruccli').focus(); 
        } else {
        }
    });
}