<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Alcala Airlines</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <link rel="shortcut icon" href="img/Alcala airlines.png" type="image/x-icon">
        <img class="fotoCabecera" src="img/iconoNombre.png">
    </head>
    <body>
        <h1 class="jsp">Login...</h1>
        
        <%@ page import="com.practicafinal.ventabilletes.bbdd.ModeloDatos" %>
        <%
            ModeloDatos bd = new ModeloDatos();
            bd.abrirConexion();
        %>
        
        <% String correo = request.getParameter("correo"); %>
        <% String contraseña = request.getParameter("contrasena"); %>

        <% if (correo == null || contraseña == null) {%>
            <meta http-equiv="refresh" content="0; url=index.html" />

        <%-- Si es un administrador, se da una sesión para él --%>
        <% } else if (bd.loginAdmin(correo, contraseña)) { %>
            <% session.setAttribute("admin", correo); %>
            <meta http-equiv="refresh" content="0; url=admin.jsp" />
            
        <%-- Si no lo es, se comprueba si es un cliente --%>
        <% } else if (bd.loginCliente(correo, contraseña)) { %>
            <%-- Datos correctos --%>
            <% session.setAttribute("cliente", correo); %>
            <meta http-equiv="refresh" content="0; url=vuelos.jsp" />
        <% } else { %>
            <%-- Datos incorrectos --%>
            <h1 class="jsp">Error.</h1>
            <h2 class="jsp">Los datos introducidos no son correctos.</h2>
            <h3 class="jspH3"><a href="index.html">Volver a inicio</a></h3>
        <% }%>
    </body>
</html>
