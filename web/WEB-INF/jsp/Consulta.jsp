<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 10px 50px">
    <div class="page-header">
        <h1>Consulta</h1>
    </div>
    <div class="row">
        <div class="form-group">
            <label for="TypeDate" class="col-sm-2 control-label">Fechas:</label>
            <div class="input-daterange input-group col-sm-10" id="datepicker" style="padding-left: 15px">
                <input type="text" class="input-sm form-control" name="start" style="width: 100px; text-align: center; float: left" value="${init}"/>
                <span class="input-group-addon" style="float: left; width: 50px">de</span>
                <input type="text" class="input-sm form-control" name="end" style="width: 100px; text-align: center; float: left" value="${end}"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group">
            <label for="TypeDate" class="col-sm-2 control-label">Ciudad</label>
            <div class="col-sm-10" style="width: 300px">
                <select id="sucursal" class="form-control" onchange="Search()">
                    <option value="">TODAS</option>
                    <c:forEach items="${lstDirecciones}" var="item">
                        <option value="${item.tesIdDireccionBi}" ${item.tesIdDireccionBi == sucursal ? "selected" : ""}>${item.tesCiudadVc}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group">
            <label for="TypeDate" class="col-sm-2 control-label">Servicio</label>
            <div class="col-sm-10" style="width: 300px">
                <select id="servicio" class="form-control" onchange="Search()">
                    <option value="">TODAS</option>
                    <c:forEach items="${lstServicios}" var="item">
                        <option value="${item.tesIdServicioBi}" ${item.tesIdServicioBi == servicio ? "selected" : ""}>${item.tesDetalleVc}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
    <div class="row" style="margin-top: 20px">
        <table class="table table-striped table-bordered table-hover table-condensed" style="border-collapse: collapse" cellpanding="7px" border="1">
            <tr>
                <th>ID</th>
                <th>CODIGO SINTESIS</th>
                <th>OPERACION</th>
                <th>NIT</th>
                <th>NOMBRE</th>
                <th>CUENTA</th>
                <th>FACTURA</th>
                <th>DIRECCION</th>
                <th>IMPRIMIR</th>
            </tr>
            <c:forEach items="${lstFacturas}" var="item">
                <tr>
                    <td>${item.tesIdFacturaBi}</td>
                    <td>${item.tesCodigoSintesisBi}</td>
                    <td>${item.tesOperacionVc}</td>
                    <td>${item.tesNitVc}</td>
                    <td>${item.tesNombreVc}</td>
                    <td>${item.tesCuentaVc}</td>
                    <td>${item.tesFacturaTexto}</td>
                    <td>${item.testIdDireccionBi}</td>
                    <td><a href="#" onclick="PDF(${item.tesIdFacturaBi})" id="${item.tesIdFacturaBi}" class="btn btn-primary center-block">IMPRIMIR</a></td>
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
        $.ajax({
            type: "GET",
            url: "/Consulta/Search",
            data: {"sucursal": sucursal, "init": "1", "end": "2", "servicio": servicio},
            success: function(json_data) {
                $("#content").html(json_data);
            }
        });
    }
    function PDF(id) {
        $.ajax({
            type: "GET",
            url: "/Consulta/pdf",
            data: {"id": id},
            success: function(json_data) {
                alert("COMPLETADo");
            }
        });
    }
</script>
</div>