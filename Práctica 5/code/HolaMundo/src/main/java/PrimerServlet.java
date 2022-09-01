/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class PrimerServlet extends HttpServlet {
    @Override
    public void doGet (HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
    {
        res.setContentType("text/html");
        try (PrintWriter out = new PrintWriter(res.getOutputStream())) {
            out.println("<html>");
            out.println("<head><title>HolaMundoServlet</title></head>");
            out.println("<body>");
            out.println("<h1><center>Hola Mundo desde el servidor WEB</center></h1>");
            out.println("</body></html>");
        }
    }
    @Override
    public String getServletInfo()
    {
        return "Crea una página HTML que dice HolaMundo";
    }
}

