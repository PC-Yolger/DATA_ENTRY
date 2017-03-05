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
                    <td>${item.tesDetalleVc}</td>
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