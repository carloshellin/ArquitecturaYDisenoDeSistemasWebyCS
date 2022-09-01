package com.practicafinal.ventabilletes.servlets;

import com.practicafinal.ventabilletes.bbdd.Billete;
import com.practicafinal.ventabilletes.bbdd.BilleteRuta;
import com.practicafinal.ventabilletes.bbdd.ModeloDatos;
import com.practicafinal.ventabilletes.bbdd.Ruta;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SeleccionarVuelo extends HttpServlet {
    
    private ModeloDatos bd;

    @Override
    public void init(ServletConfig config) throws ServletException
    {
        bd = new ModeloDatos();
        bd.abrirConexion();
    }
    
    public ArrayList<String> generarLocalizadores(int cantidad) {
        Random rd = new Random();
        ArrayList<String> localizadores = new ArrayList<>();
        String letrasNums = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        String localizador = "";
        boolean repetido;

        for (int i = 0; i < cantidad; i++) {
            repetido = false;
            while (!repetido) {
                for (int num = 0; num < 6; num++) {
                    localizador += letrasNums.charAt(rd.nextInt(letrasNums.length()));
                }
                if (!localizadores.contains(localizador)) {
                    localizadores.add(localizador);
                    repetido = true;
                }
                localizador = "";
            }
        }
        return localizadores;
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession s = request.getSession(true); 
        
        int idRuta = Integer.parseInt(request.getParameter("idRuta"));
        
        Ruta vuelo = bd.getRuta(idRuta);
        
        String localizador = generarLocalizadores(1).get(0);
        
        Date Operacion_fecha = new Date();
        
        BilleteRuta billeteRuta = bd.getBilleteRuta(idRuta);
        
        Billete billete = new Billete(localizador, billeteRuta.getBilleteRuta(),
                1, Operacion_fecha);
        
        s.setAttribute("vuelo", vuelo);
        s.setAttribute("billete", billete);
        response.sendRedirect(response.encodeRedirectURL("claseVuelo.jsp"));
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
