<%@page contentType="text/html;" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.time.format.DateTimeFormatter" %>
<jsp:include page="layout/header.jsp"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ProcutosForm.css">

<c:if test="${errores !=null && !errores.isEmpty()}">
<div class="errores">
    <ul>
        <c:if test="${not empty errores}">
            <c:forEach items="${errores}" var="valor">
                <li>${valor.value}</li>
            </c:forEach>
        </c:if>
    </ul>
</div>
</c:if>
<form action="${pageContext.request.contextPath}/productos/form" method="post">
    <h1>Formulario de productos</h1>
    <label for="nombre">Nombre</label>
    <input type="text" name="nombre" id="nombre" value="${producto.nombre !=null ? producto.nombre : ""}">
    <label for="precio">Precio</label>
    <input type="number" name="precio" id="precio" value="${producto.precio > 0 ? producto.precio : ""}">
    <label for="sku">Sku</label>
    <input type="text" name="sku" id="sku" value="${producto.sku != null ? producto.sku : ""}">
    <label for="fecha">Fecha</label>
    <input type="date" name="fecha_registro" id="fecha" value="${producto.fechaIngreso != null ? producto.fechaIngreso.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : ""}">
    <label for="categoria">Categoria</label>
    <select name="categoria" id="categoria">
        <option value="">Seleccionar</option>
        <c:forEach items="${categorias}" var="c">
            //preguntamos si cada iteracion del id de la categoria es igual al id de la categoria que corresponde al producto
            <option value="${c.id}" ${c.id.equals(producto.categoria.id) ? "selected" : ""}>${c.nombre}</option>
        </c:forEach>
    </select>
    <input class="button" type="submit" value="${producto.id != null && producto.id > 0 ? "Editar" : "Guardar" }">
    <input type="hidden" name="id" value="${producto.id}">
</form>
<jsp:include page="layout/footer.jsp"/>