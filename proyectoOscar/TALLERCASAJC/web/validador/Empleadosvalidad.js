$(document).ready(function () {
    (function ($) {
        $('#filtrarEmpleados').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.buscarEmpleados tr').hide();
            $('.buscarEmpleados tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        });
    }(jQuery));
});
$(document).ready(function () {
    allEmpleados();
    nuevoListarBarrios();
    nuevoListarCiudades();
    $(":text").val("");
});

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "codempleado": $('#idempleado').val(),
        "empnombre": $('#nombreemple').val(),
        "empapellido": $('#apellidoemple').val(),
        "empci": $('#ciemple').val(),
        "emptel": $('#telemple').val(),
        "empdireccion": $('#direccionemple').val(),
        "codbarrio": $('#idbarrio').val(),
        "codciudad": $('#idciudad').val()
    };
}

function  getUltimoCodigoEmpleados() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "Empleadoscontrol",
        url: "http://localhost:8084/TALLERCASAJC/Empleadoscontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#idempleado").val(resp);
            $("#nombreemple").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  getmostrarEmpleadosFiltro() {
    // alert('Llega recuperarEmpleadosporID ');
    crearJSON(6);

    $.ajax({
        // url: "Empleadoscontrol",
        url: "http://localhost:8084/TALLERCASAJC/Empleadoscontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#nombreemple").val(value.nombre);
                //  para tirar
                $('#idbarrio> option[value=' + value.id_barrio + ']').attr('selected', 'selected');
                $('#idciudad> option[value=' + value.id_ciudad + ']').attr('selected', 'selected');
                $("#nombreemple").focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}


function  ambEmpleados(id) {
    crearJSON(id);
    $.ajax({
        // url: "Empleadoscontrol",
        url: "http://localhost:8084/TALLERCASAJC/Empleadoscontrol",
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
    $('#miTablaEmpleados tr').click(function () {
        $('#idempleado').val($(this).find("td").eq(0).html());
        $('#nombreemple').val($(this).find("td").eq(1).html());
        $('#apellidoemple').val($(this).find("td").eq(2).html());
        $('#ciemple').val($(this).find("td").eq(3).html());
        $('#telemple').val($(this).find("td").eq(4).html());
        $('#direccionemple').val($(this).find("td").eq(5).html());
        $('#idbarrio').val($(this).find("td").eq(6).html());
        $('#idciudad').val($(this).find("td").eq(7).html());
    });

}

function allEmpleados() {
//alert("Llega allEmpleados");
    crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Empleadoscontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            //alert(resp);
            // var jparse=JSON.parse(resp);
            // alert(jparse);
            $.each(resp, function (indice, value) {
                $("#miTablaEmpleados").append($("<tr>").append($(
                        "<td>" + value.id_empleado + "</td>" +
                        "<td>" + value.nombre + "</td>" +
                        "<td>" + value.apellido + "</td>" +
                        "<td>" + value.ci + "</td>" +
                        "<td>" + value.tel + "</td>" +
                        "<td>" + value.direccion + "</td>" +
                        "<td>" + value.barr_descripcion + "</td>" +
                        "<td>" + value.ciu_descripcion + "</td>")));
            });
        }
    });
}

function nuevoListarBarrios() {
    crearJSON(7);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Empleadoscontrol",
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
        url: "http://localhost:8084/TALLERCASAJC/Empleadoscontrol",
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

function campovacioEmpleados() { //Para verificar campos vacio
    var a = $('#nombreemple').val(); //nombre del campos
    var a = $('#apellidoemple').val(); //nombre del campos
    var a = $('#ciemple').val(); //nombre del campos
    var a = $('#telemple').val(); //nombre del campos
    var a = $('#direccionemple').val(); //nombre del campos
    if (a === "") {
        alert('campo vacio');
        $('#nombreemple');
        $('#apellidoemple');
        $('#ciemple');
        $('#telemple');
        $('#direccionemple');
    } else {
        ambEmpleados(1);
    }
}
   
function ControlarCampoEmpleados(){  // Para que no se repita CI
    var dato;
    var Empleados = $('#ciemple').val();
    // alert(Empleados);
    $('#miTablaEmpleados tr').each(function () {
        dato = $(this).find('td').eq(3).html();
        if (dato === Empleados) {
            alert('ESTE CI YA EXISTE');
            $('#ciemple').val(null); //Vaciar Campos
            $('#ciemple').focus(); 
        } else {
        }
    });
}

function reportesEmpleados() {
    window.open("reportesEmpleados.jsp");
}