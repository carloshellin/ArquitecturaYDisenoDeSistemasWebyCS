<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.practicafinal.ventabilletes.bbdd.*"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Alcala Airlines | Zona Administrador</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <link rel="shortcut icon" href="img/Alcala airlines.png" type="image/x-icon">
        <img class="fotoCabecera" src="img/iconoNombre.png">
    </head>
    <body>
        <% if (session.getAttribute("admin") == null) { %>
        <%-- Si no está iniciada la sesión como administrador, se da acceso denegado --%>
        
            <h1>Acceso denegado.</h1>
            <h2 style="text-align: center;">No eres administrador.</h2>
            <h3 class="jspH3"><a href="index.html">Volver a inicio</a></h3>
            
        <% } else { %>
        
        <%@ page import="com.practicafinal.ventabilletes.bbdd.ModeloDatos" %>
        <%
            ModeloDatos bd = new ModeloDatos();
            bd.abrirConexion();
            
            ArrayList<Aeropuerto> aeropuertos = bd.getAeropuertos();

            ArrayList<Avion> aviones = bd.getAviones();
            ArrayList<Clase> clases = bd.getClases();
            
            bd.cerrarConexion();
        %>
        
         <h1>Página de administración</h1>
        
        <nav class="menu">
            <ul>
                <li><a> Añadir nuevos elementos</a>
                    <ul>
                        <li><a href="javascript:popDialog('rutaDialog')"> Añadir nuevas rutas</a></li>
                        <li><a href="javascript:popDialog('avionDialog')"> Añadir nuevos aviones</a></li>
                        <li><a href="javascript:popDialog('aeropuertoDialog')"> Añadir nuevos aeropuertos</a></li>
                    </ul>
                </li>
                <li><a> Modificar vuelo</a>
                    <ul>
                        <li><a href="javascript:popDialog('cambiarOrigenDialog')">Cambiar Origen</a></li>
                        <li><a href="javascript:popDialog('cambiarDestinoDialog')">Cambiar Destino</a></li>
                        <li><a href="javascript:popDialog('cambiarPrecioDialog')">Cambiar Precio</a></li>
                    </ul>
                </li>
                <li><a href="estadisticas.jsp"> Obtener productos estadísticos </a></li>
            </ul>
        </nav>

        <footer class="footerAdmin">En caso de querer cerrar sesión, pulse
            <a href="logout.jsp"> aquí</a>
        </footer>

        <div class="divDialog" id="rutaDialog" hidden>
            <h1>Añadir una nueva ruta</h1>
            <form action="CrearRuta" method="post">
                <p><select name="origen" required>
                    <option selected disabled value=""> Origen </option>
                    <% for (Aeropuerto aeropuerto : aeropuertos) { %>
                    <option value="<%= aeropuerto.getIdAeropuerto() %>"><%= aeropuerto.getCiudad() %></option>
                    <% } %>
                </select> 
                <select name="destino" required>
                    <option selected disabled value=""> Destino </option>
                    <% for (Aeropuerto aeropuerto : aeropuertos) { %>
                    <option value="<%= aeropuerto.getIdAeropuerto() %>"><%= aeropuerto.getCiudad() %></option>
                    <% } %>
                </select> 
                <select name="avion" required>
                    <option selected disabled value=""> Avion </option>
                    <% for (Avion avion : aviones) { %>
                    <option value="<%= avion.getIdAvion() %>"><%= avion.getModelo()+ " " +avion.getIdAvion() %></option>
                    <% } %>
                </select></p>
                
                <p><input type="datetime-local" name="fecha"  step="1" placeholder="Fecha" required>
                <input type="number" name="duracion" placeholder="Duración" required></p>
                <button onclick="popDialog('rutaDialog')">Añadir ruta</button>
            </form>
        </div>

        <div class="divDialog" id="avionDialog" hidden>
            <h1>Añadir un nuevo modelo de avión</h1>
            <form action="CrearAvion" method="post">
                <p><input type="text" name="modelo" placeholder="Modelo" required>
            <input type="number" name="capacidad" placeholder="Capacidad" min="75" required>
            <select name="clase" required>
                <option selected value="0"> Clase </option>
                    <% for (Clase clase : clases) { %>
                    <option value="<%= clase.getIdClase() %>"><%= clase.getNombre() %></option>
                    <% } %>
            </select></p>
            <button onclick="popDialog('avionDialog')">Añadir avión</button>
            </form>
        </div>

        <div class="divDialog" id="aeropuertoDialog" hidden>
            <h1>Añadir un nuevo aeropuerto de ruta</h1>
            <form action="CrearAeropuerto" method="post">
            <p><input type="text" name="pais" placeholder="País" required>
            <input type="text" name="ciudad" placeholder="Ciudad" required></p>
            <p><input type="text" name="direccion" placeholder="Dirección" required>
            <input type="text" name="nombre" placeholder="Nombre" required></p>
            <p><datalist id="listaPrefijos">
                <option value="+34">
                <option value="+40">    
                </datalist>
                <input type="tel" id="prefTelfRegistro" name="prefijo" placeholder="Prefijo*" list="listaPrefijos" required> 
                <input type="tel" name="telefono" placeholder="Teléfono*" required pattern="[0-9]{9}" minlength="9" maxlength="9">
            </p>
            <button onclick="popDialog('aeropuertoDialog')">Añadir aeropuerto</button>
            </form>
        </div>
    
        <div class="divDialog" id="cambiarOrigenDialog" hidden>
            <h1>Modificar el origen de ruta</h1>
            <form action="ModificarOrigenRuta" method="POST">
                <input type="number" name="idRuta" placeholder="idRuta" required>
                <br><br>
                <input type="number" name="origen" placeholder="Origen a cambiar" required>
                <br><br>
                <button onclick="popDialog('cambiarOrigenDialog')">Cambiar destino origen</button>
            </form>
        </div>
    
        <div class="divDialog" id="cambiarDestinoDialog" hidden>
            <h1>Modificar el destino de ruta</h1>
            <form action="ModificarDestinoRuta" method="POST">
                <input type="number" name="idRuta" placeholder="idRuta" required>
                <br><br>
                <input type="number" name="destino" placeholder="Destino a cambiar" required>
                <button onclick="popDialog('cambiarDestinoDialog')">Cambiar destino final</button>
                <br><br>
            </form>    
        </div>
	      
        <div class="divDialog" id="cambiarPrecioDialog" hidden>
            <h1>Modificar el precio de la ruta</h1>
            <form action ="ModificarPrecioRuta" method="POST">
                <input type="number" name="idRuta" placeholder="idRuta" required>
                <br><br>
                <input type="number" name="precio" placeholder="Precio a cambiar" min="1" required>
                <br><br>
                <button onclick="popDialog('cambiarPrecioDialog')">Cambiar precio ruta</button>
            </form>
        </div>
    
        <script>
            function popDialog(parameter){
                var x = document.getElementById(parameter);
                if (x.style.display === "none") {
                    x.style.display = "block";
                } else {
                    x.style.display = "none";
                }
            }
        </script>
    </body>

         <% }%>
</html>
