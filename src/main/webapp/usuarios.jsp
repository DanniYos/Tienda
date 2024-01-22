<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="layout/header.jsp"/>
<table class="table table-hover table-striped">
    <h1>${title}</h1>
    <c:if test="${username.present}">
        <p><a class="btn btn-primary" href="${pageContext.request.contextPath}/usuario/form"> Agregar [+]</a></p>
    </c:if>
    <tr>
        <td>Id</td>
        <td>Username</td>
        <td>Password</td>
        <td>Email</td>
        <c:if test="${username.present}">
            <td>Editar</td>
            <td>Eliminar</td>
        </c:if>
    </tr>
    <c:forEach items="${requestScope.usuarios}" var="usuario">
        <tr>
            <td>${usuario.id}</td>
            <td>${usuario.username}</td>
            <td>${usuario.password}</td>
            <td>${usuario.email}</td>
            <c:if test="${username.present}">
                <td><a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/usuario/form?id=${usuario.id}">Editar</a></td>
                <td><a class="btn btn-sm btn-danger" onclick="return confirm('Esta seguro que desea eliminar?')"
                       href="${pageContext.request.contextPath}/usuario/eliminar?id=${usuario.id}">Eliminar</a></td>
            </c:if>
        </tr>
    </c:forEach>
</table>
<jsp:include page="layout/footer.jsp"/>
