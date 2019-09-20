
$(document).ready(function () {

    cambioEstadoPedido();
    allPedidos();
    fechaactual();



});
//FUNCIONES SECUNDARIOS VALIDADACIONES CREADOS-----------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
function reportesPedido() {
    valor = $("#v_nropedido").val();
    window.open("reportesPedidoCompra.jsp?cod=" + valor + "", "_blank");
}

function fechaactual() {
    var fecha = new Date();
    $('#fechapedido').val(fecha.getDate() + "/" + (fecha.getMonth() + 1) + "/" + fecha.getFullYear());
}

function abrigrillArti() {
    if ($('#idmaterialGenerico').val() === "") {
        $('#grillaArtic').modal('show');
        $('#TablaArtic').find('tbody').find('tr').empty();
        AgregarArticulos();
    } else {
    }
}
function AgregarArticulos() {

    datos = {
        "opcion": 15
    };


    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/PedidosComprasServlet",
        type: 'POST',
        data: datos,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#TablaArtic").append($("<tr>").append($(
                        "<td>" + value.id_articulo + "</td>" +
                        "<td>" + value.art_descripcion + "</td>")));
            });
        }
    });
}
function seleccionArticulosS() {
    $('#TablaArtic tr').click(function () {
        $('#idmaterial').val($(this).find("td").eq(0).html());
        $('#idmaterialGenerico').val($(this).find("td").eq(0).html());
        $('#idcantidad').val(1);
        $('#iddescripcion').val($(this).find("td").eq(1).html());

        $('#idcantidad').focus();
        $('#grillaArtic').modal('hide');

    });
}
function buscarTablaArticulos() {
    var tableReg = document.getElementById('TablaArticulos');
    var searchText = document.getElementById('filtrarArticulos').value.toLowerCase();
    var cellsOfRow = "";
    var found = false;
    var compareWith = "";

// Recorremos todas las filas con contenido de la tabla
    for (var i = 1; i < tableReg.rows.length; i++)
    {
        cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
        found = false;
// Recorremos todas las celdas
        for (var j = 0; j < cellsOfRow.length && !found; j++)
        {
            compareWith = cellsOfRow[j].innerHTML.toLowerCase();
// Buscamos el texto en el contenido de la celda
            if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1))
            {
                found = true;
            }
        }
        if (found)
        {
            tableReg.rows[i].style.display = '';
        } else {
// si no ha encontrado ninguna coincidencia, esconde la
// fila de la tabla
            tableReg.rows[i].style.display = 'none';
        }
    }
}
function selecTablaArticulosDet() {
    $('#miTablaDetalleArticulos tr').click(function () {
        $('#idmaterial').val($(this).find("td").eq(0).html());
        $('#idmaterialGenerico').val($(this).find("td").eq(1).html());
        $('#iddescripcion').val($(this).find("td").eq(2).html());
        $('#idpreci').val($(this).find("td").eq(3).html());
        $('#idcantidad').val(3);
        $('#idmaterialGenerico').focus();
    });
}
function ValidacionSoloNumeros(input) {
    var num = input.value.replace(/\./g, '');
//    alert("estees" +num);
    if (!isNaN(num)) {
        num = num.toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g, '$1.');
        num = num.split('').reverse().join('').replace(/^[\.]/, '');
        input.value = num;
    } else {
        alert('Solo se permiten numeros');
        input.value = input.value.replace(/[^\d\.]*/g, '');
    }

}
function CargarArticulos() {
    var ban = false;
    if ($('#idmaterialGenerico').val() === "") {
        alert('DEBES INGRESAR UN ARTICULO');
    } else {
        var cod = $('#idmaterialGenerico').val();
        var codigo;
        $('#miTablaDetalleMercaderia').find('tbody').find('tr').each(function () {
            codigo = $(this).find("td").eq(0).html();
            if (cod === codigo) {
                var sms = confirm('Articulo cargado, desea sustituirlo ??');
                if (sms === true) {
                    $(this).closest("tr").remove();
                    ban = true;
                    agregarFilaArticulos();
                } else {
                    ban = true;
                }

            } else {

            }

        });
        if (ban === false) {
            agregarFilaArticulos();
        }
    }


}
function agregarFilaArticulos() {
    var subtotal = 0;
    var tindex = 0;
//idmaterial
    var v_codMaterialG = $('#idmaterialGenerico').val();
    var v_codmaterial = $('#idmaterial').val();
    var v_descripcion = $('#iddescripcion').val();
    var v_cant = $('#idcantidad').val();
    var v_precio = $('#idpreci').val();

    subtotal = v_precio * v_cant;

    $('#miTablaDetalleMercaderia').append("<tr id=\'prod" + tindex + "\'>\
            <td>" + v_codmaterial + "</td>\n\
            <td>" + v_descripcion + "</td>\n\
            <td>" + v_cant + "</td>\n\
            <td><button type=button title='Quitar el registro de la lista' \n\
            style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + tindex + "\').remove()\">\n\
            <span class='glyphicon glyphicon-remove'></span></button></a></td>\n\
            </tr>");

    $('#idmaterialGenerico').val(null);
    $('#iddescripcion').val(null);
    $('#idmaterialGenerico').focus;
    $('#idcantidad').val(null);
    $('#idpreci').val(null);

}
function seleccion() {
    $('#miTablaPedidos tr').click(function () {
        $('#v_nropedido').val($(this).find("td").eq(0).html());
        $('#v_estado').val($(this).find("td").eq(3).html());
        $('#v_obs').val($(this).find("td").eq(4).html()); /*Extrae el valor de la fila seleccionada y lo muestra en el campo
         //         * v_nroPlanilla*/
        var estado = $('#v_estado').val();
        if (estado === '"Pendiente"') {
            document.getElementById('v_estado').style.color = "#000000";
            document.getElementById('v_estado').style.background = "PaleGoldenrod";
        }
        if (estado === '"Aprobado"') {

            document.getElementById('v_estado').style.background = "firebrick";
            document.getElementById('v_estado').style.color = "#ffffff";
        }

    });
}
function buscadorTablaPedido() {
    var tableReg = document.getElementById('miTablaPedidos');
    var searchText = document.getElementById('filtrarPedido').value.toLowerCase();
    var cellsOfRow = "";
    var found = false;
    var compareWith = "";

// Recorremos todas las filas con contenido de la tabla
    for (var i = 1; i < tableReg.rows.length; i++)
    {
        cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
        found = false;
// Recorremos todas las celdas
        for (var j = 0; j < cellsOfRow.length && !found; j++)
        {
            compareWith = cellsOfRow[j].innerHTML.toLowerCase();
// Buscamos el texto en el contenido de la celda
            if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1))
            {
                found = true;
            }
        }
        if (found)
        {
            tableReg.rows[i].style.display = '';
        } else {
// si no ha encontrado ninguna coincidencia, esconde la
// fila de la tabla
            tableReg.rows[i].style.display = 'none';
        }
    }
}



//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------


//FUNCIONES DE TRANSACCIONES----------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "codigov": $('#codigo').val(),
        "fechav": $('#fechapedido').val(),
        "estadov": $('#estado').val(),
        "usuariov": $('#Usuario').val(),
        "observacionv": $('#observ').val()
    };
}

function getcodigo() {
    $('#btnGuardar').show();
    $('#btnmodificarpedido').hide();
    $("#observ").val(null);
    $("#usuario").val($('#usertext_v').val());
    crearJSON(1);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/PedidosComprasServlet",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            $("#codigo").val(resp);
            $("#observ").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function allPedidos() {
    crearJSON(9);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/PedidosComprasServlet",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaPedidos").append($("<tr>").append($(
                        "<td>" + value.id_pedidocompra + "</td>" +
                        "<td>" + value.pcompra_fecha + "</td>" +
                        "<td>" + value.usu_nombre + "</td>" +
                        "<td>" + value.est_descripcion + "</td>" +
                        "<td>" + value.observacion + "</td>")));

            });

        }
    });
}

function  InsertarPedidoCompra() {
    var dato = "";
    $('#miTablaDetalleMercaderia').find('tbody').find('tr').each(function () {
        dato = $(this).find("td").eq(0).html();
    });
    if (dato === "") {
        alert('No hay detalle que guardar..!');
        $("#idmaterialGenerico").focus();
    } else {
        if ($('#observ').val() === "") {
            alert('Debe ingresar todos los datos requeridos para la consulta..');
            $("#idmaterialGenerico").focus();
        } else {
            var opcion = confirm('Desea Guardar el Pedido.?');
            if (opcion === true) {
                datosCabeceraJSON = {
                    "opcion": 2,
                    "codUse": $('#idusersession_v').val(),
                    "observacionv": $('#observ').val()
                };
                $.ajax({
                    url: "http://localhost:8084/TALLERCASAJC/PedidosComprasServlet",
                    type: 'POST',
                    data: datosCabeceraJSON,
                    cache: false,
                    dataType: 'text',
                    success: function () {
                        setTimeout(function () {
                            DetalleArticulos();
                        }, 1100);



                    },
                    error: function () {
                    }
                });

            } else {

            }

        }
    }

}
function  updatecompra() {
    var dato = "";
    $('#miTablaDetalleMercaderia').find('tbody').find('tr').each(function () {
        dato = $(this).find("td").eq(0).html();
    });
    if (dato === "") {
        alert('No hay detalle que guardar..!');
        $("#idmaterialGenerico").focus();
    } else {
        if ($('#observ').val() === "") {
            alert('Debe ingresar todos los datos requeridos para la consulta..');
            $("#idmaterialGenerico").focus();
        } else {
            var opcion = confirm('Desea Guardar el Pedido.?');
            if (opcion === true) {
                datosCabeceraJSON = {
                    "opcion": 14,
                    "coduser": $('#idusersession_v').val(),
                    "codestado": 3,
                    "codobserv": $('#observ').val(),
                    "codpediod": $('#codigo').val()
                };
                $.ajax({
                    url: "http://localhost:8084/TALLERCASAJC/PedidosComprasServlet",
                    type: 'POST',
                    data: datosCabeceraJSON,
                    cache: false,
                    dataType: 'text',
                    success: function () {
                        deledet = {
                            "opcion": 16,
                            "nropedidov": $('#codigo').val()
                        };
                        $.ajax({
                            url: "http://localhost:8084/TALLERCASAJC/PedidosComprasServlet",
                            type: 'POST',
                            data: deledet,
                            cache: false,
                            dataType: 'text',
                            success: function () {
                                DetalleArticulos();

                            },
                            error: function () {
                            }
                        });


                    },
                    error: function () {
                    }
                });

            } else {

            }

        }
    }

}
function  DetalleArticulos() {

    setTimeout(function () {
        $('#miTablaDetalleMercaderia').find('tbody').find('tr').each(function () {
            datosDetalleJSON = {
                "opcion": 3,
                "codigoD": $('#codigo').val(),
                "idmercaV": $(this).find("td").eq(0).html(),
                "cantidadv": $(this).find("td").eq(2).html()
            };
            $.ajax({
                url: "http://localhost:8084/TALLERCASAJC/PedidosComprasServlet",
                type: 'POST',
                data: datosDetalleJSON,
                cache: false,
                dataType: 'text',
                success: function () {

                    location.reload();
                },
                error: function () {
                }
            });
        });
        alert("Pedido guardado correctamente.!!");
    }, 1200);


}
var idx = 0;
function recuperarDetalle() {
    $('#btnGuardar').hide();
    $('#btnmodificarpedido').show();
//    controlBotones();
    if ($('#v_nropedido').val() === "") {
        alert('Seleecione un pedido para visualizar..');
    } else {
        $('#ventanaPedido').modal('show');
        $('#miTablaDetArticulos').find('tbody').find('tr').empty();
        datosDetalleJSON = {
            "opcion": 11,
            "nropedidov": $('#v_nropedido').val()
        };
        $.ajax({
            url: "http://localhost:8084/TALLERCASAJC/PedidosComprasServlet",
            type: 'POST',
            data: datosDetalleJSON,
            cache: false,
            success: function (resp) {
                if (JSON.stringify(resp) != '[]') {
//                    alert(resp);
                    $.each(resp, function (indice, value) {
                        ///RECUPERA LA CABECERA/////////
                        $("#fechapedido").val(value.pcompra_fecha);
                        $("#estado").val(value.est_descripcion);
                        $("#usuario").val(value.usu_nombre);
                        $("#observ").val(value.observacion);

                        ///////BLOQUE LOS CAMPOS//////
                        $("#usuario").prop('disabled', true);
                        $("#observ").prop('disabled', false);
                        $("#idmaterialGenerico").prop('disabled', false);
                        $("#idmaterial").prop('disabled', true);
                        $("#idcantidad").prop('disabled', false);
                        subtotal = value.precio * value.cantidad;
///////////////////////////////////////////////////////////////////////
                        $("#miTablaDetalleMercaderia").append($("<tr id=\'prod" + idx + "\'>").append($(
                                "<td>" + value.id_articulo + "</td>" +
                                "<td>" + value.art_descripcion + "</td>" +
                                "<td>" + value.cantidad + "</td>" +
                                "<td><button type=button title='Quitar el registro de la lista' \n\
                                 style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + idx + "\').remove()\">\n\
                                 <span class='glyphicon glyphicon-remove'></span></button></td>")));

                    });
                    $('#codigo').val($('#v_nropedido').val());
                } else {
                    alert('Datos no encontrados..');
                    $("#nrosolicitud").focus();
                }
            }
        });


    }
}

////esta parte es para confirmar pendiente confirmado anuado
function confirmar() {
    if ($('#v_estado').val() === "") {
        alert('Seleccione un pedido.!');
    } else {
        if ($('#v_estado').val() === 'PENDIENTE') {
            var opcion = confirm('Desea confirmar el pedido.??');
            if (opcion === true) {

            }
        } else {
            if ($('#v_estado').val() === 'CONFIRMADO') {
                alert('El pedido ya fue confirmado..');
            }
        }
    }


}
function controlBotones() {
    v = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesPedido a', function () {
            v = ($(this).attr('id'));
            if (v === 'btnModificar' && $('#v_estado').val() === 'CONFIRMADO' || $('#v_estado').val() === 'ANULADO') {
//                $("#btnGuardar").attr("disabled", true);
                document.getElementById("btnGuardar").style.display = 'none';
            } else {
                document.getElementById("btnGuardar").style.display = '';
            }
        });

    });

}

function cambioEstadoPedido() {


    var btn = "";
    $(document).ready(function () {
        $('body').on('click', '#botonPedido a', function () {
            btn = ($(this).attr('id'));
            if (btn === 'btnAnular') {
                if ($('#v_estado').val() === "") {
                    alert('Seleccione un pedido.!');
                } else if ($('#v_estado').val() === '"PENDIENTE"' || $('#v_estado').val() === '"ANULADO"') {
                    alert('El pedido aún esta Pendiente de Confirmación o ya esta Anulada..');
                } else if ($('#v_estado').val() === 'CONFIRMADO') {
                    var opcion = confirm('Desea Anular el pedido.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 13,
                            "CDestado": 2,
                            "nroPd": $('#v_nropedido').val()
                        };
                        confirmarPedido();
                        alert('Pedido Anulado con éxito.!!');
                    }
                }
            } else if (btn === 'btnconfpedido') {

                if ($('#v_estado').val() === "") {
                    alert('Seleccione un pedido.!');
                } else if ($('#v_estado').val() === 'CONFIRMADO' || $('#v_estado').val() === 'ANULADO') {
                    alert('El pedido ya fué Confirmado o esta Anulada..');
                } else if ($('#v_estado').val() === 'PENDIENTE') {
                    var opcion = confirm('Desea Confirmar el pedido.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 13,
                            "CDestado": 1,
                            "nroPd": $('#v_nropedido').val()
                        };
                        confirmarPedido();
                        alert('Pedido Confirmado con éxito.!!');
                    }
                }
            } else if (btn === 'btnRevertir') {
                if ($('#v_estado').val() === "") {
                    alert('Seleccione un pedido.!');
                } else if ($('#v_estado').val() === '"PENDIENTE"' || $('#v_estado').val() === '"ANULADO"') {
                    alert('El pedido no se puede Revertir..');
                } else if ($('#v_estado').val() === 'CONFIRMADO') {
                    var opcion = confirm('Desea Revertir el pedido.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 13,
                            "CDestado": 3,
                            "nroPd": $('#v_nropedido').val()
                        };
                        confirmarPedido();
                        alert('El pedido ha vuelto a su estado de Origen.!!');
                    }
                }


            }
        });
    });
}
function confirmarPedido() {
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/PedidosComprasServlet",
        type: 'POST',
        data: datoJson,
        cache: false,
        dataType: 'text',
        success: function () {
            $('#miTablaPedidos').find('tbody').find('tr').empty();
            allPedidos();
        },
        error: function () {

        }
    });
}
//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------


function  grabararticulo() {
    json = {
        "opcion": 9,
        "descriart": $('#articulodescrip').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Articuloscontrol",
        data: json,
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


function  saveArticulo() {
    jsonArt = {
        "opcion": 17,
        "pArticulo": $('#articulodescripPedido').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/PedidosComprasServlet",
        data: jsonArt,
        type: 'POST',
        dataType: 'text',
        success: function () {
            $('#ventanaAgregarArticulos').modal('hide');
        },
        error: function () {

        }
    });
}