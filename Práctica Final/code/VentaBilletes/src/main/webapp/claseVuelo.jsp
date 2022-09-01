<%@page import="com.practicafinal.ventabilletes.bbdd.Billete"%>
?<%@page import="java.util.TimeZone"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.time.LocalTime"%>
<%@page import="java.time.Duration"%>
<%@page import="com.practicafinal.ventabilletes.bbdd.Aeropuerto"%>
<%@page import="com.practicafinal.ventabilletes.bbdd.Aeropuerto"%>
<%@page import="com.practicafinal.ventabilletes.bbdd.ModeloDatos"%>
<%@page import="com.practicafinal.ventabilletes.bbdd.Ruta"%>
?<!DOCTYPE html>
<html>
    <header>
        <title>Alcala Airlines | Selección datos ida</title>
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
        <div class="divVuelos">
            <article class="artSeleccion">
                <h1 class="seleccion">Seleccionar - Tarifa de vuelo</h1>
                <p>Las tarifas que se muestran en el calendario son las de precio más bajo, impuestos incluidos.</p>
                        <summary>Detalles sobre el vuelo</summary>
                        <%
                            ModeloDatos bd = new ModeloDatos();
                            bd.abrirConexion();
                            
                            Ruta vuelo = (Ruta) session.getAttribute("vuelo");
                            Billete billete = (Billete) session.getAttribute("billete");
                            Aeropuerto origen = bd.getAeropuerto(vuelo.getOrigen());
                            Aeropuerto destino = bd.getAeropuerto(vuelo.getDestino());
                            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Madrid"));
                            cal.setTime(vuelo.getFecha());
                            
                            bd.cerrarConexion();
                        %>
                        <p><label>Origen: <%= origen.getCiudad() %></label></p>
                        <p><label>Destino: <%= destino.getCiudad() %></label> </p>
                        <p><label>Duracion: <%= LocalTime.MIN.plus(Duration.ofMinutes(vuelo.getDuraction())).toString() %></label></p>
                        <p><label>Fecha de Salida: <%= cal.get(Calendar.DAY_OF_MONTH) %>-<%= cal.get(Calendar.MONTH) + 1 %>-<%= cal.get(Calendar.YEAR) %></label></p>
                        <p><label>Localización: <%= billete.getLocalizador() %> </label> </p>
                <footer>En caso de querer cerrar sesión, pulse
                    <a href="logout.jsp"> aquí</a>
                </footer>
            </article>

            <img src="img/caractClases.png">
            <button class="bc" name="turista">
                <img src="img/turista.png">
            </button>
            <button class="bc" name="primera">
                <img src="img/primera.png">
            </button>
            <button class="bc" name="business">
                <img src="img/business.png">
            </button>
            <button class="bc" name="premium">
                <img src="img/premium.png">
            </button>
            <form action="pago.jsp">
            <p><button>Siguiente</button></p></form>
        </div>
        
        
    </body>
</html>
