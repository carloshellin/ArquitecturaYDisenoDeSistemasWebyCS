package com.fia.kers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CalcularGanancia extends HttpServlet
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
        
        String circuito = request.getParameter("circuitos");
        String coche = request.getParameter("coches");

        Integer kwcurva = bd.obtenerCoche(coche);

        Integer[] array = bd.obtenerCircuito(circuito);
        Integer vueltas = array[0];
        Integer curvas = array[1];

        Integer ganancia = kwcurva * vueltas * curvas;
        
        s.setAttribute("ganancia", ganancia);
        s.setAttribute("kwcurva", kwcurva);
        s.setAttribute("vueltas", vueltas);
        s.setAttribute("curvas", curvas);
        response.sendRedirect(response.encodeRedirectURL("resultado.jsp"));
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
