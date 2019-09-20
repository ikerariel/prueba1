$(document).ready(function () {
    (function ($) {
        $('#filtrarEstados').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.buscarEstados tr').hide();
            $('.buscarEstados tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        });
    }(jQuery));
});
$(document).ready(function () {
    allEstados();
    $(":text").val("");
});

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "idestado": $('#codestado').val(),
        "descriestado": $('#descrestado').val()
        
    };
}

function  getUltimoCodigoEstados() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "Estadoscontrol",
        url: "http://localhost:8084/TALLERCASAJC/Estadoscontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#codestado").val(resp);
            $("#descrestado").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  recuperarEstadosporID() {
   // alert('Llega recuperarEstadosporID ');
    crearJSON(6);
    
    $.ajax({
        // url: "Estadoscontrol",
        url: "http://localhost:8084/TALLERCASAJC/Estadoscontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#descrestado").val(value.est_descripcion);
                $("#descrestado").val.focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}


function  ambEstados(id) {
    crearJSON(id);
    $.ajax({
        // url: "Estadoscontrol",
        url: "http://localhost:8084/TALLERCASAJC/Estadoscontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function () {
            alert('Registro Realizado Correctamente...!!!');
            location.reload();
            //allEstados();
        },
        error: function () {
            alert('Error Durante la Insercion del Registro...!!!');
        }
    });
}

function recuperar() {
    $('#miTablaEstados tr').click(function () {
        $('#codestado').val($(this).find("td").eq(0).html());
        $('#descrestado').val($(this).find("td").eq(1).html());
    });
}

//function modificar() {
//    $('#miTabla tr').click(function () {
//        $('#cod_caja').val($(this).find("td").eq(0).html());
//        $('#descr_caja').val($(this).find("td").eq(1).html());
//    });
//}

function allEstados() {
//alert("Llega allEstados");
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Estadoscontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
              //alert(resp);
           // var jparse=JSON.parse(resp);
           // alert(jparse);
            $.each(resp, function (indice,value){
                $("#miTablaEstados").append($("<tr>").append($("<td>"+value.id_estado+"</td>"+
                                                        "<td>"+value.est_descripcion+"</td>")));
                
            });
          
            //$('#miTableHtml').html(resp);
        }
    });
}

function campovacioEstados() { //Para verificar campos vacio
    var a = $('#descrestado').val(); //nombre del campos
    if (a === "") {
        alert('campo vacio');
        $('#descrestado');
    } else {
        mbEstados(1);
    }
}

function ControlarCampoEstados(){  // Para que no se repita nombre
    var dato;
    var Estados = $('#descrestado').val();
    // alert(Estados);
    $('#miTablaEstados tr').each(function () {
        dato = $(this).find('td').eq(1).html();
        if (dato === Estados) {
            alert('ESTE ESTADO YA EXISTE');
            $('#descrestado').val(null); //Vaciar Campos
            $('#descrestado').focus(); 
        } else {
        }
    });
}
function reportesEstados() {
    window.open("reportesEstados.jsp");
}