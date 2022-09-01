package com.practicafinal.ventabilletes.servlets;

import com.practicafinal.ventabilletes.bbdd.ModeloDatos;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ModificarPrecioRuta extends HttpServlet {
    private ModeloDatos bd;

    @Override
    public void init()
    {
        bd = new ModeloDatos();
        bd.abrirConexion();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession s = request.getSession(true); 
        
        int idRuta = Integer.parseInt(request.getParameter("idRuta"));
        int precio = Integer.parseInt(request.getParameter("precio"));

        bd.ModificarPrecioRuta(idRuta, precio);

        s.setAttribute("accion", "cambiado");
        s.setAttribute("tipo", "precio");
        s.setAttribute("nombre", String.valueOf(precio));
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



