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
                <th>CODIGO SINTESIS</th>
                <th>ESTADO</th>
            </tr>
            <c:forEach items="${lstServicios}" var="item">
                <tr>
                    <td>${item.tesIdServicioBi}</td>
                    <td><a href="javascript:void(0)" onclick="update(this)" style="color: #113F7C">${item.tesDetalleVc}</a></td>
                    <td>${item.tesCodigoSintesisBi}</td>
                    <td><span class="glyphicon ${item.tesEstadoBt? 'glyphicon-ok':'glyphicon-remove'}"></span> </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Editar</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal">
                    <input type="hidden" id="Id" value="${servicio.tesIdServicioBi}"/>
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Datos</h3>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <label for="Nombre" class="col-sm-3 control-label">Detalle</label>
                                        <div class="col-sm-9">
                                            <p class="form-control-static" id="Nombre">${servicio.tesDetalleVc}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="Cuenta" class="col-sm-3 control-label">Cuenta</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="Cuenta" value="${servicio.tesCuentaVc}">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="CuentaContable" class="col-sm-3 control-label">Cuenta Contable</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="CuentaContable" value="${servicio.tesCuentaContableVc}">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="Comision" class="col-sm-3 control-label">Comision</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="Comision" value="${servicio.tesComisionDc}">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="Estado" class="col-sm-3 control-label">Estado</label>
                                        <div class="col-sm-9">
                                            <input type="checkbox" id="Estado">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Márgenes</h3>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="Margen" class="col-sm-3 control-label">Superior:</label>
                                        <div class="col-sm-9">
                                            <input type="number" class="form-control" id="Margen" value="${servicio.marginTop}">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="Margen" class="col-sm-3 control-label">Inferior:</label>
                                        <div class="col-sm-9">
                                            <input type="number" class="form-control" id="Margen" value="${servicio.marginBottom}">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="Margen" class="col-sm-3 control-label">Izquierdo:</label>
                                        <div class="col-sm-9">
                                            <input type="number" class="form-control" id="Margen" value="${servicio.marginLeft}">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="Margen" class="col-sm-3 control-label">Derecho:</label>
                                        <div class="col-sm-9">
                                            <input type="number" class="form-control" id="Margen" value="${servicio.marginRight}">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">QR</h3>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="QRX" class="col-sm-3 control-label">QR X</label>
                                        <div class="col-sm-9">
                                            <input type="number" class="form-control" id="QRX" value="${servicio.qrX}">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="QRY" class="col-sm-3 control-label">QR Y</label>
                                        <div class="col-sm-9">
                                            <input type="number" class="form-control" id="QRY" value="${servicio.qrY}">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="TamanoQR" class="col-sm-3 control-label">Tamaño QR</label>
                                        <div class="col-sm-9">
                                            <input type="number" class="form-control" id="TamanoQR" value="${servicio.qrScale}">
                                        </div>
                                    </div>
                                </div>
                            </div>
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
                cache: false,
                url: "/Web/Parametro/update",
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
        var id = tds[0].innerHTML;
        $.ajax({
            type: "GET",
            cache: false,
            url: "/Web/Parametro/search",
            data: {"id": id},
            success: function(json_data) {
                $("#content").html(json_data);
                $("#myModal").modal('show');
            }
        });
    }
</script>