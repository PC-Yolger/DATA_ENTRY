<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <%@include file="Shared/header.jsp" %>
    <body>
        <%@include file="Shared/navbar.jsp" %>
        <div class="container-fluid">
            <div class="row content">
                <div class="col-sm-3 sidenav hidden-xs" style="padding-top: 10px;">
                    <img src="${img}/logotipo.png" class="img-responsive"/>
                    <ul class="nav nav-pills nav-stacked" style="margin-top: 20px;">
                        <li><a href="${contextRoot}/Consulta/">Consulta</a></li>
                        <li><a href="#section3">Reporte</a></li>
                        <li><a href="${contextRoot}/Parametro/">Parametros</a></li>
                    </ul><br>
                </div>
                <br>
                
                <div id="content" class="col-sm-9">
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
