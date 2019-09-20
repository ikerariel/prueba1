/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    getAjustes();
    AjustesProductos();
    getComboTipoAjustes();
    fechaactualL();
    getUltimoCodigoAjustes();


});
function fechaactualL() {

    var fecha = new Date();

    $('#fechaajuste').val(fecha.getDate() + "/" + (fecha.getMonth() + 1) + "/" + fecha.getFullYear());
}
tindex = 0;
function agregarFilasAjustes() {

    var v_codMaterialG = $('#codproductoAjustes').val();
    var v_cant = $('#CantidadAjustes').val();
    var v_descri = $('#descriproductov').val();

    $('#miTablaDetallesAjustes').append("<tr id=\'prod" + tindex + "\'>\
            <td>" + v_codMaterialG + "</td>\n\
            <td>" + v_descri + "</td>\n\
            <td>" + v_cant + "</td>\n\
                     <td><img onclick=\"$(\'#prod" + tindex + "\').remove()\" src='Recursos/img/delete.png' width=14 height=14/></td>\n\
            </tr>");

    //  para borrar campos y enviar focus o cursor en id
    $('#codproductoAjustes').val(null);
    $('#CantidadAjustes').val(null);
    $('#descriproductov').val(null);
    $('#codproductoAjustes').focus();

}

function getAjustes() {
    DatosAjustes = {
        'opcion': 1

    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/AjustesServlet",
        data: DatosAjustes,
        type: 'POST',
        success: function (resp) {
            console.log(resp);
            $.each(resp, function (indice, value) {
                $("#miTablaAjustes").append($("<tr>").append($(
                        "<td>" + value.id_ajuste + "</td>" +
                        "<td>" + value.fecha_ajustes + "</td>" +
                        "<td>" + value.est_descripcion + "</td>")));
            });
            return true;


        }
    });
}
function getUltimoCodigoAjustes() {
    Datoscodigo = {
        'opcion': 2

    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/AjustesServlet",
        data: Datoscodigo,
        type: 'POST',
        success: function (resp) {
            document.getElementById('codigoAjuste').value = resp;


        }
    });
}
function getComboTipoAjustes() {
    TipoAjustes = {
        'opcion': 3

    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/AjustesServlet",
        data: TipoAjustes,
        type: 'POST',
        success: function (resp) {

            $.each(resp, function (indice, value) {

                $("#TipoAjustes").append("<option value= \"" + value.id_tipajuste + "\"> " + value.descripcion + "</option>");


            });

        }
    });
}
function InsertarAjustes() {
    var res = confirm('desea guardar el ajuste');
    if (res) {
        TipoAjustes = {
            'opcion': 4,
            'codsucursal': 1,
            'coddeposito': 1,
            'codusuario': 1,
            'observa': document.getElementById('observAjustes').value,
            'codtipoajuste': document.getElementById('TipoAjustes').value

        };
        $.ajax({
            url: "http://localhost:8084/TALLERCASAJC/AjustesServlet",
            data: TipoAjustes,
            type: 'POST',
            success: function () {
            }
        });
        setTimeout(function () {
            InsertarDetallesAjustes();
        }, 2000);

    } else {

    }


}
function InsertarDetallesAjustes() {
    $('#miTablaDetallesAjustes').find('tbody').find('tr').each(function () {
        DetallesAjustes = {
            'opcion': 5,
            'codarticulo': $(this).find("td").eq(0).html(),
            'codajuste': document.getElementById('codigoAjuste').value,
            'Cantexis': $(this).find("td").eq(2).html()


        };
        $.ajax({  
            url: "http://localhost:8084/TALLERCASAJC/AjustesServlet",
            data: DetallesAjustes,
            type: 'POST',
            success: function () {
                alert('ajustes guardado');
                location.reload();

            }
        });
    });

}
function getAjustesDetTraer() {
    DatosObtenetDet = {
        'opcion': 6,
        'cod_ajustes': $('#v_nroAjustes').val()

    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/AjustesServlet",
        data: DatosObtenetDet,
        type: 'POST',
        success: function (resp) {

            $.each(resp, function (indice, value) {
                $("#miTablaDetallesAjustes").append($("<tr>").append($(
                        "<td>" + value.id_articulo + "</td>" +
                        "<td>" + value.art_descripcion + "</td>" +
                        "<td>" + value.cantexistencia + "</td>")));
                $('#fechaajuste').val(value.fecha_ajustes);
                $('#observAjustes').val(value.observacion);
                $('#codigoAjuste').val($('#v_nroAjustes').val());


            });
            return true;


        }
    });
}
function selectDetalleAjustes() {
    $('#miTablaAjustes tr').click(function () {
        $('#v_nroAjustes').val($(this).find("td").eq(0).html());
        $('#v_estadoAjustes').val($(this).find("td").eq(2).html());

    });
}

function recuperarDetAjustessss() {
    $('#btnGuardar').hide();
    $('#btnM').show();
    if ($('#v_nroAjustes').val() === "") {
        alert('Seleecione un sjuste para visualizar..');
    } else {
        if ($('#v_estadoAjustes').val() === 'Aprobado') {
            alert('La nota ya fue APROBADA..');
        } else {
            $('#ventanaajuste').modal('show');
            getAjustesDetTraer();


        }
    }
}

function AjustesProductos() {
    art = {
        "opcion": 9
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
        type: 'POST',
        data: art,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {

                $("#productoAjustes").append("<option value= \"" + value.id_articulo + "\"> " + value.art_descripcion + "</option>");


            });

        }
    });
}
//obtener productos con enter
function getProdAjustesDescripcion() {
    art = {
        "opcion": 19,
        "codArticulo": $('#codproductoAjustes').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
        type: 'POST',
        data: art,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $('#descriproductov').val(value.art_descripcion);
                $('#CantidadAjustes').focus();
                $('#CantidadAjustes').val(1);
            });

        }
    });
}

function AprobarAjustes(estado) {
    var res = confirm('desea aprobar el ajuste');
    if (res) {
        TipoAjustes = {
            'opcion': 7,
            'codestado': estado,
            'codajuste': $('#v_nroAjustes').val()

        };
        $.ajax({
            url: "http://localhost:8084/TALLERCASAJC/AjustesServlet",
            data: TipoAjustes,
            type: 'POST',
            success: function () {
            }
        });
        location.reload();

    } else {

    }


}
function getcod() {

    cod = {
        'opcion': 2

    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/AjustesServlet",
        data: cod,
        type: 'POST',
        success: function (resp) {
            $('#codigoAjuste').val(resp);

        }
    });



}

function abrirVentanas() {
    $('#btnM').hide();
    $('#btnGuardar').show();
    getcod();
    $('#miTablaDetallesAjustes').find('tbody').find('tr').empty();
}
