<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 10px 50px">
    <div class="page-header">
        <h1>Consulta</h1>
    </div>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">Filtros</h3>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label for="TypeDate" class="col-sm-3 control-label">Fechas:</label>
                        <div class="col-sm-9">
                            <div class="input-daterange input-group" id="datepicker">
                                <input type="text" class="input-sm form-control" name="start" style="width: 100px; text-align: center; float: left" value="${init}"/>
                                <span class="input-group-addon" style="float: left; width: 50px">de</span>
                                <input type="text" class="input-sm form-control" name="end" style="width: 100px; text-align: center; float: left" value="${end}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6">
                    <div class="form-group">
                        <label for="TypeDate" class="col-sm-3 control-label">Ciudad:</label>
                        <div class="col-sm-9">
                            <select id="sucursal" class="form-control" onchange="Search()">
                                <option value="">TODAS</option>
                                <c:forEach items="${lstDirecciones}" var="item">
                                    <option value="${item.tesIdDireccionBi}" ${item.tesIdDireccionBi == sucursal ? "selected" : ""}>${item.tesCiudadVc}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="form-group">
                        <label for="TypeDate" class="col-sm-3 control-label">Servicio:</label>
                        <div class="col-sm-9">
                            <select id="servicio" class="form-control" onchange="Search()">
                                <option value="">TODAS</option>
                                <c:forEach items="${lstServicios}" var="item">
                                    <option value="${item.tesCodigoSintesisBi}" ${item.tesCodigoSintesisBi == servicio ? "selected" : ""}>${item.tesDetalleVc}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row" style="margin-top: 20px">
        <table class="table table-striped table-hover table-bordered" style="width:100%">
            <tr>
                <th style="width:5%">ID</th>
                <th style="width:20%">SERVICIO</th>
                <th style="width:25%">NOMBRE</th>
                <th style="width:10%">CUENTA</th>
                <th style="width:10%">FACTURA</th>
                <th style="width:10%">CIUDAD</th>
                <th style="width:10%">PDF</th>
                <th style="width:10%">IMPRIMIR</th>
            </tr>
            <c:forEach items="${lstFacturas}" var="item">
                <tr>
                    <td>${item.tesIdFacturaBi}</td>
                    <td>${item.servicio}</td>
                    <td>${item.tesNombreVc}</td>
                    <td>${item.tesCuentaVc}</td>
                    <td>${item.tesFacturaTexto}</td>
                    <td>${item.direccion}</td>
                    <td><a href="javascript:void(0)" onclick="PDF(${item.tesIdFacturaBi})" id="${item.tesIdFacturaBi}" class="btn btn-primary center-block">PDF</a></td>
                    <td><a href="javascript:void(0)" onclick="PRINT(${item.tesIdFacturaBi})" id="${item.tesIdFacturaBi}" class="btn btn-primary center-block">IMPRIMIR</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</div>

<script>
    $(document).ready(function() {
        $('.input-daterange').datepicker({
            format: "dd/mm/yyyy"
        });
    })
    function Search() {
        var sucursal = $("#sucursal").val();
        var servicio = $("#servicio").val();
        var init = $("input[name='start']").val();
        var end = $("input[name='end']").val();
        $.ajax({
            type: "GET",
            cache: false,
            url: "/Web/Consulta/Search",
            data: {"sucursal": sucursal, "init": init, "end": end, "servicio": servicio},
            success: function(json_data) {
                $("#content").html(json_data);
            }
        });
    }
    function PDF(id) {
        $.ajax({
            type: "GET",
            cache: false,
            url: "/Web/Consulta/pdf",
            contentType: 'application/octet-stream',
            data: {"id": id},
            success: function(json_data) {
                var blob = new Blob([json_data], {type: 'application/pdf'});
                var link = document.createElement('a');
                link.tagName
                link.href = window.URL.createObjectURL(blob);
                link.download = "SearchedResults.pdf";
                link.target = '_blank';
                link.click();
//                window.open("data:application/pdf;base64, " + json_data);
            }
        });
    }
    function PRINT(id) {
        $.ajax({
            type: "GET",
            cache: false,
            url: "/Web/Consulta/print",
            data: {"id": id},
            success: function(json_data) {
                alert("COMPLETADo");
            }
        });
    }
</script>
</div>