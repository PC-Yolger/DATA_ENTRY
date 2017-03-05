<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 10px 50px">
    <div class="page-header">
        <h1>Parametros</h1>
    </div>
    <div class="row" style="margin-top: 20px">
        <table class="table table-striped table-bordered table-hover table-condensed" style="border-collapse: collapse" cellpanding="7px" border="1">
            <tr>
                <th>ID</th>
                <th>DETALLE</th>
                <th>CUENTA</th>
                <th>CUENTA CONTABLE</th>
                <th>COMISION</th>
                <th>ESTADO</th>
                <th>TAMAÑO QR</th>
                <th>QR X</th>
                <th>QR Y</th>
            </tr>
            <c:forEach items="${lstServicios}" var="item">
                <tr>
                    <td>${item.tesIdServicioBi}</td>
                    <td><a href="javascript:void(0)" onclick="update(this)" data-toggle="modal" data-target="#myModal" style="color: #113F7C">${item.tesDetalleVc}</a></td>
                    <td>${item.tesCuentaVc}</td>
                    <td>${item.tesCuentaContableVc}</td>
                    <td>${item.tesComisionDc}</td>
                    <td>${item.tesEstadoBt}</td>
                    <td>${item.qrScale}</td>
                    <td>${item.qrX}</td>
                    <td>${item.qrY}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Editar</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal">
                    <input type="hidden" id="Id"/>
                    <div class="form-group">
                        <label for="Nombre" class="col-sm-3 control-label">Detalle</label>
                        <div class="col-sm-9">
                            <p class="form-control-static" id="Nombre"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="Cuenta" class="col-sm-3 control-label">Cuenta</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="Cuenta">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="CuentaContable" class="col-sm-3 control-label">Cuenta Contable</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="CuentaContable">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="Comision" class="col-sm-3 control-label">Comision</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="Comision">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="Estado" class="col-sm-3 control-label">Estado</label>
                        <div class="col-sm-9">
                            <input type="checkbox" id="Estado">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="TamanoQR" class="col-sm-3 control-label">Tamaño QR</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="TamanoQR">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="QRX" class="col-sm-3 control-label">QR X</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="QRX">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="QRY" class="col-sm-3 control-label">QR Y</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="QRY">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                <button type="button" class="btn btn-primary save">Guardar</button>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function() {
        $(".save").click(function() {
            $("#myModal").modal('hide');
            var id = $('#Id').val();
            var nombre = $('#Nombre').text();
            var cuenta = $('#Cuenta').val();
            var cuentaContable = $('#CuentaContable').val();
            var comision = $('#Comision').val();
            var estado = $('#Estado').val();
            var tamanoQR = $('#TamanoQR').val();
            var qrx = $('#QRX').val();
            var qry = $('#QRY').val();
            $.ajax({
                type: "POST",
                url: "/Parametro/update",
                data: {"id": id, "nombre": nombre, "cuenta": cuenta, "cuentaContable": cuentaContable, "comision": comision, "estado": estado, "tamanoQR": tamanoQR, "qrx": qrx, "qry": qry},
                success: function(json_data) {
                    $("#content").html(json_data);
                }
            });
        })
    })
    function update(element) {
        var data = $(element).get(0);
        var _parent = data.parentNode.parentNode;
        var tds = _parent.getElementsByTagName("td");
        $('#Id').val(tds[0].innerHTML);
        $('#Nombre').text(data.textContent);
        $('#Cuenta').val(tds[2].innerHTML);
        $('#CuentaContable').val(tds[3].innerHTML);
        $('#Comision').val(tds[4].innerHTML);
        $('#Estado').val(tds[5].innerHTML);
        $('#TamanoQR').val(tds[6].innerHTML);
        $('#QRX').val(tds[7].innerHTML);
        $('#QRY').val(tds[8].innerHTML);
    }
</script>