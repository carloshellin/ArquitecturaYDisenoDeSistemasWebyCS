package com.practicafinal.ventabilletes.servlets;

import com.practicafinal.ventabilletes.bbdd.*;
import com.practicafinal.ventabilletes.bbdd.ModeloDatos;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CrearAvion extends HttpServlet
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
        
        String modelo = new String(request.getParameter("modelo").getBytes("ISO-8859-1"),"UTF-8");
        int numPlazas = Integer.parseInt(request.getParameter("capacidad"));
        int clase = Integer.parseInt(request.getParameter("clase"));
        
        Avion avion = new Avion(modelo, numPlazas);
      
        bd.insertarAvion(avion);
        
        Avion avionIntroducido = bd.getAviones().get(bd.getAviones().size()-1);
        ClaseAvion claseAvion = new ClaseAvion(clase,avionIntroducido.getIdAvion(),numPlazas);
        
        bd.insertarClaseAvion(claseAvion);
        
        s.setAttribute("accion", "añadido");
        s.setAttribute("tipo", "avion");
        s.setAttribute("nombre", avion.getModelo());
        response.sendRedirect(response.encodeRedirectURL("exito.jsp"));
      
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
