/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    (function ($) {
        $('#filtrarBancoCheques').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.buscarBancoCheques tr').hide();
            $('.buscarBancoCheques tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        });
    }(jQuery));
});
$(document).ready(function () {
    allBancoCheques();
    $(":text").val("");
});

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "idbancheque": $('#codbancheque').val(),
        "descri": $('#descr').val()
        
    };
}

function  getUltimoCodigo() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "Marcascontrol",
        url: "http://localhost:8084/TALLERCASAJC/BancoChequesServlet",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#codbancheque").val(resp);
            $("#descr").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  recuperarBancoChequesporID() {
   // alert('Llega recuperarMarcasporID ');
    crearJSON(6);
    
    $.ajax({
        // url: "Marcascontrol",
        url: "http://localhost:8084/TALLERCASAJC/BancoChequesServlet",
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


function  ambBancoCheques(id) {
    crearJSON(id);
    $.ajax({
        // url: "Marcascontrol",
        url: "http://localhost:8084/TALLERCASAJC/BancoChequesServlet",
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
    $('#miTablaBancoCheques tr').click(function () {
        $('#codbancheque').val($(this).find("td").eq(0).html());
        $('#descr').val($(this).find("td").eq(1).html());
    });
}

//function modificar() {
//    $('#miTabla tr').click(function () {
//        $('#cod_caja').val($(this).find("td").eq(0).html());
//        $('#descr_caja').val($(this).find("td").eq(1).html());
//    });
//}

function allBancoCheques() {
//alert("Llega allMarcas");
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/BancoChequesServlet",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
              //alert(resp);
           // var jparse=JSON.parse(resp);
           // alert(jparse);
            $.each(resp, function (indice,value){
                $("#miTablaBancoCheques").append($("<tr>").append($("<td>"+value.id_bancocheque+"</td>"+
                                                        "<td>"+value.descripcion+"</td>")));
                
            });
          
            //$('#miTableHtml').html(resp);
        }
    });
}

function campovacioBancoCheques() { //Para verificar campos vacio
    var a = $('#descr').val(); //nombre del campos
    if (a === "") {
        alert('campo vacio');
        $('#descr');
    } else {
        ambBancoCheques(1);
    }
}

function ControlarCampoBancoCheques(){  // Para que no se repita nombre
    var dato;
    var BancoCheques = $('#descr').val();
    // alert(BancoCheques);
    $('#miTablaBancoCheques tr').each(function () {
        dato = $(this).find('td').eq(1).html();
        if (dato === BancoCheques) {
            alert('ESTE BANCO CHEQUES YA EXISTE');
            $('#descr').val(null); //Vaciar Campos
            $('#descr').focus(); 
        } else {
        }
    });
}
function reportesBancoCheques() {
    window.open("reportesBancoCheques.jsp");
}