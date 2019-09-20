$(document).ready(function () {
    opcionesRecepcion();
    getSrecepciones();


});

function ver() {
    $('#mitabladetallepresupuesto').find('tbody').find('tr').each(function () {
        alert($(this).find("td").eq(5).html());
    });
}

function opcionesRecepcion() {
    fechaac();
    traerequipos();
    $('#btnNuevoRecepcion').click(function () {
        $('#clinteRecepcion').val(null);
        $('#clinteNombreRecepcion').val(null);
        $('#v_articulosRecepcion').val(null);
        $('#descriparticuloRecepcion').val(null);
        $('#cantArtRecepcion').val(null);
        $('#mitabladetallerecepcion').find('tbody').find('tr').empty();
        $('#btnguardarRecepcion').show();
        $('#btntmodificarRecepcion').hide();
        $('#ventanaRecepcion').modal('show');
        var nCell = $('#mitablaRecepcion tr').length - 1;
        if (parseInt(nCell) < 1) {
            $('#codigoRecepcion').val("1");
        } else {
            var num;
            $('#mitablaRecepcion').each(function () {
                num = parseInt($(this).find("td").eq(0).html());
            });
            $('#codigoRecepcion').val(parseInt(num) + 1);
        }



    });

    $('#clinteRecepcion').keyup(function () {
        numeroDecimal('clinteRecepcion');
    });
    $('#telefonoModalRecepcion').keyup(function () {
        verificarcampoentero('telefonoModalRecepcion');
    });
    $('#clinteRecepcion').blur(function () {
        traercliente();
    });
    $('#tbnsavecliente').click(function () {
        saveCliente();
    });
    $('#btnguardarRecepcion').click(function () {

        insertarRecepcion(1, 1, 1);
    });
    $('#btntmodificarRecepcion').click(function () {
        insertarRecepcion(1, 2, 2);
    });
    $('#btnclose').click(function () {
        location.reload();
    });
    $('#mitablaRecepcion').click(function () {
        seleccionSrecepcion();
    });
    $('#btnConfirmarRecepcion').click(function () {
        actualizarEstado(1);
    });
    $('#btnRevertirRecepcion').click(function () {
        actualizarEstado(3);
    });
    $('#filtroRecepcion').keyup(function () {
        buscardortabla('mitablaRecepcion', 'filtroRecepcion');
    });
    $('#clinteRecepcion').blur(function () {
        calcularDV();
    });
    $('#btnInformeRecepcion').click(function () {
        if($('#nroRecepcion').val() === ""){
            alert('Debes seleccionar un registro.');
        }else{
           var v = $('#nroRecepcion').val();
            var valor = 2;
        window.open(`reportesgenericos.jsp?id_recepcion=${v}&vCodigo=${valor}`, "_blank");
        }
    
        
    });


}

function fechaac() {
    var fv = new Date();
    $('#fechaRecepcion').val(fv.getDate() + "/" + (fv.getMonth() + 1) + "/" + fv.getFullYear());
}

function traercliente() {
    jsonc = {
        "opcion": 2,
        "ci_v": $('#clinteRecepcion').val().replace(/\./g, '')
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
        type: 'POST',
        data: jsonc,
        cache: false,
        success: function (data) {
            if (JSON.stringify(data) != '[]') {
                $.each(data, function (indice, valor) {
                    $('#idclienteRecepcion').val(valor.idgenerico);
                    $('#clinteNombreRecepcion').val(valor.descripgenerico);
                    $('#clinteRecepcion').val(valor.cedula);
                });

            } else {
                var consulta = confirm('Cliente no Registrado, desea agregar ?');
                if (consulta) {
                    $('#viewClienteRecepcion').modal('show');
                    $('#clienteModalRecepcion').val($('#clinteRecepcion').val());
                    $('#clienteNombreModal').focus();
                    $('#clienteNombreModal').val(null);
                    $('#telefonoModalRecepcion').val(null);
                    $('#direccioneModal').val(null);
                }
            }

        },
        error: function () {

        }

    });
}
function saveCliente() {
    var nombre = $('#clienteNombreModal').val();
    var telefono = $('#telefonoModalRecepcion').val();
    var direccion = $('#direccioneModal').val();
    if (nombre === "" || telefono === "" || direccion === "") {
        alert('Algunos datos no fueron cargados correctamente..');
    } else {
        cliente = {
            "opcion": 10,
            "ruc_cliente": $('#clinteRecepcion').val().replace(/\./g, ''),
            "razonsocial_cliente": $('#clienteNombreModal').val(),
            "telefon_cliente": $('#telefonoModalRecepcion').val(),
            "direccion_cliente": $('#direccioneModal').val(),
            "cv_cliente": $('#clienteCV').val()
        };
        $.ajax({
            url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
            type: 'POST',
            data: cliente,
            cache: false,
            dataType: 'text',
            success: function () {
                alert('Cliente Guardado');
                $('#viewClienteRecepcion').modal('hide');
                traercliente();
            },
            error: function () {

            }

        });

    }
}


function insertarRecepcion(op, caso, dtalle) {

    jsonSrecpcion = {
        'opcion': op,
        'sValor': caso,
        'sCliente': $('#idclienteRecepcion').val(),
        'sUsuario': $('#idusersession_v').val(),
        'sObserv': $('#obsRecepcion').val(),
        'sCodrecp': $('#codigoRecepcion').val()
    };
    $.ajax({
        url: "/TALLERCASAJC/sRecepcionSERVLET",
        type: 'POST',
        data: jsonSrecpcion,
        cache: false,
        dataType: 'text',
        success: function () {
            deletejson = {
                'opcion': 3,
                'sCodrecp': $('#codigoRecepcion').val()
            };
            $.ajax({
                url: "/TALLERCASAJC/sRecepcionSERVLET",
                type: 'POST',
                data: deletejson,
                cache: false,
                dataType: 'text',
                success: function (resp) {
                    setTimeout(function () {
                        $('#mitabladetallerecepcion').find('tbody').find('tr').each(function () {
                            jsonDetaller = {
                                'opcion': 2,
                                'sNrorecpe': $('#codigoRecepcion').val(),
                                'opDetalle': dtalle,
                                'sCodartic': $(this).find("td").eq(0).html(),
                                'sCant': $(this).find("td").eq(2).html()
                            };
                            $.ajax({
                                url: "/TALLERCASAJC/sRecepcionSERVLET",
                                type: 'POST',
                                data: jsonDetaller,
                                cache: false,
                                dataType: 'text',
                                success: function (resp) {
                                    $('#ventanaRecepcion').modal('hide');
                                    location.reload();
                                }
                            });
                        });
                    }, 1200);

                }
            });

        }

    });


}






function traerequipos() {
    equipos = {
        "opcion": 20,
        "codDepo": $('#coddeposito_v').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
        type: 'POST',
        data: equipos,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#listaequipos").append("<option value= \"" + value.id_articulo + "\"> " + value.art_descripcion + "</option>");

            });

        }
    });
}

function getequipos() {
    art = {
        "opcion": 19,
        "codArticulo": $('#v_articulosRecepcion').val(),
        "coddepos": $('#coddeposito_v').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
        type: 'POST',
        data: art,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $('#descriparticuloRecepcion').val(value.art_descripcion);
                $('#cantArtRecepcion').val(1);
           
                $('#cantArtRecepcion').focus();

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



function cargaGrilla() {
    var ban = false;
    if ($('#v_articulosRecepcion').val() === "") {
        alert('DEBES INGRESAR UN ARTICULO');
    } else {
        var cod = $('#v_articulosRecepcion').val();
        var codigo;
        $('#mitabladetallerecepcion').find('tbody').find('tr').each(function () {
            codigo = $(this).find("td").eq(0).html();
            if (cod === codigo) {
                var sms = confirm('Articulo cargado, desea sustituirlo ??');
                if (sms === true) {
                    $(this).closest("tr").remove();
                    ban = true;
                    vCargarGrilla();
                } else {
                    ban = true;
                }

            } else {

            }

        });
        if (ban === false) {
            vCargarGrilla();
        }






    }

}
var tindex = 0;
function vCargarGrilla() {
    //idmaterial
    var v_codmaterial = $('#v_articulosRecepcion').val();
    var v_descripcion = $('#descriparticuloRecepcion').val();
    var v_cant = $('#cantArtRecepcion').val();

    $('#mitabladetallerecepcion').append("<tr id=\'prod" + tindex + "\'>\
            <td>" + v_codmaterial + "</td>\n\
            <td>" + v_descripcion + "</td>\n\
            <td>" + v_cant + "</td>\n\
            <td><img onclick=\"$(\'#prod" + tindex + "\').remove()\n\
            \" src='Recursos/img/delete.png' width=14 height=14/></td></tr>");

    $('#v_articulosRecepcion').val(null);
    $('#v_articulosRecepcion').focus;
    $('#cantArtRecepcion').val(null);
    $('#descriparticuloRecepcion').val(null);
}


var idx = 0;
function getSrecepciones() {
    $('#mitablaRecepcion').find('tbody').find('tr').empty();
    sRecepcionesjosn = {
        'opcion': 4
    };
    $.ajax({
        url: "/TALLERCASAJC/sRecepcionSERVLET",
        type: 'POST',
        data: sRecepcionesjosn,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, valor) {
                var id = valor.id_estado;
                var color;
                if (parseInt(id) === 3) {
                    color = '#d9edf7';
                    btn = "<a class='btn btn-md btn-danger glyphicon glyphicon-remove' title='Eliminar Registro' onclick=\"$(\'#prod" + idx + "\');delteRecepcion()\"></a>";
                    vbtn = "<a class='btn btn-md btn-success glyphicon glyphicon-edit' title='Modificar Registro' onclick=\"$(\'#prod" + idx + "\');updateSrecepcion()\"></a>";
//                    print = "<a class='btn btn-md btn-info glyphicon glyphicon-print' title='Visualizar Reporte' onclick=\"$(\'#prod" + idx + "\')reporteRecepcion();\"></a>";
                } else if (parseInt(id) === 1) {
                    color = '#acffac';
                    btn = "<a disabled='' class='btn btn-md btn-danger glyphicon glyphicon-remove' title='Eliminar Registro'></a>";
                    vbtn = "<a disabled='' class='btn btn-md btn-success glyphicon glyphicon-edit' title='Modificar Registro' ></a>";
//                    print = "<a class='btn btn-md btn-info glyphicon glyphicon-print' title='Visualizar Reporte' onclick=\"$(\'#prod" + idx + "\');reporteRecepcion()\"></a>";
                }
                $("#mitablaRecepcion").append($("<tr>").append($(
                        "<td>" + valor.id_recepcion + "</td>" +
                        "<td>" + valor.fecha + "</td>" +
                        "<td>" + valor.cliente + "</td>" +
                        "<td bgcolor=" + color + ">" + valor.estado + "</td>" +
                        "<td>" + valor.usuario + "</td>" +
                        "<td style='text-align: center'> " + vbtn + "  " + btn + "</td>")));
            });


        }

    });
}

function updateSrecepcion() {
    var ci = 0;
    $('#mitablaRecepcion tr').click(function () {
        ci = parseInt($(this).find("td").eq(0).html());
        openView(ci);
    });
    function openView(vCod) {
        $('#btnguardarRecepcion').hide();
        $('#btntmodificarRecepcion').show();
        $('#ventanaRecepcion').modal('show');
        $('#mitabladetallerecepcion').find('tbody').find('tr').empty();
        setTimeout(function () {
            getDetalleRecepcion(vCod);
        }, 1200);


    }
}
function delteRecepcion() {
    var ci = 0;
    var est;
    $('#mitablaRecepcion tr').click(function () {
        ci = parseInt($(this).find("td").eq(0).html());
        est = $(this).find("td").eq(3).html();
        ver(est, ci);
    });
    function ver(v1, v2) {
        var sms = confirm("Desea Anular el Registro ?");
        if (sms) {
            if (v1 === "PENDIENTE") {
                updateEstado(v2, 2);

            } else {
                alert('Registro CONFIRMADO, no se puede ANULAR.!!');
            }
        }else{
            location.reload();
        }
    }


}
function reporteRecepcion() {
    var nro = 0;
    var est;
    $('#mitablaRecepcion tr').click(function () {
        nro = parseInt($(this).find("td").eq(0).html());
        print(nro);
    });
    function print(v) {
        var valor = 2;
        window.open(`reportesgenericos.jsp?id_recepcion=${v}&vCodigo=${valor}`, "_blank");
    }


}


function seleccionSrecepcion() {
    $('#mitablaRecepcion tr').click(function () {
        $('#nroRecepcion').val($(this).find("td").eq(0).html());
        $('#estadoRecepcion').val($(this).find("td").eq(3).html()); /*Extrae el valor de la fila seleccionada y lo muestra en el campo
         //         * v_nroPlanilla*/
        var estado = $('#estadoRecepcion').val();
        if (estado === 'PENDIENTE') {
            document.getElementById('estadoRecepcion').style.color = "#000000";
            document.getElementById('estadoRecepcion').style.background = "PaleGoldenrod";
        }
        if (estado === 'CONFIRMADO') {
            document.getElementById('estadoRecepcion').style.background = "firebrick";
            document.getElementById('estadoRecepcion').style.color = "#ffffff";
        }
    });
}//----------------------------



var tindex = 0;
function getDetalleRecepcion(vValor) {

    $('#obsRecepcion').val(null);
    $('#v_articulosRecepcion').val(null);
    $('#descriparticuloRecepcion').val(null);
    $('#cantArtRecepcion').val(null);
    detallejsonR = {
        'opcion': 5,
        'sNroRecepcion': vValor
    };
    $.ajax({
        url: "/TALLERCASAJC/sRecepcionSERVLET",
        type: 'POST',
        data: detallejsonR,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, valor) {
                $('#codigoRecepcion').val(valor.id_recepcion);
                $('#fechaRecepcion').val(valor.fecha);
                $('#clinteRecepcion').val(valor.ruc);
                $('#idclienteRecepcion').val(valor.id_cliente);
                traercliente();
                $('#clinteNombreRecepcion').val(valor.cliente);
                $('#obsRecepcion').val(valor.observacion);

                $("#mitabladetallerecepcion").append($("<tr id=\'prod" + tindex + "\'>").append($(
                        "<td>" + valor.id_articulo + "</td>" +
                        "<td>" + valor.articulo + "</td>" +
                        "<td>" + valor.cantidad + "</td>" +
                        "<td><img onclick=\"$(\'#prod" + tindex + "\').remove()\n\
            \" src='Recursos/img/delete.png' width=14 height=14/></td>")));

            });

        }

    });
}

function actualizarEstado(estado) {



    if ($('#nroRecepcion').val() === "") {
        alert('Debes seleccionar un registo..');
    } else {
        var sms;
        var confir;
        var codRecep = $('#nroRecepcion').val();
        var codestado = $('#estadoRecepcion').val();
        if (estado === 1) {
            sms = "Desea Confirmar el Registro??";
            confir = "El Registro ya esta CONFIRMADO..!";
        }
        if (estado === 2) {
            sms = "Desea Anulaar el Registro??";
            confir = "El Registro ya esta CONFIRMADO..!";
        }
        if (estado === 3) {
            sms = "Desea Revertir el Registro??";
            confir = "El Registro aún sigue sin CONFIRMAR..!";
        }

        var v_sms = confirm(sms);
        if (v_sms === true) {
            if (estado === 1) {
                if (codestado === "CONFIRMADO") {
                    alert(confir);
                } else {
                    updateEstado(codRecep, estado);
                    alert('Registro  Confirmado');

                }

            }

            if (estado === 2) {
                if (codestado === "CONFIRMADO") {
                    alert(confir);
                } else {
                    updateEstado(codRecep, estado);
                    alert('Registro  Anulado');
                }
            }
            if (estado === 3) {
                if (codestado === "PENDIENTE") {
                    alert(confir);
                } else {
                    updateEstado(codRecep, estado);
                    alert('Registro  Revertido');
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
//            $('.modal-body').load("reportesgenericos.jsp?id_presucompra="+cod+"",function() {
//                $('#modalreporte').modal({show:true});

        window.open(`reportesgenericos.jsp?id_presucompra=${cod}`, "_blank");

//        });

    }

}
function updateEstado(codRecepcion, estado) {
    estadojson = {
        'opcion': 6,
        'sEstado': estado,
        'sRececp': codRecepcion
    };
    $.ajax({
        url: "/TALLERCASAJC/sRecepcionSERVLET",
        type: 'POST',
        data: estadojson,
        cache: false,
        dataType: 'text',
        success: function () {
            location.reload();
        }

    });


}
function calcularDV() {
    estadojson = {
        'opcion': 7,
        'nroci': $('#clinteRecepcion').val().replace(/\./g, ''),
        'basemax': 11
    };
    $.ajax({
        url: "/TALLERCASAJC/sRecepcionSERVLET",
        type: 'POST',
        data: estadojson,
        cache: false,
        dataType: 'text',
        success: function (resp) {
            $('#clienteCV').val(resp);
        }

    });


}