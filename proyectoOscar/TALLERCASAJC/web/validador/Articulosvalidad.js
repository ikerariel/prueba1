$(document).ready(function () {
    (function ($) {
        $('#filtrarArticulos').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.buscarArticulos tr').hide();
            $('.buscarArticulos tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        });
    }(jQuery));
});
$(document).ready(function () {
    allArticulos();
    nuevoListarImpuestos();
    nuevoListarMarcas();
    $(":text").val("");
});

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "codarticulo": $('#idarticulo').val(),
        "descriart": $('#descriarticulo').val(),
        "comprasart": $('#artcompras').val(),
        "ventasart": $('#artventas').val().replace(/\./g, ''),
        "codimpuesto": $('#idimpuesto').val(),
        "codmarca": $('#idmarca').val(),
        "codigeneri": $('#generico').val()
    };
}

function  getUltimoCodigoArticulos() {
    crearJSON(5);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Articuloscontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#idarticulo").val(resp);
            $("#descriarticulo").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  getmostrarArticulosFiltro() {
    crearJSON(6);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Articuloscontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#descriarticulo").val(value.art_descripcion);
                $("#generico").val(value.codigenerico);
                $("#artcompras").val(value.preccompras);
                $("#artventas").val(value.precventas);
                //  para tirar
                $('#idimpuesto> option[value=' + value.id_impuesto + ']').attr('selected', 'selected');
                $('#idmarca> option[value=' + value.id_marca + ']').attr('selected', 'selected');
                $("#descriarticulo").focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}

function  ambArticulos(id) {
    crearJSON(id);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Articuloscontrol",
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

function recuperar() {
    $('#miTablaArticulos tr').click(function () {
        $('#idarticulo').val($(this).find("td").eq(0).html());
        $('#descriarticulo').val($(this).find("td").eq(1).html());
        $('#generico').val($(this).find("td").eq(2).html());
        $('#artcompras').val($(this).find("td").eq(3).html());
        $('#artventas').val($(this).find("td").eq(4).html());
        $('#idimpuesto').val($(this).find("td").eq(5).html());
        $('#idmarca').val($(this).find("td").eq(6).html());
       
    });
}

function allArticulos() {
    crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Articuloscontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaArticulos").append($("<tr>").append($(
                        "<td>" + value.id_articulo + "</td>" +
                        "<td>" + value.art_descripcion + "</td>" +
                        "<td>" + value.codigenerico + "</td>" +
                        "<td>" + value.preccompras + "</td>" +
                        "<td>" + value.precventas + "</td>" +
                        "<td>" + value.imp_descripcion + "</td>" +
                        "<td>" + value.mar_descripcion + "</td>")));
//                        "<td><center><img src=\"../Recursos/img/update.png\" onclick=\"modificar()\" /></center></td>" +
//                        "<td><center><img src=\"../Recursos/img/delete.png\" onclick=\"eliminar()\"  /></center></td>")));
            });
        }
    });
}

function nuevoListarImpuestos() {
    crearJSON(7);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Articuloscontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (data) {
            $.each(data, function (indice, value) {
                $("#idimpuesto").append("<option value= \"" + value.id_impuesto + "\"> " + value.imp_descripcion + "</option>");
            });
        }
    });
}

function nuevoListarMarcas() {
    crearJSON(8);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Articuloscontrol",
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
function reportesArticulos() {
    window.open("reportesArticulos.jsp");

}

function calcularprecioventa(){
    var costo = parseInt($("#artcompras").val());
    var precio = costo+(costo*0.30);
    $("#artventas").val(precio);
    numeroDecimal('artventas');
} 


//En el impup
//onkeyup="calcularprecioventa()"
