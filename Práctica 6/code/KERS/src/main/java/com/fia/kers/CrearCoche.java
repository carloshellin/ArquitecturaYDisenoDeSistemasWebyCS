package com.fia.kers;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CrearCoche extends HttpServlet
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
        
        String nombre = request.getParameter("nombre");
        Integer kwcurva = Integer.parseInt(request.getParameter("kwcurva"));
        
        s.setAttribute("tipo", "coche");
        s.setAttribute("nombre", nombre);
        
        if (nombre.isEmpty())
        {
            s.setAttribute("error", "El campo nombre se encuentra vacío."); 
        }
        else if (nombre.length() > 20)
        {
            s.setAttribute("error", "El campo nombre excede la longitud de 20."); 
        }
        else if ((kwcurva < 4 || kwcurva > 10))
        {
            s.setAttribute("error", "El campo kW por curva está fuera de rango."); 
        }
        
        if (s.getAttribute("error") != null)
        {
            response.sendRedirect(response.encodeRedirectURL("error.jsp"));
            return;
        }
        
        if (bd.existeCoche(nombre))
        { 
            bd.actualizarCoche(nombre, kwcurva); 
            s.setAttribute("accion", "actualizado");
            response.sendRedirect(response.encodeRedirectURL("exito.jsp"));
        } 
        else 
        {
            bd.insertarCoche(nombre, kwcurva);
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
