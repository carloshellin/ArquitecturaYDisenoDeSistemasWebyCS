
<%@page import="com.practicafinal.ventabilletes.bbdd.Aeropuerto"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alcala Airlines | Vuelos </title>
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <link rel="shortcut icon" href="img/Alcala airlines.png" type="image/x-icon">
        <img class="fotoCabecera" src="img/iconoNombre.png">
    </head>
    <body>
        <% if (session.getAttribute("admin") == null && session.getAttribute("cliente") == null) { %>
            <%-- Si no está iniciada la sesión como administrador/cliente, se vuelve al prncipio --%>
            <meta http-equiv="refresh" content="0; url=index.html" />
        <% }%>
        
        <%@ page import="com.practicafinal.ventabilletes.bbdd.ModeloDatos" %>
        <%
            ModeloDatos bd = new ModeloDatos();
            bd.abrirConexion();
            
            ArrayList<Aeropuerto> vuelosOrigen = bd.getVuelosOrigen();
            ArrayList<Aeropuerto> vuelosDestino = bd.getVuelosDestino();
            bd.cerrarConexion();
        %>
          
        <div style="text-align: center;" class="artVuelos">
          <h1>Buscador de Vuelos</h1>
            <form action="BuscarVuelo" method="POST">

            <label for="miopcion">Opciones de Vuelo: </label>
            <input type="radio" name="miopcion" value="Ida-Vuelta"> Ida y Vuelta
            <input type="radio" name="miopcion" value="Solo-ida"> Solo ida
            <br>  
            <br>
            <select name="origen">
              <option selected value="0"> Elige lugar de origen </option>
                <% for (Aeropuerto origen : vuelosOrigen) { %>
                <option value="<%= origen.getIdAeropuerto() %>"><%= origen.getCiudad() %></option>
                <% } %>
            </select> 

            <select name="destino">
              <option selected value="0"> Elige lugar de destino</option>
                <% for (Aeropuerto destino : vuelosDestino) { %>
                <option value="<%= destino.getIdAeropuerto() %>"><%= destino.getCiudad() %></option>
                <% } %>
            </select> 
            <br>
            <br>

            <p>Salida: <input type="date" name="fecha" id="fromDate"> Vuelta: <input type="date" id="toDate" min=></p>
            Número de pasajeros 
            <p>Adultos:<input type="number" id="pasajerosAdultos" placeholder="+18" min="1" max="1" required>
              Adolescentes:<input type="number" id="pasajerosAdolescentes" placeholder="+14" min="0" max="0">
              Niños:<input type="number" id="pasajerosNinos" placeholder="+5" min="0" max="0">
            </p>
            <p><button>Buscar</button></p></form>
            <footer>En caso de querer cerrar sesión, pulse
              <a href="logout.jsp"> aquí</a>
            </footer>
        </div>
    </body>
</html>
