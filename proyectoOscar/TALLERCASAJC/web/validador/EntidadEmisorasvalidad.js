$(document).ready(function () {
    (function ($) {
        $('#filtrarEntidadEmisoras').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.buscarEntidadEmisoras tr').hide();
            $('.buscarEntidadEmisoras tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        });
    }(jQuery));
});
$(document).ready(function () {
    allEntidadEmisoras();
    $(":text").val("");
});

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "identiemi": $('#codentiemi').val(),
        "descrientiemi": $('#descrentiemi').val()
        
    };
}

function  getUltimoCodigoEntidadEmisoras() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "EntidadEmisorascontrol",
        url: "http://localhost:8084/TALLERCASAJC/EntidadEmisorascontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#codentiemi").val(resp);
            $("#descrentiemi").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  recuperarEntidadEmisorasporID() {
   // alert('Llega recuperarCiudadesporID ');
    crearJSON(6);
    
    $.ajax({
        // url: "Ciudadescontrol",
        url: "http://localhost:8084/TALLERCASAJC/EntidadEmisorascontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#descrentiemi").val(value.entiemi_descripcion);
                $("#descrentiemi").val.focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}


function  ambEntidadEmisoras(id) {
    crearJSON(id);
    $.ajax({
        // url: "EntidadEmisorascontrol",
        url: "http://localhost:8084/TALLERCASAJC/EntidadEmisorascontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function () {
            alert('Registro Realizado Correctamente...!!!');
            location.reload();
            //allEntidadEmisoras();
        },
        error: function () {
            alert('Error Durante la Insercion del Registro...!!!');
        }
    });
}

function recuperar() {
    $('#miTablaEntidadEmisoras tr').click(function () {
        $('#codentiemi').val($(this).find("td").eq(0).html());
        $('#descrentiemi').val($(this).find("td").eq(1).html());
    });
}

//function modificar() {
//    $('#miTabla tr').click(function () {
//        $('#cod_caja').val($(this).find("td").eq(0).html());
//        $('#descr_caja').val($(this).find("td").eq(1).html());
//    });
//}

function allEntidadEmisoras() {
//alert("Llega allEntidadEmisoras");
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/EntidadEmisorascontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
              //alert(resp);
           // var jparse=JSON.parse(resp);
           // alert(jparse);
            $.each(resp, function (indice,value){
                $("#miTablaEntidadEmisoras").append($("<tr>").append($("<td>"+value.id_entiemi+"</td>"+
                                                        "<td>"+value.entiemi_descripcion+"</td>")));
                
            });
          
            //$('#miTableHtml').html(resp);
        }
    });
}

function campovacioEntidadEmisoras() { //Para verificar campos vacio
    var a = $('#descrentiemi').val(); //nombre del campos
    if (a === "") {
        alert('campo vacio');
        $('#descrentiemi');
    } else {
        ambEntidadEmisoras(1);
    }
}

function ControlarCampoEntidadEmisoras(){  // Para que no se repita Descripcion
    var dato;
    var EntidadEmisoras = $('#descrentiemi').val();
    // alert(EntidadEmisoras);
    $('#miTablaEntidadEmisoras tr').each(function () {
        dato = $(this).find('td').eq(1).html();
        if (dato === EntidadEmisoras) {
            alert('ESTA ENTIDAD EMISORA YA EXISTE');
            $('#descrentiemi').val(null); //Vaciar Campos
            $('#descrentiemi').focus(); 
        } else {
        }
    });
}
function reportesEntidadEmisoras() {
    window.open("reportesEntidadEmisoras.jsp");
}