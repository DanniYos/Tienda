<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="layout/header.jsp"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/StyleUsuariosForm.css">
    <form action="${pageContext.request.contextPath}/usuario/form" method="post">
        <h1>Users</h1>
        <label for="user">Username</label>
        <input name="user" id="user" value="${usuario.username != null ? usuario.username : "" }">
        <c:if test="${errores != null && errores.containsKey('usuario')}">
            <div style="color: red">${errores.usuario}</div>
        </c:if>
        <label for="password">Password</label>
        <input type="password" name="password" id="password">
        <c:if test="${errores != null && errores.containsKey('password')}">
            <div style="color: red">${errores.password}</div>
        </c:if>
        <label for="email">Email</label>
        <input name="email" id="email" value="${usuario.email != null ? usuario.email : ""}">
        <c:if test="${errores != null && errores.containsKey('email')}">
            <div style="color: red">${errores.email}</div>
        </c:if>
        <input class="button" type="submit" value="${usuario.id == null ? "Guardar" : "Actualizar"}">
        <input type="hidden" name="id" value="${usuario.id}">
    </form>
<jsp:include page="layout/footer.jsp"/>