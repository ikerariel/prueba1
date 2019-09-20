$(document).ready(function () {
    viewFechas();
});
function viewFechas() {
    var fv = new Date();
    $('#fechaOrden').val(fv.getDate() + "/" + (fv.getMonth() + 1) + "/" + fv.getFullYear());
    $('#fechapedidoventa').val(fv.getDate() + "/" + (fv.getMonth() + 1) + "/" + fv.getFullYear());
}
function buscardortabla(tabla, buscadortxt) {
    var tableReg = document.getElementById(tabla);
    var searchText = document.getElementById(buscadortxt).value.toLowerCase();
    var cellsOfRow = "";
    var found = false;
    var compareWith = "";
// Recorremos todas las filas con contenido de la tabla
    for (var i = 1; i < tableReg.rows.length; i++) {
        cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
        found = false;
// Recorremos todas las celdas
        for (var j = 0; j < cellsOfRow.length && !found; j++) {
            compareWith = cellsOfRow[j].innerHTML.toLowerCase();
// Buscamos el texto en el contenido de la celda
            if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1)) {
                found = true;
            }
        }
        if (found) {
            tableReg.rows[i].style.display = '';
        } else {
// si no ha encontrado ninguna coincidencia, esconde la fila de la tabla
            tableReg.rows[i].style.display = 'none';
        }
    }
}//--------------

function verificarcampoentero(campo) {

    var variable = $('#' + campo).val();
    var resultado = isNaN(variable);
    switch (resultado) {
        case true:

            break;
        case false:
            $('#' + campo).focus();
            $('#' + campo).val(null);
            break;
        default :
            break;
    }
}
function solonumeros(campo) {

    var variable = $('#' + campo).val();
    var resultado = isNaN(variable);
    switch (resultado) {
        case true:
            $('#' + campo).focus();
            $('#' + campo).val(null);
            break;
        case false:

            break;
        default :
            break;
    }
}
function numeroDecimal(...uno) {
    for (let numero of uno) {
        var num = document.getElementById(numero).value.replace(/\./g, '');
        if (!isNaN(num)) {
            num = num.toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g, '$1.');
            num = num.split('').reverse().join('').replace(/^[\.]/, '');
            document.getElementById(numero).value = num;
            $('#' + numero).css('font-weight', 'bold');

        } else {
            alert('Solo se permiten numeros');
            document.getElementById(numero).value = document.getElementById(numero).value.replace(/[^\d\.]*/g, '');

        }
    }

}