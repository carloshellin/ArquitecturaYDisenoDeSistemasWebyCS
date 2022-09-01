package com.practicafinal.ventabilletes.servlets;

import com.practicafinal.ventabilletes.bbdd.ModeloDatos;
import com.practicafinal.ventabilletes.bbdd.Persona;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CrearPersona extends HttpServlet {

    private ModeloDatos bd;

    @Override
    public void init(ServletConfig config) throws ServletException {
        bd = new ModeloDatos();
        bd.abrirConexion();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession s = request.getSession(true);

        String numID = request.getParameter("numID");
        String tipoIdentificacion = request.getParameter("tipoIdentificacion");
        String nombre = request.getParameter("nombre");
        String apellido1 = request.getParameter("apellido1");
        String apellido2 = request.getParameter("apellido");
        String sexo = request.getParameter("sexo");

        String fechaNacimiento = request.getParameter("fechaNacimiento");

        try {
            Persona persona = new Persona(numID, tipoIdentificacion, nombre,
                    apellido1, apellido2, fechaNacimiento, sexo);

            if (bd.existePersona(persona)) {
                s.setAttribute("error", "La persona ya existe.");
                response.sendRedirect(response.encodeRedirectURL("error.jsp"));
            } else {
                bd.insertarPersona(persona);
                s.setAttribute("accion", "insertado");
                response.sendRedirect(response.encodeRedirectURL("exito.jsp"));
            }
        } catch (ParseException pe) {
            System.out.println("Error de parseo: " + pe.getMessage());
        }

    }

    @Override
    public void destroy() {
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
