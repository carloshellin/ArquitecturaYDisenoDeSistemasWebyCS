package com.practicafinal.ventabilletes.servlets;

import com.practicafinal.ventabilletes.bbdd.Cliente;
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

public class Pago extends HttpServlet {
    
    private ModeloDatos bd;

    @Override
    public void init(ServletConfig config) throws ServletException
    {
        bd = new ModeloDatos();
        bd.abrirConexion();
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
        
        String numTarjeta = ((String) request.getParameter("numTarjeta")).replaceAll("\\s+","");
        
        BigInteger numTarjetabi = new BigInteger(numTarjeta);
        String caducidad = request.getParameter("caducidad");
        String[] caducidades = caducidad.split("/");
        int mesCaducidad = Integer.parseInt(caducidades[0]);
        int añoCaducidad = Integer.parseInt(caducidades[1]);
        int CVV = Integer.parseInt(request.getParameter("CVV"));
        
        String correo = (String) s.getAttribute("cliente");
        Cliente cliente = bd.getCliente(correo);
        
        TarjetaBancaria tarjetaBancaria = new TarjetaBancaria(numTarjetabi,
                mesCaducidad, añoCaducidad, CVV, cliente.getIdCliente());
                
        s.setAttribute("tarjetaBancaria", tarjetaBancaria);
        response.sendRedirect(response.encodeRedirectURL("finalProceso.jsp"));
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