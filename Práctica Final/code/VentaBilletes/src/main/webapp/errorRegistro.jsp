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
        <% 
            String tipo = (String) session.getAttribute("tipo");
            String error = (String) session.getAttribute("error");
            String nombre = (String) session.getAttribute("nombre");
        %> 
        <h1 class="jsp">Error en el registro. El <%= tipo %> <%= nombre %> ya está registrado en la web.</h1>
        <p><h2 class="jsp"><%= error %></h2></p>
        <p>
        <h3 class="jspH3"><a href="registro.html">Volver al formulario</a>
        <a href="index.html">Volver al inicio</a></h3></p>
    </body>
</html>

