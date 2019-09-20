$(document).ready(function () {
    (function ($) {
        $('#filtrarDepositos').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.buscarDepositos tr').hide();
            $('.buscarDepositos tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        });
    }(jQuery));
});
$(document).ready(function () {
    allDepositos();
    nuevoListarSucursales();
    $(":text").val("");
});

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "coddeposito": $('#iddeposito').val(),
        "descripdeposito": $('#descrideposito').val(),
        "codsucursal": $('#idsucursal').val()
    };
}

function  getUltimoCodigoDepositos() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "Depositoscontrol",
        url: "http://localhost:8084/TALLERCASAJC/Depositoscontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#iddeposito").val(resp);
            $("#descrideposito").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  getmorstraDepositosFiltro() {
    // alert('Llega recuperarDepositosporID ');
    crearJSON(6);

    $.ajax({
        // url: "Depositoscontrol",
        url: "http://localhost:8084/TALLERCASAJC/Depositoscontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#descrideposito").val(value.dep_descripcion);
                $('#idsucursal> option[value=' + value.id_sucursal + ']').attr('selected', 'selected');
                $("#descrideposito").val.focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}


function  ambDepositos(id) {
    crearJSON(id);
    $.ajax({
        // url: "Depositoscontrol",
        url: "http://localhost:8084/TALLERCASAJC/Depositoscontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function () {
            alert('Registro Realizado Correctamente...!!!');
            location.reload();
            //allDepositos();
        },
        error: function () {
            alert('Error Durante la Insercion del Registro...!!!');
        }
    });
}

function recuperar() {
    $('#miTablaDepositos tr').click(function () {
        $('#iddeposito').val($(this).find("td").eq(0).html());
        $('#descrideposito').val($(this).find("td").eq(1).html());
        $('#idsucursal').val($(this).find("td").eq(2).html());
    });
}

function allDepositos() {
//alert("Llega allDepositos");
    crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Depositoscontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            //alert(resp);
            // var jparse=JSON.parse(resp);
            // alert(jparse);
            $.each(resp, function (indice, value) {
                $("#miTablaDepositos").append($("<tr>").append($(
                        "<td>" + value.id_deposito + "</td>" +
                        "<td>" + value.dep_descripcion + "</td>" +
                        "<td>" + value.suc_descripcion + "</td>")));

            });
        }
    });
}


//    combo Sucursales 
    function nuevoListarSucursales() {
    crearJSON(7);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Depositoscontrol",
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

function campovacioDepositos() { //Para verificar campos vacio
    var a = $('#descrideposito').val(); //nombre del campos
    if (a === "") {
        alert('campo vacio');
        $('#descrideposito');
    } else {
        ambDepositos(1);
    }
}

function ControlarCampoDepositos(){  // Para que no se repita nombre
    var dato;
    var Depositos = $('#descrideposito').val();
    // alert(ciudades);
    $('#miTablaDepositos tr').each(function () {
        dato = $(this).find('td').eq(1).html();
        if (dato === Depositos) {
            alert('ESTE DEPOSITO YA EXISTE');
            $('#descrideposito').val(null); //Vaciar Campos
            $('#descrideposito').focus(); 
        } else {
        }
    });
}

function reportesDepositos() {
    window.open("reportesDepositos.jsp");
}