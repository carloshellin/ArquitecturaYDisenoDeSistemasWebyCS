package com.practicafinal.ventabilletes.servlets;

import java.io.IOException;

import com.practicafinal.ventabilletes.bbdd.ModeloDatos;
import com.practicafinal.ventabilletes.bbdd.AcompañanteBillete;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CrearAcompañanteBillete extends HttpServlet
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

        int Acompañante_idAcompañante = Integer.parseInt(request.getParameter("Acompañante_idAcompañante"));
        String Acompañante_Persona_numID = request.getParameter("Acompañante_Persona_numID"); 
        String Billete_localizador = request.getParameter("Billete_localizador");

        AcompañanteBillete acompañanteBillete = new AcompañanteBillete(Acompañante_idAcompañante, Acompañante_Persona_numID, Billete_localizador);

        if (bd.existeAcompañanteBillete(acompañanteBillete))
        {
            s.setAttribute("error", "El AcompañanteBillete ya existe."); 
            response.sendRedirect(response.encodeRedirectURL("error.jsp"));
        }
        else
        {
            bd.insertarAcompañanenteBillete(acompañanteBillete);
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
