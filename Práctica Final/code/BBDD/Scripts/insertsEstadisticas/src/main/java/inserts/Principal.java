package inserts;

import entidades.Ruta;
import entidades.Operacion;
import entidades.Cliente;
import entidades.AcompañantesBillete;
import entidades.Acompañante;
import entidades.Billete_Ruta;
import entidades.Avion;
import entidades.Persona;
import entidades.Billete;
import entidades.Tarjeta_Bancaria;
import entidades.Aeropuerto;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) throws IOException, ParseException
    {
        Estadísticas est = new Estadísticas();
        String rutaPersonas = "./personas.txt";
        String rutaClientes = "./clientes.txt";
        String rutaTarjetas = "./tarjetas.txt";
        String rutaAcompañantes = "./acompañantes.txt";
        String rutaOperaciones = "./operaciones.txt";
        String rutaRutas = "./rutas.txt";
        String rutaBilleteRutas = "./billeteRutas.txt";
        String rutaBilletes = "./billetes.txt";
        String rutaAcompBillete = "./acompBilletes.txt";
       
        ArrayList<Persona> personas = est.crearDatosPersonas(rutaPersonas);
        ArrayList<Cliente> clientes = est.crearDatosClientes(personas,rutaClientes);
        ArrayList<Tarjeta_Bancaria> tarjetas = est.crearDatosTarjetas(clientes,rutaTarjetas);
        ArrayList<Acompañante> acomp = est.crearDatosAcompañantes(personas,rutaAcompañantes);
        ArrayList<Operacion> operaciones = est.crearDatosOperaciones(clientes,rutaOperaciones);
        
        ArrayList<Avion> aviones = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
            aviones.add(new Avion((i+1),"Airbus A380",75));
        }
        ArrayList<Aeropuerto> aeropuertosO = new ArrayList<>();
        ArrayList<Aeropuerto> aeropuertosD = new ArrayList<>();
        aeropuertosO.add(new Aeropuerto(1,"Aeropuerto de Barajas", "Avenida de la Hispanidad", "Madrid", "España", "+34 923565887"));
        aeropuertosO.add(new Aeropuerto(2,"Aeropuerto El Prat", "El Prat de Llobregat", "Barcelona", "España", "+34 978456232"));
        aeropuertosD.add(new Aeropuerto(3,"Aeropuerto Charles de Gaulle", "Roissy-en-France", "París", "Francia", "+33 170363950"));
        aeropuertosD.add(new Aeropuerto(4,"Aeropuerto de Londres", "Hartmann Rd", "Londres", "Inglaterra", "+44 207646000"));
        aeropuertosD.add(new Aeropuerto(5,"Aeropuerto de Berlín", "Melli-Beese-Ring", "Berlín", "Alemania", "+49 656342211"));

        
        ArrayList<Ruta> rutas = est.crearDatosRutas(aviones,aeropuertosO,aeropuertosD,rutaRutas);
        ArrayList<Billete_Ruta> billetesRutas = est.crearDatosBilleteRutas(rutas,rutaBilleteRutas);
        ArrayList<Billete> billetes = est.crearDatosBilletes(rutas,operaciones,rutaBilletes);
        ArrayList<AcompañantesBillete> acompBilletes = est.crearAcompañantesBilletes(acomp,billetes,rutaAcompBillete);
        
    } 
}
