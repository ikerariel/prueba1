/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    (function ($) {
        $('#filtrarTipoServicios').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.buscarTipoServicios tr').hide();
            $('.buscarTipoServicios tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        });
    }(jQuery));
});
$(document).ready(function () {
    allTipoServicios();
    $(":text").val("");
});

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "idtiposervicio": $('#codtiposervicio').val(),
        "descri": $('#descr').val()
        
    };
}

function  getUltimoCodigo() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "Marcascontrol",
        url: "http://localhost:8084/TALLERCASAJC/TipoServiciosServlet",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#codtiposervicio").val(resp);
            $("#descr").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  recuperarTipoServiciosporID() {
   // alert('Llega recuperarMarcasporID ');
    crearJSON(6);
    
    $.ajax({
        // url: "Marcascontrol",
        url: "http://localhost:8084/TALLERCASAJC/TipoServiciosServlet",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#descr").val(value.descripcion);
                $("#descr").val.focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}


function  ambTipoServicios(id) {
    crearJSON(id);
    $.ajax({
        // url: "Marcascontrol",
        url: "http://localhost:8084/TALLERCASAJC/TipoServiciosServlet",
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
    $('#miTablaTipoServicios tr').click(function () {
        $('#codtiposervicio').val($(this).find("td").eq(0).html());
        $('#descr').val($(this).find("td").eq(1).html());
    });
}

//function modificar() {
//    $('#miTabla tr').click(function () {
//        $('#cod_caja').val($(this).find("td").eq(0).html());
//        $('#descr_caja').val($(this).find("td").eq(1).html());
//    });
//}

function allTipoServicios() {
//alert("Llega allMarcas");
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/TipoServiciosServlet",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
              //alert(resp);
           // var jparse=JSON.parse(resp);
           // alert(jparse);
            $.each(resp, function (indice,value){
                $("#miTablaTipoServicios").append($("<tr>").append($("<td>"+value.id_tiposerv+"</td>"+
                                                        "<td>"+value.descripcion+"</td>")));
                
            });
          
            //$('#miTableHtml').html(resp);
        }
    });
}

function reportesTipoServicios() {
    window.open("reportesTipoServicios.jsp");
}