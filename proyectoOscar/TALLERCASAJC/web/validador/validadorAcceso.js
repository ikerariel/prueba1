

$(document).ready(function () {



});


function accesouser() {
    userdatos = {
        'opcion': 1,
        'usuario': $('#username_v').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/acceSERVLET",
        type: 'POST',
        data: userdatos,
        cache: false,
        success: function (resp) {
            if (JSON.stringify(resp) != '[]') {
                $.each(resp, function (indice, valor) {
                    $('#iduser_v').val(valor.id_usuario);
                    $('#idsucu_v').val(valor.id_sucursal);
                    $('#descripsucu_v').val(valor.suc_descripcion);
                    $('#iddepo_v').val(valor.id_deposito);
                    $('#descripdepo_v').val(valor.dep_descripcion);
                    $('#idsupervisor_v').val(valor.idsupervisor);
                    $('#idvendedor_v').val(valor.idvendedor);
                     $("#password_v").removeAttr('disabled', true);
//                    location.href="paginaprincipal.jsp";
                });
            } else {
                    $("#password_v").prop('disabled', true);
                    $('#username_v').focus();
                    $('#username_v').val(null);
                    $('#iduser_v').val(null);
                    $('#idsucu_v').val(null);
                    $('#descripsucu_v').val(null);
                    $('#iddepo_v').val(null);
                    $('#descripdepo_v').val(null);
                    $('#idsupervisor_v').val(null);
            }

        },
        error: function () {
        }
    });
}