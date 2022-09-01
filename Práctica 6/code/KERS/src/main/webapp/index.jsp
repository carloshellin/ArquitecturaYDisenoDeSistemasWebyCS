<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Calculadora de ganancia de potencia</title>
        <meta charset="utf-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <%@ page import="java.sql.*" %>
        <%
            Connection c = DriverManager.getConnection("jdbc:derby://localhost:1527/sample","app","app"); 
            PreparedStatement pstm1 = c.prepareStatement("SELECT nombre FROM CIRCUITO");
            PreparedStatement pstm2 = c.prepareStatement("SELECT nombre FROM COCHE");
            ResultSet circuitos = pstm1.executeQuery();
            ResultSet coches = pstm2.executeQuery();
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

            <h1>Calculadora de ganancia de potencia</h1>

            <form method="post" action="CalcularGanancia">
                <p>
                    <label class="form-label">Selecciona un circuito:</label>
                    <select class="form-control" name="circuitos" required>
                        <% while (circuitos.next()) { %>
                        <option value="<%= circuitos.getString(1) %>"><%= circuitos.getString(1) %></option>
                        <% } %>
                    </select>
                </p>
                <p>
                    <label class="form-label">Selecciona un coche:</label>
                    <select class="form-control" name="coches" required>
                        <% while (coches.next()) { %>
                        <option value="<%= coches.getString(1) %>"><%= coches.getString(1) %></option>
                        <% } %>
                    </select>
                </p>
                <p><button class="btn btn-primary">Calcular</button></p>
                </div>
            </form>
        </div>
    </body>
</html>
