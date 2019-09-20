/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    (function ($) {
        $('#filtrarTiposPagos').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.buscarTiposPagos tr').hide();
            $('.buscarTiposPagos tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        });
    }(jQuery));
});
$(document).ready(function () {
    allTiposPagos();
    $(":text").val("");
});

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "idtippag": $('#codtippag').val(),
        "descr": $('#descri').val()
        
    };
}

function  getUltimoCodigoTiposPagos() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "TiposPagosServlet",
        url: "http://localhost:8084/TALLERCASAJC/TiposPagosServlet",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#codtippag").val(resp);
            $("#descri").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  recuperarTiposPagosporID() {
   // alert('Llega recuperarTiposPagosporID ');
    crearJSON(6);
    
    $.ajax({
        // url: "TiposPagosServlet",
        url: "http://localhost:8084/TALLERCASAJC/TiposPagosServlet",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#descri").val(value.descripcion);
                $("#descri").val.focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}


function  ambTiposPagos(id) {
    crearJSON(id);
    $.ajax({
        // url: "TiposPagosServlet",
        url: "http://localhost:8084/TALLERCASAJC/TiposPagosServlet",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function () {
            alert('Registro Realizado Correctamente...!!!');
            location.reload();
            //allTiposPagos();
        },
        error: function () {
            alert('Error Durante la Insercion del Registro...!!!');
        }
    });
}

function recuperar() {
    $('#miTablaTiposPagos tr').click(function () {
        $('#codtippag').val($(this).find("td").eq(0).html());
        $('#descri').val($(this).find("td").eq(1).html());
    });
}

//function modificar() {
//    $('#miTabla tr').click(function () {
//        $('#cod_caja').val($(this).find("td").eq(0).html());
//        $('#descr_caja').val($(this).find("td").eq(1).html());
//    });
//}

function allTiposPagos() {
//alert("Llega allTiposPagos");
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/TiposPagosServlet",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
              //alert(resp);
           // var jparse=JSON.parse(resp);
           // alert(jparse);
            $.each(resp, function (indice,value){
                $("#miTablaTiposPagos").append($("<tr>").append($("<td>"+value.idtipopag+"</td>"+
                                                        "<td>"+value.descripcion+"</td>")));
                
            });
          
            //$('#miTableHtml').html(resp);
        }
    });
}

function reportesTiposPagos() {
    window.open("reportesTiposPagos.jsp");
}
