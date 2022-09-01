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
        <h1 class="jsp">Logout...</h1>
        <h2 class="jsp">Hasta luego!</h2>
        
        <% if (session.getAttribute("cliente") != null) {
                //Si hay una sesión cliente, se hace logout
                session.removeAttribute("cliente");
            } else if (session.getAttribute("admin") != null) {
                //Si hay una sesión admin, se hace logout
                session.removeAttribute("admin");
            }
        %>
        
        <%-- Se redirige a index --%>
        <meta http-equiv="refresh" content="0; url=index.html" />
    </body>
</html>
