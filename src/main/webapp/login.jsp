<%@page contentType="text/html;" pageEncoding="UTF-8" %>
<jsp:include page="layout/header.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/StyleLoginForm.css">
    <title>${title}</title>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <h1>Login User</h1>
            <label for="user"> Username</label>
            <input type="txt" name="username" id="user">
            <label for="password"> Password</label>
            <input type="password" name="password" id="password">
            <input class="button" type="submit" value="Login">
        </form>
<jsp:include page="layout/footer.jsp"/>