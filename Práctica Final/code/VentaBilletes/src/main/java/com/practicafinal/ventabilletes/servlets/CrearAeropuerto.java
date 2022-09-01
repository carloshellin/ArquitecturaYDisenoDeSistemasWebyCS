package com.practicafinal.ventabilletes.servlets;

import com.practicafinal.ventabilletes.bbdd.Aeropuerto;
import com.practicafinal.ventabilletes.bbdd.ModeloDatos;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CrearAeropuerto extends HttpServlet
{
    private ModeloDatos bd;

    @Override
    public void init(ServletConfig config) throws ServletException
    {
        bd = new ModeloDatos(); 
        bd.abrirConexion();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession s = request.getSession(true); 
        
        String nombre = new String(request.getParameter("nombre").getBytes("ISO-8859-1"),"UTF-8");
        String direccion = new String(request.getParameter("direccion").getBytes("ISO-8859-1"),"UTF-8");
        String ciudad = new String(request.getParameter("ciudad").getBytes("ISO-8859-1"),"UTF-8");
        String pais = new String(request.getParameter("pais").getBytes("ISO-8859-1"),"UTF-8");
        String telefono = request.getParameter("telefono");
        String prefijo = request.getParameter("prefijo");
        
        Aeropuerto aeropuerto = new Aeropuerto(nombre, direccion, ciudad, pais,
                prefijo + " " + telefono);
        
        if (bd.existeAeropuerto(aeropuerto))
        { 
            s.setAttribute("error", "El aeropuerto ya existe."); 
            response.sendRedirect(response.encodeRedirectURL("error.jsp"));
        } 
        else 
        {
            bd.insertarAeropuerto(aeropuerto);
            s.setAttribute("accion", "insertado");
             s.setAttribute("tipo", "aeropuerto");
            s.setAttribute("nombre", aeropuerto.getNombre() + " de " + aeropuerto.getCiudad() + ", " + aeropuerto.getPais());
            response.sendRedirect(response.encodeRedirectURL("exito.jsp"));
        }        
    }

    @Override
    public void destroy()
    {
        bd.cerrarConexion();
        super.destroy();
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
