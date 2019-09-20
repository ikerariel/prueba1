<%-- 
    Document   : GestorReportes
    Created on : 27/07/2018, 10:29:59 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>REPORTE</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript">
            function loadDocPdf(bandera, valor) {
                var datos;
                var xhr = new XMLHttpRequest();

                xhr.open("GET", "/TALLERCASAJC/gestorReportesCTRL?bandera=" + bandera + "&valor=" + valor + "");
                xhr.responseType = "arraybuffer";

                xhr.onload = function () {
                    if (this.status === 200) {
                        var blob = new Blob([xhr.response], {type: "application/pdf"});
                        var objectUrl = URL.createObjectURL(blob);
                        window.open(objectUrl, '_blank');
                    }
                };
                xhr.send();
                // xhr.send(JSON.stringify(datos = {bandera: bandera}));
                // xhr.send(JSON.stringify(datos = {bandera: 2, id: $('#codigo_presupuesto').val()}));
            }
        </script>
    </head>
    <body>
        <center>
        <div>
            <h2>Listados de Referenciales</h2>
            <table>
                <tbody>
                    <tr>
                        <td>
                            Ciudades <br>
                            <input type="button" value=" PDF" onclick="loadDocPdf(1, 0);" />
                        </td>
                        <td>
                            Depositos <br>
                            <input type="button" value=" PDF" onclick="loadDocPdf(2, 0);" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            barrio <br>
                            <input type="button" value=" PDF" onclick="loadDocPdf(3, 0);" />
                        </td>

                        <td>
                            productos <br>
                            <input type="button" value=" PDF" onclick="loadDocPdf(4, 0);" />
                        </td>
                        <td>
                            Marcas <br>
                            <input type="button" value=" PDF" onclick="loadDocPdf(5, 0);" />
                        </td>

                        <td>
                            proveedores <br>
                            <input type="button" value=" PDF" onclick="loadDocPdf(6, 0);" />
                        </td>

                        <td>
                            tipo_usuario <br>
                            <input type="button" value=" PDF" onclick="loadDocPdf(7, 0);" />
                        </td>

                        <td>
                            tipo_impuesto <br>
                            <input type="button" value=" PDF" onclick="loadDocPdf(8, 0);" />
                        </td>

                        <td>
                            ajustes <br>
                            <input type="button" value=" PDF" onclick="loadDocPdf(9, 0);" />
                        </td>

                        <td>
                            sucursal <br>
                            <input type="button" value=" PDF" onclick="loadDocPdf(10, 0);" />
                        </td>

                        <td>
                            compra <br>
                            <input type="button" value=" PDF" onclick="loadDocPdf(11, 0);" />
                        </td>

                    </tr>
                    <tr>
                        <td>
                            Clientes Segun ID <br>
                            <input type="text" id="id_clientes"> <br>
                            <input type="button" value="Ver PDF" onclick="loadDocPdf(4, document.getElementById('id_clientes').value);" />
                        </td>
                        <td>
                            bb <br>
                            <input type="button" value="Ver PDF" onclick="loadDocPdf();" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </center>
    </body>
</html>
