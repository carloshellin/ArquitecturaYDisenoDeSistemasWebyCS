<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Éxito</title>
        <meta charset="utf-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <% 
            String tipo = (String) session.getAttribute("tipo");
            String nombre = (String) session.getAttribute("nombre");
            String accion = (String) session.getAttribute("accion");
        %> 
        
        <div class="container">
            <ul class="nav nav-tabs">
                <li class="nav-item">
                    <a class="nav-link" href="index.jsp">Calculadora</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <% if (tipo.equals("circuito")) out.print(" active\" href=\"crearcircuito.html\""); else out.print("\" href=\"#\""); %>>Crear circuito</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <% if (tipo.equals("coche")) out.print(" active\" href=\"crearcoche.html\""); else out.print("\" href=\"#\""); %>>Crear coche</a>
                </li>
            </ul>
            
            <h1>Se ha <%= accion %> el <%= tipo %>: <%= nombre %></h1>

            <a class="btn btn-primary" href="crear<%= tipo %>.html">Volver al formulario</a>
            <a class="btn btn-primary" href="index.jsp">Volver al inicio</a>
        </div>
    </body>
</html>
