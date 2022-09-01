package com.practicafinal.ventabilletes.servlets;

import com.practicafinal.ventabilletes.bbdd.ModeloDatos;
import com.practicafinal.ventabilletes.bbdd.TarjetaBancaria;
import java.io.IOException;
import java.math.BigInteger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CrearTarjetaBancaria extends HttpServlet 
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
        
        BigInteger numTarjeta = new BigInteger(request.getParameter("numTarjeta"));
        int mesCaducidad = Integer.parseInt(request.getParameter("mesCaducidad"));
        int añoCaducidad = Integer.parseInt(request.getParameter("añoCaducidad"));
        int CVV = Integer.parseInt(request.getParameter("CVV"));
        int Cliente_idCliente = Integer.parseInt(request.getParameter("Cliente_idCliente"));
        
        TarjetaBancaria tarjetaBancaria = new TarjetaBancaria(numTarjeta, mesCaducidad, añoCaducidad, CVV, Cliente_idCliente);

        if (bd.existeTarjetaBancaria(tarjetaBancaria))
        {
            s.setAttribute("error", "La tarjeta bancaria ya existe.");
            response.sendRedirect(response.encodeRedirectURL("error.jsp"));
        }
        else
        {
            bd.insertarTarjetaBancaria(tarjetaBancaria);
            s.setAttribute("accion", "insertado");
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
