package com.practicafinal.ventabilletes.servlets;

import com.practicafinal.ventabilletes.bbdd.ModeloDatos;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Estadisticas extends HttpServlet {
    private enum Tipo {
        HombreMujer,
        Menores,
        RangoEdades,
        NumeroVuelos
    }
    
    private ModeloDatos bd;

    @Override
    public void init(ServletConfig config) throws ServletException
    {
        bd = new ModeloDatos();
        bd.abrirConexion();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Tipo tipo = Tipo.valueOf(request.getParameter("tipo"));
        
        String array = "";
        
        switch (tipo)
        {
            case HombreMujer:
            {
                array = "[" + bd.getNumHombres() + "," + bd.getNumMujeres() + "]";
            } break;
            
            case Menores:
            {
                ArrayList<Date> fechaNacPersonas = bd.getFechaNacPersonas();
                int menores = 0;
                int mayores = 0;
                for (Date fechaNac : fechaNacPersonas)
                {
                    try
                    {
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
                        String strDate = dateFormat.format(fechaNac); 
                        if (bd.esMayorEdad(strDate))
                        {
                            mayores++;
                        }
                        else
                        {
                            menores++;
                        }
                    }
                    catch (ParseException ex)
                    {
                        Logger.getLogger(Estadisticas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                array = "[" + menores + "," + mayores + "]";
            } break;
            
            case RangoEdades:
            {
                ArrayList<Date> fechaNacPersonas = bd.getFechaNacPersonas();
                try {
                    int[] contadorEdades = bd.rangoEdad(fechaNacPersonas);
                    array = "[" + contadorEdades[0] + "," + contadorEdades[1] + "," + contadorEdades[2] + "," + contadorEdades[3] + "]";
                } catch (ParseException ex) {
                    Logger.getLogger(Estadisticas.class.getName()).log(Level.SEVERE, null, ex);
                }
            } break;
            
            case NumeroVuelos:
            {
                int[] numVuelos = bd.getNumVuelos();
                array = "[" + numVuelos[0];
                for (int i = 1; i < numVuelos.length; i++)
                {
                    array += "," + numVuelos[i];
                }
                array += "]";
            } break;
        }
        
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(array);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
