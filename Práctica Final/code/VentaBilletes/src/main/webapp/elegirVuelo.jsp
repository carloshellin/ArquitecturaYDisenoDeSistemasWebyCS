<%@page import="com.practicafinal.ventabilletes.bbdd.Aeropuerto"%>
<%@page import="com.practicafinal.ventabilletes.bbdd.ModeloDatos"%>
<%@page import="java.util.TimeZone"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.practicafinal.ventabilletes.bbdd.Ruta"%>
<!DOCTYPE html>
<html>
    <header>
        <title>Alcala Airlines | Selección fechas disponibles </title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <link rel="shortcut icon" href="img/Alcala airlines.png" type="image/x-icon">
        <img class="fotoCabecera" src="img/iconoNombre.png">
    </header>

    <body>
        <% if (session.getAttribute("admin") == null && session.getAttribute("cliente") == null) { %>
            <%-- Si no está iniciada la sesión como administrador/cliente, se vuelve al principio --%>
            <meta http-equiv="refresh" content="0; url=index.html" />
        <% }%>
        <article class="artVuelos">
            <form action="SeleccionarVuelo" method="post">
                <h1>Seleccione la fecha deseada entre las rutas disponibles</h1>
                <% ArrayList<Ruta> vuelos = (ArrayList<Ruta>) session.getAttribute("vuelos"); %>
                <% if (vuelos.size() == 0) { %> <h5>No existen vuelos para esas fechas. <a href="vuelos.jsp">Volver al buscador de vuelos</a>.</h5>
                <% } else { %>
                <h5>A continuación se muestran las fechas actualmente disponibles. Elija la que mejor se adapte a su petición.</h5>
                
                <%  ModeloDatos bd = new ModeloDatos();
                    bd.abrirConexion();
                    for (Ruta vuelo : vuelos) { %>
                <% 
                    Aeropuerto origen = bd.getAeropuerto(vuelo.getOrigen());
                    Aeropuerto destino = bd.getAeropuerto(vuelo.getDestino());
                    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Madrid"));
                    cal.setTime(vuelo.getFecha());
                %>
                    <p><input type="radio" name="idRuta" value="<%= vuelo.getIdRuta() %>"><%= origen.getCiudad() %> - <%= destino.getCiudad() %> Fecha:
                        <%= cal.get(Calendar.YEAR) %>-<%= cal.get(Calendar.MONTH) + 1 %>-<%= cal.get(Calendar.DAY_OF_MONTH) %></p>
                <% } %>
                <% bd.cerrarConexion(); %>
                <p><button>Seleccionar</button></p>
            </form>
            <% } %>
            <footer>En caso de querer cerrar sesión, pulse
                <a href="logout.jsp"> aquí</a>
            </footer>
            
        </article>
    </body>
</html>
