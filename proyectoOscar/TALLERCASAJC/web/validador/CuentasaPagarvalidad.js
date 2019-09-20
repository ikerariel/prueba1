
$(document).ready(function () {
    (function ($) {
        $('#filtrarCuentasaPagar').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.buscarCuentasaPagar tr').hide();
            $('.buscarCuentasaPagar tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        });
    }(jQuery));
});
$(document).ready(function () {
    allCuentasaPagar();
    listarFacturasCompras();
    listarEstados();
    $(":text").val("");
});

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "codcuentpag": $('#idcuentpag').val(),
        "Fevencecuentpag": $('#cuentpagFevence').val(),
        "Saldocuentpag": $('#cuentpagSaldo').val(),
        "montocuentpag": $('#cuentpagmonto').val(),
        "codcompra": $('#idcompra').val(),
        "codestado": $('#idestado').val()
    };
}

function  getUltimoCodigoCuentasaPagar() {
    crearJSON(5);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/CuentasaPagarcontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#idcuentpag").val(resp);
            $("#cuentpagFevence").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  getmostrarCuentasaPagarFiltro() {
    crearJSON(6);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/CuentasaPagarcontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#cuentpagFevence").val(value.fecha_cuencob);
                $("#cuentpagSaldo").val(value.saldo_cuencob);
                $("#cuentpagmonto").val(value.monto_cuencob);

                //  para tirar
                $('#idcompra> option[value=' + value.id_compra + ']').attr('selected', 'selected');
                $('#idestado> option[value=' + value.id_estado + ']').attr('selected', 'selected');
                $("#cuentpagFevence").focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}

function  ambCuentasaPagar(id) {
    crearJSON(id);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/CuentasaPagarcontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function () {
            alert('Registro Insertado Correctamente...!!!');
            location.reload();
        },
        error: function () {
            alert('Error Durante la Insercion del Registro...!!!');
        }
    });
}

function recuperarCuentasaPagar() {
    $('#miTablaCuentasaPagar tr').click(function () {
        $('#idcuentpag').val($(this).find("td").eq(0).html());
        $('#cuentpagFevence').val($(this).find("td").eq(1).html());
        $('#cuentpagSaldo').val($(this).find("td").eq(2).html());
        $('#cuentpagmonto').val($(this).find("td").eq(3).html());
        $('#idcompra').val($(this).find("td").eq(4).html());
        $('#idestado').val($(this).find("td").eq(5).html());

    });
}

function allCuentasaPagar() {
    crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/CuentasaPagarcontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaCuentasaPagar").append($("<tr>").append($(
                        "<td>" + value.id_cuentpag + "</td>" +
                        "<td>" + value.fechavence + "</td>" +
                        "<td>" + value.saldo + "</td>" +
                        "<td>" + value.monto + "</td>" +
                        "<td>" + value.co_cantcuota + "</td>" +
                        "<td>" + value.est_descripcion + "</td>")));
//                        "<td><center><img src=\"../Recursos/img/update.png\" onclick=\"modificar()\" /></center></td>" +
//                        "<td><center><img src=\"../Recursos/img/delete.png\" onclick=\"eliminar()\"  /></center></td>")));
            });
        }
    });
}

function listarFacturasCompras() {
    crearJSON(7);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/CuentasaPagarcontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (data) {
            $.each(data, function (indice, value) {
                $("#idcompra").append("<option value= \"" + value.id_compra + "\"> " + value.co_cantcuota + "</option>");
            });
        }
    });
}

function listarEstados() {
    crearJSON(8);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/CuentasaPagarcontrol",
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

