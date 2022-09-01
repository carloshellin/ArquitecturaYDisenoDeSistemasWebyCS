<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Error</title>
        <meta charset="utf-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <% 
            String tipo = (String) session.getAttribute("tipo");
            String error = (String) session.getAttribute("error");
        %> 
        
        <div class="container">
            <h1>Error al insertar <%= tipo %></h1>
            <p class="alert alert-danger"><%= error %></p>

            <a class="btn btn-primary" href="crear<%= tipo %>.html">Volver al formulario</a>
            <a class="btn btn-primary" href="index.jsp">Volver al inicio</a>
        </div>
    </body>
</html>

