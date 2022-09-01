<%@ page contentType="text/html;charset=GB2312"%>  
<%@page import="java.sql.*"%>  
<HTML> 
    <head> 
        <title>Datos</title>
    </head> 
    <body>
        <h1>Respuesta</h1>
       <%
        String nombre = request.getParameter("nombre");
        out.println("El profesor: " + nombre + " da clase a los siguientes alumnos: ");
        Connection conn=null;  
            try
            {  
              Class.forName("org.apache.derby.jdbc.ClientDriver");  
              String url="jdbc:derby://localhost:1527/Clase";  
              conn=DriverManager.getConnection(url,"administrador","admin");  
              Statement st=conn.createStatement();  
              ResultSet rs=st.executeQuery("Select Asistencia.DNI_ALUMNO from Asignatura INNER JOIN Profesor on DNI_PROFESOR = Profesor.DNI INNER JOIN Asistencia ON Asignatura.Id_Asignatura = Asistencia.Id_Asignatura WHERE Profesor.Nombre = '" + nombre + "'" );
              while(rs.next())  
              {  
              out.println(" "+rs.getString(1));  
              }  
            }  
            catch(Exception e)  
            {  
                    out.println("Error intentando establecer la conexi¨®n: ");  
                    out.println(e.getMessage());  
            }          
           %>
    </body>
</HTML>




