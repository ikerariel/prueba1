$(document).ready(function () {
    combos('comboproveedor', 3);
    combos('combotipomneda', 4);
    fechaac();
    $('#coddeposito').val($('#coddeposito_v').val());
    $('#depositodescrip').val($('#depos_v').val());
    getarticulos();
    getpresupuesto();


});

function ver() {
    $('#mitabladetallepresupuesto').find('tbody').find('tr').each(function () {
        alert($(this).find("td").eq(5).html());
    });
}
function fechaac() {
    var fv = new Date();
    $('#fechapresupuesto').val(fv.getDate() + "/" + (fv.getMonth() + 1) + "/" + fv.getFullYear());
}
function insertarpresupuesto(op, caso) {
    movimiento = {
        'opcion': op,
        'vcaso': caso,
        'v_codusuario': $('#idusersession_v').val(),
        'v_codproveedor': $('#comboproveedor').val(),
        'v_coddeposito': $('#coddeposito_v').val(),
        'v_codtipomoneda': $('#combotipomneda').val(),
        'v_codpresupuesto': $('#codigopresupuesto').val()
    };
    $.ajax({
        url: "/TALLERCASAJC/presupuestoControl",
        type: 'POST',
        data: movimiento,
        cache: false,
        dataType: 'text',
        success: function () {
            deletejson = {
                'opcion': 7,
                'v_codpresupuesto': $('#codigopresupuesto').val()
            };
            $.ajax({
                url: "/TALLERCASAJC/presupuestoControl",
                type: 'POST',
                data: deletejson,
                cache: false,
                dataType: 'text',
                success: function (resp) {
                    $('#mitabladetallepresupuesto').find('tbody').find('tr').each(function () {
                        movimiento = {
                            'opcion': 2,
                            'v_codarticulo': $(this).find("td").eq(0).html(),
                            'v_cantidad': $(this).find("td").eq(3).html(),
                            'v_preciounitario': $(this).find("td").eq(2).html().replace(/\./g, ''),
                            'v_codpresupuesto': $('#codigopresupuesto').val()

                        };
                        $.ajax({
                            url: "/TALLERCASAJC/presupuestoControl",
                            type: 'POST',
                            data: movimiento,
                            cache: false,
                            dataType: 'text',
                            success: function (resp) {
                                $('#ventanapresupuesto').modal('hide');
                                location.reload();
                            }
                        });
                    });
                }
            });

        }

    });


}
function modificarpresupuesto() {
    movimiento = {
        'opcion': 1,
        'v_codusuario': $('#idusersession_v').val(),
        'v_codproveedor': $('#comboproveedor').val(),
        'v_coddeposito': $('#coddeposito_v').val(),
        'v_codtipomoneda': $('#combotipomneda').val()
    };
    $.ajax({
        url: "/TALLERCASAJC/presupuestoControl",
        type: 'POST',
        data: movimiento,
        cache: false,
        dataType: 'text',
        success: function () {

            $('#mitabladetallepresupuesto').find('tbody').find('tr').each(function () {
                movimiento = {
                    'opcion': 2,
                    'v_codarticulo': $(this).find("td").eq(0).html(),
                    'v_cantidad': $(this).find("td").eq(3).html(),
                    'v_preciounitario': $(this).find("td").eq(2).html().replace(/\./g, '')

                };
                $.ajax({
                    url: "/TALLERCASAJC/presupuestoControl",
                    type: 'POST',
                    data: movimiento,
                    cache: false,
                    dataType: 'text',
                    success: function (resp) {
                        $('#ventanapresupuesto').modal('hide');
                        getpresupuesto();
                    }
                });
            });
        }

    });


}

function combos(cb, cod) {
    combosjson = {
        "opcion": cod
    };
    $.ajax({
        url: "/TALLERCASAJC/presupuestoControl",
        type: 'POST',
        data: combosjson,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $('#' + cb).append("<option value= \"" + value.cod + "\"> " + value.descrip + "</option>");

            });

        }
    });
}

function getarticulos() {
    articulos = {
        "opcion": 20,
        "codDepo": $('#coddeposito_v').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
        type: 'POST',
        data: articulos,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#lisart").append("<option value= \"" + value.id_articulo + "\"> " + value.art_descripcion + " Cantidad : " + value.cantidad + "</option>");

            });

        }
    });
}

function obarticulos() {
    art = {
        "opcion": 19,
        "codArticulo": $('#v_articulos').val(),
        "coddepos": $('#coddeposito_v').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
        type: 'POST',
        data: art,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {


                $('#descriparticulo').val(value.art_descripcion);
                $('#precioarticulo').val(value.preccompras);
                $('#cantarticulo').val(1);
                $('#cantarticulo').focus();
                valores('precioarticulo');


            });

        }
    });

}
function valores(numero) {
    var num = document.getElementById(numero).value.replace(/\./g, '');
    if (!isNaN(num)) {
        num = num.toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g, '$1.');
        num = num.split('').reverse().join('').replace(/^[\.]/, '');
        document.getElementById(numero).value = num;
    } else {
        alert('Solo se permiten numeros');
        document.getElementById(numero).value = document.getElementById(numero).value.replace(/[^\d\.]*/g, '');
    }

}



function verificarfila() {
    var ban = false;
    if ($('#v_articulos').val() === "") {
        alert('DEBES INGRESAR UN ARTICULO');
    } else {
        var cod = $('#v_articulos').val();
        var codigo;
        var vdetalle = 0;
        $('#mitabladetallepresupuesto').find('tbody').find('tr').each(function () {
            codigo = $(this).find("td").eq(0).html();
            if (cod === codigo) {
                vdetalle = $(this).find("td").eq(5).html();
                var sms = confirm('Articulo cargado, desea sustituirlo ??');
                if (sms === true) {
                    $(this).closest("tr").remove();
                    ban = true;
                    cargarfila(vdetalle);
                } else {
                    ban = true;
                }

            } else {

            }

        });
        if (ban === false) {
            cargarfila(vdetalle);
        }
    }



}
var tindex = 0;
function cargarfila(v) {
    //idmaterial
    var v_codmaterial = $('#v_articulos').val();
    var v_descripcion = $('#descriparticulo').val();
    var v_precio = $('#precioarticulo').val();
    var v_cant = $('#cantarticulo').val();
    var codD = v;

    subtotal = (v_precio.replace(/\./g, '')) * v_cant;
    tindex++;
    $('#mitabladetallepresupuesto').append("<tr id=\'prod" + tindex + "\'>\
            <td>" + v_codmaterial + "</td>\n\
            <td>" + v_descripcion + "</td>\n\
            <td>" + v_precio + "</td>\n\
            <td>" + v_cant + "</td>\n\
            <td>" + subtotal + "</td>\n\
            <td style=display:none>" + codD + "</td>\n\
            <td><button type=button title='Quitar el registro de la lista' \n\
                                 style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + tindex + "\').remove();updatemonto( " + subtotal + ", " + tindex + ")\">\n\
                                 <span class='glyphicon glyphicon-remove'></span></button></td></tr>");


    totales();
    $('#v_articulos').val(null);
    $('#v_articulos').focus;
    $('#descriparticulo').val(null);
    $('#precioarticulo').val(null);
    $('#cantarticulo').val(null);
}

function totales() {
    setTimeout(function () {
        $('#totalarticulos').val(null);
        monto = 0;
        acumu = 0;
        $('#mitabladetallepresupuesto').find('tbody').find('tr').each(function () {
            monto = parseInt($(this).find("td").eq(4).html());
            acumu = acumu + monto;
        });
        $('#totalarticulos').val(acumu);
        numeroDecimal('totalarticulos');
        tindex++;



    }, 1800);
// valores('totalarticulos');
}

function getpresupuesto() {
    $('#mitablapresupuesto').find('tbody').find('tr').empty();
    presupuestoJSON = {
        'opcion': 5
    };
    $.ajax({
        url: "/TALLERCASAJC/presupuestoControl",
        type: 'POST',
        data: presupuestoJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, valor) {
                $("#mitablapresupuesto").append($("<tr>").append($(
                        "<td>" + valor.id_presucompra + "</td>" +
                        "<td>" + valor.fecha + "</td>" +
                        "<td>" + valor.usuario + "</td>" +
                        "<td bgcolor='#d9edf7'>" + valor.proveedor + "</td>" +
                        "<td bgcolor='#d9edf7'>" + valor.estado + "</td>")));
            });


        }

    });
}


function buscarpresupuesto() {
    var tableReg = document.getElementById('mitablapresupuesto');
    var searchText = document.getElementById('filtropresupuesto').value.toLowerCase();
    var cellsOfRow = "";
    var found = false;
    var compareWith = "";
// Recorremos todas las filas con contenido de la tabla
    for (var i = 1; i < tableReg.rows.length; i++) {
        cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
        found = false;
// Recorremos todas las celdas
        for (var j = 0; j < cellsOfRow.length && !found; j++) {
            compareWith = cellsOfRow[j].innerHTML.toLowerCase();
// Buscamos el texto en el contenido de la celda
            if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1)) {
                found = true;
            }
        }
        if (found) {
            tableReg.rows[i].style.display = '';
        } else {
// si no ha encontrado ninguna coincidencia, esconde la fila de la tabla
            tableReg.rows[i].style.display = 'none';
        }
    }
}//--------------

function seleccionpresupuesto() {
    $('#mitablapresupuesto tr').click(function () {
        $('#nroprespuesto').val($(this).find("td").eq(0).html());
        $('#estadopresupuesto').val($(this).find("td").eq(4).html()); /*Extrae el valor de la fila seleccionada y lo muestra en el campo
         //         * v_nroPlanilla*/
        var estado = $('#estadopresupuesto').val();
        if (estado === 'PENDIENTE') {
            document.getElementById('estadopresupuesto').style.color = "#000000";
            document.getElementById('estadopresupuesto').style.background = "PaleGoldenrod";
        }
        if (estado === 'CONFIRMADO') {
            document.getElementById('estadopresupuesto').style.background = "firebrick";
            document.getElementById('estadopresupuesto').style.color = "#ffffff";
        }
    });
}//----------------------------

function abrirnuevopresupuesto() {
    $('#v_articulos').val(null);
    $('#descriparticulo').val(null);
    $('#precioarticulo').val(null);
    $('#cantarticulo').val(null);
    $('#totalarticulos').val(null);
    $('#codigoNropedido').val(null);
     $("#codigoNropedido").prop('disabled', false);
    $('#mitabladetallepresupuesto').find('tbody').find('tr').empty();
    $('#btnguardarpresupuesto').show();
    $('#btntmodificarpresupuesto').hide();
    $('#ventanapresupuesto').modal('show');
    var num;
    $('#mitablapresupuesto').each(function () {
        num = parseInt($(this).find("td").eq(0).html());

    });
    $('#codigopresupuesto').val(parseInt(num) + 1);

}


function getdetallepresupuesto() {
    $('#mitabladetallepresupuesto').find('tbody').find('tr').empty();
    $('#btnguardarpresupuesto').hide();
    $('#totalarticulos').val(null);
    $('#btntmodificarpresupuesto').show();
    $('#v_articulos').val(null);
    $('#descriparticulo').val(null);
    $('#precioarticulo').val(null);
    $('#cantarticulo').val(null);
    detallejson = {
        'opcion': 6,
        'nropresupuesto': $('#nroprespuesto').val()
    };
    $.ajax({
        url: "/TALLERCASAJC/presupuestoControl",
        type: 'POST',
        data: detallejson,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, valor) {
                $('#codigopresupuesto').val(valor.id_presucompra);
                $('#coddeposito').val(valor.id_deposito);
                $('#depositodescrip').val(valor.deposito);
                $('#codigoNropedido').val(valor.id_pedidocompra);
                 $("#codigoNropedido").prop('disabled', true);
                $('#comboproveedor option:selected').text(valor.proveedor);
                $('#combotipomneda option:selected').text(valor.moneda);
                sum = valor.preciounitario * valor.cantidad;
                tindex++;
                $('#mitabladetallepresupuesto').append("<tr id=\'prod" + tindex + "\'>\
            <td>" + valor.id_articulo + "</td>\n\
            <td>" + valor.articulo + "</td>\n\
            <td>" + valor.preciounitario + "</td>\n\
            <td>" + valor.cantidad + "</td>\n\
            <td>" + sum + "</td>\n\
            <td style=display:none>" + valor.iddetpresuompras + "</td>\n\
            <td><button type=button title='Quitar el registro de la lista' \n\
                                 style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + idx + "\').remove();updatemonto( " + sum + ", " + tindex + ")\">\n\
                                 <span class='glyphicon glyphicon-remove'></span></button></tr>");



            });

        }

    });
    totales();
}

function recuperarmodificar() {
    if ($('#estadopresupuesto').val() === "") {
        alert('Debes seleccionar un registro');
    } else if ($('#estadopresupuesto').val() === "CONFIRMADO") {
        alert('No se puede modificar el registro, el mismo se encuentra CONFIRMADO');
    }
    if ($('#estadopresupuesto').val() === "PENDIENTE") {
        $('#ventanapresupuesto').modal('show');
        $('#codigopresupuesto').val($('#nroprespuesto').val());
        getdetallepresupuesto();



    } else {

    }
}
function actualizarpresupuesto(estado) {
    if ($('#nroprespuesto').val() === "") {
        alert('Debes seleccionar un registo..');
    } else {
        var sms;
        var confir;
        var codpresupuesto = $('#nroprespuesto').val();
        var codestado = $('#estadopresupuesto').val();
        if (estado === 1) {
            sms = "Desea Confirmar el presupuesto??";
            confir = "El presupuesto ya esta confirmado..!";
        }
        if (estado === 2) {
            sms = "Desea Anulaar el presupuesto??";
            confir = "El presupuesto no se puede Anular, el mismo ya esta CONFIRMADO..!!";
        }
        if (estado === 3) {
            sms = "Desea Revertir la operación??";
            confir = "El presupuesto no se puede REVERTIR, el mismo esta PENDIENTE..!!";
        }

        var v_sms = confirm(sms);
        if (v_sms === true) {
            if (estado === 1) {
                if (codestado === "CONFIRMADO") {
                    alert(confir);
                } else {
                    actualizarestados(codpresupuesto, estado);
                }
            }
            if (estado === 2) {
                if (codestado === "CONFIRMADO") {
                    alert(confir);
                } else {
                    actualizarestados(codpresupuesto, estado);
                }
            }
            if (estado === 3) {
                if (codestado === "PENDIENTE") {
                    alert(confir);
                } else {
                    actualizarestados(codpresupuesto, estado);
                }
            }

        } else {

        }
    }

}

function informepresupuesto() {
    if ($('#nroprespuesto').val() === "") {
        alert('DEBES SELECCIONAR UN REGISTRO');
    } else {
        var cod = $('#nroprespuesto').val();
        var valor = 1;
        window.open(`reportesgenericos.jsp?id_presucompra=${cod}&vCodigo=${valor}`, "_blank");

//        });

    }

}
function actualizarestados(codpresupuesto, estado) {
    estadojson = {
        'opcion': 8,
        'v_estado': estado,
        'v_presupuesto': codpresupuesto
    };
    $.ajax({
        url: "/TALLERCASAJC/presupuestoControl",
        type: 'POST',
        data: estadojson,
        cache: false,
        dataType: 'text',
        success: function () {
            location.reload();
        }

    });


}

var idx = 0;
function recuperarDetallePedido() {
    $('#mitabladetallepresupuesto').find('tbody').find('tr').empty();
    datosDetalleJSON = {
        "opcion": 11,
        "nropedidov": $('#codigoNropedido').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/PedidosComprasServlet",
        type: 'POST',
        data: datosDetalleJSON,
        cache: false,
        success: function (resp) {
            if (JSON.stringify(resp) != '[]') {
                var v = JSON.stringify(resp);
                var vv = JSON.parse(v);
//                for(var i in vv){
                var estado = vv[0].id_estado;
//                }
                if (parseInt(estado) === 1) {
                    $.each(resp, function (indice, value) {
                        $("#mitabladetallepresupuesto").append($("<tr id=\'prod" + idx + "\'>").append($(
                                "<td>" + value.id_articulo + "</td>" +
                                "<td>" + value.art_descripcion + "</td>" +
                                "<td style='color:red'>" + "0" + "</td>" +
                                "<td>" + value.cantidad + "</td>" +
                                "<td style='color:red'>" + "0" + "</td>" +
                                "<td><button type=button title='Quitar el registro de la lista' \n\
                                 style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + idx + "\').remove()\">\n\
                                 <span class='glyphicon glyphicon-remove'></span></button></td>")));



                    });
                } else {
                    alert('Pedido Pendiente.!!');
                }


            } else {
                alert('Datos no encontrados..');
            }
        }
    });



}