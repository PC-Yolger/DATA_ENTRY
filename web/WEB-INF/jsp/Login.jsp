<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="Shared/header.jsp" %>
    <body>
        <div class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${contextRoot}/Web">LOGO</a>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="panel panel-primary" style="max-width: 500px; margin: 0 auto;">
                <div class="panel-heading">
                    <h3 class="panel-title">Login</h3>
                </div>
                <div class="panel-body">
                    <spring:form modelAttribute="user" method="post" action="/Web/login">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <spring:label cssClass="col-sm-3 control-label" path="usuario">Usuario:</spring:label>
                                        <div class="col-sm-9">
                                        <spring:input cssClass="form-control" path="usuario"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <spring:label cssClass="col-sm-3 control-label" path="password">Contraseña:</spring:label>
                                        <div class="col-sm-9">
                                        <spring:password cssClass="form-control" path="password"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-sm-12">
                                <input class="btn btn-primary btn-block" type="submit" value="Login">
                            </div>
                        </div>
                        <c:if test="${not empty message}">
                            <br>
                            <div class="alert alert-dismissible alert-danger">
                                <button class="close" type="button" data-dismiss="alert">&times;</button>
                                ${message}
                            </div>
                        </c:if>
                    </spring:form>
                </div>
            </div>
        </div>
    </body>
</html>
