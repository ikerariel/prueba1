
$(document).ready(function () {
    (function ($) {
        $('#filtrarCaja').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.buscarCaja tr').hide();
            $('.buscarCaja tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        });
    }(jQuery));
});
$(document).ready(function () {
    allCaja();
    $(":text").val("");
});

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "idcaja": $('#codcaja').val(),
        "descricaja": $('#descrcaja').val()
        
    };
}

function  getUltimoCodigo() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/TALLERCASAJC/Cajascontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#codcaja").val(resp);
            $("#descrcaja").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  recuperarCajaporID() {
   // alert('Llega listarClienteSegunFiltro ');
    crearJSON(6);
    
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/TALLERCASAJC/Cajascontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#descrcaja").val(value.descripcion);
                $("#descrcaja").val.focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}


function  ambcaja(id) {
    crearJSON(id);
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/TALLERCASAJC/Cajascontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function () {
            alert('Registro Realizado Correctamente...!!!');
            location.reload();
            //allClientes();
        },
        error: function () {
            alert('Error Durante la Insercion del Registro...!!!');
        }
    });
}

function recuperar() {
    $('#miTablaCaja tr').click(function () {
        $('#codcaja').val($(this).find("td").eq(0).html());
        $('#descrcaja').val($(this).find("td").eq(1).html());
    });
}

//function modificar() {
//    $('#miTabla tr').click(function () {
//        $('#cod_caja').val($(this).find("td").eq(0).html());
//        $('#descr_caja').val($(this).find("td").eq(1).html());
//    });
//}

function allCaja() {
//alert("Llega allClientes");
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Cajascontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
              //alert(resp);
           // var jparse=JSON.parse(resp);
           // alert(jparse);
            $.each(resp, function (indice,value){
                $("#miTablaCaja").append($("<tr>").append($("<td>"+value.id_caja+"</td>"+
                                                        "<td>"+value.descripcion+"</td>")));
                
            });
          
            //$('#miTableHtml').html(resp);
        }
    });
}

function campovacioCajas() { //Para verificar campos vacio
    var a = $('#descrcaja').val(); //nombre del campos
    if (a === "") {
        alert('campo vacio');
        $('#descrcaja');
    } else {
        ambcaja(1);
    }
}

function ControlarCampoCajas(){  // Para que no se repita nombre
    var dato;
    var Cajas = $('#descrcaja').val();
    // alert(Cajas);
    $('#miTablaCaja tr').each(function () {
        dato = $(this).find('td').eq(1).html();
        if (dato === Cajas) {
            alert('ESTA CAJA YA EXISTE');
            $('#descrcaja').val(null); //Vaciar Campos
            $('#descrcaja').focus(); 
        } else {
        }
    });
}
function reportesCajas() {
    window.open("reportesCajas.jsp");

}
