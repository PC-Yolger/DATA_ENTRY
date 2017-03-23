<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="Shared/header.jsp" %>
    <body>
        <div class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${contextRoot}/Web/Home/">${user}</a>
                </div>
            </div>
        </div>
        <div class="container" style="width: 100%">
            <div class="row">
                <div class="col-sm-2">
                    <div class="nav nav-stacked affix">
                        <div class="list-group">
                            <a class="list-group-item" href="${contextRoot}/Web/Consulta/">Consulta</a>
                            <a class="list-group-item" href="Reporte.jsp">Reporte</a>
                            <a class="list-group-item" href="${contextRoot}/Web/Parametro/">Parametros</a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-10" id="content">
                    <c:if test="${home == true}">
                        <%@include file="Home.jsp" %>
                    </c:if>
                    <c:if test="${consulta == true}">
                        <%@include file="Consulta.jsp" %>
                    </c:if>
                    <c:if test="${parametro == true}">
                        <%@include file="Parametro.jsp" %>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
