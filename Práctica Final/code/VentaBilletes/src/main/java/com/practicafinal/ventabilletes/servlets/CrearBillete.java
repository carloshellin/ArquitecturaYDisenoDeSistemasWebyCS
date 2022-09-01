package com.practicafinal.ventabilletes.servlets;

import com.practicafinal.ventabilletes.bbdd.ModeloDatos;
import com.practicafinal.ventabilletes.bbdd.Billete;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CrearBillete extends HttpServlet
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

        String localizador = request.getParameter("localizador");
        int BilleteRuta_idBilleteRuta = Integer.parseInt(request.getParameter("BilleteRuta_idBilleteRuta"));
        int Operacion_idOperacion = Integer.parseInt(request.getParameter("Operacion_idOperacion"));
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date Operacion_fecha = null;
        try
        {
            Operacion_fecha = format.parse(request.getParameter("Operacion_fecha")); 
        }
        catch (ParseException ex)
        {
            System.out.println("Error al parsear Operacion_fecha: " + ex.getMessage());
        }

        Billete billete = new Billete(localizador, BilleteRuta_idBilleteRuta, Operacion_idOperacion, Operacion_fecha);

        if (bd.existeBillete(billete))
        {
            s.setAttribute("error", "El billete ya existe.");
            response.sendRedirect(response.encodeRedirectURL("error.jsp"));
        }
        else
        {
            bd.insertarBillete(billete);
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
