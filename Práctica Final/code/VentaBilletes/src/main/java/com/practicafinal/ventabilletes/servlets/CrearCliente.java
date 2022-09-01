package com.practicafinal.ventabilletes.servlets;

import com.practicafinal.ventabilletes.bbdd.*;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class CrearCliente extends HttpServlet {

    private ModeloDatos bd;

    @Override
    public void init(ServletConfig config) throws ServletException {
        bd = new ModeloDatos();
        bd.abrirConexion();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession s = request.getSession(true);

        String nombre = new String(request.getParameter("nombre").getBytes("ISO-8859-1"),"UTF-8");
        String apellido1 = new String(request.getParameter("apellido1").getBytes("ISO-8859-1"),"UTF-8");
        String apellido2 = new String(request.getParameter("apellido2").getBytes("ISO-8859-1"),"UTF-8");

        String fechaNac = request.getParameter("fechaNacimiento");

        String tipoID = request.getParameter("tipoID");
        String tipoIDString = "";
        if (tipoID.equals("1")) {
            tipoIDString = "DNI";
        } else if (tipoID.equals("2")) {
            tipoIDString = "NIE";
        } else if (tipoID.equals("3")) {
            tipoIDString = "Pasaporte";
        }

        String numID = request.getParameter("numID");

        String tratamiento = request.getParameter("Tratamiento");
        String sexo = "";

        if (tratamiento.equals("1")) {
            sexo = "Hombre";
        } else {
            sexo = "Mujer";
        }

        String correo = new String(request.getParameter("correo").getBytes("ISO-8859-1"),"UTF-8");
        String contrasenna = new String(request.getParameter("contrasenna").getBytes("ISO-8859-1"),"UTF-8");
        String prefijo = request.getParameter("prefijo");
        String telefono = request.getParameter("telefono");

        try {
            Persona persona = new Persona(numID, tipoIDString, nombre, apellido1, apellido2, fechaNac, sexo);

            Cliente cliente = new Cliente(correo, contrasenna, prefijo + " " + telefono, numID);

            if (bd.existeCliente(cliente)) {
                s.setAttribute("error", "El cliente ya existe.");
                s.setAttribute("tipo", "cliente");
                s.setAttribute("nombre", persona.getNombre() + " " + persona.getApellido1() + " " + persona.getApellido2());
                response.sendRedirect(response.encodeRedirectURL("errorRegistro.jsp"));
            } else {
                bd.insertarPersona(persona);
                bd.insertarCliente(cliente);
                s.setAttribute("accion", "registrado");
                s.setAttribute("tipo", "cliente");
                s.setAttribute("nombre", persona.getNombre() + " " + persona.getApellido1() + " " + persona.getApellido2());
                response.sendRedirect(response.encodeRedirectURL("exitoRegistro.jsp"));
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
