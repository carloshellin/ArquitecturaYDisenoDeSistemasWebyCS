/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Primitivas;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bianc
 */
public class CuartoServlet extends HttpServlet {

    int primi[] = new int[6], combiUsuario[] = new int[6];
    int i, contador=0, aux, aciertos=0;
    Random rand = new Random();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param config
     * @throws ServletException if a servlet-specific error occurs
     */
    
    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        //generamos los números
        while (contador<6){
            aux = rand.nextInt(48) + 1;
            if (!comprueba(primi,aux)){
                primi[contador] = aux;
                contador++;
            }
        }
        
        //Ordenamos el array
        Arrays.sort(primi);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CuartoServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CuartoServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            }
    }
    
    private boolean comprueba(int arrayPrimi[], int num){
        for (int i=0; i<=5; i++){
            if (arrayPrimi[i]==num) return true;
        }
        return false;
    }
    
    @Override
    public void service( HttpServletRequest peticion, HttpServletResponse respuesta) throws ServletException, IOException{
        aciertos=0;
            try (ServletOutputStream out = respuesta.getOutputStream()) {
                combiUsuario[0] =
                        Integer.parseInt(peticion.getParameter("NUM1"));
                combiUsuario[1] =
                        Integer.parseInt(peticion.getParameter("NUM2"));
                combiUsuario[2] =
                        Integer.parseInt(peticion.getParameter("NUM3"));
                combiUsuario[3] =
                        Integer.parseInt(peticion.getParameter("NUM4"));
                combiUsuario[4] =
                        Integer.parseInt(peticion.getParameter("NUM5"));
                combiUsuario[5] =
                        Integer.parseInt(peticion.getParameter("NUM6"));
                out.println("Primitiva Servlet");
                //imprimimos todos los números de la combinación del usuario
                out.print("\nTu combinación es:");
                for (i=0; i<6; i++){
                    out.print(" "+combiUsuario[i]);
                }
                //comprobamos la combinación
                for (i=0; i<=5; i++){
                    if (Arrays.binarySearch(primi,combiUsuario[i])>=0){
                        out.println("\nNúmero acertado:"+combiUsuario[i]);
                        aciertos++;
                    }
                }
                out.println("\nNúmeros acertados:"+aciertos);
                //imprimimos todos los números de la combinación ganadora
                out.print("\nLa combinación ganadora es:");
                for (i=0; i<6; i++){
                    out.print(" "+primi[i]);
                }
            }
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
