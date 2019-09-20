$(document).ready(function () {
    (function ($) {
        $('#filtrarSucursales').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.buscarSucursales tr').hide();
            $('.buscarSucursales tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        });
    }(jQuery));
});
$(document).ready(function () {
    allSucursales();
    nuevoListarCiudades();
    nuevoListarBarrios();
    $(":text").val("");
});

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "codsucursal": $('#idsucursal').val(),
        "sucursaldescri": $('#descrisucursal').val(),
        "codciudad": $('#idciudad').val(),
        "codbarrio": $('#idbarrio').val()  
    };
}

function  getUltimoCodigoSucursales() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "Sucursalescontrol",
        url: "http://localhost:8084/TALLERCASAJC/Sucursalescontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#idsucursal").val(resp);
            $("#descrisucursal").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  getmostrarBarriosFiltro() {
   // alert('Llega recuperarSucursalesporID ');
    crearJSON(6);
    
    $.ajax({
        // url: "Sucursalescontrol",
        url: "http://localhost:8084/TALLERCASAJC/Sucursalescontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#descrisucursal").val(value.suc_descripcion);
                $('#idciudad> option[value=' + value.id_ciudad + ']').attr('selected', 'selected');
                $('#idbarrio> option[value=' + value.id_barrio + ']').attr('selected', 'selected');
                $("#descrisucursal").val.focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        } });
}

function  ambSucursales(id) {
    crearJSON(id);
    $.ajax({
        // url: "Sucursalescontrol",
        url: "http://localhost:8084/TALLERCASAJC/Sucursalescontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function () {
            alert('Registro Realizado Correctamente...!!!');
            location.reload();
            //allSucursales();
        },
        error: function () {
            alert('Error Durante la Insercion del Registro...!!!');
        } });
}

function recuperar() {
    $('#miTablaSucursales tr').click(function () {
        $('#idsucursal').val($(this).find("td").eq(0).html());
        $('#descrisucursal').val($(this).find("td").eq(1).html());
        $('#idciudad').val($(this).find("td").eq(2).html());
        $('#idbarrio').val($(this).find("td").eq(3).html());
    });
}

function allSucursales() {
//alert("Llega allSucursales");
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Sucursalescontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
              //alert(resp);
           // var jparse=JSON.parse(resp);
           // alert(jparse);
            $.each(resp, function (indice,value){
                $("#miTablaSucursales").append($("<tr>").append($(
                        "<td>"+value.id_sucursal+"</td>"+
                        "<td>"+value.suc_descripcion+"</td>"+
                        "<td>"+value.ciu_descripcion+"</td>"+
                        "<td>"+value.barr_descripcion+"</td>")));               
            });
        }
    });
}
//    combo ciudades 
    function nuevoListarCiudades() {
    crearJSON(7);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Sucursalescontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (data) {
            $.each(data, function (indice, value) {
                $("#idciudad").append("<option value= \"" + value.id_ciudad + "\"> " + value.ciu_descripcion + "</option>");
            });
        }
    });
}
//    combo barrios 
function nuevoListarBarrios() {
    crearJSON(8);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Sucursalescontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (data) {
            $.each(data, function (indice, value) {
                $("#idbarrio").append( "<option value= \"" 
                        + value.id_barrio + "\"> "  
                        + value.barr_descripcion + "</option>");
            });
        }
    });
}

function reportesSucursales() {
    window.open("reportesSucursales.jsp");
}