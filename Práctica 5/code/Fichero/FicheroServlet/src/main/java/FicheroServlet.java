import javax.servlet.http.HttpServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.FileSystems;
import javax.ws.rs.Path;

public class FicheroServlet extends HttpServlet
{
    StringBuffer mensaje = null;
    FileOutputStream fos = null;
    String[] strTEXTO;
    
    @Override
    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
        FileInputStream fis = null;
        //ATENCION: INTRODUZCA LA RUTA ABSOLUTA DEL FICHERO. Ejemplo: C:/Users/dferr/OneDrive/Desktop/fichero.txt
        String ruta = "C:/Users/dferr/OneDrive/Desktop/";
        try
        {   
            
            fis = new FileInputStream(ruta + "fichero.txt");
        }catch(java.io.FileNotFoundException e){e.printStackTrace();}
        
        mensaje = new StringBuffer();
        try
        {
            int caracter;
            while ( (caracter = fis.read()) != -1 )
            {
                mensaje.append((char)caracter);
            }
            fis.close();
            fos = new FileOutputStream(ruta + "log.txt");
        }catch(java.io.IOException e){e.printStackTrace();}
    }
    
    @Override
    public void service( HttpServletRequest peticion,HttpServletResponse respuesta )throws ServletException, IOException
    {
        respuesta.setContentType("text/html;charset=UTF-8");
        strTEXTO = peticion.getParameterValues("TEXTO");  
        try (ServletOutputStream out = respuesta.getOutputStream()) {
            out.println("<p>"+mensaje+"</p>");
            out.println("<p>Su nombre es: "+strTEXTO[0]+"</p>");
        }
        registrar();
    }
    
    @Override
    public void destroy()
    {
        try
        {
            fos.close();
        }catch(java.io.IOException e){e.printStackTrace();}
    }
        
    public synchronized void registrar()
    {
        try
        {
           // fos.write(strTEXTO[0].getBytes());
            fos.write(strTEXTO[0].getBytes());
            destroy();
        }catch(java.io.IOException e){e.printStackTrace();}
    }
}