<%-- 
    Document   : sesionEje
    Created on : 25 nov. 2021, 11:36:11
    Author     : bianc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head> <title> Ejemplo de Sesión </title> </head>
    <body>
        <%
        String val = request.getParameter("nombre");
        if (val != null) session.setAttribute("Nombre",val);
        %>
        <center> <h1>Ejemplo de Sesión</h1>
        Donde quieres ir!!!
        <a href="sesionEje1.jsp">Ir a Página 1</a>
        <a href="sesionEje2.jsp">Ir a Página 2</a>
    </body>
</html>
