<!DOCTYPE html>
<html>
    <header>
        <title>Alcala Airlines | Realización de Pagos</title>
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
        <article><form action="Pago" method="post">
            <h1>Pague su reserva de forma segura</h1>
            <h3>Introduzca sus datos para efectuar el pago</h3>
            <p>Número de tarjeta <br>
            <input type="text" name="numTarjeta" placeholder="0000 0000 0000 0000" required pattern="[0-9 ]{19}" ></p>
            <p>Fecha de caducidad <br>
            <input type="text" name="caducidad" placeholder="00/00" required pattern="[0-9]{2}[/20-5]{3}"></p>
            <p>Código de seguridad/CVV <br>
            <input type="text" name="CVV" placeholder="000" required pattern="[0-9]{3}"></p> 
            <p><input type="checkbox" required> 
                Acepto las Condiciones de reserva, Condiciones del Billete y Generales de Transporte, Condiciones de mercancías peligrosas.
            </p>
            <p><input type="checkbox" required> Sí, acepto la Política de privacidad.</p>
            <button onclick="showMessage()">Pagar</button>
            <footer>En caso de querer cerrar sesión, pulse
                <a href="logout.jsp"> aquí</a>
            </footer>
            </form> 
        </article>

        <div class="divDialogPago" id="acompDialog" hidden>
            <h2>Su pago se ha realizado correctamente</h2>
            <form action="vuelos.jsp">
            <button>Finalizar proceso</button></form>
        </div>

        <script>
            function showMessage(){
                var x = document.getElementById('acompDialog');
                if (x.style.display === "none") {
                    x.style.display = "block";
                } else {
                    x.style.display = "none";
                }
            }
        </script>

    </body>

</html>
