<%-- 
    Document   : sesionEje1
    Created on : 25 nov. 2021, 13:00:20
    Author     : bianc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Ejemplo de Sesión</title>
    </head>
    <body>
        <center> <h1>Ejemplo de Sesión</h1>
        Hola, <%=session.getAttribute("Nombre")%>
        Bienvenido a la página 1
        </center>
    </body>
</html>
