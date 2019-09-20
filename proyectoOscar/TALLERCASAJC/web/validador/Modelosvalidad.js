/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    (function ($) {
        $('#filtrarModelos').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.buscarModelos tr').hide();
            $('.buscarModelos tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        });
    }(jQuery));
});
$(document).ready(function () {
    allModelos();
    $(":text").val("");
});

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "idmodelo": $('#codmodelo').val(),
        "descrimodelo": $('#descrmodelo').val()
        
    };
}

function  getUltimoCodigo() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "Marcascontrol",
        url: "http://localhost:8084/TALLERCASAJC/ModelosServlet",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#codmodelo").val(resp);
            $("#descrmodelo").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  recuperarModelosporID() {
   // alert('Llega recuperarMarcasporID ');
    crearJSON(6);
    
    $.ajax({
        // url: "Marcascontrol",
        url: "http://localhost:8084/TALLERCASAJC/ModelosServlet",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#descrmodelo").val(value.model_descri);
                $("#descrmodelo").val.focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}


function  ambModelos(id) {
    crearJSON(id);
    $.ajax({
        // url: "Marcascontrol",
        url: "http://localhost:8084/TALLERCASAJC/ModelosServlet",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function () {
            alert('Registro Realizado Correctamente...!!!');
            location.reload();
            //allCiudades();
        },
        error: function () {
            alert('Error Durante la Insercion del Registro...!!!');
        }
    });
}

function recuperar() {
    $('#miTablaModelos tr').click(function () {
        $('#codmodelo').val($(this).find("td").eq(0).html());
        $('#descrmodelo').val($(this).find("td").eq(1).html());
    });
}

//function modificar() {
//    $('#miTabla tr').click(function () {
//        $('#cod_caja').val($(this).find("td").eq(0).html());
//        $('#descr_caja').val($(this).find("td").eq(1).html());
//    });
//}

function allModelos() {
//alert("Llega allMarcas");
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/ModelosServlet",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
              //alert(resp);
           // var jparse=JSON.parse(resp);
           // alert(jparse);
            $.each(resp, function (indice,value){
                $("#miTablaModelos").append($("<tr>").append($("<td>"+value.id_modelo+"</td>"+
                                                        "<td>"+value.model_descri+"</td>")));
                
            });
          
            //$('#miTableHtml').html(resp);
        }
    });
}

function reportesModelos() {
    window.open("reportesModelos.jsp");
}