$(document).ready(function () {
    (function ($) {
        $('#filtrarImpuestos').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.buscarImpuestos tr').hide();
            $('.buscarImpuestos tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        });
    }(jQuery));
});
$(document).ready(function () {
    allImpuestos();
    $(":text").val("");
});

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "idimpuesto": $('#codimpuesto').val(),
        "descriimpuesto": $('#descrimpuesto').val()
        
    };
}

function  getUltimoCodigo() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "Impuestoscontrol",
        url: "http://localhost:8084/TALLERCASAJC/Impuestoscontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#codimpuesto").val(resp);
            $("#descrimpuesto").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  recuperarImpuestosporID() {
   // alert('Llega recuperarImpuestosporID ');
    crearJSON(6);
    
    $.ajax({
        // url: "Impuestoscontrol",
        url: "http://localhost:8084/TALLERCASAJC/Impuestoscontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#descrimpuesto").val(value.imp_descripcion);
                $("#descrimpuesto").val.focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}


function  ambImpuestos(id) {
    crearJSON(id);
    $.ajax({
        // url: "Impuestoscontrol",
        url: "http://localhost:8084/TALLERCASAJC/Impuestoscontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function () {
            alert('Registro Realizado Correctamente...!!!');
            location.reload();
            //allImpustos();
        },
        error: function () {
            alert('Error Durante la Insercion del Registro...!!!');
        }
    });
}

function recuperar() {
    $('#miTablaImpuestos tr').click(function () {
        $('#codimpuesto').val($(this).find("td").eq(0).html());
        $('#descrimpuesto').val($(this).find("td").eq(1).html());
    });
}

//function modificar() {
//    $('#miTabla tr').click(function () {
//        $('#cod_caja').val($(this).find("td").eq(0).html());
//        $('#descr_caja').val($(this).find("td").eq(1).html());
//    });
//}

function allImpuestos() {
//alert("Llega allImpuestos");
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Impuestoscontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
              //alert(resp);
           // var jparse=JSON.parse(resp);
           // alert(jparse);
            $.each(resp, function (indice,value){
                $("#miTablaImpuestos").append($("<tr>").append($("<td>"+value.id_impuesto+"</td>"+
                                                        "<td>"+value.imp_descripcion+"</td>")));
                
            });
          
            //$('#miTableHtml').html(resp);
        }
    });
}


function campovacioImpuestos() { //Para verificar campos vacio
    var a = $('#descrimpuesto').val(); //nombre del campos
    if (a === "") {
        alert('campo vacio');
        $('#descrimpuesto');
    } else {
        ambImpuestos(1);
    }
}

function ControlarCampoImpuestos(){  // Para que no se repita nombre
    var dato;
    var Impuestos = $('#descrimpuesto').val();
    // alert(Impuestos);
    $('#miTablaImpuestos tr').each(function () {
        dato = $(this).find('td').eq(1).html();
        if (dato === Impuestos) {
            alert('ESTE IMPUESTO YA EXISTE');
            $('#descrimpuesto').val(null); //Vaciar Campos
            $('#descrimpuesto').focus(); 
        } else {
        }
    });
}

function reportesImpuestos() {
    window.open("reportesImpuestos.jsp");
}

