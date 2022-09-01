<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Resultado</title>
        <meta charset="utf-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <% 
            Integer ganancia = (Integer) session.getAttribute("ganancia");
            Integer kwcurva = (Integer) session.getAttribute("kwcurva");
            Integer vueltas = (Integer) session.getAttribute("vueltas");
            Integer curvas = (Integer) session.getAttribute("curvas");
        %> 
        
        <div class="container">
            <ul class="nav nav-tabs">
                <li class="nav-item">
                    <a class="nav-link active" href="#">Calculadora</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="crearcircuito.html">Crear circuito</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="crearcoche.html">Crear coche</a>
                </li>
            </ul>
            
            <h1>Resultado de la ganancia</h1>

            <p>El resultado de la ganancia es: <strong><%= ganancia %></strong></p>
            <p>
                Se ha calculado a partir de los siguientes datos:
                <ul>
                    <li>El coche gana <%= kwcurva %> kW de potencia por cada curva</li>
                    <li>El circuito tiene un total de <%= vueltas %> vueltas</li>
                    <li>El circuito tiene <%= curvas %> curvas por cada vuelta</li>
                </ul>
            </p>

            <a class="btn btn-primary" href="index.jsp">Realizar otro cálculo de ganancia</a>
        </div>
    </body>
</html>

