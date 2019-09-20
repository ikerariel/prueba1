$(document).ready(function () {
    (function ($) {
        $('#filtrarUsuarios').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.buscarUsuarios tr').hide();
            $('.buscarUsuarios tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        });
    }(jQuery));
});
$(document).ready(function () {
    allUsuarios();
    nuevoListarEmpleados();
    nuevoListarSucursales();
    nuevoListarPerfiles();
    $(":text").val("");
});

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "codusuario": $('#idusuario').val(),
        "usunombre": $('#nombreusu').val(),
        "usuclave": $('#claveusu').val(),
        "codempleado": $('#idempleado').val(),
        "codsucursal": $('#idsucursal').val(),
        "codperfil": $('#idperfil').val()
    };
}

function  getUltimoCodigoUsuarios() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "Usuarioscontrol",
        url: "http://localhost:8084/TALLERCASAJC/Usuarioscontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#idusuario").val(resp);
            $("#nombreusu").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  getmostrarUsuariosFiltro() {
    // alert('Llega recuperarUsuariosporID ');
    crearJSON(6);
    $.ajax({
        // url: "Usuarioscontrol",
        url: "http://localhost:8084/TALLERCASAJC/Usuarioscontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#nombreusu").val(value.nombre);
                $("#claveusu").val(value.usu_clave);
                //  para tirar
                $('#idempleado> option[value=' + value.id_empleado + ']').attr('selected', 'selected');
                $('#idsucursal> option[value=' + value.id_sucursal + ']').attr('selected', 'selected');
                $('#idperfil> option[value=' + value.id_perfil + ']').attr('selected', 'selected');
                $("#nombreusu").focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}


function  ambUsuarios(id) {
    crearJSON(id);
    $.ajax({
        // url: "Usuarioscontrol",
        url: "http://localhost:8084/TALLERCASAJC/Usuarioscontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function () {
            alert('Registro Realizado Correctamente...!!!');
            location.reload();
            //allUsuarios();
        },
        error: function () {
            alert('Error Durante la Insercion del Registro...!!!');
        }
    });
}

function recuperar() {
    $('#miTablaUsuarios tr').click(function () {
        $('#idusuario').val($(this).find("td").eq(0).html());
        $('#nombreusu').val($(this).find("td").eq(1).html());
        $('#claveusu').val($(this).find("td").eq(2).html());
        $('#idempleado').val($(this).find("td").eq(3).html());
        $('#idsucursal').val($(this).find("td").eq(4).html());
        $('#idperfil').val($(this).find("td").eq(5).html());
    });
}

function allUsuarios() {
//alert("Llega allEmpleados");
    crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Usuarioscontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            //alert(resp);
            // var jparse=JSON.parse(resp);
            // alert(jparse);
            $.each(resp, function (indice, value) {
                $("#miTablaUsuarios").append($("<tr>").append($(
                        "<td>" + value.id_usuario + "</td>" +
                        "<td>" + value.usu_nombre + "</td>" +
                        "<td>" + value.usu_clave + "</td>" +
                        "<td>" + value.nombre + "  " + value.apellido + "</td>" +
                        "<td>" + value.suc_descripcion + "</td>" +
                        "<td>" + value.perf_descripcion + "</td>")));
            });
        }
    });
}

function nuevoListarEmpleados() {
    crearJSON(7);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Usuarioscontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (data) {
            $.each(data, function (indice, value) {
                 $("#idempleado").append("<option value= \"" + value.id_empleado + "\"> "+value.nombre+"  "+value.apellido +"</option>");
                 
            });
        }
    });
}

//    combo Sucursales 
    function nuevoListarSucursales() {
    crearJSON(8);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Usuarioscontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (data) {
            $.each(data, function (indice, value) {
                $("#idsucursal").append("<option value= \"" + value.id_sucursal + "\"> " + value.suc_descripcion + "</option>");
            });
        }
    });
}

//    combo Perfiles 
    function nuevoListarPerfiles() {
    crearJSON(9);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Usuarioscontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (data) {
            $.each(data, function (indice, value) {
                $("#idperfil").append("<option value= \"" + value.id_perfil + "\"> " + value.perf_descripcion + "</option>");
            });
        }
    });
}

function reportesUsuarios() {
    window.open("reportesUsuarios.jsp");
}
