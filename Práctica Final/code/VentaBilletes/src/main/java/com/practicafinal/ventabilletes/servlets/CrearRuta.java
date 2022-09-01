package com.practicafinal.ventabilletes.servlets;

import com.practicafinal.ventabilletes.bbdd.*;
import com.practicafinal.ventabilletes.bbdd.Ruta;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class CrearRuta extends HttpServlet {

    private ModeloDatos bd;

    @Override
    public void init(ServletConfig config) throws ServletException {
        bd = new ModeloDatos();
        bd.abrirConexion();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession s = request.getSession(true);

        int duracion = Integer.parseInt(request.getParameter("duracion"));
        int id_avion = Integer.parseInt(request.getParameter("avion"));
        int id_origen = Integer.parseInt(request.getParameter("origen"));
        int id_destino = Integer.parseInt(request.getParameter("destino"));
        String origen = "";
        String destino = "";
        ArrayList<Aeropuerto> aeropuertos = bd.getAeropuertos();

        String fechaString = request.getParameter("fecha");
        fechaString = fechaString.replace("T", " ");

        String partes[] = fechaString.split(" ");
        String time = partes[1];
        if (time.length() != 8) {
            fechaString += ":00";
        }
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fecha = null;
        try {
            fecha = formatter.parse(fechaString);
        } catch (ParseException ex) {

            System.out.println("Error al parsear fecha: " + ex.getMessage());

        }

        Ruta ruta = new Ruta(fecha, duracion, id_avion, id_origen, id_destino, fechaString);

        bd.insertarRuta(ruta);
        s.setAttribute("accion", "insertado");
        s.setAttribute("tipo", "vuelo");

        for (int i = 0; i < aeropuertos.size(); i++) {
            if (aeropuertos.get(i).getIdAeropuerto() == id_origen) {
                origen = aeropuertos.get(i).getNombre();
            }
            if (aeropuertos.get(i).getIdAeropuerto() == id_destino) {
                destino = aeropuertos.get(i).getNombre();
            }
        }

        s.setAttribute("nombre", origen + " - " + destino + " de " + fecha);
        response.sendRedirect(response.encodeRedirectURL("exito.jsp"));
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
