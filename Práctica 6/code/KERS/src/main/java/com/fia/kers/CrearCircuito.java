package com.fia.kers;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CrearCircuito extends HttpServlet
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
        String ciudad = request.getParameter("ciudad");
        String pais = request.getParameter("pais");
        Integer vueltas = Integer.parseInt(request.getParameter("vueltas"));
        Integer longitud = Integer.parseInt(request.getParameter("longitud"));
        Integer curvas = Integer.parseInt(request.getParameter("curvas"));
        
        s.setAttribute("tipo", "circuito");
        s.setAttribute("nombre", nombre);
        
        if (nombre.isEmpty() || ciudad.isEmpty() || pais.isEmpty())
        {
            s.setAttribute("error", "Hay algún campo que se encuentra vacío."); 
        }
        else if (nombre.length() > 20 || ciudad.length() > 20 || pais.length() > 20)
        {
            s.setAttribute("error", "Hay algún campo de texto que excede la longitud de 20."); 
        }
        else if ((vueltas < 40 || vueltas > 80)
                || (longitud < 3000 || longitud > 9000)
                || (curvas < 6 || curvas > 20))
        {
            s.setAttribute("error", "Hay algún campo numérico que está fuera de rango."); 
        }
        
        if (s.getAttribute("error") != null)
        {
            response.sendRedirect(response.encodeRedirectURL("error.jsp"));
            return;
        }
        
        if (bd.existeCircuito(nombre))
        { 
            bd.actualizarCircuito(nombre, ciudad, pais, vueltas, longitud, curvas); 
            s.setAttribute("accion", "actualizado");
            response.sendRedirect(response.encodeRedirectURL("exito.jsp"));
        } 
        else 
        {
            bd.insertarCircuito(nombre, ciudad, pais, vueltas, longitud, curvas);
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
