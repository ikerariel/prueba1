$(document).ready(function () {
    (function ($) {
        $('#filtrarTiposTarjetas').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.buscarTiposTarjetas tr').hide();
            $('.buscarTiposTarjetas tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        });
    }(jQuery));
});
$(document).ready(function () {
    allTiposTarjetas();
    $(":text").val("");
});

//conecta con jsp "codtarjeta" naranja
//conecta con control "idtarjeta" naranja
function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "idtipotarjeta": $('#codtipotarjeta').val(),
        "tarjtipo": $('#tarjetipo').val()
        
    };
}

function  getUltimoCodigoTiposTarjetas() {
    crearJSON(5);
    //alert('Llega a getUltimoCodigoTarjetas');
    $.ajax({
        // url: "Tarjetascontrol",
        url: "http://localhost:8084/TALLERCASAJC/TiposTarjetasServlet",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#codtipotarjeta").val(resp);
            $("#tarjetipo").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  recuperarTiposTarjetasporID() {
   // alert('Llega recuperarTarjetasporID ');
    crearJSON(6);
    
    $.ajax({
        // url: "Tarjetascontrol",
        url: "http://localhost:8084/TALLERCASAJC/TiposTarjetasServlet",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#tarjetipo").val(value.tarj_tipo);
                $("#tarjetipo").val.focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}


function  ambTiposTarjetas(id) {
    crearJSON(id);
    $.ajax({
        // url: "Tarjetascontrol",
        url: "http://localhost:8084/TALLERCASAJC/TiposTarjetasServlet",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function () {
            alert('Registro Realizado Correctamente...!!!');
            location.reload();
            //allTarjetas();
        },
        error: function () {
            alert('Error Durante la Insercion del Registro...!!!');
        }
    });
}

function recuperar() {
    $('#miTablaTiposTarjetas tr').click(function () {
        $('#codtipotarjeta').val($(this).find("td").eq(0).html());
        $('#tarjetipo').val($(this).find("td").eq(1).html());
    });
}


function allTiposTarjetas() {
//alert("Llega allTarjetas");
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/TiposTarjetasServlet",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
              //alert(resp);
           // var jparse=JSON.parse(resp);
           // alert(jparse);
            $.each(resp, function (indice,value){
                $("#miTablaTiposTarjetas").append($("<tr>").append($("<td>"+value.id_tipotarjeta+"</td>"+
                                                        "<td>"+value.tarj_tipo+"</td>")));
                
            });
        }
    });
}


