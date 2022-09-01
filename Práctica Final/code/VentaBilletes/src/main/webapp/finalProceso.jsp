<%@page import="com.practicafinal.ventabilletes.bbdd.Tasas"%>
<%@page import="com.practicafinal.ventabilletes.bbdd.BilleteRuta"%>
<%@page import="com.practicafinal.ventabilletes.bbdd.TarjetaBancaria"%>
<%@page import="com.practicafinal.ventabilletes.bbdd.Persona"%>
<%@page import="com.practicafinal.ventabilletes.bbdd.Cliente"%>
?<%@page import="java.time.LocalTime"%>
<%@page import="java.time.Duration"%>
ï»¿<%@page import="java.util.TimeZone"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.practicafinal.ventabilletes.bbdd.Aeropuerto"%>
<%@page import="com.practicafinal.ventabilletes.bbdd.Billete"%>
<%@page import="com.practicafinal.ventabilletes.bbdd.Ruta"%>
<%@page import="com.practicafinal.ventabilletes.bbdd.ModeloDatos"%>
?<!DOCTYPE html>
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
            <%
                ModeloDatos bd = new ModeloDatos();
                bd.abrirConexion();

                Ruta vuelo = (Ruta) session.getAttribute("vuelo");
                Billete billete = (Billete) session.getAttribute("billete");
                Aeropuerto origen = bd.getAeropuerto(vuelo.getOrigen());
                Aeropuerto destino = bd.getAeropuerto(vuelo.getDestino());
                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Madrid"));
                cal.setTime(vuelo.getFecha());
                String correo = (String) session.getAttribute("cliente");
                Cliente cliente = bd.getCliente(correo);
                Persona persona = bd.getPersona(cliente.getPersona_numID());
                
                Calendar cal2 = Calendar.getInstance(TimeZone.getTimeZone("Europe/Madrid"));
                cal2.setTime(persona.getFechaNacimiento());
                
                TarjetaBancaria tarjetaBancaria = (TarjetaBancaria) session.getAttribute("tarjetaBancaria");
                
                BilleteRuta billeteRuta = bd.getBilleteRuta(vuelo.getIdRuta());
                Tasas tasasOrigen = bd.getTasas(origen.getIdAeropuerto());
                Tasas tasasDestino = bd.getTasas(destino.getIdAeropuerto());
                
                float tasas = tasasOrigen.getGestion() + tasasOrigen.getSeguridad() + tasasOrigen.getCombustible()
                        + tasasDestino.getGestion() + tasasDestino.getSeguridad() + tasasDestino.getCombustible();
                
                float precio = (billeteRuta.getPrecio() + tasas) * (billeteRuta.getImpuestos() + 1);

                bd.cerrarConexion();
            %>
            <h1>Resumen de su compra</h1>
            <h3></h3>
            <fieldset>
                <legend> Datos de la ruta </legend> 
                <label>Fecha: <%= cal.get(Calendar.DAY_OF_MONTH) %>-<%= cal.get(Calendar.MONTH) + 1 %>-<%= cal.get(Calendar.YEAR) %></label><br>
                <label>Duración: <%= LocalTime.MIN.plus(Duration.ofMinutes(vuelo.getDuraction())).toString() %></label><br>
                <label>Origen: <%= origen.getCiudad() %></label><br>
                <label>Destino: <%= destino.getCiudad() %></label>
            </fieldset>
            <fieldset>
                <legend> Datos del billete </legend> 
                <label>Localizador: <%= billete.getLocalizador() %></label><br>
                <label>Precio: <%= precio %> &euro;</label><br>
                <label>Impuestos: <%= billeteRuta.getImpuestos() * 100 %>%</label>
            </fieldset>
            <fieldset>
                <legend> Datos del pasajero </legend> 
                <label>Tipo de Documento: <%= persona.getTipoIdentificacion() %></label> <label>Nº Documento: <%= persona.getNumID() %></label><br>
                <label>Nombre: <%= persona.getNombre() %></label> 
                <br><label> Apellido1: <%= persona.getApellido1() %></label><label> Apellido2: <%= persona.getApellido2() %></label><br>
                <label>Fecha de Nacimiento: <%= cal2.get(Calendar.DAY_OF_MONTH) %>-<%= cal2.get(Calendar.MONTH) + 1 %>-<%= cal2.get(Calendar.YEAR) %></label> <br>
                <label> Sexo: <%= persona.getSexo() %></label>  
            </fieldset>
            <fieldset>
                <legend> Datos de la tarjeta de crédito bancaria </legend> 
                <label>Nº de cuenta: <%= tarjetaBancaria.getNumTarjeta() %></label><br>
                <label>Mes caducidad: <%= tarjetaBancaria.getMesCaducidad() %></label> // <label>Año caducidad: <%= tarjetaBancaria.getAñoCaducidad() %></label><br>
                <label>Código de seguridad: <%= tarjetaBancaria.getCVV() %></label>
            </fieldset>
            <form action="FinalizarCompra">
                <p><button>Finalizar</button></p></form>
            <footer>En caso de querer cerrar sesión, pulse
                <a href="logout.jsp"> aquí</a>
            </footer>
        </article>

    </body>

</html>
